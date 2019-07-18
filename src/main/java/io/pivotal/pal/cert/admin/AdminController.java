package io.pivotal.pal.cert.admin;

import io.pivotal.pal.cert.exam.CertExamService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.HashMap;
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


    @GetMapping("setup/{feedSource}") //e.g. PearsonVUE.csv --deprecated
    public int loadExamRecords(@PathVariable String feedSource) throws Exception {
        logger.info( "====>Loading initial data to DB- " + feedSource);
        //1. load the CSV
        certService.loadExamRecordsToDB(feedSource);
        //2. insertBatch to DB
        return 1;
    }

    @GetMapping("loadDataFromTA") //Load Data From True ability
    public int loadExamRecordsFromTA() throws Exception {
        logger.info( "====>Loading data TrueAbility " );

        int number = certService.loadExamRecordFromTrueAbility(1);
        //2. insertBatch to DB
        return 1;
    }

    @PostMapping("uploadFile")
    public String submit(@RequestParam("file") MultipartFile file) throws Exception {
        logger.info( "====>fileUploading "+file.getOriginalFilename()+file.getSize());

        //Step 1 fetch data
        List<String> examCsv = new ArrayList<String>();
        Scanner scan = new Scanner(file.getInputStream());
        while(scan.hasNextLine()){
            examCsv.add(scan.nextLine());
        }
        //Step 2, insert into Stage
        logger.info("====> start to load into stage DB, csvSize"+ examCsv.size());
        certService.uploadCSV("PSI",examCsv);

        logger.info("====> Upload done!");
        return "true";
    }

    @GetMapping("getDynamicQueryResult")
    public List<HashMap> getDynamicQueryResult(@RequestParam("dsql") String sql) {
        logger.info("==>"+sql);
        return certService.getDynamicQueryResult(sql);
    }


    @PostMapping("addDynamicTab")
    public String addDynamicTab(@RequestParam("tabName") String tabName, @RequestParam("dsql") String dSql) {
        return certService.addDynamicTab(tabName,dSql);
    }

    @DeleteMapping("deleteDynamicTab/{tabID}")
    public int deleteDynamicTab(@PathVariable String tabID) {
        return certService.deleteDynamicTab(tabID);
    }

}
