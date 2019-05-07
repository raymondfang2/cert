package io.pivotal.pal.cert;

import io.pivotal.pal.cert.user.CertExamController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import static org.assertj.core.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
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


	//@Test
	public void getCertSummary() throws Exception {
		assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/cert/getCertSummary/2012/2018",
				Object[].class)).hasAtLeastOneElementOfType(Object.class);

	}

	//@Test
	public void getCertSummaryByRegion() throws Exception {
		assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/cert/getCertSummaryByRegion/2012/2018/EMEA",
				Object[].class)).hasAtLeastOneElementOfType(Object.class);;

	}

}
