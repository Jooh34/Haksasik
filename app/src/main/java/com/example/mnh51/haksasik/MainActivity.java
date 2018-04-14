package com.example.mnh51.haksasik;

import android.app.ProgressDialog;
import android.graphics.Color;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;

import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;

import com.example.mnh51.haksasik.day_tab.SectionViewPager;
import com.example.mnh51.haksasik.day_tab.SectionsPagerAdapter;
import com.example.mnh51.haksasik.download.Downloader;

import me.relex.circleindicator.CircleIndicator;

public class MainActivity extends AppCompatActivity {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private SectionViewPager mViewPager;

    private static final int REQUEST_MENU = 100;
    private static final int REQUEST_DATE = 101;

    private String menuJSON; // 식단 JSON 형식
    private String dateJSON; // 날짜 JSON 형식

    ProgressDialog loadingDialog;

    int requestcount; // menu request, date request 가 비동기 작업이라, 두 가지 모두 마쳤을 때를 알기 위한 변수

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // progress dialog
        loadingDialog = new ProgressDialog(this);
        loadingDialog.setMessage("식단 업데이트 중 ... ");
        loadingDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        loadingDialog.setIndeterminate(true);

        loadingDialog.show();
        //

        requestcount = 0;
        Downloader downloader = new Downloader(this);
        downloader.updateData(new UpdateCallback(){
            @Override
            public void onSuccess(String response, int requestType) {
                if(requestType == REQUEST_MENU) {
                    menuJSON = response;
                    Log.e("menuJSON", menuJSON);
                    requestcount++;

                    if(requestcount == 2) appStart();
                }
                else {
                    dateJSON = response;
                    Log.e("dateJSON", dateJSON);
                    requestcount++;

                    if(requestcount == 2) appStart();
                }
            }

            @Override
            public void onFailure() {
                Log.e("onFailure","onFailure");
                loadingDialog.dismiss();
            }
        });
    }

    public void appStart() { // request를 마친 후 앱 시작

        loadingDialog.dismiss();

        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager(), menuJSON, dateJSON);

        // Set up the ViewPager with the sections adapter.
        mViewPager = (SectionViewPager) findViewById(R.id.container);
        mViewPager.setSwipeable(true);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        CircleIndicator indicator = (CircleIndicator) findViewById(R.id.indicator);
        indicator.setViewPager(mViewPager);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);

        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));

    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */


    public interface UpdateCallback {
        void onSuccess(String response, int requestType);
        void onFailure();
    }
}
