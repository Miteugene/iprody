package com.example.library.models;

import com.example.library.core.Postgres;

public abstract class BaseModel {
    protected String table;

    public String getTableName() {
        return table;
    }

    // Без нее ругается
    @SuppressWarnings("unchecked")
    public <T extends BaseModel> T create() {
        return (T) Postgres.insert(this);
    }

    public int update() {
        return Postgres.update(this);
    }

    public boolean delete() {
        return Postgres.delete(this);
    }
}