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
    // // ҡһҡ
    // Shake2Share s2s = new Shake2Share();
    // // ���ûص���ҡ�ε�һ���̶Ⱦͻᴥ������
    // s2s.setOnShakeListener(new OnShakeListener() {
    // public void onShake() {
    // OnekeyShare oks = new OnekeyShare();
    // // ����һ�����ڽ��������View
    // View windowView = getWindow().getDecorView();
    // oks.setViewToShare(windowView);
    // oks.setText("ҡһҡ���ͷ���");
    // oks.setPlatform(SinaWeibo.NAME);
    // oks.show(getBaseContext());
    // // ShareUtils shareUtils = new ShareUtils();
    // // shareUtils.showShare(HomeActivity.this, "�Ӵ�����,����У԰��Ѷ�ͻ���",
    // "http://121.42.51.19/");
    // }
    //
    // });
    // // ������ҡһҡ��������
    // s2s.show(HomeActivity.this, null);

    initView();
    // �����Զ�����ܵĹ㲥
    registerReceiver();

  }

  private void registerReceiver() {
    // �������item�Ĺ㲥
    IntentFilter filter = new IntentFilter(Constants.LEFITEM_ACTION);
    filter.addCategory(Intent.CATEGORY_DEFAULT);
    registerReceiver(LeftMenureceiver, filter);
    // �����Ҳ಼�ֵĹ㲥
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

  // ��ʼ��view����
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

    // �������˵�����
    setBehindContentView(R.layout.left_menu_frame);

    menu = getSlidingMenu();
    // ���ò໬�˵���ģʽ
    menu.setMode(SlidingMenu.LEFT_RIGHT);
    // ���ò໬�˵��Ĵ���ģʽ
    menu.setTouchModeAbove(SlidingMenu.TOUCHMODE_NONE);
    // ������Ӱ���
    menu.setShadowWidthRes(R.dimen.shadow_width);
    // ������Ӱ
    menu.setShadowDrawable(R.drawable.shadow);
    // ���ò˵���ʾ��ʣ��Ŀ��
    menu.setBehindOffsetRes(R.dimen.slidingmenu_offset);
    // ���ý��뽥��Ч����ֵ
    menu.setFadeDegree(0.35f);
    // �滻���˵�
    MenuLeftFragment leftMenuFragment = new MenuLeftFragment();
    getSupportFragmentManager().beginTransaction().replace(R.id.id_left_menu_frame, leftMenuFragment).commit();
    // �滻�Ҳ�˵�
    menu.setSecondaryShadowDrawable(R.drawable.shadow);
    // �����ұߣ��������໬�˵�

    menu.setSecondaryMenu(R.layout.right_menu_frame);
    changeRightFragment();
  }

  // ��ʾ�˵�
  public void showLeftMenu(View view) {
    getSlidingMenu().showMenu();
  }

  public void showRightMenu(View view) {
    getSlidingMenu().showSecondaryMenu();
  }

  public void home_click(View view) {
    tv_titlebar_desc.setText("ѧУ����");
    SchoolNews fragMent01 = new SchoolNews();
    getSupportFragmentManager().beginTransaction().replace(R.id.main_fragment, fragMent01).commit();

  }

  public void newscenter_click(View view) {
    tv_titlebar_desc.setText("У԰����");
    SchoolMessage fragMent02 = new SchoolMessage();
    getSupportFragmentManager().beginTransaction().replace(R.id.main_fragment, fragMent02).commit();

  }

  public void govaffairs_click(View view) {
    tv_titlebar_desc.setText("ѧ������");
    EduMessage fragMent03 = new EduMessage();
    getSupportFragmentManager().beginTransaction().replace(R.id.main_fragment, fragMent03).commit();
  }

  public void smartservice_click(View view) {
    tv_titlebar_desc.setText("�ǻ۷���");
    SmartServiceFragment fragMent04 = new SmartServiceFragment();
    getSupportFragmentManager().beginTransaction().replace(R.id.main_fragment, fragMent04).commit();

  }

  public void Department_click(View view) {
    tv_titlebar_desc.setText("�̿�����");
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

  // ����activityu�е�titlebar��ť������fragment
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
