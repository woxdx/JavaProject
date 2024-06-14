<%--
  Created by IntelliJ IDEA.
  User: wondo
  Date: 6/14/24
  Time: 3:52 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">

    <jsp:include page="../../include/title.jsp"/>

    <link href="<c:url value='/resources/css/admin/login_result.css' />" rel="stylesheet" type="text/css">


</head>
<body>

<jsp:include page="../../include/header.jsp"/>

<jsp:include page="../include/nav.jsp"/>

<section>

    <div id="section_wrap">

        <div class="word">

            <h3>DELETE SUCCESS!!</h3>

        </div>

    </div>

</section>

<jsp:include page="../../include/footer.jsp"/>

</body>
</html>
