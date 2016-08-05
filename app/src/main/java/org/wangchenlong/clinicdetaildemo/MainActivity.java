package org.wangchenlong.clinicdetaildemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import org.wangchenlong.clinicdetaildemo.mock.TestData;

/**
 * 首页, 负责跳转搜索的诊所页面
 * <p>
 * Created by wangchenlong on 16/8/4.
 */
public class MainActivity extends AppCompatActivity {

    public static final String CLINIC_TITLE = "MainActivity.CLINIC_TITLE"; // 诊所的标题
    public static final String CLINIC_CONTENT = "MainActivity.CLINIC_CONTENT"; // 诊所的内容

    @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * 跳转到搜索结果页面
     *
     * @param view 视图
     */
    public void gotoClinicDetail(View view) {
        Intent intent = new Intent(this, ClinicDetailActivity.class);
        intent.putExtra(CLINIC_TITLE, TestData.CLINIC_TITLE);
        intent.putExtra(CLINIC_CONTENT, TestData.CLINIC_CONTENT);
        startActivity(intent);
    }
}
