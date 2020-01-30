package com.beyond.note.integration.refresh;

import com.beyond.note.integration.constant.DocumentConst;
import com.beyond.note.integration.entity.Document;

import java.util.Date;

public class Sql2FileMetaData {

    private String id;
    private String title;
    private String type = Document.NOTE;
    private Date createTime;
    private Date lastModifyTime;
    private Integer version;
    private Integer readFlag = DocumentConst.READ_FLAG_NORMAL;
    private Integer priority = DocumentConst.PRIORITY_DEFAULT;
    private Boolean valid = true;
    private Date lastRefreshTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getLastModifyTime() {
        return lastModifyTime;
    }

    public void setLastModifyTime(Date lastModifyTime) {
        this.lastModifyTime = lastModifyTime;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public Integer getReadFlag() {
        return readFlag;
    }

    public void setReadFlag(Integer readFlag) {
        this.readFlag = readFlag;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public Boolean getValid() {
        return valid;
    }

    public void setValid(Boolean valid) {
        this.valid = valid;
    }

    public Date getLastRefreshTime() {
        return lastRefreshTime;
    }

    public void setLastRefreshTime(Date lastRefreshTime) {
        this.lastRefreshTime = lastRefreshTime;
    }
}
