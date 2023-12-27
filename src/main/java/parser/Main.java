package parser;

import parser.syntaxtree.Program;

import java.io.FileNotFoundException;

public class Main {

    public static void main(String[] args) throws FileNotFoundException, ParseException {

        Program program = Parser.parse("input.txt");

        System.out.println(program.getStatements());

        program.execute();

        System.out.println("Unused assignments:");
        for (var a : program.findUnusedAssignments()) {
            System.out.println(a.info());
        }

//        System.out.println("Done");

    }
}
