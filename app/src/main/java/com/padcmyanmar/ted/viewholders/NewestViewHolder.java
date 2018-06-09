package com.padcmyanmar.ted.viewholders;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.padcmyanmar.ted.delegates.NewestDelegate;

public class NewestViewHolder extends RecyclerView.ViewHolder {

   private NewestDelegate mnewestDelegate;

    public NewestViewHolder(View itemView, NewestDelegate newestDelegate) {

        super(itemView);

        mnewestDelegate = newestDelegate;

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mnewestDelegate.onTapImage();
            }
        });
    }
}
