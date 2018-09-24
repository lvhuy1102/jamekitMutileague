package com.hcpt.multileagues.database.binder;

import android.database.sqlite.SQLiteStatement;

import com.hcpt.multileagues.objects.AlarmMatch;

/**
 * Created by DoanKiem on 3/4/2016.
 */
public class AlarmMatchBinder implements ParameterBinder {
    @Override
    public void bind(SQLiteStatement st, Object object) {
        AlarmMatch alarmMatch = (AlarmMatch) object;
        st.bindString(1, alarmMatch.getMatchId());
        st.bindString(2, alarmMatch.getMatchTitle());
        st.bindString(3, stringOfBoolean(alarmMatch.isOnTime()));
        st.bindString(4, stringOfBoolean(alarmMatch.isBefore15Min()));
        st.bindString(5, stringOfBoolean(alarmMatch.isBefore30Min()));
        st.bindString(6, stringOfBoolean(alarmMatch.isBefore60Min()));
        st.bindString(7, alarmMatch.getTime());

    }

    private String stringOfBoolean(Boolean b) {
        return (b) ? "1" : "0";
    }
}
