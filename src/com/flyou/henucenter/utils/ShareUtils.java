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
 * 项目名称：HenuCenter   
 * 
 * 类名称：ShareUtils   
 * 
 * 类描述：   
 * 
 * 创建人：flyou  
 * 
 * 创建时间：2015-5-18 下午10:19:51  
 *  
 * 修改备注：   
 * 
 * 版本：@version    
 *   ============================================================ 
 */
public class ShareUtils {
  public static final String TAG = "ShareUtils";
  public void showShare(Context context,String title,String url,View windowView) {
    ShareSDK.initSDK(context);
    OnekeyShare oks = new OnekeyShare();
    //关闭sso授权
    oks.disableSSOWhenAuthorize(); 

   // 分享时Notification的图标和文字  2.5.9以后的版本不调用此方法
    //oks.setNotification(R.drawable.ic_launcher, getString(R.string.app_name));
    // title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间使用
    oks.setTitle(context.getString(R.string.share));
    // titleUrl是标题的网络链接，仅在人人网和QQ空间使用
    oks.setTitleUrl(url);
    // text是分享文本，所有平台都需要这个字段
    oks.setText(title);
    // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
    
    oks.setViewToShare(windowView);
//    oks.setImagePath("/sdcard/test.jpg");//确保SDcard下面存在此张图片
    // url仅在微信（包括好友和朋友圈）中使用
    oks.setUrl(url);
    // comment是我对这条分享的评论，仅在人人网和QQ空间使用
    oks.setComment("我是测试评论文本");
    // site是分享此内容的网站名称，仅在QQ空间使用
    oks.setSite(context.getString(R.string.app_name));
    // siteUrl是分享此内容的网站地址，仅在QQ空间使用
    oks.setSiteUrl(url);

   // 启动分享GUI
    oks.show(context);
    }
  

}
