package com.flyou.henucenter;

import android.app.Activity;
import android.os.Bundle;
import android.os.Vibrator;
import android.text.TextUtils;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
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

public class FeedBackActivity extends Activity {
  @ViewInject(R.id.qq)
  private EditText et_qq;
  @ViewInject(R.id.message)
  private EditText et_advise;

  private Vibrator vibrator;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_feed_back);
    ViewUtils.inject(this);
    vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);

  }

  public void back(View view) {

    finish();
    overridePendingTransition(R.anim.anim_activity_previous_in, R.anim.anim_activity_previous_out);

  }

  public void send(View view) {
    String QQ = et_qq.getText().toString().trim();
    String Advise = et_advise.getText().toString().trim();
    if (TextUtils.isEmpty(QQ)) {
      Toast.makeText(FeedBackActivity.this, "��д��ϵ��ʽ������������ϵ�����������", 0).show();
      Shark(et_qq);
      return;
    }
    if (TextUtils.isEmpty(Advise)) {
      Toast.makeText(FeedBackActivity.this, "��û����д������ϢŶ", 0).show();
      Shark(et_advise);
      return;
    }
    String phone = (String) SPUtils.get(FeedBackActivity.this, "UserName", "");
    HttpUtils httpUtils = new HttpUtils();
    httpUtils.send(HttpMethod.GET, Constants.HENU_FEEDBACK_URL+"?qq="+QQ+"&phone="+phone+"&advise="+Advise, new RequestCallBack<String>() {

      @Override
      public void onFailure(HttpException error, String msg) {
        Toast.makeText(FeedBackActivity.this, "������Ϣ����ʧ��" + msg, 0).show();
      }

      @Override
      public void onSuccess(ResponseInfo<String> responseInfo) {
        String result = responseInfo.result.trim().toString();
        if ("1".equals(result)) {
          Toast.makeText(FeedBackActivity.this, "������Ϣ�Ѿ��ʹ���ǻᾡ�촦������ȡ����ϵ", 1).show();
          finish();
          overridePendingTransition(R.anim.anim_activity_previous_in, R.anim.anim_activity_previous_out);
        } else {
          Toast.makeText(FeedBackActivity.this, "������Ϣ����ʧ��", 0).show();
        }
      }
    });

  }

  public void Shark(View view) {

    // �ζ�����
    Animation shake = AnimationUtils.loadAnimation(FeedBackActivity.this, R.anim.shake);
    view.startAnimation(shake);
    // �𶯷���
    // ������
    vibrator.vibrate(2000);
    // ������Ƶ�� { �������ʱ�䣬�������ʱ��}
    long pattern[] = { 200, 300, 300, 500, 200, 300 };
    vibrator.vibrate(pattern, -1);
  }

}
