package com.example.pt2024_30425_bobos_razvanandrei_assignment_3.DataAccess;

import com.example.pt2024_30425_bobos_razvanandrei_assignment_3.Connection.ConnectionFactory;

import java.beans.IntrospectionException;
import java.lang.reflect.Field;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AbstractDAO<T> {

   private final Class<T> type;
   private final String tableName;

   private final Connection connection;

    public AbstractDAO(Class<T> type) {
        this.type = type;
        this.tableName=type.getSimpleName();
        this.connection = ConnectionFactory.getConnection();
    }

    private String createSelectQuery(String field){
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT * FROM ");
        sb.append(tableName);
        sb.append(" WHERE ");
        sb.append(field);
        sb.append("=?");
        return sb.toString();
    }

    public T findById(int id) {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<T> items = new ArrayList<>();
        String query = createSelectQuery("id");
        try {
            statement = this.connection.prepareStatement(query);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            items = createObjects(resultSet);
            if (!items.isEmpty()) {
                return items.get(0);
            }
        } catch (SQLException | InstantiationException | IllegalAccessException | InvocationTargetException | IntrospectionException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);

        }
        return null;
    }



    public List<T> findAll() {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<T> items = new ArrayList<>();
        String query = "SELECT * FROM " + tableName;
        try {
            statement = this.connection.prepareStatement(query);
            resultSet = statement.executeQuery();
            items = createObjects(resultSet);
        } catch (SQLException | InstantiationException | IllegalAccessException | InvocationTargetException | IntrospectionException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
        }
        return items;
    }

    //translating a ResultSet into a list of Java objects of type T
    private List<T> createObjects(ResultSet resultSet) throws InstantiationException, IllegalAccessException, SQLException, InvocationTargetException, IntrospectionException, NoSuchMethodException {
        List<T> list=new ArrayList<>();

        while (resultSet.next()) {
            T instance = type.getDeclaredConstructor().newInstance(); // Using getDeclaredConstructor
            for (Field field : type.getDeclaredFields()) {
                field.setAccessible(true);
                Object value = resultSet.getObject(field.getName());
                PropertyDescriptor propertyDescriptor = new PropertyDescriptor(field.getName(), type);
                Method method = propertyDescriptor.getWriteMethod();
                method.invoke(instance, value);
            }
            list.add(instance);
        }
        return list;
    }
    private String createInsertQuery() {
        StringBuilder fields = new StringBuilder();
        StringBuilder placeholders = new StringBuilder();

        for (Field field : type.getDeclaredFields()) {
            if (!field.getName().equals("id")) {
                fields.append(field.getName()).append(", ");
                placeholders.append("?, ");
            }
        }
        if (fields.length() > 0 && placeholders.length() > 0) {
            fields.setLength(fields.length() - 2);
            placeholders.setLength(placeholders.length() - 2);
        }
        String query = "INSERT INTO " + tableName + " (" + fields.toString() + ") VALUES (" + placeholders.toString() + ")";
        //System.out.println("Constructed SQL Query: " + query);
        return query;
    }


    public void insert(T object) {
        PreparedStatement statement = null;
        String query = createInsertQuery();
        try {
            statement = this.connection.prepareStatement(query);  // Ensure the query includes correct placeholders
            int i = 1;
            for (Field field : type.getDeclaredFields()) {
                if (!field.getName().equals("id")) {
                    field.setAccessible(true);
                    Object value = field.get(object);
                    statement.setObject(i++, value);  // Bind each field's value to the query
                }
            }
            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Insertion failed, no rows affected.");
            }
        } catch (SQLException | IllegalAccessException e) {
            e.printStackTrace();
        } finally {
            ConnectionFactory.close(statement);
        }
    }

    private String createUpdateQuery() {
        StringBuilder query = new StringBuilder("UPDATE " + tableName + " SET ");
        List<String> fieldNames = new ArrayList<>();

        for (Field field : type.getDeclaredFields()) {
            if (!field.getName().equals("id")) {
                fieldNames.add(field.getName() + " = ?");
            }
        }

        query.append(String.join(", ", fieldNames));
        query.append(" WHERE id = ?");
        return query.toString();
    }

    public void update(T object) {
        PreparedStatement statement = null;
        String query = createUpdateQuery();
        try {
            statement = this.connection.prepareStatement(query);
            int i = 1;
            for (Field field : type.getDeclaredFields()) {
                field.setAccessible(true);
                Object value = field.get(object);
                if (field.getName().equals("id")) {
                    statement.setObject(type.getDeclaredFields().length, value);
                } else {
                    statement.setObject(i++, value);
                }
            }
            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Update failed, no rows affected.");
            }
        } catch (SQLException | IllegalAccessException e) {
            e.printStackTrace();
        } finally {
            ConnectionFactory.close(statement);
        }
    }

    private String createDeleteQuery() {
        return "DELETE FROM " + tableName + " WHERE id = ?";
    }

    public void delete(int id) {
        PreparedStatement statement = null;
        String query = createDeleteQuery();
        try {
            statement = this.connection.prepareStatement(query);
            statement.setInt(1, id);
            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Deletion failed, no rows affected.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
         } finally {
            ConnectionFactory.close(statement);
        }
    }




   ///asta este fara Reflexion pt ca este pt bill am voie:)
   public int findLatestId() {
       PreparedStatement statement = null;
       ResultSet resultSet = null;
       String query = "SELECT id FROM " + tableName + " ORDER BY id DESC LIMIT 1";
       try {
           statement = this.connection.prepareStatement(query);
           resultSet = statement.executeQuery();
           if (resultSet.next()) { // Just check if there is at least one result
               return resultSet.getInt("id"); // Return the id of the latest row
           } else {
               return -1; // Or handle this case as needed (e.g., throw an exception or return a default value)
           }
       } catch (SQLException e) {
           e.printStackTrace();
           return -1; // Or rethrow as a RuntimeException if you prefer
       } finally {
           ConnectionFactory.close(resultSet);
           ConnectionFactory.close(statement);
       }
   }

}
