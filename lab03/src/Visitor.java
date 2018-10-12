public interface Visitor {

    void visitAddition(Addition addition);
    void visitSubtraction(Subtraction sub);
    void visitMultiplication(Multiplication mult);
    void visitDivision(Division div);
    void visitFactorial(Factorial factorial);
    void visitNegation(Negation negate);
    void visitConstant(Constant constant);

}
