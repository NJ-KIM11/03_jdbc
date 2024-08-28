package com.ohgiraffers.section03.delete;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Scanner;

import static com.ohgiraffers.common.JDBCTemplate.*;

public class Application {
    public static void main(String[] args) {

        Scanner scr = new Scanner(System.in);

        Connection con = getConnection();
        PreparedStatement pstmt = null;
        Properties prop = new Properties();
        int result = 0;

        System.out.println("제거할 메뉴 입력 : ");
        String menuToDelete = scr.nextLine();

        try {
            prop.loadFromXML(new FileInputStream("src/main/resources/mapper/menu-query.xml"));
            pstmt = con.prepareStatement(prop.getProperty("deleteMenu"));
            pstmt.setString(1, menuToDelete);
            result = pstmt.executeUpdate();

            if (result == 1) {
                System.out.println("성공");
            } else{
                System.out.println("실패");
            }

        } catch (IOException | SQLException e) {
            throw new RuntimeException(e);
        }finally {
            close(con, pstmt);
        }


    }
}
