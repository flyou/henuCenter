package com.flyou.henucenter.ui;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.flyou.henucenter.R;

/**
 * �Զ���dialog
 * 
 * @author Ϧ��
 * 
 */
public class MyDialog extends Dialog {

	Context context;
	View view;
	View.OnClickListener onClickListener;
	String title;

	public MyDialog(Context context, String title, View view,
			View.OnClickListener onClickListener) {
		super(context, R.style.MyDialog);
		this.context = context;
		this.title = title;
		this.view = view;
		this.onClickListener = onClickListener;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.henu_dialog);
		// ʵ�����ؼ�
		Button btn_confir = (Button) findViewById(R.id.btn_confir);
		Button btn_cancel = (Button) findViewById(R.id.btn_cancel);
		TextView textv_title = (TextView) findViewById(R.id.textv_title);
		// ���ñ���
		textv_title.setText(title);
		// ���Ҫ���õĲ���
		LinearLayout mainLayout = (LinearLayout) findViewById(R.id.relayout_addview);
		mainLayout.addView(view);
		// ��ȷ�ϼ����ü���
		btn_confir.setOnClickListener(onClickListener);
		// ���ȡ���ر�dialog
		btn_cancel.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				MyDialog.this.dismiss();
			}
		});
	}
}