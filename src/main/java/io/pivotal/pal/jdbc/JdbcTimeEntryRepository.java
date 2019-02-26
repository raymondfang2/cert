package io.pivotal.pal.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class JdbcTimeEntryRepository {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public JdbcTimeEntryRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public TimeEntry find(Long id) {
        return jdbcTemplate.queryForObject("SELECT id, project_id, user_id, date, hours FROM time_entries WHERE id = ?",
                new Object[]{id}, mapper);
    }

    public List<TimeEntry> list() {
        return jdbcTemplate.query("SELECT id, project_id, user_id, date, hours FROM time_entries",
                 mapper);
    }

    private final RowMapper<TimeEntry> mapper = (rs, rowNum) ->
       new TimeEntry(
                    rs.getLong("id"),
                    rs.getLong("project_id"),
                    rs.getLong("user_id"),
                    rs.getDate("date"),
                    rs.getInt("hours")
            );

}
