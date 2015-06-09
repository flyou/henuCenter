package com.flyou.henucenter;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

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
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.MyLocationConfiguration;
import com.baidu.mapapi.map.MyLocationConfiguration.LocationMode;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.overlayutil.OverlayManager;
import com.baidu.mapapi.search.core.RouteLine;
import com.baidu.mapapi.search.route.RoutePlanSearch;

public class POILocationMapActivity extends Activity {
  private LocationClient client;
  private BDLocationListener listener;
  private boolean isFirstLoc = true;
  private MapView mMapView = null;
  private BitmapDescriptor mCurrentMarker;
  private TextView tv_titlebar_desc;
  private RoutePlanSearch mSearch;
  private LatLng aimLng;
  private LatLng myLng;
  private BaiduMap map;
  private ImageButton id_iv_right;
  private ListView listView;
//���·�߽ڵ����
  Button mBtnPre = null;//��һ���ڵ�
  Button mBtnNext = null;//��һ���ڵ�
  int nodeIndex = -1;//�ڵ�����,������ڵ�ʱʹ��
  RouteLine route = null;
  OverlayManager routeOverlay = null;
  boolean useDefaultIcon = false;
  private TextView popupText = null;//����view
  private int imageID[] = { R.drawable.ft_sync_st01, R.drawable.bus_sync_st01, R.drawable.car_sync_st01 };

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    // ��ʹ��SDK�����֮ǰ��ʼ��context��Ϣ������ApplicationContext
    // ע��÷���Ҫ��setContentView����֮ǰʵ��
    SDKInitializer.initialize(getApplicationContext());

    setContentView(R.layout.location_map);
    mSearch = RoutePlanSearch.newInstance();
    tv_titlebar_desc = (TextView) findViewById(R.id.tv_titlebar_desc);
    id_iv_right = (ImageButton) findViewById(R.id.id_iv_right);
//    id_iv_right.setVisibility(View.VISIBLE);
   

    // ��ȡ��ͼ�ؼ�����

    mMapView = (MapView) findViewById(R.id.bmapView);

    // ��ȡ��ͼ������
    map = mMapView.getMap();
    
    // ���ÿ�����λ�Լ���λ��
    map.setMyLocationEnabled(true);
    // ����markͼ��

    double Latitude = getIntent().getDoubleExtra("Latitude", 39.997741);
    double Longitude = getIntent().getDoubleExtra("Longitude", 116.316176);
    // ��ȡĿ�ĵ�����
    aimLng = new LatLng(Latitude, Longitude);
    String name = getIntent().getStringExtra("name");
    tv_titlebar_desc.setText(name);
    LatLng locationData = new LatLng(Latitude, Longitude);

    BitmapDescriptor bitmap = BitmapDescriptorFactory.fromResource(R.drawable.icon_marka);
    // ����markeroption,�����ڵ�ͼ�����marker
    OverlayOptions options = new MarkerOptions().icon(bitmap).title(name).position(locationData);

    map.addOverlay(options);
    // ���ö�λ��ͼ�꣬����ʹ��ϵͳ��Ҳ�����Զ���

    // mCurrentMarker =
    // BitmapDescriptorFactory.fromResource(R.drawable.icon_geo);
    // ���ʹ��ϵͳ����mCurrentMarkerΪ�ռ���
    map.setMyLocationConfigeration(new MyLocationConfiguration(LocationMode.NORMAL, true, null));
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
    mSearch.destroy();
  }

  @Override
  protected void onResume() {
    super.onResume();
    location();
    // ��activityִ��onResumeʱִ��mMapView. onResume ()��ʵ�ֵ�ͼ�������ڹ���
    mMapView.onResume();
    // ����ʵ����
   
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

  // ��λ�ļ���
  BDLocationListener Mylisetener = new BDLocationListener() {

    @Override
    public void onReceiveLocation(BDLocation location) {
      // map view ���ٺ��ڴ����½��յ�λ��
      if (location == null || mMapView == null)
        return;
      MyLocationData locData = new MyLocationData.Builder().accuracy(location.getRadius())
      // �˴����ÿ����߻�ȡ���ķ�����Ϣ��˳ʱ��0-360
          .direction(100).latitude(location.getLatitude()).longitude(location.getLongitude()).build();
      // ��ֻ���ĵ�����
      map.setMyLocationData(locData);
      if (isFirstLoc) {
        isFirstLoc = false;
        myLng = new LatLng(location.getLatitude(), location.getLongitude());
        // ���ŵķ�Χ��3-20
        MapStatusUpdate u = MapStatusUpdateFactory.newLatLngZoom(myLng, 17f);

        map.animateMapStatus(u);

      }
    }

  };
 
public void back(View view){
  
  finish();
}
  
  
}
