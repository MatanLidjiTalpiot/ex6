package foundation;
import java.util.regex.*;
import blocks.Block;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Parser {
    private static Parser ourInstance = new Parser();

    public static Parser getInstance() {
        return ourInstance;
    }

    private Parser() {
    }

    public int parse(String filename, Block mainBlock) {
        try {
            File file = new File(filename);
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                if(this.lineAction(line,mainBlock)){
                    return 1;
                }
            }
            fileReader.close();
            return 0;
        }
        catch (IOException e) {
            e.printStackTrace();
        }


    }
    private int parseBlock(BufferedReader bufferedReader, Block block) throws IOException {
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            if(this.lineAction(line,block)){
                return 0;
            }
        }
        return 1;
    }

    private boolean lineAction(String line,Block block){


    }
    private boolean declerationLineAction(String line, Block block, boolean isFinal) {
    }

}


