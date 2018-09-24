package mobi.letsplay.livescore.database.mapper;

import android.database.Cursor;

import mobi.letsplay.livescore.configs.DatabaseConfig;

import mobi.letsplay.livescore.configs.DatabaseConfig;


/**
 * Created by DoanKiem on 3/8/2016.
 */
public class IdAlarmMatchMaper implements RowMapper<String> {
    @Override
    public String mapRow(Cursor row, int rowNum) {
        return CursorParseUtility.getString(row, DatabaseConfig.KEY_MATCH_ID);
    }
}
