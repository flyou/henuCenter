package com.flyou.henucenter;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import cn.jpush.android.api.JPushInterface;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MyLocationConfiguration;
import com.baidu.mapapi.map.MyLocationConfiguration.LocationMode;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.model.LatLng;

public class LocationMapActivity extends Activity {
  private LocationClient client;
  private BDLocationListener listener;
  private boolean isFirstLoc = true;
  MapView mMapView = null;
  BitmapDescriptor mCurrentMarker;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    // 在使用SDK各组件之前初始化context信息，传入ApplicationContext
    // 注意该方法要再setContentView方法之前实现
    SDKInitializer.initialize(getApplicationContext());

    setContentView(R.layout.location_map);
    // 获取地图控件引用
    mMapView = (MapView) findViewById(R.id.bmapView);

    map = mMapView.getMap();
    // 设置开启定位自己的位置
    map.setMyLocationEnabled(true);

    // 设置定位点图标，可以使用系统的也可以自定义

    mCurrentMarker = BitmapDescriptorFactory.fromResource(R.drawable.icon_geo);
    // 如果使用系统的让mCurrentMarker为空即可
    map.setMyLocationConfigeration(new MyLocationConfiguration(LocationMode.NORMAL, true, mCurrentMarker));
    location();
  }

  private void location() {
    client = new LocationClient(getApplicationContext()); // 声明LocationClient类
    listener = Mylisetener;
    // 注册监听
    client.registerLocationListener(listener);

    LocationClientOption option = new LocationClientOption();
    option.setCoorType("bd09ll");// 返回的定位结果是百度经纬度,默认值gcj02
    option.setScanSpan(5000);// 设置发起定位请求的间隔时间为5000ms
    option.setOpenGps(true);// 打开gps
    // option.setIsNeedAddress(true);// 返回的定位结果包含地址信息
    // option.setNeedDeviceDirect(true);// 返回的定位结果包含手机机头的方向
    client.setLocOption(option);
  }

  @Override
  protected void onDestroy() {
    super.onDestroy();
    // 在activity执行onDestroy时执行mMapView.onDestroy()，实现地图生命周期管理
    mMapView.onDestroy();
    // map.setMyLocationEnabled(false);
  }


  @Override
  protected void onResume() {
    super.onResume();
    location();
    // 在activity执行onResume时执行mMapView. onResume ()，实现地图生命周期管理
    mMapView.onResume();

    client.start();

    JPushInterface.onResume(this);
  }

  @Override
  protected void onPause() {
    super.onPause();
    // 在activity执行onPause时执行mMapView. onPause ()，实现地图生命周期管理
    mMapView.onPause();

    client.stop();
    JPushInterface.onPause(this);
  }

  BDLocationListener Mylisetener = new BDLocationListener() {

    @Override
    public void onReceiveLocation(BDLocation location) {
      // map view 销毁后不在处理新接收的位置
      if (location == null || mMapView == null)
        return;
      MyLocationData locData = new MyLocationData.Builder().accuracy(location.getRadius())
      // 此处设置开发者获取到的方向信息，顺时针0-360
          .direction(100).latitude(location.getLatitude()).longitude(location.getLongitude()).build();
      //这只中心点坐标
      map.setMyLocationData(locData);
      if (isFirstLoc) {
        isFirstLoc = false;
        LatLng ll = new LatLng(location.getLatitude(), location.getLongitude());
        // 缩放的范围是3-20
        MapStatusUpdate u = MapStatusUpdateFactory.newLatLngZoom(ll, 17f);

        map.animateMapStatus(u);

      }
    }

  };
  private BaiduMap map;
  
  public void back(View view) {

    finish();

  }
}
