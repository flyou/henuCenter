package com.flyou.henucenter.fragment;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.flyou.henucenter.DaitalNewsWebViewActivity;
import com.flyou.henucenter.HomeActivity;
import com.flyou.henucenter.R;
import com.flyou.henucenter.adapter.NewsXlistviewAdapter;
import com.flyou.henucenter.domain.News;
import com.flyou.henucenter.ui.XListView;
import com.flyou.henucenter.ui.XListView.IXListViewListener;
import com.flyou.henucenter.utils.Constants;
import com.flyou.henucenter.utils.NewsUrl;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingFragmentActivity;
import com.medroid.adapter.engine.departments.ComputerUrlPaster;
import com.medroid.adapter.engine.departments.FaUrlPaster;
import com.medroid.adapter.engine.departments.HuanJingUrlPaster;
import com.medroid.adapter.engine.departments.JkyUrlPaster;
import com.medroid.adapter.engine.departments.LifeUrlPaster;
import com.medroid.adapter.engine.departments.MinShengUrlPaster;
import com.medroid.adapter.engine.departments.OYaUrlPaster;
import com.medroid.adapter.engine.departments.WenXueUrlPaster;
import com.medroid.adapter.engine.departments.YaoUrlPaster;
import com.medroid.adapter.engine.departments.YiUrlPaster;
import com.medroid.adapter.engine.departments.ZheXueUrlPaster;

/**
 * ============================================================ 项目名称：Fragement01
 * 
 * 类名称：FragMent05
 * 
 * 类描述：
 * 
 * 创建人：flyou
 * 
 * 创建时间：2015-4-6 下午2:16:39
 * 
 * 修改备注：
 * 
 * 版本：@version ==========================.==================================
 */
// 0 1 2 3 4……14
// ("教育科学学院", "民生学院", "法学院", "护理学院", "文学院", "药学院", "医学院", "外语学院",
// "体育学院", "环境与规划学院", "计算机与信息工程学院", "历史文化学院", "欧亚国际学院", "生命科学学院", "哲学与公共管理学院");
public class DepartmentFragMent extends Fragment implements OnClickListener {
  public static final String TAG = "FragMent03";
  private View view;
  private XListView listView;
  private List<News> news;
  private int pageCount = 1;
  private LinearLayout loading;
  private NewsXlistviewAdapter adapter = null;

  public static int type = 0;

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    view = inflater.inflate(R.layout.fragment05, null);
    // 注册监听
    registerReceiver();
    // 初始化titlebar
    initTitleBar();
    initMenu();
    return view;

  }

  private void initMenu() {
    menu = ((SlidingFragmentActivity) getActivity()).getSlidingMenu();
    menu.setMode(SlidingMenu.LEFT);
    menu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
    
  }

  // 初始化titlebar
  private void initTitleBar() {
    leftMenu = ((HomeActivity) getActivity()).getLeftMenu();
    leftMenu.setVisibility(View.VISIBLE);

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
    listView = (XListView) view.findViewById(R.id.fragment3_listview);
    listView.setPullLoadEnable(true);
    listView.setPullRefreshEnable(true);
    listView.setRefreshTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
    new MyAsyncTask().execute(type, 1);
    listView.setXListViewListener(MyXlistViewAdapter);
    listView.setOnItemClickListener(new OnItemClickListener() {

      @Override
      public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        Intent intent = new Intent(getActivity(), DaitalNewsWebViewActivity.class);
        intent.putExtra("newsUrl", news.get(position - 1).getUrl());
        intent.putExtra("Departments_type", type);
        intent.putExtra("newsTitle", news.get(position - 1).getTitle());

        startActivity(intent);
      }

    });

  }

  public IXListViewListener MyXlistViewAdapter = new IXListViewListener() {

    @Override
    public void onRefresh() {
      // 第一个参数是显示院系的对应position 从零开始，第二个参数为页码
      new MyAsyncTask().execute(type, 1);

    }

    @Override
    public void onLoadMore() {
      pageCount++;
      new MyAsyncTask().execute(type, pageCount);
    }
  };

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
  }

  // 异步处理网络数据
  public class MyAsyncTask extends AsyncTask<Integer, String, List<News>> {

    private List<News> allNews;

    @Override
    protected List<News> doInBackground(Integer... params) {
      switch (params[0]) {
        case 0:
          String path0 = NewsUrl.getJKYUrl(params[1]);
          allNews = JkyUrlPaster.GetShow(path0);
          break;
        case 1:
          String path1 = NewsUrl.getMINSHENGUrl(params[1]);
          allNews = MinShengUrlPaster.GetShow(path1);
          break;
        case 2:
          String path2 = NewsUrl.getFAUrl(params[1]);
          allNews = FaUrlPaster.GetShow(path2);
          break;
        case 3:
          String path3 = NewsUrl.getWENXUEUrl(params[1]);
          allNews = WenXueUrlPaster.GetShow(path3);
          break;
        case 4:
          String path4 = NewsUrl.getYAOUrl(params[1]);
          allNews = YaoUrlPaster.GetShow(path4);

          break;
        case 5:
          String path5 = NewsUrl.getYIUrl(params[1]);
          allNews = YiUrlPaster.GetShow(path5);

          break;
        case 6:

          break;
        case 7:

          break;
        case 8:
          String path8 = NewsUrl.getHUANJINGUrl(params[1]);
          allNews = HuanJingUrlPaster.GetShow(path8);

          break;
        case 9:
          String path9 = NewsUrl.getCOMPUTERUrl(params[1]);
          allNews = ComputerUrlPaster.GetShow(path9);
          break;
        case 10:

          break;
        case 11:
          String path11 = NewsUrl.getOYAUrl(params[1]);
          allNews = OYaUrlPaster.GetShow(path11);
          break;
        case 12:
          String path12 = NewsUrl.getLIFEUrl(params[1]);
          allNews = LifeUrlPaster.GetShow(path12);
          break;
        case 13:
          String path13 = NewsUrl.getZHEXUEUrl(params[1]);
          allNews = ZheXueUrlPaster.GetShow(path13);
          break;

      }
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
      loading.setVisibility(View.GONE);

      if (adapter == null) {
        System.out.println("adapter==null");
        news = result;
        adapter = new NewsXlistviewAdapter(getActivity(), result);
        listView.setAdapter(adapter);
      } else {

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

  @Override
  public void onClick(View v) {
    // 发送广播通知activity关闭侧滑

  }

  // 注册广播
  private void registerReceiver() {
    // 来自左侧item的广播
    IntentFilter filter = new IntentFilter(Constants.LEFITEM_ACTION);
    filter.addCategory(Intent.CATEGORY_DEFAULT);
    getActivity().registerReceiver(LeftMenureceiver, filter);

  }

  private BroadcastReceiver LeftMenureceiver = new BroadcastReceiver() {

    @Override
    public void onReceive(Context context, Intent intent) {
      int position = intent.getIntExtra("Left_position", 0);
      type = position;

    }
  };
  private SlidingMenu menu;
  private ImageButton leftMenu;
public void onDestroy() {
  super.onDestroy();
  menu.setTouchModeAbove(SlidingMenu.TOUCHMODE_NONE);
  
  leftMenu.setVisibility(View.GONE);
  if (LeftMenureceiver!=null) {
    getActivity().unregisterReceiver(LeftMenureceiver);
  }
};
}
