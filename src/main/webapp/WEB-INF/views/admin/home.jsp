<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: wondo
  Date: 5/3/24
  Time: 3:24 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8"/>
    <jsp:include page="../include/title.jsp" />
    <link href="<c:url value='/resources/css/admin/home.css' />" rel="stylesheet" type="text/css">
</head>
<body>
    <jsp:include page="../include/header.jsp" />
    <jsp:include page="./include/nav.jsp" />

    <section>
        <div id="section_wrap">
            <div class="word">
                <h3>ADMIN HOME</h3>
            </div>
        </div>
    </section>

    <jsp:include page="../include/footer.jsp" />
</body>
</html>
