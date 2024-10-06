

<%
    String ticketId = (String)request.getAttribute("ticketId");
    Ticket ticket = (Ticket)request.getAttribute("ticket");
%>





<html>
<head>
    <title>Ticket #<%=ticketId%></title>
</head>
<body>
    <h2>Ticket Post</h2>
    <h3>Name: <%=ticket.getName()%> </h3>
    <h3>Subject: <%=ticket.getSubject()%> </h3>
    <p>Summary: <%=ticket.getBodyOfTicket()%></p>
    <% if (ticket.hasImage()) {%>
        <a href="ticket?action=download&ticketId<%=ticketId%>&attachment=<%=ticket.getAttachments().getName()%>">
            <%=ticket.getAttachments().getName()%></a>

    <%}%>
    <br><a href="ticket">Return to the ticket list</a>


</body>
</html>
