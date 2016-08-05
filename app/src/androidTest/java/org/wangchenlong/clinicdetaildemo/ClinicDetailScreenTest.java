package org.wangchenlong.clinicdetaildemo;

import android.content.Context;
import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.google.gson.Gson;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.wangchenlong.clinicdetaildemo.diseases.RelatedDiseasesData;
import org.wangchenlong.clinicdetaildemo.hospitals.StarHospitalsData;
import org.wangchenlong.clinicdetaildemo.mock.TestData;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.*;
import static android.support.test.espresso.assertion.ViewAssertions.*;
import static android.support.test.espresso.matcher.RootMatchers.*;
import static android.support.test.espresso.matcher.ViewMatchers.*;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertTrue;

/**
 * 科室详情页面测试
 * <p>
 * Created by wangchenlong on 16/8/4.
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class ClinicDetailScreenTest {
    @Rule
    public ActivityTestRule<ClinicDetailActivity> mActivityTestRule =
            new ActivityTestRule<ClinicDetailActivity>(ClinicDetailActivity.class) {
                @Override protected Intent getActivityIntent() {
                    Context targetContext = InstrumentationRegistry.getInstrumentation()
                            .getTargetContext();
                    Intent result = new Intent(targetContext, ClinicDetailActivity.class);
                    result.putExtra(MainActivity.CLINIC_TITLE, TestData.CLINIC_TITLE);
                    result.putExtra(MainActivity.CLINIC_CONTENT, TestData.CLINIC_CONTENT);
                    return result;
                }
            };

    /**
     * 显示科室详情页面
     */
    @Test
    public void showClinicDetailScreen() {
        // 显示科室标题
        onView(withId(R.id.clinic_tv_title))
                .check(matches(isDisplayed()));

        // 匹配科室标题对应文字
        onView(allOf(withId(R.id.clinic_tv_title), withText(TestData.CLINIC_TITLE)))
                .check(matches(isDisplayed()));

        // 显示科室内容
        onView(withId(R.id.clinic_tv_content))
                .check(matches(isDisplayed()));

        // 匹配科室内容对应文字
        onView(allOf(withId(R.id.clinic_tv_content), withText(TestData.CLINIC_CONTENT)))
                .check(matches(isDisplayed()));
    }

    /**
     * 关闭科室详情页面, 跳转至前一页
     */
    @Test
    public void gotoBeforeScreen() {
        // 点击返回按钮
        onView(withId(R.id.action_bar_iv_back)).perform(click());
        // 当前页面关闭
        assertTrue(mActivityTestRule.getActivity().isFinishing());
    }

    /**
     * 点击相关疾病Tab, 显示疾病列表
     */
    @Test
    public void clickRelatedDiseasesTab_ShowDiseasesList() {
        onView(withText(R.string.related_diseases_tab)).perform(click());

        final RelatedDiseasesData searchData = new Gson().fromJson(
                TestData.RELATED_DISEASES_DATA_JSON,
                RelatedDiseasesData.class);

        if (searchData != null && searchData.result != null && !searchData.result.isEmpty()) {
            onView(withText(searchData.result.get(0).name)).check(matches(isDisplayed()));
        } else {
            onView(withId(R.id.tab_lv_list)).check(matches(not(isDisplayed())));
        }
    }

    /**
     * 点击明星医院Tab, 显示医院列表
     */
    @Test
    public void clickStarHospitalsTab_ShowHospitalsList() {
        onView(withText(R.string.star_hospital_tab)).perform(click());

        final StarHospitalsData hospitalsData = new Gson().fromJson(
                TestData.STAR_HOSPITALS_DATA_JSON,
                StarHospitalsData.class);

        if (hospitalsData != null && hospitalsData.result != null && !hospitalsData.result.isEmpty()) {
            onView(withText(hospitalsData.result.get(0).title)).check(matches(isDisplayed()));
        } else {
            onView(withId(R.id.tab_iv_empty)).check(matches(isDisplayed()));
        }
    }

    /**
     * 点击相关疾病的列表项, 提示信息
     */
    @Test
    public void clickRelatedDiseasesList_ShowToast() {
        onView(withText(R.string.related_diseases_tab)).perform(click());

        final RelatedDiseasesData searchData = new Gson().fromJson(
                TestData.RELATED_DISEASES_DATA_JSON,
                RelatedDiseasesData.class);

        if (searchData != null && searchData.result != null && !searchData.result.isEmpty()) {
            // 点击ListView的第一项
            onData(anything())
                    .inAdapterView(withId(R.id.tab_lv_list))
                    .atPosition(0)
                    .perform(click());

            // Toast提示
            onView(withText(searchData.result.get(0).name)).inRoot(
                    withDecorView(not(is(mActivityTestRule.getActivity().getWindow().getDecorView()))))
                    .check(matches(isDisplayed()));
        } else {
            onView(withId(R.id.tab_lv_list)).check(matches(not(isDisplayed())));
        }
    }

    /**
     * 点击明星医院的列表项, 提示信息
     */
    @Test
    public void clickStarHospitalList_ShowToast() {
        onView(withText(R.string.star_hospital_tab)).perform(click());

        final StarHospitalsData hospitalsData = new Gson().fromJson(
                TestData.STAR_HOSPITALS_DATA_JSON,
                StarHospitalsData.class);

        if (hospitalsData != null && hospitalsData.result != null && !hospitalsData.result.isEmpty()) {
            // 点击ListView的第一项
            onData(anything())
                    .inAdapterView(withId(R.id.tab_lv_list))
                    .atPosition(0)
                    .perform(click());

            // Toast提示
            onView(withText(hospitalsData.result.get(0).title)).inRoot(
                    withDecorView(not(is(mActivityTestRule.getActivity().getWindow().getDecorView()))))
                    .check(matches(isDisplayed()));
        } else {
            onView(withId(R.id.tab_lv_list)).check(matches(not(isDisplayed())));
        }
    }
}
