package parser.syntaxtree.expressions;

import java.util.HashSet;
import java.util.List;
import java.util.Map;

public class VariableExpression implements Expression {
    String variableName;

    public VariableExpression(String variableName) {
        this.variableName = variableName;
    }


    @Override
    public int calculate(Map<String, Integer> variables) {
        return variables.get(variableName);
    }

    @Override
    public String info() {
        return variableName;
    }

    @Override
    public HashSet<String> getUsedVariables() {
        return new HashSet<>(List.of(variableName));
    }
}
