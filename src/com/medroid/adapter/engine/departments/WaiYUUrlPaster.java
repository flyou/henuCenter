package com.medroid.adapter.engine.departments;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

/**  ============================================================  
 * 项目名称：HenuCenter   
 * 
 * 类名称：JkyUrlPaster   
 * 
 * 类描述：   
 * 
 * 创建人：flyou  
 * 
 * 创建时间：2015-4-26 下午9:20:12  
 *  
 * 修改备注：   
 * 
 * 版本：@version    
 *   ============================================================ 
 */
public class WaiYUUrlPaster {
  public static final String TAG = "JkyUrlPaster";
  public static final String PATH = "JkyUrlPaster";
  public static final String ID = "form1";
  private static Document doc;
  private static Element content;


  public static String GetShow() {
    try {
      doc = Jsoup.connect(PATH).timeout(10000).get();
      content = doc.getElementById(ID);

    } catch (Exception e) {

      e.printStackTrace();
      return "";
    }
    return content.toString();
  }

}
