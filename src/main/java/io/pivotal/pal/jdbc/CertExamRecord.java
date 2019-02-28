package io.pivotal.pal.jdbc;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class CertExamRecord implements Serializable {
    private long ID;
    private String dataSource;
    private Date createDate;
    private Date updateDate;
    private String candidateEmail;
    private String candidateFirstname;
    private String candidateLastname;
    private String candidaterCompany;
    private String siteRegion;
    private String siteCountry;
    private String examCode;
    private String examTiele;
    private Date exam_date;
    private int score;
    private String grade;

    public long getID() {
        return ID;
    }

    public void setID(long ID) {
        this.ID = ID;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CertExamRecord certExam = (CertExamRecord) o;
        return ID == certExam.ID || (
                Objects.equals(dataSource, certExam.dataSource) &&
                Objects.equals(candidateEmail, certExam.candidateEmail) &&
                Objects.equals(examCode, certExam.examCode) &&
                Objects.equals(exam_date, certExam.exam_date));
    }

    @Override
    public int hashCode() {
        return Objects.hash(ID, dataSource, candidateEmail, examCode, exam_date);
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public Date getExam_date() {
        return exam_date;
    }

    public void setExam_date(Date exam_date) {
        this.exam_date = exam_date;
    }

    public String getExamTiele() {
        return examTiele;
    }

    public void setExamTiele(String examTiele) {
        this.examTiele = examTiele;
    }

    public String getExamCode() {
        return examCode;
    }

    public void setExamCode(String examCode) {
        this.examCode = examCode;
    }

    public String getSiteCountry() {
        return siteCountry;
    }

    public void setSiteCountry(String siteCountry) {
        this.siteCountry = siteCountry;
    }

    public String getSiteRegion() {
        return siteRegion;
    }

    public void setSiteRegion(String siteRegion) {
        this.siteRegion = siteRegion;
    }

    public String getCandidaterCompany() {
        return candidaterCompany;
    }

    public void setCandidaterCompany(String candidaterCompany) {
        this.candidaterCompany = candidaterCompany;
    }

    public String getCandidateLastname() {
        return candidateLastname;
    }

    public void setCandidateLastname(String candidateLastname) {
        this.candidateLastname = candidateLastname;
    }

    public String getCandidateFirstname() {
        return candidateFirstname;
    }

    public void setCandidateFirstname(String candidateFirstname) {
        this.candidateFirstname = candidateFirstname;
    }

    public String getCandidateEmail() {
        return candidateEmail;
    }

    public void setCandidateEmail(String candidateEmail) {
        this.candidateEmail = candidateEmail;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getDataSource() {
        return dataSource;
    }

    public void setDataSource(String dataSource) {
        this.dataSource = dataSource;
    }
}
