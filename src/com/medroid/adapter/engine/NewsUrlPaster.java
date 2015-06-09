package com.medroid.adapter.engine;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.flyou.henucenter.domain.News;

/**
 * ============================================================ ��Ŀ���ƣ�HenuNews
 * 
 * �����ƣ�NewsUrlPaster
 * 
 * ��������
 * 
 * �����ˣ�flyou
 * 
 * ����ʱ�䣺2015-4-19 ����11:51:39
 * 
 * �޸ı�ע��
 * 
 * �汾��@version ============================================================
 */
public class NewsUrlPaster {
  public static final String TAG = "NewsUrlPaster";
  private static List<News> list;
  private static News news;

  public static List<News> getAllNews(String url) {
    list = new ArrayList<News>();
    Document doc;
    try {
      doc = Jsoup.connect(url).timeout(10000).get();
      Element content = doc.getElementsByClass("news").first();
      Elements links = content.getElementsByTag("a");
      if (content.select("a").size() > 0) {
        for (Element link : links) {
      
          //��þ���·��
          String newsUrl = link.attr("abs:href");
          String newsTitle = link.text();
          news = new News(newsTitle, newsUrl);
          list.add(news);
        }
        
      }

    } catch (IOException e) {
      return null;
    }
   
    return list;
  }

}
