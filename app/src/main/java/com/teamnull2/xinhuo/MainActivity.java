package com.teamnull2.xinhuo;

import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import me.amiee.nicetab.NiceTabLayout;
import me.amiee.nicetab.NiceTabStrip;



public class MainActivity extends AppCompatActivity implements NiceTabStrip.OnIndicatorColorChangedListener {
    private ViewPager mViewPager;
    private NiceTabLayout mNiceTabLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Initialize the ViewPager and set an adapter
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Initialize the ViewPager and set an adapter
        // mViewPager = (ViewPager) findViewById(R.id.viewpager);
       //  mViewPager.setAdapter(new SampleFragmentPagerAdapter(getChildFragmentManager()));

        // Bind the tabs to the ViewPager
        //mNiceTabLayout = (NiceTabLayout) findViewById(R.id.sliding_tabs);
        //mNiceTabLayout.setViewPager(mViewPager);
        /*FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/
        if (savedInstanceState == null) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.mail_fl, TabFragment.newInstance());
            transaction.commit();
        }
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
    public void onIndicatorColorChanged(NiceTabLayout tabLayout, int color) {

    }


}
