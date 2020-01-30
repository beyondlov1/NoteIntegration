package com.beyond.note.integration.entity;


import com.beyond.note.integration.constant.DocumentConst;
import com.beyond.sync.entity.Traceable;

import java.util.Date;

public class Document implements Cloneable, Traceable {

    public final static String NOTE = "note";
    public final static String TODO = "todo";

    private String id;
    private String title;
    private String content;
    private String type;
    private Date createTime;
    private Date lastModifyTime;
    private Integer version;
    private Integer readFlag;
    private Integer priority = DocumentConst.PRIORITY_DEFAULT;
    private Boolean valid = true;

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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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

    @Override
    protected Object clone() throws CloneNotSupportedException {
        Document document = (Document) super.clone();
        try {
            document = this.getClass().newInstance();
            document.setId(this.getId());
            document.setTitle(this.getTitle());
            document.setContent(this.getContent());
            document.setCreateTime(this.getCreateTime());
            document.setLastModifyTime(this.getLastModifyTime());
            document.setVersion(this.getVersion());
            document.setType(this.getType());
            document.setPriority(this.getPriority());
            document.setValid(this.getValid());
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return document;
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

}
