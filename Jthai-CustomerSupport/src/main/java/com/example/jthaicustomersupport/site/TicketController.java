package com.example.jthaicustomersupport.site;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.RedirectView;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

@Controller
@RequestMapping("ticket")
public class TicketController {
    private volatile int TICKET_ID = 1;
    private Map<Integer, Ticket> ticketDB = new LinkedHashMap<>();

    @RequestMapping(value={"list", ""})
    public String listTickets(Model model) {
        model.addAttribute("ticketDatabase", ticketDB);
        return "listTickets";
    }

    @GetMapping("create")
    public ModelAndView createTicket() {
        return new ModelAndView("ticketForm","ticket", new TicketForm());
    }

    @PostMapping("create")
    public View createPost(@ModelAttribute("ticket")TicketForm form) throws IOException {
        Ticket ticket = new Ticket();
        ticket.setName(form.getName());
        ticket.setSubject(form.getSubject());
        ticket.setBodyOfTicket(form.getBodyOfTicket());
        ticket.setAttachments(form.getAttachments());

        Attachment file = form.getAttachments();
        Attachment attachment = new Attachment();
        attachment.setName(file.getOriginalFilename());
        attachment.setContents(file.getBytes());
        if ((attachment.getName() != null && attachment.getName().length() > 0 ||
                (attachment.getContents() != null && attachment.getContents().length > 0))) {
            ticket.setAttachments(attachment);
        }

        int id;
        synchronized(this) {
            id = this.TICKET_ID++;
            ticketDB.put(id, ticket);
        }

        return new RedirectView("view/"+id, true, false);

    }

    @GetMapping("view/{ticketId}")
    public ModelAndView viewTicket(Model model, @PathVariable("ticketIc")int ticketId) {
        Ticket ticket = ticketDB.get(ticketId);

        if(ticket == null) {
            return new ModelAndView(new RedirectView("listTickets", true, false));
        }

        model.addAttribute("ticketId", ticketId);
        model.addAttribute("ticket", ticket);

        return new ModelAndView("viewTicket");
    }

    public static class TicketForm {
        private String name;
        private String subject;
        private String bodyOfTicket;
        private Attachment attachments;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getSubject() {
            return subject;
        }

        public void setSubject(String subject) {
            this.subject = subject;
        }

        public String getBodyOfTicket() {
            return bodyOfTicket;
        }

        public void setBodyOfTicket(String bodyOfTicket) {
            this.bodyOfTicket = bodyOfTicket;
        }

        public Attachment getAttachments() {
            return attachments;
        }

        public void setAttachments(Attachment attachments) {
            this.attachments = attachments;
        }
    }



}
