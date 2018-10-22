import others.Expression;
import others.RPNParser;
import others.RunStrategy;

import java.io.PrintStream;
import java.util.List;

public class LongStategy implements RunStrategy<Long> {

    private LongVisitor visitor;

    public LongStategy() {
        this.visitor = new LongVisitor();
    }
    @Override
    public RPNParser<Long> getParser() {
        return new RPNParser<Long>(this);
    }

    @Override
    public Long parseValue(String str) {
        return Long.parseLong(str);
    }

    @Override
    public Long evaluate(Expression<Long> expr) {
        expr.accept(visitor);
        return visitor.getResult();
    }

    @Override
    public void generateJava(String className, PrintStream out, List<Expression<Long>> expressions) {

    }
}
