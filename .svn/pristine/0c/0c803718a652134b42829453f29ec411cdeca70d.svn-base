package com.hcpt.multileagues.database;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import com.hcpt.multileagues.configs.DatabaseConfig;
import com.hcpt.multileagues.utilities.SmartLog;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseOpenhelper extends SQLiteOpenHelper {

	private static final String TAG = "DatabaseOpenhelper";
	private Context context = null;
	private DatabaseConfig databaseConfig = null;

	public DatabaseOpenhelper(Context context, DatabaseConfig config) {

		super(context, config.getDB_NAME(), null, config.getDB_VERSION());
		this.context = context;
		this.databaseConfig = config;

		if (!isDatabaseExist()) {

			// Create blank file
			getReadableDatabase();
			close();
			try {
				copyDatabase();
			} catch (IOException e) {
				Log.d(TAG, "Error to init database");
			}
		}
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// Create Api table.
		String strCreateApiTbl = "create table if not exists "
				+ DBKeyConfig.TABLE_API + "(" + DBKeyConfig.KEY_API_ID + ","
				+ DBKeyConfig.KEY_API_API + "," + DBKeyConfig.KEY_API_RESULT
				+ ")";
		db.execSQL(strCreateApiTbl);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

	}

	/**
	 * Copy database to application directory on SD card
	 * 
	 * @throws IOException
	 * 
	 * @throws IOException
	 */
	private void copyDatabase() throws IOException {
		SmartLog.log(TAG, "Copy database into application directory");
		SmartLog.log(TAG, databaseConfig.getDatabasefullpath());
		InputStream is = context.getAssets().open(databaseConfig.getDB_NAME());
		OutputStream os = new FileOutputStream(
				databaseConfig.getDatabasefullpath());
		byte[] buffer = new byte[1024];
		int length;
		while ((length = is.read(buffer)) > 0) {
			os.write(buffer, 0, length);
		}
		os.flush();
		os.close();
		is.close();
	}

	/**
	 * Check database is exist
	 * 
	 * @return
	 */
	private boolean isDatabaseExist() {
		SQLiteDatabase checkDB = null;
		try {
			checkDB = SQLiteDatabase.openDatabase(
					databaseConfig.getDatabasefullpath(), null,
					SQLiteDatabase.NO_LOCALIZED_COLLATORS
							| SQLiteDatabase.OPEN_READONLY);
			
			SmartLog.log(
					TAG,
					"Database is exist! "
							+ databaseConfig.getDatabasefullpath()
							+ " ======================");
		} catch (SQLiteException e) {
			SmartLog.log(
					TAG,
					"Database is not exist! "
							+ databaseConfig.getDatabasefullpath()
							+ " ======================");
			e.printStackTrace();
		}
		if (checkDB != null) {
			checkDB.close();
		}
		return (checkDB != null) ? true : false;
	}

}
