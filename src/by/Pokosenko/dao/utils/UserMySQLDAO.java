package by.Pokosenko.dao.utils;


import by.Pokosenko.dao.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;


/**
 * Created by Vladislav on 01.05.2017.
 */
public class UserMySQLDAO {

    public static void main(String[] args) {

        User myUser = new User(0,"3213","dfs","ssdfsdf");
        UserMySQLDAO userMySQLDAO = new UserMySQLDAO();
        userMySQLDAO.add(myUser);
    }
    private final String SQL_GET_ALL_USERS = "SELECT * FROM users\n";
    private final String SQL_ADD_USER = "INSERT INTO users (id, login, password,name) VALUES (?, ?, ?,?);";

    public List getALL() {
        List<User> result = new LinkedList<User>();
        try {
            Connection cn = Connector.getConnection();
            System.out.println("Connection created");
            PreparedStatement st = cn.prepareStatement(SQL_GET_ALL_USERS);
            System.out.println("Statement created");
            ResultSet rs = st.executeQuery();
            System.out.println("RS created");
            while (rs.next()) {
                System.out.println("Adding new User...");
                result.add(new User(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4)));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public void add(User user){
        try {
            Connection cn = Connector.getConnection();
            PreparedStatement st = cn.prepareStatement(SQL_ADD_USER);
            st.setInt(1,user.getId());
            st.setString(2,user.getLogin());
            st.setString(3,user.getPassword());
            st.setString(4,user.getName());
            st.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}