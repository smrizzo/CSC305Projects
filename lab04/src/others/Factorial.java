/*
 * NOTE:  Classes in the "others" package are not to be modified.
 *        This is like what happens in real programming project, where
 *        you're deadling with code that is owned by other people.
 */

package others;


public class Factorial<T> extends UnaryExpression<T> {

    public Factorial(Expression<T> arg) {
        super(arg);
    }

    @Override
    public void accept(ExpressionVisitor<T> v) {
	v.visitFactorial(this);
    }

    @Override
    public String toString() {
        return "(" + getArg() + ")!";
    }
}

