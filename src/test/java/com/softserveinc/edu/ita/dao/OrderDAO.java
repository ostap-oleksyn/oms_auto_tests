package com.softserveinc.edu.ita.dao;

import com.softserveinc.edu.ita.domains.Order;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

/**
 * This class represents a concrete implementation of Order model.
 */
public class OrderDAO extends AbstractDAO<Order> {

    /**
     * This query to database for getting records.
     *
     * @return
     */
    @Override
    protected String getSelectQuery() {
        return "select orders.ordername, orders.totalprice, orders.maxdiscount, orders.deliverydate, orderstatuses.orederstatusname, users.login, roles.rolename " +
                "from orders left outer join orderstatuses on orders.orderstatusref = orderstatuses.id" +
                "join users on orders.assigne = users.id " +
                "join roles on users.roleref = roles.id";
    }

    public OrderDAO(Connection connection) {
        super(connection);
    }

    /**
     * sets records to list
     *
     * @param resultSet
     * @return list with records
     * @throws DAOException
     */
    @Override
    protected List<Order> parseResultSet(ResultSet resultSet) throws DAOException {
        LinkedList<Order> resultList = new LinkedList<>();
        try {
            while (resultSet.next()) {
                Order order = Order.newBuilder()
                        .orderName(resultSet.getString("OrderName"))
                        .totalPrice(resultSet.getString("TotalPrice"))
                        .maxDiscount(resultSet.getString("MaxDiscount"))
                        .deliveryDate(resultSet.getString("DeliveryDate"))
                        .status(resultSet.getString("OrederStatusName"))
                        .assignee(resultSet.getString("Login"))
                        .role(resultSet.getString("RoleName"))
                        .build();
                resultList.add(order);
            }
        } catch (Exception e) {
            throw new DAOException(e);
        }
        return resultList;
    }

    /**
     * gets records from database by their ID
     *
     * @param id
     * @return
     * @throws DAOException
     */
    @Override
    public Order getById(int id) throws DAOException {
        List<Order> list;
        String sqlQuery = getSelectQuery();
        sqlQuery += " where orders.id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sqlQuery)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            list = parseResultSet(resultSet);
        } catch (Exception e) {
            throw new DAOException(e);
        }
        return (Order) list;
    }

    /**
     * gets last order from database
     *
     * @return
     * @throws DAOException
     */
    @Override
    public Order getLast() throws DAOException {
        List<Order> list;
        String selectQuery = getSelectQuery();
        selectQuery += " ORDER BY orders.id DESC LIMIT 1";
        try (PreparedStatement statement = connection.prepareStatement(selectQuery)) {
            ResultSet resultSet = statement.executeQuery();
            list = parseResultSet(resultSet);
        } catch (Exception e) {
            throw new DAOException(e);
        }
        return (Order) list;
    }
}
