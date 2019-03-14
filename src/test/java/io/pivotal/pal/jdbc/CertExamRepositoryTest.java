package io.pivotal.pal.jdbc;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.sql.Date;

import static org.assertj.core.api.Assertions.*;

@RunWith(SpringRunner.class)
@DataJdbcTest
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
@Import(CertExamRepository.class) //Somehow @DataJdbcTest not able to load @Repository, Import to force it happen
@Transactional //This will cause each test rollback after testing
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

    @Test
    public void getCertExamRecords() {
        List<CertExamRecord> result = repo.getCertExamRecords("2012-01-01","2018-12-31",50);
        System.out.println(result.size());
        assertThat(result).size().isEqualTo(50);
    }

    @Test
    public void getCountryList() {
        List<String> result = repo.getCountryList();
        System.out.println(result.size());
        assertThat(result).size().isGreaterThan(10);
    }

    @Test
    public void getDynamicQueryResult() {
        String sql = "select candidate_email, score from  cert_exam_result  limit 10 ";
        List<HashMap> result =  repo.getDynamicQueryResult(sql);
        System.out.println("===>"+result.get(1).get("candidate_email"));
        assertThat(result).size().isEqualTo(10);
    }

    @Test
    public void insertBath() {
        List<CertExamRecord> records = new ArrayList<>(1);
        CertExamRecord record= new CertExamRecord();
        record.setDataSource("PearTest2");
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

    @Test
    public void addDynamicTab() {
        int result =  repo.addDynamicTab("DTAB1","My Report","SELECT select site_country, candidate_email, exam_code, score, grade from  cert_exam_result \n" +
                "where site_country='Singapore' limit 10");
       assertThat(result).isEqualTo(1);
    }

    //addDynamicTab(String tabID, String tabName, String dSql)
}
