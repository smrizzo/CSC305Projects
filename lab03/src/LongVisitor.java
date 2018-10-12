public class LongVisitor implements Visitor {
    public long result;
    public long left;
    public long right;




    @Override
    public void visitAddition(Addition addition) {
        Expression lhs = addition.lhs;;
        Expression rhs = addition.rhs;;

        lhs.accept(this);
        left = getResult();
        rhs.accept(this);
        right = getResult();

        result = left + right;

    }

    @Override
    public void visitSubtraction(Subtraction sub) {
        Expression lhs = sub.lhs;;
        Expression rhs = sub.rhs;;

        lhs.accept(this);
        left = getResult();
        rhs.accept(this);
        right = getResult();

        result = left - right;

    }

    @Override
    public void visitMultiplication(Multiplication mult) {
        Expression lhs = mult.lhs;;
        Expression rhs = mult.rhs;;

        lhs.accept(this);
        left = getResult();
        rhs.accept(this);
        right = getResult();

        result = left * right;
    }

    @Override
    public void visitDivision(Division div) {
        Expression lhs = div.lhs;;
        Expression rhs = div.rhs;;

        lhs.accept(this);
        left = getResult();
        rhs.accept(this);
        right = getResult();

        result = left / right;
    }

    @Override
    public void visitFactorial(Factorial factorial) {
        Expression arg;
        arg = factorial.arg;
        arg.accept(this);
        long count = Math.round(getResult());
        //System.out.println("visited Factorial"+ count);
        //This is good enough for a lab.  Factorial for non-integer types isn't
        //defined, so we just round to the nearest int.
        if (count < 0) {
            throw new IllegalArgumentException("" + count + "! not defined");
        }
        long factValue = 1;
        for (int i = 1; i <= count; i++) {
            factValue *= i;
        }
        result = factValue;


    }

    @Override
    public void visitNegation(Negation negate) {
        Expression arg;
        arg = negate.arg;
        arg.accept(this);
        result = -getResult();

    }

    @Override
    public void visitConstant(Constant constant) {
        result = constant.value.longValue();
    }

    public long getResult() {
        return result;
    }


}
