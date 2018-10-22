/*
 * NOTE:  Classes in the "others" package are not to be modified.
 *        This is like what happens in real programming project, where
 *        you're deadling with code that is owned by other people.
 */

package others;


public final class Constant<T> implements Expression<T> {

    private final T value;

    public Constant(T value) {
        this.value = value;
    }

    public T getValue() {
	return value;
    }

    @Override
    public void accept(ExpressionVisitor<T> v) {
	v.visitConstant(this);
    }

    @Override
    public String toString() {
        return value.toString();
    }
}
