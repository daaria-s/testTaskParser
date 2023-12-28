package parser.syntaxtree;

import parser.syntaxtree.statements.AssignStatement;

import java.util.*;

public record UnusedAssignmentsAnalysis(Set<String> usedVariables, Map<String, List<AssignStatement>> currentUnusedAssignments, List<AssignStatement> unusedAssignments) {
}
