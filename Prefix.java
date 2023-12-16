import java.util.Stack;

public class Prefix {
    static String operator = "+-/*";

    public static String Calculate(String[] tokens) {
        Stack<Integer> evaluationStack = new Stack<>();
        Stack<String> infixStack = new Stack<>();

        for (int i = tokens.length - 1; i >= 0; i--) {
            if (operator.contains(tokens[i])) {
                int operand1 = evaluationStack.pop();
                int operand2 = evaluationStack.pop();
                String infix1 = infixStack.pop();
                String infix2 = infixStack.pop();

                switch (tokens[i]) {
                    case "+":
                        evaluationStack.push(operand1 + operand2);
                        infixStack.push("(" + infix1 + " + " + infix2 + ")");
                        break;

                    case "-":
                        evaluationStack.push(operand1 - operand2);
                        infixStack.push("(" + infix1 + " - " + infix2 + ")");
                        break;

                    case "*":
                        evaluationStack.push(operand1 * operand2);
                        infixStack.push("(" + infix1 + " * " + infix2 + ")");
                        break;

                    case "/":
                        try {
                            evaluationStack.push(operand1 / operand2);
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
        return infixStack.pop() + " = " + evaluationStack.pop();
    }

    public static Boolean isValid(String[] tokens) {
        int operatorCount = 0;
        int length = tokens.length;
        if (operator.contains(tokens[length - 1]) || operator.contains(tokens[length - 2])) {
            return false;
        }
        for (int i = 0; i < length; i++) {
            if (operator.contains(tokens[i])) {
                operatorCount++;
            }
        }
        return length - 2 * operatorCount == 1;
    }
}
