/*
 * NOTE:  Classes in the "others" package are not to be modified.
 *        This is like what happens in real programming project, where
 *        you're deadling with code that is owned by other people.
 */

package others;

public interface Expression<T> {

    /**
     * Given a fully-parenthized infix representation of the expression,
     * for debugging purposes.
     */
    String toString();

    void accept(ExpressionVisitor<T> v);

}
