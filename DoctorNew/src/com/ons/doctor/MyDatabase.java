package com.ons.doctor;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;



public class MyDatabase {
	public static   String DB_NAME ="doctorapp";
	 
	private static final int DATABASE_VER = 8;
 
	private static final String TAG = "DBAdapter";
	
	private final Context context;
	 
	private DatabaseHelper DBHelper;
 
	private SQLiteDatabase db;
	
	public static final String Table_Location="location";
	public static final String Table_Specialty="specialty";
	public static final String Table_SubSpecialty="subspecialty";
	public static final String Table_Language="language";
	public static final String Table_Insurance="insurance";
	public static final String Table_Main="main";
	
	
	private static final String create_table_location="CREATE TABLE location (_id INTEGER PRIMARY KEY AUTOINCREMENT ,primary_location TEXT,phone TEXT,fax TEXT,hours_of_operation TEXT,address1 TEXT,address2 TEXT,address3 TEXT,city TEXT,state TEXT,zip TEXT);";
	private static final String create_table_specialty="CREATE TABLE specialty (_id INTEGER PRIMARY KEY AUTOINCREMENT ,specialty_name TEXT);";
	private static final String create_table_subspecialty="CREATE TABLE subspecialty (_id INTEGER PRIMARY KEY AUTOINCREMENT ,specialty_id TEXT,sub_specialty_name TEXT);";
	private static final String create_table_language="CREATE TABLE language (_id INTEGER PRIMARY KEY AUTOINCREMENT ,language_name TEXT);";
	private static final String create_table_insurance="CREATE TABLE insurance (_id INTEGER PRIMARY KEY AUTOINCREMENT ,insurance_name TEXT);";
	private static final String create_table_main="CREATE TABLE main (_id INTEGER PRIMARY KEY AUTOINCREMENT ,employee_id TEXT,location_id TEXT,specialty_id TEXT,sub_specialty_id TEXT,language_id TEXT,insurance_id TEXT,is_active TEXT,date_inactivated TEXT,inactive_notes TEXT,status TEXT,gender TEXT,role TEXT,prefix TEXT,first_name TEXT,middle_initial TEXT,last_name TEXT,degree TEXT,practice_name TEXT,photo TEXT,video TEXT,board_certifications TEXT,sub_specialty_board TEXT);";
	
	public static final String Column_id="_id";
	public static final String Column_primary_location="primary_location";
	public static final String Column_phone="phone";
	public static final String Column_fax="fax";
	public static final String Column_hours_of_operation="hours_of_operation";
	public static final String Column_address1="address1";
	public static final String Column_address2="address2";
	public static final String Column_address3="address3";
	public static final String Column_city="city";
	public static final String Column_state="state";
	public static final String Column_zip="zip";
	
	public static final String Column_specialty_name="specialty_name";
	
	public static final String Column_specialty_id="specialty_id";
	public static final String Column_sub_specialty_name="sub_specialty_name";
	
	public static final String Column_language_name="language_name";
	public static final String Column_insurance_name="insurance_name";
	
	public static final String Column_employee_id="employee_id";
	public static final String Column_location_id="location_id";
	
	public static final String Column_sub_specialty_id="sub_specialty_id";
	
	public static final String Column_language_id="language_id";
	public static final String Column_insurance_id="insurance_id";
	public static final String Column_is_active="is_active";
	public static final String Column_date_inactivated="date_inactivated";
	
