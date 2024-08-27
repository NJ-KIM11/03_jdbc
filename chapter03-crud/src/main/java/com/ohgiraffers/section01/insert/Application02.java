package com.ohgiraffers.section01.insert;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Scanner;

import static com.ohgiraffers.common.JDBCTemplate.*;

public class Application02 {

    /*
    * 사용자가 원하는 메뉴를 등록할 수 있도록 만들어 주세요.
    * 등록이 완료되면 성공, 실패하면 실패라고 출력 해주세요.
    * */

    public static void main(String[] args) {
        Connection con = getConnection();
        PreparedStatement pstmt = null;
        Properties prop = new Properties();

        Scanner scr = new Scanner(System.in);
        int insertedNum = 0;
        System.out.println("menu name :");
        String menuName = scr.nextLine();
        System.out.println("menu price :");
        int menuPrice = scr.nextInt();
        System.out.println("category code : ");
        int categoryCode = scr.nextInt();
        scr.nextLine();
        System.out.println("orderable status : ");
        String orderableStatus = scr.nextLine();

        try {
            prop.loadFromXML(new FileInputStream("src/main/resources/mapper/menu-query.xml"));
            pstmt = con.prepareStatement(prop.getProperty("insertMenu"));

            pstmt.setString(1, menuName);
            pstmt.setInt(2, menuPrice);
            pstmt.setInt(3, categoryCode);
            pstmt.setString(4, orderableStatus);

            switch (pstmt.executeUpdate()) {
                case 1 : System.out.println("성공"); break;
                case 0 : System.out.println("실패"); break;
            }

            /*insertedNum = pstmt.executeUpdate();
            if (insertedNum == 0) {
                System.out.println("실패");
            } else {
                System.out.println("성공");
            }*/


        } catch (IOException | SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(con, pstmt);
            System.out.println("done");
        }


    }
}
