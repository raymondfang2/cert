package io.pivotal.pal.cert.exam;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestOperations;

import java.io.Writer;
import java.util.*;

@Service
public class CertExamService {
    Logger logger = LoggerFactory.getLogger(CertExamService.class);


    private CertExamRepository certRepo;
    private CsvLoader csvLoader;
    private CsvConverter csvConverter;
    private RestOperations restOperations;

    @Value("${dynamictab.max}")
    private int maxTabNo;
    @Value("${trueability.readOnly}")
    private String trueability_readOnly;
    @Value("${trueability.api}")
    private String trueability_api;

    @Autowired
    public CertExamService(CertExamRepository certRepo, CsvLoader csvLoader, CsvConverter csvConverter, RestOperations restOperations)
    {
        this.certRepo = certRepo;
        this.csvLoader = csvLoader;
        this.csvConverter = csvConverter;
        this.restOperations = restOperations;
    }

    @Transactional
    public int loadExamRecordFromTrueAbilityToStage() throws Exception {
        int totalPage = 0;
        int page = 1;
        while (page!=0) {
           page = loadExamRecordFromTrueAbilityToStage(page);
           totalPage += page;
        }
        return totalPage;
    }

    //Load data from TrueAbility
    @Transactional
    public int loadExamRecordFromTrueAbilityToStage(int currentPage) throws Exception {

        HttpHeaders headers = new HttpHeaders();
        headers.set("accept","application/json");
        headers.set("X-API-KEY",trueability_readOnly);

        final HttpEntity<String> entity = new HttpEntity<String>(headers);

        ResponseEntity<String> response = restOperations.exchange(trueability_api+"?page="+currentPage, HttpMethod.GET, entity, String.class);
        String data = response.getBody();
        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(data);
        JsonNode results = root.path("results");

        List<HashMap<String, String>> trueAbilityData = new ArrayList<HashMap<String, String>>();
        for (JsonNode result : results) {
            HashMap record = new HashMap();
            record.put("CANDIDATE_EMAIL",result.path("user_email").textValue() );
            record.put("EXAM_NAME",result.path("ability_screen").path("name").textValue() );
            record.put("EXAM_STATUS",result.path("user_transcript").path("status").textValue() );
            record.put("VENDOR_RESERVATION_GUID",result.path("vendor_reservation_id").textValue() );
            record.put("VENDOR",result.path("vendor").textValue() );
            record.put("VENDOR_COMPLETION_STATUS",result.path("vendor_completion_status").textValue() );
            record.put("COMPLETED_AT",result.path("completed_at").textValue() );
            trueAbilityData.add(record);
        }

        certRepo.insertBatch("TRUE_ABILITY_RESULT_STAGE",trueAbilityData);

        int nextPage = root.path("meta").path("next_page").intValue();
        logger.info("======>nextPage:"+nextPage);

        return nextPage;
    }

    public int loadExamRecordsToDB (String feedSource) throws Exception {
        logger.info( "====>Loading initial data to DB- " + feedSource);
        //1. load the CSV
        int size = csvLoader.csvToDB(feedSource, certRepo);
        //2. insertBatch to DB
        return size;
    }

    //
    public int generateCsvFile(String startYear, String endYear, Writer writer) throws Exception {
        //TODO: If the data is too big, for memory foot-print
        //we can easily select from DB and generate the CSV batch by batch using limit
        //The similar approach can be used for loading CSV to DB as well

        //1. load the db
        List<CertExamRecord> examRecords = certRepo.getCertExamRecords(startYear, endYear);

        //2. send to writer
        csvLoader.generateCsv(examRecords, writer);
        logger.info("=====>no of records: "+examRecords.size());
        return examRecords.size();
    }

    public List<CertExamSummary> getCertSummary(String start, String end) {
        logger.info("=====>getCertSummary-"+start+"--"+end);
        List<CertExamSummary> summaryList= certRepo.getCertExamSummary(start, end);
        return summaryList;
    }

    public List<CertExamSummary> getCertSummaryByRegion(String start, String end, String region) {
      List<CertExamSummary> summaryList= certRepo.getCertExamSummaryByRegion(start, end, region);
        return summaryList;
    }

    public List<CertExamRecord> getCertExamRecords(String start, String end) {
        logger.info("=====>getCertExamRecords-"+start+"--"+end);
        List<CertExamRecord> examList= certRepo.getCertExamRecords(start, end);
        return examList;
    }

    public List<CertExamRecord> getCertExamRecords(String start, String end, int limit) {
        logger.info("=====>getCertExamRecords-"+start+"--"+end);
        List<CertExamRecord> examList= certRepo.getCertExamRecords(start, end, limit);
        return examList;
    }

    public List<HashMap> getDynamicQueryResult(String sql) {
        logger.info("=====>getDynamicQueryResult-"+sql);
        return certRepo.getDynamicQueryResult(sql);
    }

    /*
        return newTabID
     */
    @Transactional
    public String addDynamicTab(String tabName, String dSql) {
        String newTabID = "-1"; //tabID is String, but from "0" to "4"
        List<String> tabIDs =  certRepo.getDynamicTabIDs();
        if (tabIDs.size()==maxTabNo) return newTabID; //-1 reach the max tab, no insertion

        //else, find the right tabID, tabID is ordered from DB
        for (int i=0; i<tabIDs.size(); i++) {
            if (tabIDs.get(i).equals(""+i)) continue;
            else {
                newTabID = ""+i;
                break;
            }
        }

        if (newTabID.equals("-1")) {
            newTabID = ""+tabIDs.size();
        }

        this.addDynamicTab(newTabID, tabName, dSql);
        return newTabID;
    }