	public static final String Column_inactive_notes="inactive_notes";
	public static final String Column_status="status";
	public static final String Column_gender="gender";
	public static final String Column_role="role";
	public static final String Column_prefix="prefix";
	public static final String Column_first_name="first_name";
	public static final String Column_middle_initial="middle_initial";
	public static final String Column_degree="degree";
	public static final String Column_practice_name="practice_name";
	public static final String Column_photo="photo";
	public static final String Column_last_name="last_name";
	public static final String Column_video="video";
	public static final String Column_board_certifications="board_certifications";
	public static final String Column_sub_specialty_board="sub_specialty_board";
	
	
    static String locationAllColumn[]={Column_id,Column_primary_location,Column_phone,Column_fax,Column_hours_of_operation,Column_address1,Column_address2,Column_address3,Column_city,Column_state,Column_zip};
    static String specialtyAllColumn[]={Column_id,Column_specialty_name};
    static String subspecialtyAllColumn[]={Column_id,Column_specialty_id,Column_sub_specialty_name};
    static String languageAllColumn[]={Column_id,Column_language_name};
    static String insuranceAllColumn[]={Column_id,Column_insurance_name};
    static String mainAllColumn[]={Column_id,Column_employee_id,Column_location_id,Column_specialty_id,Column_sub_specialty_id,Column_language_id,Column_insurance_id,Column_is_active,Column_date_inactivated,Column_inactive_notes,Column_status,Column_gender,Column_role,Column_prefix,Column_first_name,Column_middle_initial,Column_last_name,Column_degree,Column_practice_name,Column_photo,Column_video,Column_board_certifications,Column_sub_specialty_board};
    
	
	public MyDatabase(Context con)
	{
		this.context=con;
		DBHelper=new DatabaseHelper(context);
	}
	private static class DatabaseHelper extends SQLiteOpenHelper
	{
		public DatabaseHelper(Context context)
		{
			super(context, DB_NAME,null,DATABASE_VER);
		}
		@Override
		public void onUpgrade(SQLiteDatabase db, int oldversion, int newversion) 
		{
			db.execSQL("DROP TABLE IF EXISTS "+Table_Location);
			db.execSQL("DROP TABLE IF EXISTS "+Table_Insurance);
			db.execSQL("DROP TABLE IF EXISTS "+Table_Language);
			db.execSQL("DROP TABLE IF EXISTS "+Table_Main);
			db.execSQL("DROP TABLE IF EXISTS "+Table_Specialty);
			db.execSQL("DROP TABLE IF EXISTS "+Table_SubSpecialty);
			onCreate(db);
		}
		@Override
		public void onCreate(SQLiteDatabase db) 
		{
			db.execSQL(create_table_location);
			db.execSQL(create_table_insurance);
			db.execSQL(create_table_language);
			db.execSQL(create_table_main);
			db.execSQL(create_table_specialty);
			db.execSQL(create_table_subspecialty);
			insertLocation(db);
			insertInsurance(db);
			insertLanguage(db);
			insertSpecialty(db);
			insertSubspecialty(db);
			insertMain(db);
		}
		public void insertLocation(SQLiteDatabase db)
		{
			insertDataInLocation(db,"Yes","555-666-7777","555-666-7778","M-F 9am - 5pm","1 Main St","Suite 301","","Mineola","NY","11501");
			insertDataInLocation(db,"No","555.777.8888","555.666.8889","M-TH 9am - 5pm F 10am - 5pm","2 River Ave","Suite 2","","Garden City","NY","11530");
			insertDataInLocation(db,"Yes","555-666-7777","555-666-7778","M-F 9am - 5pm","1 Main St","Suite 301","","Mineola","NY","11514");
			insertDataInLocation(db,"Yes","555-666-7777","555-666-7778","M-F 9am - 5pm","1 Main St","Suite 301","","Mineola","NY","11554");
		}
		public void insertInsurance(SQLiteDatabase db)
		{
			insertDataInInsurance(db,"CIGNA");
			insertDataInInsurance(db,"Oxford");
			insertDataInInsurance(db,"Blue Cross");
			insertDataInInsurance(db,"Aetna");
			insertDataInInsurance(db,"UnitedHealth");
		}
		public void insertLanguage(SQLiteDatabase db)
		{
			insertDataInLanguage(db,"English");
			insertDataInLanguage(db,"Spanish");
			insertDataInLanguage(db,"Italian");
			insertDataInLanguage(db,"French");
		}
		public void insertSpecialty(SQLiteDatabase db)
		{
			insertDataInSpecialty(db,"Specialty1");
			insertDataInSpecialty(db,"Specialty2");
			insertDataInSpecialty(db,"Specialty3");
			insertDataInSpecialty(db,"Specialty4");
		}
		public void insertSubspecialty(SQLiteDatabase db)
		{
			insertDataInSubspecialty(db,"1","Sub specialty 1");
			insertDataInSubspecialty(db,"1","Sub specialty 2");
			insertDataInSubspecialty(db,"1","Sub specialty 3");
			insertDataInSubspecialty(db,"2","Sub specialty 4");
			insertDataInSubspecialty(db,"2","Sub specialty 5");
			insertDataInSubspecialty(db,"3","Sub specialty 6");
			insertDataInSubspecialty(db,"3","Sub specialty 7");
		}
		public void insertMain(SQLiteDatabase db)
		{
			insertDataInmain(db,"22348472","1,2","1","1","1","1,2,4,5","Yes","","","Active","M","Doctor","Dr.","Derek","C.","Jeter","M.D.","Jeter Associates, P.C.","http://www.domain.com/images/jeter.headshot.jpg","http://youtube.com/82737366","Board 1, Board 2","Sub specialty board");
			insertDataInmain(db,"123433","1,2,3","2","3,4","1,2","3,4","Yes","","","Active","f","Doctor","Mrs.","Dana","L.","Jeter","M.D.","Jeter Associates, P.C.","http://www.domain.com/images/jeter.headshot.jpg","http://youtube.com/82737366","Board 1, Board 2","Sub specialty board");
			insertDataInmain(db,"22348472","1","3","2,4","1,3","1,2,4,5","Yes","","","Active","M","Doctor","Dr.","Derek","C.","Jeter","M.D.","Jeter Associates, P.C.","http://www.domain.com/images/jeter.headshot.jpg","http://youtube.com/82737366","Board 1, Board 2","Sub specialty board");
		}
		public void insertDataInLocation(SQLiteDatabase db,String primary_location,String phone,String fax,String hours_of_operation,String address1,String address2,String address3,String city,String state,String zip)
		{
			ContentValues value=new ContentValues();
			value.put(Column_primary_location, primary_location);
			value.put(Column_phone, phone);
			value.put(Column_fax, fax);
			value.put(Column_hours_of_operation, hours_of_operation);
			value.put(Column_address1, address1);
			value.put(Column_address2, address2);
			value.put(Column_address3, address3);
			value.put(Column_city, city);
			value.put(Column_state, state);
			value.put(Column_zip, zip);
			db.insert(Table_Location, null, value);
		}
		public void insertDataInInsurance(SQLiteDatabase db,String name)
		{
			ContentValues value=new ContentValues();
			value.put(Column_insurance_name, name);
			db.insert(Table_Insurance, null, value);
		}
		public void insertDataInLanguage(SQLiteDatabase db,String name)
		{
			ContentValues value=new ContentValues();
			value.put(Column_language_name, name);
			db.insert(Table_Language, null, value);
		}
		public void insertDataInSpecialty(SQLiteDatabase db,String name)
		{
			ContentValues value=new ContentValues();
			value.put(Column_specialty_name, name);
			db.insert(Table_Specialty, null, value);
		}
		public void insertDataInSubspecialty(SQLiteDatabase db,String spe_id,String name)
		{
			ContentValues value=new ContentValues();
			value.put(Column_specialty_id, spe_id);
			value.put(Column_sub_specialty_name, name);
			db.insert(Table_SubSpecialty, null, value);
		}
		public void insertDataInmain(SQLiteDatabase db,String employee_id,String location_id,String specialty_id,String sub_specialty_id,String language_id,String insurance_id,String is_active,String date_inactivated,String inactive_notes,String state,String gender,String role,String prefix,String first_name,String middle_initial,String last_name,String degree,String practice_name,String photo,String video,String board_certifications,String sub_specialty_board)
		{
			ContentValues value=new ContentValues();
			value.put(Column_employee_id, employee_id);
			value.put(Column_location_id, location_id);
			value.put(Column_specialty_id, specialty_id);
			value.put(Column_sub_specialty_id, sub_specialty_id);
			value.put(Column_language_id, language_id);
			value.put(Column_insurance_id, insurance_id);
			value.put(Column_is_active, is_active);
			value.put(Column_date_inactivated, date_inactivated);
			value.put(Column_inactive_notes, inactive_notes);
			value.put(Column_status, state);
			value.put(Column_gender, gender);
			value.put(Column_role, role);
			value.put(Column_prefix, prefix);
			value.put(Column_first_name, first_name);
			value.put(Column_middle_initial, middle_initial);
			value.put(Column_last_name, last_name);
			value.put(Column_degree, degree);
			value.put(Column_practice_name, practice_name);
			value.put(Column_photo, photo);
			value.put(Column_video, video);
			value.put(Column_board_certifications, board_certifications);
			value.put(Column_sub_specialty_board, sub_specialty_board);
			db.insert(Table_Main, null, value);
		}
	}
	public MyDatabase open() throws SQLException
	{
		db = DBHelper.getWritableDatabase();
        return this;
    }
	public void close() 
	{
		DBHelper.close();
    }
	
