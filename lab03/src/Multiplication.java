

class Multiplication extends BinaryExpression {

    public Multiplication(Expression lhs, Expression rhs) {
        super(lhs, rhs);
    }

    @Override
    protected double evaluate(double lhs, double rhs) {
        System.out.println("Got inside multiplication evaluate");
        return lhs * rhs;
    }

    @Override
    protected String operatorName() {
        return "*";
    }


    @Override
    public void accept(Visitor v) {
        v.visitMultiplication(this);
    }
}
