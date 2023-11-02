<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Insert title here</title>
	</head>
	<body>
		<h1>회원정보</h1>
         <table class = "table-info">
             <tr>
                 <th>회원번호</th>
                 <th>회원아이디</th>
                 <th>회원이름</th>
                 <th>회원나이</th>
             </tr>
             
             <tr>
                 <td>${user.userNo}</td>
                 <td>${user.userId}</td>
                 <td>${user.userName}</td>
                 <td>${user.userAge}</td>
             </tr>
         </table>
	</body>
</html>