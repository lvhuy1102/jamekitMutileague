package com.hcpt.multileagues.database.mapper;

import android.database.Cursor;

import com.hcpt.multileagues.configs.DatabaseConfig;


/**
 * Created by DoanKiem on 3/8/2016.
 */
public class IdAlarmMatchMaper implements RowMapper<String> {
    @Override
    public String mapRow(Cursor row, int rowNum) {
        return CursorParseUtility.getString(row, DatabaseConfig.KEY_MATCH_ID);
    }
}
