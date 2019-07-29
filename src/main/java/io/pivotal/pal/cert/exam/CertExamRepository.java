package io.pivotal.pal.cert.exam;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.*;


@Repository
public class CertExamRepository {
    Logger logger = LoggerFactory.getLogger(CertExamRepository.class);

    private final JdbcTemplate jdbcTemplate;
    private final SimpleJdbcCall simpleJdbcCall;

    private final String ALL_SUMMARY = "select 'ALL' as region, exam_name, " +
            "      count(case when exam_result='Passed' then 1 end) pass, " +
            "      count(case when exam_result='Failed' then 1 end) fail, " +
            "      count(case when exam_result is null or exam_result='Unknown' then 1 end) unknown " +
            "from CERT_EXAM_VW " +
            "where exam_date>=?  AND exam_date<=? " +
            "group by exam_name " +
            "order by exam_name ";

    private final String REGION_SUMMARY = "select 'ALL' as region, exam_name, " +
            "      count(case when exam_result='Passed' then exam_result end) pass, " +
            "      count(case when exam_result='Failed' then exam_result end) fail, " +
            "      count(case when exam_result not in ('Passed','Failed') then exam_result end) unknown " +
            "from CERT_EXAM_VW " +
            "where exam_date>=?  AND exam_date<=? and region=?" +
            "group by exam_name " +
            "order by exam_name ";


    private final String EXAM_DETAIL = "select EXAM_CENTER,CANDIDATE_EMAIL,FIRST_NAME,LAST_NAME, " +
            "  COMPANY_NAME,REGION,COUNTRY,EXAM_CODE,EXAM_NAME,EXAM_DATE,EXAM_PERCENTAGE,EXAM_RESULT " +
            " from CERT_EXAM_VW where exam_date>=? and exam_date<=? " +
            "order by exam_date ";
    //+ "limit 50";

    private final String EXAM_RECORD_INSERT = "INSERT INTO CERT_EXAM_RESULT " +
            "(DATA_SOURCE, CREATE_DATE, CANDIDATE_EMAIL, CANDIDATE_FIRSTNAME, CANDIDATE_LASTNAME, CANDIDATE_COMPANY,SITE_REGION,SITE_COUNTRY,EXAM_CODE,EXAM_TITLE,EXAM_DATE, SCORE, GRADE) " +
            "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)";


