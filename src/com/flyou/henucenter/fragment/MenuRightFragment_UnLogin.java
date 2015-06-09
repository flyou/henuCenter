package com.flyou.henucenter.fragment;

import java.util.HashMap;

import android.R.integer;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;
import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;
import cn.smssdk.gui.RegisterPage;

import com.flyou.henucenter.LoginActivity;
import com.flyou.henucenter.R;
import com.flyou.henucenter.utils.Constants;
import com.flyou.henucenter.utils.UserRegist;
import com.flyou.utils.SPUtils;

public class MenuRightFragment_UnLogin extends Fragment implements OnClickListener {
  private View mView;

  public Button login;
  public Button regist;

  private Button logout;

  private String userName;
  
  private String phone;

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

  
      mView = inflater.inflate(R.layout.henu_login_or_regist, null);
   

    return mView;
  }

  @Override
  public void onActivityCreated(Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);
    initView();

  }

  private void initView() {
  
      login = (Button) mView.findViewById(R.id.henu_login_btn);
      regist = (Button) mView.findViewById(R.id.henu_regist_btn);
      initLoginData();

    
  }

  

  private void initLoginData() {

    login.setOnClickListener(this);
    regist.setOnClickListener(this);
  }

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
  }

  @Override
  public void onClick(View v) {

    switch (v.getId()) {
      case R.id.henu_login_btn:
      
        Intent intent = new Intent(getActivity(), LoginActivity.class);
        startActivity(intent);
        break;
      case R.id.henu_regist_btn:
        // 打开注册页面
        RegisterPage registerPage = new RegisterPage();
        registerPage.setRegisterCallback(new EventHandler() {


          public void afterEvent(int event, int result, Object data) {
            // 解析注册结果
            if (result == SMSSDK.RESULT_COMPLETE) {
              @SuppressWarnings("unchecked")
              HashMap<String, Object> phoneMap = (HashMap<String, Object>) data;
              phone = (String) phoneMap.get("phone");

              // 提交注册
              new RegistAsyncTask().execute(phone, phone, phone+"@163.com", phone);
              
         
            }
          }
        });
        registerPage.show(getActivity());
        break;

     
    }
  }
  class RegistAsyncTask extends AsyncTask<String, String, String>{

    @Override
    protected String doInBackground(String... params) {
     String resultData = new UserRegist().regist(params[0],params[1],params[2],params[3]).trim().toString();
      return resultData;
    }

  
    @Override
    protected void onPostExecute(String result) {
      super.onPostExecute(result);
    int resultData=  Integer.parseInt(result);
      switch (resultData) {
        case 0:
          Toast.makeText(getActivity(), "此电话号已经注册，请直接登录,初始密码为手机号", 1).show();
          break;
          case 1:
            Toast.makeText(getActivity(), "网络连接异常，注册失败", 1).show();
            break;
          case 200:
          SPUtils.put(getActivity(), "userName", phone);
            Toast.makeText(getActivity(), "注册成功，请登录", 1).show();
            break;
        default:
          break;
      }
    }
    
  }
  
}
