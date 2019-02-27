package io.pivotal.pal.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class CertExamRepository {
    private final JdbcTemplate jdbcTemplate;

    /*---2. final query
select m.pivotal_code,  r.site_region, r.site_country,  r.grade, count(*) as candidates
from cert_exam_result r, exam_code_map m
where r.data_source = m.data_source and r.exam_code = m.exam_code
and r.exam_date>'2012-01-01'  and r.site_region='VUE EMEA'
group by pivotal_code, site_region, site_country,  grade
order by pivotal_code, site_region, site_country, grade*/


    @Autowired
    public CertExamRepository(DataSource dataSource) {
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
