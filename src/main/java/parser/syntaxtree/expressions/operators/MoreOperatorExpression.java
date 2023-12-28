package parser.syntaxtree.expressions.operators;

import parser.syntaxtree.expressions.Expression;

import java.util.Map;

public class MoreOperatorExpression extends OperatorExpression{
    public MoreOperatorExpression(Expression leftExpression, Expression rightExpression) {
        super(leftExpression, rightExpression);
    }

    @Override
    public int calculate(Map<String, Integer> variables) {
        if (leftExpression.calculate(variables) > rightExpression.calculate(variables)) {
            return 1;
        }
        return 0;
    }
    @Override
    public String info() {
        return  "(" + super.leftExpression.info() + " > " + super.rightExpression.info() + ")";
    }
}
