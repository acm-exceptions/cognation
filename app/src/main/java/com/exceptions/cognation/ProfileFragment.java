package com.exceptions.cognation;

import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ProfileFragment extends Fragment {

    private ListView mListview;
    private List<String> list;
    private ImageView iv_avatar;
    private ImageView iv_takephoto;
    private CustomDialog customDialog;

    public ProfileFragment() {}

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.fragment_profile, container, false);

            list = new ArrayList(Arrays.asList("Edit Profile", "Log Out"));

            mListview = (ListView) view.findViewById(R.id.lv_profile);
            mListview.setAdapter(new CustomListAdapter(getActivity().getApplicationContext(), list));
            mListview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    if (i == 1) {
                        FirebaseAuth.getInstance().signOut();
                        startActivity(new Intent(getActivity(), LoginActivity.class));
                        getActivity().finish();
                    }
                }
            });

            iv_avatar = (ImageView) view.findViewById(R.id.iv_avatar);
            iv_takephoto = (ImageView) view.findViewById(R.id.iv_takePhoto);
            customDialog = new CustomDialog();

            iv_avatar.setImageBitmap(Utility.getCroppedBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.icon_avatar)));
            iv_takephoto.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    customDialog.show(getActivity().getFragmentManager(), null);
                }
            });

            return view;
        }

    private class CustomListAdapter extends BaseAdapter {

        private Context context;
        private List<String> list;

        public CustomListAdapter(Context context, List<String> list) {
            super();
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
            View v = LayoutInflater.from(context).inflate(R.layout.fragment_profile_lisititem, null);
            ((TextView) v.findViewById(R.id.tv_listitem)).setText(list.get(i));
            return v;
        }
    }
}
