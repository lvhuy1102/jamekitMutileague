package mobi.letsplay.livescore.database;

import android.database.Cursor;

public interface IRowMapper<E> {
	E mapRow(Cursor row, int rowNum);
}