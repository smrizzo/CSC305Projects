

public abstract class UnaryExpression implements Expression {

    protected final Expression arg;

    public UnaryExpression(Expression arg) {
        this.arg = arg;
    }

    abstract protected double evaluate(double argVal);

    @Override
    public final double evaluate() {
        return evaluate(arg.evaluate());
    }
}

