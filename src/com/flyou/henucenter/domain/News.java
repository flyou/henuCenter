package com.flyou.henucenter.domain;

/**
 * ============================================================ ��Ŀ���ƣ�HenuCenter
 * 
 * �����ƣ�News
 * 
 * ��������
 * 
 * �����ˣ�flyou
 * 
 * ����ʱ�䣺2015-4-19 ����11:28:32
 * 
 * �޸ı�ע�� ������Ϣ��ʵ����
 * 
 * �汾��@version ============================================================
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
