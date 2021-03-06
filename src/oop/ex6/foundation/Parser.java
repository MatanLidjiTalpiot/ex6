package oop.ex6.foundation;

import oop.ex6.blocks.*;
import oop.ex6.lines.*;
import oop.ex6.foundation.exceptions.*;
import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * this is a singleton for parsing the data to our data structure
 */
public class Parser {
    private static Parser ourInstance = new Parser();

    private Block mainBlock;
    private int rowNumber;
    private ArrayList<String> fileData;


    public static Parser getInstance() {
        return ourInstance;
    }

    private Parser() {
    }

    /**
     *
     * @param fileName - the name of the file we want to compile
     * @return the block that represents the main block,the data structure
     * @throws IOException
     * @throws FileException
     */
    public Block Parse(String fileName) throws IOException, FileException{

        this.fileData = preProcess(fileName);

        this.mainBlock = new Block();
        this.rowNumber = -1;

        this.parseBlock(this.mainBlock);

        return this.mainBlock;
    }

    /**
     * @param fileName - the name of the file we want to compile
     * @return an ArrayList that holds the rows of our file, after we processed a rules and simplefy the
     * file. redundant return line,  comment oop.ex6.lines and return only in oop.ex6.blocks
     * @throws IOException
     * @throws FileException
     */
    private ArrayList<String> preProcess(String fileName)throws IOException, FileException{

        File file = new File(fileName);
        FileReader fileReader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(fileReader);

        ArrayList<String> al = new ArrayList<>();
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            al.add(line);
        }

