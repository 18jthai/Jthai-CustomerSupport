<html>
<head>
    <title>Login Page</title>
</head>
<body>
    <h2>Login</h2>
    Please log in to access site<br>
    <c:if test="${loginFailed == true}">
        <b><c:out value="The username or password is incorrect"></c:out></b>
    </c:if>
    <form method="POST" action="<c:url value='/login'/>">
        Username: <input type="text" name="username"><br><br>
        Password: <input type="password" name="password"><br><br>
        <input type="submit" value="Log In">
    </form>
</body>
</html>
