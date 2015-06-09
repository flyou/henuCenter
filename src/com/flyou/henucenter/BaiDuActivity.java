package com.flyou.henucenter;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import cn.jpush.android.api.JPushInterface;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.model.LatLng;

public class BaiDuActivity extends Activity {
  private LatLng locData;
  private LocationClient client;
  private BDLocationListener listener;
  private GridView gridView;
  private double Latitude;
  private double Longitude;
  private int imageIds[] = { R.drawable.dinner, R.drawable.hotel, R.drawable.bus, R.drawable.scenic,
      R.drawable.cybercafe, R.drawable.ktv, R.drawable.mall, R.drawable.salon, R.drawable.bank };
  private String names[] = { "Ѱ��ʳ", "���Ƶ�", "����վ", "�Ѿ���", "������", "�����", "�䳬��", "�޵���", "ȡ��Ǯ" };

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    SDKInitializer.initialize(getApplicationContext());
    setContentView(R.layout.activity_bai_du);
    initView();

  }

  private void initView() {

    gridView = (GridView) findViewById(R.id.baidu_service);
    gridView.setSelector(new ColorDrawable(Color.TRANSPARENT));
    gridView.setAdapter(BaiDuadapter);
    gridView.setOnItemClickListener(new OnItemClickListener() {

      @Override
      public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(BaiDuActivity.this, BaiDuFoodActivity.class);
        intent.putExtra("Latitude", Latitude);
        intent.putExtra("Longitude", Longitude);
        intent.putExtra("position", position);
        startActivity(intent);
      }

    });
  }

  private BaseAdapter BaiDuadapter = new BaseAdapter() {

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
      ViewHolder holder;
      if (convertView == null) {
        holder = new ViewHolder();
        convertView = View.inflate(BaiDuActivity.this, R.layout.list_item_baidu_service, null);
        holder.name = (TextView) convertView.findViewById(R.id.tv_item_name);
        holder.icon = (ImageView) convertView.findViewById(R.id.iv_item_icon);
        convertView.setTag(holder);
      } else {
        holder = (ViewHolder) convertView.getTag();
      }
      holder.icon.setImageResource(imageIds[position]);
      holder.name.setText(names[position]);
      return convertView;
    }

    @Override
    public long getItemId(int position) {
      return position;
    }

    @Override
    public Object getItem(int position) {
      return position;
    }

    @Override
    public int getCount() {
      return imageIds.length;
    }
  };

  private class ViewHolder {
    TextView name;
    ImageView icon;

  }

  // ----------------------------------------------------------------------------------
  public void share(View view) {
    // ����λ��

  }

  public void back(View view) {

    finish();

  }

  public void ShowMap(View view) {
    Intent intent = new Intent(BaiDuActivity.this, LocationMapActivity.class);

    startActivity(intent);

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

  BDLocationListener Mylisetener = new BDLocationListener() {

    @Override
    public void onReceiveLocation(BDLocation location) {
      // map view ���ٺ��ڴ����½��յ�λ��
      if (location == null)
        return;
      // ����λ����Ϣ
      Latitude = location.getLatitude();
      Longitude = location.getLongitude();

      // Toast.makeText(BaiDuActivity.this,"Latitude:"+location.getLatitude()+",Longitude:"+
      // location.getLongitude(), 0).show();
      // locData = new LatLng(location.getLatitude(), location.getLongitude());

    }

  };

  @Override
  protected void onDestroy() {
    super.onDestroy();
    // ��activityִ��onDestroyʱִ��mMapView.onDestroy()��ʵ�ֵ�ͼ�������ڹ���

  }

  @Override
  protected void onResume() {
    super.onResume();
    location();

    client.start();
    JPushInterface.onResume(this);
  }

  @Override
  protected void onPause() {
    super.onPause();
    client.stop();
    JPushInterface.onPause(this);
  }


}
