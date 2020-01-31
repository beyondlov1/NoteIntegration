package com.beyond.note.integration.entity;


import com.beyond.note.integration.constant.DocumentConst;
import com.beyond.sync.utils.IDUtil;

import javax.persistence.*;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Entity
public class Note extends Document {

    public static Note create(){
        Date currDate = new Date();
        Note note = new Note();
        note.setId(IDUtil.uuid());
        note.setCreateTime(currDate);
        note.setVersion(0);
        note.setLastModifyTime(currDate);
        note.setReadFlag(DocumentConst.READ_FLAG_NORMAL);
        return note;
    }

    @Id
    private String id;
    @Column(columnDefinition = "text")
    private String title;
    @Column(columnDefinition = "text")
    private String content;
    private String type = Document.NOTE;
    private Date createTime;
    private Date lastModifyTime;
    private Integer version;
    private Integer readFlag = DocumentConst.READ_FLAG_NORMAL;
    private Integer priority = DocumentConst.PRIORITY_DEFAULT;
    private Boolean valid = true;
    @OneToMany(mappedBy = "noteId",fetch = FetchType.EAGER)
    private List<Attachment> attachments = Collections.emptyList();

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String getContent() {
        return content;
    }

    @Override
    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String getType() {
        return type;
    }

    @Override
    public void setType(String type) {
        this.type = type;
    }

    @Override
    public Date getCreateTime() {
        return createTime;
    }

    @Override
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public Date getLastModifyTime() {
        return lastModifyTime;
    }

    @Override
    public void setLastModifyTime(Date lastModifyTime) {
        this.lastModifyTime = lastModifyTime;
    }

    @Override
    public Integer getVersion() {
        return version;
    }

    @Override
    public void setVersion(Integer version) {
        this.version = version;
    }

    @Override
    public Integer getReadFlag() {
        return readFlag;
    }

    @Override
    public void setReadFlag(Integer readFlag) {
        this.readFlag = readFlag;
    }

    @Override
    public Integer getPriority() {
        return priority;
    }

    @Override
    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    @Override
    public Boolean getValid() {
        return valid;
    }

    @Override
    public void setValid(Boolean valid) {
        this.valid = valid;
    }

    public List<Attachment> getAttachments() {
        return attachments;
    }

    public void setAttachments(List<Attachment> attachments) {
        this.attachments = attachments;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Note note = (Note) o;

        if (id != null ? !id.equals(note.id) : note.id != null) return false;
        if (title != null ? !title.equals(note.title) : note.title != null) return false;
        if (content != null ? !content.equals(note.content) : note.content != null) return false;
        if (type != null ? !type.equals(note.type) : note.type != null) return false;
        if (createTime != null ? !createTime.equals(note.createTime) : note.createTime != null) return false;
        if (lastModifyTime != null ? !lastModifyTime.equals(note.lastModifyTime) : note.lastModifyTime != null)
            return false;
        if (version != null ? !version.equals(note.version) : note.version != null) return false;
        if (readFlag != null ? !readFlag.equals(note.readFlag) : note.readFlag != null) return false;
        if (priority != null ? !priority.equals(note.priority) : note.priority != null) return false;
        return valid != null ? valid.equals(note.valid) : note.valid == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (content != null ? content.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
        result = 31 * result + (lastModifyTime != null ? lastModifyTime.hashCode() : 0);
        result = 31 * result + (version != null ? version.hashCode() : 0);
        result = 31 * result + (readFlag != null ? readFlag.hashCode() : 0);
        result = 31 * result + (priority != null ? priority.hashCode() : 0);
        result = 31 * result + (valid != null ? valid.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Note{");
        sb.append("id='").append(id).append('\'');
        sb.append(", title='").append(title).append('\'');
        sb.append(", content='").append(content).append('\'');
        sb.append(", type='").append(type).append('\'');
        sb.append(", createTime=").append(createTime);
        sb.append(", lastModifyTime=").append(lastModifyTime);
        sb.append(", version=").append(version);
        sb.append(", readFlag=").append(readFlag);
        sb.append(", priority=").append(priority);
        sb.append(", valid=").append(valid);
        sb.append('}');
        return sb.toString();
    }
}
