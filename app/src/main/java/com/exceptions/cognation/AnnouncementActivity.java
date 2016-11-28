package com.exceptions.cognation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class AnnouncementActivity extends AppCompatActivity {

    private ImageView imageView;
    private TextView textViewTitle;
    private TextView textViewDesc;

    public AnnouncementActivity() {}

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_announcement);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        imageView = (ImageView) findViewById(R.id.iv_announcement);
        textViewTitle = (TextView) findViewById(R.id.tv_announcement_title);
        textViewDesc = (TextView) findViewById(R.id.tv_announcement_description);

        String title = getIntent().getExtras().getString("title");
        String photourl = getIntent().getExtras().getString("photourl");
        String details = getIntent().getExtras().getString("details");

        Picasso.with(AnnouncementActivity.this).load(photourl).into(imageView);
        textViewTitle.setText(title);
        textViewDesc.setText(details + " Lorem impsum ienfkjebuifjebiuf feufh ejwbf ueuif eh  e fuhwi efbu e fjbeu fbe gfu ebhf geu fbue gfui geyufg yueg fyudsjnvjds vdsd edf e f e fe f e fe fu heh fbh rg b rhfv ye hfj evhyf hej fbh egf jeb hfg eub fhe gfh beh fg ehb fhe hjf be jfb je hfj e fbe j fbe huf heu hfe.");
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
