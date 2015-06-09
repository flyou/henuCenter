package com.flyou.henucenter;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.flyou.henucenter.domain.AirCommnet;
import com.flyou.henucenter.utils.Constants;
import com.flyou.henucenter.utils.ShareUtils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.lidroid.xutils.view.annotation.ViewInject;

public class PagerAriPlanceCommentActivity extends Activity {
  @ViewInject(R.id.tv_titlebar_desc)
  private TextView titleDesc;
  @ViewInject(R.id.text)
  private TextView ariContent;
  @ViewInject(R.id.from)
  private TextView ariFrom;
  @ViewInject(R.id.henu_pager_ariplance_comment)
  private ListView commentsList;
  @ViewInject(R.id.bt_send)
  private Button sendComment;
  @ViewInject(R.id.et_input)
  private EditText commentText;
  private int id;
  private List<AirCommnet> dataList;
  private Vibrator vibrator;
private boolean  isAdapterFrist=true;
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_pager_ari_plance_comment);
    ViewUtils.inject(this);
    vibrator=(Vibrator) getSystemService(VIBRATOR_SERVICE);
    initcommentViews();
    initAriPlance();
    initListViewDate();

  }

  private void initcommentViews() {
    sendComment.setVisibility(View.INVISIBLE);
    sendComment.setOnClickListener(new View.OnClickListener() {

      @Override
      public void onClick(View v) {
        String comment = commentText.getText().toString().trim();
        if (!comment.isEmpty()) {
          try {
            comment=URLEncoder.encode(comment, "UTF-8");
          } catch (UnsupportedEncodingException e) {
           e.printStackTrace();
          }
          HttpUtils httpUtils = new HttpUtils();
          
          httpUtils.send(HttpMethod.GET, Constants.HENU_ADD_PAGER_ARIPLANCE_COMMENTS+"?airPlancesId="+id+"&comment="+comment, new RequestCallBack<String>() {

            @Override
            public void onFailure(HttpException error, String msg) {
              Toast.makeText(PagerAriPlanceCommentActivity.this, "发表评论失败" + msg, 0).show();
            }

            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
              if ("1".equals(responseInfo.result.toString().trim())) {
                Toast.makeText(PagerAriPlanceCommentActivity.this, "评论成功", 0).show();
                initListViewDate();
                commentText.setText("");
              }
              else {
                Toast.makeText(PagerAriPlanceCommentActivity.this, "评论失败", 0).show();
              }
            }
          });
        } else {
          Toast.makeText(PagerAriPlanceCommentActivity.this, "评论内容不能为空哦", 0).show();
          // 晃动动画
          Animation shake = AnimationUtils.loadAnimation(PagerAriPlanceCommentActivity.this, R.anim.shake);
          commentText.startAnimation(shake);
          // 震动服务
          // 震动两秒
          vibrator.vibrate(2000);
          // 定义震动频率 { 间隔，震动时间，间隔，震动时间}
          long pattern[] = { 200, 300, 300, 500, 200, 300 };
          vibrator.vibrate(pattern, -1);

        }
      }
    });
  }

  private void initListViewDate() {

    HttpUtils httpUtils = new HttpUtils();
    httpUtils.send(HttpMethod.GET, Constants.HENU_GET_PAGER_ARIPLANCE_COMMENTS + "?airPlancesId=" + id,
        new RequestCallBack<String>() {

          @Override
          public void onFailure(HttpException error, String msg) {

            Toast.makeText(PagerAriPlanceCommentActivity.this, "获得评论列表失败" + msg, 0).show();
          }

          @Override
          public void onSuccess(ResponseInfo<String> responseInfo) {
            operData(responseInfo.result);
          }
        });
  }

  protected void operData(String result) {
    Gson gson = new Gson();
    dataList = gson.fromJson(result, new TypeToken<List<AirCommnet>>() {
    }.getType());

    initListView();

  }

  private void initListView() {
    if(isAdapterFrist){
      
      commentsList.setAdapter(myAdapter);
      
      sendComment.setVisibility(View.VISIBLE);
    }
    else {
      myAdapter.notifyDataSetChanged();
    }
  }

  private void initAriPlance() {
    Intent intent = getIntent();

    id = intent.getIntExtra("id", 1);
    content = intent.getStringExtra("content");
    String from = intent.getStringExtra("from");
    ariContent.setText(content);
    ariFrom.setText("来自:" + from);

  }

  BaseAdapter myAdapter = new BaseAdapter() {

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
      ViewHolder viewHolder;
      if (convertView == null) {
        viewHolder = new ViewHolder();
        convertView = View.inflate(PagerAriPlanceCommentActivity.this, R.layout.henu_ari_plance_comment_item, null);
        viewHolder.date = (TextView) convertView.findViewById(R.id.date);
        viewHolder.ariComment = (TextView) convertView.findViewById(R.id.ariComment);

        convertView.setTag(viewHolder);
      } else {
        viewHolder = (ViewHolder) convertView.getTag();

      }
      viewHolder.date.setText(dataList.get(position).dataTime.toString().trim());
      viewHolder.ariComment.setText(dataList.get(position).comment.toString().trim());
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
  private String content;

  class ViewHolder {
    TextView date;
    TextView ariComment;

  }

  public void back(View view) {
    finish();
    overridePendingTransition(R.anim.anim_activity_previous_in, R.anim.anim_activity_previous_out);

  }

  public void share(View v) {

    ShareUtils share = new ShareUtils();
    View view = getWindow().getDecorView();
    share.showShare(PagerAriPlanceCommentActivity.this, "我使用[河大在线]向你分享了一个纸飞机:" + "《" + content + "》,下载地址",
        Constants.DOWNLOAD_URl, view);
    Toast.makeText(PagerAriPlanceCommentActivity.this, "调取分享api", 0).show();

  }
}
