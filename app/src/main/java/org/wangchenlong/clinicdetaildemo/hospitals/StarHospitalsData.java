package org.wangchenlong.clinicdetaildemo.hospitals;

import java.util.List;

/**
 * 明星医院的数据
 * <p>
 * Created by wangchenlong on 16/8/3.
 */
public class StarHospitalsData {

    public List<ResultEntity> result;

    public static class ResultEntity {
        public String image;
        public String title;
        public String level;
        public String address;
        public String id;
    }
}
