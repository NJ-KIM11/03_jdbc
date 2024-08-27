package com.ohgiraffers.understand;

import com.ohgiraffers.dto.EmployeeDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class EmpList {

    public ArrayList<EmployeeDTO> getList(ResultSet rset){
        EmployeeDTO row;
        ArrayList<EmployeeDTO> empList = new ArrayList<>();

        while (true) {
            try {
                if (!rset.next()) break;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            row = new EmployeeDTO();

            try {
                row.setEmpId(rset.getString("EMP_ID"));
                row.setEmpName(rset.getString("EMP_NAME"));
                row.setEmpNo(rset.getString("EMP_NO"));
                row.setEmail(rset.getString("EMAIL"));
                row.setPhone(rset.getString("PHONE"));
                row.setDeptCode(rset.getString("DEPT_CODE"));
                row.setJobCode(rset.getString("JOB_CODE"));
                row.setManagerId(rset.getString("MANAGER_ID"));
                row.setSalLevel(rset.getString("SAL_LEVEL"));
                row.setEntYN(rset.getString("ENT_YN"));
                row.setSalary(rset.getInt("SALARY"));
                row.setBonus(rset.getDouble("BONUS"));
                row.setHireDate(rset.getDate("HIRE_DATE"));
                row.setEntDate(rset.getDate("ENT_DATE"));
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            empList.add(row);

        }

        return empList;
    }
}
