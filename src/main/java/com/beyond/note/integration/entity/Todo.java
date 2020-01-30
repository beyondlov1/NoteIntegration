package com.beyond.note.integration.entity;

import com.beyond.note.integration.constant.DocumentConst;
import com.beyond.sync.utils.IDUtil;

import java.util.Date;

@SuppressWarnings("StringEquality")
public class Todo extends Document {

    public static Todo create(String content){
        Todo todo = new Todo();
        todo.setId(IDUtil.uuid());
        todo.setTitle(content.length() > 10 ? content.substring(0, 10) : content);
        todo.setContent(content);
        Date currDate = new Date();
        todo.setCreateTime(currDate);
        todo.setVersion(0);
        todo.setLastModifyTime(currDate);
        todo.setReadFlag(DocumentConst.READ_FLAG_NORMAL);
        todo.setValid(true);
        return todo;
    }

    private String id;
    private String reminderId;
    private String title;
    private String content;
    private String contentWithoutTime;
    private String type = Document.TODO;
    private Date createTime;
    private Date lastModifyTime;
    private Integer version;
    private Integer readFlag = DocumentConst.READ_FLAG_NORMAL;
    private Integer priority = DocumentConst.PRIORITY_DEFAULT;
    private Boolean valid = true;

    private Reminder reminder;

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getReminderId() {
        return this.reminderId;
    }

    public void setReminderId(String reminderId) {
        this.reminderId = reminderId;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getContentWithoutTime() {
        return this.contentWithoutTime;
    }

    public void setContentWithoutTime(String contentWithoutTime) {
        this.contentWithoutTime = contentWithoutTime;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getLastModifyTime() {
        return this.lastModifyTime;
    }

    public void setLastModifyTime(Date lastModifyTime) {
        this.lastModifyTime = lastModifyTime;
    }

    public Integer getVersion() {
        return this.version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public Integer getReadFlag() {
        return this.readFlag;
    }

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

    public Reminder getReminder() {
        return reminder;
    }

    public void setReminder(Reminder reminder) {
        this.reminder = reminder;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Todo todo = (Todo) o;

        if (id != null ? !id.equals(todo.id) : todo.id != null) return false;
        if (reminderId != null ? !reminderId.equals(todo.reminderId) : todo.reminderId != null)
            return false;
        if (title != null ? !title.equals(todo.title) : todo.title != null) return false;
        if (content != null ? !content.equals(todo.content) : todo.content != null) return false;
        if (contentWithoutTime != null ? !contentWithoutTime.equals(todo.contentWithoutTime) : todo.contentWithoutTime != null)
            return false;
        if (type != null ? !type.equals(todo.type) : todo.type != null) return false;
        if (createTime != null ? !createTime.equals(todo.createTime) : todo.createTime != null)
            return false;
        if (lastModifyTime != null ? !lastModifyTime.equals(todo.lastModifyTime) : todo.lastModifyTime != null)
            return false;
        if (version != null ? !version.equals(todo.version) : todo.version != null) return false;
        if (readFlag != null ? !readFlag.equals(todo.readFlag) : todo.readFlag != null)
            return false;
        if (priority != null ? !priority.equals(todo.priority) : todo.priority != null)
            return false;
        if (valid != null ? !valid.equals(todo.valid) : todo.valid != null) return false;
        return reminder != null ? reminder.equals(todo.reminder) : todo.reminder == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (id != null ? id.hashCode() : 0);
        result = 31 * result + (reminderId != null ? reminderId.hashCode() : 0);
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (content != null ? content.hashCode() : 0);
        result = 31 * result + (contentWithoutTime != null ? contentWithoutTime.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
        result = 31 * result + (lastModifyTime != null ? lastModifyTime.hashCode() : 0);
        result = 31 * result + (version != null ? version.hashCode() : 0);
        result = 31 * result + (readFlag != null ? readFlag.hashCode() : 0);
        result = 31 * result + (priority != null ? priority.hashCode() : 0);
        result = 31 * result + (valid != null ? valid.hashCode() : 0);
        result = 31 * result + (reminder != null ? reminder.hashCode() : 0);
        return result;
    }
}
