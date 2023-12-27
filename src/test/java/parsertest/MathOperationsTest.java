package parsertest;

import org.junit.Test;
import parser.syntaxtree.Program;
import parser.syntaxtree.expressions.ConstantExpression;
import parser.syntaxtree.expressions.operators.PlusOperatorExpression;
import parser.syntaxtree.statements.AssignStatement;

import java.util.List;

public class MathOperationsTest {

    @Test
    public void mathOperationsTest1() {

        Program actualProgram = new Program();
        actualProgram.setStatementList(List.of(new AssignStatement("a", new PlusOperatorExpression(new ConstantExpression(2), new ConstantExpression(3)))));
    }
}
