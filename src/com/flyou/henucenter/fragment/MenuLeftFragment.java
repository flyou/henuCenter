package com.flyou.henucenter.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.flyou.henucenter.R;
import com.flyou.henucenter.adapter.LeftMenuListAdapter;
import com.flyou.henucenter.utils.Constants;

public class MenuLeftFragment extends Fragment {
  private View mView;
  private ListView mCategories;

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    if (mView == null) {
      initView(inflater, container);
    }
    return mView;
  }

  private void initView(LayoutInflater inflater, ViewGroup container) {
    mView = inflater.inflate(R.layout.left_menu, container, false);
    mCategories = (ListView) mView.findViewById(R.id.id_listview_categories);
    LeftMenuListAdapter adapter = new LeftMenuListAdapter(getActivity());
    mCategories.setAdapter(adapter);
  }

  @Override
  public void onActivityCreated(Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);
    mCategories.setOnItemClickListener(new OnItemClickListener() {

      @Override
      public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        //发送左侧item点击事件的广播
        Intent intent = new Intent(Constants.LEFITEM_ACTION);
        intent.addCategory(Intent.CATEGORY_DEFAULT);
        intent.putExtra("Left_position", position);
        getActivity().sendBroadcast(intent);

      }
    });
  }

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
  }

}
