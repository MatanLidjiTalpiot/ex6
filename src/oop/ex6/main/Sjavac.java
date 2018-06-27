package oop.ex6.main;

import blocks.Block;
import foundation.Parser;
import foundation.exceptions.FileException;

import java.io.IOException;

public class Sjavac {
    private static Parser parser = Parser.getInstance();

    /**
     * The main method that acts as a manager, does at the end the S javac action.
     * @param args the arguments from the cmd.
     */
    public static void main(String[] args) {
        try {
            if (args.length != 1) {
                throw new IOException("more than one argument in the cmd");
            }
            String filename = args[0];
            Block mainBlock = parser.Parse(filename);
            mainBlock.check(mainBlock.getScope());
            System.out.println(0);
        }
        catch (IOException e){
            System.err.println(e.getMessage());
            System.out.println(2);
        }
        catch(FileException e){
            System.out.println(1);
        }

    }
}
