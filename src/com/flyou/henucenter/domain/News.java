package com.flyou.henucenter.domain;

/**
 * ============================================================ 项目名称：HenuCenter
 * 
 * 类名称：News
 * 
 * 类描述：
 * 
 * 创建人：flyou
 * 
 * 创建时间：2015-4-19 下午11:28:32
 * 
 * 修改备注： 新闻信息的实体类
 * 
 * 版本：@version ============================================================
 */
public class News {

  public String title;
  public String url;

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public News(String title, String url) {
    super();
    this.title = title;
    this.url = url;
  }

 

}
