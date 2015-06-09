package com.flyou.henucenter.test;

import java.util.Random;

import com.flyou.henucenter.utils.NewsUrl;
import com.medroid.adapter.engine.NewsUrlPaster;

import android.test.AndroidTestCase;

/**
 * ============================================================ 项目名称：HenuCenter
 * 
 * 类名称：TestNewsPaster
 * 
 * 类描述：
 * 
 * 创建人：flyou
 * 
 * 创建时间：2015-4-20 上午12:00:19
 * 
 * 修改备注：
 * 
 * 版本：@version ============================================================
 */
public class TestNewsPaster extends AndroidTestCase {
  public static final String TAG = "TestNewsPaster";

  public void TestNewspaster() {

//    System.out.println(NewsUrlPaster.getAllNews(NewsUrl.BASE_SCHOOL_NEWS+NewsUrl.COUNT_SCHOOL_NEWS+NewsUrl.END_SCHOOL_NEWS).toString());
  System.out.println(  new Random().nextInt(6));
  }

}
