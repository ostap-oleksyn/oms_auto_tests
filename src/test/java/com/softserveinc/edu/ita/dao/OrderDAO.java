package com.softserveinc.edu.ita.dao;

import com.softserveinc.edu.ita.domains.Order;
import com.softserveinc.edu.ita.domains.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
        return "SELECT ID, OrderName, OrderNumber, TotalPrice, Assigne, Customer, OrderStatusRef, " +
                "MaxDiscount, DeliveryDate, PreferableDeliveryDate \n" +
                "FROM Orders";
    }

    @Override
    protected String getSelectQuery() {
        return "SELECT ID, OrderName, OrderNumber, TotalPrice, Assigne, Customer, OrderStatusRef, " +
                "MaxDiscount, DeliveryDate, PreferableDeliveryDate\n" +
                "FROM Orders \n" +
                "WHERE ID=?";
    }

    @Override
    protected String getUpdateQuery() {
        return "UPDATE Orders SET OrderName=?, OrderNumber=?, TotalPrice=?, " +
                "Assigne=?, Customer=?, OrderStatusRef=?," +
                "MaxDiscount=?, DeliveryDate=?, PreferableDeliveryDate=? \n" +
                "WHERE ID = ?";
    }

    @Override
    protected String getInsertQuery() {
        return "INSERT INTO Orders (OrderName, OrderNumber, TotalPrice, Assigne, Customer, OrderStatusRef, " +
                "MaxDiscount, DeliveryDate, PreferableDeliveryDate) " +
                "VALUES (?, ?, ?, ?, ?, ?)";
    }

    protected String getByOrderNumberQuery() {
        return "SELECT ID, OrderName, OrderNumber, TotalPrice, Assigne, Customer, OrderStatusRef, " +
                "MaxDiscount, DeliveryDate, PreferableDeliveryDate\n" +
                "FROM Orders \n" +
                "WHERE OrderNumber=?";
    }

    @Override
    protected String getDeleteQuery() {
        return "DELETE FROM Orders WHERE ID = ?";
    }

    public String getOrdersByCustomer(){
        return "SELECT OrderName FROM Orders WHERE Customer = ?";
    }

    @Override
    protected void setUpdateParameters(final PreparedStatement statement, final T object) throws DAOException {
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
            throw new DAOException(e);
        }
    }

    @Override
    protected void setInsertParameters(final PreparedStatement statement, final T object) throws DAOException {
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
            throw new DAOException(e);
        }
    }

    @Override
    protected List<T> parseResultSet(final ResultSet resultSet) throws DAOException {
        final List<T> resultList = new LinkedList<>();
        try {
            while (resultSet.next()) {
                final Order order = Order.newBuilder()
                        .withId(resultSet.getInt("ID"))
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
        } catch (SQLException e) {
            throw new DAOException(e);
        }

        return resultList;
    }

    public Order getByOrderNumber(final int orderNumber) throws DAOException {
        List<Order> list;
        final String sqlQuery = getByOrderNumberQuery();

        try (final PreparedStatement statement = connection.prepareStatement(sqlQuery)) {
            statement.setInt(1, orderNumber);
            final ResultSet resultSet = statement.executeQuery();
            list = (List<Order>) parseResultSet(resultSet);
        } catch (Exception e) {
            throw new DAOException(e);
        }
        return list.get(0);
    }

    public List<String> getOrderNamesByCustomer(final User user) throws DAOException {
        final List<String> orderNames = new ArrayList<>();
        final String sqlQuery = getOrdersByCustomer();

        try (final PreparedStatement statement = connection.prepareStatement(sqlQuery)) {
            statement.setInt(1, user.getId());
            final ResultSet result = statement.executeQuery();
            while (result.next()){
                orderNames.add(result.getString("OrderName"));
            }

        } catch (SQLException e) {
            throw new DAOException( e );
        }

        return orderNames;
    }
}
