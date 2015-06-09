package com.flyou.henucenter;

import java.util.List;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import cn.jpush.android.api.JPushInterface;

import com.flyou.henucenter.adapter.LeftMenuListAdapter;
import com.flyou.henucenter.fragment.DepartmentFragMent;
import com.flyou.henucenter.fragment.EduMessage;
import com.flyou.henucenter.fragment.MenuLeftFragment;
import com.flyou.henucenter.fragment.MenuRightFragment_Logined;
import com.flyou.henucenter.fragment.MenuRightFragment_UnLogin;
import com.flyou.henucenter.fragment.SchoolMessage;
import com.flyou.henucenter.fragment.SchoolNews;
import com.flyou.henucenter.fragment.SchoolNews.GetHomeFrageMetord;
import com.flyou.henucenter.fragment.SmartServiceFragment;
import com.flyou.henucenter.utils.Constants;
import com.flyou.utils.SPUtils;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingFragmentActivity;

public class HomeActivity extends SlidingFragmentActivity implements GetHomeFrageMetord {
  private String TAG = "HomeActivity";
  public TextView tv_titlebar_desc;
  public ImageButton id_iv_left;
  public ImageButton id_iv_right;

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    setContentView(R.layout.activity_home);
    // // 摇一摇
    // Shake2Share s2s = new Shake2Share();
    // // 设置回调，摇晃到一定程度就会触发分享
    // s2s.setOnShakeListener(new OnShakeListener() {
    // public void onShake() {
    // OnekeyShare oks = new OnekeyShare();
    // // 设置一个用于截屏分享的View
    // View windowView = getWindow().getDecorView();
    // oks.setViewToShare(windowView);
    // oks.setText("摇一摇，就分享");
    // oks.setPlatform(SinaWeibo.NAME);
    // oks.show(getBaseContext());
    // // ShareUtils shareUtils = new ShareUtils();
    // // shareUtils.showShare(HomeActivity.this, "河大在线,您的校园资讯客户端",
    // "http://121.42.51.19/");
    // }
    //
    // });
    // // 启动“摇一摇分享”功能
    // s2s.show(HomeActivity.this, null);

