package com.flyou.henucenter.adapter;

import java.util.List;
import java.util.Random;

import com.flyou.henucenter.R;
import com.flyou.henucenter.domain.News;

import android.R.raw;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**  ============================================================  
 * 项目名称：HenuCenter   
 * 
 * 类名称：homeFragmentXlistviewAdapter   
 * 
 * 类描述：   
 * 
 * 创建人：flyou  
 * 
 * 创建时间：2015-4-20 上午12:37:33  
 *  
 * 修改备注：   
 * 
 * 版本：@version    
 *   ============================================================ 
 */
public class NewsXlistviewAdapter extends BaseAdapter {
  public static final String TAG = "homeFragmentXlistviewAdapter";
  private int imagesID[]={R.drawable.f_geili,R.drawable.f_jiong,R.drawable.f_meng,R.drawable.f_shenma,R.drawable.f_v5,R.drawable.f_xi};
private List<News>news;
private Context context;
  public NewsXlistviewAdapter(Context context ,List<News> news) {
this.news=news;
this.context=context;
  }

  @Override
  public int getCount() {
    return news.size();
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
  public View getView(int position, View convertView, ViewGroup parent) {
    viewHolder holder;
    if (convertView==null) {
      holder=new viewHolder();
      convertView=View.inflate(context, R.layout.home_fragmentlistview , null);
      holder.image=(ImageView) convertView.findViewById(R.id.image);
      holder.title=(TextView) convertView.findViewById(R.id.title);
      convertView.setTag(holder);
    }else {
      holder=(viewHolder) convertView.getTag();
    }
    int random = new Random().nextInt(6);
    holder.image.setImageResource(imagesID[position%6]);
    holder.title.setText(news.get(position).getTitle());
    
    return convertView;
  }
private class viewHolder{
  TextView title;
  ImageView image;
  
}
}
