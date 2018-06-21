package foundation;
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

    public Checkable parse(String filename) {
        Block mainBlock = new Block();
        try {
            File file = new File(filename);
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            this.parseBlock(bufferedReader, mainBlock);
            fileReader.close();
            return mainBlock;
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

    private boolean lineAction(String line,Block block){ }
}

//TODO finidh this
