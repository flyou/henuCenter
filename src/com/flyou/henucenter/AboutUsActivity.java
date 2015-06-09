package com.flyou.henucenter;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import android.widget.Toast;

import com.flyou.henucenter.ui.MyDialog;
import com.flyou.henucenter.utils.Constants;
import com.flyou.henucenter.utils.UpdateUtil;
import com.flyou.utils.AppUtils;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

public class AboutUsActivity extends Activity implements OnClickListener {
  private static final String TAG = "AboutUsActivity";
  @ViewInject(R.id.update)
  private TextView update;
  @ViewInject(R.id.weibo)
  private TextView weibo;
  @ViewInject(R.id.henu)
  private TextView henu;
  @ViewInject(R.id.author)
  private TextView author;
  private String versionName;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_about_us);
    ViewUtils.inject(this);
    initView();

  }

  private void initView() {
    update.setOnClickListener(this);
    weibo.setOnClickListener(this);
    henu.setOnClickListener(this);
    author.setOnClickListener(this);

  }

  @Override
  public void onClick(View v) {
    switch (v.getId()) {
    // 版本更新
      case R.id.update:
        versionName = AppUtils.getVersionName(AboutUsActivity.this);
    Toast.makeText(AboutUsActivity.this, "已经是最新版本了", 0).show();

        break;
      // 官方微博
      case R.id.weibo:
        Intent intent = new Intent(AboutUsActivity.this, DaitalNewsWebViewActivity.class);
        intent.putExtra("newsUrl", Constants.WEIBO);
        intent.putExtra("newsTitle", "官方微博");
        intent.putExtra("Departments_type", 400);
        startActivity(intent);
        break;
      // 河大在线
      case R.id.henu:
        Intent aboutHenuCenterIntent =new Intent(AboutUsActivity.this, AboutHenuCenterActivity.class);
      startActivity(aboutHenuCenterIntent);
      overridePendingTransition(R.anim.anim_activity_next_in, R.anim.anim_activity_next_out);
        break;
      // 关于作者
      case R.id.author:
        Intent aboutAuthorIntent =new Intent(AboutUsActivity.this, AboutMeActivity.class);
        startActivity(aboutAuthorIntent);
        overridePendingTransition(R.anim.anim_activity_next_in, R.anim.anim_activity_next_out);
        break;

    }

  }

 
}
