<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="views/_header.jsp"/>
<div>
    <jsp:include page="views/_navbar.jsp"></jsp:include>
</div>
<div class="post-container">
    <jsp:useBean id="carsForJsp" scope="request" type="java.util.List"/>
    <c:forEach items="${carsForJsp}" var="car">
        <a href="${pageContext.request.contextPath}/carInfo?carId=${car.id}">
            <div class="post">
                <div class="product">
                    <img src="${pageContext.request.contextPath}/file?id=${car.fileId}">
                    <div class="info">
                        <h4>${car.mark}</h4>
                        <span class="description">${car.model}</span>
                        <span class="price">${car.price}</span>
                    </div>
                </div>
            </div>
        </a>
    </c:forEach>
</div>
<jsp:include page="views/_footer.jsp"/>

<style>
    body {
        background-image: url(https://subtlepatterns.com/patterns/kindajean.png);
    }

    a,
    a:hover {
        text-decoration: none;
    }

    .post-container {
        display: flex;
        flex-direction: row;
        justify-content: center;
        flex-wrap: wrap;
    }

    .post {
        display: flex;
        margin: 15px;
        width: 295px;
        border-radius: 5px;
        background: #fff;
        box-shadow: 0 1px 2px rgba(0, 0, 0, 0.2);
        transition: transform 0.2s;
    }

    .post:hover {
        transform: scale(1.05);
        opacity: 0.95;
    }

    .product img {
        width: 100%;
        border-top-left-radius: 5px;
        border-top-right-radius: 5px;
    }

    .info {
        display: block;
        position: relative;
        padding: 20px;
    }

    .details {
        border-top: 1px solid #e5e5e5;
        padding: 18px 20px;
    }

    .info h4 {
        position: relative;
        padding: 0 0 20px 0;
        margin: 0 0 20px 0;
        font-family: 'Open Sans', sans-serif;
        font-weight: 700;
        font-size: 19px;
        line-height: 25px;
        color: #372f2b;
        letter-spacing: -1px;
    }

    .info h4::after {
        display: block;
        position: absolute;
        bottom: 0px;
        content: '';
        width: 40px;
        height: 2px;
        background: #3b86c4;
    }

    .info .description {
        display: block;
        padding-bottom: 20px;
        font-family: 'Open Sans', sans-serif;
        font-size: 14px;
        font-weight: 600;
        color: #5f5f5f;
    }

    .info .price {
        font-family: 'Open Sans', Helvetica, Arial, sans-serif;
        font-size: 24px;
        font-weight: 700;
        color: #372f2b;
        line-height: 26px;
    }
</style>
