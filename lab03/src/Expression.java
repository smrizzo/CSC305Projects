interface Expression {

    /**
     * Evaluate this expression.
     */
    double evaluate();
    void accept(Visitor v);

    /**
     * Given a fully-parenthized infix representation of the expression
     */
    String toString();


}
