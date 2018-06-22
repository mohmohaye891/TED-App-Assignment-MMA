package com.padcmyanmar.ted.viewholders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.padcmyanmar.ted.R;
import com.padcmyanmar.ted.data.vos.TalkVO;
import com.padcmyanmar.ted.delegates.NewestDelegate;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NewestViewHolder extends RecyclerView.ViewHolder {

   private NewestDelegate mnewestDelegate;
   private TalkVO mTalk;

   @BindView(R.id.iv_newest)
    ImageView ivNewest;

   @BindView(R.id.tv_talker_name)
    TextView tvTalkerName;

    @BindView(R.id.tv_talk_title)
    TextView tvTalkTitle;

    @BindView(R.id.tv_time)
    TextView tvTime;

    public NewestViewHolder(View itemView, NewestDelegate newestDelegate) {

        super(itemView);
        ButterKnife.bind(this, itemView);

        mnewestDelegate = newestDelegate;

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mnewestDelegate.onTapImage(mTalk);
            }
        });
    }

    public void setNewestData(TalkVO talk){
        mTalk = talk;
        tvTalkerName.setText(talk.getSpeaker().getName());
        tvTalkTitle.setText(talk.getTitle());
        tvTime.setText(talk.getDurationInSec());

        Glide.with(ivNewest.getContext())
                .load(talk.getImageUrl())
                .into(ivNewest);
    }
}
