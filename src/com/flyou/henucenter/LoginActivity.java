package com.flyou.henucenter;

import com.flyou.henucenter.ui.MyDialog;
import com.flyou.henucenter.utils.Constants;
import com.flyou.henucenter.utils.UserLoginUtils;
import com.flyou.utils.SPUtils;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import cn.jpush.android.api.JPushInterface;

/**
 * 此类 是对布局main.xml上 控件的操作
 * 
 * @author dl
 * 
 */
public class LoginActivity extends Activity implements OnClickListener {
  Button rebackBtn, loginBtn, forgetPasswdBtn;
  EditText userEdit, passwdEdit;
  PopupWindow popup;
  RelativeLayout loginLayout;
  private String userName;

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    requestWindowFeature(Window.FEATURE_NO_TITLE);
    setContentView(R.layout.henu_login);
    rebackBtn = (Button) findViewById(R.id.login_reback_btn);
    rebackBtn.setOnClickListener(this);// 注册监听器 一定不能忘
    loginBtn = (Button) findViewById(R.id.login_login_btn);
    loginBtn.setOnClickListener(this);// 注册监听器 一定不能忘
    passwdEdit = (EditText) findViewById(R.id.login_passwd_edit);
    userEdit = (EditText) findViewById(R.id.login_user_edit);
    forgetPasswdBtn = (Button) findViewById(R.id.forget_passwd);
    forgetPasswdBtn.setOnClickListener(this);
    loginLayout = (RelativeLayout) findViewById(R.id.login_layout);
  }

  @Override
  public void onClick(View v) {
    int viewId = v.getId();
    MyDialog myDialog = null;
    switch (viewId) {
      case R.id.login_reback_btn:// 返回按钮
        LoginActivity.this.finish();// 关闭这个Activity 返回上一个Activity
        break;
      case R.id.login_login_btn:// 点击登录按钮 进行判断 用户名和密码是否为空
        userName = userEdit.getText().toString().trim();
        String password = passwdEdit.getText().toString().trim();
        if (TextUtils.isEmpty(userName)||TextUtils.isEmpty(password)) {// 只要用户名和密码有一个为空
          new AlertDialog.Builder(LoginActivity.this).setIcon(getResources().getDrawable(R.drawable.login_error_icon))
              .setTitle("登录失败").setMessage("账号或密码不能为空，请输入账号或密码").create().show();

        } else {
         new Login().execute(userName, password);
         
         
          
        }
        break;
      case R.id.forget_passwd:// 点击 “忘记密码” 这个文本
        View view = View.inflate(LoginActivity.this, R.layout.textview, null);
        TextView textView = (TextView) view.findViewById(R.id.textv_content);
        textView.setText("初始密码为手机号,登录后可进行修改");
        myDialog = new MyDialog(LoginActivity.this, "忘记密码~", view, null);
        // 显示dialog
        myDialog.show();
       
        break;
    }
  }

class Login extends AsyncTask<String, String, String> {

  @Override
  protected String doInBackground(String... params) {
    
    int login = new UserLoginUtils().Login(params[0],params[1]);
    return String.valueOf(login);
  }


  @Override
  protected void onPostExecute(String result1) {
    super.onPostExecute(result1);
    
 int  result=Integer.parseInt(result1);
    System.out.println("LoginActivity:"+result);
    if (result == 1 || result == 2) {
      //登录成功，保存用户名到本地
      SPUtils.put(LoginActivity.this, "UserName", userName);
      Toast.makeText(LoginActivity.this, "登录成功", 0).show();
      
      // 通知改变右侧的fragment
      Intent intent=new Intent(Constants.RIGHT_MENU_CHANGE_ACTION);
      sendBroadcast(intent);
      finish();
      
    }  else {
      Toast.makeText(LoginActivity.this, "用户名或密码错误", 0).show();
    } 
  }
  

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