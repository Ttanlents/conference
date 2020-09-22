package com.yjf.entity;


import java.util.Objects;

/**
 * @author 余俊锋
 * @date 2020/9/22 10:30
 * @Description  需参加会议实体类
 */
public class MeetingJoin extends BaseEntity {

  /**
   *需参加人id
   */
  private Integer uId;
  /**
   *会议id
   */
  private Integer cId;

  public Integer getuId() {
    return uId;
  }

  public void setuId(Integer uId) {
    this.uId = uId;
  }

  public Integer getcId() {
    return cId;
  }

  public void setcId(Integer cId) {
    this.cId = cId;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    MeetingJoin that = (MeetingJoin) o;
    return Objects.equals(uId, that.uId) &&
            Objects.equals(cId, that.cId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(uId, cId);
  }

  @Override
  public String toString() {
    return "MeetingJoin{" +
            "uId=" + uId +
            ", cId=" + cId +
            '}';
  }
}
