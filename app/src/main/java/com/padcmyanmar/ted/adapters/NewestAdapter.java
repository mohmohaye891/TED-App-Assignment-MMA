package com.padcmyanmar.ted.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.padcmyanmar.ted.R;
import com.padcmyanmar.ted.data.vos.TalkVO;
import com.padcmyanmar.ted.delegates.NewestDelegate;
import com.padcmyanmar.ted.viewholders.NewestViewHolder;

import java.util.ArrayList;
import java.util.List;

public class NewestAdapter extends RecyclerView.Adapter<NewestViewHolder> {

    private NewestDelegate mNewestDelegate;

    private List<TalkVO> mTalkList;

    public NewestAdapter(NewestDelegate NewestDelegate) {
        this.mNewestDelegate = NewestDelegate;
         mTalkList = new ArrayList<>();
    }

    @NonNull
    @Override
    public NewestViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.view_holder_talk, parent, false);
        return new NewestViewHolder(view, mNewestDelegate);
    }

    @Override
    public void onBindViewHolder(@NonNull NewestViewHolder holder, int position) {
        holder.setNewestData(mTalkList.get(position));
    }


    @Override
    public int getItemCount() {
        return mTalkList.size();
    }

    public void setNewestList(List<TalkVO> newestList) {
        mTalkList = newestList;
        notifyDataSetChanged();
    }
}
