package com.softserveinc.edu.ita.dao;


import com.softserveinc.edu.ita.domains.Product;
import org.testng.Reporter;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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

    protected String getLastAddedQuery() {
        return "SELECT Id, IsProductActive as Status, ProductName, ProductDescription, ProductPrice \n" +
                "FROM Products WHERE IsProductActive = 1 ORDER BY ID DESC LIMIT 1;";
    }

    protected String getDeleteQuery() {
        return "DELETE FROM Products WHERE Id = ?;";
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

    @Override
    public Product getLast() throws DAOException {
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

    public void deleteById(Product product) throws DAOException {
        try (PreparedStatement statement = connection.prepareStatement(getDeleteQuery())) {
            statement.setInt(1, product.getId());
            statement.executeUpdate();
            Reporter.log(String.format("<br>INFO&nbsp;&nbsp; - Product <b>%s</b> deleted from database", product.getProductName()));
        } catch (Exception e) {
            throw new DAOException(e);
        }
    }
}
