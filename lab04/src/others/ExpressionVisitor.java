/*
 * NOTE:  Classes in the "others" package are not to be modified.
 *        This is like what happens in real programming project, where
 *        you're deadling with code that is owned by other people.
 */

package others;


public interface ExpressionVisitor<T> {

    public void visitAddition(Addition<T> expr);

    public void visitConstant(Constant<T> expr);

    public void visitDivision(Division<T> expr);

    public void visitFactorial(Factorial<T> expr);

    public void visitMultiplication(Multiplication<T> expr);

    public void visitNegation(Negation<T> expr);

    public void visitSubtraction(Subtraction<T> expr);

}
