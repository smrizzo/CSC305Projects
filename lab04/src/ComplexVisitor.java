import others.*;

public class ComplexVisitor implements ExpressionVisitor<Complex> {
    private Complex result;
    private Complex left;
    private Complex right;

    @Override
    public void visitAddition(Addition<Complex> expr) {
        Expression<Complex> lhs = expr.getLhs();
        Expression<Complex> rhs = expr.getRhs();
        lhs.accept(this);
        left = getResult();
        rhs.accept(this);
        right = getResult();
        result = left.add(right);

    }

    @Override
    public void visitConstant(Constant<Complex> expr) {
        result = expr.getValue();
    }

    @Override
    public void visitDivision(Division<Complex> expr) {
        Expression<Complex> lhs = expr.getLhs();
        Expression<Complex> rhs = expr.getRhs();
        lhs.accept(this);
        left = getResult();
        rhs.accept(this);
        right = getResult();
        result = left.divide(right);
    }

    @Override
    public void visitFactorial(Factorial<Complex> expr) {
        Expression<Complex> arg = expr.getArg();
        arg.accept(this);
        result = getResult().factorial();
    }

    @Override
    public void visitMultiplication(Multiplication<Complex> expr) {
        Expression<Complex> lhs = expr.getLhs();
        Expression<Complex> rhs = expr.getRhs();
        lhs.accept(this);
        left = getResult();
        rhs.accept(this);
        right = getResult();
        result = left.multiply(right);
    }

    @Override
    public void visitNegation(Negation<Complex> expr) {
        Expression<Complex> arg = expr.getArg();
        arg.accept(this);
        result = getResult().negate();

    }

    @Override
    public void visitSubtraction(Subtraction<Complex> expr) {
        Expression<Complex> lhs = expr.getLhs();
        Expression<Complex> rhs = expr.getRhs();
        lhs.accept(this);
        left = getResult();
        rhs.accept(this);
        right = getResult();
        result = left.subtract(right);

    }

    public Complex getResult() {
        return result;
    }
}
