import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import java.util.Stack;
import org.w3c.dom.Element;

public class XML {

    static String traverseXML(Node root) {
        StringBuilder result = new StringBuilder();
        Stack<Node> stack = new Stack<>();
        stack.push(root);

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