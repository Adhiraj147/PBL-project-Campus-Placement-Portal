package com.placement.dao;

import com.placement.model.User;
import java.sql.SQLException;
import java.util.List;

public interface AdminDAO {

    List<User> getAllUsers() throws SQLException;

    int getTotalUsers() throws SQLException;

    int getUsersByRole(String role) throws SQLException;
}