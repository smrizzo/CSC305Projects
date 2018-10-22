import others.*;

public class LongVisitor implements ExpressionVisitor<Long> {
    private Long result;
    private Long left;
    private Long right;

    public Long getResult() {
        return result;
    }

    @Override
    public void visitAddition(Addition<Long> expr) {
        Expression<Long> lhs = expr.getLhs();
        Expression<Long> rhs = expr.getRhs();
        lhs.accept(this);
        left = getResult();
        rhs.accept( this);
        right = getResult();

        result = left + right;
    }

    @Override
    public void visitConstant(Constant<Long> expr) {
        result = expr.getValue();
    }

    @Override
    public void visitDivision(Division<Long> expr) {
        Expression<Long> lhs = expr.getLhs();
        Expression<Long> rhs = expr.getRhs();
        lhs.accept(this);
        left = getResult();
        rhs.accept( this);
        right = getResult();

        result = left / right;
    }

    @Override
    public void visitFactorial(Factorial<Long> expr) {
        Expression<Long> arg = expr.getArg();
        arg.accept(this);
        long count = Math.round(getResult());
        // This is good enough for a lab.  Factorial for non-integer types isn't
        // defined, so we just round to the nearest int.
        if (count < 0) {
            throw new IllegalArgumentException("" + count + "! not defined");
        }
        long factValue = 1;
        for (int i = 2; i <= count; i++) {
            factValue *= i;
        }
        result = factValue;
    }

    @Override
    public void visitMultiplication(Multiplication<Long> expr) {
        Expression<Long> lhs = expr.getLhs();
        Expression<Long> rhs = expr.getRhs();
        lhs.accept(this);
        left = getResult();
        rhs.accept( this);
        right = getResult();

        result = left * right;
    }

    @Override
    public void visitNegation(Negation<Long> expr) {
        Expression<Long> arg = expr.getArg();
        arg.accept(this);
        result = -getResult();
    }

    @Override
    public void visitSubtraction(Subtraction<Long> expr) {
        Expression<Long> lhs = expr.getLhs();
        Expression<Long> rhs = expr.getRhs();
        lhs.accept(this);
        left = getResult();
        rhs.accept( this);
        right = getResult();
        result = left - right;
    }
}
