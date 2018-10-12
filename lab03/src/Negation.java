


public class Negation extends UnaryExpression {

    public Negation(Expression arg) {
        super(arg);
    }

    @Override
    protected double evaluate(double argVal) {
        return -argVal;
    }

    @Override
    public void accept(Visitor v) {
        v.visitNegation(this);
    }

    @Override
    public String toString() {
        return "negate(" + arg + ")";
    }
}

