package com.ss.mailshot.data.datamodel;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by IntelliJ IDEA.
 * User: jatin
 * Date: 14-Mar-2010
 * Time: 12:22:13
 */
@Entity
public class Field {
    @Id
    @GeneratedValue
    private Long id;

    private String fieldName;
    private String fieldType;

    public Field(String fieldName, String fieldType) {
        this.fieldName = fieldName;
        this.fieldType = fieldType;
    }

    public Field() {
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getFieldType() {
        return fieldType;
    }

    public void setFieldType(String fieldType) {
        this.fieldType = fieldType;
    }

    @Override
    public String toString() {
        return fieldName;
    }

    public String toDebugString() {
        return "Field{" +
                "id=" + id +
                ", fieldName='" + fieldName + '\'' +
                ", fieldType='" + fieldType + '\'' +
                '}';
    }
}
