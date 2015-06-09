package com.medroid.adapter.engine;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.jsoup.Connection;
import org.jsoup.Connection.Method;
import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

/**
 * ============================================================ ��Ŀ���ƣ�HenuCenter
 * 
 * �����ƣ�CETUrlPaster
 * 
 * ��������
 * 
 * �����ˣ�flyou
 * 
 * ����ʱ�䣺2015-4-23 ����12:08:33
 * 
 * �޸ı�ע��
 * 
 * �汾��@version ============================================================
 */
public class CETUrlPaster {
  public static final String TAG = "CETUrlPaster";
  public static final String BASE_CETURL = "http://www.chsi.com.cn/cet/query?zkzh=";
  public static final String END_CETURL = "&xm=";
  private static Document doc;
  private static Element content;
  private static String sessionId;
  private static Response res;

  public static String GetCETShow(String number, String name) {
    
    try {
      res = Jsoup.connect("http://www.chsi.com.cn/cet/query")
          .data("zkzh", number).data("xm", name) // �������
         
     
          .timeout(3000) // �������ӳ�ʱʱ��
        .method(Method.POST)
        .execute();
     doc = res.parse();
    sessionId = res.cookie("SESSIONID"); 
    
    } catch (IOException e) {
      e.printStackTrace();
    } 

  content=doc.getElementsByClass("cetTable ").first();
return res.body();
  }
}
