package com.hcpt.multileagues.database.mapper;

import com.hcpt.multileagues.database.DBKeyConfig;
import com.hcpt.multileagues.objects.APIObj;

import android.database.Cursor;

public class ApiMapper implements RowMapper<APIObj> {

	@Override
	public APIObj mapRow(Cursor row, int rowNum) {
		// TODO Auto-generated method stub
		APIObj mode = new APIObj();
		// mode.setId(CursorParseUtility.getInt(row, DBKeyConfig.KEY_API_ID));
		mode.setmApi(CursorParseUtility.getString(row, DBKeyConfig.KEY_API_API));
		mode.setmResult(CursorParseUtility.getString(row,
				DBKeyConfig.KEY_API_RESULT));

		return mode;
	}

}
