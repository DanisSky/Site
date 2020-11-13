<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="views/_header.jsp"/>
<jsp:include page="views/_navbar.jsp"></jsp:include>
<h1>FROM JSP USERS</h1>
<div>
    <table>
        <tr>
            <th>ID</th>
            <th>FIRST NAME</th>
            <th>LAST NAME</th>
        </tr>
        <jsp:useBean id="usersForJsp" scope="request" type="java.util.List"/>
        <c:forEach items="${usersForJsp}" var="user">
            <tr>
                <td>${user.id}</td>
                <td>${user.firstName}</td>
                <td>${user.lastName}</td>
            </tr>
        </c:forEach>
    </table>
</div>
<jsp:include page="views/_footer.jsp"/>
