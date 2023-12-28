package parsertest;

import org.junit.Test;
import parser.ParseException;
import parser.Parser;
import parser.syntaxtree.Program;
import parser.syntaxtree.expressions.ConstantExpression;
import parser.syntaxtree.expressions.VariableExpression;
import parser.syntaxtree.expressions.operators.*;
import parser.syntaxtree.statements.AssignStatement;

import java.io.FileNotFoundException;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class MathOperationsTest {

    String filesDirectory = "src/test/files/mathoperations/";

    @Test
    public void mathOperationsTest1() throws FileNotFoundException, ParseException {

        Program expectedProgram = new Program();
        expectedProgram.setStatementList(List.of(new AssignStatement("a", new PlusOperatorExpression(new ConstantExpression(2), new ConstantExpression(3))),
                new AssignStatement("b", new MinusOperatorExpression(new ConstantExpression(70), new ConstantExpression(9))),
                new AssignStatement("c", new MultiplyOperatorExpression(new ConstantExpression(2), new ConstantExpression(60))),
                new AssignStatement("d", new DivideOperatorExpression(new ConstantExpression(50), new ConstantExpression(2))),
                new AssignStatement("e", new MoreOperatorExpression(new ConstantExpression(4), new ConstantExpression(5))),
                new AssignStatement("f", new LessOperatorExpression(new ConstantExpression(12), new ConstantExpression(50)))));

        Program actualProgram = Parser.parse(filesDirectory + "mathoperations1.txt");

        assertEquals(expectedProgram.getStatements(), actualProgram.getStatements());

        // executor test
        expectedProgram.execute();
        actualProgram.execute();
        assertEquals(expectedProgram.getVariables(), actualProgram.getVariables());
    }


    @Test
    public void mathOperationsTest2() throws FileNotFoundException, ParseException {

        Program expectedProgram = new Program();
        expectedProgram.setStatementList(List.of(new AssignStatement("x", new PlusOperatorExpression(new
                        ConstantExpression(12), new MultiplyOperatorExpression(new ConstantExpression(8), new ConstantExpression(3)))),
                new AssignStatement("y", new DivideOperatorExpression(new
                        MinusOperatorExpression(new ConstantExpression(10), new ConstantExpression(2)), new ConstantExpression(2))),
                new AssignStatement("z", new MinusOperatorExpression(new
                        PlusOperatorExpression(new MultiplyOperatorExpression(new VariableExpression("x"), new
                        VariableExpression("x")), new DivideOperatorExpression(new VariableExpression("y"), new VariableExpression("y"))),
                        new VariableExpression("x")))));
        Program actualProgram = Parser.parse(filesDirectory + "mathoperations2.txt");

        assertEquals(expectedProgram.getStatements(), actualProgram.getStatements());

        // executor test
        expectedProgram.execute();
        actualProgram.execute();
        assertEquals(expectedProgram.getVariables(), actualProgram.getVariables());
    }
}
