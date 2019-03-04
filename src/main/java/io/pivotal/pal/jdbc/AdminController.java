package io.pivotal.pal.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {
    private CertExamRepository certRepo;

    @Autowired
    public AdminController(CertExamRepository certRepo) {
        this.certRepo = certRepo;
    }

    @GetMapping("setup/{feedSource}")
    public String getCertSummary(@PathVariable String feedSource) {
        return "Loading initial data - " + feedSource;
    }



}
