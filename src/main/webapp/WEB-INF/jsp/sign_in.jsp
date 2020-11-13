<jsp:include page="views/_header.jsp"/>

<div>
    <jsp:include page="views/_navbar.jsp"></jsp:include>
</div>
<div class="container">
    <div class="row justify-content-center">
        <div class="col-5 ">
            <form action="${pageContext.request.contextPath}/signIn" method="post">
                <div class="form-group">
                    <label>Email</label>
                    <input name="email" type="email" class="form-control" placeholder="Your email"
                           aria-describedby="emailHelp" required>
                </div>
                <div class="form-group">
                    <label> Password </label>
                    <input type="password" class="form-control" name="password" placeholder="Your password" required>
                </div>
                <button type="submit" class="btn btn-primary" value="Send">Submit</button>
            </form>
        </div>
    </div>
</div>

<jsp:include page="views/_footer.jsp"/>