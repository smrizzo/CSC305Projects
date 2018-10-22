import others.*;

public class DoubleVisitor implements ExpressionVisitor<Double> {
    private Double left;
    private Double right;
    private Double result;

    public Double getResult() {
        return result;
    }


    @Override
    public void visitAddition(Addition<Double> expr) {
        Expression<Double> lhs = expr.getLhs();
        Expression<Double> rhs = expr.getRhs();
        lhs.accept(this);
        left = getResult();
        rhs.accept( this);
        right = getResult();

        result = left + right;
    }

    @Override
    public void visitConstant(Constant<Double> expr) {
        result = expr.getValue();
    }

    @Override
    public void visitDivision(Division<Double> expr) {
        Expression<Double> lhs = expr.getLhs();
        Expression<Double> rhs = expr.getRhs();
        lhs.accept(this);
        left = getResult();
        rhs.accept( this);
        right = getResult();

        result = left / right;
    }

    @Override
    public void visitFactorial(Factorial<Double> expr) {
        Expression<Double> arg = expr.getArg();
        arg.accept(this);
        long count = Math.round(getResult().longValue());
        // This is good enough for a lab.  Factorial for non-integer types isn't
        // defined, so we just round to the nearest int.
        if (count < 0) {
            throw new IllegalArgumentException("" + count + "! not defined");
        }
        double factValue = 1.0;
        for (long i = 2; i <= count; i++) {
            factValue *= i;
        }
        result = factValue;
    }

    @Override
    public void visitMultiplication(Multiplication<Double> expr) {
        Expression<Double> lhs = expr.getLhs();
        Expression<Double> rhs = expr.getRhs();
        lhs.accept(this);
        left = getResult();
        rhs.accept( this);
        right = getResult();

        result = left * right;
    }

    @Override
    public void visitNegation(Negation<Double> expr) {
        Expression<Double> arg = expr.getArg();
        arg.accept(this);
        result = -getResult();
    }

    @Override
    public void visitSubtraction(Subtraction<Double> expr) {
        Expression<Double> lhs = expr.getLhs();
        Expression<Double> rhs = expr.getRhs();
        lhs.accept(this);
        left = getResult();
        rhs.accept( this);
        right = getResult();
        result = left - right;
    }
}
