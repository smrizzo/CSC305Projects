import java.math.BigDecimal;
import java.math.RoundingMode;

public class BigDecimalVisitor implements Visitor  {

    BigDecimal result;
    BigDecimal left;
    BigDecimal right;

    @Override
    public void visitAddition(Addition addition) {
        Expression lhs = addition.lhs;
        Expression rhs = addition.rhs;
        lhs.accept(this);
        left = getResult();
        rhs.accept(this);
        right = getResult();

        result = left.add(right);
    }

    @Override
    public void visitSubtraction(Subtraction sub) {
        Expression lhs = sub.lhs;
        Expression rhs = sub.rhs;
        lhs.accept(this);
        left = getResult();
        rhs.accept(this);
        right = getResult();

        result = left.subtract(right);
    }

    @Override
    public void visitMultiplication(Multiplication mult) {
        Expression lhs = mult.lhs;
        Expression rhs = mult.rhs;
        lhs.accept(this);
        left = getResult();
        rhs.accept(this);
        right = getResult();

        result = left.multiply(right);
    }

    @Override
    public void visitDivision(Division div) {
        Expression lhs = div.lhs;
        Expression rhs = div.rhs;
        lhs.accept(this);
        left = getResult();
        rhs.accept(this);
        right = getResult();
        result = left.divide(right, 6, RoundingMode.HALF_EVEN);
    }

    @Override
    public void visitFactorial(Factorial factorial) {
        Expression arg;
        arg = factorial.arg;
        arg.accept(this);
        long count = Math.round(getResult().longValue());
        BigDecimal BigCount = new BigDecimal(getResult().toString());
        BigDecimal update;
        //This is good enough for a lab.  Factorial for non-integer types isn't
        //defined, so we just round to the nearest int.
        if (count < 0) {
            throw new IllegalArgumentException("" + count + "! not defined");
        }
        int j = 1;
        BigDecimal factValue = new BigDecimal(1);
        BigDecimal num;
        for (int i = 0; i < count; i++) {
            num = new BigDecimal(i);
            update = BigCount.subtract(num);
            factValue = factValue.multiply(update);
        }
        result = factValue;
    }

    @Override
    public void visitNegation(Negation negate) {
        Expression arg;
        arg = negate.arg;
        arg.accept(this);
        BigDecimal update = new BigDecimal(getResult().toString());
        result = update.multiply(new BigDecimal(-1));
    }

    @Override
    public void visitConstant(Constant constant) {
        result = new BigDecimal(constant.value.toString());

    }

    public BigDecimal getResult() {
        return result;
    }
}
