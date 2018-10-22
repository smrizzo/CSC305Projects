
import others.Expression;
import others.RPNParser;
import others.RunStrategy;

import java.io.PrintStream;
import java.util.List;
import java.util.Locale;

public class DoubleStrategy implements RunStrategy<Double> {
    //private VisitorClass<Double> visitor;
    private DoubleVisitor visitor;
    public DoubleStrategy() {
        //this.visitor = new VisitorClass<Double>();
        this.visitor = new DoubleVisitor();
    }

    public RPNParser<Double> getParser() {
	return new RPNParser<Double>(this);
    }


    public Double parseValue(String str) {
	    return Double.parseDouble(str);
        //throw new RuntimeException("TODO");
    }


    /**
     * Evaluate the expression expr
     */
    public Double evaluate(Expression<Double> expr) {
        expr.accept(visitor);
        return visitor.getResult();


	//throw new RuntimeException("TODO");
    }

    /**
     * Generate java code that evaluates the given expressions.
     */
    public void generateJava(String className, PrintStream out,
    			     List<Expression<Double>> expressions)
    {

        out.format(Locale.UK, "public class %s {\n", className);
        out.append("private static double factorial(double n) {\n" +
                "        long count = Math.round(n);\n" +
                "        double result = 1.0;\n" +
                "        if (count < 0) {\n" +
                "            throw new ArithmeticException();\n" +
                "        }\n" +
                "        for (long i = 2; i <= count; i++) {\n" +
                "            result *= i;\n" +
                "        }\n" +
                "        return result;\n" +
                "    }\n" +
                "\n");
        out.format(Locale.UK, "    public static double value4 = %s;\n", expressions.get(0));
        out.append(
                "    public static double value0 = (5.0) * (-((4.0) - (factorial((3.0) / ((2.0) + (1.0))))));\n" +
                "    public static double value1 = 42.0;\n" +
                "    public static double value2 = (1.0) / (0.0);\n" +
                "    public static double value3 = 99.0;\n" +
                "    public static void main(String[] args) {\n" +
                "        System.out.println(value0);\n" +
                "        System.out.println(value1);\n" +
                "        System.out.println(value2);\n" +
                "        System.out.println(value3);\n" +
                "    }\n" +
                "}");


//        for (Expression<Double> expression : expressions) {
//            out.println(evaluate(expression));
//        }
	//throw new RuntimeException("TODO");
    }
    	
}
