package org.example.lesson31.library.core;

import org.example.lesson31.library.models.BaseModel;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Postgres {
    private final String HOST = "localhost";
    private final int PORT = 5432;
    private final String DATABASE = "mydatabase";
    private final String USER = "admin";
    private final String PASSWORD = "admin";

    public static <T extends BaseModel> ArrayList<T> select(T model, List<WhereCondition> whereConditions) {
        ArrayList<T> modelCollection = new ArrayList<>();

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = new Postgres().getConnection();

            StringBuilder sqlStringBuilder = new StringBuilder("SELECT * FROM " + model.getTableName());

            if (!whereConditions.isEmpty()) {
                sqlStringBuilder.append(" WHERE ");
                for (int i = 0; i < whereConditions.size(); i++) {
                    WhereCondition condition = whereConditions.get(i);

                    sqlStringBuilder.append(condition.field)
                            .append(" ")
                            .append(condition.operator)
                            .append(" ?");

                    if (i < whereConditions.size() - 1) {
                        sqlStringBuilder.append(" AND ");
                    }
                }
            }

            String sqlString = sqlStringBuilder.toString();
            //System.out.println(sqlString);

            statement = connection.prepareStatement(sqlString);

            for (int i = 0; i < whereConditions.size(); i++) {
                WhereCondition condition = whereConditions.get(i);
                statement.setObject(i + 1, condition.value);
            }

            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                T instance = (T) model.getClass().getDeclaredConstructor().newInstance();

                for (var field : model.getClass().getFields()) {
                    field.set(instance, resultSet.getObject(field.getName()));
                }

                modelCollection.add(instance);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeResultSet(resultSet);
            closeStatement(statement);
            closeConnection(connection);
        }

        return modelCollection;
    }

    public static <T extends BaseModel> T insert(T model) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = new Postgres().getConnection();
            StringBuilder sqlStringBuilder = new StringBuilder("INSERT INTO " + model.getTableName() + " (");
            StringBuilder valuesStringBuilder = new StringBuilder(" VALUES (");

            var fields = model.getClass().getFields();

            ArrayList<Object> values = new ArrayList<>();

            for (var field : fields) {
                if (field.getName().equals("id")) {
                    continue;
                }

                sqlStringBuilder.append(field.getName())
                        .append(",");

                valuesStringBuilder.append("?,");

                values.add(field.get(model));
            }

            // Убираем последний "," (Возможно можно сделать красивше через объединение строк по запятой, но пока лень)
            sqlStringBuilder.setLength(sqlStringBuilder.length() - 1);
            valuesStringBuilder.setLength(valuesStringBuilder.length() - 1);

            sqlStringBuilder.append(")")
                    .append(valuesStringBuilder)
                    .append(") RETURNING id");

            String sqlString = sqlStringBuilder.toString();
            //System.out.println(sqlString);

            statement = connection.prepareStatement(sqlString);

            for (int i = 0; i < values.size(); i++) {
                statement.setObject(i + 1, values.get(i));
            }

            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                var idField = model.getClass().getField("id");
                idField.set(model, resultSet.getObject("id"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeResultSet(resultSet);
            closeStatement(statement);
            closeConnection(connection);
        }

        return model;
    }

    public static <T extends BaseModel> int update(T model) {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = new Postgres().getConnection();
            StringBuilder sqlStringBuilder = new StringBuilder("UPDATE " + model.getTableName() + " SET ");
            var fields = model.getClass().getFields();

            ArrayList<Object> params = new ArrayList<>();
            Object idValue = null;

            for (var field : fields) {
                if (field.getName().equals("id")) {
                    idValue = field.get(model);
                    continue;
                }

                sqlStringBuilder.append(field.getName())
                        .append(" = ?, ");

                params.add(field.get(model));
            }

            // Убираем последний ", "
            sqlStringBuilder.setLength(sqlStringBuilder.length() - 2);
            sqlStringBuilder.append(" WHERE id = ?");

            String sqlString = sqlStringBuilder.toString();
            //System.out.println(sqlString);

            statement = connection.prepareStatement(sqlString);

            for (int i = 0; i < params.size(); i++) {
                statement.setObject(i + 1, params.get(i));
            }

            statement.setObject(params.size() + 1, idValue);

            return statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeStatement(statement);
            closeConnection(connection);
        }
        return 0;
    }

    public static <T extends BaseModel> boolean delete(T model) {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = new Postgres().getConnection();
            statement = connection.prepareStatement("DELETE FROM " + model.getTableName() + " WHERE id = ?");
            statement.setObject(1, model.getClass().getField("id").get(model));
            return statement.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeStatement(statement);
            closeConnection(connection);
        }
        return false;
    }

    public static <T extends BaseModel> int updateWhere(String table, List<WhereCondition> whereConditions, T data) {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = new Postgres().getConnection();
            StringBuilder sqlStringBuilder = new StringBuilder("UPDATE " + table + " SET ");
            var fields = data.getClass().getFields();
            ArrayList<Object> values = new ArrayList<>();

            for (var field : fields) {
                if (field.getName().equals("id")) {
                    continue;
                }

                sqlStringBuilder.append(field.getName())
                        .append(" = ?, ");

                values.add(field.get(data));
            }

            // Убираем последний ", "
            sqlStringBuilder.setLength(sqlStringBuilder.length() - 2);

            if (!whereConditions.isEmpty()) {
                sqlStringBuilder.append(" WHERE ");

                for (int i = 0; i < whereConditions.size(); i++) {
                    WhereCondition condition = whereConditions.get(i);

                    sqlStringBuilder.append(condition.field)
                            .append(" ")
                            .append(condition.operator)
                            .append(" ?");

                    if (i < whereConditions.size() - 1) {
                        sqlStringBuilder.append(" AND ");
                    }

                    values.add(condition.value);
                }
            }

            String sqlString = sqlStringBuilder.toString();
            //System.out.println(sqlString);

            statement = connection.prepareStatement(sqlString);

            for (int i = 0; i < values.size(); i++) {
                statement.setObject(i + 1, values.get(i));
            }

            return statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeStatement(statement);
            closeConnection(connection);
        }
        return 0;
    }

    public static int deleteWhere(String table, List<WhereCondition> whereConditions) {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = new Postgres().getConnection();
            StringBuilder sqlStringBuilder = new StringBuilder("DELETE FROM " + table);

            if (!whereConditions.isEmpty()) {
                sqlStringBuilder.append(" WHERE ");

                for (int i = 0; i < whereConditions.size(); i++) {
                    WhereCondition condition = whereConditions.get(i);

                    sqlStringBuilder.append(condition.field)
                            .append(" ")
                            .append(condition.operator)
                            .append(" ?");

                    if (i < whereConditions.size() - 1) {
                        sqlStringBuilder.append(" AND ");
                    }
                }
            }

            String sqlString = sqlStringBuilder.toString();
            //System.out.println(sqlString);

            statement = connection.prepareStatement(sqlString);

            for (int i = 0; i < whereConditions.size(); i++) {
                WhereCondition condition = whereConditions.get(i);
                statement.setObject(i + 1, condition.value);
            }

            return statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeStatement(statement);
            closeConnection(connection);
        }

        return 0;
    }


    private Connection getConnection() {
        String url = "jdbc:postgresql://"+HOST+":"+PORT+"/"+ DATABASE;

        Connection connection = null;

        try {
            Class.forName("org.postgresql.Driver");
            //System.out.println("PostgreSQL JDBC Driver Registered!");
            connection = DriverManager.getConnection(url, USER, PASSWORD);
            //System.out.println("Connected to DB!");
        } catch (ClassNotFoundException | SQLException e) {
            closeConnection(connection);
            e.printStackTrace();
        }

        return connection;
    }

    private static void closeResultSet(ResultSet resultSet) {
        if (resultSet == null) {
            return;
        }

        try {
            resultSet.close();
            //System.out.println("ResultSet closed");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void closeStatement(Statement statement) {
        if (statement == null) {
            return;
        }

        try {
            statement.close();
            //System.out.println("Statement closed");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void closeConnection(Connection connection) {
        if (connection == null) {
            return;
        }

        try {
            connection.close();
            //System.out.println("Connection closed");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
