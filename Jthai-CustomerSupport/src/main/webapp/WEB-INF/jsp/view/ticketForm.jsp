<html>
<head>
    <title>Create a new Ticket</title>
</head>
<body>
    <a href="<c:url value='/login'>
        <c:param name='logout'/>
        </c:url>">Logout</a>
    <h2>Create a Ticket</h2>

    <form:form method="POST" action="create" modelAttribute="ticket" enctype="multipart/form-data">
        <form:label path="name">Name:</form:label><br>
        <form:input path="name"/><br><br>
        <form:label path="subject">Subject:</form:label><br>
        <form:input path="subject"/><br><br>
        <form:label path="bodyOfTicket">Body:</form:label><br>
        <form:textarea path="bodyOfTicket" rows="25" cols="100"/><br><br>
        <b>Attachment</b><br>
        <form:input path="attachments" type="file"/>
        <input type="submit" value="Submit">
    </form:form>
</body>
</html>
