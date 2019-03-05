package io.pivotal.pal.jdbc;

import java.io.File;
import java.io.FileReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.fasterxml.jackson.dataformat.csv.CsvSchema.ColumnType.NUMBER;

import com.google.common.collect.Iterators; //batch processing
import org.springframework.stereotype.Component;

@Component
public class CsvLoader {
    Logger logger = LoggerFactory.getLogger(CsvLoader.class);

    public  void loadCsv(String path, CertExamRepository certRepo) throws Exception {
        logger.info("reading csv...");

        // load file from resource
        ClassLoader classLoader = CsvLoader.class.getClassLoader();
        File file = new File(classLoader.getResource(path).getFile());

        /*
        First Name,Last Name,Name,Email(lookup),Company(lookup),Region,Country,Code,Title,ExamDate,Score,Grade,Language
         */
        // configure the schema we want to read
        CsvSchema schema = CsvSchema.builder()
                .addColumn("firstName")
                .addColumn("lastName")
                .addColumn("name")
                .addColumn("email")
                .addColumn("company")
                .addColumn("siteRegion")
                .addColumn("siteCountry")
                .addColumn("examCode")
                .addColumn("examTitle")
                .addColumn("examDate")
                .addColumn("score", NUMBER)
                .addColumn("grade")
                .addColumn("language")
                .build().withHeader();
        CsvMapper mapper = new CsvMapper();
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        ObjectReader oReader = mapper.readerFor(CertExamRecord.class).with(schema);

        // read from file
        try (Reader reader = new FileReader(file)) {
            MappingIterator<CertExamRecord> mi = oReader.readValues(reader);
            //Use Guava Iterator to simplify code - to do batch processing
            Iterator<List<CertExamRecord>> partitionResult = Iterators.partition(mi,500);
            while (partitionResult.hasNext()) {
                List<CertExamRecord> subset = partitionResult.next();
                logger.info("=====>subset size: "+subset.size());
                /*
                CertExamRecord record;
                for (int i=0; i<subset.size(); i++) {
                    record = subset.get(i);
                    //logger.info(record.getFirstName()+ " " +record.getEmail());
                    if ((record.getEmail()==null) ||(record.getEmail().isEmpty())) {
                        logger.error("+++email empty!!!");
                    }
                }
                */
                certRepo.insertBatch(subset);

            }
        }
    }

}

