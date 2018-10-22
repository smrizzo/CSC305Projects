/*
 * NOTE:  Classes in the "others" package are not to be modified.
 *        This is like what happens in real programming project, where
 *        you're deadling with code that is owned by other people.
 */

package others;


public class Negation<T> extends UnaryExpression<T> {

    public Negation(Expression<T> arg) {
        super(arg);
    }

    @Override
    public void accept(ExpressionVisitor<T> v) {
	v.visitNegation(this);
    }

    @Override
    public String toString() {
        return "negate(" + getArg() + ")";
    }
}

