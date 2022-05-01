package service.impl;

import dao.upfile;
import service.Operate;

import java.sql.*;

public class Operatimpl implements Operate {


    @Override
    public int savefile(upfile file) {

        //建立数据库连接
        String JDBC_DRIVER = "com.mysql.jdbc.Driver";
        String DB_URL = "jdbc:mysql://localhost/dbtest";


        String USER = "user";
        String PASS = "userroot";

        Connection conn = null;
        Statement stmt = null;
        PreparedStatement ps=null;
        int state=1;
        try {


            //STEP 2: Register JDBC driver
            Class.forName("com.mysql.jdbc.Driver");

            //STEP 3: Open a connection
            System.out.println("Connecting to a selected database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            System.out.println("Connected database successfully...");

            //STEP 4: Execute a query
            System.out.println("Inserting records into the table...");
            stmt = conn.createStatement();

            String sql = "INSERT INTO file(uid,name,type,size,path,realpath) " +
                    "VALUES (?, ?, ?, ?,?,?)";
            ps=conn.prepareStatement(sql);
            ps.setString(1,file.getUid());

            ps.setString(2,file.getName());
            ps.setString(3,file.getType());
            ps.setString(4,file.getSize());
            ps.setString(5,file.getPath());
            ps.setString(6,file.getRealpath());
            ps.executeUpdate();

        } catch (SQLException se) {
            state=0;
            se.printStackTrace();
        } catch (Exception e) {
            state=0;
            e.printStackTrace();
        } finally {

            try {
                if (stmt != null)
                    conn.close();
            } catch (SQLException se) {
            }
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }

        return state;

    }

    @Override
    public upfile findpath(String uuid) {
        String JDBC_DRIVER = "com.mysql.jdbc.Driver";
        String DB_URL = "jdbc:mysql://localhost/dbtest";


        String USER = "user";
        String PASS = "userroot";

        Connection conn = null;
        Statement stmt = null;
        PreparedStatement ps=null;
        upfile f1=new upfile();
        int state=1;
        try {


            //STEP 2: Register JDBC driver
            Class.forName("com.mysql.jdbc.Driver");

            //STEP 3: Open a connection
            System.out.println("Connecting to a selected database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            System.out.println("Connected database successfully...");

            //STEP 4: Execute a query
            System.out.println("Inserting records into the table...");
            stmt = conn.createStatement();

            String sql = "select * from file where uid=?";
            ps=conn.prepareStatement(sql);
            ps.setString(1,uuid);
            ResultSet rs=ps.executeQuery();

            //返回结果集
            while(rs.next()){
                f1.setPath(rs.getString("path"));
                f1.setName(rs.getString("name"));
                f1.setType(rs.getString("type"));
                f1.setRealpath(rs.getString("realpath"));
                f1.setSize(rs.getString("size"));
                f1.setUid(rs.getString("uid"));
                f1.setId(rs.getString("id"));
                f1.setCreatetime(rs.getString("createtime"));
            }


        } catch (SQLException se) {
            state=0;
            se.printStackTrace();
        } catch (Exception e) {
            state=0;
            e.printStackTrace();
        } finally {

            try {
                if (stmt != null)
                    conn.close();
            } catch (SQLException se) {
            }
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }

        return f1;

    }
}
