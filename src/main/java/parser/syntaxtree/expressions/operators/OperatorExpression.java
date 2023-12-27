package parser.syntaxtree.expressions.operators;

import parser.syntaxtree.expressions.Expression;

import java.util.HashSet;

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


}
