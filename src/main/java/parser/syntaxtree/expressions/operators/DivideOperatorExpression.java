package parser.syntaxtree.expressions.operators;

import parser.syntaxtree.expressions.Expression;

import java.util.Map;

public class DivideOperatorExpression extends OperatorExpression {
    public DivideOperatorExpression(Expression leftExpression, Expression rightExpression) {
        super(leftExpression, rightExpression);
    }

    @Override
    public int calculate(Map<String, Integer> variables) {
        return leftExpression.calculate(variables) / rightExpression.calculate(variables);
    }


    @Override
    public String info() {
        return "(" + super.leftExpression.info() + " / " + super.rightExpression.info() + ")";
    }
}
