package io.pivotal.pal.cert.admin;

import io.pivotal.pal.cert.exam.CertExamService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


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
    public void submit(@RequestParam("file") MultipartFile file) throws Exception {
        logger.info( "====>fileUploading "+file.getOriginalFilename()+file.getSize());
        //0. delete previous record
        //1. convert to HashMap List
        //2. batch insertion
        //3. merge

        //Step 1 fetch data
        List<String> examCsv = new ArrayList<String>();
        Scanner scan = new Scanner(file.getInputStream());
        while(scan.hasNextLine()){
            examCsv.add(scan.nextLine());
        }



    }


}
