<jsp:include page="views/_header.jsp"/>
<div>
    <jsp:include page="views/_navbar.jsp"></jsp:include>
    <h3>Hello: ${sessionScope.userDto.firstName}</h3>
</div>
<div class="container">

    <div class="row justify-content-center">
        <h3>Change Password</h3>
        <div class="col-5 ">
            <form action="${pageContext.request.contextPath}/userProfile" method="post">
                <div class="form-group">
                    <label for="formGroupExampleInput"></label>
                    <input name="old_password" type="password" class="form-control" id="formGroupExampleInput"
                           placeholder="old password">
                </div>
                <div class="form-group">
                    <label for="formGroupExampleInput2"></label>
                    <input name="new_password" type="password" class="form-control" id="formGroupExampleInput2"
                           placeholder="new password">
                </div>
                <div class="form-group">
                    <label for="formGroupExampleInput3"></label>
                    <input name="repeated_new_password" type="password" class="form-control" id="formGroupExampleInput3"
                           placeholder="repeat">
                </div>
                <button type="submit" class="btn btn-primary" value="Send">Submit</button>
            </form>
        </div>
    </div>
</div>
<jsp:include page="views/_footer.jsp"/>