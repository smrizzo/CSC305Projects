


public class Factorial extends UnaryExpression {

    public Factorial(Expression arg) {
        super(arg);
    }

    @Override
    protected double evaluate(double argVal) {
        long count = Math.round(argVal);
        // This is good enough for a lab.  Factorial for non-integer types isn't
        // defined, so we just round to the nearest int.
        if (count < 0) {
            throw new IllegalArgumentException("" + count + "! not defined");
        }
        double result = 1.0;
        for (int i = 1; i <= count; i++) {
            result *= i;
        }
        return result;
    }


    @Override
    public void accept(Visitor v) {
        v.visitFactorial(this);
    }

    @Override
    public String toString() {
        return "(" + arg + ")!";
    }
}

