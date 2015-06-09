package com.flyou.henucenter.test;

import com.flyou.henucenter.utils.NewsUrl;
import com.medroid.adapter.engine.departments.MinShengUrlPaster;
import com.medroid.adapter.engine.departments.WenXueUrlPaster;

import android.test.AndroidTestCase;

/**  ============================================================  
 * 项目名称：HenuCenter   
 * 
 * 类名称：TestNewsUrl   
 * 
 * 类描述：   
 * 
 * 创建人：flyou  
 * 
 * 创建时间：2015-4-20 上午12:31:19  
 *  
 * 修改备注：   
 * 
 * 版本：@version    
 *   ============================================================ 
 */
public class TestNewsUrl extends AndroidTestCase {
  public static final String TAG = "TestNewsUrl";
public void testNewsUrl(){
  
  System.out.println(WenXueUrlPaster.GetShow("http://minsheng.henu.edu.cn/a/minshengxinwenwang/xueyuanxinwen/list_26_2.html").toString());
  
}
}
