package com.flyou.henucenter.test;

import java.net.URLEncoder;

import com.medroid.adapter.engine.CETUrlPaster;

import android.test.AndroidTestCase;

/**  ============================================================  
 * 项目名称：HenuCenter   
 * 
 * 类名称：TestCETpaster   
 * 
 * 类描述：   
 * 
 * 创建人：flyou  
 * 
 * 创建时间：2015-4-23 上午12:26:32  
 *  
 * 修改备注：   
 * 
 * 版本：@version    
 *   ============================================================ 
 */
public class TestCETpaster extends AndroidTestCase {
  public static final String TAG = "TestCETpaster";
  public void TestCetPaster(){
   System.out.println(CETUrlPaster.GetCETShow("411011142205311", URLEncoder.encode("房泽龙")));
    
  }

}
