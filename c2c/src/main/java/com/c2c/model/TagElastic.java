package com.c2c.model;

import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import java.util.Objects;

public class TagElastic {
    @Field(type = FieldType.Keyword, name = "tag_name")
    private String tag_name;

    @Field(type = FieldType.Text, name = "tag_value")
    private String tag_value;

    public TagElastic() {
    }

    public TagElastic(String tag_name, String tag_value) {
        this.tag_name = tag_name;
        this.tag_value = tag_value;
    }

    public String getTag_name() {
        return this.tag_name;
    }

    public void setTag_name(String tag_name) {
        this.tag_name = tag_name;
    }

    public String getTag_value() {
        return this.tag_value;
    }

    public void setTag_value(String tag_value) {
        this.tag_value = tag_value;
    }

    @Override
    public String toString() {
        return "{" +
            " tag_name='" + getTag_name() + "'" +
            ", tag_value='" + getTag_value() + "'" +
            "}";
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof TagElastic)) {
            return false;
        }
        TagElastic tagElastic = (TagElastic) o;
        return Objects.equals(tag_name, tagElastic.tag_name) && Objects.equals(tag_value, tagElastic.tag_value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tag_name, tag_value);
    }

}