    @Autowired
    public CertExamRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        this.simpleJdbcCall = new SimpleJdbcCall(dataSource);
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
                    rs.getString("exam_name"),
                    rs.getInt("pass"),
                    rs.getInt("fail"),
                    rs.getInt("unknown")
            );

    public List<CertExamRecord> getCertExamRecords(String startDate, String endDate) {
        return jdbcTemplate.query(EXAM_DETAIL, new Object[]{startDate, endDate},
                examRecordMapper);
    }

    public List<CertExamRecord> getCertExamRecords(String startDate, String endDate, int limit) {
        String sql = EXAM_DETAIL + " limit " + limit;
        return jdbcTemplate.query(sql, new Object[]{startDate, endDate},
                examRecordMapper);
    }

    /*
      select EXAM_CENTER,CANDIDATE_EMAIL,FIRST_NAME,LAST_NAME,
             COMPANY_NAME,REGION,COUNTRY,EXAM_CODE,EXAM_NAME,EXAM_DATE,EXAM_PERCENTAGE,EXAM_RESULT


     */
    private final RowMapper<CertExamRecord> examRecordMapper = (rs, rowNum) ->
            new CertExamRecord(
                    rs.getString("EXAM_CENTER"),
                    rs.getString("CANDIDATE_EMAIL"),
                    rs.getString("FIRST_NAME"),
                    rs.getString("LAST_NAME"),
                    rs.getString("COMPANY_NAME"),
                    rs.getString("REGION"),
                    rs.getString("COUNTRY"),
                    rs.getString("EXAM_CODE"),
                    rs.getString("EXAM_NAME"),
                    rs.getDate("EXAM_DATE"),
                    rs.getInt("EXAM_PERCENTAGE"),
                    rs.getString("EXAM_RESULT")
            );



    //TODO: to prevent from SQL Injection, dynamic query should be parsed before sending to server
    //e.g. not allow certain keywords: DELETE, UPDATE, DROP, only allowed certain table name...
    public List<HashMap> getDynamicQueryResult(String sql) {
        return jdbcTemplate.query(sql,
                hashMapper);
    }

    private final RowMapper<HashMap> hashMapper = (rs, rowNum) -> {
        ResultSetMetaData metaData = rs.getMetaData();
        int colCount = metaData.getColumnCount();
        HashMap row = new LinkedHashMap(); //This is to protect the insertion order
        for (int i = 1; i <= colCount; i++) {
            row.put(metaData.getColumnLabel(i), rs.getObject(i));
        }
        return  row;
    };

    //batch insertion - for admin processing
    //Deprecated, this is for insertion for "Pearson VUE" only
    @Transactional
    public int[] insertBatch(final List<CertExamRecord> examRecords){

            logger.info("=====>Start a batch");
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


    /*
    This is general purpose batchInsertion, dynamic tableName, columns based on HashMap keyset
    Requirement for this version --> all the table columns are String - this is purposely designed for staging table
    Usually, DB is able to convert String to the right type if the format is correct
    */
    //EXAM_CENTER not from source
    public int[] insertBatch(String tableName,  List<HashMap<String, String>> examRecords){

        logger.info("=====>Start a batch");
        //preparation for insert statement
        StringBuffer statement = new StringBuffer("INSERT INTO ").append(tableName).append(" (");
        StringBuffer valuePlaceholder = new StringBuffer(" VALUES (");
        HashMap<String, String> first = examRecords.get(0);
        first.forEach((k,v)->{
            statement.append(k).append(",");
            valuePlaceholder.append("?,");
        });
        //remove last extra "," - 1 characters
        statement.setLength(statement.length()-1);
        valuePlaceholder.setLength(valuePlaceholder.length()-1);
        //whole statement
        statement.append(")").append(valuePlaceholder).append(")");

        //insertion, set values
        return jdbcTemplate.batchUpdate(statement.toString(),
                new BatchPreparedStatementSetter() {

                    public void setValues(PreparedStatement ps, int i) throws SQLException {
                        //This is LinkMap so that order is preserved
                        HashMap<String, String> record = examRecords.get(i);
                        List<String> keys = new ArrayList<String>(record.keySet());

                        for (int j=0; j<record.size(); j++) {
                            ps.setString(j+1, record.get(keys.get(j)));
                        }
                    }
                    @Override
                    public int getBatchSize() {
                        return examRecords.size();
                    }
                });

    }

    private final String UPDATE_STAGE_EXAMCENTER = "UPDATE CERT_EXAM_STAGE SET EXAM_CENTER = ?";
    public int updateStageExamCenter(String examCenter) {
        return jdbcTemplate.update(UPDATE_STAGE_EXAMCENTER,examCenter);
    }

    private final String TRUNCATE_STAGE_TABLE = "TRUNCATE TABLE CERT_EXAM_STAGE";
    public int truncateStageTable() {
        return jdbcTemplate.update(TRUNCATE_STAGE_TABLE);
    }

    private final String MERGE_STAGE_2_MAIN = "MERGE_STAGE_2_MAIN";
    public void mergeStage2Main() {
        simpleJdbcCall.withProcedureName(MERGE_STAGE_2_MAIN).execute();
    }

    private final String INSERT_DYNAMIC_TAB = "insert into DYNAMIC_TAB (tab_id, tab_name, dsql, create_date) "
            + " values (?,?,?, NOW())";
    public int addDynamicTab(String tabID, String tabName, String dSql) {
        return jdbcTemplate.update(
                INSERT_DYNAMIC_TAB, tabID, tabName, dSql);
    }

    private final String DELETE_DYNAMIC_TAB = "delete from DYNAMIC_TAB where tab_id=?";
    public int deleteDynamicTab(String tabID) {
        return jdbcTemplate.update(
                DELETE_DYNAMIC_TAB, tabID);
    }

    private final String GET_DYNAMIC_TABIDS = "select tab_id from DYNAMIC_TAB order by tab_id";
    public List<String> getDynamicTabIDs() {
        return jdbcTemplate.queryForList(GET_DYNAMIC_TABIDS, String.class);
    }

    private final String GET_DYNAMIC_TABIDNAMES = "select tab_id, tab_name from DYNAMIC_TAB order by tab_id";
    public List<HashMap> getDynamicTabIDNAMEs() {
        return jdbcTemplate.query(GET_DYNAMIC_TABIDNAMES,
                hashMapper);
    }

    private final String GET_DYNAMIC_TAB_BY_ID = "select  tab_id, tab_name, dsql, create_date, update_date from DYNAMIC_TAB where tab_id=?";
    public HashMap getDynamicTabByID(String tabID) {
        logger.info("===>tabID: "+tabID);
        return jdbcTemplate.queryForObject(GET_DYNAMIC_TAB_BY_ID, new Object[]{tabID},
                hashMapper);

    }

    private final String GET_ROLE_BY_EMAIL = "select USER_ROLE from USER_ROLE where EMAIL=? ";
    public String getRoleByEmail(String email) {
        logger.info("===>email: "+email);
        String role = "";
        try {
            role = jdbcTemplate.queryForObject(GET_ROLE_BY_EMAIL, new Object[]{email},
                    String.class);
        }
        catch (EmptyResultDataAccessException e) {
            logger.warn("=====>user does not have role in DB: "+email);
            //return empty String
        }
        return role;
    }

}
