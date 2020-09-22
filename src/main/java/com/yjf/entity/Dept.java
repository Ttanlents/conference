package com.yjf.entity;

import java.io.Serializable;
import java.util.Objects;

/**
 * @author 余俊锋
 * @date 2020/9/22 10:30
 * @Description 部门实体类
 */
public class Dept extends BaseEntity {


  /**
   *部门Id
   */
  private Integer id;

  /**
   *部门名称
   */
  private String name;


  public Dept() {
  }

  public Dept(Integer id, String name) {
    this.id = id;
    this.name = name;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Dept dept = (Dept) o;
    return Objects.equals(id, dept.id) &&
            Objects.equals(name, dept.name);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name);
  }

  @Override
  public String toString() {
    return "Dept{" +
            "id=" + id +
            ", name='" + name + '\'' +
            '}';
  }
}
