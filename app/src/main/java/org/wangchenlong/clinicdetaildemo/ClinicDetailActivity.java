package org.wangchenlong.clinicdetaildemo;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.StringRes;
import android.support.v4.app.FragmentTabHost;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;

import org.wangchenlong.clinicdetaildemo.diseases.RelatedDiseasesFragment;
import org.wangchenlong.clinicdetaildemo.hospitals.StarHospitalsFragment;
import org.wangchenlong.clinicdetaildemo.mock.TestData;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ClinicDetailActivity extends AppCompatActivity {

    public static final String RELATED_DISEASES_KEY = "ClinicDetailActivity.RELATED_DISEASES_KEY";
    public static final String STAR_HOSPITALS_KEY = "ClinicDetailActivity.STAR_HOSPITALS_KEY";

    @BindView(R.id.clinic_tv_title) TextView mTvTitle; // 页面的标题
    @BindView(R.id.clinic_tv_content) TextView mTvContent; // 页面的内容
    @BindView(android.R.id.tabhost) FragmentTabHost mTabHost; // TabHost页面
    @BindView(R.id.clinic_fl_progress) FrameLayout mFlProgress;

    private LocalBroadcastManager mLocalBroadcastManager;

    // Tab的标签
    @StringRes
    private int mTabs[] = {
            R.string.related_diseases_tab,
            R.string.star_hospital_tab
    };

    private List<Class> mClassList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clinic_detail);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        String title = intent.getStringExtra(MainActivity.CLINIC_TITLE); // 科室标题
        String content = intent.getStringExtra(MainActivity.CLINIC_CONTENT); // 科室内容

        initActionBar(title); // 初始化并设置ActionBar
        initTabHost(); // 初始化TabHost

        mTvTitle.setText(title);
        mTvContent.setText(content);

        mLocalBroadcastManager = LocalBroadcastManager.getInstance(getApplicationContext());

        new LoadingDataTasks().execute();
    }

    /**
     * 初始化并设置ActionBar
     */
    private void initActionBar(String title) {
        ActionBar actionBar = getSupportActionBar();
        View view = getLayoutInflater().inflate(R.layout.action_bar_main, null);
        if (actionBar != null) {
            actionBar.setCustomView(view);
            actionBar.setDisplayShowCustomEnabled(true);
        }

        // 获取返回按钮
        ImageView ivBack = (ImageView) view.findViewById(R.id.action_bar_iv_back);

        // 获取标题按钮
        TextView tvTitle = (TextView) view.findViewById(R.id.action_bar_tv_title);

        // 点击返回上一个页面
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View view) {
                finish();
            }
        });

        // 设置标题
        tvTitle.setText(title);
    }

    // 异步线程
    private class LoadingDataTasks extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPostExecute(Void v) {
            mFlProgress.setVisibility(View.GONE);
        }

        @Override
        protected Void doInBackground(Void... params) {
            SystemClock.sleep(1000); // 模拟加载
            return null;
        }
    }


    /**
     * 初始化TabHost
     */
    private void initTabHost() {
        mTabHost.setup(this, getSupportFragmentManager(), android.R.id.tabcontent);
        mTabHost.getTabWidget().setDividerDrawable(null);

        mClassList = new ArrayList<>();
        mClassList.add(RelatedDiseasesFragment.class);
        mClassList.add(StarHospitalsFragment.class);

        // Fragment的启动参数
        Bundle disease = new Bundle();
        disease.putSerializable(RELATED_DISEASES_KEY, TestData.RELATED_DISEASES_DATA_JSON);
        Bundle hospital = new Bundle();
        hospital.putSerializable(STAR_HOSPITALS_KEY, TestData.STAR_HOSPITALS_DATA_JSON);
        List<Bundle> bundles = new ArrayList<>();
        bundles.add(disease);
        bundles.add(hospital);


        for (int i = 0; i < mTabs.length; i++) {
            // Tab按钮添加文字和图片
            TabHost.TabSpec tabSpec = mTabHost.newTabSpec(getString(mTabs[i])).setIndicator(getTabView(i));
            // 添加Fragment
            mTabHost.addTab(tabSpec, mClassList.get(i), bundles.get(i));
        }

        mTabHost.setCurrentTab(0); // 选择第一个Tab
    }

    /**
     * 获取Tab项的布局
     *
     * @param index 索引
     * @return 视图
     */
    private View getTabView(int index) {
        View view = getLayoutInflater().inflate(R.layout.item_clinic_tab, null);
        TextView textView = (TextView) view.findViewById(R.id.tab_tv_text);
        textView.setText(mTabs[index]);
        return view;
    }
}
