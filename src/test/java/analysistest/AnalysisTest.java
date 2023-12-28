package analysistest;

import org.junit.Test;
import parser.ParseException;
import parser.Parser;
import parser.syntaxtree.Program;
import parser.syntaxtree.expressions.ConstantExpression;
import parser.syntaxtree.expressions.VariableExpression;
import parser.syntaxtree.statements.AssignStatement;

import java.io.FileNotFoundException;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class AnalysisTest {

    String filesDirectory = "src/test/files/assignmentsanalysis/";

    @Test
    public void unusedassignmentsTest1() throws FileNotFoundException, ParseException {
        Program program = Parser.parse(filesDirectory + "assignmentsanalysis1.txt");

        List<AssignStatement> expected = List.of(new AssignStatement("x", new ConstantExpression(1)),
                new AssignStatement("y", new ConstantExpression(2)),
                new AssignStatement("z", new ConstantExpression(3)));
        List<AssignStatement> actual = program.findUnusedAssignments();
        assertEquals(expected, actual);
    }

    @Test
    public void unusedassignmentsTest2() throws FileNotFoundException, ParseException {
        Program program = Parser.parse(filesDirectory + "assignmentsanalysis2.txt");

        List<AssignStatement> expected = List.of(new AssignStatement("e", new VariableExpression("d")));
        List<AssignStatement> actual = program.findUnusedAssignments();
        assertEquals(expected, actual);
    }


    @Test
    public void unusedassignmentsTest3() throws FileNotFoundException, ParseException {
        Program program = Parser.parse(filesDirectory + "assignmentsanalysis3.txt");

        List<AssignStatement> expected = List.of(new AssignStatement("y", new ConstantExpression(4)),
                new AssignStatement("z", new VariableExpression("x")),
                new AssignStatement("y", new ConstantExpression(10)));
        List<AssignStatement> actual = program.findUnusedAssignments();
        assertEquals(expected, actual);
    }



}
