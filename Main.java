import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;

public class Main {
    public static void main(String[] args) {
        try {
            File file = new File("E:\\DS\\file3.xml");

            String expression = XML.toString(file).trim();
            String[] tokens = expression.split(" ");

            if (!Prefix.isValid(tokens)) {
                System.out.println("Invalid prefix expression, exiting.");
                System.exit(0);
            }
            System.out.println("Prefix: " + expression + "\nInfix: " + Prefix.Calculate(tokens));

        } catch (ParserConfigurationException e) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, e);
            System.exit(0);
        } catch (IOException e) {
            System.out.println("File not found\n" + e);
            System.exit(0);
        } catch (Exception e) {
            System.out.println("Unhandled exception\n" + e);
            System.exit(0);
        }
    }
}
