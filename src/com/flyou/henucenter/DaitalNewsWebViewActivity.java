package com.flyou.henucenter;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebSettings;
import android.webkit.WebSettings.LayoutAlgorithm;
import android.webkit.WebSettings.TextSize;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import cn.jpush.android.api.JPushInterface;

import com.flyou.henucenter.ui.FousedTextView;
import com.flyou.henucenter.utils.ShareUtils;
import com.medroid.adapter.engine.NewsitemShow;
import com.medroid.adapter.engine.departments.ComputerNewsitemShow;
import com.medroid.adapter.engine.departments.FaNewsitemShow;
import com.medroid.adapter.engine.departments.HuanJingNewsitemShow;
import com.medroid.adapter.engine.departments.JkyNewsitemShow;
import com.medroid.adapter.engine.departments.LifeNewsitemShow;
import com.medroid.adapter.engine.departments.MinSHengNewsitemShow;
import com.medroid.adapter.engine.departments.OYaNewsitemShow;
import com.medroid.adapter.engine.departments.WenXueNewsitemShow;
import com.medroid.adapter.engine.departments.YaoNewsitemShow;
import com.medroid.adapter.engine.departments.YiNewsitemShow;
import com.medroid.adapter.engine.departments.ZheXueNewsitemShow;

public class DaitalNewsWebViewActivity extends Activity implements OnClickListener {

  // 0 1 2 3 4����14
  // ("������ѧѧԺ", "����ѧԺ", "��ѧԺ", "����ѧԺ", "��ѧԺ", "ҩѧԺ", "ҽѧԺ", "����ѧԺ",
  // "����ѧԺ", "������滮ѧԺ", "���������Ϣ����ѧԺ", "��ʷ�Ļ�ѧԺ", "ŷ�ǹ���ѧԺ", "������ѧѧԺ",
  // "��ѧ�빫������ѧԺ");
  private ImageButton back;
  private ImageButton share;
  private FousedTextView title;
  private WebView webView;
  private String newsUrl;
  private String newsTitle;
  private int Departments_type;
  private LinearLayout loading;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_daital_news_web_view);

    newsUrl = (String) getIntent().getExtras().get("newsUrl");
    newsTitle = (String) getIntent().getExtras().get("newsTitle");
    Departments_type = getIntent().getIntExtra("Departments_type", 200);
    // ��ʼ��view
    initView();
    // ��ʼ������
    initOper();

  }

  private void initOper() {
    loading.setVisibility(View.VISIBLE);

    title.setText(newsTitle);
    WebSettings webSettings = webView.getSettings();
    webView.getSettings().setRenderPriority(WebSettings.RenderPriority.HIGH);
    webSettings.setJavaScriptEnabled(true);
    webSettings.setBuiltInZoomControls(true);
    webSettings.setBlockNetworkImage(false);
    webSettings.setDefaultTextEncodingName("utf-8");
    webSettings.setLayoutAlgorithm(LayoutAlgorithm.SINGLE_COLUMN);
    webSettings.setTextSize(TextSize.NORMAL);
    webView.setBackgroundColor(0);
    webView.setWebViewClient(new WebViewClient());
    // ִ����ʾ�ֲ���ҳ
    new NewsItemWebViewAsyncTask().execute(newsUrl);
  }

  // ��ʼ��view
  private void initView() {
    loading = (LinearLayout) findViewById(R.id.loading);
    back = (ImageButton) findViewById(R.id.id_iv_left);
    share = (ImageButton) findViewById(R.id.id_iv_right);
    title = (FousedTextView) findViewById(R.id.tv_titlebar_desc);
    webView = (WebView) findViewById(R.id.wb_news_item);
    // ���ð�ť����
    back.setOnClickListener(this);
    share.setOnClickListener(this);

  }

  @Override
  public void onClick(View v) {
    switch (v.getId()) {
      case R.id.id_iv_left:
        // �жϽ���ʱ�������͵�֪ͨ
        if (Departments_type == 300) {

          Intent backIntent = new Intent(DaitalNewsWebViewActivity.this, HomeActivity.class);
          startActivity(backIntent);

        }
        finish();
        break;
      case R.id.id_iv_right:
        // Intent intent = new Intent();
        // intent.setAction("android.intent.action.SEND");
        // intent.addCategory(Intent.CATEGORY_DEFAULT);
        // intent.setType("text/plain");
        // intent.putExtra(Intent.EXTRA_TEXT, "��ʹ�úӴ��������������һ������" + newsTitle +
        // "����ַ����" + newsUrl);
        // startActivity(intent);
        ShareUtils share = new ShareUtils();
        View view = getWindow().getDecorView();
        share.showShare(DaitalNewsWebViewActivity.this, "��ʹ��[�Ӵ�����]���������һ������:" + "��" + newsTitle + "��" + newsUrl,
            newsUrl, view);
        break;

    }
  }

  public class NewsItemWebViewAsyncTask extends AsyncTask<String, String, String> {
    private String newsbody;

    // ��̨�����ȡ����
    @Override
    protected String doInBackground(String... params) {
      switch (Departments_type) {
        case 0:
          newsbody = JkyNewsitemShow.getNewsbody(params[0]);
          break;
        case 1:
          newsbody = MinSHengNewsitemShow.getNewsbody(params[0]);
          break;
        case 2:
          newsbody = FaNewsitemShow.getNewsbody(params[0]);
          break;
        case 3:
          newsbody = WenXueNewsitemShow.getNewsbody(params[0]);
          break;
        case 4:
          newsbody = YaoNewsitemShow.getNewsbody(params[0]);
          break;
        case 5:
          newsbody = YiNewsitemShow.getNewsbody(params[0]);
          break;
        case 6:

          break;
        case 7:

          break;
        case 8:
          newsbody = HuanJingNewsitemShow.getNewsbody(params[0]);
          break;
        case 9:
          newsbody = ComputerNewsitemShow.getNewsbody(params[0]);
          break;
        case 10:

          break;
        case 11:
          newsbody = OYaNewsitemShow.getNewsbody(params[0]);
          break;
        case 12:
          newsbody = LifeNewsitemShow.getNewsbody(params[0]);
          break;
        case 13:
          newsbody = ZheXueNewsitemShow.getNewsbody(params[0]);
          break;

        case 200:
          newsbody = NewsitemShow.getNewsbody(params[0]);
          break;
        case 300:
          newsbody = newsUrl;
          break;
        case 400:
          newsbody=newsUrl;
          break;
      }

      return newsbody;
    }

    /*
     * @param result
     */
    @Override
    protected void onPostExecute(String result) {
      // �ж��������Է��������͵���Ϣ,��������Ĭ������һ��url
      if (Departments_type == 300||Departments_type==400) {
        webView.loadUrl(result);
      } else {

        webView.loadDataWithBaseURL(newsUrl, result, "text/html", "utf-8", null);
      }
      loading.setVisibility(View.GONE);
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
