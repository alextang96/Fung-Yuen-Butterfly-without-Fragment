package hk.org.taipoea;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

public class CustomListViewAndroid extends Activity {

	ListView list;
	CustomAdapter adapter;
	public CustomListViewAndroid CustomListView = null;
	public ArrayList<ListModel> CustomListViewValuesArr = new ArrayList<ListModel>();
	private String[] strButterflyData;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_custom_list_view_android_example);

		// Get Bundle Object
		Bundle bundleButterflyData = this.getIntent().getExtras();
		
		// Get Bundle Data
		strButterflyData = bundleButterflyData.getStringArray("butterfly");
		
		CustomListView = this;

		/******** Take some data in Arraylist ( CustomListViewValuesArr ) ***********/
		setListData();

		Resources res = getResources();
		list = (ListView) findViewById(R.id.list); // List defined in XML ( See
													// Below )

		/**************** Create Custom Adapter *********/
		adapter = new CustomAdapter(CustomListView, CustomListViewValuesArr,
				res);
		list.setAdapter(adapter);

	}

	/****** Function to set data in ArrayList *************/
	public void setListData() {

		for (int i = 0; i < strButterflyData.length ; i++) {

			final ListModel sched = new ListModel();

			/******* Firstly take data in model object ******/
			sched.setButterflyName(strButterflyData[i]);
			sched.setImage("type" +  (i+1));

			/******** Take Model Object in ArrayList **********/
			CustomListViewValuesArr.add(sched);
		}

	}

	/***************** This function used by adapter ****************/
	public void onItemClick(int mPosition)
    {
        ListModel tempValues = ( ListModel ) CustomListViewValuesArr.get(mPosition);


       // SHOW ALERT                  

        Toast.makeText(CustomListView,
                ""+tempValues.getButterflyName()
                  +" Image:"+tempValues.getImage(),
                Toast.LENGTH_LONG)
        .show();
        
        Intent intent = new Intent(CustomListViewAndroid.this, BtfDetails.class);
		Bundle butterflyData = new Bundle();
		butterflyData.putString("butterfly", tempValues.getButterflyName());
		
		intent.putExtras(butterflyData);
		startActivity(intent);
    }
}
