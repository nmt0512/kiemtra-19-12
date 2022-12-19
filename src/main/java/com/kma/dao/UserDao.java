package com.kma.dao;

import com.kma.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao {
    public Boolean checkUser(User user) {
        if (user != null) {
            Connection con = null;
            PreparedStatement statement = null;
            ResultSet resultSet = null;
            try{
                con = ConnectionDao.getConnection();
                String query = "SELECT * FROM DBUser WHERE Username = ? AND Password = ?";
                statement = con.prepareStatement(query);
                statement.setString(1, user.getUsername());
                statement.setString(2, user.getPassword());
                resultSet = statement.executeQuery();
                if(resultSet.next())
                    return true;
            }
            catch(SQLException e)
            {
                e.printStackTrace();
            }
            finally {
                try{
                    if(resultSet != null)
                        resultSet.close();
                    if(statement != null)
                        statement.close();
                    if(con != null)
                        con.close();
                }
                catch(SQLException e1)
                {
                    e1.printStackTrace();
                }
            }
        }
        return false;
    }
}
