package io.pivotal.pal.jdbc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public int loadExamRecords(String feedSource) throws Exception {
        logger.info( "====>Loading initial data to DB- " + feedSource);
        //1. load the CSV
        csvLoader.loadCsv(feedSource, certRepo);
        //2. insertBatch to DB
        return 1;
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

}
