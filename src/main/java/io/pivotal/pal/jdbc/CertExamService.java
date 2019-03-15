package io.pivotal.pal.jdbc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class CertExamService {
    Logger logger = LoggerFactory.getLogger(CertExamService.class);


    private CertExamRepository certRepo;
    private CsvLoader csvLoader;

    @Autowired
    public CertExamService(CertExamRepository certRepo, CsvLoader csvLoader) {
        this.certRepo = certRepo;
        this.csvLoader = csvLoader;
    }

    public int loadExamRecordsToDB (String feedSource) throws Exception {
        logger.info( "====>Loading initial data to DB- " + feedSource);
        //1. load the CSV
        int size = csvLoader.csvToDB(feedSource, certRepo);
        //2. insertBatch to DB
        return size;
    }

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

    public int addDynamicTab(String tabID, String tabName, String dSql) {
        return certRepo.addDynamicTab(tabID,tabName, dSql);
    }

    public List<String> getDynamicTabIDs() {
        return certRepo.getDynamicTabIDs();
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

}
