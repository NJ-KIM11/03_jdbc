package com.ohgiraffers.section01.insert;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Scanner;

import static com.ohgiraffers.common.JDBCTemplate.*;

public class Application01 {
    public static void main(String[] args) {

        Connection con = getConnection();
        PreparedStatement pstmt = null;
//        ResultSet rset = null;
        // insert 문에서는 결과를 rset 이 아닌 정수로 받는다.
        // insert 성공시 1 실패시 0
        // 결과를 담아줄 변수
        int result = 0;

        Properties prop = new Properties();
        try {
            prop.loadFromXML(new FileInputStream("src/main/resources/mapper/menu-query.xml"));
            pstmt = con.prepareStatement(prop.getProperty("insertMenu"));
            pstmt.setString(1,"쌀국수");
            pstmt.setInt(2,11900);
            pstmt.setInt(3,4);
            pstmt.setString(4,"y");

            result = pstmt.executeUpdate();

            System.out.println("결과 : " + result);



        } catch (IOException | SQLException e) {
            throw new RuntimeException(e);
        }finally {
            close(con);
            close(pstmt);
        }


    }
}
