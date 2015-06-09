package com.flyou.henucenter.domain;
/**  ============================================================  
 * 项目名称：Finger   
 * 
 * 类名称：Data   
 * 
 * 类描述：   
 * 
 * 创建人：flyou  
 * 
 * 创建时间：2015-4-8 上午12:16:36  
 *  
 * 修改备注：   
 * 
 * 版本：@version    
 *   ============================================================ 
 */
public class Data {
private String code;
private String text;
/**
 * @return the code
 */
public String getCode() {
  return code;
}
/**
 * @param code the code to set
 */
public void setCode(String code) {
  this.code = code;
}
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
public Data(String code, String text) {
  super();
  this.code = code;
  this.text = text;
}
public Data() {
  super();
}

@Override
public String toString() {
  return "Data [code=" + code + ", text=" + text + "]";
}

}
