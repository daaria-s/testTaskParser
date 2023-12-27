package parser.syntaxtree.expressions;

import java.util.HashSet;
import java.util.Map;

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
}
