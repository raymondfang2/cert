package io.pivotal.pal.cert;


import io.pivotal.pal.cert.exam.CertExamRecord;
import io.pivotal.pal.cert.exam.CertExamRepository;
import io.pivotal.pal.cert.exam.CertExamSummary;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.sql.Date;

import static org.assertj.core.api.Assertions.*;

@RunWith(SpringRunner.class)
@DataJdbcTest
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
@Import(CertExamRepository.class) //Somehow @DataJdbcTest not able to load @Repository, Import to force it happen
@Transactional //This will cause each test rollback after testing, also rollback the beforeTestRun.sql
@SqlGroup({
        @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = "classpath:beforeTestRun.sql"),
       // @Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD, scripts = "classpath:afterTestRun.sql")
})
public class CertExamRepositoryTest {

    @Autowired
    private CertExamRepository repo;


    @Test
    public void getCertExamSummary() {
        List<CertExamSummary> en = repo.getCertExamSummary("2019-01-01","2019-01-02");
        System.out.println("=====>"+en.size());
        for (CertExamSummary es: en) {
            System.out.println("=====>"+es.getPivotalExamCode()+":"+es.getPassCount()+":"+es.getTotal());
        }
        assertThat(en).size().isGreaterThan(0);
     }

     @Test
    public void getRegionCertExamSummary() {
        List<CertExamSummary> en = repo.getCertExamSummaryByRegion("2019-01-01","2019-01-02","APAC");
        System.out.println(en.size());
        assertThat(en).size().isGreaterThan(0);
    }

    @Test
    public void getCertExamRecords() {
        List<CertExamRecord> result = repo.getCertExamRecords("2019-01-01","2019-01-02",50);
        System.out.println(result.size());
        assertThat(result).size().isGreaterThan(0);
    }


    @Test
    public void getDynamicQueryResult() {
        String sql = "select CANDIDATE_EMAIL from  CERT_EXAM_VW  where EXAM_CENTER='TEST' ";
        List<HashMap> result =  repo.getDynamicQueryResult(sql);
        System.out.println("===>"+result.get(0).get("CANDIDATE_EMAIL"));
        assertThat(result).size().isEqualTo(1);
    }

    @Test
    public void insertBath() {
        //Test 1 OLD: for olde CERT_EXAM_RESULT --> PEARSON VUE DATA, DEPREATE SOON
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

        //Test 2: NEW: FOR CERT_EXAM -->PSI DATA
        List<HashMap<String, String>> data = new ArrayList<HashMap<String, String>>();

        LinkedHashMap<String, String> single = new LinkedHashMap<>();
        single.put("EXAM_CENTER","EXAM_CENTER");
        single.put("FIRST_NAME","FIRST_NAME");
        single.put("LAST_NAME","LAST_NAME");
        data.add(single);
        result = repo.insertBatch("CERT_EXAM_STAGE",data);
        assertThat(result[0]==1);



    }

    @Test
    public void allDynamicTab() {
        int result =  repo.addDynamicTab("dTabTest","My Report","SELECT EXAM_NAME, CANDIDATE_EMAIL FROM CERT_EXAM \n" +
                "where EXAM_CENTER='TEST'");
       assertThat(result).isEqualTo(1);


        List<String> result1 = repo.getDynamicTabIDs();
        assertThat(result1).size().isGreaterThan(0);


        HashMap result2 = repo.getDynamicTabByID("dTabTest");
        assertThat(result2).size().isGreaterThan(1);

        result = repo.deleteDynamicTab("dTabTest");
        assertThat(result).isEqualTo(1);

    }

    @Test
    public void getRoleByEmail() {
        String result = repo.getRoleByEmail("TEST@TEST.COM");
        System.out.println("===>"+result);
        assertThat(result).isEqualTo("TESTER");
    }


    @Test
    public void getCertSummaryReport() {
        List<HashMap> result = repo.getCertSummaryReport("2019-01-01","2019-12-12 23:59:59","EMEA");
        System.out.println(result.toString());
    }
}
