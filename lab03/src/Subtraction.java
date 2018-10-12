

class Subtraction extends BinaryExpression {

    public Subtraction(Expression lhs, Expression rhs) {
        super(lhs, rhs);
    }

    @Override
    protected double evaluate(double lhs, double rhs) {
        //System.out.println("Got inside subtraction evaluate");
        return lhs - rhs;
    }

    @Override
    protected String operatorName() {
        return "-";
    }


    @Override
    public void accept(Visitor v) {
        v.visitSubtraction(this);
    }
}
