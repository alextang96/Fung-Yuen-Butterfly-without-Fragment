package hk.org.taipoea;

import java.util.HashMap;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBButterfly extends SQLiteOpenHelper {

	// All Static variables
	// Database Version
	// TODO communicate with API
	public static int DATABASE_VERSION = 1;

	// Database Name
	public static final String DATABASE_NAME = "DBButterfly";

	// Login table name
	public static final String TABLE_NAME = "ButterflyInfo";
	
	// Butterfly Table Columns names
	public static final String BTF_ID = "_id";
	public static final String BTF_SEX = "sex";
	public static final String BTF_1SPECIES = "spec1";
	public static final String BTF_2SPECIES = "spec2";
	public static final String BTF_CHINESENAME = "chiName";
	public static final String BTF_ENGLISHNAME = "engName";
	public static final String BTF_SUBJECT = "sciName";
	public static final String BTF_BODYRANGE = "bodyRange";
	public static final String BTF_RANGETYPE = "rangeType";
	public static final String BTF_FONTCOLOR = "fotColor";
	public static final String BTF_BACKCOLOR = "bakColor";
	public static final String BTF_HAVEWINGTAIL = "haveWingTail";
	public static final String BTF_ADULTHABIT = "adultHabit";
	public static final String BTF_BABYHABIT = "babyHabit";
	public static final String BTF_DETAIL = "idenDetail";
	public static final String BTF_APPEARTIME = "appearTime";
	public static final String BTF_DISTRIBUTIONS = "distributions";
	public static final String BTF_IMAGE1 = "image1";
	public static final String BTF_IMAGE2 = "image2";
	public static final String BTF_IMAGE3 = "image3";
	
	private static Context context;

	public DBButterfly(Context context) {
		this(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	public DBButterfly(Activity activity, String dbName) {
		this(activity, DATABASE_NAME, null, DATABASE_VERSION);
	}
	

	public DBButterfly(Context context, String name, CursorFactory factory,
			int version) {
		super(context, name, factory, version);
		this.setContext(context);
	}

	// Creating Tables
	@Override
	public void onCreate(SQLiteDatabase db) {
		String CREATE_BUTTERFLY_TABLE = "CREATE TABLE " + TABLE_NAME + "("
				+ BTF_ID + " INTEGER PRIMARY KEY," + BTF_SEX + " TEXT,"
				+ BTF_1SPECIES + " TEXT," + BTF_2SPECIES + " TEXT,"
				+ BTF_CHINESENAME + " TEXT,"
				+ BTF_ENGLISHNAME + " TEXT," 
				+ BTF_SUBJECT + " TEXT," + BTF_BODYRANGE + " TEXT,"
				+ BTF_RANGETYPE + " TEXT," + BTF_FONTCOLOR + " TEXT,"
				+ BTF_BACKCOLOR + " TEXT," + BTF_HAVEWINGTAIL + " TEXT,"
				+ BTF_ADULTHABIT + " TEXT," + BTF_BABYHABIT + " TEXT,"
				+ BTF_DETAIL + " TEXT," + BTF_APPEARTIME + " TEXT,"
				+ BTF_DISTRIBUTIONS + " TEXT," 
				+ BTF_IMAGE1 + " TEXT," 
				+ BTF_IMAGE2 + " TEXT," 
				+ BTF_IMAGE3 + " TEXT" + ")";
		db.execSQL(CREATE_BUTTERFLY_TABLE);
		
		// UserFunctions -> Json -> Serverside Database
		Log.e("Database Created", "true");
		
		UserFunctions uf = new UserFunctions(getContext());
		JSONObject json = uf.checkVersion(1);
//		
//		//db.execSQL("INSERT INTO " + TABLE_NAME + "(_id) VALUES (null)");
//		addRecord(db, 1, "M", "SPEC", "Chinese Name", "English Name", "Sciname", "10-20", "Smail", "White", "Black", "Yes", "I don't know", "Same", "Ur???", "Sep - Oct", "Sha Tin");
		
		try {
			for (int i = 0 ; i < json.getInt("noOfRecord") ; i ++) {
			addRecord(db, json.getInt(BTF_ID + i), json.getString(BTF_SEX + i),json.getString(BTF_1SPECIES + i), json.getString(BTF_2SPECIES + i), json.getString(BTF_CHINESENAME + i), json.getString(BTF_ENGLISHNAME + i),
					json.getString(BTF_SUBJECT + i), json.getString(BTF_BODYRANGE + i), json.getString(BTF_RANGETYPE + i), json.getString(BTF_FONTCOLOR + i), json.getString(BTF_BACKCOLOR + i),
					json.getString(BTF_HAVEWINGTAIL + i), json.getString(BTF_ADULTHABIT + i), json.getString(BTF_BABYHABIT + i), json.getString(BTF_DETAIL + i), json.getString(BTF_APPEARTIME + i), json.getString(BTF_DISTRIBUTIONS + i), json.getString(BTF_IMAGE1 + i), json.getString(BTF_IMAGE2 + i), json.getString(BTF_IMAGE3 + i));
			Log.e("BTF_ID",json.getInt(BTF_ID + i) + "" );
			Log.e("BTF_CHINESENAME", json.getString(BTF_CHINESENAME + i) + "");
			Log.e("BTF_DISTRIBUTIONS", json.getString(BTF_DISTRIBUTIONS + i));
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	// Upgrading database
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// Drop older table if existed
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);

		// Create tables again
		onCreate(db);
	}

	/**
	 * Storing butterfly details in database
	 * */
	public void addRecord(SQLiteDatabase db,int _id, String sex, String spec1, String spec2, String chiName,
			String engName, String sciName, String bodyRange, String rangeType,
			String fotColor, String bakColor, String haveWingTail,
			String adultHabit, String babyHabit, String idenDetail,
			String appearTime, String distributions, String image1, String image2, String image3) {
		// TODO addRecord
		//SQLiteDatabase db = _db; //this.getWritableDatabase();
		
		ContentValues values = new ContentValues();
		values.put(BTF_ID, _id);
		values.put(BTF_SEX, sex);
		values.put(BTF_1SPECIES, spec1);
		values.put(BTF_2SPECIES, spec2);
		values.put(BTF_CHINESENAME, chiName);
		values.put(BTF_ENGLISHNAME, engName);
		values.put(BTF_SUBJECT, sciName);
		values.put(BTF_BODYRANGE, bodyRange);
		values.put(BTF_RANGETYPE, rangeType);
		values.put(BTF_FONTCOLOR, fotColor);
		values.put(BTF_BACKCOLOR, bakColor);
		values.put(BTF_HAVEWINGTAIL, haveWingTail);
		values.put(BTF_ADULTHABIT, adultHabit);
		values.put(BTF_BABYHABIT, babyHabit);
		values.put(BTF_DETAIL, idenDetail);
		values.put(BTF_APPEARTIME, appearTime);
		values.put(BTF_DISTRIBUTIONS, distributions);
		values.put(BTF_IMAGE1, image1);
		values.put(BTF_IMAGE2, image2);
		values.put(BTF_IMAGE3, image3);
		
		// Inserting Row
		db.insert(TABLE_NAME, null, values);
	}

	/**
	 * Getting butterfly data from database
	 * */
	public HashMap<String, String> getButterflyDetails(SQLiteDatabase db, String chiName) {
		HashMap<String, String> btfData = new HashMap<String, String>();
		String selectQuery = "select " + "*" + " from " + TABLE_NAME +
				" WHERE " + BTF_CHINESENAME + " = '" + chiName + "'";

		Cursor cursor = db.rawQuery(selectQuery, null);
		// Move to first row
		cursor.moveToFirst();
		if (cursor.getCount() > 0) {
			btfData.put(BTF_ID, cursor.getString(0));
			btfData.put(BTF_SEX, cursor.getString(1));
			btfData.put(BTF_1SPECIES, cursor.getString(2));
			btfData.put(BTF_2SPECIES, cursor.getString(3));
			btfData.put(BTF_CHINESENAME, cursor.getString(4));
			btfData.put(BTF_ENGLISHNAME, cursor.getString(5));
			btfData.put(BTF_SUBJECT, cursor.getString(6));
			btfData.put(BTF_BODYRANGE, cursor.getString(7));
			btfData.put(BTF_RANGETYPE, cursor.getString(8));
			btfData.put(BTF_FONTCOLOR, cursor.getString(9));
			btfData.put(BTF_BACKCOLOR, cursor.getString(10));
			btfData.put(BTF_HAVEWINGTAIL, cursor.getString(11));
			btfData.put(BTF_ADULTHABIT, cursor.getString(12));
			btfData.put(BTF_BABYHABIT, cursor.getString(13));
			btfData.put(BTF_DETAIL, cursor.getString(14));
			btfData.put(BTF_APPEARTIME, cursor.getString(15));
			btfData.put(BTF_DISTRIBUTIONS, cursor.getString(16));
		}
		else {
			// No Data
		}
		cursor.close();
		// return user
		return btfData;
	}	


	/**
	 * Re crate database Delete all tables and create them again
	 * */
	public void resetTables() {
		SQLiteDatabase db = this.getWritableDatabase();
		// Delete All Rows
		db.delete(TABLE_NAME, null, null);
		db.close();
	}

	public static Context getContext() {
		return context;
	}

	public static void setContext(Context context) {
		DBButterfly.context = context;
	}
	
	
// Use cursor to translate data to String Array
	public String[] cursorToArray(Cursor cursor) {
		int rows_num = cursor.getCount(); // calculate number of rows
		
		String[] sNote = new String[rows_num];
		if (rows_num != 0) {
			cursor.moveToFirst(); // pull back to the first row of record
			for (int i = 0; i < rows_num; i++) {
				String strCr = cursor.getString(0);
				sNote[i] = strCr;
				Log.e("result", sNote[i]);
				
				cursor.moveToNext();// move to next record
			}
		}
		else {
			sNote = new String[1];
			sNote[0] = "No Record!"; 
		}
		
		return sNote;
		
	}
	
	public String getNoOfData(SQLiteDatabase db) {
		Cursor cursor = db.rawQuery("" +
				"select Count(" + BTF_ID + ") from " + TABLE_NAME
				, null);
		
		// sNote is using for store the retrieve data
		String[] sNote = cursorToArray(cursor);
		
		cursor.close(); // close the cursor to release resources

		return sNote[0];
	}
	
	
	public String[] getAllDistributions(SQLiteDatabase db) {
		Cursor cursor = db.rawQuery("" +
				"select " + BTF_DISTRIBUTIONS + " from " + TABLE_NAME
				, null);
		
		// sNote is using for store the retrieve data
		String[] sNote = cursorToArray(cursor);
		
		cursor.close(); // close the cursor to release resources

		return sNote;
	}
	
	public String getDistributionsByChineseName(SQLiteDatabase db, String chiName) {
		Cursor cursor = db.rawQuery("" +
				"select " + BTF_DISTRIBUTIONS + " from " + TABLE_NAME +
				" WHERE " + BTF_CHINESENAME + " = '" + chiName + "'"
				, null);
		
		// sNote is using for store the retrieve data
		String[] sNote = cursorToArray(cursor);
		
		cursor.close(); // close the cursor to release resources

		return sNote[0];
	}
	
	public String[] getAllAppearTime(SQLiteDatabase db) {
		Cursor cursor = db.rawQuery("" +
				"select " + BTF_APPEARTIME + " from " + TABLE_NAME
				, null);
		
		// sNote is using for store the retrieve data
		String[] sNote = cursorToArray(cursor);
		
		cursor.close(); // close the cursor to release resources

		return sNote;
	}
	
	public String getAppearTimeByChineseName(SQLiteDatabase db, String chiName) {
		Cursor cursor = db.rawQuery("" +
				"select " + BTF_APPEARTIME + " from " + TABLE_NAME +
				" WHERE " + BTF_CHINESENAME + " = '" + chiName + "'"
				, null);
		
		// sNote is using for store the retrieve data
		String[] sNote = cursorToArray(cursor);
		
		cursor.close(); // close the cursor to release resources

		return sNote[0];
	}
	
	public String[] getAllDetail(SQLiteDatabase db) {
		Cursor cursor = db.rawQuery("" +
				"select " + BTF_DETAIL + " from " + TABLE_NAME
				, null);
		
		// sNote is using for store the retrieve data
		String[] sNote = cursorToArray(cursor);
		
		cursor.close(); // close the cursor to release resources

		return sNote;
	}
	
	public String getDetailByChineseName(SQLiteDatabase db, String chiName) {
		Cursor cursor = db.rawQuery("" +
				"select " + BTF_DETAIL + " from " + TABLE_NAME +
				" WHERE " + BTF_CHINESENAME + " = '" + chiName + "'"
				, null);
		
		// sNote is using for store the retrieve data
		String[] sNote = cursorToArray(cursor);
		
		cursor.close(); // close the cursor to release resources

		return sNote[0];
	}
	
	public String[] getAllBabyHabit(SQLiteDatabase db) {
		Cursor cursor = db.rawQuery("" +
				"select " + BTF_BABYHABIT + " from " + TABLE_NAME
				, null);
		
		// sNote is using for store the retrieve data
		String[] sNote = cursorToArray(cursor);
		
		cursor.close(); // close the cursor to release resources

		return sNote;
	}
	
	public String getBabyHabitByChineseName(SQLiteDatabase db, String chiName) {
		Cursor cursor = db.rawQuery("" +
				"select " + BTF_BABYHABIT + " from " + TABLE_NAME +
				" WHERE " + BTF_CHINESENAME + " = '" + chiName + "'"
				, null);
		
		// sNote is using for store the retrieve data
		String[] sNote = cursorToArray(cursor);
		
		cursor.close(); // close the cursor to release resources

		return sNote[0];
	}
	
	public String[] getAllAdultHabit(SQLiteDatabase db) {
		Cursor cursor = db.rawQuery("" +
				"select " + BTF_ADULTHABIT + " from " + TABLE_NAME
				, null);
		
		// sNote is using for store the retrieve data
		String[] sNote = cursorToArray(cursor);
		
		cursor.close(); // close the cursor to release resources

		return sNote;
	}
	
	public String getAdultHabitByChineseName(SQLiteDatabase db, String chiName) {
		Cursor cursor = db.rawQuery("" +
				"select " + BTF_ADULTHABIT + " from " + TABLE_NAME +
				" WHERE " + BTF_CHINESENAME + " = '" + chiName + "'"
				, null);
		
		// sNote is using for store the retrieve data
		String[] sNote = cursorToArray(cursor);
		
		cursor.close(); // close the cursor to release resources

		return sNote[0];
	}
	
	public String[] getAllHaveWingTail(SQLiteDatabase db) {
		Cursor cursor = db.rawQuery("" +
				"select " + BTF_HAVEWINGTAIL + " from " + TABLE_NAME
				, null);
		
		// sNote is using for store the retrieve data
		String[] sNote = cursorToArray(cursor);
		
		cursor.close(); // close the cursor to release resources

		return sNote;
	}
	
	public String getHaveWingTailByChineseName(SQLiteDatabase db, String chiName) {
		Cursor cursor = db.rawQuery("" +
				"select " + BTF_HAVEWINGTAIL + " from " + TABLE_NAME +
				" WHERE " + BTF_CHINESENAME + " = '" + chiName + "'"
				, null);
		
		// sNote is using for store the retrieve data
		String[] sNote = cursorToArray(cursor);
		
		cursor.close(); // close the cursor to release resources

		return sNote[0];
	}
	
	public String[] getAllBackColor(SQLiteDatabase db) {
		Cursor cursor = db.rawQuery("" +
				"select " + BTF_BACKCOLOR + " from " + TABLE_NAME
				, null);
		
		// sNote is using for store the retrieve data
		String[] sNote = cursorToArray(cursor);
		
		cursor.close(); // close the cursor to release resources

		return sNote;
	}
	
	public String getBackColorByChineseName(SQLiteDatabase db, String chiName) {
		Cursor cursor = db.rawQuery("" +
				"select " + BTF_BACKCOLOR + " from " + TABLE_NAME +
				" WHERE " + BTF_CHINESENAME + " = '" + chiName + "'"
				, null);
		
		// sNote is using for store the retrieve data
		String[] sNote = cursorToArray(cursor);
		
		cursor.close(); // close the cursor to release resources

		return sNote[0];
	}
	
	public String[] getAllFontColor(SQLiteDatabase db) {
		Cursor cursor = db.rawQuery("" +
				"select " + BTF_FONTCOLOR + " from " + TABLE_NAME
				, null);
		
		// sNote is using for store the retrieve data
		String[] sNote = cursorToArray(cursor);
		
		cursor.close(); // close the cursor to release resources

		return sNote;
	}
	
	public String getFontColorByChineseName(SQLiteDatabase db, String chiName) {
		Cursor cursor = db.rawQuery("" +
				"select " + BTF_FONTCOLOR + " from " + TABLE_NAME +
				" WHERE " + BTF_CHINESENAME + " = '" + chiName + "'"
				, null);
		
		// sNote is using for store the retrieve data
		String[] sNote = cursorToArray(cursor);
		
		cursor.close(); // close the cursor to release resources

		return sNote[0];
	}
	
	public String[] getAllRangeType(SQLiteDatabase db) {
		Cursor cursor = db.rawQuery("" +
				"select " + BTF_RANGETYPE + " from " + TABLE_NAME
				, null);
		
		// sNote is using for store the retrieve data
		String[] sNote = cursorToArray(cursor);
		
		cursor.close(); // close the cursor to release resources

		return sNote;
	}
	
	public String getRangeTypeByChineseName(SQLiteDatabase db, String chiName) {
		Cursor cursor = db.rawQuery("" +
				"select " + BTF_RANGETYPE + " from " + TABLE_NAME +
				" WHERE " + BTF_CHINESENAME + " = '" + chiName + "'"
				, null);
		
		// sNote is using for store the retrieve data
		String[] sNote = cursorToArray(cursor);
		
		cursor.close(); // close the cursor to release resources

		return sNote[0];
	}
	
	public String[] getAllBodyRange(SQLiteDatabase db) {
		Cursor cursor = db.rawQuery("" +
				"select " + BTF_BODYRANGE + " from " + TABLE_NAME
				, null);
		
		// sNote is using for store the retrieve data
		String[] sNote = cursorToArray(cursor);
		
		cursor.close(); // close the cursor to release resources

		return sNote;
	}
	
	public String getBodyRangeByChineseName(SQLiteDatabase db, String chiName) {
		Cursor cursor = db.rawQuery("" +
				"select " + BTF_BODYRANGE + " from " + TABLE_NAME +
				" WHERE " + BTF_CHINESENAME + " = '" + chiName + "'"
				, null);
		
		// sNote is using for store the retrieve data
		String[] sNote = cursorToArray(cursor);
		
		cursor.close(); // close the cursor to release resources

		return sNote[0];
	}
	
	public String[] getAllSubject(SQLiteDatabase db) {
		Cursor cursor = db.rawQuery("" +
				"select " + BTF_SUBJECT + " from " + TABLE_NAME
				, null);
		
		// sNote is using for store the retrieve data
		String[] sNote = cursorToArray(cursor);
		
		cursor.close(); // close the cursor to release resources

		return sNote;
	}
	
	public String[] getSubjectByChineseName(SQLiteDatabase db, String chiName) {
		Cursor cursor = db.rawQuery("" +
				"select " + BTF_SUBJECT + " from " + TABLE_NAME +
				" WHERE " + BTF_CHINESENAME + " = '" + chiName + "'"
				, null);
		
		// sNote is using for store the retrieve data
		String[] sNote = cursorToArray(cursor);
		
		cursor.close(); // close the cursor to release resources

		return sNote;
	}
	
	public String[] getAllEnglishName(SQLiteDatabase db) {
		Cursor cursor = db.rawQuery("" +
				"select " + BTF_ENGLISHNAME + " from " + TABLE_NAME
				, null);
		
		// sNote is using for store the retrieve data
		String[] sNote = cursorToArray(cursor);
		
		cursor.close(); // close the cursor to release resources

		return sNote;
	}
	
	public String getEnglishNameByChineseName(SQLiteDatabase db, String chiName) {
		Cursor cursor = db.rawQuery("" +
				"select " + BTF_ENGLISHNAME + " from " + TABLE_NAME +
				" WHERE " + BTF_CHINESENAME + " = '" + chiName + "'"
				, null);
		
		// sNote is using for store the retrieve data
		String[] sNote = cursorToArray(cursor);
		
		cursor.close(); // close the cursor to release resources

		return sNote[0];
	}
	
	public String[] getSpecific1And2ChineseNameBySpecies(SQLiteDatabase db,String species) {
		String statement = "" +
				"SELECT " + BTF_CHINESENAME + " FROM " + TABLE_NAME
				+ " WHERE " +
				BTF_1SPECIES +" = '" + species + "' OR " + BTF_2SPECIES + " = '" + species + "'";
		
		Cursor cursor = db.rawQuery(statement
				, null);
		Log.e("statement", statement);
		Log.e("cursor", cursor.toString());
		
		// sNote is using for store the retrieve data
		String[] sNote = cursorToArray(cursor);
		
		cursor.close(); // close the cursor to release resources

		return sNote;
	}
	
	public String getSpecific1ByChineseName(SQLiteDatabase db, String chiName) {
		Cursor cursor = db.rawQuery("" +
				"select " + BTF_1SPECIES + " from " + TABLE_NAME +
				" WHERE " + BTF_CHINESENAME + " = '" + chiName + "'"
				, null);
		
		// sNote is using for store the retrieve data
		String[] sNote = cursorToArray(cursor);
		
		cursor.close(); // close the cursor to release resources

		return sNote[0];
	}
	
	public String[] getAllChineseName(SQLiteDatabase db) {
		Cursor cursor = db.rawQuery("" +
				"select " + BTF_CHINESENAME + " from " + TABLE_NAME
				, null);
		
		// sNote is using for store the retrieve data
		String[] sNote = cursorToArray(cursor);
		
		cursor.close(); // close the cursor to release resources

		return sNote;
	}
	
	
	public String[] getAllSpecies1(SQLiteDatabase db) {
		Cursor cursor = db.rawQuery("" +
				"select DISTINCT " + BTF_1SPECIES + " from " + TABLE_NAME
				, null);
		
		// sNote is using for store the retrieve data
		String[] sNote = cursorToArray(cursor);
		
		cursor.close(); // close the cursor to release resources

		return sNote;
	}
	
	public String[] getAllSex(SQLiteDatabase db) {
		Cursor cursor = db.rawQuery("" +
				"select " + BTF_SEX + " from " + TABLE_NAME
				, null);
		
		// sNote is using for store the retrieve data
		String[] sNote = cursorToArray(cursor);
		
		cursor.close(); // close the cursor to release resources

		return sNote;
	}
	
	public String getSexByChineseName(SQLiteDatabase db, String chiName) {
		Cursor cursor = db.rawQuery("" +
				"select " + BTF_SEX + " from " + TABLE_NAME +
				" WHERE " + BTF_CHINESENAME + " = '" + chiName + "'"
				, null);
		
		// sNote is using for store the retrieve data
		String[] sNote = cursorToArray(cursor);
		
		cursor.close(); // close the cursor to release resources

		return sNote[0];
	}
	
	public String[] getAllID(SQLiteDatabase db) {
		Cursor cursor = db.rawQuery("" +
				"select " + BTF_ID + " from " + TABLE_NAME
				, null);
		
		// sNote is using for store the retrieve data
		String[] sNote = cursorToArray(cursor);
		
		cursor.close(); // close the cursor to release resources

		return sNote;
	}

} 
