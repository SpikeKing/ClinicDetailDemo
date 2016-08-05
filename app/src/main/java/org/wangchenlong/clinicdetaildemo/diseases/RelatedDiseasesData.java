package org.wangchenlong.clinicdetaildemo.diseases;

import java.util.List;

/**
 * 搜索疾病的结果
 * Created by wangchenlong on 16/8/3.
 */
public class RelatedDiseasesData {

    /**
     * desc : 历史医疗数据推测,仅供参考
     * type : DISEASE_PREDICT
     * result : [{"count":78386,"rate":0.4242332400647288,"type":"disease","name":"感冒","id":"11720"},{"count":35133,"rate":0.1901434748959523,"type":"disease","name":"过敏","id":"23756"},{"count":28537,"rate":0.15444523220635273,"type":"disease","name":"气管炎","id":"12918"},{"count":22200,"rate":0.12014872463752428,"type":"disease","name":"支气管炎","id":"24598"},{"count":20515,"rate":0.11102932819544192,"type":"disease","name":"咽炎","id":"2017"}]
     * name : 共计184771.0个病例
     */
    public String desc;
    public String type;
    public String name;
    public List<ResultEntity> result;

    /**
     * count : 78386
     * rate : 0.4242332400647288
     * type : disease
     * name : 感冒
     * id : 11720
     */
    public static class ResultEntity {
        public int count; // 病例数
        public double rate; // 比例
        public String type; // 疾病类型
        public String name; // 疾病名称
        public String id; // 疾病ID
    }
}
