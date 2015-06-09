package com.flyou.henucenter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.R.integer;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.flyou.henucenter.domain.AirPlances;
import com.flyou.henucenter.ui.XListView;
import com.flyou.henucenter.ui.XListView.IXListViewListener;
import com.flyou.henucenter.utils.Constants;
import com.flyou.henucenter.utils.ShareUtils;
import com.flyou.utils.SPUtils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.lidroid.xutils.view.annotation.ViewInject;

public class Paper_AirplanesActivity extends Activity {
  private AirPlances plances;
  private static final String TAG = "Paper_AirplanesActivity";
  private List<AirPlances> dataList;
  @ViewInject(R.id.henu_paper_ari_plance)
  private XListView listView;
  private MyAdapter myAdapter;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState); 
   
    setContentView(R.layout.activity_paper__airplanes);
    ViewUtils.inject(this);
    
    
    listView.setPullLoadEnable(true);
    listView.setPullRefreshEnable(true);
    
    String oldData = (String) SPUtils.get(Paper_AirplanesActivity.this, Constants.HENU_GET_PAGER_ARIPLANCE, "");
    if (TextUtils.isEmpty(oldData)) {
      operatorData(oldData);
    }
    getDataFromNet();

  }

  private void getDataFromNet() {

    HttpUtils httpUtils = new HttpUtils();
    httpUtils.send(HttpMethod.GET, Constants.HENU_GET_PAGER_ARIPLANCE, new RequestCallBack<String>() {

      @Override
      public void onFailure(HttpException error, String msg) {

        Toast.makeText(Paper_AirplanesActivity.this, "获取服务器数据异常" + msg, 0).show();
      }

      @Override
      public void onSuccess(ResponseInfo<String> responseInfo) {
        // 保存数据到本地
        SPUtils.put(Paper_AirplanesActivity.this, Constants.HENU_GET_PAGER_ARIPLANCE, responseInfo.result);
        operatorData(responseInfo.result);
        // 初始化视图，显示数据
        initView();
        
          listView.stopRefresh();
        
      }

    });

  }

  public void operatorData(String result) {

    Gson gson = new Gson();
    dataList = gson.fromJson(result, new TypeToken<List<AirPlances>>() {
    }.getType());

  }

  protected void initView() {
    if (myAdapter==null) {
      myAdapter = new MyAdapter();
     
      listView.setAdapter(myAdapter);
      
    }
    myAdapter.notifyDataSetChanged();
    listView.setRefreshTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));

    listView.setOnItemClickListener(new OnItemClickListener() {

      @Override
      public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
     

        Intent commentIntent = new Intent(Paper_AirplanesActivity.this, PagerAriPlanceCommentActivity.class);
        if (position==14) {
          return;
        }
        commentIntent.putExtra("id", dataList.get(position-1).id);
        commentIntent.putExtra("content", dataList.get(position-1).content);
        commentIntent.putExtra("from", dataList.get(position-1).userName);
        startActivity(commentIntent);
        overridePendingTransition(R.anim.anim_activity_next_in, R.anim.anim_activity_next_out);

      }
    });

  }

  class   MyAdapter extends  BaseAdapter {

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
      plances = dataList.get(position);
      ViewHolder viewHolder = null;
      if (convertView == null) {
        viewHolder = new ViewHolder();
        convertView = View.inflate(Paper_AirplanesActivity.this, R.layout.henu_pager_air_plance_item, null);
        viewHolder.comment = (ImageButton) convertView.findViewById(R.id.comment);
        viewHolder.share = (ImageButton) convertView.findViewById(R.id.share);
        viewHolder.like = (ImageButton) convertView.findViewById(R.id.like);

        viewHolder.text = (TextView) convertView.findViewById(R.id.text);
        viewHolder.from = (TextView) convertView.findViewById(R.id.from);
        viewHolder.date = (TextView) convertView.findViewById(R.id.date);
        convertView.setTag(viewHolder);
      } else {
        viewHolder = (ViewHolder) convertView.getTag();
      }
      viewHolder.comment.setTag(position);
      viewHolder.share.setTag(position);
      viewHolder.like.setTag(position);
      viewHolder.comment.setOnClickListener(new CommentListenter());
      viewHolder.share.setOnClickListener(new ShareListenter());
      viewHolder.like.setOnClickListener(new LikeListenter());

      viewHolder.text.setText(plances.content);
      viewHolder.from.setText("来自:" + plances.userName);
      viewHolder.date.setText("时间:" + plances.dateTime);
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
      return dataList.size();
    }
  };

  class ViewHolder {

    TextView text;
    TextView from;
    TextView date;
    ImageButton comment;
    ImageButton share;
    ImageButton like;

  }

  // 评论按钮的监听
  class CommentListenter implements OnClickListener {

    @Override
    public void onClick(View v) {
      Intent commentIntent = new Intent(Paper_AirplanesActivity.this, PagerAriPlanceCommentActivity.class);
      commentIntent.putExtra("id", dataList.get((Integer) v.getTag()).id);
      commentIntent.putExtra("content", dataList.get((Integer) v.getTag()).content);
      commentIntent.putExtra("from", dataList.get((Integer) v.getTag()).userName);
      startActivity(commentIntent);
      overridePendingTransition(R.anim.anim_activity_next_in, R.anim.anim_activity_next_out);

    }
  }

  // 分享按钮的监听
  class ShareListenter implements OnClickListener {

    @Override
    public void onClick(View v) {
      // 调取分享api

      ShareUtils share = new ShareUtils();
      View view = getWindow().getDecorView();
      share.showShare(Paper_AirplanesActivity.this, "我使用[河大在线]向你分享了一个纸飞机:" + "《"
          + dataList.get((Integer) v.getTag()).content + "》", Constants.DOWNLOAD_URl, view);

    }
  }

  // supper按钮的监听
  class LikeListenter implements OnClickListener {

    @Override
    public void onClick(View v) {
      // 1.判断显示的状态
      if (v.isEnabled()) {

        Toast.makeText(Paper_AirplanesActivity.this, "我知道了", 0).show();
      } else {

        Toast.makeText(Paper_AirplanesActivity.this, "点赞", 0).show();
      }
      // 2. 把自己的操作状态保存到本地

      // 3.把 操作提交到服务器

    }
  }

  // 标题菜单事件的监听
  public void back(View view) {
    finish();
    overridePendingTransition(R.anim.anim_activity_previous_in, R.anim.anim_activity_previous_out);
  }

  public void add(View view) {
    String phone = (String) SPUtils.get(Paper_AirplanesActivity.this, "UserName", "");
    if (!TextUtils.isEmpty(phone)) {
      // 添加纸飞机
      Intent intent = new Intent(Paper_AirplanesActivity.this, AddPagerAriPlanceActivity.class);
      startActivity(intent);
      overridePendingTransition(R.anim.anim_activity_next_in, R.anim.anim_activity_next_out);
    } else {
      Toast.makeText(Paper_AirplanesActivity.this, "请先登录", 0).show();
    }

  }

  public class MyXlistViewAdapter implements IXListViewListener {

    @Override
    public void onRefresh() {
      getDataFromNet();
    }

    @Override
    public void onLoadMore() {
    }}
  @Override
  protected void onRestart() {
    super.onRestart();
    getDataFromNet();
    Log.i(TAG, "onRestart");
  }

}
