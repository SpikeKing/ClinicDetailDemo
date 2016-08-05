package org.wangchenlong.clinicdetaildemo.hospitals;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;

import org.wangchenlong.clinicdetaildemo.ClinicDetailActivity;
import org.wangchenlong.clinicdetaildemo.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 明星医院页面
 * Created by wangchenlong on 16/8/3.
 */
public class StarHospitalsFragment extends Fragment {
    @BindView(R.id.tab_lv_list) ListView mLvList;
    @BindView(R.id.tab_iv_empty) ImageView mImageView;

    @Nullable @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tab_list, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        final StarHospitalsData hospitalsData = new Gson().fromJson(
                getArguments().getString(ClinicDetailActivity.STAR_HOSPITALS_KEY),
                StarHospitalsData.class);
        if (hospitalsData != null && hospitalsData.result != null && !hospitalsData.result.isEmpty()) {
            mImageView.setVisibility(View.GONE);
            mLvList.setVisibility(View.VISIBLE);

            StarHospitalsListAdapter adapter = new StarHospitalsListAdapter(getContext(), hospitalsData.result);
            mLvList.setAdapter(adapter);
            mLvList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    Toast.makeText(getContext(), hospitalsData.result.get(i).title,
                            Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            mImageView.setVisibility(View.VISIBLE);
            mLvList.setVisibility(View.GONE);
        }
    }
}
