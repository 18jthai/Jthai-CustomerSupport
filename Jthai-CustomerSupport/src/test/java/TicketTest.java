import org.junit.jupiter.api.Test;
import com.example.jthaicustomersupport.Attachment;
import com.example.jthaicustomersupport.Ticket;
import static org.junit.jupiter.api.Assertions.*;


class TicketTest {
    @Test
    void getsNameOnTicket() {
        byte[] picList = {};
        var attachment = new Attachment("Picture", picList);
        var ticket = new Ticket("Tim", "Water Overflowing", "There's water overflowing", attachment);

        assertEquals("Tim", ticket.getName());
    }

    @Test
    void getsSubjectOfTicket() {
        byte[] picList = {};
        var attachment = new Attachment("Picture", picList);
        var ticket = new Ticket("Tim", "Water Overflowing", "There's water overflowing", attachment);

        assertEquals("Water Overflowing", ticket.getSubject());

    }

    @Test
    void getsBodyOfTicket() {
        byte[] picList = {};
        var attachment = new Attachment("Picture", picList);
        var ticket = new Ticket("Tim", "Water Overflowing", "There's water overflowing", attachment);

        assertEquals("There's water overflowing", ticket.getBodyOfTicket());

    }
}
