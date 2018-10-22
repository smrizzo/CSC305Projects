/*
 * NOTE:  Classes in the "others" package are not to be modified.
 *        This is like what happens in real programming project, where
 *        you're deadling with code that is owned by other people.
 */

package others;

public abstract class BinaryExpression<T> implements Expression<T> {

    private final Expression<T> lhs;
    private final Expression<T> rhs;
    
    BinaryExpression(Expression<T> lhs, Expression<T> rhs) {
        this.lhs = lhs;
        this.rhs = rhs;
    }

    abstract protected String operatorName();

    @Override
    public String toString() {
        return "(" + lhs.toString() + " " + operatorName() + " " + rhs.toString() + ")";
    }

    public Expression<T> getLhs() {
	return lhs;
    }

    public Expression<T> getRhs() {
	return rhs;
    }
}
