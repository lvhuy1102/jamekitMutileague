package com.hcpt.multileagues.adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.Query;
import com.firebase.client.ValueEventListener;
import com.hcpt.multileagues.Listener.AdapterListener;
import com.hcpt.multileagues.R;
import com.hcpt.multileagues.configs.Constants;
import com.hcpt.multileagues.configs.FruitySharedPreferences;
import com.hcpt.multileagues.objects.MessageObj;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Map;

/**
 * Created by GL62 on 7/5/2017.
 */

public class MessageAdapter extends BaseAdapter {
    private ArrayList<MessageObj> arlMessage;
    private Activity activity;
    Firebase ref;
    private String mMatchId;
    private AdapterListener.MessageAdapterListener listener;
    private String lastKey = "";
    public boolean loadMore = true;

    public MessageAdapter(ArrayList<MessageObj> _arlMessage, Activity _activity, String _mMatchId, AdapterListener.MessageAdapterListener _listener) {
        this.arlMessage = _arlMessage;
        this.activity = _activity;
        this.mMatchId = _mMatchId;
        ref = new Firebase(Constants.FIREBASE_URL);
        this.listener = _listener;
        getData();
    }

    public void getMore() {
        final ArrayList<MessageObj> arr = new ArrayList<>();
        Query query = ref.child(Constants.FIREBASE_ROOM).child(mMatchId).child(Constants.FIREBASE_USER).orderByKey().endAt(lastKey).limitToLast(50);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.hasChildren()) {
                    for (DataSnapshot datas : dataSnapshot.getChildren()) {
                        MessageObj messengerFirebase = new MessageObj();
                        Map<String, Object> map = (Map<String, Object>) datas.getValue();
                        if (map != null) {
                            messengerFirebase.setUserName(map.get("userName") + "");
                            messengerFirebase.setUserID(map.get("userID") + "");
                            messengerFirebase.setDatepost(map.get("datepost") + "");
                            messengerFirebase.setContent(map.get("content") + "");
                            arr.add(0, messengerFirebase);
                            if (arr.size() == 1) {
                                lastKey = datas.getKey();
                            }
                        }
                    }
                    if (arr.size() < 50) {
                        loadMore = false;
                        Toast.makeText(activity, "No more message", Toast.LENGTH_SHORT).show();
                    } else {
                        loadMore = true;
                    }
                    arr.remove(0);
                    setListTobottom(arr.size());
                    Collections.reverse(arr);
                    arlMessage.addAll(0, arr);
                    notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
    }

    private void getData() {
        arlMessage.clear();
        Query query = ref.child(Constants.FIREBASE_ROOM).child(mMatchId).child(Constants.FIREBASE_USER).limitToLast(50);
        query.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                if (dataSnapshot.hasChildren()) {
                    try{
                        MessageObj messageObj = dataSnapshot.getValue(MessageObj.class);
                        if (messageObj != null) {
                            arlMessage.add(messageObj);
                            notifyDataSetChanged();
                        }
                        if (arlMessage.size() == 1) {
                            lastKey = dataSnapshot.getKey();
                        }
                    }catch (Exception e){

                    }
                }
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                if (dataSnapshot.hasChildren()) {
                    MessageObj messageObj = dataSnapshot.getValue(MessageObj.class);
                    if (messageObj != null) {
                        arlMessage.add(messageObj);
                        notifyDataSetChanged();
                    }
                    if (arlMessage.size() == 1) {
                        lastKey = dataSnapshot.getKey();
                    }
                    arlMessage.size();
                }
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
    }


    @Override
    public int getCount() {
        return arlMessage.size();
    }

    @Override
    public Object getItem(int i) {
        return arlMessage.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        final HolderView holder;
        if (view == null) {
            view = inflater.inflate(R.layout.item_chatbox, null);
            holder = new HolderView();
            view.setTag(holder);
        } else {
            holder = (HolderView) view.getTag();
        }
        holder.tvMessage = (TextView) view.findViewById(R.id.tvMessage);
        holder.tvUserName = (TextView) view.findViewById(R.id.tvUserName);
        holder.tvDateTime = (TextView) view.findViewById(R.id.tvDateTime);

        MessageObj messageObj = arlMessage.get(i);
        if (messageObj != null) {
            holder.tvMessage.setText(messageObj.getContent());
            if (messageObj.getUserID().equals(FruitySharedPreferences.getInstance(activity).getStringValue(Constants.USERID))) {
                holder.tvUserName.setTextColor(activity.getResources().getColor(R.color.red));
                holder.tvUserName.setText(messageObj.getUserName());
            } else {
                holder.tvUserName.setText(messageObj.getUserName());
                holder.tvUserName.setTextColor(activity.getResources().getColor(R.color.black));
            }
        }

        //setDateTime
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        long longVal = Long.valueOf(messageObj.getDatepost()) * 1000;
        Date dateSend = new Date(longVal);
        holder.tvDateTime.setText(sdf.format(dateSend));

        return view;
    }

    public void setListTobottom(int postion) {
        listener.setListtoBottom(postion);
    }

    public class HolderView {
        TextView tvUserName, tvMessage, tvDateTime;
    }
}
