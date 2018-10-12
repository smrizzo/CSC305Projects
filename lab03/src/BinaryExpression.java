
public abstract class BinaryExpression implements Expression {

    public final Expression lhs;
    public final Expression rhs;
    
    BinaryExpression(Expression lhs, Expression rhs) {
        this.lhs = lhs;
        this.rhs = rhs;
    }

    abstract protected double evaluate(double lhs, double rhs);
    //abstract protected long evaluate(long lhs, long rhs);



    @Override
    public final double evaluate() {
        //System.out.println("Got inside BinaryExpressions evaluate");
        return evaluate(lhs.evaluate(), rhs.evaluate());
    }

    abstract protected String operatorName();

    @Override
    public String toString() {
        return "(" + lhs.toString() + " " + operatorName() + " " + rhs.toString() + ")";
    }


}
