package org.wangchenlong.clinicdetaildemo.diseases;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.wangchenlong.clinicdetaildemo.R;
import org.wangchenlong.clinicdetaildemo.widgets.RateBarView;

import java.util.List;
import java.util.Locale;

import static org.wangchenlong.clinicdetaildemo.diseases.RelatedDiseasesData.ResultEntity;

/**
 * 搜索疾病的列表适配器
 * <p>
 * Created by wangchenlong on 16/8/3.
 */
class RelatedDiseasesListAdapter extends BaseAdapter {

    private List<ResultEntity> mResultEntities;
    private LayoutInflater mInflater;

    RelatedDiseasesListAdapter(Context context, List<ResultEntity> resultEntities) {
        mResultEntities = resultEntities;
        mInflater = LayoutInflater.from(context);
    }

    @SuppressWarnings("unused")
    public void setResultEntities(List<ResultEntity> resultEntities) {
        mResultEntities = resultEntities;
        notifyDataSetChanged();
    }

    @Override public int getCount() {
        if (mResultEntities != null) {
            return mResultEntities.size();
        } else {
            return 0;
        }
    }

    @Override public Object getItem(int i) {
        return null;
    }

    @Override public long getItemId(int i) {
        return 0;
    }

    @Override public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;

        if (view == null) {
            view = mInflater.inflate(R.layout.item_related_diseases, viewGroup, false);
            holder = new ViewHolder();
            holder.tvName = (TextView) view.findViewById(R.id.related_diseases_tv_name);
            holder.tvCases = (TextView) view.findViewById(R.id.related_diseases_tv_cases);
            holder.rbvRate = (RateBarView) view.findViewById(R.id.related_diseases_rbv_rate);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        ResultEntity resultEntity = mResultEntities.get(i);

        holder.tvName.setText(resultEntity.name);
        String possibility = String.format(Locale.CHINESE, "%d个相似病例", resultEntity.count);
        holder.tvCases.setText(possibility);
        holder.rbvRate.setRate((float) resultEntity.rate);

        return view;
    }

    private static class ViewHolder {
        TextView tvName;
        TextView tvCases;
        RateBarView rbvRate;
    }
}
