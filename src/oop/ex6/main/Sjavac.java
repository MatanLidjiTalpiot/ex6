package oop.ex6.main;

import blocks.Block;
import foundation.Parser;

import java.io.IOException;

public class Sjavac {
    private static Parser parser = Parser.getInstance();

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
            System.out.println(2);
        }
        catch(Exception e){
            System.err.println(e.getMessage());//TODO remove after debugging
            System.out.println(1);
        }

    }
}