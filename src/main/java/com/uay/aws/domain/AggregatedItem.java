package com.uay.aws.domain;

public class AggregatedItem {

    private String text;
    private Long count;

    public AggregatedItem() {
    }

    public AggregatedItem(String text, Long count) {
        this.text = text;
        this.count = count;
    }

    public AggregatedItem(String text, String count) {
        this(text, Long.valueOf(count));
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "AggregatedItem{" +
                "text='" + text + '\'' +
                ", count=" + count +
                '}';
    }
}
