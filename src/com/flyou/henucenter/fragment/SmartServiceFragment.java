package com.flyou.henucenter.fragment;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.model.LatLng;
import com.flyou.henucenter.BaiDuActivity;
import com.flyou.henucenter.CETQueryActivity;
import com.flyou.henucenter.HenuImageActivity;
import com.flyou.henucenter.Paper_AirplanesActivity;
import com.flyou.henucenter.ZhariYanActivity;
import com.flyou.henucenter.HomeActivity;
import com.flyou.henucenter.R;
import com.flyou.henucenter.SmartChat;
import com.flyou.henucenter.utils.Constants;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingFragmentActivity;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;

/**
 * ============================================================ ��Ŀ���ƣ�Fragement01
 * 
 * �����ƣ�FragMent01
 * 
 * ��������
 * 
 * �����ˣ�flyou
 * 
 * ����ʱ�䣺2015-4-6 ����12:51:13
 * 
 * �޸ı�ע��
 * 
 * �汾��@version ============================================================
 */
public class SmartServiceFragment extends Fragment implements OnClickListener {

  public static final String TAG = "FragMent04";
  private TextView henu_good_message;
  private View view;
  private GridView gridView;
  private int imageIds[] = { R.drawable.ic_found_countdown_icon, R.drawable.ic_found_course_icon,
      R.drawable.ic_found_note_icon, R.drawable.ic_found_papers_icon, R.drawable.ic_found_robot_chat_icon,
      R.drawable.ic_found_tools_icon, R.drawable.ic_found_treehole_icon, R.drawable.ic_found_countdown_icon1,
      R.drawable.ic_found_unkown_icon };
  private String names[] = { "���ܱ�", "ͼ˵�Ӵ�", "լ��լ��", "ֽ�ɻ�", "�ǻ���", "С����", "��һ��", "�Ǳʼ�", "δ֪��" };
  private SlidingMenu menu;
  private ImageButton rightMenu;

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    view = inflater.inflate(R.layout.fragment04, null);
    initMenu();
    initTitleBar();
    return view;

  }

  private void initMenu() {
    menu = ((SlidingFragmentActivity) getActivity()).getSlidingMenu();
    menu.setMode(SlidingMenu.LEFT_RIGHT);

    menu.setTouchModeAbove(SlidingMenu.TOUCHMODE_NONE);
  }

  // ��ʼ��titlebar
  private void initTitleBar() {
    rightMenu = ((HomeActivity) getActivity()).getRightMenu();

    rightMenu.setVisibility(View.VISIBLE);

  }

  /*
   * @param savedInstanceState
   */
  @Override
  public void onActivityCreated(Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);
    henu_good_message = (TextView) view.findViewById(R.id.henu_good_message);
    henu_good_message.setOnClickListener(this);
    getHenuMessageFromNet();

    gridView = (GridView) view.findViewById(R.id.smart_service);
    gridView.setAdapter(new MyGridAdapter());
    gridView.setOnItemClickListener(new OnItemClickListener() {

      @Override
      public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        switch (position) {
          case 0:
            Intent baiduIntent = new Intent(getActivity(), BaiDuActivity.class);
            startActivity(baiduIntent);
            break;
          case 1:

            Intent henuImageIntent = new Intent(getActivity(), HenuImageActivity.class);
            startActivity(henuImageIntent);
            break;

          case 2:
            Intent henuHistoryIntent = new Intent(getActivity(), ZhariYanActivity.class);
            startActivity(henuHistoryIntent);
            break;

          case 3:
            Intent pager_ari_plance_Intent = new Intent(getActivity(), Paper_AirplanesActivity.class);
            startActivity(pager_ari_plance_Intent);
            break;

          case 4:
            Intent chatIntent = new Intent(getActivity(), SmartChat.class);
            startActivity(chatIntent);
            break;

          case 5:
            Toast.makeText(getActivity(), "�°汾�Ƴ�", 0).show();
//            Intent CetIntent = new Intent(getActivity(), CETQueryActivity.class);
//            startActivity(CetIntent);
            break;

          case 6:
            Toast.makeText(getActivity(), "�Ʊʼ����ϳ���", 0).show();
            break;

          case 7:
            Toast.makeText(getActivity(), "�����ڴ�", 0).show();
            break;

          default:
            break;
        }
      }

    });
  }

  private void getHenuMessageFromNet() {
    HttpUtils httpUtils = new HttpUtils();
    httpUtils.configHttpCacheSize(0);
    httpUtils.send(HttpMethod.GET, Constants.HENU_GOOD_MESSAGE, new RequestCallBack<String>() {

      @Override
      public void onFailure(HttpException arg0, String arg1) {

        henu_good_message.setText("������ã���ӭʹ�úӴ�����,���µ�У԰��Ѷ�����ٺ����Ѱ�");
      }

      @Override
      public void onSuccess(ResponseInfo<String> responseInfo) {
        String json = responseInfo.result;
        try {
          JSONObject jsonObject = new JSONObject(json);
          String words = (String) jsonObject.get("words");
          henu_good_message.setText(words);
        } catch (JSONException e) {
          e.printStackTrace();
          henu_good_message.setText("������ã���ӭʹ�úӴ�����,���µ�У԰��Ѷ�����ٺ����Ѱ�");
        }

      }
    });

  }

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    // ��ʹ��SDK�����֮ǰ��ʼ��context��Ϣ������ApplicationContext
    // ע��÷���Ҫ��setContentView����֮ǰʵ��

  }

  public class MyGridAdapter extends BaseAdapter {

    @Override
    public int getCount() {
      return imageIds.length;
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
        convertView = View.inflate(getActivity(), R.layout.list_item_smart_service, null);
        holder.name = (TextView) convertView.findViewById(R.id.tv_item_name);
        holder.icon = (ImageView) convertView.findViewById(R.id.iv_item_icon);
        convertView.setTag(holder);
      } else {
        holder = (ViewHolder) convertView.getTag();
      }
      holder.icon.setImageResource(imageIds[position]);
      holder.name.setText(names[position]);
      return convertView;
    }
  }

  private class ViewHolder {

    TextView name;
    ImageView icon;

  }

  @Override
  public void onDestroy() {
    super.onDestroy();
    menu.setTouchModeAbove(SlidingMenu.TOUCHMODE_NONE);
    rightMenu.setVisibility(View.GONE);

  }

  @Override
  public void onClick(View view) {
    switch (view.getId()) {
      case R.id.henu_good_message:
        getHenuMessageFromNet();
        break;

      default:
        break;
    }
  }
}