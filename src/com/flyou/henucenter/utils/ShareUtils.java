package com.flyou.henucenter.utils;

import com.flyou.henucenter.HomeActivity;
import com.flyou.henucenter.R;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;
import cn.sharesdk.onekeyshare.Shake2Share;
import cn.sharesdk.onekeyshare.Shake2Share.OnShakeListener;

/**  ============================================================  
 * ��Ŀ���ƣ�HenuCenter   
 * 
 * �����ƣ�ShareUtils   
 * 
 * ��������   
 * 
 * �����ˣ�flyou  
 * 
 * ����ʱ�䣺2015-5-18 ����10:19:51  
 *  
 * �޸ı�ע��   
 * 
 * �汾��@version    
 *   ============================================================ 
 */
public class ShareUtils {
  public static final String TAG = "ShareUtils";
  public void showShare(Context context,String title,String url,View windowView) {
    ShareSDK.initSDK(context);
    OnekeyShare oks = new OnekeyShare();
    //�ر�sso��Ȩ
    oks.disableSSOWhenAuthorize(); 

   // ����ʱNotification��ͼ�������  2.5.9�Ժ�İ汾�����ô˷���
    //oks.setNotification(R.drawable.ic_launcher, getString(R.string.app_name));
    // title���⣬ӡ��ʼǡ����䡢��Ϣ��΢�š���������QQ�ռ�ʹ��
    oks.setTitle(context.getString(R.string.share));
    // titleUrl�Ǳ�����������ӣ�������������QQ�ռ�ʹ��
    oks.setTitleUrl(url);
    // text�Ƿ����ı�������ƽ̨����Ҫ����ֶ�
    oks.setText(title);
    // imagePath��ͼƬ�ı���·����Linked-In�����ƽ̨��֧�ִ˲���
    
    oks.setViewToShare(windowView);
//    oks.setImagePath("/sdcard/test.jpg");//ȷ��SDcard������ڴ���ͼƬ
    // url����΢�ţ��������Ѻ�����Ȧ����ʹ��
    oks.setUrl(url);
    // comment���Ҷ�������������ۣ�������������QQ�ռ�ʹ��
    oks.setComment("���ǲ��������ı�");
    // site�Ƿ�������ݵ���վ���ƣ�����QQ�ռ�ʹ��
    oks.setSite(context.getString(R.string.app_name));
    // siteUrl�Ƿ�������ݵ���վ��ַ������QQ�ռ�ʹ��
    oks.setSiteUrl(url);

   // ��������GUI
    oks.show(context);
    }
  

}
