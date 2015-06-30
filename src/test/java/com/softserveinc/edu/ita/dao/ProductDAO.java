package com.softserveinc.edu.ita.dao;


import com.softserveinc.edu.ita.domains.Product;
import com.softserveinc.edu.ita.enums.ProductStatus;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class ProductDAO extends AbstractDAO {

    public ProductDAO(Connection connection) {
        super(connection);
    }

    @Override
    protected String getSelectQuery() {
        return "SELECT Id, IsProductActive as Status, ProductName, ProductDescription, ProductPrice, \n" +
                "FROM Products \n" +
                "WHERE Id = ?";
    }

    @Override
    protected String getSelectAllQuery() {
        return "SELECT Id, IsProductActive as Status, ProductName, ProductDescription, ProductPrice, \n" +
                "FROM Products";
    }

    @Override
    protected String getUpdateQuery() {
        return "UPDATE Products SET IsProductActive = ?, ProductName = ?, ProductDescription = ?, ProductPrice = ?";
    }

    @Override
    protected String getInsertQuery() {
        return "INSERT INTO Products (IsProductActive, ProductName, ProductDescription, ProductPrice " +
                "VALUES (?, ?, ?, ?)";
    }

    protected String getLastAddedQuery() {
        return "SELECT Id, IsProductActive as Status, ProductName, ProductDescription, ProductPrice \n" +
                "FROM Products WHERE IsProductActive = 1 ORDER BY ID DESC LIMIT 1;";
    }

    @Override
    protected String getDeleteQuery() {
        return "DELETE FROM Products WHERE Id = ?;";
    }

    private String getProductQuery() {
        return "SELECT Id, IsProductActive as Status, ProductName, ProductDescription, ProductPrice \n" +
                "FROM Products \n" +
                "WHERE ProductName = ? AND ProductDescription = ?";
    }

    private String setProductStatus() {
        return "UPDATE PRODUCTS SET IsProductActive = ? \n" +
                "WHERE ProductName = ? AND ProductDescription = ?";
    }

    @Override
    protected void setUpdateParameters(PreparedStatement statement, Object object) {
        Product product = (Product) object;
        try {
            int i = 1;
            statement.setInt(i++, product.getStatus());
            statement.setString(i++, product.getProductName());
            statement.setString(i++, product.getProductDescription());
            statement.setDouble(i++, product.getProductPrice());
            statement.setInt(i++, product.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void setInsertParameters(PreparedStatement statement, Object object) {
        Product product = (Product) object;
        try {
            int i = 1;
            statement.setInt(i++, product.getStatus());
            statement.setString(i++, product.getProductName());
            statement.setString(i++, product.getProductDescription());
            statement.setDouble(i++, product.getProductPrice());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected List parseResultSet(ResultSet resultSet) throws DAOException {
        LinkedList<Product> resultList = new LinkedList<>();
        try {
            while (resultSet.next()) {
                Product product = Product.newBuilder()
                        .withId(resultSet.getInt("Id"))
                        .withStatus(resultSet.getInt("Status"))
                        .withProductName(resultSet.getString("ProductName"))
                        .withProductDescription(resultSet.getString("ProductDescription"))
                        .withProductPrice(resultSet.getDouble("ProductPrice"))
                        .build();
                resultList.add(product);
            }
        } catch (Exception e) {
            throw new DAOException(e);
        }
        return resultList;
    }


    public Product getLastAddedProduct() throws DAOException {
        List<Product> productList;
        try (PreparedStatement statement = connection.prepareStatement(getLastAddedQuery())) {
            ResultSet resultSet = statement.executeQuery();
            productList = parseResultSet(resultSet);
        } catch (Exception e) {
            throw new DAOException(e);
        }
        if (productList == null || productList.size() == 0) {
            throw new DAOException("Product not found.");
        }

        return productList.get(0);
    }

    public int getProductStatus(String name, String description) throws DAOException {
        List<Product> list;
        String sqlQuery = getProductQuery();

        try (PreparedStatement statement = connection.prepareStatement(sqlQuery)) {
            statement.setString(1, name);
            statement.setString(2, description);
            ResultSet resultSet = statement.executeQuery();
            list = (List<Product>) parseResultSet(resultSet);
        } catch (Exception e) {
            throw new DAOException(e);
        }
        return list.get(0).getStatus();
    }

    public void setProductStatus(String name, String description, ProductStatus status) throws DAOException {
        String sqlQuery = setProductStatus();
        try (PreparedStatement statement = connection.prepareStatement(sqlQuery)) {
            statement.setInt(1, status.getStatus());
            statement.setString(2, name);
            statement.setString(2, description);
            statement.executeUpdate();
        } catch (Exception e) {
            throw new DAOException(e);
        }
    }

    public Product getProduct(String name, String description) throws DAOException {
        List<Product> list;
        String sqlQuery = getProductQuery();

        try (PreparedStatement statement = connection.prepareStatement(sqlQuery)) {
            statement.setString(1, name);
            statement.setString(2, description);
            ResultSet resultSet = statement.executeQuery();
            list = (List<Product>) parseResultSet(resultSet);
        } catch (Exception e) {
            throw new DAOException(e);
        }
        return list.get(0);
    }
}
