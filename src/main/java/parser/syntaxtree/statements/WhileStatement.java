package parser.syntaxtree.statements;

import parser.syntaxtree.UnusedAssignmentsAnalysis;
import parser.syntaxtree.expressions.Expression;

import java.util.List;
import java.util.Map;
import java.util.Set;

import static parser.syntaxtree.Program.unusedAssignmentsInList;

public class WhileStatement extends Statement {
    Expression expression;
    List<Statement> statementList;

    public WhileStatement(Expression expression, List<Statement> statementList) {
        this.expression = expression;
        this.statementList = statementList;
    }

    @Override
    public String info() {
        String res =  "while: " + expression.info() + " then ";
        StringBuilder r = new StringBuilder(res);
        for (var s : statementList) {
            r.append(s.info());
            r.append(" ");
        }
        return r.toString();
    }

    @Override
    public void execute(Map<String, Integer> variables) {
        while (expression.calculate(variables) != 0) {
            for (var st : statementList) {
                st.execute(variables);
            }
        }
    }

    @Override
    public UnusedAssignmentsAnalysis unusedAssignmentsAnalysis() {
        Set<String> usedVariables = expression.getUsedVariables();
        var result = unusedAssignmentsInList(statementList);
        var currentUnused = result.currentUnusedAssignments();
        for (var variable : usedVariables) {
            currentUnused.remove(variable);
        }
        for (var variable : result.usedVariables()) {
            currentUnused.remove(variable);
        }

        usedVariables.addAll(result.usedVariables());
        return new UnusedAssignmentsAnalysis(usedVariables, currentUnused, result.unusedAssignments());

    }

    public List<Statement> getStatementList() {
        return statementList;
    }
}
