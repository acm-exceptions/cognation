package com.exceptions.cognation;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class NotificationActivity extends AppCompatActivity {

    private ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("Notifications");

        List<Notif> list = new ArrayList();

        for (int i = 0; i < 25; i++) {
            list.add(new Notif("MARCOS PA RIN MGA ULOL sent you a message.", 300));
        }

        mListView = (ListView) findViewById(R.id.lv_notification);
        mListView.setAdapter(new CustomListAdapter(NotificationActivity.this, list));
    }

    private class CustomListAdapter extends BaseAdapter {

        private Context context;
        private List<Notif> list;

        public CustomListAdapter(Context context, List<Notif> list) {
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
        public View getView(int i, View view, ViewGroup viewGroup) {
            View v = LayoutInflater.from(context).inflate(R.layout.activity_notification_listitem, null);
            ((TextView) v.findViewById(R.id.tv_notificationlistitem_description)).setText(list.get(i).description);

            String str = "";

            if (list.get(i).secs < 60) {
                str = list.get(i) + " seconds ago";
            } else if (list.get(i).secs < 3600) {
                str = (list.get(i).secs / 60) + " minutes ago";
            } else if (list.get(i).secs < 216000) {
                str = (list.get(i).secs / 3600) + " hours ago";
            } else {
                str = (list.get(i).secs / 216000) + " days ago";
            }

            ((TextView) v.findViewById(R.id.tv_notificationlistitem_timestamp)).setText(str);
            Picasso.with(context).load("https://www.techinasia.com/assets/images/profile/icon-defaultprofile.png").into(((ImageView) v.findViewById(R.id.iv_notificationlistitem)));
            return v;
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
