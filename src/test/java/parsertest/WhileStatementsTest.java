package parsertest;

import org.junit.Test;
import parser.ParseException;
import parser.Parser;
import parser.syntaxtree.Program;
import parser.syntaxtree.expressions.ConstantExpression;
import parser.syntaxtree.expressions.VariableExpression;
import parser.syntaxtree.expressions.operators.LessOperatorExpression;
import parser.syntaxtree.expressions.operators.MinusOperatorExpression;
import parser.syntaxtree.expressions.operators.MoreOperatorExpression;
import parser.syntaxtree.expressions.operators.PlusOperatorExpression;
import parser.syntaxtree.statements.AssignStatement;
import parser.syntaxtree.statements.WhileStatement;

import java.io.FileNotFoundException;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class WhileStatementsTest {


    String filesDirectory = "src/test/files/whilestatements/";

    @Test
    public void whileStatementTest1() throws FileNotFoundException, ParseException {

        Program expectedProgram = new Program();
        expectedProgram.setStatementList(List.of(new AssignStatement("x", new ConstantExpression(0)),
                new WhileStatement(new LessOperatorExpression(new VariableExpression("x"), new ConstantExpression(5)),
                        List.of(new AssignStatement("x", new PlusOperatorExpression(new VariableExpression("x"), new ConstantExpression(1)))))));
        Program actualProgram = Parser.parse(filesDirectory + "whilestatement1.txt");

        assertEquals(expectedProgram.getStatements(), actualProgram.getStatements());

        // executor test
        expectedProgram.execute();
        actualProgram.execute();
        assertEquals(expectedProgram.getVariables(), actualProgram.getVariables());
    }

    @Test
    public void whileStatementTest2() throws FileNotFoundException, ParseException {

        Program expectedProgram = new Program();
        expectedProgram.setStatementList(List.of(new AssignStatement("x", new ConstantExpression(4)),
                new WhileStatement(new MoreOperatorExpression(new VariableExpression("x"), new ConstantExpression(5)),
                        List.of(new AssignStatement("x", new MinusOperatorExpression(new VariableExpression("x"), new ConstantExpression(1)))))));
        Program actualProgram = Parser.parse(filesDirectory + "whilestatement2.txt");

        assertEquals(expectedProgram.getStatements(), actualProgram.getStatements());

        // executor test
        expectedProgram.execute();
        actualProgram.execute();
        assertEquals(expectedProgram.getVariables(), actualProgram.getVariables());
    }
}
