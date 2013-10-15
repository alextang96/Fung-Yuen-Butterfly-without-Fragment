package hk.org.taipoea;

import static hk.org.taipoea.DBButterfly.DATABASE_NAME;
import static hk.org.taipoea.DBButterfly.BTF_1SPECIES;
import static hk.org.taipoea.DBButterfly.BTF_2SPECIES;
import static hk.org.taipoea.DBButterfly.BTF_ADULTHABIT;
import static hk.org.taipoea.DBButterfly.BTF_APPEARTIME;
import static hk.org.taipoea.DBButterfly.BTF_BABYHABIT;
import static hk.org.taipoea.DBButterfly.BTF_BACKCOLOR;
import static hk.org.taipoea.DBButterfly.BTF_BODYRANGE;
import static hk.org.taipoea.DBButterfly.BTF_CHINESENAME;
import static hk.org.taipoea.DBButterfly.BTF_DETAIL;
import static hk.org.taipoea.DBButterfly.BTF_DISTRIBUTIONS;
import static hk.org.taipoea.DBButterfly.BTF_ENGLISHNAME;
import static hk.org.taipoea.DBButterfly.BTF_FONTCOLOR;
import static hk.org.taipoea.DBButterfly.BTF_HAVEWINGTAIL;
import static hk.org.taipoea.DBButterfly.BTF_ID;
import static hk.org.taipoea.DBButterfly.BTF_IMAGE1;
import static hk.org.taipoea.DBButterfly.BTF_IMAGE2;
import static hk.org.taipoea.DBButterfly.BTF_IMAGE3;
import static hk.org.taipoea.DBButterfly.BTF_RANGETYPE;
import static hk.org.taipoea.DBButterfly.BTF_SEX;
import static hk.org.taipoea.DBButterfly.BTF_SUBJECT;

import java.util.HashMap;

import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class BtfDetails extends Activity {
	final int[][] tvSET = { { 0, R.id.sex }, { 1, R.id.spec },
			{ 2, R.id.engName }, { 3, R.id.bodyRange }, { 4, R.id.rangeType },
			{ 5, R.id.fotColor }, { 6, R.id.backColor },
			{ 7, R.id.haveWingTail }, { 8, R.id.adultHabit },
			{ 9, R.id.badyHabit }, { 10, R.id.idenDetail },
			{ 11, R.id.Distributions }, { 12, R.id.appearTime },
			{ 13, R.id.chiName }, { 14, R.id.sciName } };

	final int tvSEX = 0, tvSPEC = 1, tvENGNAME = 2, tvBODYRANGE = 3,
			tvRangeType = 4, tvFONTCOLOR = 5, tvBACKCOLOR = 6,
			tvHAVEWINGTAIL = 7, tvADULTHABIT = 8, tvBABYHABIT = 9,
			tvIDENDETAIL = 10, tvDISTRIBUTIONS = 11, tvAPPEARTIME = 12,
			tvCHINAME = 13, tvSCINAME = 14;

	final int INDEX = 0, RID = 1;
	TextView[] tv = new TextView[15];

	private String strButterflyName;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_btf_details);
		// init the TextView with INDEX

		for (int i = 0; i < tv.length; i++) {
			tv[i] = (TextView) findViewById(tvSET[i][RID]);
		}

		// Database Helper Class
		DBButterfly helper = new DBButterfly(BtfDetails.this, DATABASE_NAME);

		SQLiteDatabase db = helper.getReadableDatabase();

		// Get Bundle Object
		Bundle bundleButterflyData = this.getIntent().getExtras();

		// Get Bundle Data
		strButterflyName = bundleButterflyData.getString("butterfly");

		HashMap<String, String> btfData = new HashMap<String, String>();
		btfData = helper.getButterflyDetails(db, strButterflyName);

		if (!btfData.isEmpty()) {
		tv[tvSET[tvENGNAME][INDEX]].setText(btfData.get(BTF_ENGLISHNAME));
		tv[tvSET[tvSEX][INDEX]].setText(btfData.get(BTF_SEX));
		tv[tvSET[tvSPEC][INDEX]].setText(btfData.get(BTF_1SPECIES) + " "
				+ btfData.get(BTF_2SPECIES));
		tv[tvSET[tvBODYRANGE][INDEX]].setText(btfData.get(BTF_BODYRANGE));
		tv[tvSET[tvRangeType][INDEX]].setText(btfData.get(BTF_RANGETYPE));
		tv[tvSET[tvFONTCOLOR][INDEX]].setText(btfData.get(BTF_FONTCOLOR));
		tv[tvSET[tvBACKCOLOR][INDEX]].setText(btfData.get(BTF_BACKCOLOR));
		tv[tvSET[tvHAVEWINGTAIL][INDEX]].setText(btfData.get(BTF_HAVEWINGTAIL));
		tv[tvSET[tvADULTHABIT][INDEX]].setText(btfData.get(BTF_ADULTHABIT));
		tv[tvSET[tvBABYHABIT][INDEX]].setText(btfData.get(BTF_BABYHABIT));
		tv[tvSET[tvIDENDETAIL][INDEX]].setText(btfData.get(BTF_DETAIL));
		tv[tvSET[tvAPPEARTIME][INDEX]].setText(btfData.get(BTF_APPEARTIME));
		tv[tvSET[tvDISTRIBUTIONS][INDEX]].setText(btfData
				.get(BTF_DISTRIBUTIONS));
		tv[tvSET[tvCHINAME][INDEX]].setText(btfData.get(BTF_CHINESENAME));
		tv[tvSET[tvSCINAME][INDEX]].setText(btfData.get(BTF_SUBJECT));
		}

		db.close();
	}

	// @Override
	// public boolean onCreateOptionsMenu(Menu menu) {
	// // Inflate the menu; this adds items to the action bar if it is present.
	// getMenuInflater().inflate(R.menu.btf_details, menu);
	// return true;
	// }

}
