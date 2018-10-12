
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	public static enum calcType {
		DOUBLE, LONG, BIGDECIMAL
	}
	public static calcType Type;

    private static void usage() {
        System.out.println("Usage:  java Main (double | long | decimal | test)");
        System.exit(1);
    }

    public static void main(String[] args) throws IOException {
		String mode = "";
        if (args.length != 1) {
            usage();
        }
        if ("double".equals(args[0])) {
            // Do nothing - that's what's implemented by default
            Type = calcType.DOUBLE;
            mode = "double";
        } else if ("long".equals(args[0])) {
			Type = calcType.LONG;
			mode = "long";
        } else if ("decimal".equals(args[0])) {
            Type = calcType.BIGDECIMAL;
            mode = "decimal";
        } else if ("test".equals(args[0])) {
            (new Tests()).runTests();
            System.exit(0);
        } else {
            System.out.println("Unrecognized argument:  ${args[0]}");
            usage();
        }
	RPNParser parser = new RPNParser();
        parser.setMode(mode);
	System.out.println("RPN, or post-fix, puts the operation at the end.  So, for example,");
	System.out.println("2 + 3 * 5 in RPN is \"3 5 * 2 +\".  Use \"negate\" for negation");
	System.out.println();
	BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	while (true) {
	    System.out.println();
	    System.out.println("Enter an RPN expression, or q to quit:");
	    String line = input.readLine();
	    if (line == null || "q".equals(line)) {
		break;
	    }
	    try {
		Expression expr = parser.parse(line);

		switch(Type) {
			case DOUBLE:
				System.out.println(expr + " = " + expr.evaluate());
				break;
			case LONG:
				LongVisitor visitor = new LongVisitor();
				expr.accept(visitor);
				System.out.println(expr + " = " + visitor.getResult());
				break;
			case BIGDECIMAL:
				BigDecimalVisitor decimal = new BigDecimalVisitor();
				expr.accept(decimal);
				System.out.println(expr + " = " + decimal.getResult());
				break;

		}

	    } catch (Exception ex) {
		System.out.println("Error in input:  " + ex);
	    }
	}
    }
}
