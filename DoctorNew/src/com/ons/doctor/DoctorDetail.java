package com.ons.doctor;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.widget.TextView;

public class DoctorDetail extends Activity{
	TextView tv_name,tv_desc,tv_speciality,tv_place,tv_calldoctor;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.doctor_detail);
		Doctor doctor = (Doctor) getIntent().getSerializableExtra("data");
		tv_name=(TextView)findViewById(R.id.tv_name);
		tv_name.setText(doctor.getName());
		tv_desc=(TextView)findViewById(R.id.tv_desc);
		tv_desc.setText(doctor.getDesc());
		tv_speciality=(TextView)findViewById(R.id.tv_speciality);
		tv_speciality.setText(doctor.getSpeciality());
		tv_place=(TextView)findViewById(R.id.tv_place);
		tv_place.setText(doctor.getPlace());
		tv_calldoctor=(TextView)findViewById(R.id.tv_calldoctor);
		
	}

}
