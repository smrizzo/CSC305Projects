/*
 * 	DO NOT MODIFY THIS FILE
 *
 *  	This class can be used for launching your program.  You should make
 *	all modifications to Program.java, not here.
 */


import others.Complex;
import others.Expression;
import others.RPNParser;
import others.RunStrategy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.ArrayList;

public abstract class Main {

    /**
     * Print a usage message and exit
     */
    protected final void usage() {
        System.out.println("Usage:  java Main (double | complex | long | test)");
        System.exit(1);
    }

    /**
     * Runs unit tests.
     */
    abstract protected void runTests();

    /**
     * Get the strategy object for various types of computations
     */
    abstract protected RunStrategy<Double> getDoubleStrategy();
    abstract protected RunStrategy<Complex> getComplexStrategy();
    abstract protected RunStrategy<Long> getLongStrategy();


    public final void run(String[] args) throws IOException {
        if (args.length != 1) {
            usage();
        }
        if ("double".equals(args[0])) {
	    runWith(getDoubleStrategy());
	} else if ("complex".equals(args[0])) {
	    runWith(getComplexStrategy());
	} else if ("long".equals(args[0])) {
	    runWith(getLongStrategy());
        } else if ("test".equals(args[0])) {
	    runTests();
            System.exit(0);
        } else {
            System.out.println("Unrecognized argument:  ${args[0]}");
            usage();
        }
    }

    private final <T> void runWith(final RunStrategy<T> strategy) 
	    throws IOException
    {
	System.out.println();
	System.out.println();
	System.out.println();
	System.out.println("RPN, or post-fix, puts the operation at the "
			   +"end.  So, for example,");
	System.out.println("2 + 3 * 5 in RPN is \"3 5 * 2 +\".  Use "
			  + "\"negate\" for negation");
	System.out.println();

	BufferedReader input = new BufferedReader(
				new InputStreamReader(System.in));
	ArrayList<Expression<T>> expressions = new ArrayList<>();
	RPNParser<T> parser = strategy.getParser();
	while (true) {
	    System.out.println();
	    System.out.println("Enter an RPN expression, \"generate:\", "
	    		       + "\"generate:ClassName\", or q to quit:");
	    String line = input.readLine();
	    if (line == null || "q".equals(line)) {
		break;
	    } else if (line.startsWith("generate:")) {
		String className = line.substring(9).trim();
		if (className.equals("")) {
		    strategy.generateJava("stdout", System.out, expressions);
		} else {
		    String fn =  className + ".java";
		    System.out.println("Generating file " + fn);
		    PrintStream out = new PrintStream(fn);
		    strategy.generateJava(className, out, expressions);
		    out.close();
		}
	    } else {
		try {
		    Expression<T> expr = parser.parse(line);
		    expressions.add(expr);
		    System.out.println("" + expr + " = " 
		    	  	       + strategy.evaluate(expr));
		} catch (Exception ex) {
		    System.out.println("Error in input:  " + ex);
		}
	    }
	}
    }

    public static void main(String[] args) throws IOException {
	(new Program()).run(args);
    }
}
