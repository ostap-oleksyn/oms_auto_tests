package com.softserveinc.edu.ita.dao;

import com.softserveinc.edu.ita.domains.OrderItem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class OrderItemDAO<T> extends AbstractDAO<T> {

    public OrderItemDAO(Connection connection) {
        super(connection);
    }

    @Override
    protected String getSelectQuery() {
        return "select ID, Cost, ItemPrice, Quantity, DimensionRef, OrderRef, ProductRef \n" +
                "from orderitems \n" +
                "where ID=?";
    }

    @Override
    protected String getSelectAllQuery() {
        return "select ID, Cost, ItemPrice, Quantity, DimensionRef, OrderRef, ProductRef \n" +
                "from orderitems";
    }

    @Override
    protected String getUpdateQuery() {
        return "update orderitems \n" +
                "set Cost=?, ItemPrice=?, Quantity=?, DimensionRef=?, OrderRef=?, ProductRef=? \n" +
                "where ID=?";
    }

    @Override
    protected String getInsertQuery() {
        return "insert into orderitems \n" +
                "(ID, Cost, ItemPrice, Quantity, DimensionRef, OrderRef, ProductRef) \n" +
                "values(?, ?, ?, ?, ?, ?, ?)";
    }

    @Override
    protected String getDeleteQuery() {
        return "delete from orderitems \n" +
                "where ID=?";
    }

    private String getDeleteByOrderReferenceQuery() {
        return "delete from orderitems \n" +
                "where OrderRef=?";
    }

    @Override
    protected void setUpdateParameters(PreparedStatement statement, T object) {
        OrderItem orderItem = (OrderItem) object;
        try {
            int i = 1;
            statement.setDouble(i++, orderItem.getCost());
            statement.setDouble(i++, orderItem.getItemPrice());
            statement.setInt(i++, orderItem.getQuantity());
            statement.setInt(i++, orderItem.getDimensionReference());
            statement.setInt(i++, orderItem.getOrderReference());
            statement.setInt(i++, orderItem.getProductReference());
            statement.setInt(i++, orderItem.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void setInsertParameters(PreparedStatement statement, T object) {
        OrderItem orderItem = (OrderItem) object;
        try {
            int i = 1;
            statement.setDouble(i++, orderItem.getCost());
            statement.setDouble(i++, orderItem.getItemPrice());
            statement.setInt(i++, orderItem.getQuantity());
            statement.setInt(i++, orderItem.getDimensionReference());
            statement.setInt(i++, orderItem.getOrderReference());
            statement.setInt(i++, orderItem.getProductReference());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected List<T> parseResultSet(ResultSet resultSet) throws DAOException {
        List<T> resultList = new LinkedList<>();
        try {
            while (resultSet.next()) {

                OrderItem orderItem = OrderItem.newBuilder()
                        .withId(resultSet.getInt("Id"))
                        .withCost(resultSet.getDouble("Cost"))
                        .withItemPrice(resultSet.getDouble("ItemPrice"))
                        .withQuantity(resultSet.getInt("Quantity"))
                        .withDimensionReference(resultSet.getInt("DimensionRef"))
                        .withOrderReference(resultSet.getInt("OrderRef"))
                        .withProductReference(resultSet.getInt("ProductRef"))
                        .build();

                resultList.add((T) (orderItem));
            }
        } catch (Exception e) {
            throw new DAOException(e);
        }

        return resultList;
    }

    public void deleteByOrderReference(int orderReference) throws DAOException{
        String sqlQuery = getDeleteByOrderReferenceQuery();
        try (PreparedStatement statement = connection.prepareStatement(sqlQuery)) {
            statement.setInt(1, orderReference);
            statement.executeUpdate();
        } catch (Exception e) {
            throw new DAOException(e);
        }
    }


}