    @Transactional
    public int addDynamicTab(String tabID, String tabName, String dSql) {
        return certRepo.addDynamicTab(tabID,tabName, dSql);
    }

    @Transactional
    public int deleteDynamicTab(String tabID) {
        return certRepo.deleteDynamicTab(tabID);
    }

    public List<String> getDynamicTabIDs() {
        return certRepo.getDynamicTabIDs();
    }

    public List<HashMap> getDynamicTabIDNAMEs() {
        return certRepo.getDynamicTabIDNAMEs();
    }

    public HashMap getDynamicTabByID(String tabID) {
        return certRepo.getDynamicTabByID(tabID);
    }

    /*
        return value: { tab_name: My Report, searchResult: List<HashMap> }
     */
    public HashMap getDynamicTabContent(String tabID) {
        HashMap result = new HashMap();
        HashMap tabRecord = certRepo.getDynamicTabByID(tabID);
        result.put("tab_name", tabRecord.get("tab_name") );

        List<HashMap> searchResult = certRepo.getDynamicQueryResult((String) tabRecord.get("dsql"));
        result.put("searchResult", searchResult);

        return result;
    }

    public String getRoleByEmail(String email) {
        return certRepo.getRoleByEmail(email);
    }

    @Transactional
    public int uploadCSV(String examCenter, List<String> csv) {
        //1. delete the previous batch
        certRepo.truncateStageTable();
        //2. insert into stage, process null etc properly in  CSVConverter, set the exam_center with update,
        int[] no = certRepo.insertBatch("CERT_EXAM_STAGE",csvConverter.csvSplit(csv));
        certRepo.updateStageExamCenter("PSI"); //at present, only one exam_center
        //3. merge to main table
        certRepo.mergeStage2Main();

        return no[0]; //return the no of records uploaded
    }

    @Transactional
    public int loadDataFromTrueAbility() throws Exception {
        //1. delete the previous batch
        certRepo.truncateTAStageTable();
        //2. load TA data for TA stage
        int totalPages = loadExamRecordFromTrueAbilityToStage();
        //3. merge to main table with the PSI data uploaded with CSV --> ref to above method
        certRepo.mergeTAStage2Main();

        return totalPages;
    }

    //Regions
    private final String AMER="AMERICA";
    private final String EMEA="EMEA";
    private final String APAC="APAC";
    private final String UNKNOWN="UNKNOWN REGION";
    private final String ALL="ALL";
    //Column names in report
    private final String EXAM_NAME="EXAM_NAME";
    private final String CERTIFIED="CERTIFIED";
    private final String DELIVERED="DELIVERED";
    private final String PASS_RATE="PASS_RATE";
    public List<String> generateSummaryReport(String start, String end) {
        //
        logger.info("=====>generateSummaryReport-" + start + "--" + end);
        List<HashMap> amer = certRepo.getCertSummaryReport(start, end, AMER);
        List<HashMap> emea = certRepo.getCertSummaryReport(start, end, EMEA);
        List<HashMap> apj = certRepo.getCertSummaryReport(start, end, APAC);
        List<HashMap> unknown = certRepo.getCertSummaryReport(start, end, UNKNOWN);
        List<HashMap> allRegions = certRepo.getCertSummaryReport(start, end, ALL);

        List<HashMap> resultList = new ArrayList<HashMap>();
        //merge the regions into all ==> do pivot
        HashMap allRegionsRecord;
        for(int i=0; i<allRegions.size(); i++) {
            LinkedHashMap resultRecord = new LinkedHashMap(); //To keep the insert order
            allRegionsRecord = allRegions.get(i);

            String current_exam_name = (String) allRegionsRecord.get(EXAM_NAME);
            pivotRegionSummary(resultRecord,current_exam_name,amer,AMER);
            pivotRegionSummary(resultRecord,current_exam_name,emea,EMEA);
            pivotRegionSummary(resultRecord,current_exam_name,apj,APAC);
            pivotRegionSummary(resultRecord,current_exam_name,unknown,UNKNOWN);


            //ADD BACK the All REGION record at last (the very left side csv)
            resultRecord.put(ALL+"-"+CERTIFIED, allRegionsRecord.get(CERTIFIED));
            resultRecord.put(ALL+"-"+DELIVERED, allRegionsRecord.get(DELIVERED));
            resultRecord.put(ALL+"-"+PASS_RATE, allRegionsRecord.get(PASS_RATE));

            resultList.add(resultRecord);

        }


        List<String> result = csvConverter.csvGenerate(resultList);
        return result;
    }

    private void  pivotRegionSummary(HashMap resultRecord, String current_exam_name, List<HashMap> regionList, String regionName ) {
        String certified = "0";
        String delivered = "0";
        String pass_rate = "N/A";
        for (HashMap regionRecord: regionList) {
            if (regionRecord.get(EXAM_NAME).equals(current_exam_name)) {
                //find the same exam in this region
                certified = regionRecord.get(CERTIFIED).toString();
                delivered = (String)regionRecord.get(DELIVERED).toString();
                pass_rate = (String)regionRecord.get(PASS_RATE).toString();
                break;
            }
        }

        resultRecord.put(EXAM_NAME, current_exam_name);
        resultRecord.put(regionName+"-"+CERTIFIED, certified);
        resultRecord.put(regionName+"-"+DELIVERED, delivered);
        resultRecord.put(regionName+"-"+PASS_RATE, pass_rate);

    }

}
