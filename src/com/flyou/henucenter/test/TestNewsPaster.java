package com.flyou.henucenter.test;

import java.util.Random;

import com.flyou.henucenter.utils.NewsUrl;
import com.medroid.adapter.engine.NewsUrlPaster;

import android.test.AndroidTestCase;

/**
 * ============================================================ ��Ŀ���ƣ�HenuCenter
 * 
 * �����ƣ�TestNewsPaster
 * 
 * ��������
 * 
 * �����ˣ�flyou
 * 
 * ����ʱ�䣺2015-4-20 ����12:00:19
 * 
 * �޸ı�ע��
 * 
 * �汾��@version ============================================================
 */
public class TestNewsPaster extends AndroidTestCase {
  public static final String TAG = "TestNewsPaster";

  public void TestNewspaster() {

//    System.out.println(NewsUrlPaster.getAllNews(NewsUrl.BASE_SCHOOL_NEWS+NewsUrl.COUNT_SCHOOL_NEWS+NewsUrl.END_SCHOOL_NEWS).toString());
  System.out.println(  new Random().nextInt(6));
  }

}
