package com.flyou.henucenter;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.LayoutParams;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;

public class WelcomeViewPaper extends Activity {
  private ViewPager viewPager;
  public static final int PAPERCOUNT = 4;
  private ImageView view;
  private List<ImageView> images;
  private Button enter;
  private LinearLayout ll_pointGroup;
  private int imageIds[] = { R.drawable.guide1, R.drawable.guide2, R.drawable.guide3, R.drawable.guide4 };
  private int lastPosition;
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_welcome_view_paper);
    ll_pointGroup = (LinearLayout) findViewById(R.id.ll_pointGroup);
    enter = (Button) findViewById(R.id.bt_enter);
    images = new ArrayList<ImageView>();
    viewPager = (ViewPager) findViewById(R.id.welcome_viewpaper);

    enter.setOnClickListener(new View.OnClickListener() {

      @Override
      public void onClick(View v) {
        Intent intent=new Intent(WelcomeViewPaper.this, HomeActivity.class);
        startActivity(intent);
        finish();
      }
    });
    // 增加数据
    for (int i = 0; i < PAPERCOUNT; i++) {
      view = new ImageView(WelcomeViewPaper.this);

      // 添加 指示点
      ImageView point = new ImageView(this);
      LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
          LinearLayout.LayoutParams.WRAP_CONTENT);

      params.rightMargin = 15;
      point.setLayoutParams(params);

      point.setBackgroundResource(R.drawable.point_selector);
      if (i == 0) {
        point.setEnabled(true);
      } else {
        point.setEnabled(false);
      }
      ll_pointGroup.addView(point);
      view.setImageResource(imageIds[i]);
      view.setScaleType(ScaleType.FIT_XY);
      images.add(view);

    }
    viewPager.setAdapter(new Myadapter());
    viewPager.setOnPageChangeListener(new OnPageChangeListener() {

      @Override
      public void onPageSelected(int position) {
        position = position % images.size();
      

        // 改变指示点的状态
        // 把当前点enbale 为true
        ll_pointGroup.getChildAt(position).setEnabled(true);
        //把上一个指示点设为false
        ll_pointGroup.getChildAt(lastPosition).setEnabled(false);  
        lastPosition = position;

        // 设置按钮显示
        if (position == PAPERCOUNT - 1) {
          enter.setVisibility(View.VISIBLE);
        } else {
          enter.setVisibility(View.INVISIBLE);
        }
      }

      @Override
      public void onPageScrolled(int arg0, float arg1, int arg2) {
      }

      @Override
      public void onPageScrollStateChanged(int arg0) {
      }
    });
  }

  public class Myadapter extends PagerAdapter {

    @Override
    public int getCount() {
      return PAPERCOUNT;
    }

    @Override
    public boolean isViewFromObject(View arg0, Object arg1) {
      return arg0 == arg1;
    }

    @Override
    public int getItemPosition(Object object) {
      return super.getItemPosition(object);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {

      container.removeView((View) object);
      object = null;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
      container.addView(images.get(position), LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
      return images.get(position);
    }

  }
}
