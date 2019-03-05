package io.pivotal.pal.jdbc;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.sql.Date;

import static org.assertj.core.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//@JdbcTest  TODO: find a way to use JdbcTest instead of SpringBootTest
//@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
public class CertExamRepositoryTest {

    @Autowired
    private CertExamRepository repo;

    @Test
    public void getCertExamSummary() {
        List<CertExamSummary> en = repo.getCertExamSummary("2012-01-01","2018-12-31");
        System.out.println(en.size());
        assertThat(en).size().isGreaterThan(1);
     }

     @Test
    public void getRegionCertExamSummary() {
        List<CertExamSummary> en = repo.getCertExamSummaryByRegion("2012-01-01","2018-12-31","EMEA");
        System.out.println(en.size());
        assertThat(en).size().isGreaterThan(1);
    }

    //TODO: to use @jdbcTest so that records will be deleted automatcially after test
    //@Test
    public void insertBath() {
        List<CertExamRecord> records = new ArrayList<>(1);
        CertExamRecord record= new CertExamRecord();
        record.setDataSource("Pear");
        record.setCompany("Com");
        Date today = new Date(new java.util.Date().getTime());
        record.setCreateDate(today);
        record.setExamDate(today);
        record.setEmail("a@b.com");
        record.setExamCode("ecode");
        record.setExamTitle("eTitle");
        record.setGrade("P");
        record.setFirstName("fname");
        record.setLastName("lname");
        record.setScore(100);
        record.setSiteCountry("Sin");
        record.setSiteRegion("APAC");

        records.add(record);
        int[] result = repo.insertBatch(records);
        assertThat(result[0]==1);


    }


}
