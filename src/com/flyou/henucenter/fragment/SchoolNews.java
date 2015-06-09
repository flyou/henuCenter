package com.flyou.henucenter.fragment;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.flyou.henucenter.DaitalNewsWebViewActivity;
import com.flyou.henucenter.R;
import com.flyou.henucenter.adapter.NewsXlistviewAdapter;
import com.flyou.henucenter.domain.ImageDesc;
import com.flyou.henucenter.domain.News;
import com.flyou.henucenter.ui.XListView;
import com.flyou.henucenter.ui.XListView.IXListViewListener;
import com.flyou.henucenter.utils.Constants;
import com.flyou.henucenter.utils.NewsUrl;
import com.flyou.henucenter.utils.UrlImageUtil;
import com.flyou.utils.SPUtils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lidroid.xutils.BitmapUtils;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.lidroid.xutils.view.annotation.ViewInject;
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
public class SchoolNews extends Fragment {

  private ImageDesc desc;
  private NewsXlistviewAdapter adapter;
  public static final String TAG = "HomeFragment";
  @ViewInject(R.id.viewpaper)
  private ViewPager viewpaper;
  @ViewInject(R.id.tv_imageDesc)
  private TextView imageDesc;
  @ViewInject(R.id.ll_pointGroup)
  private LinearLayout ll_pointGroup;

  private List<ImageView> imageList;
  private XListView fragment1_listview;
  private List<News> news = null;
  public static boolean isRunning = false;
  public static int count = 1;
  private LinearLayout loading;
  // 图片资源ID
  private final int[] imageIds = { R.drawable.default1, R.drawable.default2, R.drawable.default3, R.drawable.default4,
      R.drawable.default5 };

