package org.example.lesson31.library.builders;

import org.example.lesson31.library.core.Postgres;
import org.example.lesson31.library.core.WhereCondition;
import org.example.lesson31.library.models.BaseModel;

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
