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

import java.util.List;

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
        List<CertExamSummary> en = repo.getCertExamSummaryByRegion("2012-01-01","2018-12-31","VUE EMEA");
        System.out.println(en.size());
        assertThat(en).size().isGreaterThan(1);
    }
}
