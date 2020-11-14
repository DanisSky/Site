<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="views/_header.jsp"/>
<title>Car info</title>
<jsp:include page="views/_navbar.jsp"></jsp:include>
<jsp:useBean id="Comments" scope="request" type="java.util.List"/>
<jsp:useBean id="Car" scope="request" type="ru.itis.dto.CarDto"/>
<div class="container">
    <header class="d-flex flex-wrap justify-content-center mt-5">
        <div class="car__image">
            <img src="WebContent/images/${Car.fileStorageName}.${Car.fileType}"
                 alt="car"/>
        </div>
        <div class="car__title ml-5">

            <h1>The <br><c:out value="${Car.mark}"/><br> <c:out value="${Car.model}"/><br></h1>

        </div>
    </header>
    <div class="car__about">
            <span>
            <c:out value="${Car.description}"/>

            </span>
    </div>
    <div class="line my-5"></div>

    <div class="comments w-75 mx-auto" id="commentsList">
        <c:forEach items="${Comments}" var="comment">
            <div class="comment">
                <h3>${comment.userDto.firstName} </h3>
                <div class="comment-text">${comment.text}</div>
            </div>
        </c:forEach>
    </div>


    <form class="w-75 d-flex flex-column justify-content-center mx-auto" id="MyForm">
        <label class="d-block" for="comment">
            <h5>Add comment</h5>
        </label>
        <div class="form-group w-75 d-flex flex-column">
            <textarea class="form-control rounded-0" id="comment" rows="5"></textarea>
            <div class="add_comment mt-2">
                <div class="btn btn-secondary" onclick="sendComment()">submit</div>
            </div>
        </div>
    </form>


</div>
<jsp:include page="views/_footer.jsp"/>
<script>
    function renderTable(comments) {
        let textCOM = '';
        $('#commentsList').empty();
        let len = comments.length;
        for (let i = 0; i < len; i++) {
            let comment = comments[i];
            let {text} = comment;
            let {userDto: {firstName}} = comment;
            let {userDto: {lastName}} = comment;
            textCOM += '<div class="comment">' + '<h3>' + firstName + ' ' + lastName + '</h3>' +
                '<div class="comment-text">' + text + '</div>' + '</div>';
        }
        $('#commentsList').append(textCOM);
    }

    function sendComment() {
        let comment = $('#comment').val();
        if (comment) {
            $('#comment').val('');
        }
        let data = {
            text: comment,
        };

        $.ajax({
            type: "POST",

            data: JSON.stringify(data),
            success: (response) => {
                console.log(response);
                renderTable(response);
            },
            dataType: "json",
            contentType: "application/json"
        }).catch((err)=>{console.log(err)})
    }

</script>

<style>
    body {
        background-image: url(https://subtlepatterns.com/patterns/kindajean.png);
        font-family: 'Open Sans', sans-serif;
    }

    .comment {
        display: flex;
        margin-bottom: 2rem;
        padding: 20px;
        flex-direction: column;
        background: rgb(211, 209, 209);
        border-radius: 30px;
    }

    .comments {
        display: flex;
        flex-direction: column;
        justify-content: center;
    }

    .line {
        display: block;
        margin: auto;
        height: 5px;
        width: 80%;
        background-color: lightgray;
    }

    .car__about {
        margin-top: 3rem;
        padding: 0 5rem;
        font-size: 1rem;
    }

    .car__title h1 {
        font-weight: 200;
        font-size: 4.5rem;
    }

    .car__image img {
        border: 3px solid rgb(211, 209, 209);
        border-radius: 20px;
        width: 500px;
    }

    a,
    a:hover {
        text-decoration: none;
    }
</style>

