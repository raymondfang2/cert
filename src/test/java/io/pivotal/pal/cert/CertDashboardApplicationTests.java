package io.pivotal.pal.cert;

import io.pivotal.pal.cert.user.CertExamController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.context.junit4.SpringRunner;

import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

import java.util.HashMap;

import static org.assertj.core.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@SqlGroup({
		@Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = "classpath:beforeTestRun.sql"),
		@Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD, scripts = "classpath:afterTestRun.sql")
})
public class CertDashboardApplicationTests {

	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate restTemplate;

	@Autowired
	private CertExamController cc;


	@Test
	public void contextLoads() {
		assertThat(cc).isNotNull();
	}


	@Test
	public void getCertSummary() throws Exception {
		assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/cert/getCertSummary/2019/2019?role=ADMIN",
				Object[].class)).hasAtLeastOneElementOfType(Object.class);

	}

	@Test
	public void getCertSummaryByRegion() throws Exception {
		assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/cert/getCertSummaryByRegion/2019/2019/APAC?role=ADMIN",
				Object[].class)).hasAtLeastOneElementOfType(Object.class);;

	}

	@Test
	public void getRole() throws Exception {
		HashMap<String,String> returnValue = this.restTemplate.getForObject("http://localhost:" + port + "/cert/getRole?role=ADMIN",
				HashMap.class);
		assertThat(returnValue.size()).isEqualTo(1);
		System.out.println("===>"+returnValue);

	}

}
