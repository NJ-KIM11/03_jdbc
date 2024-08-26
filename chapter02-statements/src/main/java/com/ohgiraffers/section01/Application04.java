package com.ohgiraffers.section01;

import com.ohgiraffers.dto.EmployeeDTO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import static com.ohgiraffers.common.JDBCTemplate.*;

public class Application04 {
    public static void main(String[] args) {

        Connection con = getConnection();

        Statement stmt = null;
        ResultSet rset = null;

        EmployeeDTO selectEmp = null;

        Scanner scr = new Scanner(System.in);
        System.out.println("조회하실 사원 번호 입력 : ");

        String empId = scr.nextLine();

        String query = "SELECT * FROM EMPLOYEE WHERE EMP_ID = '" + empId + "'";

        try {
            stmt = con.createStatement();
            rset = stmt.executeQuery(query);

            if(rset.next()) {
                selectEmp = new EmployeeDTO();
                selectEmp.setEmpId(rset.getString("EMP_ID"));
                selectEmp.setEmpName(rset.getString("EMP_NAME"));
                selectEmp.setEmpNo(rset.getString("EMP_NO"));
                selectEmp.setEmail(rset.getString("EMAIL"));
                selectEmp.setPhone(rset.getString("PHONE"));
                selectEmp.setDeptCode(rset.getString("DEPT_CODE"));
                selectEmp.setJobCode(rset.getString("JOB_CODE"));
                selectEmp.setManagerId(rset.getString("MANAGER_ID"));
                selectEmp.setSalLevel(rset.getString("SAL_LEVEL"));
                selectEmp.setEntYN(rset.getString("ENT_YN"));
                selectEmp.setSalary(rset.getInt("SALARY"));
                selectEmp.setBonus(rset.getDouble("BONUS"));
                selectEmp.setHireDate(rset.getDate("HIRE_DATE"));
                selectEmp.setEntDate(rset.getDate("ENT_DATE"));


            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            close(con);
            close(stmt);
            close(rset);
        }
        System.out.println("selectEmp = " + selectEmp);

    }
}
