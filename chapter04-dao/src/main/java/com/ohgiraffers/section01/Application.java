package com.ohgiraffers.section01;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import static com.ohgiraffers.common.JDBCTemplate.*;

public class Application {
    public static void main(String[] args) {

        Scanner scr = new Scanner(System.in);
        Connection con = getConnection();
        Properties prop = new Properties();

        PreparedStatement pstmt1 = null;
        ResultSet rset1 = null;
        int result = 0;

        PreparedStatement pstmt2 = null;
        ResultSet rset2 = null;
        List<Map<Integer,String>> categoryList = null;

        PreparedStatement pstmt3 = null;
        int result2 = 0;

        try {
            prop.loadFromXML(new FileInputStream("src/main/resources/mapper/menu-query.xml"));

            String query = prop.getProperty("selectLastMenuCode");
            String query2 = prop.getProperty("selectAllCategoryList");
            String query3 = prop.getProperty("insertMenu");

            pstmt1 = con.prepareStatement(query);
            pstmt2 = con.prepareStatement(query2);
            pstmt3 = con.prepareStatement(query3);

            rset1 = pstmt1.executeQuery();
            while (rset1.next()) {
                result = rset1.getInt("MAX(MENU_CODE)");
            }
            System.out.println("최신 메뉴 코드 : " + result);

            rset2 = pstmt2.executeQuery();
            categoryList = new ArrayList<>();
            while (rset2.next()) {
                Map<Integer,String> category = new HashMap<>();
                category.put(rset2.getInt("CATEGORY_CODE"), rset2.getString("CATEGORY_NAME"));
                categoryList.add(category);
            }
            System.out.println("categoryList = " + categoryList);


            System.out.println("추가할 메뉴 입력 : ");
            String menuName = scr.nextLine();
            System.out.println("추가할 메뉴의 가격 입력 : ");
            int menuPrice = scr.nextInt();
            System.out.println("추가할 메뉴의 카테고리 코드 입력 : ");
            int categoryCode = scr.nextInt();
            scr.nextLine();
            System.out.println("추가할 메뉴의 주문가능 여부 입력 : ");
            char orderableStatus = scr.next().charAt(0);
            pstmt3.setString(1, menuName);
            pstmt3.setInt(2, menuPrice);
            pstmt3.setInt(3, categoryCode);
            pstmt3.setString(4, orderableStatus+"");
            /*MENU_NAME,
                    MENU_PRICE,
                    CATEGORY_CODE,
                    ORDERABLE_STATUS*/
            result2 = pstmt3.executeUpdate();
            if (result2 == 1) {
                System.out.println("성공");
            } else {
                System.out.println("실패");
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            close(con, pstmt1, rset1);
            close(con, pstmt2, rset2);
            close(pstmt3);
        }

    }
}
