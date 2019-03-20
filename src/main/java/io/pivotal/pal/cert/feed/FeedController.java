package io.pivotal.pal.cert.feed;

import io.pivotal.pal.cert.exam.CertExamService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
public class FeedController {
    Logger logger = LoggerFactory.getLogger(FeedController.class);

    private CertExamService certService;

    @Autowired
    public FeedController(CertExamService certService) {
        this.certService = certService;
    }


    @PostMapping("setup/{feedSource}") //e.g. PearsonVUE.csv
    public int loadExamRecords(@PathVariable String feedSource) throws Exception {
        logger.info( "====>Loading initial data to DB- " + feedSource);
        //1. load the CSV
        certService.loadExamRecordsToDB(feedSource);
        //2. insertBatch to DB
        return 1;
    }



}
