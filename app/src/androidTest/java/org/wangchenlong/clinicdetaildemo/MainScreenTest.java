package org.wangchenlong.clinicdetaildemo;

import static android.support.test.espresso.Espresso.*;

import static android.support.test.espresso.action.ViewActions.*;
import static android.support.test.espresso.assertion.ViewAssertions.*;
import static android.support.test.espresso.matcher.ViewMatchers.*;

import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import static org.hamcrest.Matchers.*;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.wangchenlong.clinicdetaildemo.mock.TestData;

/**
 * 首页的测试
 * <p>
 * Created by wangchenlong on 16/8/4.
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class MainScreenTest {
    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule =
            new ActivityTestRule<>(MainActivity.class);

    @Test
    public void clickOnGotoSearchClinic_ShowClinicDetailScreen() {
        // 点击跳转页面按钮
        onView(withId(R.id.main_b_clinic_detail))
                .perform(click());

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

        // 匹配ActionBar对应文字
        onView(allOf(withId(R.id.action_bar_tv_title), withText(TestData.CLINIC_TITLE)))
                .check(matches(isDisplayed()));
    }
}
