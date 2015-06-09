package com.flyou.henucenter.test;

import com.flyou.henucenter.utils.NewsUrl;
import com.medroid.adapter.engine.departments.WenXueUrlPaster;
import com.medroid.adapter.engine.departments.ZheXueUrlPaster;

import android.test.AndroidTestCase;

/**  ============================================================  
 * 项目名称：HenuCenter   
 * 
 * 类名称：TestURlShow   
 * 
 * 类描述：   
 * 
 * 创建人：flyou  
 * 
 * 创建时间：2015-4-26 下午9:52:43  
 *  
 * 修改备注：   
 * 
 * 版本：@version    
 *   ============================================================ 
 */
public class TestURlShow extends AndroidTestCase {
  public static final String TAG = "TestURlShow";
public void Test(){
 System.out.println(WenXueUrlPaster.GetShow("http://zg.henu.edu.cn/index.php/former/article_list/32").toString());

  
}
}
