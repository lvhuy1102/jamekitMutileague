package com.hcpt.multileagues.database.mapper;

import android.database.Cursor;

import com.hcpt.multileagues.configs.DatabaseConfig;
import com.hcpt.multileagues.objects.AlarmMatch;


/**
 * Created by DoanKiem on 3/4/2016.
 */
public class AlarmMatchMaper implements RowMapper<AlarmMatch> {
    @Override
    public AlarmMatch mapRow(Cursor row, int rowNum) {
        AlarmMatch item = new AlarmMatch();
        item.setMatchId(CursorParseUtility.getString(row, DatabaseConfig.KEY_MATCH_ID));
        item.setMatchTitle(CursorParseUtility.getString(row, DatabaseConfig.KEY_MATCH_TITLE));
        item.setTime(CursorParseUtility.getString(row, DatabaseConfig.KEY_TIME));
        item.setOnTime(convertToBoolean(CursorParseUtility.getString(row, DatabaseConfig.KEY_ON_TIME)));
        item.setBefore15Min(convertToBoolean(CursorParseUtility.getString(row, DatabaseConfig.KEY_15MIN_BEFORE)));
        item.setBefore30Min(convertToBoolean(CursorParseUtility.getString(row, DatabaseConfig.KEY_30MIN_BEFORE)));
        item.setBefore60Min(convertToBoolean(CursorParseUtility.getString(row, DatabaseConfig.KEY_60MIN_BEFORE)));
        return item;
    }

    private boolean convertToBoolean(String s) {
        return (s.equals("1")) ? true : false;
    }
}
