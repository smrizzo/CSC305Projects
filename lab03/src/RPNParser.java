import java.math.BigDecimal;
import java.util.Deque;
import java.util.ArrayDeque;

/**
 * A little parser to parse expresssions.  The only thing you need to change
 * here is the kind of Number that is provided to the Constant objects.
 */
public class RPNParser {
    public String mode;


    void setMode(String mode) {
        this.mode = mode;
    }

    public Expression parse(String line) {
        Deque<Expression> stack = new ArrayDeque<Expression>();
        for (String s : line.split("\\s+")) {
            if ("negate".equals(s)) {
                stack.push(new Negation(stack.pop()));
            } else if ("!".equals(s)) {
                stack.push(new Factorial(stack.pop()));
            } else if ("+".equals(s)) {
                Expression rhs = stack.pop();
                stack.push(new Addition(stack.pop(), rhs));
            } else if ("-".equals(s)) {
                Expression rhs = stack.pop();
                stack.push(new Subtraction(stack.pop(), rhs));
            } else if ("*".equals(s)) {
                Expression rhs = stack.pop();
                stack.push(new Multiplication(stack.pop(), rhs));
            } else if ("/".equals(s)) {
                Expression rhs = stack.pop();
                stack.push(new Division(stack.pop(), rhs));
            } else {
                Number n;
                if(this.mode.equals("decimal")) {
                    n = new BigDecimal(s);
                } else if (this.mode.equals("long")) {
                    n = java.lang.Long.valueOf(s);
                } else {
                    n = java.lang.Double.valueOf(s);
                }

                stack.push(new Constant(n));
            }
        }
        if (stack.size() == 1) {
            return stack.pop();
        } else {
            throw new IllegalArgumentException("Expression stack has " + 
                            stack.size() + " elements, not the expected 1");
        }
    }
}
