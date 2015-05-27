package com.ons.doctor;

import java.io.Serializable;
import java.util.ArrayList;









import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.text.NoCopySpan.Concrete;
import android.text.Spanned;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

public class SearchResult extends Activity{
	
	TextView tv_speciality,tv_insurance;
	ListView lv_doc;
	ArrayList<Doctor> arrayList;
	LatLng KIEL;
	private GoogleMap map;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.doctor_list);
		@SuppressWarnings("unchecked")
		ArrayList<Doctor> serializableExtra = (ArrayList<Doctor>)getIntent().getSerializableExtra("list");
		arrayList=serializableExtra;
		KIEL = new LatLng(28.592218,77.318516);
		map = ((MapFragment) getFragmentManager().findFragmentById(R.id.map)).getMap();
		Marker kiel = map.addMarker(new MarkerOptions()
        .position(KIEL)
        .title("Kiel")
        .snippet("Kiel is cool")
        .icon(BitmapDescriptorFactory.fromResource(R.drawable.marker)));
		map.moveCamera(CameraUpdateFactory.newLatLngZoom(KIEL, 15));
		 map.animateCamera(CameraUpdateFactory.zoomTo(10), 2000, null);
		tv_speciality=(TextView)findViewById(R.id.tv_speciality);
		tv_insurance=(TextView)findViewById(R.id.tv_insurance);
		lv_doc=(ListView)findViewById(R.id.lv_doc);
		lv_doc.setAdapter(new Docadaptor());
		lv_doc.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,int position, long id) {
				Doctor doctor=arrayList.get(position);
				Intent i=new Intent(SearchResult.this,DoctorDetail.class);
				i.putExtra("data", doctor);
				startActivity(i);
				
			}
		});
		
	}
	
public class Docadaptor extends BaseAdapter
{

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return arrayList.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return arrayList.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		
		View v=convertView;
		if(v==null)
		{
		LayoutInflater inflater=(LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		v=inflater.inflate(R.layout.doc_list_item,null);
		}
		TextView tv_name=(TextView)v.findViewById(R.id.tv_name);
		TextView tv_address=(TextView)v.findViewById(R.id.tv_aadress);
		//Spanned spanned=Html.fromHtml(arrayList.get(position).getName()+", "+"<font color='#57D3DB'>"+arrayList.get(position).getDegree()+"</font>");
		//tv_name.setText(spanned);
		tv_name.setText(arrayList.get(position).getName()+", "+arrayList.get(position).getDegree());
		Spanned spanned=Html.fromHtml("<font color='#000000'>"+arrayList.get(position).getSpeciality()+"</font>, "+"<font color='#57D3DB'>"+arrayList.get(position).getPlace()+"</font>");
		tv_address.setText(spanned);
		//tv_address.setText(arrayList.get(position).getSpeciality()+", "+arrayList.get(position).getPlace());
		
		return v;
	}
	
}
}
