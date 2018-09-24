package com.hcpt.multileagues.fragments;

import android.app.AlarmManager;
import android.app.Dialog;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.hcpt.multileagues.R;
import com.hcpt.multileagues.activities.MatchDetailActivity;
import com.hcpt.multileagues.activities.MatchDetailNotPlayed;
import com.hcpt.multileagues.activities.MatchDetailNotPlayedActivity;
import com.hcpt.multileagues.adapters.ScheduleMatchsAdapter;
import com.hcpt.multileagues.adapters.SpinnerRoundAdapter;
import com.hcpt.multileagues.configs.Constants;
import com.hcpt.multileagues.configs.GlobalValue;
import com.hcpt.multileagues.configs.WebservicesConfigs;
import com.hcpt.multileagues.database.DatabaseUtility;
import com.hcpt.multileagues.interfaces.OnNotificationButtonClickListener;
import com.hcpt.multileagues.json.utils.ParserUtils;
import com.hcpt.multileagues.modelmanager.ModelManager;
import com.hcpt.multileagues.modelmanager.ModelManagerListener;
import com.hcpt.multileagues.network.NetworkUtility;
import com.hcpt.multileagues.objects.APIObj;
import com.hcpt.multileagues.objects.AlarmMatch;
import com.hcpt.multileagues.objects.MatchObj;
import com.hcpt.multileagues.objects.RoundObj;
import com.hcpt.multileagues.services.AlarmReceiver;
import com.hcpt.multileagues.utilities.DateTimeUtility;

import java.util.ArrayList;

public class ScheduleCLFragment extends Fragment {

    private static final String TAG = ScheduleCLFragment.class.getSimpleName();

    private View view;
    private Spinner mSpinRound;
    private SpinnerRoundAdapter mRoundAdapter;
    private ArrayList<RoundObj> mArrRound;
    private ScheduleMatchsAdapter mMatchAdapter;
    private ListView mLvMatch;
    private ArrayList<MatchObj> mArrMatch;

    private static MatchObj mMatchObj;

    public int currentRound = 0;

    private String roundId = "";
    private TextView lbl_no_data;

    private BroadcastReceiver broadcastReceiver;
    private IntentFilter filter;
    private AlarmMatch alarmMatchPrevious;
    private AlarmManager alarmManager;
    private PendingIntent pendingIntent;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Allow display option menu in this fragment.
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        view = inflater.inflate(R.layout.fragment_schedule_cl, container, false);

