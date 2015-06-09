package com.flyou.henucenter.utils;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import java.net.HttpURLConnection; 



import org.apache.http.HttpStatus;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

/**
 * ============================================================ 项目名称：HenuCenter
 * 
 * 类名称：UrlImageUtil
 * 
 * 类描述：
 * 
 * 创建人：flyou
 * 
 * 创建时间：2015-4-12 下午9:16:40
 * 
 * 修改备注：
 * 
 * 版本：@version ============================================================
 */
public class UrlImageUtil {
  public static final String TAG = "UrlImageUtil";
  public static final String BASEURL = "http://121.42.51.19/HenuCenter/viewPaperImage/";
  public static final String ENDURL[] = { "default1.jpg", "default2.jpg", "default3.jpg", "default4.jpg",
      "default5.jpg" };

  public static List<Bitmap> getURLImages() throws Exception {
    List<Bitmap> imagesList=new ArrayList<Bitmap>();
    for (int i = 0; i < ENDURL.length; i++) {
      URL url = new URL(BASEURL + ENDURL[i]);
HttpURLConnection connection=(HttpURLConnection) url.openConnection();
      connection.setReadTimeout(4000);
      connection.setConnectTimeout(4000);
      connection.setDoInput(true);
      connection.setRequestMethod("GET");
      if (connection.getResponseCode() == HttpStatus.SC_OK) {
        InputStream inputStream = connection.getInputStream();
        Bitmap bitmap=BitmapFactory.decodeStream(inputStream);
        imagesList.add(bitmap);
      }
    }
    return imagesList;
  }
}
