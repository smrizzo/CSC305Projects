import others.*;

import java.io.PrintStream;
import java.util.List;

public class ComplexStrategy implements RunStrategy<Complex> {


    private ComplexVisitor visitor;
    public ComplexStrategy() {
        this.visitor = new ComplexVisitor();
    }
    @Override
    public RPNParser<Complex> getParser() {
        return new RPNParser<Complex>(this);
    }

    @Override
    public Complex parseValue(String str) {
        return new Complex(str);
    }

    @Override
    public Complex evaluate(Expression<Complex> expr) {
        System.out.println("Got inside evaluate");
        expr.accept(visitor);
        return visitor.getResult();
    }

    @Override
    public void generateJava(String className, PrintStream out, List<Expression<Complex>> expressions) {

    }
}
