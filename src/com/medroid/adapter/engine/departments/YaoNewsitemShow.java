package com.medroid.adapter.engine.departments;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

/**  ============================================================  
 * ��Ŀ���ƣ�HenuCenter   
 * 
 * �����ƣ�Newsitem   
 * 
 * ��������   
 * 
 * �����ˣ�flyou  
 * 
 * ����ʱ�䣺2015-4-20 ����9:55:24  
 *  
 * �޸ı�ע��   
 * 
 * �汾��@version    
 *   ============================================================ 
 */
public class YaoNewsitemShow {
  private static Document doc;
  private static Element divAllItemElement;
  public static final String TAG = "Newsitem";
public static String getNewsbody(String itemUrl){

  
    try {
      doc=Jsoup.connect(itemUrl).timeout(7000).get();
      divAllItemElement=doc.getElementById("content");
      
    } catch (IOException e) {
    
      e.printStackTrace();
      return "";
    }
    return divAllItemElement.toString();
  }
  

}
