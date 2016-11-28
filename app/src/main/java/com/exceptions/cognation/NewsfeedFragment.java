package com.exceptions.cognation;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.text.format.DateUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.exceptions.cognation.model.Feed;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class NewsfeedFragment extends Fragment {

    private DatabaseReference mDatabaseReference;
    private ListView mListView;

    public NewsfeedFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_newsfeed, container, false);

        mListView = (ListView) view.findViewById(R.id.lv_newsfeed);
        mDatabaseReference = FirebaseDatabase.getInstance().getReference("newsfeed");
        mDatabaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                ArrayList<Feed> list = new ArrayList();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Feed feed = snapshot.getValue(Feed.class);
                    list.add(feed);
                }
                mListView.setAdapter(new CustomListAdapter(getActivity().getApplicationContext(), list));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        return view;
    }

    private class CustomListAdapter extends BaseAdapter {

        private Context context;
        private List<Feed> list;

        public CustomListAdapter(Context context, List<Feed> list) {
            this.context = context;
            this.list = list;
        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int i) {
            return list.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(final int i, View view, ViewGroup viewGroup) {
            View v = LayoutInflater.from(context).inflate(R.layout.fragment_newsfeed_listitem, null);

            Picasso.with(context).load(list.get(i).getPhotoUrl()).into(((ImageView) v.findViewById(R.id.iv_newsfeed)));
            ((TextView) v.findViewById(R.id.tv_newsfeed_2)).setText(list.get(i).getTitle());
            ((TextView) v.findViewById(R.id.tv_newsfeed_3)).setText(list.get(i).getShortDetails());
            ((TextView) v.findViewById(R.id.tv_newsfeed_1)).setText(DateUtils.getRelativeDateTimeString(context, list.get(i).getDate().getTime(), DateUtils.SECOND_IN_MILLIS, DateUtils.DAY_IN_MILLIS, 0));

            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(getActivity(), AnnouncementActivity.class);
                    intent.putExtra("title", list.get(i).getTitle());
                    intent.putExtra("photourl", list.get(i).getPhotoUrl());
                    intent.putExtra("details", list.get(i).getShortDetails());
                    startActivity(intent);
                }
            });

            return v;
        }
    }
}
