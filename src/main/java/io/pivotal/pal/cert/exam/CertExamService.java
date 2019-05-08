package io.pivotal.pal.cert.exam;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Writer;
import java.util.HashMap;
import java.util.List;

@Service
public class CertExamService {
    Logger logger = LoggerFactory.getLogger(CertExamService.class);


    private CertExamRepository certRepo;
    private CsvLoader csvLoader;
    private CsvConverter csvConverter;

    @Value("${dynamictab.max}")
    private int maxTabNo;

    @Autowired
    public CertExamService(CertExamRepository certRepo, CsvLoader csvLoader, CsvConverter csvConverter) {
        this.certRepo = certRepo;
        this.csvLoader = csvLoader;
        this.csvConverter = csvConverter;
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

        return no[0];
    }
}
