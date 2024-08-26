package com.ohgiraffers.section02.template;

import java.sql.Connection;

import static com.ohgiraffers.section02.template.JDBCtemplate.close;
import static com.ohgiraffers.section02.template.JDBCtemplate.getConnection;

public class Application {
    public static void main(String[] args) {

        Connection con = getConnection();
        System.out.println(con);
        close(con);


    }
}
