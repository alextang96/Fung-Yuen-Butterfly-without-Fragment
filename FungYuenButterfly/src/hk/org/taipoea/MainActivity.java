package hk.org.taipoea;

import static hk.org.taipoea.DBButterfly.DATABASE_NAME;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.Toast;

public class MainActivity extends Activity {
	// SQLiteDatabase Target
	SQLiteDatabase db;
 
	// Helper Class Name
	DBButterfly helper = new DBButterfly(MainActivity.this, DATABASE_NAME);
	
	ImageButton[] imageButtons = new ImageButton[11];

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.guide1);
		db = helper.getReadableDatabase();
		int intNoOfRecord = Integer.valueOf(helper.getNoOfData(db));
		Log.e("NoOfRecord", intNoOfRecord + "");
		
		 View.OnClickListener ListenerA = new OnClickListener() {

			@Override
			public void onClick(View v) {
				db = helper.getReadableDatabase();
				String [] returnString = helper.getSpecific1And2ChineseNameBySpecies(db,v.getTag() + "");
				for (int i = 0 ; i < returnString.length ; i++) {
					Log.e("getSpecific1ChineseNameBySpecies", returnString[i]);
				}
				Intent intent = new Intent(MainActivity.this, CustomListViewAndroid.class);
				Bundle butterflyData = new Bundle();
				for (int i = 0 ; i < returnString.length ; i++) {
					butterflyData.putStringArray("butterfly", returnString);
				}
				
				intent.putExtras(butterflyData);
				startActivity(intent);
				
				Toast.makeText(getApplication(), v.getTag() + "", Toast.LENGTH_SHORT).show();
				db.close();
			}
			
		};
		
		// initial the imageButtons
		imageButtons[0] = (ImageButton) findViewById(R.id.imageButton1);
		imageButtons[1] = (ImageButton) findViewById(R.id.imageButton2);
		imageButtons[2] = (ImageButton) findViewById(R.id.imageButton3);
		imageButtons[3] = (ImageButton) findViewById(R.id.imageButton4);
		imageButtons[4] = (ImageButton) findViewById(R.id.imageButton5);
		imageButtons[5] = (ImageButton) findViewById(R.id.imageButton6);
		imageButtons[6] = (ImageButton) findViewById(R.id.imageButton7);
		imageButtons[7] = (ImageButton) findViewById(R.id.imageButton8);
		imageButtons[8] = (ImageButton) findViewById(R.id.imageButton9);
		imageButtons[9] = (ImageButton) findViewById(R.id.imageButton10);
		imageButtons[10] = (ImageButton) findViewById(R.id.imageButton11);
//		byte[] bb = helper.getBitMap(db);
//		Bitmap image = compressImage(BitmapFactory.decodeByteArray(bb, 0, bb.length));
//		imageButtons[0].setImageBitmap(image);
		
		

		
		// set to the listenerA
		for (int i = 0 ; i < imageButtons.length ; i++) {
			imageButtons[i].setOnClickListener(ListenerA);	
		}
		
		db.close();
	}
	


}