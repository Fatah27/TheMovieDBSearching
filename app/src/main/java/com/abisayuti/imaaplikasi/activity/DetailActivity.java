package com.abisayuti.imaaplikasi.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.abisayuti.imaaplikasi.R;
import com.abisayuti.imaaplikasi.model.ResultsItem;
import com.bumptech.glide.Glide;

public class DetailActivity extends AppCompatActivity {

    TextView titleMovie;
    ImageView posterMovie;

    public static final String EXTRA_OBJECT = "OBJECT";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        titleMovie = findViewById(R.id.titleMovie);
        posterMovie = findViewById(R.id.imagePoster);



        ResultsItem get = getIntent().getParcelableExtra(EXTRA_OBJECT);
        Glide.with(DetailActivity.this)
                .load(get.getPosterPath())
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher)
                .into(posterMovie);
        titleMovie.setText(get.getTitle());

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setTitle(titleMovie.getText().toString());


    }
}
