package com.softserveinc.edu.ita.dao;

import com.softserveinc.edu.ita.domains.OrderItem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class OrderItemDAO<T> extends AbstractDAO<T> {

    public OrderItemDAO(final Connection connection) {
        super(connection);
    }

    @Override
    protected String getSelectQuery() {
        return "SELECT ID, Cost, ItemPrice, Quantity, DimensionRef, OrderRef, ProductRef \n" +
                "FROM OrderItems \n" +
                "WHERE ID=?";
    }

    @Override
    protected String getSelectAllQuery() {
        return "SELECT ID, Cost, ItemPrice, Quantity, DimensionRef, OrderRef, ProductRef \n" +
                "FROM OrderItems";
    }

    @Override
    protected String getUpdateQuery() {
        return "UPDATE OrderItems \n" +
                "SET Cost=?, ItemPrice=?, Quantity=?, DimensionRef=?, OrderRef=?, ProductRef=? \n" +
                "WHERE ID=?";
    }

    @Override
    protected String getInsertQuery() {
        return "INSERT INTO OrderItems \n" +
                "(ID, Cost, ItemPrice, Quantity, DimensionRef, OrderRef, ProductRef) \n" +
                "VALUES(?, ?, ?, ?, ?, ?, ?)";
    }

    @Override
    protected String getDeleteQuery() {
        return "DELETE FROM OrderItems \n" +
                "WHERE ID=?";
    }

    private String getDeleteByOrderReferenceQuery() {
        return "DELETE FROM OrderItems \n" +
                "WHERE OrderRef=?";
    }

    @Override
    protected void setUpdateParameters(final PreparedStatement statement, final T object) throws DAOException {
        final OrderItem orderItem = (OrderItem) object;
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
            throw new DAOException(e);
        }
    }

    @Override
    protected void setInsertParameters(final PreparedStatement statement, final T object) throws DAOException {
        final OrderItem orderItem = (OrderItem) object;
        try {
            int i = 1;
            statement.setDouble(i++, orderItem.getCost());
            statement.setDouble(i++, orderItem.getItemPrice());
            statement.setInt(i++, orderItem.getQuantity());
            statement.setInt(i++, orderItem.getDimensionReference());
            statement.setInt(i++, orderItem.getOrderReference());
            statement.setInt(i++, orderItem.getProductReference());
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }

    @Override
    protected List<T> parseResultSet(final ResultSet resultSet) throws DAOException {
        final List<T> resultList = new LinkedList<>();
        try {
            while (resultSet.next()) {

                final OrderItem orderItem = OrderItem.newBuilder()
                        .withId(resultSet.getInt("ID"))
                        .withCost(resultSet.getDouble("Cost"))
                        .withItemPrice(resultSet.getDouble("ItemPrice"))
                        .withQuantity(resultSet.getInt("Quantity"))
                        .withDimensionReference(resultSet.getInt("DimensionRef"))
                        .withOrderReference(resultSet.getInt("OrderRef"))
                        .withProductReference(resultSet.getInt("ProductRef"))
                        .build();

                resultList.add((T) (orderItem));
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        }

        return resultList;
    }

    public void deleteByOrderReference(final int orderReference) throws DAOException{
        final String sqlQuery = getDeleteByOrderReferenceQuery();
        try (final PreparedStatement statement = connection.prepareStatement(sqlQuery)) {
            statement.setInt(1, orderReference);
            statement.executeUpdate();
        } catch (Exception e) {
            throw new DAOException(e);
        }
    }
}
