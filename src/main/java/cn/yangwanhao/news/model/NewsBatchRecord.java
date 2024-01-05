package cn.yangwanhao.news.model;

import java.io.Serializable;
import java.util.Date;

public class NewsBatchRecord implements Serializable {
    private String batchId;

    private String hasSent;

    private Date createTime;

    private Date updateTime;

    private static final long serialVersionUID = 1L;

    public String getBatchId() {
        return batchId;
    }

    public void setBatchId(String batchId) {
        this.batchId = batchId == null ? null : batchId.trim();
    }

    public String getHasSent() {
        return hasSent;
    }

    public void setHasSent(String hasSent) {
        this.hasSent = hasSent == null ? null : hasSent.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}