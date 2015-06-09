package com.flyou.henucenter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.app.Activity;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.os.Handler;
import android.text.ClipboardManager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import cn.jpush.android.api.JPushInterface;

import com.flyou.henucenter.domain.Data;
import com.flyou.henucenter.domain.ShowData;
import com.flyou.henucenter.utils.JsonUtils;
import com.flyou.utils.NetUtils;
import com.flyou.utils.SPUtils;

public class SmartChat extends Activity implements OnItemLongClickListener {
  private ListView listview;
  private EditText et_input;
  private Button bt_send;
  private List<ShowData> datas;
  private MyAdapter adapter;
  private Handler mHandler = new Handler();
  private SoundPool soundPool;
  private int currentPosition;
 
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.smart_chat_layout);
    soundPool = new SoundPool(21, AudioManager.STREAM_SYSTEM, 10);
    soundPool.load(SmartChat.this, R.raw.notificationsound, 1);
    initstatus();
    initView();
    initData();

  }

  private void initstatus() {
    if (!NetUtils.isConnected(getApplicationContext())) {
      NetUtils.openSetting(SmartChat.this);
    }
  }

  private void initData() {
    // 为listview设置adapter

    // 设置数据集
    datas = new ArrayList<ShowData>();
    datas.add(new ShowData(new Date(), "孩子你过来，我们谈谈人生。聊一聊，新功能上线了，查天气，查百科，讲故事，说笑话，数字计算统统帮你搞定，速速来约吧!", true));
    adapter = new MyAdapter();
    listview.setAdapter(adapter);
    listview.setOnItemLongClickListener(this);
  }

  private void initView() {
    listview = (ListView) findViewById(R.id.listview_msgs);
    et_input = (EditText) findViewById(R.id.et_input);
    bt_send = (Button) findViewById(R.id.bt_send);
    bt_send.setOnClickListener(new Send());

  }

  public class MyAdapter extends BaseAdapter  {

    private String userName;

    @Override
    public int getCount() {
      return datas.size();
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
    public int getItemViewType(int position) {
      ShowData chatMessage = datas.get(position);
      if (chatMessage.getIsReceive()) {
        return 0;
      }
      return 1;
    }

    @Override
    public int getViewTypeCount() {
      return 2;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
     currentPosition=position;

      ViewHolder holder;
      if (convertView == null) {
        if (datas.get(position).getIsReceive()) {
          convertView = LayoutInflater.from(getApplicationContext()).inflate(R.layout.receive_msg, null);
          holder = new ViewHolder();
          holder.data = (TextView) convertView.findViewById(R.id.receive_date);
          holder.msg = (TextView) convertView.findViewById(R.id.receive_msg);
          holder.user_name = (TextView) convertView.findViewById(R.id.user_name);
          userName = "henuer";
        } else {
          convertView = LayoutInflater.from(getApplicationContext()).inflate(R.layout.to_msg, null);
          holder = new ViewHolder();
          holder.data = (TextView) convertView.findViewById(R.id.to_date);
          holder.msg = (TextView) convertView.findViewById(R.id.to_msg);
          holder.user_name = (TextView) convertView.findViewById(R.id.user_name);
          userName = (String) SPUtils.get(SmartChat.this, "UserName", "");
        }
        convertView.setTag(holder);
      } else {
        holder = (ViewHolder) convertView.getTag();
      }
      holder.data.setText(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(datas.get(position).getDate()));
      holder.msg.setText(datas.get(position).getMsg());
  
    if (convertView  instanceof LinearLayout&&!TextUtils.isEmpty(userName)) {
   
        holder.user_name.setText(userName);
     
    } 
      
      return convertView;
    }

    private class ViewHolder {
      TextView user_name;
      private TextView data;
      private TextView msg;

    }

   
 
  }

  // onclick事件处理
  public class Send implements OnClickListener {

    @Override
    public void onClick(View v) {

      final String sendMsg = et_input.getText().toString().trim();
      if (!TextUtils.isEmpty(sendMsg)) {
        ShowData sendData = new ShowData(new Date(), sendMsg, false);
        datas.add(sendData);
        adapter.notifyDataSetChanged();
        et_input.setText("");
        new Thread() {
          public void run() {
            Data msg2Domain = JsonUtils.msg2Domain(sendMsg);
            if (msg2Domain == null) {
              mHandler.post(runToast);
              ShowData receiveData = new ShowData(new Date(), "网络连接异常哦亲，请检查网络后重试", true);
              datas.add(receiveData);
              mHandler.post(runnableUi);
              return;
            }
            String receiveMsg = msg2Domain.getText();

            ShowData receiveData = new ShowData(new Date(), receiveMsg, true);

            datas.add(receiveData);

            soundPool.play(1, 1.0f, 1.0f, 0, 0, 1f);
            mHandler.post(runnableUi);

          };
        }.start();

      } else {
        Toast.makeText(SmartChat.this, "输入的内容不能为空哦", 0).show();
        et_input.setText("");
        return;
      }
    }
  }

  Runnable runnableUi = new Runnable() {
    @Override
    public void run() {
      // 更新界面
      adapter.notifyDataSetChanged();
    }

  };
  // 通过hander发送post命令传递给外部线程更新UI
  Runnable runToast = new Runnable() {
    @Override
    public void run() {
      // 土司提示
      Toast.makeText(SmartChat.this, "网络连接异常，请检查你的网络", 0).show();
    }

  };

 
  
  
  
  
  
  
  
  
  
//  标题按钮事件监听
  public void back(View view){
    finish();
    
  }
  public void menu(View view){
    
    
    
  }
  
  @Override
  protected void onResume() {
    super.onResume();
    JPushInterface.onResume(this);
  }

  @Override
  protected void onPause() {
    super.onPause();
    JPushInterface.onPause(this);
  }

  @Override
  public boolean onItemLongClick(AdapterView<?> AdapterView, View view, int position, long id) {
    ClipboardManager  clipboardManager = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
    clipboardManager.setText(datas.get(position).getMsg());
    Toast.makeText(SmartChat.this, "文字已经复制到剪贴板", 0).show();
    return false;
  }
}
