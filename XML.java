import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.IOException;
import java.util.Stack;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class XML {
    public static Node getRoot(File file) throws SAXException, IOException, ParserConfigurationException{
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(file);
        doc.getDocumentElement().normalize();

        return doc.getDocumentElement();
    }

    static String toString(File file) throws SAXException, IOException, ParserConfigurationException {
        StringBuilder result = new StringBuilder();
        Stack<Node> stack = new Stack<>();
        stack.push(getRoot(file));

        while (!stack.isEmpty()) {
            Node node = stack.pop();

            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) node;
                String nodeName = element.getNodeName();

                if ("atom".equals(nodeName) || "operator".equals(nodeName)) {
                    result.append(" " + element.getAttribute("value"));
                }

                NodeList nodeList = element.getChildNodes();
                if (nodeList != null && nodeList.getLength() > 0) {
                    for (int i = nodeList.getLength() - 1; i >= 0; i--) {
                        Node childNode = nodeList.item(i);
                        stack.push(childNode);
                    }
                }
            }
        }

        return result.toString();
    }
}