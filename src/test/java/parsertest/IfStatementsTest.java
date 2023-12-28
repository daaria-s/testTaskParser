package parsertest;

import org.junit.Test;
import parser.ParseException;
import parser.Parser;
import parser.syntaxtree.Program;
import parser.syntaxtree.expressions.ConstantExpression;
import parser.syntaxtree.expressions.VariableExpression;
import parser.syntaxtree.expressions.operators.*;
import parser.syntaxtree.statements.AssignStatement;
import parser.syntaxtree.statements.IfStatement;

import java.io.FileNotFoundException;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class IfStatementsTest {

    String filesDirectory = "src/test/files/ifstatements/";


    @Test
    public void ifStatementTest1() throws FileNotFoundException, ParseException {

        Program expectedProgram = new Program();
        expectedProgram.setStatementList(List.of(new AssignStatement("x", new ConstantExpression(1)),
                new IfStatement(new MoreOperatorExpression(new ConstantExpression(2), new ConstantExpression(1)),
                        List.of(new AssignStatement("x", new ConstantExpression(5))))));
        Program actualProgram = Parser.parse(filesDirectory + "ifstatement1.txt");

        assertEquals(expectedProgram.getStatements(), actualProgram.getStatements());

        // executor test
        expectedProgram.execute();
        actualProgram.execute();
        assertEquals(expectedProgram.getVariables(), actualProgram.getVariables());
    }

    @Test
    public void ifStatementTest2() throws FileNotFoundException, ParseException {

        Program expectedProgram = new Program();
        expectedProgram.setStatementList(List.of(new AssignStatement("a", new ConstantExpression(1)),
                new IfStatement(new MoreOperatorExpression(new VariableExpression("a"), new ConstantExpression(5)),
                        List.of(new AssignStatement("a", new ConstantExpression(5))))));
        Program actualProgram = Parser.parse(filesDirectory + "ifstatement2.txt");

        assertEquals(expectedProgram.getStatements(), actualProgram.getStatements());

        // executor test
        expectedProgram.execute();
        actualProgram.execute();
        assertEquals(expectedProgram.getVariables(), actualProgram.getVariables());
    }
}
