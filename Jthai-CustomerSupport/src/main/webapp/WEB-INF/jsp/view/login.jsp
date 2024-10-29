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
    <form:form method="POST" action="login" modelAttribute="loginForm">
        <form:label path="username">Username:&nbsp;</form:label>
        <form:input path="username"/><br><br>
        <form:label path="password">Password:&nbsp;</form:label>
        <form:input path="password"/><br><br>
        <input type="submit" value="Log In">
    </form:form>
</body>
</html>
