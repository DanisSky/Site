<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="views/_header.jsp"/>
<title>Admin</title>
<jsp:include page="views/_navbar.jsp"></jsp:include>
<form action="${pageContext.request.contextPath}/admin" enctype="multipart/form-data" method="post">
    <label>
        <input type="text" name="description">
    </label>
    <input type="file" name="photo" multiple accept="image/*,image/jpeg">
    <button type="submit" value="send">send</button>
</form>

<jsp:include page="views/_footer.jsp"/>