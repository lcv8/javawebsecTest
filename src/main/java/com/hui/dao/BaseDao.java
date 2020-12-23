package com.hui.dao;

import com.sun.scenario.effect.impl.prism.PrImage;
import org.apache.commons.dbcp.BasicDataSource;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @author lcv8
 */
public abstract class BaseDao {
    private String user;
    private String url;
    private String password;
    private String driverClass;
    private String initialSize;
    private String maxActive;
    private String minIdle;
    private ThreadLocal<Connection> connection = new ThreadLocal<>();
    //private BasicDataSource bds = new BasicDataSource();
    public BaseDao(){
        Properties properties = new Properties();
        //InputStream resource = BaseDao.class.getResourceAsStream("jdbc.properties");
        String path = BaseDao.class.getClassLoader().getResource("jdbc.properties").getPath();
        try {
            properties.load(new FileInputStream(new File(path)));
            user = properties.getProperty("user");
            url = properties.getProperty("url");
            password = properties.getProperty("password");
            driverClass = properties.getProperty("driverClass");
            initialSize = properties.getProperty("initialSize");
            maxActive = properties.getProperty("maxActive");
            minIdle = properties.getProperty("minIdle");
            Class.forName(driverClass);
            //创建连接池
            /*bds.setUsername(user);
            bds.setUrl(url);
            bds.setPassword(password);
            bds.setDriverClassName(driverClass);
            bds.setInitialSize(Integer.valueOf(initialSize));
            bds.setMaxIdle(Integer.valueOf(maxActive));
            bds.setMinIdle(Integer.valueOf(minIdle));*/
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    protected Connection getConn() throws SQLException {
        return this.getConn(true);
    }

    protected Connection getConn(boolean autoCommit) throws SQLException {
        if(connection.get() != null){
            return connection.get();
        } else {
            Connection conn = DriverManager.getConnection(url,user,password);
            conn.setAutoCommit(autoCommit);
            this.connection.set(conn);
            return conn;
        }
    }

    protected void closeConn(){
        if(connection.get() != null){
            try {
                connection.get().close();
                connection.remove();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    protected void commit(){
        if(connection.get() != null){
            try {
                connection.get().commit();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    protected void rollback(){
        if(connection.get() != null){
            try {
                connection.get().rollback();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }
}
