package mobi.letsplay.livescore.database.binder;

import mobi.letsplay.livescore.objects.APIObj;

import android.database.sqlite.SQLiteStatement;

import mobi.letsplay.livescore.objects.APIObj;

public class ApiBinder implements ParameterBinder {

	@Override
	public void bind(SQLiteStatement st, Object object) {
		// TODO Auto-generated method stub
		APIObj mode = (APIObj) object;
		// st.bindLong(1, mode.getId());
		st.bindString(1, mode.getmApi());
		if (mode.getmResult() == null) {
			st.bindString(2, "");
		} else {
			st.bindString(2, mode.getmResult());
		}
	}
}
