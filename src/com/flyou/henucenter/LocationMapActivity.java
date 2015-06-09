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
    // ��ʹ��SDK�����֮ǰ��ʼ��context��Ϣ������ApplicationContext
    // ע��÷���Ҫ��setContentView����֮ǰʵ��
    SDKInitializer.initialize(getApplicationContext());

    setContentView(R.layout.location_map);
    // ��ȡ��ͼ�ؼ�����
    mMapView = (MapView) findViewById(R.id.bmapView);

    map = mMapView.getMap();
    // ���ÿ�����λ�Լ���λ��
    map.setMyLocationEnabled(true);

    // ���ö�λ��ͼ�꣬����ʹ��ϵͳ��Ҳ�����Զ���

    mCurrentMarker = BitmapDescriptorFactory.fromResource(R.drawable.icon_geo);
    // ���ʹ��ϵͳ����mCurrentMarkerΪ�ռ���
    map.setMyLocationConfigeration(new MyLocationConfiguration(LocationMode.NORMAL, true, mCurrentMarker));
    location();
  }

  private void location() {
    client = new LocationClient(getApplicationContext()); // ����LocationClient��
    listener = Mylisetener;
    // ע�����
    client.registerLocationListener(listener);

    LocationClientOption option = new LocationClientOption();
    option.setCoorType("bd09ll");// ���صĶ�λ����ǰٶȾ�γ��,Ĭ��ֵgcj02
    option.setScanSpan(5000);// ���÷���λ����ļ��ʱ��Ϊ5000ms
    option.setOpenGps(true);// ��gps
    // option.setIsNeedAddress(true);// ���صĶ�λ���������ַ��Ϣ
    // option.setNeedDeviceDirect(true);// ���صĶ�λ��������ֻ���ͷ�ķ���
    client.setLocOption(option);
  }

  @Override
  protected void onDestroy() {
    super.onDestroy();
    // ��activityִ��onDestroyʱִ��mMapView.onDestroy()��ʵ�ֵ�ͼ�������ڹ���
    mMapView.onDestroy();
    // map.setMyLocationEnabled(false);
  }


  @Override
  protected void onResume() {
    super.onResume();
    location();
    // ��activityִ��onResumeʱִ��mMapView. onResume ()��ʵ�ֵ�ͼ�������ڹ���
    mMapView.onResume();

    client.start();

    JPushInterface.onResume(this);
  }

  @Override
  protected void onPause() {
    super.onPause();
    // ��activityִ��onPauseʱִ��mMapView. onPause ()��ʵ�ֵ�ͼ�������ڹ���
    mMapView.onPause();

    client.stop();
    JPushInterface.onPause(this);
  }

  BDLocationListener Mylisetener = new BDLocationListener() {

    @Override
    public void onReceiveLocation(BDLocation location) {
      // map view ���ٺ��ڴ����½��յ�λ��
      if (location == null || mMapView == null)
        return;
      MyLocationData locData = new MyLocationData.Builder().accuracy(location.getRadius())
      // �˴����ÿ����߻�ȡ���ķ�����Ϣ��˳ʱ��0-360
          .direction(100).latitude(location.getLatitude()).longitude(location.getLongitude()).build();
      //��ֻ���ĵ�����
      map.setMyLocationData(locData);
      if (isFirstLoc) {
        isFirstLoc = false;
        LatLng ll = new LatLng(location.getLatitude(), location.getLongitude());
        // ���ŵķ�Χ��3-20
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
