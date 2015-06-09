package com.flyou.henucenter.fragment;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.flyou.henucenter.DaitalNewsWebViewActivity;
import com.flyou.henucenter.HomeActivity;
import com.flyou.henucenter.R;
import com.flyou.henucenter.adapter.NewsXlistviewAdapter;
import com.flyou.henucenter.domain.News;
import com.flyou.henucenter.ui.XListView;
import com.flyou.henucenter.ui.XListView.IXListViewListener;
import com.flyou.henucenter.utils.NewsUrl;
import com.flyou.utils.SPUtils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingFragmentActivity;
import com.medroid.adapter.engine.NewsUrlPaster;

/**
 * ============================================================ 项目名称：Fragement01
 * 
 * 类名称：FragMent01
 * 
 * 类描述：
 * 
 * 创建人：flyou
 * 
 * 创建时间：2015-4-6 下午12:51:13
 * 
 * 修改备注：
 * 
 * 版本：@version ============================================================
 */
public class SchoolMessage extends Fragment {
  public static final String TAG = "FragMent02";
  private View view;
  private XListView listView;
  private List<News> news;
  private int pageCount = 1;
  private LinearLayout loading;
  private NewsXlistviewAdapter adapter = null;

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    view = inflater.inflate(R.layout.fragment02, null);

    return view;

  }

  @Override
  public void onActivityCreated(Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);
    loading = (LinearLayout) view.findViewById(R.id.loading);
    loading.setVisibility(View.VISIBLE);
    initListView();

  }

  private void initListView() {
    news = new ArrayList<News>();
    listView = (XListView) view.findViewById(R.id.fragment2_listview);
    listView.setPullLoadEnable(true);
    listView.setPullRefreshEnable(true);
    listView.setRefreshTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));

    // 检查本地数据
    String locData = (String) SPUtils.get(getActivity(), NewsUrl.getSchoolMessageUrl(1), "");
    if (!TextUtils.isEmpty(locData)) {
      news = new Gson().fromJson(locData, new TypeToken<List<News>>() {
      }.getType());
      firstLoadData(news);
      loading.setVisibility(View.GONE);
      adapter = null;

    }
    new MyAsyncTask().execute(1);
    listView.setXListViewListener(MyXlistViewAdapter);
    listView.setOnItemClickListener(new OnItemClickListener() {

      @Override
      public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        Intent intent = new Intent(getActivity(), DaitalNewsWebViewActivity.class);
        intent.putExtra("newsUrl", news.get(position - 1).getUrl());
        intent.putExtra("newsTitle", news.get(position - 1).getTitle());
        startActivity(intent);
      }

    });

  }

  public IXListViewListener MyXlistViewAdapter = new IXListViewListener() {

    @Override
    public void onRefresh() {
      new MyAsyncTask().execute(1);

    }

    @Override
    public void onLoadMore() {
      pageCount++;
      new MyAsyncTask().execute(pageCount);
    }
  };

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
  }

  // 异步处理网络数据
  public class MyAsyncTask extends AsyncTask<Integer, String, List<News>> {

    @Override
    protected List<News> doInBackground(Integer... params) {
      String schoolMessageUrl = NewsUrl.getSchoolMessageUrl(params[0]);
      List<News> allNews = NewsUrlPaster.getAllNews(schoolMessageUrl);
      return allNews;
    }

    @Override
    protected void onPostExecute(List<News> result) {
      listView.stopRefresh();
      listView.stopLoadMore();
      if (result == null) {
        // Toast.makeText(getActivity(), "网络或服务器异常", 0).show();
        return;
      }

      if (adapter == null) {

        news = result;

        // 保存json数据到本地
        Gson newsjson = new Gson();
        String newsData = newsjson.toJson(news);
        if (!TextUtils.isEmpty(newsData) && getActivity() != null) {

          SPUtils.put(getActivity(), NewsUrl.getSchoolMessageUrl(1), newsData);
        }
        firstLoadData(result);
      } else {
        loading.setVisibility(View.GONE);

        if (result.contains(news.get(0))) {
          news = result;
          adapter.notifyDataSetChanged();

        } else {

          news.addAll(result);
          adapter.notifyDataSetChanged();

        }

      }
    }

  }

  private void firstLoadData(List<News> result) {

    adapter = new NewsXlistviewAdapter(getActivity(), news);
    listView.setAdapter(adapter);
  }

}
