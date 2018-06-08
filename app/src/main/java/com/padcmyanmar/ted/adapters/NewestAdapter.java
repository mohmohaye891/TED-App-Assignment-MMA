package com.padcmyanmar.ted.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.padcmyanmar.ted.R;
import com.padcmyanmar.ted.delegates.NewestDelegate;
import com.padcmyanmar.ted.viewholders.NewestViewHolder;

public class NewestAdapter extends RecyclerView.Adapter {

    private NewestDelegate mNewestDelegate;

    public NewestAdapter(NewestDelegate newestDelegate) {
        mNewestDelegate = newestDelegate;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.view_holder_talk, parent, false);
        return new NewestViewHolder(view, mNewestDelegate);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 15;
    }
}
