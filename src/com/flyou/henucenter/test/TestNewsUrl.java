package com.flyou.henucenter.test;

import com.flyou.henucenter.utils.NewsUrl;
import com.medroid.adapter.engine.departments.MinShengUrlPaster;
import com.medroid.adapter.engine.departments.WenXueUrlPaster;

import android.test.AndroidTestCase;

/**  ============================================================  
 * ��Ŀ���ƣ�HenuCenter   
 * 
 * �����ƣ�TestNewsUrl   
 * 
 * ��������   
 * 
 * �����ˣ�flyou  
 * 
 * ����ʱ�䣺2015-4-20 ����12:31:19  
 *  
 * �޸ı�ע��   
 * 
 * �汾��@version    
 *   ============================================================ 
 */
public class TestNewsUrl extends AndroidTestCase {
  public static final String TAG = "TestNewsUrl";
public void testNewsUrl(){
  
  System.out.println(WenXueUrlPaster.GetShow("http://minsheng.henu.edu.cn/a/minshengxinwenwang/xueyuanxinwen/list_26_2.html").toString());
  
}
}
