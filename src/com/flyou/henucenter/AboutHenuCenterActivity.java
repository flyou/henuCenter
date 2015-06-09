package com.flyou.henucenter;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;

public class AboutHenuCenterActivity extends Activity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_about_henu_center);

  }
  public void back(View view){
    
    finish();
    overridePendingTransition(R.anim.anim_activity_previous_in, R.anim.anim_activity_previous_out);
  }
}
