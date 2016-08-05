package org.wangchenlong.clinicdetaildemo.hospitals;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.wangchenlong.clinicdetaildemo.R;

import java.util.List;

import static org.wangchenlong.clinicdetaildemo.hospitals.StarHospitalsData.ResultEntity;

/**
 * 明星医院的列表适配器
 * <p>
 * Created by wangchenlong on 16/8/3.
 */
public class StarHospitalsListAdapter extends BaseAdapter {
    private List<ResultEntity> mResultEntities;
    private LayoutInflater mLayoutInflater;
    private Context mContext;

    public StarHospitalsListAdapter(Context context, List<ResultEntity> resultEntities) {
        mResultEntities = resultEntities;
        mContext = context;
        mLayoutInflater = LayoutInflater.from(mContext);
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
            view = mLayoutInflater.inflate(R.layout.item_star_hospital, viewGroup, false);
            holder = new ViewHolder();
            holder.ivImage = (ImageView) view.findViewById(R.id.star_hospital_iv_image);
            holder.tvTitle = (TextView) view.findViewById(R.id.star_hospital_tv_title);
            holder.tvLevel = (TextView) view.findViewById(R.id.star_hospital_tv_level);
            holder.tvAddress = (TextView) view.findViewById(R.id.star_hospital_tv_address);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        ResultEntity resultEntity = mResultEntities.get(i);

        Picasso.with(mContext).load(resultEntity.image)
                .placeholder(R.drawable.hospital_default).into(holder.ivImage);
        Log.e("DEBUG", resultEntity.image);
        holder.tvTitle.setText(resultEntity.title);
        holder.tvLevel.setText(resultEntity.level);
        holder.tvAddress.setText(resultEntity.address);

        return view;
    }

    private static class ViewHolder {
        public ImageView ivImage;
        public TextView tvTitle;
        public TextView tvLevel;
        public TextView tvAddress;
    }

}
