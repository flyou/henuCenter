package com.flyou.henucenter.domain;

import java.util.Date;

/**
 * ============================================================ 项目名称：Finger
 * 
 * 类名称：ShowData
 * 
 * 类描述：
 * 
 * 创建人：flyou
 * 
 * 创建时间：2015-4-8 上午1:36:39
 * 
 * 修改备注：
 * 
 * 版本：@version ============================================================
 */
public class ShowData {
  public static final String TAG = "ShowData";
  private Date date;
  private String msg;
  private Boolean isReceive;
  /**
   * @return the date
   */
  public Date getDate() {
    return date;
  }
  /**
   * @param date the date to set
   */
  public void setDate(Date date) {
    this.date = date;
  }
  /**
   * @return the msg
   */
  public String getMsg() {
    return msg;
  }
  /**
   * @param msg the msg to set
   */
  public void setMsg(String msg) {
    this.msg = msg;
  }
  /**
   * @return the isReceive
   */
  public Boolean getIsReceive() {
    return isReceive;
  }
  /**
   * @param isReceive the isReceive to set
   */
  public void setIsReceive(Boolean isReceive) {
    this.isReceive = isReceive;
  }
  public ShowData(Date date, String msg, Boolean isReceive) {
    super();
    this.date = date;
    this.msg = msg;
    this.isReceive = isReceive;
  }
  public ShowData() {
    super();
  }
  
 
  @Override
  public String toString() {
    return "ShowData [date=" + date + ", msg=" + msg + ", isReceive=" + isReceive + "]";
  }

}
