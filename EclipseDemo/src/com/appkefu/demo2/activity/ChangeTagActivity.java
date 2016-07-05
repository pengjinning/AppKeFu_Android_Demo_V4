package com.appkefu.demo2.activity;

import com.appkefu.demo2.R;
import com.appkefu.lib.interfaces.KFAPIs;

import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class ChangeTagActivity extends Activity implements OnClickListener{

	private Button	 backButton;
	private Button   changeBtn;
	private TextView profileFieldTextView;
	
	private String  profileField;
	private String 	profileValue;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_change_tag);
		
		backButton = (Button) findViewById(R.id.change_profile_reback_btn);
		backButton.setOnClickListener(this);
		
		changeBtn = (Button) findViewById(R.id.change_profile_btn);
		changeBtn.setOnClickListener(this);
		
		profileFieldTextView = (TextView) findViewById(R.id.change_profile_user_edit);
		
		profileField = getIntent().getStringExtra("profileField");
		profileValue = getIntent().getStringExtra("value");
		
		profileFieldTextView.setText(profileValue);
	}

	public void changeProfile()
	{		
		profileValue = profileFieldTextView.getText().toString();
		if(profileValue.length() <= 0)
		{
			Toast.makeText(this, "should not be null", Toast.LENGTH_SHORT).show();
			return;
		}	
		
		changeBtn.setEnabled(false);


		if(profileField.equals("NICKNAME"))
		{
			KFAPIs.setTagNickname(profileValue, this);
		}
		else if(profileField.equals("SEX"))
		{
			KFAPIs.setTagSex(profileValue, this);
		}
		else if(profileField.equals("LANGUAGE"))
		{
			KFAPIs.setTagLanguage(profileValue, this);
		}
		else if(profileField.equals("CITY"))
		{
			KFAPIs.setTagCity(profileValue, this);
		}
		else if(profileField.equals("PROVINCE"))
		{
			KFAPIs.setTagProvince(profileValue, this);
		}
		else if(profileField.equals("COUNTRY"))
		{
			KFAPIs.setTagCountry(profileValue, this);
		}
		else if(profileField.equals("OTHER"))
		{
			KFAPIs.setTagOther(profileValue, this);
		}

		finish();
	}

	@Override
	public void onClick(View view) {
		// TODO Auto-generated method stub
		switch(view.getId()) {
		case R.id.change_profile_reback_btn:
			finish();
			break;
		case R.id.change_profile_btn:
			changeProfile();
			break;
		default:
			break;
		}	
	}
	

}