  protected int lastPosition;
  private View view;
  private Handler handler;
  private List<Bitmap> urlImages;

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    view = inflater.inflate(R.layout.fragment01, null);
    top_viewPagerView = inflater.inflate(R.layout.school_news_top_viewpager, null);
    ViewUtils.inject(this, top_viewPagerView);
    return view;

  }

  @Override
  public void onCreate(Bundle savedInstanceState) {

    try {
      urlImages = UrlImageUtil.getURLImages();
    } catch (Exception e) {
      e.printStackTrace();
    }
    super.onCreate(savedInstanceState);

  }

  @Override
  public void onActivityCreated(Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);
    loading = (LinearLayout) view.findViewById(R.id.loading);
    loading.setVisibility(View.VISIBLE);

    isRunning = true;
    // 初始化viewPaper
    initViewPaper();
    // 初始化ListView
    initListView();
    getSelect();
    String locData = (String) SPUtils.get(getActivity(), NewsUrl.getSchoolNewsUrl(1), "");
    if (!TextUtils.isEmpty(locData)) {
      news = new Gson().fromJson(locData, new TypeToken<List<News>>() {
      }.getType());

      operatorListview();
    }
    // 加载数据
    getNewsData();

    fragment1_listview.addHeaderView(top_viewPagerView);
    // 自动轮换
    // 清除所有的handler防止内存泄露
    // handler.removeCallbacksAndMessages();
    handler.sendEmptyMessageDelayed(0, 2000);
    // 为item设置点击事件
    fragment1_listview.setOnItemClickListener(myItemClickListener);
  }

  public OnItemClickListener myItemClickListener = new OnItemClickListener() {

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
      Intent intent = new Intent(getActivity(), DaitalNewsWebViewActivity.class);
      // 由于Xlistview的设置模式，占用了下拉刷新，和header 所以需要进行减2操作
      intent.putExtra("newsUrl", news.get(position - 2).getUrl());
      intent.putExtra("newsTitle", news.get(position - 2).getTitle());
      startActivity(intent);
    }
  };
  private View top_viewPagerView;

  private void getSelect() {

    handler = new Handler() {

      @Override
      public void handleMessage(Message msg) {
        switch (msg.what) {
          case 0:
            // 滑动到下一页
            viewpaper.setCurrentItem(viewpaper.getCurrentItem() + 1);
            // 判断是否存在 是的话就执行
            if (isRunning) {
              sendEmptyMessageDelayed(0, 2000);
              Log.i(TAG, "isrunning:   " + isRunning);
            }
            break;
          case 1:
            loading.setVisibility(View.GONE);
            news = (List<News>) msg.obj;

            // 保存json数据到本地
            Gson newsjson = new Gson();
            String newsData = newsjson.toJson(news);
            if (!TextUtils.isEmpty(newsData)&&getActivity()!=null) {
              
              SPUtils.put(getActivity(), NewsUrl.getSchoolNewsUrl(1), newsData);
            }

            operatorListview();

            break;
          case 1000:

            break;
          case 2:
            news.addAll(news.size(), (List<News>) msg.obj);
            adapter.notifyDataSetChanged();
            fragment1_listview.stopLoadMore();
            break;
        }
      }

    };
  }

  private void operatorListview() {
    loading.setVisibility(View.GONE);
    if (adapter == null) {
      adapter = new NewsXlistviewAdapter(getActivity(), news);
      fragment1_listview.setAdapter(adapter);
    } else {
      adapter.notifyDataSetChanged();
      fragment1_listview.stopRefresh();
    }
  }

  private void initListView() {
    fragment1_listview = (XListView) view.findViewById(R.id.fragment1_listview);
    fragment1_listview.setPullLoadEnable(true);
    fragment1_listview.setPullRefreshEnable(true);

    // System.out.println(news.toString());

    // 设置刷新时间
    fragment1_listview.setRefreshTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
    fragment1_listview.setXListViewListener(new MyXlistViewAdapter());

  }

  private void getNewsData() {
    new Thread(new Runnable() {

      @Override
      public void run() {
        String path = NewsUrl.getSchoolNewsUrl(1);
        List<News> allNews = NewsUrlPaster.getAllNews(path);

        Message message = Message.obtain();
        // 获取数据为空直接土司结束
        if (allNews == null || allNews.size() == 0) {
          message.what = 1000;
          message.obj = 1;
        } else {
          message.what = 1;
          message.obj = allNews;
        }

        handler.sendMessage(message);
      }
    }).start();
  }

  public class MyXlistViewAdapter implements IXListViewListener {

    @Override
    public void onRefresh() {
      getNewsData();

    }

    @Override
    public void onLoadMore() {
      count++;

      new Thread(new Runnable() {

        @Override
        public void run() {
          String path = NewsUrl.getSchoolNewsUrl(count);
          List<News> allNews = NewsUrlPaster.getAllNews(path);

          Message message = Message.obtain();
          // 获取数据为空直接土司结束
          if (allNews == null || allNews.size() == 0) {
            message.what = 1000;
            message.obj = 1;
          } else {
            message.what = 2;
            message.obj = allNews;
          }

          handler.sendMessage(message);
        }
      }).start();

    }
  }

  // 设置 paperadapter
  public class MyAdapter extends PagerAdapter {

    @Override
    public int getCount() {
      return Integer.MAX_VALUE;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
      return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
      position = position % imageList.size();
      container.addView(imageList.get(position));
      return imageList.get(position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
      container.removeView((View) object);
      object = null;
    }

  }

  // 初始化ViewPaper
  private void initViewPaper() {

    viewpaper.setOffscreenPageLimit(0);

    imageList = new ArrayList<ImageView>();
    BitmapUtils bitmapUtils = new BitmapUtils(getActivity());
    bitmapUtils.configDefaultBitmapConfig(Config.ARGB_4444);

    for (int i = 0; i < imageIds.length; i++) {
      ImageView imageView = new ImageView(getActivity());
      imageView.setImageResource(imageIds[0]);
      bitmapUtils.display(imageView, Constants.SCHOOL_NEWS_TOP_VIEWPAGER_IMAGES_URL + (i + 1)
          + Constants.SCHOOL_NEWS_TOP_VIEWPAGER_IMAGES_URL_END);
      imageView.setScaleType(ScaleType.CENTER_CROP);
      imageList.add(imageView);

      // 添加指示点
      ImageView point = new ImageView(getActivity());
      LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(8, 8);

      params.rightMargin = 10;
      point.setLayoutParams(params);

      point.setBackgroundResource(R.drawable.point_selector);
      if (i == 0) {
        point.setEnabled(true);
      } else {
        point.setEnabled(false);
      }
      ll_pointGroup.addView(point);
    }

    viewpaper.setAdapter(new MyAdapter());
    // 初始化位置
    viewpaper.setCurrentItem(Integer.MAX_VALUE / 2 - (Integer.MAX_VALUE / 2 % imageList.size()));
    // 设置监听
    viewpaper.setOnPageChangeListener(new OnPageChangeListener() {
      // 滑动后
      @Override
      public void onPageSelected(int position) {
        position = position % imageList.size();
        final int finalPosition = position;
        HttpUtils httpUtils = new HttpUtils();
        httpUtils.send(HttpMethod.GET, Constants.SCHOOL_NEWS_TOP_VIEWPAGER_IMAGE_DESC, new RequestCallBack<String>() {

          @Override
          public void onFailure(HttpException arg0, String msg) {
            System.out.println("获取信息失败" + msg);
          }

          @Override
          public void onSuccess(ResponseInfo<String> responseInfo) {
            Gson gson = new Gson();
            desc = gson.fromJson(responseInfo.result, ImageDesc.class);

            try {
              imageDesc.setText(new String(desc.ImageDesc.get(finalPosition).getBytes(), "utf-8"));
            } catch (UnsupportedEncodingException e) {

            }
          }
        });
        // 设置文字描述内容

        // 改变指示点的状态
        // 把当前点enbale 为true
        ll_pointGroup.getChildAt(position).setEnabled(true);
        // 把上一个点设为false
        ll_pointGroup.getChildAt(lastPosition).setEnabled(false);
        lastPosition = position;

        Log.i(TAG, "page changed …………");
      }

      // 滑动时
      @Override
      public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
      }

      // 状态改变
      @Override
      public void onPageScrollStateChanged(int state) {
      }
    });

  }

  @Override
  public void onDestroy() {
    isRunning = false;
    super.onDestroy();
    handler.removeCallbacksAndMessages(null);
  }

  public interface GetHomeFrageMetord {
    public List<Bitmap> getBitmaps();

  }

  @Override
  public void onPause() {
    super.onPause();
    isRunning = false;
  }

  @Override
  public void onResume() {
    super.onResume();
    isRunning = true;
  }
}
