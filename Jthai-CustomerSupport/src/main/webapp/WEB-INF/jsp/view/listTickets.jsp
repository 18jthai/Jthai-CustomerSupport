
<%@ page import="java.util.Map" %>

<%
    @SuppressWarnings("unchecked")
    Map<Integer, Ticket> db = (Map<Integer, Ticket>)request.getAttribute("ticketDatabase");
%>

<html>
<head>
    <title>Ticket Posts</title>
</head>
<body>
    <h2>Ticket Posts</h2>
    <a href="ticket?action=createTicket">Create Ticket</a><br><br>
    <%
        if(db.size() == 0) {%>
            <%="There are no tickets yet"%>
        <%}
        else {
            for (int id : db.keySet()) {
                Ticket ticket = db.get(id);%>
                <%="Ticket # " + id%>
                <a href="ticket?action=view&ticketId="<%=id%>><%=ticket.getName()%></>a><br>
            <%}
        }%>

</body>
</html>
