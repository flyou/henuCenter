package com.flyou.henucenter.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import cn.jpush.android.data.r;

import com.flyou.henucenter.AboutUsActivity;
import com.flyou.henucenter.FeedBackActivity;
import com.flyou.henucenter.R;
import com.flyou.henucenter.R.dimen;
import com.flyou.henucenter.ShowUserInfoActivity;
import com.flyou.henucenter.ui.MyDialog;
import com.flyou.henucenter.utils.Constants;
import com.flyou.utils.SPUtils;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

public class MenuRightFragment_Logined extends Fragment implements OnClickListener {
  private MyDialog myDialog = null;
  private View mView;
  @ViewInject(R.id.henu_right_menu_logout)
  private Button logout;
  @ViewInject(R.id.userName)
  private TextView userName;
  @ViewInject(R.id.feedback)
  private TextView feedback;
  @ViewInject(R.id.aboutUs)
  private TextView aboutUs;

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

    mView = inflater.inflate(R.layout.right_menu, null);
    // 加入到注解框架
    ViewUtils.inject(this, mView);
    return mView;
  }

  @Override
  public void onActivityCreated(Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);
    initView();

  }

  private void initView() {
    logout.setOnClickListener(this);
    String user_name = (String) SPUtils.get(getActivity(), "UserName", "");
    userName.setText(user_name);
    userName.setOnClickListener(this);
    feedback.setOnClickListener(this);
    aboutUs.setOnClickListener(this);

  }

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
  }

  @Override
  public void onClick(View v) {
    switch (v.getId()) {
      case R.id.henu_right_menu_logout:

        View view = View.inflate(getActivity(), R.layout.textview, null);
        TextView textView = (TextView) view.findViewById(R.id.textv_content);
        textView.setText("你确定要注销当前账号？");
        myDialog = new MyDialog(getActivity(), "温馨提示~", view, new OnClickListener() {

          @Override
          public void onClick(View arg0) {
            // 通知改变右侧的fragment
            SPUtils.put(getActivity(), "UserName", "");
            Intent intent = new Intent(Constants.RIGHT_MENU_CHANGE_ACTION);

            getActivity().sendBroadcast(intent);
            // 关闭dialog
            myDialog.dismiss();
          }
        });
        // 显示dialog
        myDialog.show();
        break;
      case R.id.userName:
        Intent updateinfoIntent = new Intent(getActivity(), ShowUserInfoActivity.class);
        startActivity(updateinfoIntent);
        getActivity().overridePendingTransition(R.anim.anim_activity_next_in, R.anim.anim_activity_next_out);
        break;
        
      case R.id.feedback:
        Intent feedBackIntent=new Intent(getActivity(), FeedBackActivity.class);
        startActivity(feedBackIntent);
        getActivity().overridePendingTransition(R.anim.anim_activity_next_in, R.anim.anim_activity_next_out);
        break;
        
      case R.id.aboutUs:
        Intent aboutUsIntent=new Intent(getActivity(), AboutUsActivity.class);
        startActivity(aboutUsIntent);
        getActivity().overridePendingTransition(R.anim.anim_activity_next_in, R.anim.anim_activity_next_out);
        break;
      default:
        break;
    }
  }

}
