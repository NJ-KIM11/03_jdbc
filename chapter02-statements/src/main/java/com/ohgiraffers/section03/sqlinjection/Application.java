package com.ohgiraffers.section03.sqlinjection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static com.ohgiraffers.common.JDBCTemplate.*;

public class Application {

    private static String empId = "210";
    private static String empName = "' OR 1=1 AND EMP_ID = '201";
    //statement 는 위에 empName 까지 query 문으로 읽어버린다.
    //preparedstatement 는 2 스텝으로 단계가 나눠져 있기 때문에 query 문은 미완성으로 놔두고 다음 단계에서 ?(위치홀더)에 String 추가
    // 따라서 안정성 증가


    public static void main(String[] args) {

        Connection con = getConnection();
        Statement stmt = null;
        ResultSet rset = null;

        String query = "SELECT * FROM EMPLOYEE WHERE EMP_ID = '" + empId +"' AND EMP_NAME = '" + empName + "'";
        System.out.println(query);

        try {
            stmt = con.createStatement();
            rset = stmt.executeQuery(query);
            if (rset.next()) {
                System.out.println(rset.getString("EMP_ID") + "님 환영합니다.");
            }else {
                System.out.println("회원 정보 XXX");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            close(con);
            close(stmt);
            close(rset);
        }

    }

}
