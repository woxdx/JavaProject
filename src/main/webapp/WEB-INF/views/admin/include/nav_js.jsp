<%--
  Created by IntelliJ IDEA.
  User: wondo
  Date: 5/3/24
  Time: 3:30 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script type="text/javascript">
    function searchBookForm() {
        console.log("searchBookForm() called !!! ");

        let form = document.search_book_form;

        if (form.b_name.value == '') {
            alert('Enter the name of the book you are looking for.');
            form.b_name.focus();

        } else {
            form.submit();

        }

    }
</script>

