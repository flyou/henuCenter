package com.flyou.henucenter.test;

import com.flyou.henucenter.utils.NewsUrl;
import com.medroid.adapter.engine.departments.WenXueUrlPaster;
import com.medroid.adapter.engine.departments.ZheXueUrlPaster;

import android.test.AndroidTestCase;

/**  ============================================================  
 * ��Ŀ���ƣ�HenuCenter   
 * 
 * �����ƣ�TestURlShow   
 * 
 * ��������   
 * 
 * �����ˣ�flyou  
 * 
 * ����ʱ�䣺2015-4-26 ����9:52:43  
 *  
 * �޸ı�ע��   
 * 
 * �汾��@version    
 *   ============================================================ 
 */
public class TestURlShow extends AndroidTestCase {
  public static final String TAG = "TestURlShow";
public void Test(){
 System.out.println(WenXueUrlPaster.GetShow("http://zg.henu.edu.cn/index.php/former/article_list/32").toString());

  
}
}
