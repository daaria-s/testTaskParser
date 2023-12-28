package parser.syntaxtree.expressions.operators;

import parser.syntaxtree.expressions.Expression;

import java.util.HashSet;
import java.util.Objects;

abstract public class OperatorExpression implements Expression {
    Expression leftExpression;
    Expression rightExpression;

    public OperatorExpression(Expression leftExpression, Expression rightExpression) {
        this.leftExpression = leftExpression;
        this.rightExpression = rightExpression;
    }

    @Override
    public HashSet<String> getUsedVariables() {
        var result = leftExpression.getUsedVariables();
        result.addAll(rightExpression.getUsedVariables());
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OperatorExpression that = (OperatorExpression) o;
        return Objects.equals(leftExpression, that.leftExpression) && Objects.equals(rightExpression, that.rightExpression);
    }

    @Override
    public int hashCode() {
        return Objects.hash(leftExpression, rightExpression);
    }
}
