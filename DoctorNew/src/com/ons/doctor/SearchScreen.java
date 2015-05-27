package com.ons.doctor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SearchScreen extends Activity implements OnClickListener{
	
	EditText edt_sarch,edt_name,edt_insurance,edt_especial;
	TextView tv_search;
	String zip="";
	String name="";
	String insurance="";
	String speciality="";
	MyDatabase myDatabase=new MyDatabase(this);
	ArrayList<Doctor> arrayList=new ArrayList<Doctor>();
	Doctor doctor;
	String location="";
	String insurancename="";
	HashSet<String> hashSet=new HashSet<String>();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.search);
		
		edt_sarch=(EditText)findViewById(R.id.edt_sarch);
//		edt_sarch.setText("11501");
		edt_name=(EditText)findViewById(R.id.edt_name);
//		edt_name.setText("Derek");
		edt_insurance=(EditText)findViewById(R.id.edt_insurance);
//		edt_insurance.setText("CIGNA");
		edt_especial=(EditText)findViewById(R.id.edt_especial);
//		edt_especial.setText("Specialty1");
		tv_search=(TextView)findViewById(R.id.tv_search);
		tv_search.setOnClickListener(this);
	}

	@Override
	public void onClick(View arg0) {
		zip=edt_sarch.getText().toString();
		name=edt_name.getText().toString();
		insurance=edt_insurance.getText().toString();
		speciality=edt_especial.getText().toString();
		if(zip.equals("")&&name.equals("")&&insurance.equals("")&&speciality.equals(""))
		{
			Toast.makeText(getApplicationContext(), "Please enter data",Toast.LENGTH_SHORT).show();
		}
		else
		{
			myDatabase.open();
			Cursor cr=myDatabase.getDoctors();
			int a=cr.getCount();
			if(cr.moveToFirst())
			{
			do{
				
				if(!zip.equals(""))
				{
					String location_id=myDatabase.getLocationidByzip(zip);
					String locationIds[]=cr.getString(2).split(",");
					for(int i=0;i<locationIds.length;i++)
					{
						if(location_id.equals(locationIds[i]))
						 {
							 hashSet.add(cr.getString(0));
							 location=myDatabase.getLocationById(location_id);
						 }
					}
				}
				if(!name.equals(""))
				{
					if(cr.getString(14).equals("name")||cr.getString(16).equals("name"))
					{
						 hashSet.add(cr.getString(0));
					}
				}
				if(!insurance.equals(""))
				{
					String insurance_id=myDatabase.getInsuranceidByname(insurance);
					String insuranceIds[]=cr.getString(6).split(",");
					for(int i=0;i<insuranceIds.length;i++)
					{
						if(insurance_id.equals(insuranceIds[i]))
						 {
							 hashSet.add(cr.getString(0));
							 insurancename=insurance;
						 }
					}
				}
				if(!speciality.equals(""))
				{
					String speciality_id=myDatabase.getSpecilaityidByname(speciality);
					if(speciality_id==cr.getString(3))
					{
						 hashSet.add(cr.getString(0));
					}
				}
				
			}while(cr.moveToNext());
			myDatabase.close();
			}
			
			Iterator iter = hashSet.iterator();
			arrayList.clear();
			 myDatabase.open();
			while (iter.hasNext()) {
				Cursor cr1=myDatabase.getDoctorbyId(iter.next().toString());
				cr1.moveToNext();
				doctor=new Doctor();
				doctor.setId(cr1.getString(0));
				doctor.setName(cr1.getString(14)+" "+cr1.getString(16));
				doctor.setDesc("khdhdkfh dfjgfdhg gdfhgfd gdfjhgfhdgj gdhg dfjhghjdgytsdygj shgsjgdjhgdsjgjhgsd sjghhjgsdgh dsgdjhgsjdg sjdg");
				doctor.setSpeciality(myDatabase.getSpecilaityById(cr1.getString(3)));
				if(location.equals(""))
					doctor.setPlace(myDatabase.getLocationById(cr1.getString(2).split(",")[0]));
				else
					doctor.setPlace(location);
				doctor.setNumber(myDatabase.getphoneByLocationId(cr1.getString(2).split(",")[0]));
				doctor.setDegree(cr1.getString(17));
				doctor.setImage(cr1.getString(19));
				arrayList.add(doctor);
			    
			}
			if(arrayList.size()>0)
			{
				Intent i=new Intent(SearchScreen.this,SearchResult.class);
				i.putExtra("list",(Serializable)arrayList);
				startActivity(i);
			}
			else
			{
				Toast.makeText(getApplicationContext(), "No Record found",Toast.LENGTH_SHORT).show();
			}
		
			
			
		}
	}

}
