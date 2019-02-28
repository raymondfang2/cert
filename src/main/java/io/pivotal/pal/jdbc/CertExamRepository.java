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

    private final String ALL_SUMMARY = "select 'ALL' as region, m.pivotal_code, " +
            "      count(case when grade='pass' then grade end) pass, " +
            "      count(case when grade='fail' then grade end) fail, " +
            "      count(case when grade='refused' then grade end) refused " +
            "from cert_exam_result r, exam_code_map m " +
            "where r.data_source = m.data_source and r.exam_code = m.exam_code " +
            "and r.exam_date>=?  AND r.exam_date<=? " +
            "group by pivotal_code " +
            "order by pivotal_code ";

    private final String REGION_SUMMARY = "select substr(r.site_region,5) as region, m.pivotal_code, " +
            "      count(case when grade='pass' then grade end) pass, " +
            "      count(case when grade='fail' then grade end) fail, " +
            "      count(case when grade='refused' then grade end) refused " +
            "from cert_exam_result r, exam_code_map m " +
            "where r.data_source = m.data_source  and r.exam_code = m.exam_code " +
            "and r.exam_date>=? and r.exam_date<=?  and substr(r.site_region,5)=? " +
            "group by site_region, pivotal_code " +
            "order by site_region, pivotal_code ";

    @Autowired
    public CertExamRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public List<CertExamSummary> getCertExamSummary(String startDate, String endDate) {
        return jdbcTemplate.query(ALL_SUMMARY, new Object[]{startDate, endDate},
                 mapper);
    }

    public List<CertExamSummary> getCertExamSummaryByRegion(String startDate, String endDate, String region) {
        return jdbcTemplate.query(REGION_SUMMARY, new Object[]{startDate, endDate, region},
                mapper);
    }

    private final RowMapper<CertExamSummary> mapper = (rs, rowNum) ->
       new CertExamSummary(
                    rs.getString("region"),
                    rs.getString("pivotal_code"),
                    rs.getInt("pass"),
                    rs.getInt("fail"),
                    rs.getInt("refused")
            );

}
