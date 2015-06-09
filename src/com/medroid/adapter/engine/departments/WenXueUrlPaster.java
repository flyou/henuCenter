package com.medroid.adapter.engine.departments;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.flyou.henucenter.domain.News;

/**
 * ============================================================ 项目名称：HenuCenter
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
 * 版本：@version ============================================================
 */
public class WenXueUrlPaster {
  public static final String TAG = "WenXueUrlPaster";
  public static final String CLASS = "newslist";
 
  private static List<News> list;
  private static News news;

  public static List<News> GetShow(String path) {
    list = new ArrayList<News>();
    Document doc;
    try {
      doc = Jsoup.connect(path).timeout(10000).get();
      Element content = doc.getElementsByClass(CLASS).first();
      Elements links = content.getElementsByTag("a");
      if (content.select("a").size() > 0) {
        for (Element link : links) {

          // 获得绝对路径
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
