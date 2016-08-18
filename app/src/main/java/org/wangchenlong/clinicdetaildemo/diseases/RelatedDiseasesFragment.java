package org.wangchenlong.clinicdetaildemo.diseases;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;

import org.wangchenlong.clinicdetaildemo.R;
import org.wangchenlong.clinicdetaildemo.ClinicDetailActivity;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * 诊所页面
 * Created by wangchenlong on 16/8/3.
 */
public class RelatedDiseasesFragment extends Fragment {

    @BindView(R.id.tab_lv_list) ListView mLvList;

    @Nullable @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tab_list, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle != null) {
            final RelatedDiseasesData searchData = new Gson().fromJson(
                    getArguments().getString(ClinicDetailActivity.RELATED_DISEASES_KEY),
                    RelatedDiseasesData.class);

            if (searchData != null && searchData.result != null && !searchData.result.isEmpty()) {
                ListAdapter adapter = new RelatedDiseasesListAdapter(getContext(), searchData.result);
                mLvList.setAdapter(adapter);
                mLvList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        Toast.makeText(getContext(), searchData.result.get(i).name,
                                Toast.LENGTH_SHORT).show();
                    }
                });
            }
        }
    }
}
