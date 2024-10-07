
<html>
<head>
    <title>Ticket #<c:out value="${ticketId}"/></title>
</head>
<body>
    <h2>Ticket Post</h2>
    <h3>Name: <c:out value="${ticket.name}"/></h3>
    <h3>Subject: <c:out value="${ticket.subject}"/></h3>
    <p>Summary: <c:out value="${ticket.bodyOfTicket}"/></p>
    <c:if test="${ticket.hasImage()}">
        <a href="<c:url value='/ticket' >
            <c:param name='action' value='download' />
            <c:param name='ticketId' value='${ticketId}' />
            <c:param name='attachment' value='${ticket.attachment.name}' />
        </c:url>"><c:out value="${ticket.attachment.name}"/></a>
    </c:if>
    <br><a href="ticket">Return to the ticket list</a>


</body>
</html>
