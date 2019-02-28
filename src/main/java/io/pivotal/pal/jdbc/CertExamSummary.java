package io.pivotal.pal.jdbc;

import java.io.Serializable;
import java.util.Objects;

public class CertExamSummary implements Serializable {
    private String region;
    private String pivotalExamCode;
    private int passCount;
    private int failCount;
    private int refusedCount;
    //private int total;

    public CertExamSummary(String region, String pivotalExamCode, int passCount, int failCount, int refusedCount) {
        this.region = region;
        this.pivotalExamCode = pivotalExamCode;
        this.passCount = passCount;
        this.failCount = failCount;
        this.refusedCount = refusedCount;
    }

    public int getTotal() {
        return getPassCount()+getFailCount()+getRefusedCount();
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getPivotalExamCode() {
        return pivotalExamCode;
    }

    public void setPivotalExamCode(String pivotalExamCode) {
        this.pivotalExamCode = pivotalExamCode;
    }

    public int getPassCount() {
        return passCount;
    }

    public void setPassCount(int passCount) {
        this.passCount = passCount;
    }

    public int getFailCount() {
        return failCount;
    }

    public void setFailCount(int failCount) {
        this.failCount = failCount;
    }

    public int getRefusedCount() {
        return refusedCount;
    }

    public void setRefusedCount(int refusedCount) {
        this.refusedCount = refusedCount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CertExamSummary that = (CertExamSummary) o;
        return Objects.equals(region, that.region) &&
                Objects.equals(pivotalExamCode, that.pivotalExamCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(region, pivotalExamCode);
    }
}
