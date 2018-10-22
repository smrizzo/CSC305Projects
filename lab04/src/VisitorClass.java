//public class VisitorClass<T extends Number> implements ExpressionVisitor<T> {
//
//    private T left;
//    private T right;
//    private T result;
//
//    public T getResult() {
//        return result;
//    }
//
//    @Override
//    public void visitAddition(Addition<T> expr) {
//        Expression<T> lhs = expr.getLhs();
//        Expression<T> rhs = expr.getRhs();
//        lhs.accept(this);
//        left = getResult();
//        rhs.accept( this);
//        right = getResult();
//        if(left instanceof Double) {
//
//            result = (T) Double.valueOf((left.doubleValue() + right.doubleValue()));
//        } else if (left instanceof  Long) {
//            result = (T) Long.valueOf((left.longValue() + right.longValue()));
//        }
//
//    }
//
//    @Override
//    public void visitConstant(Constant<T> expr) {
//        result = expr.getValue();
//    }
//
//    @Override
//    public void visitDivision(Division<T> expr) {
//        Expression<T> lhs = expr.getLhs();
//        Expression<T> rhs = expr.getRhs();
//        lhs.accept(this);
//        left = getResult();
//        rhs.accept( this);
//        right = getResult();
//        if(left instanceof Double) {
//            result = (T) Double.valueOf((left.doubleValue() / right.doubleValue()));
//        } else if (left instanceof  Long) {
//            result = (T) Long.valueOf((left.longValue() / right.longValue()));
//        }
//    }
//
//    @Override
//    public void visitFactorial(Factorial<T> expr) {
//        Expression<T> arg = expr.getArg();
//        arg.accept(this);
//        long count = Math.round(getResult().longValue());
//
//        if (count < 0) {
//            throw new IllegalArgumentException("" + count + "! not defined");
//        }
//        if(getResult() instanceof Double) {
//            Double factValue = 1.0;
//            for (long i = 2; i <= count; i++) {
//                factValue *= i;
//
//            }
//            result = (T) factValue;
//        } else if (getResult() instanceof Long) {
//            long factValue = 1;
//            for (int i = 1; i <= count; i++) {
//                factValue *= i;
//            }
//            Long newFactValue = factValue;
//            result = (T) newFactValue;
//        }
//    }
//
//
//    @Override
//    public void visitMultiplication(Multiplication<T> expr) {
//        Expression<T> lhs = expr.getLhs();
//        Expression<T> rhs = expr.getRhs();
//        lhs.accept(this);
//        left = getResult();
//        rhs.accept( this);
//        right = getResult();
//        if(left instanceof Double) {
//            result = (T) Double.valueOf((left.doubleValue() * right.doubleValue()));
//        } else if (left instanceof  Long) {
//            result = (T) Long.valueOf((left.longValue() * right.longValue()));
//        }
//    }
//
//    @Override
//    public void visitNegation(Negation<T> expr) {
//        Expression<T> arg = expr.getArg();
//        arg.accept(this);
//        if(getResult() instanceof Double) {
//            result = (T) Double.valueOf(-getResult().doubleValue());
//        } else if(getResult() instanceof Long) {
//            result = (T) Long.valueOf(-getResult().longValue());
//        }
//    }
//
//    @Override
//    public void visitSubtraction(Subtraction<T> expr) {
//        Expression<T> lhs = expr.getLhs();
//        Expression<T> rhs = expr.getRhs();
//        lhs.accept(this);
//        left = getResult();
//        rhs.accept( this);
//        right = getResult();
//        if(left instanceof Double) {
//            result = (T) Double.valueOf((left.doubleValue() - right.doubleValue()));
//        } else if (left instanceof  Long) {
//            result = (T) Long.valueOf((left.longValue() - right.longValue()));
//        }
//    }
//}
