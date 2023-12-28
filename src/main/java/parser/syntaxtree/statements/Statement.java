package parser.syntaxtree.statements;

import parser.syntaxtree.UnusedAssignmentsAnalysis;

import java.util.Map;

public abstract class Statement {

    public abstract String info();


    public abstract void execute(Map<String, Integer> variables);


    public abstract UnusedAssignmentsAnalysis unusedAssignmentsAnalysis();
}
