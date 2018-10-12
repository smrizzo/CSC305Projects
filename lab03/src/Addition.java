

class Addition extends BinaryExpression {

    public Addition(Expression lhs, Expression rhs) {

        super(lhs, rhs);

    }

    @Override
    protected double evaluate(double lhs, double rhs) {
        return lhs + rhs;
    }


    @Override
    protected String operatorName() {
        return "+";
    }


    @Override
    public void accept(Visitor v) {
        v.visitAddition(this);
    }
}