        int blockOpeners = 0;
        for(int i = 0; i < al.size(); i++) {
            if (Regex.isEndBlockLine(al.get(i))) {
                blockOpeners--;
            }else{
                if ((Regex.isStartBlockLine(al.get(i))).matches()) {
                    blockOpeners++;
                }else{
                    if(Regex.isReturnLine(al.get(i))&& blockOpeners<=0)
                    {
                        throw new SyntaxException(0);
                    }
                }
            }
        }
        for(int i = 0; i < al.size(); i++){
            if (Regex.isCommentLine(al.get(i))||Regex.isLineEmpthy(al.get(i))){
                al.set(i,"");
            }else{
                if(Regex.isReturnLine(al.get(i)) && !Regex.isEndBlockLine(al.get(min(i+1,al.size()-1)))){
                    al.set(i,"");
                }
            }

            if(Regex.isReturnLine(al.get(i))&& i+1 == al.size()) {
                throw new SyntaxException(i);
            }
        }
        if(blockOpeners!=0){
            throw new SyntaxException(0);
        }
        for(int i = 0; i < al.size(); i++){
            al.set(i,al.get(i).trim());
        }
        return al;
    }

    /**
     *
     * @param block the block we want to fill with cheackebles
     * @throws FileException
     * @throws IOException
     */
    private void parseBlock(Block block) throws FileException, IOException {
        boolean isMainBlock = (TypesOfCheckable.BLOCK == block.getTypeOfCheckable());
        for(;rowNumber < this.fileData.size()-1;){
            rowNumber++;
            if(!isMainBlock && Regex.isEndBlockLine(this.fileData.get(rowNumber))){
                if(block.getTypeOfCheckable() == TypesOfCheckable.CONDITION_BLOCK){
                    return;
                }
                else{
                    if(Regex.isReturnLine(this.fileData.get(rowNumber - 1))){
                        return;
                    }
                    else{
                        throw new SyntaxException(rowNumber);
                    }
                }
            }
            if(!this.fileData.get(rowNumber).equals("")) {
                this.rowAction(this.fileData.get(rowNumber), block);
            }
        }
    }

    /**
     * this method decides what is the given line and parse if accordingly
     * @param line the string of the line we want to parse
     * @param block the block we are working in
     * @throws FileException
     * @throws IOException
     */
    private void rowAction(String line,Block block)throws FileException, IOException {

        Matcher matcher  = Regex.isStartBlockLine(line);
        if (matcher.matches()){
            this.blockAction(matcher.group(1),block);
        }
        else{
            this.lineAction(line,block);
        }
    }
    /**
     * this method decides what block is the given line and parse if accordingly
     * @throws FileException
     * @throws IOException
     */
    private void blockAction(String line,Block block)throws FileException, IOException {

        Matcher matcher = Regex.isConditionBlock(line);
        if(matcher.matches()){
            conditionBlockAction(matcher, block);
            return;
        }

        matcher = Regex.isMethodBlock(line);
        if(matcher.matches()){
            methodeBlockAction(matcher, block);
            return;
        }

        throw new SyntaxException(rowNumber);
    }
    /**
     * this method decides what line is the given line and parse if accordingly
     * @throws FileException
     * @throws IOException
     */
    private void lineAction(String line,Block block)throws FileException, IOException{

        Matcher matcher  = Regex.isFinalDeclerationLine(line);
        if(Regex.isReturnLine(line)){
            return;
        }
        if (matcher.matches()){
            declerationLineAction(matcher,block,true);
            return;
        }
        if(!line.contains("(")&&!line.contains(")")) {
            matcher = Regex.isDeclerationLine(line);
            if (matcher.matches()) {
                declerationLineAction(matcher, block, false);
                return;
            }
        }

        matcher  = Regex.isAssimentLine(line);
        if (matcher.matches()){
            assignmentLineAction(matcher,block);
            return;
        }

        matcher  = Regex.isMethodCallLine(line);
        if (matcher.matches()){
            methodeCallLineAction(matcher,block);
            return;
        }

        if(Regex.isReturnLine(line)){
            return;
        }

        throw new SyntaxException(rowNumber);
    }
    /**
     * this method parses the given line to assignment Line
     * @throws FileException
     */
    private void assignmentLineAction(Matcher matcher, Block block) throws FileException {
        if (Type.isType(matcher.group(2)) || Regex.isVariableName(matcher.group(2))){
            block.addCheckable(new VariableAssignmentLine(matcher.group(1).trim(),matcher.group(2).trim()));
            return;
        }
        throw new SyntaxException(this.rowNumber);
    }
    /**
     * this method parses the given line to assignment tLine and simple deceleration line
     * @throws FileException
     */
    private void simpleDeclarationLineAction(boolean isFinal, Type type, String str, Block block) throws
            FileException {
        if(Regex.isVariableName(str)) {
            if(isFinal) {
                block.addCheckable(new VariableDeclarationLine(type, str.trim(), isFinal, false));
            }
            else{
                block.addCheckable(new VariableDeclarationLine(type, str.trim()));
            }
        }
        else{
            Matcher matcher = Regex.isAssiment(str);
            if(matcher.matches() && Regex.isVariableName(matcher.group(1))){
                if(isFinal) {
                    block.addCheckable(new VariableDeclarationLine(type, (matcher.group(1)).trim(),
                            isFinal,true));
                    assignmentLineAction(matcher, block);
                }
                else{
                    block.addCheckable(new VariableDeclarationLine(type, (matcher.group(1)).trim()));
                    assignmentLineAction(matcher, block);
                }
            }
            else {
                throw new SyntaxException(this.rowNumber);
            }
        }
    }
    /**
     * this method parses the given line to assignment Line and simple deceleration line
     * @throws FileException
     */
    private void declerationLineAction(Matcher matcher, Block block, boolean isFinal) throws
            FileException {
        Type type1 = Type.INT;
        try{
            type1 = Type.strToType(matcher.group(1));
        }
        catch (InvalidTypeException e){
            throw new SyntaxException(this.rowNumber);
        }
        int k = 0;
        for(char ch: matcher.group(2).toCharArray()){
            if(ch == ','){
                k++;
            }
        }
        String[] parts = matcher.group(2).split(",");
        if(parts.length-1!=k){
            throw new SyntaxException(rowNumber);
        }
        for (int i = 0; i < parts.length; i++) {
            this.simpleDeclarationLineAction(isFinal,type1, parts[i].trim(), block);
        }
    }
    /**
     * this method parses the given line to a method call line
     * @throws FileException
     */
    private void methodeCallLineAction(Matcher matcher, Block block) throws FileException{

        String[] parts = matcher.group(2).split(",");
        LinkedList<String> param = new LinkedList<>();
        if(parts.length == 1 && Regex.isLineEmpthy(parts[0])){
            block.addCheckable(new MethodCallLine(matcher.group(1),param));
            return;
        }
        for(String part : parts) {
            if(!Regex.isVariableName(part) && !Type.isType(part))
                throw new SyntaxException(rowNumber);
            param.add(part);
        }
        block.addCheckable(new MethodCallLine(matcher.group(1),param));
    }
    /**
     * this method parses the given line to condition block, and begin to parse it
     * @throws FileException
     * @throws IOException
     */
    private void conditionBlockAction(Matcher matcher, Block block)throws IOException, FileException{

        String[] parts = matcher.group(2).split("&&|\\|\\|");
        LinkedList<String> conditions = new LinkedList<>();
        for (String part : parts) {
            if(Regex.isVariableName(part.trim()))
                conditions.add(part.trim());
            else
                if(Type.getTypeOf(part) != Type.INT&&Type.getTypeOf(part) != Type.DOUBLE&&Type.getTypeOf
                        (part) != Type.BOOLEAN)
                    throw new SyntaxException(rowNumber);
        }
        ConditionBlock newBlock = new ConditionBlock(conditions, block.getScope());
        parseBlock(newBlock);
        block.addCheckable(newBlock);
    }
    /**
     * this method parses the given line to method block, and begin to parse it
     * @throws FileException
     * @throws IOException
     */
    private void methodeBlockAction(Matcher matcher, Block block)throws FileException, IOException{

        LinkedList<String> varNamesByOrder = new LinkedList<>();
        LinkedList<Type> typeNamesByOrder = new LinkedList<>();
        LinkedList<Boolean> isFinalByOrder = new LinkedList<>();

        String[] parts = (matcher.group(2)).split(",");

        if((parts.length != 1) || (!Pattern.matches("^\\s*$", parts[0]))){
            for (int i = 0; i < parts.length; i++) {
                Matcher matcher1 = Regex.varDeclerationInMethodeBlock(parts[i].trim());
                if (!matcher1.matches()) {
                    throw new SyntaxException(rowNumber);
                }
                if (matcher1.group(1) == null) {
                    isFinalByOrder.add(false);
                }
                else {
                    isFinalByOrder.add(true);
                }
                typeNamesByOrder.add(Type.strToType(matcher1.group(2).trim()));
                if (Regex.isVariableName(matcher1.group(3).trim()))
                    varNamesByOrder.add(matcher1.group(3));
                else
                    throw new SyntaxException(rowNumber);
            }
        }

        MethodBlock newBlock = new MethodBlock(matcher.group(1),varNamesByOrder, typeNamesByOrder,
                isFinalByOrder, block.getScope());
        parseBlock(newBlock);
        block.addCheckable(newBlock);
    }


    private int min(int a, int b){
        if (a < b){
            return a;
        }
        return b;
    }
}
