package parsertest;

import org.junit.Test;
import parser.ParseException;
import parser.Parser;
import parser.syntaxtree.Program;
import parser.syntaxtree.expressions.ConstantExpression;
import parser.syntaxtree.expressions.VariableExpression;
import parser.syntaxtree.expressions.operators.PlusOperatorExpression;
import parser.syntaxtree.statements.AssignStatement;

import java.io.FileNotFoundException;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

public class AssignmentsTest {


    String filesDirectory = "src/test/files/assignments/";

    @Test
    public void assignmentsTest1() throws FileNotFoundException, ParseException {
        // parser test
        Program expectedProgram = new Program();
        expectedProgram.setStatementList(List.of(new AssignStatement("a", new ConstantExpression(1))));
        Program actualProgram = Parser.parse(filesDirectory + "assignment1.txt");

        assertEquals(expectedProgram.getStatements(), actualProgram.getStatements());

        // executor test
        expectedProgram.execute();
        actualProgram.execute();
        assertEquals(expectedProgram.getVariables(), actualProgram.getVariables());

    }


    @Test
    public void assignmentsTest2() throws FileNotFoundException, ParseException {
        // parser test
        Program expectedProgram = new Program();
        expectedProgram.setStatementList(List.of(new AssignStatement("x", new ConstantExpression(1)),
                new AssignStatement("y", new ConstantExpression(2)),
                new AssignStatement("z", new ConstantExpression(3))));

        Program actualProgram = Parser.parse(filesDirectory + "assignment2.txt");

        assertEquals(expectedProgram.getStatements(), actualProgram.getStatements());

        // executor test
        expectedProgram.execute();
        actualProgram.execute();
        assertEquals(expectedProgram.getVariables(), actualProgram.getVariables());

    }


    @Test
    public void assignmentsTest3() throws FileNotFoundException, ParseException {
        // parser test
        Program expectedProgram = new Program();
        expectedProgram.setStatementList(List.of(new AssignStatement("a", new ConstantExpression(1)),
                new AssignStatement("b", new ConstantExpression(3)),
                new AssignStatement("c", new PlusOperatorExpression(new VariableExpression("a"), new VariableExpression("b"))),
                new AssignStatement("d", new PlusOperatorExpression(new VariableExpression("x"), new VariableExpression("y")))));

        Program actualProgram = Parser.parse(filesDirectory + "assignment3.txt");

        assertEquals(expectedProgram.getStatements(), actualProgram.getStatements());

        // executor test
        assertThrows(NullPointerException.class, actualProgram::execute);

    }
}
