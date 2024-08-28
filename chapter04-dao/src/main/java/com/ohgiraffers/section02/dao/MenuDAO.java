package com.ohgiraffers.section02.dao;

import com.ohgiraffers.section02.dto.MenuDTO;

import java.awt.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.*;
import java.util.List;

import static com.ohgiraffers.common.JDBCTemplate.*;

public class MenuDAO {

    // Data Access Object - 데이터베이스와 상호작용을 할 클래스

    private Properties prop = new Properties();

    public MenuDAO(String url){
        try {
            prop.loadFromXML(new FileInputStream(url));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public int selectLastMenuCode(Connection con) {
        Statement stmt = null;
        ResultSet rset = null;
        int maxCode = 0;
        String query = prop.getProperty("selectLastMenuCode");
        try {
            stmt = con.createStatement();
            rset = stmt.executeQuery(query);

            if (rset.next()) {
                maxCode = rset.getInt("MAX(MENU_CODE)");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            close(con);
            close(stmt);
            close(rset);
        }

        return maxCode;
    }

    public List<Map> selectAllCategoryList(Connection con){
        Statement stmt = null;
        ResultSet rset = null;
        List<Map> categoryList = new ArrayList<>();
        String query = prop.getProperty("selectAllCategoryList");

        try {
            stmt = con.createStatement();
            rset = stmt.executeQuery(query);
            while (rset.next()) {
                Map<Integer, String> categoryMap = new HashMap<>();
                categoryMap.put(rset.getInt("CATEGORY_CODE"), rset.getString("CATEGORY_NAME"));
                categoryList.add(categoryMap);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            close(stmt);
            close(con);
            close(rset);
        }
        return categoryList;
    }

    public int insertMenu(Connection con, MenuDTO dto){
        PreparedStatement pstmt = null;
        int result = 0;
        try {
            pstmt = con.prepareStatement(prop.getProperty("insertMenu"));
            pstmt.setString(1, dto.getMenuName());
            pstmt.setInt(2,dto.getMenuPrice());
            pstmt.setInt(3,dto.getCategoryCode());
            pstmt.setString(4,dto.getOrderableStatus());

            result = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("잘못된 값이 입력됨...");
        }finally {
            close(con);
            close(pstmt);
        }
        return result;
    }


    /*public MenuDTO insertMenu(Connection con){
        PreparedStatement pstmt = null;
        int result = 0;
        Scanner scr = new Scanner(System.in);

        System.out.println("추가할 메뉴 입력 : ");
        String menuName = scr.nextLine();
        System.out.println("추가할 메뉴의 가격 입력 : ");
        int menuPrice = scr.nextInt();
        System.out.println("추가할 메뉴의 카테고리 코드 입력 : ");
        int categoryCode = scr.nextInt();
        scr.nextLine();
        System.out.println("추가할 메뉴의 주문가능 여부 입력 : ");
        String orderableStatus = scr.nextLine();
        scr.nextLine();
        MenuDTO menuDTO = new MenuDTO(menuName, menuPrice, categoryCode, orderableStatus);

        try {
            pstmt = con.prepareStatement(prop.getProperty("insertMenu"));

            pstmt.setString(1,menuDTO.getMenuName());
            pstmt.setInt(2,menuDTO.getMenuPrice());
            pstmt.setInt(3,menuDTO.getCategoryCode());
            pstmt.setString(4,menuDTO.getOrderableStatus());

            result = pstmt.executeUpdate();
            if (result == 1) {
                System.out.println("성공");
            }else {
                System.out.println("실패");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            close(con);
            close(pstmt);
        }
        return menuDTO;
    }*/


}
