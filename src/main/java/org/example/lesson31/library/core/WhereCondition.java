package org.example.lesson31.library.core;

public class WhereCondition {
    public String field;
    public String operator;
    public Object value;

    public WhereCondition(String field, String operator, Object value) {
        this.field = field;
        this.operator = operator;
        this.value = value;
    }
}
