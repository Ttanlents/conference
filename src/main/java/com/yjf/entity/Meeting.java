package com.yjf.entity;

import java.util.Objects;

/**
 * @author 余俊锋
 * @date 2020/9/22 10:30
 * @Description  会议实体类
 */
public class Meeting extends BaseEntity {

    /**
     *会议表Id
     */
  private Integer id;

    /**
     *部门名称
     */
  private String deptName;

    /**
     *部门Id
     */
  private Integer deptId;

    /**
     *会议标题
     */
  private String title;

    /**
     *会议内容
     */
  private String content;

    /**
     *会议发布时间
     */
  private String publishDate;

    /**
     *会议开始时间
     */
  private String startTime;

    /**
     *会议结束时间
     */
  private String endTime;

    /**
     *会议状态
     * 会议状态 0:未开始 1:进行中 2:已结束
     */
  private Integer status;

    /**
     *会议抄送人
     * 抄送人id，以逗号分隔
     */
  private String makeUser;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public Integer getDeptId() {
        return deptId;
    }

    public void setDeptId(Integer deptId) {
        this.deptId = deptId;
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

    public String getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(String publishDate) {
        this.publishDate = publishDate;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMakeUser() {
        return makeUser;
    }

    public void setMakeUser(String makeUser) {
        this.makeUser = makeUser;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Meeting meeting = (Meeting) o;
        return Objects.equals(id, meeting.id) &&
                Objects.equals(deptName, meeting.deptName) &&
                Objects.equals(deptId, meeting.deptId) &&
                Objects.equals(title, meeting.title) &&
                Objects.equals(content, meeting.content) &&
                Objects.equals(publishDate, meeting.publishDate) &&
                Objects.equals(startTime, meeting.startTime) &&
                Objects.equals(endTime, meeting.endTime) &&
                Objects.equals(status, meeting.status) &&
                Objects.equals(makeUser, meeting.makeUser);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, deptName, deptId, title, content, publishDate, startTime, endTime, status, makeUser);
    }

    @Override
    public String toString() {
        return "Meeting{" +
                "id=" + id +
                ", deptName='" + deptName + '\'' +
                ", deptId=" + deptId +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", publishDate='" + publishDate + '\'' +
                ", startTime='" + startTime + '\'' +
                ", endTime='" + endTime + '\'' +
                ", status=" + status +
                ", makeUser='" + makeUser + '\'' +
                '}';
    }
}
