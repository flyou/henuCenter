package com.flyou.henucenter.adapter;

import java.io.File;
import java.util.List;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Environment;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.flyou.henucenter.R;
import com.flyou.henucenter.domain.HenuImages;
import com.flyou.henucenter.utils.Constants;
import com.lidroid.xutils.BitmapUtils;

/**
 * ============================================================ 项目名称：HenuCenter
 * 
 * 类名称：HenuImageAdapter
 * 
 * 类描述：
 * 
 * 创建人：flyou
 * 
 * 创建时间：2015-5-24 上午12:32:28
 * 
 * 修改备注：
 * 
 * 版本：@version ============================================================
 */
public class HenuImageAdapter extends BaseAdapter {
  public static final String TAG = "HenuImageAdapter";
  private List<HenuImages> imageList;
  private Context context;
  private BitmapUtils bitmapUtils;

  public HenuImageAdapter(List<HenuImages> imageList, Context context) {
    this.imageList = imageList;
    this.context = context;
    // 获得存储路径 默认为内部存储
    File file = new File(Environment.getExternalStorageDirectory() + File.separator + "henuCenterCache");
    if (!file.exists()) {
      file.mkdir();
    }
    bitmapUtils = new BitmapUtils(context, file.getAbsolutePath(), 0.15f, 80 * 1024 * 1024);
    // 正在加载的图
    bitmapUtils.configDefaultLoadingImage(R.drawable.henu_iamge_loading);
    //加载失败的图
    bitmapUtils.configDefaultLoadFailedImage(R.drawable.henu_iamge__fauilt);
    bitmapUtils.configDefaultBitmapConfig(Bitmap.Config.RGB_565);
  }

  @Override
  public int getCount() {
    return imageList.size();
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
    HenuImages  imagesdata=imageList.get(position);
    ViewHolder holder;
    if (convertView==null) {
      holder=new ViewHolder();
      convertView=View.inflate(context, R.layout.henu_image_itme, null);
      holder.image=(ImageView) convertView.findViewById(R.id.henu_image);
      holder.imageTitle=(TextView) convertView.findViewById(R.id.henu_image_title);
      holder.imageDesc=(TextView) convertView.findViewById(R.id.henu_image_desc);
      convertView.setTag(holder);
    }
    else {
      holder=(ViewHolder) convertView.getTag();
      }
   bitmapUtils.display(holder.image, Constants.HENUIMAGESURL+imagesdata.url);
   holder.imageTitle.setText(imagesdata.imageTitle);
   holder.imageDesc.setText(imagesdata.imageDesc);
    return convertView;
  }

  class ViewHolder {
    ImageView image;
    TextView imageTitle;
    TextView imageDesc;

  }
}
