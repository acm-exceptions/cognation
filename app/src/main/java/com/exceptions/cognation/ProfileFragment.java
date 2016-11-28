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
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.exceptions.cognation.utils.TouchEffect;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ProfileFragment extends Fragment {

    private List<String> list;
    private LinearLayout linearLayout;
    private ImageView iv_avatar;
    private ImageView iv_takephoto;
    private CustomDialog customDialog;

    public ProfileFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        linearLayout = (LinearLayout) view.findViewById(R.id.ll_profile_2);

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.setMargins(0, 15, 0, 15);

        View v1 = LayoutInflater.from(getActivity().getApplicationContext()).inflate(R.layout.fragment_profile_lisititem, null);
        ((TextView) v1.findViewById(R.id.tv_profilelistitem)).setText("Edit Profile");
        ((ImageView) v1.findViewById(R.id.iv_profilelistitem)).setImageResource(R.drawable.ic_one);
        v1.setClickable(true);
        v1.setLayoutParams(params);
        linearLayout.addView(v1);
        v1.setOnTouchListener(new TouchEffect());

        View v2 = LayoutInflater.from(getActivity().getApplicationContext()).inflate(R.layout.fragment_profile_lisititem, null);
        ((TextView) v2.findViewById(R.id.tv_profilelistitem)).setText("Work Schedule");
        ((ImageView) v2.findViewById(R.id.iv_profilelistitem)).setImageResource(R.drawable.ic_two);
        v2.setClickable(true);
        linearLayout.addView(v2);
        v2.setOnTouchListener(new TouchEffect());
        v2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), WorkSchedule.class));
            }
        });

        View v3 = LayoutInflater.from(getActivity().getApplicationContext()).inflate(R.layout.fragment_profile_lisititem, null);
        ((TextView) v3.findViewById(R.id.tv_profilelistitem)).setText("Leave Management");
        ((ImageView) v3.findViewById(R.id.iv_profilelistitem)).setImageResource(R.drawable.s06);
        v3.setClickable(true);
        linearLayout.addView(v3);
        v3.setOnTouchListener(new TouchEffect());
        v3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), LeaveManagementActivity.class));
            }
        });

        View v4 = LayoutInflater.from(getActivity().getApplicationContext()).inflate(R.layout.fragment_profile_lisititem, null);
        ((TextView) v4.findViewById(R.id.tv_profilelistitem)).setText("Overtime Management");
        ((ImageView) v1.findViewById(R.id.iv_profilelistitem)).setImageResource(R.drawable.s05);
        v4.setClickable(true);
        linearLayout.addView(v4);
        v4.setOnTouchListener(new TouchEffect());

        View v5 = LayoutInflater.from(getActivity().getApplicationContext()).inflate(R.layout.fragment_profile_lisititem, null);
        ((TextView) v5.findViewById(R.id.tv_profilelistitem)).setText("Payroll Management");
        ((ImageView) v1.findViewById(R.id.iv_profilelistitem)).setImageResource(R.drawable.s04);
        v5.setClickable(true);
        linearLayout.addView(v5);
        v5.setOnTouchListener(new TouchEffect());
        v5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), PayrollManagementActivity.class));
            }
        });

        View v6 = LayoutInflater.from(getActivity().getApplicationContext()).inflate(R.layout.fragment_profile_lisititem, null);
        ((TextView) v6.findViewById(R.id.tv_profilelistitem)).setText("Incident Report");
        ((ImageView) v1.findViewById(R.id.iv_profilelistitem)).setImageResource(R.drawable.s03);
        v6.setClickable(true);
        linearLayout.addView(v6);
        v6.setOnTouchListener(new TouchEffect());

        LinearLayout.LayoutParams params1 = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params1.setMargins(0, 15, 0, 0);

        View v7 = LayoutInflater.from(getActivity().getApplicationContext()).inflate(R.layout.fragment_profile_lisititem, null);
        ((TextView) v7.findViewById(R.id.tv_profilelistitem)).setText("Company Policies");
        ((ImageView) v1.findViewById(R.id.iv_profilelistitem)).setImageResource(R.drawable.s02);
        v7.setClickable(true);
        v7.setLayoutParams(params1);
        linearLayout.addView(v7);
        v7.setOnTouchListener(new TouchEffect());

        View v8 = LayoutInflater.from(getActivity().getApplicationContext()).inflate(R.layout.fragment_profile_lisititem, null);
        ((TextView) v8.findViewById(R.id.tv_profilelistitem)).setText("Terms and Conditions");
        v8.setClickable(true);
        linearLayout.addView(v8);
        v8.setOnTouchListener(new TouchEffect());

        View v9 = LayoutInflater.from(getActivity().getApplicationContext()).inflate(R.layout.fragment_profile_lisititem, null);
        ((TextView) v9.findViewById(R.id.tv_profilelistitem)).setText("About Cognation");
        v9.setClickable(true);
        linearLayout.addView(v9);
        v9.setOnTouchListener(new TouchEffect());

        View v10 = LayoutInflater.from(getActivity().getApplicationContext()).inflate(R.layout.fragment_profile_lisititem, null);
        ((TextView) v10.findViewById(R.id.tv_profilelistitem)).setText("Log Out");
        v10.setClickable(true);
        v10.setLayoutParams(params);
        linearLayout.addView(v10);
        v10.setOnTouchListener(new TouchEffect());

        v10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(getActivity(), LoginActivity.class));
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
}
