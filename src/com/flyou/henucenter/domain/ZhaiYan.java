package com.flyou.henucenter.domain;

/**
 * ============================================================ ��Ŀ���ƣ�HenuCenter
 * 
 * �����ƣ�ZhaiYan
 * 
 * ��������
 * 
 * �����ˣ�flyou
 * 
 * ����ʱ�䣺2015-5-31 ����8:58:11
 * 
 * �޸ı�ע��
 * 
 * �汾��@version ============================================================
 */
public class ZhaiYan {

  private String text;
  private String type;
  private String from;
  private String date;
  /**
   * @return the text
   */
  public String getText() {
    return text;
  }
  /**
   * @param text the text to set
   */
  public void setText(String text) {
    this.text = text;
  }
  /**
   * @return the type
   */
  public String getType() {
    return type;
  }
  /**
   * @param type the type to set
   */
  public void setType(String type) {
    this.type = type;
  }
  /**
   * @return the from
   */
  public String getFrom() {
    return from;
  }
  /**
   * @param from the from to set
   */
  public void setFrom(String from) {
    this.from = from;
  }
  /**
   * @return the date
   */
  public String getDate() {
    return date;
  }
  /**
   * @param date the date to set
   */
  public void setDate(String date) {
    this.date = date;
  }
  public ZhaiYan(String text, String type, String from, String date) {
    super();
    this.text = text;
    this.type = type;
    this.from = from;
    this.date = date;
  }
  /* 
  * @return 
  */
  @Override
  public String toString() {
    return "ZhaiYan [text=" + text + ", type=" + type + ", from=" + from + ", date=" + date + "]";
  }
  
}
