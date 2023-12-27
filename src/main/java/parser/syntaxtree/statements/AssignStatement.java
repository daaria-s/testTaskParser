package parser.syntaxtree.statements;

import parser.syntaxtree.UnusedAssignmentsAnalysis;
import parser.syntaxtree.expressions.Expression;

import java.util.Collections;
import java.util.List;
import java.util.Map;

public class AssignStatement extends Statement {

    private final String variableName;
    private final Expression expression;

    private int index = 0;


    public void setIndex(int index) {
        this.index = index;
    }

    public int getIndex() {
        return index;
    }

    public AssignStatement(String variableName, Expression expression) {
        this.variableName = variableName;
        this.expression = expression;
    }

    @Override
    public String info() {
        return variableName + " = " + expression.info();
    }

    @Override
    public void execute(Map<String, Integer> variables) {
        variables.put(variableName, expression.calculate(variables));
    }

    @Override
    public UnusedAssignmentsAnalysis unusedAssignmentsAnalysis() {
        return new UnusedAssignmentsAnalysis(expression.getUsedVariables(), Map.of(variableName, List.of(this)), Collections.emptyList());
    }


    public Expression getExpression() {
        return expression;
    }

    public String getVariableName() {
        return variableName;
    }
}
