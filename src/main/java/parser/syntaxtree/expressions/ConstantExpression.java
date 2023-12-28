package parser.syntaxtree.expressions;

import java.util.HashSet;
import java.util.Map;
import java.util.Objects;

public class ConstantExpression implements Expression {
    int value;

    public ConstantExpression(int value) {
        this.value = value;
    }


    @Override
    public int calculate(Map<String, Integer> variables) {
        return value;
    }

    public String info() {
        return Integer.toString(value);
    }

    @Override
    public HashSet<String> getUsedVariables() {
        return new HashSet<>();
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ConstantExpression that = (ConstantExpression) o;
        return value == that.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
