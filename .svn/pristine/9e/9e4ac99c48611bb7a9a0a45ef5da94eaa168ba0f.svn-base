/*
 * Name: $RCSfile: DatabaseUtility.java,v $
 * Version: $Revision: 1.1 $
 * Date: $Date: Oct 31, 2011 2:55:54 PM $
 *
 * Copyright (C) 2011 COMPANY_NAME, Inc. All rights reserved.
 */

package com.hcpt.multileagues.database;

import java.util.ArrayList;
import java.util.List;

import com.hcpt.multileagues.configs.DatabaseConfig;
import com.hcpt.multileagues.database.binder.AlarmMatchBinder;
import com.hcpt.multileagues.database.binder.ApiBinder;
import com.hcpt.multileagues.database.mapper.AlarmMatchMaper;
import com.hcpt.multileagues.database.mapper.ApiMapper;
import com.hcpt.multileagues.objects.APIObj;
import com.hcpt.multileagues.objects.AlarmMatch;

import android.content.Context;
import android.util.Log;

public final class DatabaseUtility {

    public static String STRING_SQL_INSERT_INTO_API = "INSERT OR IGNORE INTO Api("
            + DBKeyConfig.KEY_API_API
            + ","
            + DBKeyConfig.KEY_API_RESULT
            + ")VALUES(?,?)";

    private static String STRING_SQL_INSERT_INTO_TABLE_ALARMS = "INSERT OR REPLACE INTO "
            + DatabaseConfig.TABLE_ALARMS
            + " ("
            + DatabaseConfig.KEY_MATCH_ID
            + " , "
            + DatabaseConfig.KEY_MATCH_TITLE
            + " , "
            + DatabaseConfig.KEY_ON_TIME
            + " , "
            + DatabaseConfig.KEY_15MIN_BEFORE
            + " , "
            + DatabaseConfig.KEY_30MIN_BEFORE
            + " , "
            + DatabaseConfig.KEY_60MIN_BEFORE
            + " , "
            + DatabaseConfig.KEY_TIME + ") VALUES (?, ?, ?, ?, ?, ?, ?) ";

    // ************************* TABLE API ************************************

    // add
    public static boolean insertApi(Context context, APIObj apiInfo) {
        PrepareStatement statement = new PrepareStatement(context);
        return statement.insert(STRING_SQL_INSERT_INTO_API, apiInfo,
                new ApiBinder());
    }

    // search
    public static boolean checkExistsApi(Context context, String API) {
        PrepareStatement statement = new PrepareStatement(context);
        ArrayList<APIObj> apiInfos = new ArrayList<APIObj>();
        apiInfos = statement.select(DBKeyConfig.TABLE_API, "*",
                DBKeyConfig.KEY_API_API + "='" + API + "'", new ApiMapper());
        if (apiInfos.size() > 0)
            return true;
        else {
            return false;
        }
    }

    public static APIObj getResuftFromApi(Context context, String API) {
        PrepareStatement statement = new PrepareStatement(context);
        ArrayList<APIObj>

                apiInfos = statement.select(DBKeyConfig.TABLE_API, "*",
                DBKeyConfig.KEY_API_API + "='" + API + "'", new ApiMapper());
        Log.e("SIZE", "size " + apiInfos.size());
        if (apiInfos.size() > 0) {

            return apiInfos.get(0);

        } else {
            return null;
        }
    }

    public static boolean updateResuft_Api(Context context, String result,
                                           String api) {
        char c = '\'';
        for (int i = 0; i < result.length(); i++) {
            if ((result.charAt(i) + "").equals(c + "")) {
                result = result.replace(c + "", "N\u00b4");
            }
        }

        PrepareStatement statement = new PrepareStatement(context);
        return statement.update(DBKeyConfig.TABLE_API, "result='" + result
                + "'", DBKeyConfig.KEY_API_API + "='" + api + "'");
    }

    public boolean insertAlarmMatch(AlarmMatch alarmMatch, Context context) {
        PrepareStatement statement = new PrepareStatement(context);
        return statement.insert(STRING_SQL_INSERT_INTO_TABLE_ALARMS, alarmMatch, new AlarmMatchBinder());
    }

    public boolean deleteAlarmMatch(String match_id, Context context) {
        PrepareStatement statement = new PrepareStatement(context);
        return statement.query(
                "DELETE FROM " + DatabaseConfig.TABLE_ALARMS
                        + " WHERE " + DatabaseConfig.KEY_MATCH_ID + " = '" + match_id + "'", null);
    }

    public ArrayList<AlarmMatch> getAlarmMatchById(String matchId, Context context) {
        PrepareStatement statement = new PrepareStatement(context);
        return statement.select(DatabaseConfig.TABLE_ALARMS, "*", DatabaseConfig.KEY_MATCH_ID + " = '" + matchId + "'", new AlarmMatchMaper());
    }

    public boolean checkAlarmSetted(String matchID, Context context) {
        PrepareStatement statement = new PrepareStatement(context);
        List<AlarmMatch> list = statement.select(DatabaseConfig.TABLE_ALARMS, "*",
                DatabaseConfig.KEY_MATCH_ID + " =  '" + matchID + "'", new AlarmMatchMaper());
        if (list.size() > 0) {
            return true;
        }
        return false;
    }
}
