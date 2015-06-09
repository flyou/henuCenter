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
 * ============================================================ ��Ŀ���ƣ�HenuCenter
 * 
 * �����ƣ�BaiduFoodAdapter
 * 
 * ��������
 * 
 * �����ˣ�flyou
 * 
 * ����ʱ�䣺2015-5-4 ����5:21:46
 * 
 * �޸ı�ע���ٶ���ʳ������������
 * 
 * �汾��@version v1.0
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
    holder.distance.setText((int) baiduFoods.get(position).getDistance() + "��");
    // �绰��������ͼ����
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
      // ��ͼ��λ����
      Intent intent=new Intent(context, POILocationMapActivity.class);
      intent.putExtra("Latitude", baiduFoods.get((Integer)v.getTag()).getLocation().latitude);
      intent.putExtra("Longitude", baiduFoods.get((Integer)v.getTag()).getLocation().longitude);
      intent.putExtra("name", baiduFoods.get((Integer)v.getTag()).getName());
      context.startActivity(intent);
    }}
  
  class Call implements OnClickListener{

    @Override
    public void onClick(View v) {
      // ����绰����ͼ����
      Uri uri = Uri.parse("tel:" + baiduFoods.get((Integer)v.getTag()).getPhone()) ;  // ���ò�����·��
      Intent it = new Intent() ;
      it.setAction(Intent.ACTION_DIAL) ;  // ����Ҫ������Action
      it.setData(uri) ; // Ҫ���õ�����
      context.startActivity(it) ; // ִ����ת
    }}
 
}