    initView();
    // 设置自定义接受的广播
    registerReceiver();

  }

  private void registerReceiver() {
    // 来自左侧item的广播
    IntentFilter filter = new IntentFilter(Constants.LEFITEM_ACTION);
    filter.addCategory(Intent.CATEGORY_DEFAULT);
    registerReceiver(LeftMenureceiver, filter);
    // 来自右侧布局的广播
    IntentFilter filter_right = new IntentFilter(Constants.RIGHT_MENU_CHANGE_ACTION);
    filter_right.addCategory(Intent.CATEGORY_DEFAULT);
    registerReceiver(RightMenuReceiver, filter_right);
  }

  private BroadcastReceiver LeftMenureceiver = new BroadcastReceiver() {

    @Override
    public void onReceive(Context context, Intent intent) {
      int position = intent.getIntExtra("Left_position", 0);
      tv_titlebar_desc.setText(LeftMenuListAdapter.DepartmentsName.get(position));
      DepartmentFragMent fragMent05 = new DepartmentFragMent();
      getSupportFragmentManager().beginTransaction().replace(R.id.main_fragment, fragMent05).commit();
      menu.toggle();
    }
  };

  private BroadcastReceiver RightMenuReceiver = new BroadcastReceiver() {

    @Override
    public void onReceive(Context context, Intent intent) {
        changeRightFragment();
     
    }
  };
  private SlidingMenu menu;

  // 初始化view界面
  private void initView() {
    tv_titlebar_desc = (TextView) findViewById(R.id.tv_titlebar_desc);
    id_iv_right = (ImageButton) this.findViewById(R.id.id_iv_right);
    id_iv_left = (ImageButton) this.findViewById(R.id.id_iv_left);

    id_iv_right.setVisibility(View.GONE);
    id_iv_left.setVisibility(View.GONE);
    initSlidingMenu();
  }

  protected void changeRightFragment() {
   String userName= (String) SPUtils.get(HomeActivity.this, "UserName", "");
    if (TextUtils.isEmpty(userName)) {
       Fragment unLoginRightFragment = new MenuRightFragment_UnLogin();
    getSupportFragmentManager().beginTransaction().replace(R.id.id_right_menu_frame, unLoginRightFragment).commitAllowingStateLoss();
    }
    else {
      
      Fragment  loginFragment=new MenuRightFragment_Logined();
      getSupportFragmentManager().beginTransaction().replace(R.id.id_right_menu_frame, loginFragment).commitAllowingStateLoss();
    }
   
  }

  private void initSlidingMenu() {

    // 设置左侧菜单布局
    setBehindContentView(R.layout.left_menu_frame);

    menu = getSlidingMenu();
    // 设置侧滑菜单的模式
    menu.setMode(SlidingMenu.LEFT_RIGHT);
    // 设置侧滑菜单的触摸模式
    menu.setTouchModeAbove(SlidingMenu.TOUCHMODE_NONE);
    // 设置阴影宽度
    menu.setShadowWidthRes(R.dimen.shadow_width);
    // 设置阴影
    menu.setShadowDrawable(R.drawable.shadow);
    // 设置菜单显示后剩余的宽度
    menu.setBehindOffsetRes(R.dimen.slidingmenu_offset);
    // 设置渐入渐出效果的值
    menu.setFadeDegree(0.35f);
    // 替换左侧菜单
    MenuLeftFragment leftMenuFragment = new MenuLeftFragment();
    getSupportFragmentManager().beginTransaction().replace(R.id.id_left_menu_frame, leftMenuFragment).commit();
    // 替换右侧菜单
    menu.setSecondaryShadowDrawable(R.drawable.shadow);
    // 设置右边（二级）侧滑菜单

    menu.setSecondaryMenu(R.layout.right_menu_frame);
    changeRightFragment();
  }

  // 显示菜单
  public void showLeftMenu(View view) {
    getSlidingMenu().showMenu();
  }

  public void showRightMenu(View view) {
    getSlidingMenu().showSecondaryMenu();
  }

  public void home_click(View view) {
    tv_titlebar_desc.setText("学校新闻");
    SchoolNews fragMent01 = new SchoolNews();
    getSupportFragmentManager().beginTransaction().replace(R.id.main_fragment, fragMent01).commit();

  }

  public void newscenter_click(View view) {
    tv_titlebar_desc.setText("校园公告");
    SchoolMessage fragMent02 = new SchoolMessage();
    getSupportFragmentManager().beginTransaction().replace(R.id.main_fragment, fragMent02).commit();

  }

  public void govaffairs_click(View view) {
    tv_titlebar_desc.setText("学术公告");
    EduMessage fragMent03 = new EduMessage();
    getSupportFragmentManager().beginTransaction().replace(R.id.main_fragment, fragMent03).commit();
  }

  public void smartservice_click(View view) {
    tv_titlebar_desc.setText("智慧服务");
    SmartServiceFragment fragMent04 = new SmartServiceFragment();
    getSupportFragmentManager().beginTransaction().replace(R.id.main_fragment, fragMent04).commit();

  }

  public void Department_click(View view) {
    tv_titlebar_desc.setText("教科新闻");
    DepartmentFragMent fragMent05 = new DepartmentFragMent();
    getSupportFragmentManager().beginTransaction().replace(R.id.main_fragment, fragMent05).commit();

  }

  @Override
  public void onAttachFragment(Fragment fragment) {

    Log.i(TAG, "onAttachFragment");
    super.onAttachFragment(fragment);
  }

  @Override
  public List<Bitmap> getBitmaps() {

    return null;
  }

  @Override
  protected void onDestroy() {

    System.out.println("onDestroy");
    unregisterReceiver(LeftMenureceiver);
    unregisterReceiver(RightMenuReceiver);
    super.onDestroy();

  }

  // 返回activityu中的titlebar按钮给其他fragment
  public ImageButton getLeftMenu() {

    return id_iv_left;
  }

  public ImageButton getRightMenu() {
    return id_iv_right;

  }

  public TextView getTitleDesc() {
    return tv_titlebar_desc;

  }

  @Override
  protected void onResume() {
    super.onResume();
    JPushInterface.onResume(this);
  }

  @Override
  protected void onPause() {
    super.onPause();
    JPushInterface.onPause(this);
  }
}
