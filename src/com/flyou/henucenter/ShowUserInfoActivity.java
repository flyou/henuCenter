package com.flyou.henucenter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.flyou.henucenter.utils.Constants;
import com.flyou.utils.SPUtils;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.lidroid.xutils.view.annotation.ViewInject;

public class ShowUserInfoActivity extends Activity {
  @ViewInject(R.id.userName)
  private TextView userName;
  @ViewInject(R.id.phone)
  private TextView phone;
  @ViewInject(R.id.email)
  private TextView email;
  @ViewInject(R.id.updateInfo)
  private ImageButton updateInfo;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_user_info);
    ViewUtils.inject(this);
    GetDataFromNet();
    initAction();
  }

  private void initAction() {
    updateInfo.setOnClickListener(new OnClickListener() {

      @Override
      public void onClick(View v) {

        Intent intent = new Intent(ShowUserInfoActivity.this, UpdateUserInfoActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.anim_activity_next_in, R.anim.anim_activity_next_out);

      }
    });
  }

  private void GetDataFromNet() {
    final String phone = (String) SPUtils.get(ShowUserInfoActivity.this, "UserName", "");
    HttpUtils httpUtils = new HttpUtils();
    httpUtils.send(HttpMethod.GET, Constants.HENU_GET_USERINFO + phone, new RequestCallBack<String>() {

      @Override
      public void onFailure(HttpException error, String msg) {
        Toast.makeText(ShowUserInfoActivity.this, "获取数据失败" + msg, 1).show();
      }

      @Override
      public void onSuccess(ResponseInfo<String> responseInfo) {
        String result = responseInfo.result;
        if (TextUtils.isEmpty(result)) {
          Toast.makeText(ShowUserInfoActivity.this, "获取数据失败,该用户可能不存在", 1).show();

        } else {
          String[] userInfo = result.split("#####");
          userName.setText(userInfo[0]);
          ShowUserInfoActivity.this.phone.setText(phone);
          email.setText(userInfo[1]);
        }

      }
    });
  }

  public void back(View view) {

    finish();
    overridePendingTransition(R.anim.anim_activity_previous_in, R.anim.anim_activity_previous_out);
  }
  @Override
  protected void onRestart() {
    super.onRestart();
    GetDataFromNet();
  
  }
}
