package io.pivotal.pal.jdbc;

import java.io.File;
import java.io.FileReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;

import static com.fasterxml.jackson.dataformat.csv.CsvSchema.ColumnType.NUMBER;


public class CsvLoaderUtil {

    public static void loadCsv(String path) throws Exception {
        System.out.println("read csv");

        // load file from resource
        ClassLoader classLoader = CsvLoaderUtil.class.getClassLoader();
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
            CertExamRecord re;
            while (mi.hasNext()) {
                re = mi.next();
                System.out.println(re.getFirstName() + " - "+ re.getEmail() + " - "+ re.getExamDate() );
            }
        }
    }

    public static void main (String[] argv) throws  Exception {
        loadCsv("PearsonVUE.csv");
    }

}

