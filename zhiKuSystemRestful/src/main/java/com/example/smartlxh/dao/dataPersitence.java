package com.example.smartlxh.dao;

import com.example.smartlxh.model.ReturnCodeEnum;
import com.example.smartlxh.model.Scholar;
import com.example.smartlxh.model.User;
import com.example.smartlxh.model.Work;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;


/**
 * Created by lixianhai on 26/11/2016.
 */
public class dataPersitence {

    public static final String url = "jdbc:mysql://127.0.0.1/graduteDesign?useUnicode=true&characterEncoding=UTF-8&useSSL=false";
    public static final String name = "com.mysql.jdbc.Driver";
    public static final String user = "root";
    public static final String password = "123";

    public dataPersitence(){
//        try {
//
//            /*Properties prop = new Properties();
//            FileInputStream fi = new FileInputStream("src/main/Resources/db.properties");
//            prop.load(fi);
//            username = prop.getProperty("userName");
//            userPasswd = prop.getProperty("userPasswd");
//            url = prop.getProperty("url");
//            driver = prop.getProperty("driver");*/
//            username = "root";
//            password = "123";
//            url = "jdbc:mysql://127.0.0.1:3306/douban?useUnicode=true&characterEncoding=UTF-8";
//            driver = "com.mysql.jdbc.Driver";
//
//        }catch(Exception e){
//            e.printStackTrace();
//        }
    }


    public Connection getConnection(){
        Connection conn=null;
        try {

            Class.forName(name);//指定连接类型
            conn = DriverManager.getConnection(url, user, password);//获取连接
        }catch(Exception e){
            e.printStackTrace();
        }

        return conn;

    }



    public LinkedList<Scholar> getByKey(String key, String value){

        LinkedList list = new LinkedList<Scholar>();
        try{


            Connection conn = getConnection();
            Statement state= conn.createStatement();
            String sql = "select * from scholar where %s like \"%s\"";
            sql = String.format(sql,key,value);

            ResultSet result = state.executeQuery(sql);
            Scholar scholar = new Scholar();
            while(result.next()){
                scholar.setId(result.getInt(1));
                scholar.setName(result.getString(2).trim());
                scholar.setAge(result.getInt(3));
                scholar.setDescription(result.getString(4).trim());
                scholar.setWorks(result.getString(5).trim());
                scholar.setResearchDirction(result.getString(6).trim());
                list.add(scholar);
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return list;

    }

    public ReturnCodeEnum insertScholar(Scholar scholar){


        Statement state= null;
        String sql = null;
        try {
            Connection conn = getConnection();
            state = conn.createStatement();
            sql = "insert into scholar values(%d,\"%s\",%d,\"%s\",\"%s\",\"%s\")";
            sql = String.format(sql,scholar.getId(),scholar.getName(),scholar.getAge(),scholar.getDescription(),scholar.getWorks(),scholar.getResearchDirction());

            state.execute(sql);

        } catch (SQLException e) {
            e.printStackTrace();
            return ReturnCodeEnum.ERROR;

        }

        return ReturnCodeEnum.SUCCESS;

    }


    public ReturnCodeEnum deleteInfo(String key,String value){


        Statement state= null;
        String sql = null;
        try {
            Connection conn = getConnection();
            state = conn.createStatement();
            sql = "delete from scholar where %s = \"%s\"";
            sql = String.format(sql,key,value);
            System.out.println(sql);
            state.execute(sql);

        } catch (SQLException e) {
            e.printStackTrace();
            return ReturnCodeEnum.ERROR;

        }

        return ReturnCodeEnum.SUCCESS;
    }

    public List<User> getLoginUser(String name, String password){

        LinkedList list = new LinkedList<User>();

        try{

            Connection conn = getConnection();
            Statement state= conn.createStatement();
            String sql = "select * from users where name =\"%s\" and password = \"%s\"";
            sql = String.format(sql,name,password);

            ResultSet result = state.executeQuery(sql);
            User user = new User();
            while(result.next()){
                user.setUserName(result.getString(1));
                user.setUserName(result.getString(2));
                list.add(user);
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return list;
    }


    public ReturnCodeEnum updateInfo(Scholar scholar){

        Statement state= null;
        String sql = null;
        try {
            Connection conn = getConnection();
            state = conn.createStatement();
            sql = "update scholar set name = \"%s\",age = %d,description = \"%s\",works = \"%s\",researchDirection=\"%s\" " +
                    "where id = %d";

            sql = String.format(sql,scholar.getName(),scholar.getAge(),scholar.getDescription(),scholar.getWorks(),scholar.getResearchDirction(),scholar.getId());

            state.execute(sql);

        } catch (SQLException e) {
            e.printStackTrace();
            return ReturnCodeEnum.ERROR;

        }

        return ReturnCodeEnum.SUCCESS;
    }

    public ReturnCodeEnum insertUser(User user){


        Statement state= null;
        String sql = null;
        try {
            Connection conn = getConnection();
            state = conn.createStatement();
            sql = "insert into users values(\"%s\",\"%s\")";
            sql = String.format(sql,user.getUserName(),user.getPassWord());

            state.execute(sql);

        } catch (SQLException e) {
            e.printStackTrace();
            return ReturnCodeEnum.ERROR;

        }

        return ReturnCodeEnum.SUCCESS;

    }



    public LinkedList<Work>  queryByFuzzle(String query){

        LinkedList<Work> works = new LinkedList<Work>();

        try{


            Connection conn = getConnection();
            Statement state= conn.createStatement();
            String sql = "select * from works where name like \"%%%s%%\" or time like \"%%%s%%\" or type like \"%%%s%%\" or research_area like \"%%%s%%\" or award like \"%%%s%%\"";

            sql = String.format(sql,query,query,query,query,query);


            System.out.println(sql);
            ResultSet result = state.executeQuery(sql);
            Work work = new Work();
            while(result.next()){
                System.out.println("dd");
                work.setId(result.getInt(1));
                work.setName(result.getString(2));
                work.setTime(result.getString(3));
                work.setType(result.getString(4));
                work.setResearchArea(result.getString(5));
                work.setAward(result.getString(6));
                work.setFilePath(result.getString(7));
                works.add(work);
            }

        }catch (Exception e){

            e.printStackTrace();
        }
        finally {
            return works;
        }

    }

}

