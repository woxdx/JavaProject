<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: wondo
  Date: 5/17/24
  Time: 2:06 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="../../include/title.jsp" />
    <link rel="stylesheet" href="<c:url value='/resources/css/admin/create_account_result.css' />" type="text/css">
</head>
<body>
    <jsp:include page="../../include/header.jsp" />
    <jsp:include page="../include/nav.jsp" />
    <section>
        <div id="section_wrap">
            <div class="word">
                <h3>CREATE ACCOUNT FAIL!!</h3>
            </div>

            <div class="others">
                <a href ="<c:url value='/admin/member/admin/createAccountForm'/> ">create account</a>
                <a href ="<c:url value='/admin/member/loginForm'/> ">login</a>
            </div>
        </div>

    </section>
    <jsp:include page="../../include/footer.jsp" />
</body>
</html>
