package parser.syntaxtree.expressions;

import java.util.HashSet;
import java.util.Map;

public abstract class Expression {
    abstract int calculate(Map<String, Integer> variables);

     abstract String info();

     abstract HashSet<String> getUsedVariables();


}
