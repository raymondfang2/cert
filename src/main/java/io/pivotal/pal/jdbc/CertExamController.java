package io.pivotal.pal.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/cert")
public class CertExamController {
    private CertExamRepository certRepo;

    @Autowired
    public CertExamController(CertExamRepository certRepo) {
        this.certRepo = certRepo;
    }

    @GetMapping("getCertSummary/{startYear}/{endYear}")
    public List<CertExamSummary> getCertSummary(@PathVariable String startYear, @PathVariable String endYear) {
        String start = startYear + "-01-01"; //The MySQL default date format
        String end = endYear + "-12-31";
        List<CertExamSummary> summaryList= certRepo.getCertExamSummary(start, end);
        return summaryList;
    }

    @GetMapping("getCertSummaryByRegion/{startYear}/{endYear}/{region}")
    public List<CertExamSummary> getCertSummaryByRegion(@PathVariable String startYear, @PathVariable String endYear, @PathVariable String region) {
        String start = startYear + "-01-01"; //The MySQL default date format
        String end = endYear + "-12-31";
        List<CertExamSummary> summaryList= certRepo.getCertExamSummaryByRegion(start, end, region);
        return summaryList;
    }

}
