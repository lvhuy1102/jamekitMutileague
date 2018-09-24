package com.hcpt.multileagues.fragments;


import android.app.AlertDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.hcpt.multileagues.Listener.AdapterListener;
import com.hcpt.multileagues.R;
import com.hcpt.multileagues.activities.MatchDetailActivity;
import com.hcpt.multileagues.adapters.MessageAdapter;
import com.hcpt.multileagues.configs.AppUtils;
import com.hcpt.multileagues.configs.Constants;
import com.hcpt.multileagues.configs.FruitySharedPreferences;
import com.hcpt.multileagues.network.NetworkUtility;
import com.hcpt.multileagues.objects.MatchObj;
import com.hcpt.multileagues.objects.MessageObj;

import java.util.ArrayList;
import java.util.UUID;

public class ChatRoomFragment extends Fragment implements View.OnClickListener, AdapterListener.MessageAdapterListener {

    private static final String ROOM_ID = "Room";

    Firebase ref;
    private MatchObj mMatchObj;
    private String mMatchId;
    private EditText edtChatBox;
    private ImageView btnSend, btnRename;
    private ListView lsvMessage;
    private MessageAdapter adapter;
    private ArrayList<MessageObj> arlMessage = new ArrayList<>();
    private TextView btnPrevious;


    private boolean isViewShown = false;
    private boolean isBackground = false;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mMatchObj = MatchDetailActivity.currentMatch;
        mMatchId = MatchDetailActivity.currentMatch.getmMatchId();

        // Allow display option menu in this fragment.
        setHasOptionsMenu(true);

