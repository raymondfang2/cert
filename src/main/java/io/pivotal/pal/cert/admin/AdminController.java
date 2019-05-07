package io.pivotal.pal.cert.admin;

import io.pivotal.pal.cert.exam.CertExamService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/admin")
public class AdminController {
    Logger logger = LoggerFactory.getLogger(AdminController.class);

    private CertExamService certService;

    @Autowired
    public AdminController(CertExamService certService) {
        this.certService = certService;
    }


    @GetMapping("setup/{feedSource}") //e.g. PearsonVUE.csv
    public int loadExamRecords(@PathVariable String feedSource) throws Exception {
        logger.info( "====>Loading initial data to DB- " + feedSource);
        //1. load the CSV
        certService.loadExamRecordsToDB(feedSource);
        //2. insertBatch to DB
        return 1;
    }

    @PostMapping("uploadFile")
    public void submit(@RequestParam("file") MultipartFile file) {
        logger.info( "====>fileUploading "+file.getOriginalFilename()+file.getSize());

        //modelMap.addAttribute("file", file);

    }


}
