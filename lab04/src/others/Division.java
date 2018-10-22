/*
 * NOTE:  Classes in the "others" package are not to be modified.
 *        This is like what happens in real programming project, where
 *        you're deadling with code that is owned by other people.
 */

package others;


public final class Division<T> extends BinaryExpression<T> {

    public Division(Expression<T> lhs, Expression<T> rhs) {
        super(lhs, rhs);
    }

    @Override
    public void accept(ExpressionVisitor<T> v) {
	v.visitDivision(this);
    }

    @Override
    protected String operatorName() {
        return "/";
    }

}