        ref = new Firebase(Constants.FIREBASE_URL);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_blank, container, false);

        init(v);
        initControl();

        return v;
    }

    public void init(View v) {
        edtChatBox = (EditText) v.findViewById(R.id.edtChatBox);
        btnPrevious = (TextView) v.findViewById(R.id.btnPrevious);
        btnPrevious.setText(AppUtils.underLine(btnPrevious.getText().toString()));
        btnSend = (ImageView) v.findViewById(R.id.btnSend);
        btnRename = (ImageView) v.findViewById(R.id.btnRename);
        lsvMessage = (ListView) v.findViewById(R.id.lsvMessage);
        btnPrevious.setVisibility(View.GONE);

        btnSend.setOnClickListener(this);
        btnRename.setOnClickListener(this);
        btnPrevious.setOnClickListener(this);
    }


    public void initControl() {
        adapter = new MessageAdapter(arlMessage, getActivity(), mMatchId, ChatRoomFragment.this);
        lsvMessage.setAdapter(adapter);
        lsvMessage.smoothScrollToPosition(adapter.getCount());
        lsvMessage.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView absListView, int i) {

            }

            @Override
            public void onScroll(AbsListView absListView, int i, int i1, int i2) {
                if (i == 0) {
                    if (arlMessage.size() < 46) {
                        btnPrevious.setVisibility(View.GONE);
                    } else {
                        btnPrevious.setVisibility(View.VISIBLE);
                    }
                } else {
                    btnPrevious.setVisibility(View.GONE);
                }
            }
        });

    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        menu.clear();
        inflater.inflate(R.menu.menu_main, menu);
        menu.findItem(R.id.action_save).setVisible(false);


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_refresh) {
            onResume();
        }

        return super.onOptionsItemSelected(item);
    }

    public void showRenameDialog(String title) {
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View v = inflater.inflate(R.layout.item_dialog, null, false);

        String username = FruitySharedPreferences.getInstance(getContext()).getStringValue(Constants.USERNAME);

        TextView btnCancel = (TextView) v.findViewById(R.id.btnCancel);
        TextView btnOk = (TextView) v.findViewById(R.id.btnOk);
        TextView tvTitle = (TextView) v.findViewById(R.id.tvTitle);
        final EditText edtRename = (EditText) v.findViewById(R.id.edtRename);
        edtRename.setText(username);
        tvTitle.setText(title);
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(v);
        if (title.equals(getString(R.string.create_name))) {
            btnOk.setText(getString(R.string.text_btn_ok));
            btnCancel.setVisibility(View.GONE);
            builder.setCancelable(false);
        } else {
            btnOk.setText(getString(R.string.text_btn_change));
            btnCancel.setVisibility(View.VISIBLE);
            builder.setCancelable(true);
        }

        final AlertDialog alertDialog = builder.create();

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
            }
        });

        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                boolean isWhitespace = edtRename.getText().toString().matches("^\\s*$");

                if (edtRename.getText().toString().equals("")) {
                    Toast.makeText(getActivity(), "Username can't be blank", Toast.LENGTH_SHORT).show();
                } else if (isWhitespace) {
                    Toast.makeText(getActivity(), "Username can't be space", Toast.LENGTH_SHORT).show();
                } else {
                    FruitySharedPreferences.getInstance(getActivity()).putStringValue(Constants.USERNAME, edtRename.getText().toString());
                    alertDialog.dismiss();
                }
            }
        });


        alertDialog.show();
    }

    public void sendMessage() {
        if (validateMessage()) {
            MessageObj message = new MessageObj();

            message.setContent(edtChatBox.getText().toString());
            message.setDatepost(String.valueOf(System.currentTimeMillis() / 1000));
            message.setUserID(FruitySharedPreferences.getInstance(getActivity()).getStringValue(Constants.USERID));
            message.setUserName(FruitySharedPreferences.getInstance(getActivity()).getStringValue(Constants.USERNAME));
            ref.child(Constants.FIREBASE_ROOM).child(mMatchId).child(Constants.FIREBASE_USER).push().setValue(message);
            edtChatBox.setText("");
            adapter.notifyDataSetChanged();
        }
    }

    public boolean validateMessage() {
        if ("".equals(edtChatBox.getText().toString())) {
            return false;
        } else {
            return true;
        }
    }


    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        if (isVisibleToUser) {
            if (NetworkUtility.getInstance(getActivity()).isNetworkAvailable()) {
                String userID = FruitySharedPreferences.getInstance(getActivity()).getStringValue(Constants.USERID);
                final String userName = FruitySharedPreferences.getInstance(getActivity()).getStringValue(Constants.USERNAME);
                if ("".equals(userID)) {
                    //Create UserID
                    String userIDs = UUID.randomUUID().toString();
                    FruitySharedPreferences.getInstance(getActivity()).putStringValue(Constants.USERID,
                            userIDs);
                    showRenameDialog(getString(R.string.create_name));
                } else {
                    //Create new Name
                    if ("".equals(userName)) {
                        showRenameDialog(getString(R.string.create_name));
                    }
                }

                //Set up RoomChat
                ref.child(Constants.FIREBASE_ROOM).child(mMatchId).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if (!dataSnapshot.hasChildren()) {
                            ref.child(Constants.FIREBASE_ROOM).child(mMatchId).child(Constants.FIREBASE_MATCHNAME)
                                    .setValue(mMatchObj.getmAwayName() + "-" + mMatchObj.getmHomeName());
                        }
                    }

                    @Override
                    public void onCancelled(FirebaseError firebaseError) {

                    }
                });
            } else {
                Toast.makeText(getActivity(), R.string.no_network, Toast.LENGTH_SHORT).show();
            }
        }

    }

    @Override
    public void onResume() {
        super.onResume();
        if (isBackground) {
            adapter = new MessageAdapter(arlMessage, getActivity(), mMatchId, ChatRoomFragment.this);
            lsvMessage.setAdapter(adapter);
            isBackground = false;
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        isBackground = true;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnRename:
                //Change Name
                showRenameDialog(getString(R.string.change_name));
                break;
            case R.id.btnSend:
                sendMessage();
                lsvMessage.smoothScrollToPosition(adapter.getCount());
                break;
            case R.id.btnPrevious:
                if (adapter.loadMore) {
                    adapter.getMore();
                }
                break;
        }
    }


    @Override
    public void setListtoBottom(int postion) {
        lsvMessage.setSelection(postion);
    }
}
