package com.example.jthaicustomersupport;
import java.io.*;
import java.util.LinkedHashMap;
import java.util.Map;
import jakarta.servlet.*;
import jakarta.servlet.annotation.*;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

@WebServlet(name = "ticket", value = "/ticket")
@MultipartConfig(fileSizeThreshold = 5_242_880, maxFileSize = 20_971_520L, maxRequestSize = 41_943_040L)
public class SupportServlet extends HttpServlet {
    private volatile int SUPPORT_ID = 1;
    private Map<Integer, Ticket> ticketDB = new LinkedHashMap<>();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // check for login
        if (request.getSession().getAttribute("username") == null) {
            response.sendRedirect("login");
            return;
        }



        String action = request.getParameter("action");

        if (action == null) {
            action = "list";
        }
        switch(action) {
            case "createTicket" -> showTicketForm(request, response);
            case "view" -> viewTicket(request, response);
            case "download" -> downloadAttachment(request, response);
            default -> listTickets(request,response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // check for login
        if (request.getSession().getAttribute("username") == null) {
            response.sendRedirect("login");
            return;
        }

        String action = request.getParameter("action");

        if (action == null) {
            action = "list";
        }
        switch(action) {
            case "create" -> createTicket(request, response);
            default -> response.sendRedirect("ticket"); // blog
        }

    }


    private void createTicket(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Ticket ticket = new Ticket();
        ticket.setName(request.getParameter("name"));
        ticket.setSubject(request.getParameter("subject"));
        ticket.setBodyOfTicket(request.getParameter("body"));

        Part file = request.getPart("file1");
        if (file != null) {
            Attachment attachment = this.processAttachment(file);
            if (attachment != null) {
                ticket.setAttachments(attachment);
            }
        }

        // add and synchronize
        int id;
        synchronized(this) {
            id = this.SUPPORT_ID++;
            ticketDB.put(id, ticket);
        }

        response.sendRedirect("ticket?action=view&ticketId=" + id);




    }

    private void listTickets(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("ticketDatabase", ticketDB);

        request.getRequestDispatcher("WEB-INF/jsp/view/listTickets.jsp").forward(request, response);

        /*

        PrintWriter out = response.getWriter();

        // heading and link to create ticket
        out.println("<html><body><h2>Ticket Posts</h2>");
        out.println("<a href=\"ticket?action=createTicket\">Create Ticket</a><br><br>");

        if (ticketDB.size() == 0) {
            out.println("There are no ticket posts");
        }
        else {
            for (int id : ticketDB.keySet()) {
                Ticket ticket = ticketDB.get(id);
                out.println("Blog #" + id);
                out.println(": <a href=\"ticket?action=view&ticketId" + id + "\">");
                out.println(ticket.getName() + "</a><br>");
            }
        }
        out.println("</body></html>");

        */

    }

    private void downloadAttachment(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idString = request.getParameter("ticketId");

        Ticket ticket = getTicket(idString, response);

        String name = request.getParameter("attachment"); // ================================== CHECK IMAGE =========================
        if (name == null) {
            response.sendRedirect("ticket?action=view&ticketId=" + idString);
        }

        Attachment attachment = ticket.getAttachments();
        if (attachment == null) {
            response.sendRedirect("ticket?action=view&ticketId=" + idString);
            return;
        }

        response.setHeader("Content-Disposition", "file1; filename=" + attachment.getName());
        response.setContentType("application/octet-stream");

        ServletOutputStream out = response.getOutputStream();
        out.write(attachment.getContents());

    }

    private void viewTicket(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            String idString = request.getParameter("ticketId");


            Ticket ticket = getTicket(idString, response);
            request.setAttribute("ticket", ticket);
            request.getRequestDispatcher("WEB-INF/jsp/view/viewTicket.jsp").forward(request, response);

            /*

            PrintWriter out = response.getWriter();
            out.println("<html><body><h2>Ticket Post</h2>");
            out.println("<h3>Name: " + ticket.getName() + "</h3>");
            out.println("<h3>Subject: " + ticket.getSubject() + "</h3>");
            out.println("<p>Summary: " + ticket.getBodyOfTicket() + "</p>");
            if (ticket.hasImage()) {
                out.println("<a href=\"ticket?action=download&ticketId" + idString + "&attachment=" +
                        ticket.getAttachments().getName() +
                        "\">" + ticket.getAttachments().getName() + "</a><br><br>");
            }
            out.println("<a href=\"ticket\">Return to the ticket list</a>");
            out.println("</body></html>");

            */

    }

    private void showTicketForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("WEB-INF/jsp/view/ticketForm.jsp").forward(request, response);

    }

    private Attachment processAttachment(Part file) throws IOException {
        InputStream in = file.getInputStream();
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        // processing the binary data to bytes
        int read;
        final byte[] bytes = new byte[1024];
        while ((read = in.read(bytes)) != -1) {
            out.write(bytes, 0, read);
        }

        Attachment attachment = new Attachment();
        attachment.setName(file.getSubmittedFileName());
        attachment.setContents(out.toByteArray());

        return attachment;
    }

    private Ticket getTicket(String idString, HttpServletResponse response) throws ServletException, IOException {
        if (idString == null || idString.length() == 0) {
            response.sendRedirect("ticket");
            return null;
        }

        // find in the database
        try {
            int id = Integer.parseInt(idString);
            Ticket ticket = ticketDB.get(id);
            if (ticket == null) {
                response.sendRedirect("ticket");
                return null;
            }
            return ticket;

        }
        catch (Exception e) {
            response.sendRedirect("ticket");
            return null;
        }
    }


}
