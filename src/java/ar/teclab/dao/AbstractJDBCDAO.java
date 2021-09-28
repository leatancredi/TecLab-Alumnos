/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.teclab.dao;

import com.mysql.jdbc.jdbc2.optional.MysqlConnectionPoolDataSource;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author user
 */
public abstract class AbstractJDBCDAO {

    private Conexion con;
    private Connection connection;

    AbstractJDBCDAO(String url, Properties dbProperties) {
        this.con = new Conexion(url, dbProperties);
    }

    protected ResultSet executeQuery(String query) throws SQLException {
        try {
            con.conectar();
            Statement statement = con.getJdbcConnection().createStatement();
            return statement.executeQuery(query);
        } catch (SQLException sqlException) {
            Logger.getLogger(AbstractJDBCDAO.class.getName()).log(Level.SEVERE, null, sqlException);
            throw sqlException;
        }
    }

    protected PreparedStatement prepareStatement(String sql) throws SQLException {
        try {
            connection = con.getJdbcConnection();
            return connection.prepareStatement(sql);
        } catch (SQLException sqlException) {
            Logger.getLogger(AbstractJDBCDAO.class.getName()).log(Level.SEVERE, null, sqlException);
            throw sqlException;
        }
    }

    protected void desconectar() throws SQLException {
        con.desconectar();
    }

    protected void conectar() throws SQLException {
        con.conectar();
    }
}
