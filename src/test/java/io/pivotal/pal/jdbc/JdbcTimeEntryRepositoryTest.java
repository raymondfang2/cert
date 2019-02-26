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
//@JdbcTest
//@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
public class JdbcTimeEntryRepositoryTest {

    @Autowired
    private JdbcTimeEntryRepository repo;

    @Test
    public void findByID() {
        TimeEntry te = repo.find((long) 1);
        assertThat(te).isNotNull();
        assertThat(te.getId()).isEqualTo(1);
    }

    @Test
    public void list() {
        List<TimeEntry> en = repo.list();
        System.out.println(en.size());
        assertThat(en).size().isEqualTo(1);
     }

}
