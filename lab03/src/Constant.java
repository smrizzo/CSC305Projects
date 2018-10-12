public class Constant implements Expression {

    final Number value;

    public Constant(Number value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value.toString();
    }

    @Override
    public double evaluate() {

        return value.doubleValue();
    }

    @Override
    public void accept(Visitor v) {
        v.visitConstant(this);
    }


}
