package com.exceptions.cognation;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class NewsfeedFragment extends Fragment {

    private SwipeRefreshLayout mSwipeRefreshLayout;
    private ListView mListView;

    public NewsfeedFragment() {}


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_newsfeed, container, false);

        mSwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.srl_newsfeed);
        mListView = (ListView) view.findViewById(R.id.lv_newsfeed);

        final SwipeRefreshLayout.OnRefreshListener onRefreshListener = new SwipeRefreshLayout.OnRefreshListener()
        {
            @Override
            public void onRefresh()
            {
                //fetchData();
            }
        };

        mSwipeRefreshLayout.setOnRefreshListener(onRefreshListener);

        mSwipeRefreshLayout.post(new Runnable()
        {
            @Override
            public void run()
            {
                mSwipeRefreshLayout.setRefreshing(true);
                onRefreshListener.onRefresh();
            }
        });

        mListView.setAdapter(new CustomListAdapter(getActivity().getApplicationContext()));

        return view;
    }

    private class CustomListAdapter extends BaseAdapter {

        private Context context;

        public CustomListAdapter(Context context) {
            this.context = context;
        }

        @Override
        public int getCount() {
            return 0;
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            return null;
        }
    }

}
