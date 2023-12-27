package parser.syntaxtree.statements;

import parser.syntaxtree.UnusedAssignmentsAnalysis;
import parser.syntaxtree.expressions.Expression;

import java.util.*;

import static parser.syntaxtree.Program.unusedAssignmentsInList;

public class IfStatement extends Statement {

    Expression expression;
    List<Statement> statementList;


    public IfStatement(Expression expression, List<Statement> statementList) {
        this.expression = expression;
        this.statementList = statementList;
    }

    @Override
    public String info() {
        StringBuilder result = new StringBuilder("if: " + expression.info() + " then ");
        for (var s : statementList) {
            result.append(s.info());
            result.append(" ");
        }
        result.append("end");
        return result.toString();
    }

    @Override
    public void execute(Map<String, Integer> variables) {
        if (expression.calculate(variables) != 0) {
            for (var st : statementList) {
                st.execute(variables);
            }
        }
    }

    @Override
    public UnusedAssignmentsAnalysis unusedAssignmentsAnalysis() {
        Set<String> usedVariables = expression.getUsedVariables();
        var result = unusedAssignmentsInList(statementList);

        usedVariables.addAll(result.usedVariables());
        return new UnusedAssignmentsAnalysis(usedVariables, result.currentUnusedAssignments(), result.unusedAssignments());

    }


    public List<Statement> getStatementList() {
        return statementList;
    }
}
