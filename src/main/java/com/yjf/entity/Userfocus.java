package com.yjf.entity;


/**
 * @author 余俊锋
 * @date 2020/9/22 10:30
 * @Description 用户实体类
 */
public class Userfocus extends BaseEntity {

  /**
   *用户id
   */
  private Integer userId;

  /**
   *用户关注朋友的id
   */
  private Integer userFocusId;

  public Userfocus() {
  }

  public Userfocus(Integer userId, Integer userFocusId) {
    this.userId = userId;
    this.userFocusId = userFocusId;
  }

  public Integer getUserId() {
    return userId;
  }

  public void setUserId(Integer userId) {
    this.userId = userId;
  }

  public Integer getUserFocusId() {
    return userFocusId;
  }

  public void setUserFocusId(Integer userFocusId) {
    this.userFocusId = userFocusId;
  }
}
