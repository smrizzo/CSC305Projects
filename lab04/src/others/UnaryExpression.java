/*
 * NOTE:  Classes in the "others" package are not to be modified.
 *        This is like what happens in real programming project, where
 *        you're deadling with code that is owned by other people.
 */

package others;

public abstract class UnaryExpression<T> implements Expression<T> {

    private final Expression<T> arg;

    public UnaryExpression(Expression<T> arg) {
        this.arg = arg;
    }

    public Expression<T> getArg() {
	return arg;
    }

}

