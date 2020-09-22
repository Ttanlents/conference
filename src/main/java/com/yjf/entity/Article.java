package com.yjf.entity;


import java.util.Objects;

/**
 * @author 余俊锋
 * @date 2020/9/22 10:30
 * @Description 文章实体类
 */
public class Article extends BaseEntity {

  /**
   *文章id
   */
  private Integer id;

  /**
   *文章标题
   */
  private String title;

  /**
   *文章内容
   */
  private String content;

  /**
   *浏览次数
   */
  private Integer browseCount;

  /**
   *发布时间
   */
  private String publishDate;

  /**
   *发布人名称
   */
  private String publishRealName;

  /**
   *发布人（用户）id
   */
  private Integer userId;

  public Article() {
  }

  public Article(Integer id, String title, String content, Integer browseCount, String publishDate, String publishRealName, Integer userId) {
    this.id = id;
    this.title = title;
    this.content = content;
    this.browseCount = browseCount;
    this.publishDate = publishDate;
    this.publishRealName = publishRealName;
    this.userId = userId;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
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

  public Integer getBrowseCount() {
    return browseCount;
  }

  public void setBrowseCount(Integer browseCount) {
    this.browseCount = browseCount;
  }

  public String getPublishDate() {
    return publishDate;
  }

  public void setPublishDate(String publishDate) {
    this.publishDate = publishDate;
  }

  public String getPublishRealName() {
    return publishRealName;
  }

  public void setPublishRealName(String publishRealName) {
    this.publishRealName = publishRealName;
  }

  public Integer getUserId() {
    return userId;
  }

  public void setUserId(Integer userId) {
    this.userId = userId;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Article article = (Article) o;
    return Objects.equals(id, article.id) &&
            Objects.equals(title, article.title) &&
            Objects.equals(content, article.content) &&
            Objects.equals(browseCount, article.browseCount) &&
            Objects.equals(publishDate, article.publishDate) &&
            Objects.equals(publishRealName, article.publishRealName) &&
            Objects.equals(userId, article.userId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, title, content, browseCount, publishDate, publishRealName, userId);
  }

  @Override
  public String toString() {
    return "Article{" +
            "id=" + id +
            ", title='" + title + '\'' +
            ", content='" + content + '\'' +
            ", browseCount=" + browseCount +
            ", publishDate='" + publishDate + '\'' +
            ", publishRealName='" + publishRealName + '\'' +
            ", userId=" + userId +
            '}';
  }
}