        initUI();
        mListener();
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        getActivity().unregisterReceiver(broadcastReceiver);
    }

    private void mListener() {
        broadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                if (intent.getAction().equals(Constants.REFRESH)) {
                    mMatchAdapter.notifyDataSetChanged();
                }
            }
        };
        filter = new IntentFilter();
        filter.addAction(Constants.REFRESH);
        getActivity().registerReceiver(broadcastReceiver, filter);
    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        // TODO Auto-generated method stub
        super.onPrepareOptionsMenu(menu);

        // Hide filter menu.
        MenuItem item = menu.findItem(R.id.action_filter);
        item.setVisible(false);
        menu.findItem(R.id.action_save).setVisible(false);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);

        menu.clear();
        inflater.inflate(R.menu.menu_main, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_refresh) {
            initMatchesByRound();
        }

        return super.onOptionsItemSelected(item);
    }

    // Init widget controls.
    private void initUI() {
        mLvMatch = (ListView) view.findViewById(R.id.lv_matchs);
        mSpinRound = (Spinner) view.findViewById(R.id.spin_duration);
        lbl_no_data = (TextView) view.findViewById(R.id.lbl_no_data);

        // Should call this method at the end of declaring UI
//        getCurrentRound();

        initRound();
        initControl();
    }

    private void initControl() {
        mSpinRound.setOnItemSelectedListener(onSpinRoundItemSelected);
        mLvMatch.setOnItemClickListener(onListItemClick);
    }

    // Set onclick for list view.
    OnItemClickListener onListItemClick = new OnItemClickListener() {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position,
                                long id) {
            // Get match which is selected.
            mMatchObj = mArrMatch.get(position);

            // MatchDetailActivity.matchId = mMatchObj.getmMatchId();
            MatchDetailActivity.currentMatch = mMatchObj;
            MatchDetailNotPlayedActivity.matchObj = mMatchObj;

            // Check time to set screen.
//            if (GlobalFunctions.checkMatchTime(mMatchObj) >= 0) {
            startActivity(new Intent(getActivity(),
                    MatchDetailActivity.class));
//            } else {
//                startActivity(new Intent(getActivity(),
//                        MatchDetailNotPlayedActivity.class));
//            }
        }
    };

    // Set on item selected for week spinner.
    OnItemSelectedListener onSpinRoundItemSelected = new OnItemSelectedListener() {

        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            // Get round id
            roundId = mArrRound.get(position).getId();

            // Get matches
            initMatchesByRound();
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {
            // Do nothing.
        }
    };

    // Init Rounds.
    private void initRound() {
        if (mArrRound == null) {
            mArrRound = new ArrayList<>();
        } else {
            mArrRound.clear();
        }

        if (NetworkUtility.getInstance(getActivity()).isNetworkAvailable()) {
            getRoundFromApi();
        } else {
            getRoundFromDB();
        }
    }

    private void getCurrentRound(String roundId) {
        ModelManager.getMatchesByRound(getActivity(), roundId, true, new ModelManagerListener() {
            @Override
            public void onError() {

            }

            @Override
            public void onSuccess(Object object) {
                String json = (String) object;
                ArrayList<MatchObj> arr = ParserUtils.parseSchedule(json);
                currentRound = Integer.parseInt(arr.get(0).getmRound());
            }
        });
    }

    private void getRoundFromApi() {
        ModelManager.getRoundsByLeague(getActivity(), false, new ModelManagerListener() {
            @Override
            public void onError() {
            }

            @Override
            public void onSuccess(Object object) {
                try {
                    String json = (String) object;
                    mArrRound = ParserUtils.parseRounds(getActivity(), json);
                    // Need to remove item 'All'
                    mArrRound.remove(0);
                    mRoundAdapter = new SpinnerRoundAdapter(getActivity(), mArrRound);
                    mSpinRound.setAdapter(mRoundAdapter);

                    // Choose current round for first time
                    for (int i = 1; i < mArrRound.size(); i++) {
                        if (mArrRound.get(i).isCurrent()) {
                            mSpinRound.setSelection(i);
                            getCurrentRound(mArrRound.get(i).getId());
                            break;
                        }
                    }
                    if (mArrRound.size() == 0) {
                        lbl_no_data.setVisibility(View.VISIBLE);
                    } else {
                        lbl_no_data.setVisibility(View.GONE);
                    }
                } catch (IndexOutOfBoundsException ex) {
                    ex.printStackTrace();
                }
            }
        });
    }

    private void getRoundFromDB() {
        APIObj apiInfos = DatabaseUtility.getResuftFromApi(getActivity(),
                WebservicesConfigs.URL_GET_ROUND + GlobalValue.leagueId);
        if (apiInfos != null) {
            try {
                String json = apiInfos.getmResult();
                mArrRound = ParserUtils.parseRounds(getActivity(), json);
                // Need to remove item 'All'
                mArrRound.remove(0);
                mRoundAdapter = new SpinnerRoundAdapter(getActivity(), mArrRound);
                mSpinRound.setAdapter(mRoundAdapter);

                // Choose current round for first time
                for (int i = 1; i < mArrRound.size(); i++) {
                    if (mArrRound.get(i).isCurrent()) {
                        mSpinRound.setSelection(i);
                        getCurrentRound(mArrRound.get(i).getId());
                        break;
                    }
                }
                if (mArrRound.size() == 0) {
                    lbl_no_data.setVisibility(View.VISIBLE);
                } else {
                    lbl_no_data.setVisibility(View.GONE);
                }
            } catch (IndexOutOfBoundsException ex) {
                ex.printStackTrace();
            }
        }
    }

    // Get schedule by round.
    private void initMatchesByRound() {
        if (NetworkUtility.getInstance(getActivity()).isNetworkAvailable()) {
            getMatchesByRoundFromAPI();
        } else {
            getMatchesByRoundFromDB();
        }
    }

    private void getMatchesByRoundFromDB() {
        APIObj apiInfos = DatabaseUtility.getResuftFromApi(getActivity(),
                (WebservicesConfigs.URL_GET_MATCHES_BY_ROUND + GlobalValue.leagueId + roundId));
        if (apiInfos != null) {
            String json = apiInfos.getmResult();
            setDataForListView(json);
        }
    }

    private void getMatchesByRoundFromAPI() {
        ModelManager.getMatchesByRound(getActivity(), roundId, true,
                new ModelManagerListener() {

                    @Override
                    public void onSuccess(Object object) {
                        String json = (String) object;
                        setDataForListView(json);
                    }

                    @Override
                    public void onError() {
                    }
                });
    }

    // Set adapter for listview matches.
    private void setDataForListView(String json) {
        if (mArrMatch == null) {
            mArrMatch = ParserUtils.parseSchedule(json);
            mMatchAdapter = new ScheduleMatchsAdapter(getActivity(), mArrMatch);
            mLvMatch.setAdapter(mMatchAdapter);
        } else {
            ArrayList<MatchObj> arrTemp = ParserUtils.parseSchedule(json);
            if (arrTemp != null) {
                mArrMatch.clear();
                mArrMatch.addAll(arrTemp);
                mMatchAdapter.notifyDataSetChanged();

                // Reset temp var
                arrTemp.clear();
            }
        }
        mMatchAdapter.setOnNotificationButtonClickListener(new OnNotificationButtonClickListener() {
            @Override
            public void onNotificationButtonClick(MatchObj matchObj) {
                mMatchObj = matchObj;
                MatchDetailActivity.currentMatch = mMatchObj;
                MatchDetailNotPlayed.currentMatch = mMatchObj;
                showDialogAlarm(matchObj.getmTime(), "", matchObj);
            }
        });
    }

    //================================================
    private void showDialogAlarm(final String times, final String titleReminder, final MatchObj mMatchObj) {
        alarmManager = (AlarmManager) getActivity().getSystemService(Context.ALARM_SERVICE);
        final Dialog dialog = new Dialog(getActivity());
        dialog.setTitle(DateTimeUtility.convertTimeStampToDate(mMatchObj.getmTime(), "HH:mm EEE, yyyy-MM-dd"));
        dialog.setContentView(R.layout.dialog_settings_reminder);

        // Init dialog's controls.
        Button btnOk = (Button) dialog.findViewById(R.id.btn_reminder_ok);
        final Button btnCancel = (Button) dialog
                .findViewById(R.id.btn_reminder_cancel);
        final CheckBox chkBefore15 = (CheckBox) dialog
                .findViewById(R.id.chk_reminder_15);
        final CheckBox chkBefore30 = (CheckBox) dialog
                .findViewById(R.id.chk_reminder_30);
        final CheckBox chkBefore60 = (CheckBox) dialog
                .findViewById(R.id.chk_reminder_60);
        final CheckBox chkOnTime = (CheckBox) dialog
                .findViewById(R.id.chk_reminder_on_time);

        DatabaseUtility databaseUtility = new DatabaseUtility();
        if (databaseUtility.checkAlarmSetted(mMatchObj.getmMatchId(), getActivity())) {
            alarmMatchPrevious = databaseUtility.getAlarmMatchById(mMatchObj.getmMatchId(), getActivity()).get(0);
            chkOnTime.setChecked(alarmMatchPrevious.isOnTime());
            chkBefore15.setChecked(alarmMatchPrevious.isBefore15Min());
            chkBefore60.setChecked(alarmMatchPrevious.isBefore60Min());
            chkBefore30.setChecked(alarmMatchPrevious.isBefore30Min());
            if (alarmMatchPrevious.isOnTime() || alarmMatchPrevious.isBefore15Min() || alarmMatchPrevious.isBefore30Min() || alarmMatchPrevious.isBefore60Min()) {
                btnCancel.setVisibility(View.VISIBLE);
            }
        } else {
            String match_title = mMatchObj.getmHomeName() + " - " + mMatchObj.getmAwayName();
            alarmMatchPrevious = new AlarmMatch(mMatchObj.getmMatchId(), match_title, mMatchObj.getmTime(), false, false, false, false);
        }

        dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {

            @SuppressWarnings("deprecation")
            @Override
            public void onDismiss(DialogInterface dialog) {
            }
        });

        btnOk.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                dialog.dismiss();
                AlarmMatch item = new AlarmMatch();
                item.setTime(mMatchObj.getmTime());
                item.setMatchId(mMatchObj.getmMatchId());
                item.setMatchTitle(mMatchObj.getmHomeName() + " - " + mMatchObj.getmAwayName());
                item.setOnTime(chkOnTime.isChecked());
                item.setBefore15Min(chkBefore15.isChecked());
                item.setBefore30Min(chkBefore30.isChecked());
                item.setBefore60Min(chkBefore60.isChecked());
                if (chkOnTime.isChecked() || chkBefore15.isChecked() || chkBefore30.isChecked() || chkBefore60.isChecked()) {
                    saveSettingToDatabase(item);
                } else {
                    // remove from database
                    removeSettingFromDatabase(mMatchObj.getmMatchId());
                }
                setOrCancelAlarm(item, alarmMatchPrevious);

            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();
    }

    private void saveSettingToDatabase(AlarmMatch alarmMatch) {
        DatabaseUtility databaseUtility = new DatabaseUtility();
        databaseUtility.insertAlarmMatch(alarmMatch, getActivity());
        requestRefreshData();
    }

    private void removeSettingFromDatabase(String matchId) {
        DatabaseUtility databaseUtility = new DatabaseUtility();
        databaseUtility.deleteAlarmMatch(matchId, getActivity());
        requestRefreshData();
    }

    void requestRefreshData() {
        Intent intent = new Intent();
        intent.setAction(Constants.REFRESH);
        getActivity().sendBroadcast(intent);
    }

    //New notification Local
    private void setOrCancelAlarm(AlarmMatch nowAlarmMatch, AlarmMatch previosAlarmMatch) {
        Long TIME_ON_TIME = 0L;
        Long TIME_15_MINUTES = 15 * 60 * 1000L;
        Long TIME_30_MINUTES = 30 * 60 * 1000L;
        Long TIME_60_MINUTES = 60 * 60 * 1000L;

        if (previosAlarmMatch.isOnTime()) {
            if (!nowAlarmMatch.isOnTime()) cancelAlarm(1);
        } else {
            if (nowAlarmMatch.isOnTime()) setAlarm(TIME_ON_TIME, 1);
        }
        if (previosAlarmMatch.isBefore15Min()) {
            if (!nowAlarmMatch.isBefore15Min()) cancelAlarm(2);
        } else {
            if (nowAlarmMatch.isBefore15Min()) setAlarm(TIME_15_MINUTES, 2);
        }

        if (previosAlarmMatch.isBefore30Min()) {
            if (!nowAlarmMatch.isBefore30Min()) cancelAlarm(3);
        } else {
            if (nowAlarmMatch.isBefore30Min()) setAlarm(TIME_30_MINUTES, 3);
        }

        if (previosAlarmMatch.isBefore60Min()) {
            if (!nowAlarmMatch.isBefore60Min()) cancelAlarm(4);
        } else {
            if (nowAlarmMatch.isBefore60Min()) setAlarm(TIME_60_MINUTES, 4);
        }


    }

    private void setAlarm(Long timeToSet, int requestCode) {

        Intent intent = new Intent(getActivity(), AlarmReceiver.class);
        intent.putExtra(Constants.KEY_TITLE_ALARM, alarmMatchPrevious.getMatchTitle());
        Log.e(TAG, "setAlarm: " + DateTimeUtility.convertTimeStampToHours(alarmMatchPrevious.getTime()));
        intent.putExtra(Constants.KEY_MESSAE_ALARM, DateTimeUtility.convertTimeStampToHours(alarmMatchPrevious.getTime()));
        pendingIntent = PendingIntent.getBroadcast(getActivity(), (Integer.parseInt(alarmMatchPrevious.getMatchId()) + requestCode), intent, PendingIntent.FLAG_ONE_SHOT);
        Long timeOfMatch = Long.parseLong(alarmMatchPrevious.getTime());

        alarmManager.set(AlarmManager.RTC_WAKEUP, timeOfMatch * 1000L - timeToSet, pendingIntent);
    }

    private void cancelAlarm(int requestCode) {
        Intent intent = new Intent(getActivity(), AlarmReceiver.class);
        intent.putExtra(Constants.KEY_TITLE_ALARM, alarmMatchPrevious.getMatchTitle());
        Log.d("ALARM", alarmMatchPrevious.getTime());
        intent.putExtra(Constants.KEY_MESSAE_ALARM, DateTimeUtility.convertTimeStampToHours(alarmMatchPrevious.getTime()));
        pendingIntent = PendingIntent.getBroadcast(getActivity(), (Integer.parseInt(alarmMatchPrevious.getMatchId()) + requestCode), intent, PendingIntent.FLAG_ONE_SHOT);
        alarmManager.cancel(pendingIntent);
    }
}
