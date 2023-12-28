package parser.syntaxtree.expressions;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;

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


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VariableExpression that = (VariableExpression) o;
        return Objects.equals(variableName, that.variableName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(variableName);
    }
}
