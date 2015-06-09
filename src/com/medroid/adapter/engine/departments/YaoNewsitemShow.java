package com.medroid.adapter.engine.departments;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

/**  ============================================================  
 * 项目名称：HenuCenter   
 * 
 * 类名称：Newsitem   
 * 
 * 类描述：   
 * 
 * 创建人：flyou  
 * 
 * 创建时间：2015-4-20 上午9:55:24  
 *  
 * 修改备注：   
 * 
 * 版本：@version    
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
