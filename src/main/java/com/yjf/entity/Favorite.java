package com.yjf.entity;

import java.util.Objects;

/**
 * @author 余俊锋
 * @date 2020/9/22 10:30
 * @Description  （用户喜欢的文章）中间表实体类
 */
public class Favorite extends BaseEntity {

  /**
   *用户id
   */
  private Integer uId;

  /**
   *用户喜欢的文章Id
   */
  private Integer aId;

  public Favorite() {
  }

  public Favorite(Integer uId, Integer aId) {
    this.uId = uId;
    this.aId = aId;
  }

  public Integer getuId() {
    return uId;
  }

  public void setuId(Integer uId) {
    this.uId = uId;
  }

  public Integer getaId() {
    return aId;
  }

  public void setaId(Integer aId) {
    this.aId = aId;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Favorite favorite = (Favorite) o;
    return Objects.equals(uId, favorite.uId) &&
            Objects.equals(aId, favorite.aId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(uId, aId);
  }

  @Override
  public String toString() {
    return "Favorite{" +
            "uId=" + uId +
            ", aId=" + aId +
            '}';
  }
}
