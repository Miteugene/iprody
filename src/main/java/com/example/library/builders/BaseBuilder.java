package com.example.library.builders;

import com.example.library.core.Postgres;
import com.example.library.core.WhereCondition;
import com.example.library.models.BaseModel;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseBuilder<T extends BaseModel> {
    protected T model;
    protected List<WhereCondition> whereConditions = new ArrayList<>();

    public BaseBuilder(T model) {
        this.model = model;
    }

    public BaseBuilder<T> where(String field, String operator, Object value) {
        whereConditions.add(new WhereCondition(field, operator, value));
        return this;
    }

    public BaseBuilder<T> whereId(Object value) {
        return where("id", "=", value);
    }

    public ArrayList<T> get() {
        return Postgres.select(model, whereConditions);
    }

    public T first() {
        ArrayList<T> results = get();
        return results.isEmpty() ? null : results.get(0);
    }

    public int update(T newData) {
        return Postgres.updateWhere(model.getTableName(), whereConditions, newData);
    }

    public int delete() {
        return Postgres.deleteWhere(model.getTableName(), whereConditions);
    }
}
