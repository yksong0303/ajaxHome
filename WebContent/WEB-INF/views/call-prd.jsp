<%@page import="java.sql.SQLException"%>
<%@page import="java.sql.CallableStatement"%>
<%@page import="com.ajax.test.servlet.InitServlet"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
   Connection con =InitServlet.getConnection();
      String sql= "{call prd_increase_sal(?,?)}";
      try {
         CallableStatement cs = con.prepareCall(sql);
         cs.setInt(1, 1);
         cs.setDouble(2, 1.2);
         int result =cs.executeUpdate();
         out.println("결과:"+result);
         out.println("1번 사원이20%인상 되었습니다.");
         
      } catch (SQLException e1) {
         // TODO Auto-generated catch block
         e1.printStackTrace();
      
      }finally {
         if(con!=null) {
            try {
               con.close();
            } catch (SQLException e) {
               e.printStackTrace();
            }
         }
      }
         %>
</body>
</html>