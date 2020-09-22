package com.yjf.entity;


import java.util.Objects;

/**
 * @author 余俊锋
 * @date 2020/9/22 10:30
 * @Description 用户实体类
 */
public class Menu {

  private Integer id;
  private Integer parentId;
  private String name;
  private String url;
  private String type;
  private Integer sort;

  public Menu() {
  }

  public Menu(Integer id, Integer parentId, String name, String url, String type, Integer sort) {
    this.id = id;
    this.parentId = parentId;
    this.name = name;
    this.url = url;
    this.type = type;
    this.sort = sort;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Integer getParentId() {
    return parentId;
  }

  public void setParentId(Integer parentId) {
    this.parentId = parentId;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public Integer getSort() {
    return sort;
  }

  public void setSort(Integer sort) {
    this.sort = sort;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Menu menu = (Menu) o;
    return Objects.equals(id, menu.id) &&
            Objects.equals(parentId, menu.parentId) &&
            Objects.equals(name, menu.name) &&
            Objects.equals(url, menu.url) &&
            Objects.equals(type, menu.type) &&
            Objects.equals(sort, menu.sort);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, parentId, name, url, type, sort);
  }

  @Override
  public String toString() {
    return "Menu{" +
            "id=" + id +
            ", parentId=" + parentId +
            ", name='" + name + '\'' +
            ", url='" + url + '\'' +
            ", type='" + type + '\'' +
            ", sort=" + sort +
            '}';
  }
}
