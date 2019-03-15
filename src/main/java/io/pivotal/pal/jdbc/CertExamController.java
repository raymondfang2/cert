package io.pivotal.pal.jdbc;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/cert")
public class CertExamController {
    Logger logger = LoggerFactory.getLogger(CertExamController.class);

    private CertExamService examService;


    @Autowired
    public CertExamController(CertExamService examService) {
        this.examService = examService;

    }

    @GetMapping("getCertSummary/{startYear}/{endYear}")
    public List<CertExamSummary> getCertSummary(@PathVariable String startYear, @PathVariable String endYear) {

        String start = startYear + "-01-01"; //The MySQL default date format
        String end = endYear + "-12-31";
        logger.info("=====>getCertSummary-" + start + "--" + end);
        List<CertExamSummary> summaryList = examService.getCertSummary(start, end);
        return summaryList;
    }

    @GetMapping("getCertSummaryByRegion/{startYear}/{endYear}/{region}")
    public List<CertExamSummary> getCertSummaryByRegion(@PathVariable String startYear, @PathVariable String endYear, @PathVariable String region) {
        String start = startYear + "-01-01"; //The MySQL default date format
        String end = endYear + "-12-31";
        List<CertExamSummary> summaryList = examService.getCertSummaryByRegion(start, end, region);
        return summaryList;
    }

    @GetMapping("getCertExamRecords/{startYear}/{endYear}")
    public List<CertExamRecord> getCertExamRecords(@PathVariable String startYear, @PathVariable String endYear) {

        String start = startYear + "-01-01"; //The MySQL default date format
        String end = endYear + "-12-31";
        logger.info("=====>getCertExamRecords-" + start + "--" + end);
        List<CertExamRecord> examList = examService.getCertExamRecords(start, end, 50);//TODO: get from property file later
        return examList;
    }

    @RequestMapping(value = "/downloadExamRecord/{startYear}/{endYear}")
    public void downloadCSV(@PathVariable String startYear, @PathVariable String endYear, HttpServletResponse response) throws Exception {
        String csvFileName = "examRecord.csv";

        startYear = startYear + "-01-01"; //The MySQL default date format
        endYear = endYear + "-12-31";

        response.setContentType("text/csv");

        String headerKey = "Content-Disposition";
        String headerValue = String.format("attachment; filename=\"%s\"",
                csvFileName);
        response.setHeader(headerKey, headerValue);

        examService.generateCsvFile(startYear, endYear, response.getWriter());

    }

    //TODO: change to Body to send the QUERY FOR LONG STRING - AngularJS to be changed accordingly
    @GetMapping("getDynamicQueryResult/{sql}")
    public List<HashMap> getDynamicQueryResult(@PathVariable String sql) {
        return examService.getDynamicQueryResult(sql);
    }

    //TODO: change to POST (INSERTION) - AngularJS to be changed accordingly
    @GetMapping("addDynamicTab/{tabID}/{tabName}/{dSql}")
    public int addDynamicTab(@PathVariable String tabID, @PathVariable String tabName, @PathVariable String dSql) {
        return examService.addDynamicTab(tabID,tabName,dSql);
    }

    //TODO: change to POST (INSERTION) - AngularJS to be changed accordingly
    @GetMapping("deleteDynamicTab/{tabID}")
    public int deleteDynamicTab(@PathVariable String tabID) {
        return examService.deleteDynamicTab(tabID);
    }

    @GetMapping("getDynamicTabIDs")
    public List<String> getDynamicTabIDs() {
        return examService.getDynamicTabIDs();
    }

    @GetMapping("getDynamicTabByID/{tabID}")
    public HashMap getDynamicTabByID(@PathVariable String tabID) {
        return examService.getDynamicTabByID(tabID);
    }

    /*
        return value: { tab_name: "My Report", searchResult: List<HashMap> }
     */
    @GetMapping("getDynamicTabByRoutePath/{routePath}")
    public HashMap getDynamicTabContentByRoutePath(@PathVariable String routePath) {
        //routePath - "dynamicTab1"
        String tabID = routePath.substring(10); //fetch the number part of above string
        return examService.getDynamicTabContent(tabID);
    }

    public static void main(String[] argv) {
        System.out.println("dynamicTab1".substring(10));
    }
}