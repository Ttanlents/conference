package com.yjf.entity;

/**
 * @author 余俊锋
 * @date 2020/9/23 17:23
 * @Description
 */
public class Count extends BaseEntity{
    private Integer count;

    public Count(Integer count) {
        this.count = count;
    }

    public Count() {
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "Count{" +
                "count=" + count +
                '}';
    }
}
