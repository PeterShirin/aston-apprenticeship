package ru.aston.apprenticeship.simpleservice.jdbc.DAO;

import ru.aston.apprenticeship.simpleservice.jdbc.DatabaseConnection;
import ru.aston.apprenticeship.simpleservice.jdbc.model.Subcategory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SubcategoryDAO {
    public List<Subcategory> getAllSubcategories() {
        List<Subcategory> subcategories = new ArrayList<>();
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM subcategories");
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Subcategory subcategory = new Subcategory();
                subcategory.setId(resultSet.getInt("id"));
                subcategory.setName(resultSet.getString("name"));
                subcategories.add(subcategory);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return subcategories;
    }

    public Subcategory getSubcategoryById(int id) {
        Subcategory subcategory = null;
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM subcategories WHERE id = ?")) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    subcategory = new Subcategory();
                    subcategory.setId(resultSet.getInt("id"));
                    subcategory.setName(resultSet.getString("name"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return subcategory;
    }

    public void saveSubcategory(Subcategory subcategory) {
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement("INSERT INTO subcategories (name) VALUES (?)")) {
            statement.setString(1, subcategory.getName());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateSubcategory(Subcategory subcategory) {
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement("UPDATE subcategories SET name = ? WHERE id = ?")) {
            statement.setString(1, subcategory.getName());
            statement.setInt(2, subcategory.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteSubcategory(int id) {
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement("DELETE FROM subcategories WHERE id = ?")) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}