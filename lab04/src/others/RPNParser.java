/*
 * NOTE:  Classes in the "others" package are not to be modified.
 *        This is like what happens in real programming project, where
 *        you're deadling with code that is owned by other people.
 */

package others;

import java.util.Deque;
import java.util.ArrayDeque;

/**
 * A little parser to parse expressions that can be evaluated to a
 * value type T.
 */
public class RPNParser<T> {

    private RunStrategy<T> valueParser;

    public RPNParser(RunStrategy<T> valueParser) {
	this.valueParser = valueParser;
    }

    /**
     * Parse a string into a valid expression, or throw a runtime exception
     * if there is a parse error.
     */
    public Expression<T> parse(String line) {
        Deque<Expression<T>> stack = new ArrayDeque<Expression<T>>();
        for (String s : line.split("\\s+")) {
            if ("negate".equals(s)) {
                stack.push(new Negation<T>(stack.pop()));
            } else if ("!".equals(s)) {
                stack.push(new Factorial<T>(stack.pop()));
            } else if ("+".equals(s)) {
                Expression<T> rhs = stack.pop();
                stack.push(new Addition<T>(stack.pop(), rhs));
            } else if ("-".equals(s)) {
                Expression<T> rhs = stack.pop();
                stack.push(new Subtraction<T>(stack.pop(), rhs));
            } else if ("*".equals(s)) {
                Expression<T> rhs = stack.pop();
                stack.push(new Multiplication<T>(stack.pop(), rhs));
            } else if ("/".equals(s)) {
                Expression<T> rhs = stack.pop();
                stack.push(new Division<T>(stack.pop(), rhs));
            } else {
                T value = valueParser.parseValue(s);
                stack.push(new Constant<T>(value));
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
