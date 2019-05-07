package io.pivotal.pal.cert.csvutil;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.google.common.collect.Iterators;
import io.pivotal.pal.cert.admin.CsvLoader;
import io.pivotal.pal.cert.exam.CertExamRecord;
import io.pivotal.pal.cert.exam.CertExamRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static com.fasterxml.jackson.dataformat.csv.CsvSchema.ColumnType.NUMBER;

/*
To convert Csv to List of HashMap and vice verser
Since our csv is like following,
'field1','field2','filed3'
we use seperator "','" 3 chars in the double quote
Not using JackSon CSV Mapper, use String split directly to handle leading and ending extra "'"
 */
public class CsvConverter {
    Logger logger = LoggerFactory.getLogger(CsvConverter.class);

    private CsvSchema setupCsvSchema() {
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
        return schema;
    }

    //Load a CSV file from classpath into DB
    public  int csvToDB(String path, CertExamRepository certRepo) throws Exception {
        logger.info("reading csv..."+path);
        int size = 0;
        // load file from resource
        ClassLoader classLoader = CsvLoader.class.getClassLoader();
        File file = new File(classLoader.getResource(path).getFile());
        logger.info("=====>file opened:"+file.getAbsolutePath());
        /*
        First Name,Last Name,Name,Email(lookup),Company(lookup),Region,Country,Code,Title,ExamDate,Score,Grade,Language
         */
        // configure the schema we want to read
        CsvSchema schema = setupCsvSchema();
        CsvMapper mapper = new CsvMapper();
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        ObjectReader oReader = mapper.readerFor(CertExamRecord.class).with(schema);

        // read from file
        try (Reader reader = new FileReader(file)) {
            logger.info("====>in FileReader...");
            MappingIterator<CertExamRecord> mi = oReader.readValues(reader);
            //size = mi.readAll().size(); This will cause the iterator to point to the end.
            //logger.info("=====>size of file:"+size);
            //Use Guava Iterator to simplify code - to do batch processing
            int i=0;
            Iterator<List<CertExamRecord>> partitionResult = Iterators.partition(mi,500);
            while (partitionResult.hasNext()) {
                i++;
                List<CertExamRecord> subset = partitionResult.next();
                logger.info("=====>subset size: "+subset.size());
                size += subset.size();
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
                logger.info("======> finish a batch:" + i);

            }
        }
        return size; //At the moment
    }

    public void generateCsv(List<CertExamRecord> examRecords, Writer writer) throws Exception {
        CsvSchema schema = setupCsvSchema();
        CsvMapper mapper = new CsvMapper();
        mapper.configure(JsonGenerator.Feature.IGNORE_UNKNOWN, true);
        // mapper.enable(CsvParser.Feature.WRAP_AS_ARRAY);
        //The following is for one batch
        ObjectWriter objectWriter = mapper.writer(schema.withLineSeparator("\n"));
        SequenceWriter seqw = objectWriter.writeValues(writer);
        seqw.writeAll(examRecords);

        seqw.flush();
        seqw.close();
    }

    //Load a data from DB table and generate a CSV
    public static void main(String[] args) throws Exception {

        List<CertExamRecord> exams = new ArrayList<>();
        CertExamRecord exam =
                new CertExamRecord(1,"Pearson VUE", new Date(new java.util.Date().getTime()), null, "r@b.com", "firstName", "lastName", "Pivotal", "APH", "Singapore", "CoreSpring", "CoreSpring 4.5", new Date(new java.util.Date().getTime()), 90, "Pass");
        CertExamRecord exam2 =
                new CertExamRecord(2,"Pearson VUE", new Date(new java.util.Date().getTime()), null, "r@b.com", "firstName", "lastName", "Pivotal", "APH", "Singapore", "CoreSpring", "CoreSpring 4.5", null, 90, "Pass");
        exams.add(exam);
        exams.add(exam2);

        File file = new File("test.csv");
        Writer writer = new FileWriter(file);

        CsvLoader l = new CsvLoader();
        l.generateCsv(exams, writer);

    }
}
