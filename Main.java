import java.io.File;
import java.io.IOException;
import java.util.Stack;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

public class Main {
    public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
        File file = new File("prefix.xml");
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(file);
        doc.getDocumentElement().normalize();
        Node root = doc.getDocumentElement();

        String expression = XML.traverseXML(root).trim();
        String[] tokens = expression.split(" ");

        Stack<Integer> evaluationStack = new Stack<>();
        Stack<String> infixStack = new Stack<>();
        final String operator = "+-/*";

        for (int i = tokens.length - 1; i >= 0; i--) {
            if (operator.contains(tokens[i])) {
                int a = evaluationStack.pop();
                int b = evaluationStack.pop();
                String infix1 = infixStack.pop();
                String infix2 = infixStack.pop();

                switch (tokens[i]) {
                    case "+":
                        evaluationStack.push(a + b);
                        infixStack.push("(" + infix1 + " + " + infix2 + ")");
                        break;

                    case "-":
                        evaluationStack.push(a - b);
                        infixStack.push("(" + infix1 + " - " + infix2 + ")");
                        break;

                    case "*":
                        evaluationStack.push(a * b);
                        infixStack.push("(" + infix1 + " * " + infix2 + ")");
                        break;

                    case "/":
                        try {
                            evaluationStack.push(a / b);
                        } catch (ArithmeticException e) {
                            System.out.println(e);
                        }
                        infixStack.push("(" + infix1 + " / " + infix2 + ")");
                        break;
                }
            } else {
                evaluationStack.push(Integer.parseInt(tokens[i]));
                infixStack.push(tokens[i]);
            }
        }
        System.out.println(infixStack.pop() + " = " + evaluationStack.pop());
    }
}