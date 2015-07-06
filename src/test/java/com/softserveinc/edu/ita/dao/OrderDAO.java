package com.softserveinc.edu.ita.dao;



import com.softserveinc.edu.ita.domains.Order;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;


/**
 * DAO class for order domain.
 */
public class OrderDAO<T> extends AbstractDAO<T> {

    protected OrderDAO(final Connection connection) {
        super(connection);
    }

    @Override
    protected String getSelectAllQuery() {
        return "select Id, OrderName, OrderNumber, TotalPrice, Assigne, Customer, OrderStatusRef, " +
                "MaxDiscount, DeliveryDate, PreferableDeliveryDate \n" +
                "from orders";
    }

    @Override
    protected String getSelectQuery() {
        return "select Id, OrderName, OrderNumber, TotalPrice, Assigne, Customer, OrderStatusRef, " +
                "MaxDiscount, DeliveryDate, PreferableDeliveryDate\n" +
                "from orders \n" +
                "where id=?";
    }

    @Override
    protected String getUpdateQuery() {
        return "update orders set OrderName=?, OrderNumber=?, TotalPrice=?, " +
                "Assigne=?, Customer=?, OrderStatusRef=?," +
                "MaxDiscount=?, DeliveryDate=?, PreferableDeliveryDate=? \n" +
                "where id = ?";
    }

    @Override
    protected String getInsertQuery() {
        return "insert into orders (OrderName, OrderNumber, TotalPrice, Assigne, Customer, OrderStatusRef, " +
                "MaxDiscount, DeliveryDate, PreferableDeliveryDate) " +
                "values (?, ?, ?, ?, ?, ?)";
    }

    protected String getByOrderNumberQuery() {
        return "select Id, OrderName, OrderNumber, TotalPrice, Assigne, Customer, OrderStatusRef, " +
                "MaxDiscount, DeliveryDate, PreferableDeliveryDate\n" +
                "from orders \n" +
                "where OrderNumber=?";
    }

    @Override
    protected String getDeleteQuery() {
        return "delete from orders where id = ?";
    }

    @Override
    protected void setUpdateParameters(final PreparedStatement statement, final T object) {
        final Order order = (Order) object;
        try {
            int i = 1;
            statement.setString(i++, order.getOrderName());
            statement.setInt(i++, order.getOrderNumber());
            statement.setDouble(i++, order.getTotalPrice());
            statement.setInt(i++, order.getAssignee());
            statement.setInt(i++, order.getCustomer());
            statement.setInt(i++, order.getOrderStatusReference());
            statement.setDouble(i++, order.getMaxDiscount());
            statement.setString(i++, order.getDeliveryDate());
            statement.setString(i++, order.getPreferableDeliveryDate());
            statement.setInt(i++, order.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void setInsertParameters(final PreparedStatement statement, final T object) {
        final Order order = (Order) object;
        try {
            int i = 1;
            statement.setString(i++, order.getOrderName());
            statement.setInt(i++, order.getOrderNumber());
            statement.setDouble(i++, order.getTotalPrice());
            statement.setInt(i++, order.getAssignee());
            statement.setInt(i++, order.getCustomer());
            statement.setInt(i++, order.getOrderStatusReference());
            statement.setDouble(i++, order.getMaxDiscount());
            statement.setString(i++, order.getDeliveryDate());
            statement.setString(i++, order.getPreferableDeliveryDate());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected List<T> parseResultSet(final ResultSet resultSet) throws DAOException {
        final List<T> resultList = new LinkedList<>();
        try {
            while (resultSet.next()) {
                final Order order = Order.newBuilder()
                        .withId(resultSet.getInt("Id"))
                        .withOrderName(resultSet.getString("OrderName"))
                        .withOrderNumber(resultSet.getInt("OrderNumber"))
                        .withTotalPrice(resultSet.getDouble("TotalPrice"))
                        .withAssignee(resultSet.getInt("Assigne"))
                        .withCustomer(resultSet.getInt("Customer"))
                        .withOrderStatusReference(resultSet.getInt("OrderStatusRef"))
                        .withMaxDiscount(resultSet.getDouble("MaxDiscount"))
                        .withDeliveryDate(resultSet.getString("DeliveryDate"))
                        .withPreferableDeliveryDate(resultSet.getString("PreferableDeliveryDate"))
                        .build();
                resultList.add((T) (order));
            }
        } catch (Exception e) {
            throw new DAOException(e);
        }

        return resultList;
    }

    public Order getByOrderNumber(final int orderNumber) throws DAOException {
        List<Order> list;
        final String sqlQuery = getByOrderNumberQuery();

        try (PreparedStatement statement = connection.prepareStatement(sqlQuery)) {
            statement.setInt(1, orderNumber);
            final ResultSet resultSet = statement.executeQuery();
            list = (List<Order>) parseResultSet(resultSet);
        } catch (Exception e) {
            throw new DAOException(e);
        }
        return list.get(0);
    }

}
