package com.flyou.henucenter.adapter;

import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.flyou.henucenter.POILocationMapActivity;
import com.flyou.henucenter.R;
import com.flyou.henucenter.domain.BaiduFood;

/**
 * ============================================================ 项目名称：HenuCenter
 * 
 * 类名称：BaiduFoodAdapter
 * 
 * 类描述：
 * 
 * 创建人：flyou
 * 
 * 创建时间：2015-5-4 下午5:21:46
 * 
 * 修改备注：百度美食的适配器操作
 * 
 * 版本：@version v1.0
 * ============================================================
 */
public class BaiduFoodAdapter extends BaseAdapter {
  public static final String TAG = "BaiduFoodAdapter";
  private List<BaiduFood> baiduFoods;
  private final Context context;

  public BaiduFoodAdapter(Context context, List<BaiduFood> baiduFoods) {
    this.context = context;
    this.baiduFoods = baiduFoods;
  }

  @Override
  public int getCount() {
    return baiduFoods.size();
  }

  @Override
  public Object getItem(int position) {
    return position;
  }

  @Override
  public long getItemId(int position) {
    return position;
  }

  @Override
  public View getView(final int position, View convertView, ViewGroup parent) {
    ViewHolder holder;
    if (convertView == null) {
      holder = new ViewHolder();
      convertView = View.inflate(context, R.layout.baidu_map_food, null);
      holder.name = (TextView) convertView.findViewById(R.id.name);
      holder.address = (TextView) convertView.findViewById(R.id.address);
      holder.distance = (TextView) convertView.findViewById(R.id.distance);
      holder.call = (TextView) convertView.findViewById(R.id.call);
      holder.gothere = (TextView) convertView.findViewById(R.id.gothere);
      convertView.setTag(holder);

    } else {
      holder = (ViewHolder) convertView.getTag();
    }
    holder.name.setText(baiduFoods.get(position).getName());
    holder.address.setText(baiduFoods.get(position).getAddress());
    holder.distance.setText((int) baiduFoods.get(position).getDistance() + "米");
    // 电话操作，地图操作
    if (TextUtils.isEmpty(baiduFoods.get(position).getPhone())) {
      holder.call.setVisibility(View.GONE);
    } else {
      holder.call.setTag(position);
      holder.call.setOnClickListener(new Call());

    }
    holder.gothere.setTag(position);
    holder.gothere.setOnClickListener(new GoThere());

    return convertView;
  }

  public class ViewHolder {
    TextView name;
    TextView address;
    TextView distance;
    TextView call;
    TextView gothere;
  }
  class GoThere implements OnClickListener{
    @Override
    public void onClick(View v) {
      // 地图定位操作
      Intent intent=new Intent(context, POILocationMapActivity.class);
      intent.putExtra("Latitude", baiduFoods.get((Integer)v.getTag()).getLocation().latitude);
      intent.putExtra("Longitude", baiduFoods.get((Integer)v.getTag()).getLocation().longitude);
      intent.putExtra("name", baiduFoods.get((Integer)v.getTag()).getName());
      context.startActivity(intent);
    }}
  
  class Call implements OnClickListener{

    @Override
    public void onClick(View v) {
      // 拨打电话的意图操作
      Uri uri = Uri.parse("tel:" + baiduFoods.get((Integer)v.getTag()).getPhone()) ;  // 设置操作的路径
      Intent it = new Intent() ;
      it.setAction(Intent.ACTION_DIAL) ;  // 设置要操作的Action
      it.setData(uri) ; // 要设置的数据
      context.startActivity(it) ; // 执行跳转
    }}
 
}
