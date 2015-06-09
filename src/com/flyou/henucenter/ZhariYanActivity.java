package com.flyou.henucenter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import cn.jpush.android.api.m;

import com.flyou.henucenter.domain.ZhaiYan;
import com.flyou.henucenter.ui.XListView;
import com.flyou.henucenter.ui.XListView.IXListViewListener;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.lidroid.xutils.view.annotation.ViewInject;

/**
 * ============================================================ ��Ŀ���ƣ�HenuCenter
 * 
 * �����ƣ�HistoryActivity
 * 
 * ��������
 * 
 * �����ˣ�flyou
 * 
 * ����ʱ�䣺2015-5-31 ����1:08:39
 * 
 * �޸ı�ע��
 * 
 * �汾��@version ============================================================
 */
public class ZhariYanActivity extends Activity {
  @ViewInject(R.id.henu_history_today)
  private XListView mListView;
  
  private List<ZhaiYan> listZhaiYan;

  private MyAdapter adapter;
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_zhaiyan);
    // ע����
    ViewUtils.inject(this);
    listZhaiYan=new ArrayList<ZhaiYan>();
    initListData();
   
  }

  // ��ʼ��ListView����
  private void initListData() {
    HttpUtils httpUtils=new HttpUtils();
    httpUtils.configHttpCacheSize(0);
    for (int i = 0; i < 5; i++) {
      getDataFromNet(httpUtils);
    
  
   
   
    }
  }

  private void initView() {
    adapter = new MyAdapter();
    mListView.setAdapter(adapter);
    mListView.setRefreshTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
    mListView.setPullLoadEnable(true);
    mListView.setPullRefreshEnable(true);
    mListView.setXListViewListener(XListViewAdapter);
  }
  IXListViewListener XListViewAdapter =new IXListViewListener(){

    @Override
    public void onRefresh() {
        
      mListView.stopRefresh();
    }

    @Override
    public void onLoadMore() {
      initListData(); 
     mListView.stopLoadMore();
    }};
  private void getDataFromNet(HttpUtils httpUtils) {
 
    httpUtils.send(HttpMethod.GET, "http://zyfree.acman.cn/utf8", new RequestCallBack<String>() {

      @Override
      public void onFailure(HttpException e, String msg) {
      Toast.makeText(ZhariYanActivity.this, "��ȡ����ʧ��"+msg, 0).show();
      }

      @Override
      public void onSuccess(ResponseInfo<String> responseInfo) {
      try {
        JSONObject jsonObject=new JSONObject(responseInfo.result);
        String text = (String) jsonObject.get("zhaiyan");
        String type = (String) jsonObject.get("catname");
        String from = (String) jsonObject.get("source");
        String date = (String) jsonObject.get("date");
       
        ZhaiYan zhaiYan=new ZhaiYan(text, type, from, date);
        
        
        listZhaiYan.add(0, zhaiYan);
        if (adapter!=null) {
          adapter.notifyDataSetChanged();
        }
        else {
          initView();
        }
        
      } catch (JSONException e) {
        e.printStackTrace();
      }
      }
    });
  }

  
  class MyAdapter extends BaseAdapter{

    @Override
    public int getCount() {
      return listZhaiYan.size();
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
      ZhaiYan zhaiYan = listZhaiYan.get(position);
     ViewHolder viewHolder;
      if (convertView==null) {
        viewHolder=new ViewHolder();
          convertView=View.inflate(ZhariYanActivity.this, R.layout.henu_zhaiyan_item, null);
            viewHolder.from=(TextView) convertView.findViewById(R.id.from);
            viewHolder.text=(TextView) convertView.findViewById(R.id.text);
            viewHolder.date=(TextView) convertView.findViewById(R.id.date);
            viewHolder.type=(TextView) convertView.findViewById(R.id.type);
            convertView.setTag(viewHolder);
      }
      else {
        viewHolder=(ViewHolder) convertView.getTag();
      }
      
      viewHolder.date.setText("ʱ��:"+zhaiYan.getDate());
      viewHolder.from.setText("��Դ:"+zhaiYan.getFrom());
      viewHolder.text.setText("��"+zhaiYan.getText()+"��");
      viewHolder.type.setText("����:"+zhaiYan.getType());
      return convertView;
    }}
  class ViewHolder{
    TextView text;
    TextView type;
    TextView from ;
    TextView date ;
    
  }
  // ����˵��¼��ļ���
  public void back(View view) {
    finish();

  }

  public void menu(View view) {

  }
}
