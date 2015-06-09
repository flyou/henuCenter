package com.flyou.henucenter.adapter;

import java.util.Arrays;
import java.util.List;

import com.flyou.henucenter.R;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * ============================================================ 项目名称：HenuCenter
 * 
 * 类名称：LeftMenuListAdapter
 * 
 * 类描述：
 * 
 * 创建人：flyou
 * 
 * 创建时间：2015-4-25 下午4:07:06
 * 
 * 修改备注： 左侧侧滑菜单的adapter
 * 
 * 版本：@version ============================================================
 */
public class LeftMenuListAdapter extends BaseAdapter {
  public static final String TAG = "LeftMenuListAdapter";

  public static List<String> DepartmentsName = Arrays.asList("教育科学学院", "民生学院", "法学院", "文学院", "药学院", "医学院", "外语学院",
      "体育学院", "环境与规划学院", "计算机学院", "历史文化学院", "欧亚国际学院", "生命科学学院", "哲学与公共管理学院");
  private int ImagesIDs[] = { R.drawable.a0, R.drawable.a1, R.drawable.a2, R.drawable.a4,
      R.drawable.a5, R.drawable.a6, R.drawable.a7, R.drawable.a8, R.drawable.a9, R.drawable.a10, R.drawable.a11,
      R.drawable.a12, R.drawable.a13, R.drawable.a14};
  private Context context;

  public LeftMenuListAdapter(Context context) {
    this.context = context;
  }

  @Override
  public int getCount() {
    return DepartmentsName.size();
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
    ViewHolder holder;
    if (convertView == null) {
      holder = new ViewHolder();
      convertView = View.inflate(context, R.layout.list_left_menu_departments, null);
      holder.departmentsIcon = (ImageView) convertView.findViewById(R.id.Departments_icon);
      holder.departmentsName = (TextView) convertView.findViewById(R.id.Departments_name);
      convertView.setTag(holder);
    } else {
      holder = (ViewHolder) convertView.getTag();
    }
    holder.departmentsName.setText(DepartmentsName.get(position));
    holder.departmentsIcon.setImageResource(ImagesIDs[position]);
    return convertView;
  }

  public class ViewHolder {
    ImageView departmentsIcon;
    TextView departmentsName;

  }
}
