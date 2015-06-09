package com.flyou.henucenter;

import java.io.File;
import java.util.List;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.view.PagerAdapter;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.Toast;
import cn.jpush.android.api.JPushInterface;

import com.flyou.henucenter.adapter.HenuImageAdapter;
import com.flyou.henucenter.domain.HenuImages;
import com.flyou.henucenter.ui.ExtendedViewPager;
import com.flyou.henucenter.ui.TouchImageView;
import com.flyou.henucenter.utils.Constants;
import com.flyou.utils.SPUtils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.handmark.pulltorefresh.library.PullToRefreshGridView;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.lidroid.xutils.BitmapUtils;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.bitmap.BitmapDisplayConfig;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.lidroid.xutils.view.annotation.ViewInject;

public class HenuImageActivity extends Activity {
  private int showType = 0;
  private int currentPage = 0;
  private int imageCount = 15;

  @ViewInject(R.id.iv_henu_image_listview)
  private PullToRefreshListView listView;

  @ViewInject(R.id.gd_henu_image_gridView)
  private PullToRefreshGridView gridView;
  @ViewInject(R.id.view_pager)
  private ExtendedViewPager viewPage;
  public List<HenuImages> henuImageslist;
  private HenuImageAdapter adapter;
  private BitmapUtils bitmapUtils;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_henu_image);
    // 把activity添加入注解机制
    ViewUtils.inject(this);
    // 获取数据

    // 判断是否有缓存数据
    String oldData = (String) SPUtils.get(HenuImageActivity.this, Constants.HENU_IMAGE_URL, "");
    if (!TextUtils.isEmpty(oldData)) {
      operatorData(oldData);
    }

    initData();

  }

  private void initView() {

    adapter = new HenuImageAdapter(henuImageslist, HenuImageActivity.this);
    listView.setAdapter(adapter);
    viewPage.setAdapter(new TouchImageAdapter());

    // 获得存储路径 默认为内部存储
    File file = new File(Environment.getExternalStorageDirectory() + File.separator + "henuCenterCache");
    if (!file.exists()) {
      file.mkdir();
    }
    bitmapUtils = new BitmapUtils(HenuImageActivity.this, file.getAbsolutePath(), 0.15f, 80 * 1024 * 1024);
    // 正在加载的图
     bitmapUtils.configDefaultLoadingImage(R.drawable.henu_iamge_loading);
     // 加载失败的图
     bitmapUtils.configDefaultLoadFailedImage(R.drawable.henu_iamge__fauilt);
    bitmapUtils.configDefaultBitmapConfig(Bitmap.Config.RGB_565);

  }

  private void initData() {
    GetDataFromNet();
  }

  private void GetDataFromNet() {
    HttpUtils httpUtils = new HttpUtils();
    httpUtils.send(HttpMethod.GET,
        Constants.HENUIMAGEURL + "?currentPage=" + currentPage + "&imageCount=" + imageCount,
        new RequestCallBack<String>() {

          @Override
          public void onFailure(HttpException error, String msg) {
            Toast.makeText(HenuImageActivity.this, "获取网络图片异常", 0).show();
          }

          @Override
          public void onSuccess(ResponseInfo<String> responseInfo) {
            // 保存数据到本地
            SPUtils.put(HenuImageActivity.this, Constants.HENU_IMAGE_URL, responseInfo.result);
            operatorData(responseInfo.result);
          }
        });
  }

  // 处理数据
  public void operatorData(String result) {
    Gson gson = new Gson();
    henuImageslist = gson.fromJson(result, new TypeToken<List<HenuImages>>() {
    }.getType());
    // 获取到数据后，初始化view
    initView();
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

  // 按钮时间的响应
  public void back(View view) {
    finish();

  }

  public void switchShow(View view) {
    showType++;
    switch (showType % 3) {
      case 0:
        listView.setVisibility(View.VISIBLE);
        listView.setAdapter(adapter);

        gridView.setAdapter(null);
        gridView.setVisibility(View.GONE);

        viewPage.setVisibility(view.GONE);
        viewPage.setAdapter(null);
        view.setBackgroundResource(R.drawable.icon_pic_grid_type);
        break;
      case 1:
        gridView.setVisibility(View.VISIBLE);
        gridView.setAdapter(adapter);

        listView.setAdapter(null);

        listView.setVisibility(View.GONE);

        viewPage.setVisibility(view.GONE);
        viewPage.setAdapter(null);

        view.setBackgroundResource(R.drawable.icon_pic_list_type);
        break;
      case 2:
        viewPage.setVisibility(view.VISIBLE);
        gridView.setVisibility(View.GONE);
        listView.setVisibility(View.GONE);

        viewPage.setAdapter(new TouchImageAdapter());

        view.setBackgroundResource(R.drawable.icon_pic_detial_type);
        break;
    }

  }

  class TouchImageAdapter extends PagerAdapter {

    @Override
    public int getCount() {
      return henuImageslist.size();
    }

    @Override
    public View instantiateItem(ViewGroup container, int position) {
      TouchImageView img = new TouchImageView(container.getContext());

      bitmapUtils.display(img, Constants.HENUIMAGESURL + henuImageslist.get(position).url);
      bitmapUtils.configDefaultAutoRotation(true);
      img.setScaleType(ScaleType.CENTER_CROP);
      container.addView(img, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
      return img;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
      container.removeView((View) object);
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
      return view == object;
    }

  }
}
