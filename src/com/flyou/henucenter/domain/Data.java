package com.flyou.henucenter.domain;
/**  ============================================================  
 * ��Ŀ���ƣ�Finger   
 * 
 * �����ƣ�Data   
 * 
 * ��������   
 * 
 * �����ˣ�flyou  
 * 
 * ����ʱ�䣺2015-4-8 ����12:16:36  
 *  
 * �޸ı�ע��   
 * 
 * �汾��@version    
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
