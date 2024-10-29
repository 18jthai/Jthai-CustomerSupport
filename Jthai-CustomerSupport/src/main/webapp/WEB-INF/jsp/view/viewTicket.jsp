
<html>
<head>
    <title>Ticket #<c:out value="${ticketId}"/></title>
</head>
<body>
    <a href="<c:url value='/login'>
        <c:param name='logout'/>
        </c:url>">Logout</a>
    <h2>Ticket Post</h2>
    <h3>Name: <c:out value="${ticket.name}"/></h3>
    <h3>Subject: <c:out value="${ticket.subject}"/></h3>
    <p>Summary: <c:out value="${ticket.bodyOfTicket}"/></p>
    <c:if test="${ticket.hasImage()}">
        <a href="<c:url value='/ticket/${ticketId}/attachments/${ticket.attachments.name}' />">
            <c:out value="${ticket.attachments.name}"/></a>
    </c:if>
    <br><a href="<c:url value='/ticket/list'/>">Return to the ticket list</a>


</body>
</html>
