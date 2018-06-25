package foundation;

import blocks.*;
import lines.*;
import foundation.Exceptions.*;
import java.io.*;
import java.util.LinkedList;
import java.util.regex.Matcher;

public class Parser {
    //TODO which is static method and which is not
    private static Parser ourInstance = new Parser();

    private File file;
    private FileReader fileReader;
    private BufferedReader bufferedReader;
    private Block mainBlock;
    private int rowNumber;


    public static Parser getInstance() {
        return ourInstance;
    }

    private Parser() {
    }

    public Block Parse(String fileName) throws SyntaxException, IOException{
        this.file = new File(fileName);
        this.fileReader = new FileReader(this.file);
        this.bufferedReader = new BufferedReader(fileReader);
        this.mainBlock = new Block();
        this.rowNumber = -1;

        this.parseMainBlock(this.mainBlock);

        return this.mainBlock;
    }

    private void parseMainBlock(Block block) throws IOException, SyntaxException {
        String line, newLine;
        while ((line = this.bufferedReader.readLine()) != null) {
            rowNumber++;
            newLine = this.linePreProcessing(line);
            this.lineAction(newLine,block);
        }
    }

    private void parseBlock(Block block) throws IOException, SyntaxException {
        String line, newLine;
        while ((line = this.bufferedReader.readLine()) != null) {
            rowNumber++;
            if(Regex.isEndBlockLine(line))
                break;
            newLine = this.linePreProcessing(line);
            this.lineAction(newLine,block);
        }
    }

    private String linePreProcessing(String line){
        //TODO cheack if there are any other prepossessing
        String[] parts = line.split("\"");
        for(int i = 0; i<parts.length;i++) {
            if(!parts[i].equals(""))
                if (parts[i].substring(parts[i].length() - 1) == "\\") {
                    //TODO CHEACK THIS FUCKING \\\\\\\ THING
                    parts[i] += parts[i+1];
                    parts[i+1] = "";
                }
        }
        for(int i = 0; i<parts.length;i++) {
            if(i%2 == 1)
                parts[i] = "a";
        }
        return String.join("\"", parts);
    }

    private void lineAction(String line,Block block)throws SyntaxException {
        //TODO fill it after all the other line actions are complete
    }

    private void assignmentLineAction(Matcher matcher, Block block) throws SyntaxException {
        if (Type.isFromType(matcher.group(2)) || Regex.isVariableName(matcher.group(2)) ){
            block.addCheckable(new VariableAssignmentLine(matcher.group(1),matcher.group(2)));
        }
        throw new SyntaxException(this.rowNumber);
    }

    private void simpleDeclerationLineAction(boolean isFinal, Type type, String str, Block block) {
        //  TODO wtf with the eceptions
        if(Regex.isVariableName(str)) {
            block.addCheckable(new VariableDeclarationLine(type, str, isFinal));
        }
        else{
            Matcher matcher = Regex.isAssimentLine(str);
            if(matcher.matches() && Regex.isVariableName(matcher.group(1))){
                block.addCheckable(new VariableDeclarationLine(type, matcher.group(1), isFinal));
                assignmentLineAction(matcher, block);
            }
            else {
                throw new SyntaxException(this.rowNumber);
            }
        }
    }

    private void declerationLineAction(Matcher matcher, Block block, boolean isFinal) {
        if (!Type.isType(matcher.group(1))) {
            throw new SyntaxException(this.rowNumber);
        }
        String[] parts = matcher.group(2).split(",");
        for (int i = 0; i < parts.length; i++) {
            this.simpleDeclerationLineAction(isFinal,Type.getTypeOf(matcher.group(1)), parts[i], block);
        }
    }

    private void methodeCallLineAction(Matcher matcher, Block block){

        String[] parts = matcher.group(2).split(",");
        for(String part : parts) {
            if(!Regex.isVariableName(part) && !Type.isFromType(part))
                return false;
        }
        // TODO how the fuck the methode call line work
        LinkedList<String> param = new LinkedList<String>();
        for(String part : parts) {
            param.add(part);
        }
        block.addCheckable(new MethodCallLine(matcher.group(1),param);
    }

    private void conditionBlockAction(Matcher matcher, Block block)throws InvalidConditionException{
        try {
            String[] parts = matcher.group(2).split("&&|\\|\\|");
            LinkedList<String> conditions = new LinkedList<>();
            for (int i = 0; i < parts.length; i++) {
                conditions.add(parts[i]);
            }
            ConditionBlock newBlock = new ConditionBlock(conditions, block.getScope());
            parseBlock(newBlock);
            block.addCheckable(newBlock);
        }
        catch (InvalidConditionException | IOException | SyntaxException e){

        }
    }

    private void methodeBlockAction((Matcher matcher, Block block)throws SyntaxException,
            InvalidTypeException, IOException{
        //TODO possibole fail in what heppend if it doesnt chach a group "final"
        String[] parts = matcher.group(2).split(",");

        LinkedList<String> varNamesByOrder = new LinkedList<>();
        LinkedList<Type> typeNamesByOrder = new LinkedList<>();
        LinkedList<Boolean> isFinalByOrder = new LinkedList<>();

        for(int i = 0; i < parts.length; i++){
            Matcher matcher1 = Regex.varDeclerationInMethodeBlock(parts[i]);
            if (!matcher1.matches())
                throw new SyntaxException(rowNumber);
            if (matcher.group(1).equals("final"))
                isFinalByOrder.add(true);
            else
                isFinalByOrder.add(false);
            typeNamesByOrder.add(Type.strToType(matcher.group(2)));
            if (Regex.isVariableName(matcher.group(3)))
                varNamesByOrder.add(matcher.group(3));
            else
                throw new SyntaxException(rowNumber);
        }

        MethodBlock newBlock = new MethodBlock(varNamesByOrder, typeNamesByOrder, isFinalByOrder, block.getScope());
        parseBlock(newBlock);
        block.addCheckable(newBlock);
    }
}
//TODO block in block, RETURN