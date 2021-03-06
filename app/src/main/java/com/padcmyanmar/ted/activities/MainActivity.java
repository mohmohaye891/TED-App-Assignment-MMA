package com.padcmyanmar.ted.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.padcmyanmar.ted.R;
import com.padcmyanmar.ted.adapters.NewestAdapter;
import com.padcmyanmar.ted.data.models.NewestModel;
import com.padcmyanmar.ted.data.vos.TalkVO;
import com.padcmyanmar.ted.delegates.NewestDelegate;
import com.padcmyanmar.ted.events.SuccessGetNewestEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class MainActivity extends BaseActivity implements NewestDelegate{

    private NewestAdapter mNewestAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        RecyclerView rvNewest = findViewById(R.id.rv_newest);
        mNewestAdapter = new NewestAdapter(this);
        rvNewest.setAdapter(mNewestAdapter);
        rvNewest.setLayoutManager(new LinearLayoutManager(getApplicationContext(),
                LinearLayoutManager.VERTICAL,
                false));

        NewestModel.getObjectInstance().LoadNewestList();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onTapImage(TalkVO talks) {
        Intent intent = new Intent(getApplicationContext(), NewestDetailActivity.class);
        intent.putExtra("newestId", talks.getTalkId());
        startActivity(intent);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onSuccessGetNewest(SuccessGetNewestEvent event){
        Log.d("onSuccessGetNewest", "onSuccessGetNewest : " + event.getNewestList());
        mNewestAdapter.setNewestList(event.getNewestList());
    }
}
