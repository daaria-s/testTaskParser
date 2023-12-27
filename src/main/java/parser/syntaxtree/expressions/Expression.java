package parser.syntaxtree.expressions;

import java.util.HashSet;
import java.util.Map;

public interface  Expression {
     int calculate(Map<String, Integer> variables);

      String info();

      HashSet<String> getUsedVariables();


}
