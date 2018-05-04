package com.sksoft.tipcalculatorsurvey;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;


public class ReadWriteManagerInPrefference {
	private final SharedPreferences mShrdPrfrnce;
	private final String PREF_NAME= "tipCal";

	public ReadWriteManagerInPrefference(Context con) {
		mShrdPrfrnce = con.getSharedPreferences(PREF_NAME,
				Context.MODE_PRIVATE);
	}

	public void writeStringIntoPrefference(String enumtoString, String TAG) {
		Editor editor = mShrdPrfrnce.edit();
		editor.putString(TAG, enumtoString);
		editor.commit();
		editor.clear();
	}
	public void writeStringIntoPrefference(String lat,String TAG_LAT,String lon, String TAG_LON) {
		Editor editor = mShrdPrfrnce.edit();
		editor.putString(TAG_LAT, lat);
		editor.putString(TAG_LON, lon);
		editor.commit();
		editor.clear();
	}
	public String readStringFromPrefference(String TAG) {
		return mShrdPrfrnce.getString(TAG, "");
	}

	public void writeIntergerIntoPrefference(int value, String TAG) {
		Editor editor = mShrdPrfrnce.edit();
		editor.putInt(TAG, value);
		editor.commit();
		editor.clear();
	}

	public int readIntegerFromPrefference(String TAG) {
		return mShrdPrfrnce.getInt(TAG, 0);
	}

	public void writeBolleanIntoPrefference(boolean value, String TAG) {
		Editor editor = mShrdPrfrnce.edit();
		editor.putBoolean(TAG, value);
		editor.commit();
		editor.clear();
	}

	public boolean readBooleanFromPrefference(String TAG) {
		return mShrdPrfrnce.getBoolean(TAG, false);
	}
	

}
