package com.flyou.henucenter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import cn.jpush.android.api.JPushInterface;

import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.core.CityInfo;
import com.baidu.mapapi.search.core.PoiInfo;
import com.baidu.mapapi.search.core.SearchResult;
import com.baidu.mapapi.search.poi.OnGetPoiSearchResultListener;
import com.baidu.mapapi.search.poi.PoiDetailResult;
import com.baidu.mapapi.search.poi.PoiNearbySearchOption;
import com.baidu.mapapi.search.poi.PoiResult;
import com.baidu.mapapi.search.poi.PoiSearch;
import com.baidu.mapapi.utils.DistanceUtil;
import com.flyou.henucenter.adapter.BaiduFoodAdapter;
import com.flyou.henucenter.domain.BaiduFood;
import com.flyou.henucenter.ui.XListView;
import com.flyou.henucenter.ui.XListView.IXListViewListener;

public class BaiDuFoodActivity extends Activity {
  private int totalpages = 0;
  private TextView textDesc;
  private Double Latitude;
  private Double Longitude;
  private LatLng locationData;
  private XListView listView = null;
  private PoiSearch poiSearch;
  private int currentPage = 0;
  private BaiduFoodAdapter adapter = null;
  private List<BaiduFood> baiduFoods;
  private String TAG = "BaiDuFoodActivity";
  private int myPosition;
  private LinearLayout loading;
  private String names[] = { "Ѱ��ʳ", "���Ƶ�", "����վ", "�Ѿ���", "������", "�����", "�䳬��", "�޵���", "ȡ��Ǯ" };
  private String keyWords[] = { "��ʳ", "�Ƶ�", "����վ", "����", "����", "ktv", "����", "����", "ATM" };

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    SDKInitializer.initialize(getApplicationContext());
    setContentView(R.layout.activity_bai_du_food);
    getLocationDate();

    initView();

    initData();
    Search();

    listView.setOnItemClickListener(new OnItemClickListener() {

      @Override
      public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(BaiDuFoodActivity.this, "position:" + position, 0).show();
      }
    });
  }

  private void Search() {
    poiSearch.searchNearby(new PoiNearbySearchOption().keyword(keyWords[myPosition]).location(locationData).radius(3000)
        .pageNum(currentPage));// ����Ϊ��λ������Ҳ���Լ��ƶ�
  }

  private void initData() {
    // ����titleBar����
    textDesc.setText(names[myPosition]);
    // ��ֻpoiserch�ļ���
    poiSearch.setOnGetPoiSearchResultListener(poiListener);

  }

  private void getLocationDate() {
    Latitude = getIntent().getDoubleExtra("Latitude", 39.997741);
    Longitude = getIntent().getDoubleExtra("Longitude", 116.316176);
    myPosition = getIntent().getIntExtra("position", 0);

    locationData = new LatLng(Latitude, Longitude);
  }

  private void initView() {
    loading = (LinearLayout) findViewById(R.id.loading);
    loading.setVisibility(View.VISIBLE);

    textDesc = (TextView) findViewById(R.id.tv_titlebar_desc);
    listView = (XListView) findViewById(R.id.listView);
    poiSearch = PoiSearch.newInstance();
    baiduFoods = new ArrayList<BaiduFood>();
    listView.setPullLoadEnable(true);
    listView.setPullRefreshEnable(true);
    listView.setRefreshTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
    listView.setXListViewListener(new IXListViewListener() {

      @Override
      public void onRefresh() {
        new Timer().schedule(new TimerTask() {

          @Override
          public void run() {
            runOnUiThread(new Runnable() {
              public void run() {

                listView.stopRefresh();
              }
            });
          }
        }, 2000);
      }

      @Override
      public void onLoadMore() {
        currentPage++;
        if (currentPage < totalpages) {
          poiSearch.searchNearby(new PoiNearbySearchOption().keyword(keyWords[myPosition]).location(locationData).radius(3000)
              .pageNum(currentPage));// ����Ϊ��λ������Ҳ���Լ��ƶ�

        } else {
          Toast.makeText(BaiDuFoodActivity.this, "û�и�����Ŀ ", 0).show();
          listView.stopLoadMore();
        }
      }
    });
  }

  // �ڶ���������POI���������ߣ�
  OnGetPoiSearchResultListener poiListener = new OnGetPoiSearchResultListener() {

    public void onGetPoiResult(PoiResult result) {
      loading.setVisibility(View.GONE);
      // ��ȡPOI�������
      if (result == null || result.error == SearchResult.ERRORNO.RESULT_NOT_FOUND) {
        Toast.makeText(BaiDuFoodActivity.this, "δ�ҵ����", Toast.LENGTH_LONG).show();
        return;
      }
      if (result.error == SearchResult.ERRORNO.NO_ERROR) {
        List<PoiInfo> allPoi = result.getAllPoi();
        totalpages = result.getTotalPageNum();
        currentPage = result.getCurrentPageNum();
        if (currentPage == 0) {

          baiduFoods = getDatas(allPoi);

          adapter = new BaiduFoodAdapter(BaiDuFoodActivity.this, baiduFoods);
          listView.setAdapter(adapter);

        } else {
          baiduFoods.addAll(getDatas(allPoi));
          adapter.notifyDataSetChanged();
          listView.stopLoadMore();

        }
        return;
      }
      if (result.error == SearchResult.ERRORNO.AMBIGUOUS_KEYWORD) {

        // ������ؼ����ڱ���û���ҵ����������������ҵ�ʱ�����ذ����ùؼ�����Ϣ�ĳ����б�
        String strInfo = "��";
        for (CityInfo cityInfo : result.getSuggestCityList()) {
          strInfo += cityInfo.city;
          strInfo += ",";
        }
        strInfo += "�ҵ����";
        Toast.makeText(BaiDuFoodActivity.this, strInfo, Toast.LENGTH_LONG).show();
      }
    }

    private List<BaiduFood> getDatas(List<PoiInfo> allPoi) {
      List<BaiduFood> newBaiduFoods = new ArrayList<BaiduFood>();
      for (int i = 0; i < allPoi.size(); i++) {
        String name = allPoi.get(i).name;
        String address = allPoi.get(i).address;
        String phone = allPoi.get(i).phoneNum + "";
        LatLng location = allPoi.get(i).location;
        double distance = DistanceUtil.getDistance(locationData, location);
        String city = allPoi.get(i).city;
        BaiduFood baiduFood = new BaiduFood(name, address, phone, location, distance, city);
        newBaiduFoods.add(baiduFood);
      }
      return newBaiduFoods;
    }

    public void onGetPoiDetailResult(PoiDetailResult result) {
      // ��ȡPlace����ҳ�������

    }
  };

  public void back(View view) {
    finish();
  }

  

  @Override
  protected void onDestroy() {
    super.onDestroy();
    poiSearch.destroy();
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
