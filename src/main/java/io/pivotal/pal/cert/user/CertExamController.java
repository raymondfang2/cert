package io.pivotal.pal.cert.user;


import io.pivotal.pal.cert.exam.CertExamRecord;
import io.pivotal.pal.cert.exam.CertExamService;
import io.pivotal.pal.cert.exam.CertExamSummary;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;
import java.util.ArrayList;
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



    @GetMapping("getCertSummary/{startDate}/{endDate}")
    public List<CertExamSummary> getCertSummary(@PathVariable String startDate, @PathVariable String endDate) {

        String start = startDate; //The MySQL default date format
        String end = endDate + " 23:59:59";
        logger.info("=====>getCertSummary-" + start + "--" + end);
        List<CertExamSummary> summaryList = examService.getCertSummary(start, end);
        return summaryList;
    }

    @GetMapping("getCertSummaryByRegion/{startDate}/{endDate}/{region}")
    public List<CertExamSummary> getCertSummaryByRegion(@PathVariable String startDate, @PathVariable String endDate, @PathVariable String region) {
        String start = startDate; //The MySQL default date format
        String end = endDate + " 23:59:59";
        List<CertExamSummary> summaryList = examService.getCertSummaryByRegion(start, end, region);
        return summaryList;
    }

    @GetMapping("getCertExamRecords/{startYear}/{endYear}")
    public List<CertExamRecord> getCertExamRecords(@PathVariable String startYear, @PathVariable String endYear) {

        String start = startYear + "-01-01"; //The MySQL default date format
        String end = endYear + "-12-31 23:59:59";
        logger.info("=====>getCertExamRecords-" + start + "--" + end);
        List<CertExamRecord> examList = examService.getCertExamRecords(start, end, 50);//TODO: get from property file later
        return examList;
    }

    @RequestMapping(value = "/downloadExamRecord/{startYear}/{endYear}")
    public void downloadCSV(@PathVariable String startYear, @PathVariable String endYear, HttpServletResponse response) throws Exception {
        String csvFileName = "examRecord.csv";

        startYear = startYear + "-01-01"; //The MySQL default date format
        endYear = endYear + "-12-31 23:59:59";

        response.setContentType("text/csv");

        String headerKey = "Content-Disposition";
        String headerValue = String.format("attachment; filename=\"%s\"",
                csvFileName);
        response.setHeader(headerKey, headerValue);

        examService.generateCsvFile(startYear, endYear, response.getWriter());

    }

    @RequestMapping(value = "/downloadCertSummary/{startDate}/{endDate}")
    public void downloadSummarytCSV(@PathVariable String startDate, @PathVariable String endDate, HttpServletResponse response) throws Exception {
        String csvFileName = "certSummary-"+startDate+"-"+endDate+".csv";

        String start = startDate; //The MySQL default date format
        String end = endDate + " 23:59:59";

        //1. AMER Regions
        logger.info("=====>getCertSummary-" + start + "--" + end);
        List<CertExamSummary> amer = examService.getCertSummaryByRegion(start, end, "AMERICA");
        List<CertExamSummary> emea = examService.getCertSummaryByRegion(start, end, "EMEA");
        List<CertExamSummary> apj = examService.getCertSummaryByRegion(start, end, "APAC");
        List<CertExamSummary> unknowns = examService.getCertSummaryByRegion(start, end, "UNKNOWN REGION");
        List<CertExamSummary> allRegions = examService.getCertSummary(start, end);

        response.setContentType("text/csv");

        String headerKey = "Content-Disposition";
        String headerValue = String.format("attachment; filename=\"%s\"",
                csvFileName);
        response.setHeader(headerKey, headerValue);

        //TODO: test only
        PrintWriter writer = response.getWriter();
        writer.write("a,b,c");
        writer.flush();
        writer.close();

    }

    @GetMapping("getDynamicQueryResult")
    public List<HashMap> getDynamicQueryResult(@RequestParam("dsql") String sql) {
        logger.info("==>"+sql);
        return examService.getDynamicQueryResult(sql);
    }


    @PostMapping("addDynamicTab")
    public String addDynamicTab(@RequestParam("tabName") String tabName, @RequestParam("dsql") String dSql) {
        return examService.addDynamicTab(tabName,dSql);
    }

    @DeleteMapping("deleteDynamicTab/{tabID}")
    public int deleteDynamicTab(@PathVariable String tabID) {
        return examService.deleteDynamicTab(tabID);
    }

    @GetMapping("getDynamicTabIDs")
    public List<String> getDynamicTabIDs() {
        return examService.getDynamicTabIDs();
    }

    @GetMapping("getDynamicTabIDNAMEs")
    public List<HashMap> getDynamicTabIDNAMEs() {
        return examService.getDynamicTabIDNAMEs();
    }

    @GetMapping("getRole")
    public HashMap<String, String> getRole(HttpSession session) {
        String role = (String) session.getAttribute("PALCERT-ROLE");
        logger.info("=====>"+role);
        HashMap<String, String> result =  new HashMap<String, String>();
        result.put("ROLE", role);
        return result;
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