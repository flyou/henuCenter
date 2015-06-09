package com.flyou.henucenter;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import android.app.Activity;
import android.os.Bundle;
import android.os.Vibrator;
import android.text.TextUtils;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.flyou.henucenter.utils.Constants;
import com.flyou.utils.Md5Parser;
import com.flyou.utils.SPUtils;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.lidroid.xutils.view.annotation.ViewInject;

public class UpdateUserInfoActivity extends Activity {
  private Vibrator vibrator;
  @ViewInject(R.id.et_userName)
  private EditText userName;
  @ViewInject(R.id.et_email)
  private EditText email;
  @ViewInject(R.id.et_password)
  private EditText password;
  @ViewInject(R.id.et_repassword)
  private EditText repassword;
  @ViewInject(R.id.et_phone)
  private TextView phone;

  private String userNameText;

  private String emailText;

  private String passwordText;

  private String rePasswordText;
  private String phoneText;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_update_user_info);
    ViewUtils.inject(this);

    initView();
  }

  private void initView() {
    vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
    phoneText = (String) SPUtils.get(UpdateUserInfoActivity.this, "UserName", "");
    if (!TextUtils.isEmpty(phoneText)) {
      phone.setText(phoneText);
    }

  }

  public void Shark(View view) {

    // 晃动动画
    Animation shake = AnimationUtils.loadAnimation(UpdateUserInfoActivity.this, R.anim.shake);
    view.startAnimation(shake);
    // 震动服务
    // 震动两秒
    vibrator.vibrate(2000);
    // 定义震动频率 { 间隔，震动时间，间隔，震动时间}
    long pattern[] = { 200, 300, 300, 500, 200, 300 };
    vibrator.vibrate(pattern, -1);
  }

  public void update(View view) {

    try {
      userNameText = URLEncoder.encode(userName.getText().toString().trim(),"UTF-8");
    } catch (UnsupportedEncodingException e) {
    e.printStackTrace();
    }
    emailText = email.getText().toString().trim();
    passwordText = Md5Parser.md5Parser(password.getText().toString().trim());
    rePasswordText = Md5Parser.md5Parser(repassword.getText().toString().trim());

    if (TextUtils.isEmpty(userNameText)) {
      Toast.makeText(UpdateUserInfoActivity.this, "用户名不能为空", 0).show();
      Shark(userName);
      return;
    }
    if (TextUtils.isEmpty(emailText)) {
      Toast.makeText(UpdateUserInfoActivity.this, "邮箱不能为空", 0).show();
      Shark(email);
      return;
    }
    if (!emailText.matches("^\\s*\\w+(?:\\.{0,1}[\\w-]+)*@[a-zA-Z0-9]+(?:[-.][a-zA-Z0-9]+)*\\.[a-zA-Z]+\\s*$")) {
      Toast.makeText(UpdateUserInfoActivity.this, "邮箱格式非法", 0).show();
      Shark(email);
      return;
    }
    if (TextUtils.isEmpty(passwordText)) {
      Toast.makeText(UpdateUserInfoActivity.this, "密码不能为空", 0).show();
      Shark(password);
      return;
    }
    
    if (!passwordText.equals(rePasswordText)) {
      Toast.makeText(UpdateUserInfoActivity.this, "两次密码输入不一致", 0).show();
      Shark(repassword);
      return;
    }
HttpUtils httpUtils=new HttpUtils();
httpUtils.send(HttpMethod.GET, Constants.Henu_UPDATE_USERINFO+"?userName="+userNameText+"&password="+passwordText+"&email="+emailText+"&phone="+phoneText,   new RequestCallBack<String>() {

  @Override
  public void onFailure(HttpException error, String msg) {
    Toast.makeText(UpdateUserInfoActivity.this, "修改失败"+msg, 0).show();
  }

  @Override
  public void onSuccess(ResponseInfo<String> responseInfo) {
    String result=responseInfo.result.trim();
    if ("1".equals(result)) {
      Toast.makeText(UpdateUserInfoActivity.this, "修改成功", 0).show();
  
    }
    else {
      Toast.makeText(UpdateUserInfoActivity.this, "修改失败", 0).show();
    }
    finish();
    overridePendingTransition(R.anim.anim_activity_previous_in, R.anim.anim_activity_previous_out);
  }
});
   

  }

  public void back(View view) {

    finish();
    overridePendingTransition(R.anim.anim_activity_previous_in, R.anim.anim_activity_previous_out);

  }
}
