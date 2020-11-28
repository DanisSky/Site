<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="views/_header.jsp"/>
<title>Admin</title>
<jsp:include page="views/_navbar.jsp"></jsp:include>
<form action="${pageContext.request.contextPath}/admin" enctype="multipart/form-data" method="post">
    <input type="text" name="mark" placeholder="mark">
    <input type="text" name="model" placeholder="model">
    <input type="text" name="price" placeholder="price">
    <input type="text" name="description" placeholder="description">
    <input type="text" name="mileage" placeholder="mileage">
    <input type="file" name="photo" multiple accept="image/*,image/jpeg" placeholder="photo">
    <button type="submit" value="send">send</button>
</form>

<jsp:include page="views/_footer.jsp"/>