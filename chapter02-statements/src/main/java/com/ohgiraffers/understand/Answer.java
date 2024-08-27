package com.ohgiraffers.understand;

import com.ohgiraffers.dto.EmployeeDTO;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Scanner;

import static com.ohgiraffers.common.JDBCTemplate.*;

public class Answer {
    public static void main(String[] args) {

        Connection con = getConnection();
        PreparedStatement pstmt = null;
        ResultSet rset = null;
        PreparedStatement pstmt1 = null;
        ResultSet rset1 = null;

        Scanner scr = new Scanner(System.in);

        ArrayList<EmployeeDTO> empList = new ArrayList<EmployeeDTO>();
        ArrayList<EmployeeDTO> empList1 = new ArrayList<EmployeeDTO>();
        EmpList listClass = new EmpList();

        Properties prop = new Properties();

        try {
            prop.loadFromXML(new FileInputStream("src/main/java/com/ohgiraffers/mapper/employee-query.xml"));
            pstmt = con.prepareStatement(prop.getProperty("selectEmpByMaxSalary"));
            rset = pstmt.executeQuery();
            empList = listClass.getList(rset);


            System.out.println("조회할 사원의 성씨 입력 : ");
            String empName = scr.nextLine();
            pstmt1 = con.prepareStatement(prop.getProperty("selectEmpByNameJoinJobName"));
            pstmt1.setString(1, empName);
            rset1 = pstmt1.executeQuery();
            empList1 = listClass.getList(rset1);



        } catch (IOException | SQLException e) {
            throw new RuntimeException(e);
        }finally {
            close(con);
            close(pstmt);
            close(rset);
        }


    }
}
