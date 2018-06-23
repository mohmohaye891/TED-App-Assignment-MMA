package com.padcmyanmar.ted.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.padcmyanmar.ted.R;
import com.padcmyanmar.ted.data.models.NewestModel;
import com.padcmyanmar.ted.data.vos.TalkVO;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NewestDetailActivity extends BaseActivity {

    @BindView(R.id.iv_newest_backdrop)
    ImageView ivNewestBackdrop;

    @BindView(R.id.tv_title_name)
    TextView tvTitleName;

    @BindView(R.id.tv_title)
    TextView tvTitle;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newest_details);
        ButterKnife.bind(this, this);

        String newestId = getIntent().getStringExtra("newestId");

        Log.d("NewestDetailActivity", "newestId : " + newestId);

        TalkVO talks = NewestModel.getObjectInstance().getTalkById(newestId);
        bindData(talks);
    }

    private void bindData(TalkVO talks) {
        tvTitleName.setText(talks.getTitle());
        //tvTitle.setText(talks.getTitle());


        Glide.with(ivNewestBackdrop.getContext())
                .load(talks.getImageUrl())
                .into(ivNewestBackdrop);
    }
}
