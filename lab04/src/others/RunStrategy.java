
package others;

import java.io.PrintStream;
import java.util.List;

public interface RunStrategy<T> {

    public RPNParser<T> getParser();


    /**
     * Parse a string into an object of the apprpriate value type, T.
     * Thow a runtime exception if it cannot be parsed.
     */
    public T parseValue(String str);


    /**
     * Evaluate the expression expr
     */
    public T evaluate(Expression<T> expr);

    /**
     * Generate java code that evaluates the given expressions.
     */
    public void generateJava(String className, PrintStream out,
    			     List<Expression<T>> expressions);
    	
}


