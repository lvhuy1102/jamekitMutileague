package com.hcpt.multileagues.configs;

import com.hcpt.multileagues.BuildConfig;
import com.hcpt.multileagues.utilities.PacketUtility;

public final class DatabaseConfig {

	private static int DB_VERSION = 1;
	private static String DB_NAME = "premierleague.sqlite";
	private static DatabaseConfig instance = null;

	// --------------------TABLE ALARMS----------------------
	public static String TABLE_ALARMS = "tbAlarms";
	public static String KEY_MATCH_ID = "match_id";
	public static String KEY_MATCH_TITLE = "match_title";
	public static String KEY_ON_TIME = "on_time";
	public static String KEY_30MIN_BEFORE = "before_30min";
	public static String KEY_60MIN_BEFORE = "before_60min";
	public static String KEY_15MIN_BEFORE = "before_15min";
	public static String KEY_TIME = "time";

	/**
	 * constructor
	 */
	public DatabaseConfig() {

	}

	/**
	 * get database version
	 * 
	 * @return
	 */
	public int getDB_VERSION() {
		return DB_VERSION;
	}

	/**
	 * get database name
	 * 
	 * @return
	 */
	public String getDB_NAME() {
		return DB_NAME;
	}

	/**
	 * get class instance
	 * 
	 * @return
	 */

	public static DatabaseConfig getInstance() {
		if (instance == null) {
			instance = new DatabaseConfig();
		}
		return instance;
	}

	/**
	 * get database path
	 * 
	 * @return
	 */
	public String getDatabasepath() {
		PacketUtility packetUtility = new PacketUtility();
		return "data/data/" + BuildConfig.APPLICATION_ID + "/databases/";

	}

	/**
	 * get database full path
	 * 
	 * @return
	 */
	public String getDatabasefullpath() {
		return getDatabasepath() + DB_NAME;

	}
}
