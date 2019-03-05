package io.pivotal.pal.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.SQLException;
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

    private final String EXAM_RECORD_INSERT = "INSERT INTO cert_exam_result " +
            "(DATA_SOURCE, CREATE_DATE, CANDIDATE_EMAIL, CANDIDATE_FIRSTNAME, CANDIDATE_LASTNAME, CANDIDATE_COMPANY,SITE_REGION,SITE_COUNTRY,EXAM_CODE,EXAM_TITLE,EXAM_DATE, SCORE, GRADE) " +
            "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)";


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

    //batch insertion - for feed processing
    //TODO: real feed processing should be in stage first then merge
    public int[] insertBatch(final List<CertExamRecord> examRecords){


            return jdbcTemplate.batchUpdate(EXAM_RECORD_INSERT,
                    new BatchPreparedStatementSetter() {

                        public void setValues(PreparedStatement ps, int i) throws SQLException {
                            ps.setString(1, examRecords.get(i).getDataSource());
                            ps.setDate(2, examRecords.get(i).getCreateDate());
                            ps.setString(3, examRecords.get(i).getEmail());
                            ps.setString(4, examRecords.get(i).getFirstName());
                            ps.setString(5, examRecords.get(i).getLastName());
                            ps.setString(6, examRecords.get(i).getCompany());
                            ps.setString(7, examRecords.get(i).getSiteRegion());
                            ps.setString(8, examRecords.get(i).getSiteCountry());
                            ps.setString(9, examRecords.get(i).getExamCode());
                            ps.setString(10, examRecords.get(i).getExamTitle());
                            ps.setDate(11, examRecords.get(i).getExamDate());
                            ps.setInt(12, examRecords.get(i).getScore());
                            ps.setString(13, examRecords.get(i).getGrade());

                        }
                        @Override
                        public int getBatchSize() {
                            return examRecords.size();
                        }
                    });

    }


}