	public String getLocationidByzip(String zip)
	{
//		Cursor cr=db.rawQuery("select * from location where zip='"+zip+"'", null);
		Cursor cr=db.query(Table_Location,locationAllColumn,Column_zip+"="+"'"+zip+"'",null,null,null,null,null);
		int a=cr.getCount();
		cr.moveToNext();
		return cr.getString(0);
	}
	public String getInsuranceidByname(String name)
	{
		Cursor cr=db.rawQuery("select distinct _id from insurance where insurance_name='"+name+"'", null);
		cr.moveToFirst();
		return cr.getString(0);
	}
	public String getSpecilaityidByname(String name)
	{
		Cursor cr=db.rawQuery("select distinct _id from specialty where specialty_name='"+name+"'", null);
		cr.moveToFirst();
		return cr.getString(0);
	}
	public String getLocationById(String id)
	{
		Cursor cr=db.rawQuery("select * from location where _id='"+id+"'", null);
		cr.moveToFirst();
		String city=cr.getString(8);
		String state=cr.getString(9);
		return city+","+state;
	}
    public Cursor getDoctors()
    {
    	Cursor cr=db.query(Table_Main, mainAllColumn, null, null,null,null, null);
		return cr;
    }
    
    public Cursor getDoctorbyId(String id)
    {
    	Cursor cr=db.rawQuery("select * from main where _id='"+id+"'", null);
    	return cr;
    }
    public String getSpecilaityById(String id)
	{
		Cursor cr=db.rawQuery("select * from specialty where _id='"+id+"'", null);
		cr.moveToFirst();
		String Specilaity=cr.getString(1);
		return Specilaity;
	}
    public String getphoneByLocationId(String id)
	{
		Cursor cr=db.rawQuery("select * from location where _id='"+id+"'", null);
		cr.moveToFirst();
		String contact=cr.getString(2);
		return contact;
	}
    
}
