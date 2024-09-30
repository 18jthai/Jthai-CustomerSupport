import org.junit.jupiter.api.Test;
import com.example.jthaicustomersupport.Attachment;
import static org.junit.jupiter.api.Assertions.*;



public class AttachmentTest {
    @Test
    void getsNameOfPic() {
        byte[] picList = {};
        var attachment = new Attachment("Dog", picList);

        assertEquals("Dog", attachment.getName());
    }
}
