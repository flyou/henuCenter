package com.flyou.henucenter.test;

import java.net.URLEncoder;

import com.medroid.adapter.engine.CETUrlPaster;

import android.test.AndroidTestCase;

/**  ============================================================  
 * ��Ŀ���ƣ�HenuCenter   
 * 
 * �����ƣ�TestCETpaster   
 * 
 * ��������   
 * 
 * �����ˣ�flyou  
 * 
 * ����ʱ�䣺2015-4-23 ����12:26:32  
 *  
 * �޸ı�ע��   
 * 
 * �汾��@version    
 *   ============================================================ 
 */
public class TestCETpaster extends AndroidTestCase {
  public static final String TAG = "TestCETpaster";
  public void TestCetPaster(){
   System.out.println(CETUrlPaster.GetCETShow("411011142205311", URLEncoder.encode("������")));
    
  }

}
