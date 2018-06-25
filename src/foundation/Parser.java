package foundation;

import blocks.*;
import lines.*;
import foundation.exceptions.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.regex.Matcher;

public class Parser {
    //TODO which is static method and which is not
    private static Parser ourInstance = new Parser();

    private Block mainBlock;
    private int rowNumber;
    private ArrayList<String> fileData;


    public static Parser getInstance() {
        return ourInstance;
    }

    private Parser() {
    }

    //TODO i started checking from here
    public Block Parse(String fileName) throws IOException, FileException{
        this.fileData = preProcess(fileName);

        this.mainBlock = new Block();
        this.rowNumber = -1;

        this.parseBlock(this.mainBlock, true);

        return this.mainBlock;
    }

    private ArrayList preProcess(String fileName)throws IOException, FileException{

        File file = new File(fileName);
        FileReader fileReader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(fileReader);

        ArrayList<String> al = new ArrayList<>();
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            al.add(line.trim());
        }
        ArrayList<Integer> toDelete = new ArrayList<>();
        for(int i = 0; i < al.size(); i++){
            if(Regex.isLineEmpthy(al.get(i))||Regex.isCommentLine(al.get(i)) || Regex.isReturnLine(al.get
                    (i)) && !Regex.isEndBlockLine(al.get(i+1))){//TODO really patchy
               toDelete.add(i);
            }
        }
        for(int i = toDelete.size()-1; i >= 0; i--){
           al.remove(toDelete.get(i));
        }
        return al;
    }

    private void parseBlock(Block block, boolean isMainBlock) throws FileException, IOException {
        for(;rowNumber < this.fileData.size()-1;){
            rowNumber++;
            if(!isMainBlock && Regex.isEndBlockLine(this.fileData.get(rowNumber))){
                if(block.getTypeOfBlock() == 1){
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

    private void rowAction(String line,Block block)throws FileException, IOException {

        Matcher matcher  = Regex.isStartBlockLine(line);
        if (matcher.matches()){
            this.blockAction(matcher.group(1),block);
        }
        else{
            this.lineAction(line,block);
        }
    }

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

    private void lineAction(String line,Block block)throws FileException, IOException{
        Matcher matcher  = Regex.isAssimentLine(line);
        if (matcher.matches()){
            assignmentLineAction(matcher,block);
            return;
        }

        matcher  = Regex.isFinalDeclerationLine(line);
        if (matcher.matches()){
            declerationLineAction(matcher,block,true);
            return;
        }

        matcher  = Regex.isDeclerationLine(line);
        if (matcher.matches()){
            declerationLineAction(matcher,block,false);
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

    private void assignmentLineAction(Matcher matcher, Block block) throws FileException {
        if (Type.isType(matcher.group(2)) || Regex.isVariableName(matcher.group(2))){
            block.addCheckable(new VariableAssignmentLine(matcher.group(1),matcher.group(2)));
        }
        throw new SyntaxException(this.rowNumber);
    }

    private void simpleDeclarationLineAction(boolean isFinal, Type type, String str, Block block) throws
            FileException {
        if(Regex.isVariableName(str)) {
            block.addCheckable(new VariableDeclarationLine(type, str, isFinal));
        }
        else{
            Matcher matcher = Regex.isAssiment(str);
            if(matcher.matches() && Regex.isVariableName(matcher.group(1))){
                block.addCheckable(new VariableDeclarationLine(type, matcher.group(1), isFinal));
                assignmentLineAction(matcher, block);
            }
            else {
                throw new SyntaxException(this.rowNumber);
            }
        }
    }

    private void declerationLineAction(Matcher matcher, Block block, boolean isFinal) throws
            FileException {
        if (!Type.isType(matcher.group(1))) {
            throw new SyntaxException(this.rowNumber);
        }
        String[] parts = matcher.group(2).split(",");
        for (int i = 0; i < parts.length; i++) {
            this.simpleDeclarationLineAction(isFinal,Type.getTypeOf(matcher.group(1)), parts[i], block);
        }
    }

    private void methodeCallLineAction(Matcher matcher, Block block) throws FileException{

        String[] parts = matcher.group(2).split(",");
        LinkedList<String> param = new LinkedList<>();
        for(String part : parts) {
            if(!Regex.isVariableName(part) && !Type.isType(part))
                throw new SyntaxException(rowNumber);
            param.add(part);
        }
        block.addCheckable(new MethodCallLine(matcher.group(1),param));
    }

    private void conditionBlockAction(Matcher matcher, Block block)throws IOException, FileException{

        String[] parts = matcher.group(2).split("&&|\\|\\|");
        LinkedList<String> conditions = new LinkedList<>();
        for (String part : parts) {
            if(Regex.isVariableName(part))
                conditions.add(part);
            else
                if(Type.getTypeOf(part) != Type.INT||Type.getTypeOf(part) != Type.DOUBLE||Type.getTypeOf
                        (part) != Type.BOOLEAN)
                    throw new SyntaxException(rowNumber);
        }
        ConditionBlock newBlock = new ConditionBlock(conditions, block.getScope());
        parseBlock(newBlock,false);
        block.addCheckable(newBlock);
    }

    private void methodeBlockAction(Matcher matcher, Block block)throws FileException, IOException{

        LinkedList<String> varNamesByOrder = new LinkedList<>();
        LinkedList<Type> typeNamesByOrder = new LinkedList<>();
        LinkedList<Boolean> isFinalByOrder = new LinkedList<>();

        String[] parts = matcher.group(2).split(",");

        if((parts.length != 1) && (!parts[0].equals(""))){
            for (int i = 0; i < parts.length; i++) {
                Matcher matcher1 = Regex.varDeclerationInMethodeBlock(parts[i]);
                if (!matcher1.matches())
                    throw new SyntaxException(rowNumber);
                if (matcher.group(1) == null)
                    isFinalByOrder.add(false);
                else
                    isFinalByOrder.add(true);
                typeNamesByOrder.add(Type.strToType(matcher.group(2)));
                if (Regex.isVariableName(matcher.group(3)))
                    varNamesByOrder.add(matcher.group(3));
                else
                    throw new SyntaxException(rowNumber);
            }
        }

        MethodBlock newBlock = new MethodBlock(matcher.group(1),varNamesByOrder, typeNamesByOrder,
                isFinalByOrder, block.getScope());
        parseBlock(newBlock, false);
        block.addCheckable(newBlock);
    }
}
//TODO preprosses