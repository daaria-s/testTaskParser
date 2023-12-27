package parser.syntaxtree;

import parser.syntaxtree.statements.AssignStatement;
import parser.syntaxtree.statements.IfStatement;
import parser.syntaxtree.statements.Statement;
import parser.syntaxtree.statements.WhileStatement;

import java.util.*;

public class Program {

    private List<Statement> statements = new ArrayList<>();

    private final Map<String, Integer> variables = new HashMap<>();

    public void setStatementList(List<Statement> statementList) {
        this.statements = statementList;
    }

    public List<String> getStatements() {
        return statements.stream().map(Statement::info).toList();
    }

    public Map<String, Integer> getVariables() {
        return variables;
    }


    public void execute() {
        System.out.println("Starting execution...");

        for (var st : statements) {
            st.execute(variables);
        }

        System.out.println("Executed successfully, " + statements.size() + " statements");

        System.out.println("Variable values at the end of the program:");

        for (var variable : variables.entrySet()) {
            System.out.println(variable.getKey() + " = " + variable.getValue());
        }
    }

    public void numberAllAssignments() {
        numberList(0, statements);
    }

    private int numberList(int index, List<Statement> statements) {
        for (var st : statements) {
            if (st.getClass().equals(AssignStatement.class)) {
                AssignStatement assign = (AssignStatement) st;
                assign.setIndex(index);
                index++;
            } else if (st.getClass().equals(IfStatement.class)) {
                IfStatement ifSt = (IfStatement) st;
                index = numberList(index, ifSt.getStatementList());
            } else if (st.getClass().equals(WhileStatement.class)) {
                WhileStatement whileSt = (WhileStatement) st;
                index = numberList(index, whileSt.getStatementList());
            }
        }
        return index;
    }


    public List<AssignStatement> findUnusedAssignments() {

        numberAllAssignments();

        UnusedAssignmentsAnalysis result = unusedAssignmentsInList(statements);


        ArrayList<AssignStatement> unusedAssignments = new ArrayList<>(result.unusedAssignments());

        Map<String, List<AssignStatement>> currentUnusedAssignments = result.currentUnusedAssignments();


        for (var varName : currentUnusedAssignments.keySet()) {
            unusedAssignments.addAll(currentUnusedAssignments.get(varName));
        }

        return unusedAssignments.stream().sorted(Comparator.comparingInt(AssignStatement::getIndex)).toList();
    }


    public static UnusedAssignmentsAnalysis unusedAssignmentsInList(List<Statement> statementList) {

        Set<String> usedVariables = new HashSet<>();
        Set<AssignStatement> unusedAssignments = new HashSet<>();
        HashMap<String, List<AssignStatement>> currentUnusedAssignments = new HashMap<>();
        for (var statement : statementList) {
            UnusedAssignmentsAnalysis result = statement.unusedAssignmentsAnalysis();

            for (var variable : result.usedVariables()) {
                if (currentUnusedAssignments.containsKey(variable)) {
                    currentUnusedAssignments.remove(variable);
                } else {
                    usedVariables.add(variable);
                }
            }
            if (statement.getClass().equals(AssignStatement.class)) {
                String varName = result.currentUnusedAssignments().entrySet().stream().findFirst().get().getKey();
                AssignStatement assignStatement = result.currentUnusedAssignments().get(varName).getFirst();
                if (currentUnusedAssignments.containsKey(varName)) {
                    unusedAssignments.addAll(currentUnusedAssignments.get(varName));
                }
                currentUnusedAssignments.put(varName, new ArrayList<>(List.of(assignStatement)));
            } else {
                for (var varName : result.currentUnusedAssignments().keySet()) {
                    if (currentUnusedAssignments.containsKey(varName)) {
                        currentUnusedAssignments.get(varName).addAll(result.currentUnusedAssignments().get(varName));
                    } else {
                        currentUnusedAssignments.put(varName, new ArrayList<>(result.currentUnusedAssignments().get(varName)));
                    }
                }

            }
        }

        return new UnusedAssignmentsAnalysis(usedVariables, currentUnusedAssignments, unusedAssignments.stream().toList());
    }


}