package com.flyou.henucenter;

import com.medroid.adapter.engine.CETUrlPaster;
import com.medroid.adapter.engine.NewsitemShow;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.webkit.WebSettings.LayoutAlgorithm;
import android.webkit.WebSettings.TextSize;

public class CETQueryActivity extends Activity {
private WebView webView;
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_cetquery);
    webView=(WebView) findViewById(R.id.wb_cet_query);
    
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
    new CETQueryWebViewAsyncTask().execute("411011142205311","房泽龙");
    
  }
  public class CETQueryWebViewAsyncTask extends AsyncTask<String, String, String> {
    // 后台处理获取数据
    @Override
    protected String doInBackground(String... params) {
      String newsbody = CETUrlPaster.GetCETShow(params[0], params[1]);

      return newsbody;
    }

    /*
     * @param result
     */
    @Override
    protected void onPostExecute(String result) {

      webView.loadDataWithBaseURL("http://www.chsi.com.cn/cet/query?zkzh="+"&xm="+"", result, "text/html", "utf-8", null);
     
    }

  }

}
