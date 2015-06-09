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
 * ============================================================ ��Ŀ���ƣ�HenuCenter
 * 
 * �����ƣ�LeftMenuListAdapter
 * 
 * ��������
 * 
 * �����ˣ�flyou
 * 
 * ����ʱ�䣺2015-4-25 ����4:07:06
 * 
 * �޸ı�ע�� ���໬�˵���adapter
 * 
 * �汾��@version ============================================================
 */
public class LeftMenuListAdapter extends BaseAdapter {
  public static final String TAG = "LeftMenuListAdapter";

  public static List<String> DepartmentsName = Arrays.asList("������ѧѧԺ", "����ѧԺ", "��ѧԺ", "��ѧԺ", "ҩѧԺ", "ҽѧԺ", "����ѧԺ",
      "����ѧԺ", "������滮ѧԺ", "�����ѧԺ", "��ʷ�Ļ�ѧԺ", "ŷ�ǹ���ѧԺ", "������ѧѧԺ", "��ѧ�빫������ѧԺ");
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
