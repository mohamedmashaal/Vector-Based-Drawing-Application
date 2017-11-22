package eg.edu.alexu.csd.oop.db.cs60.Model;

import eg.edu.alexu.csd.oop.db.cs60.Model.DBObjects.Record;
import eg.edu.alexu.csd.oop.db.cs60.Model.DBObjects.Table;
import eg.edu.alexu.csd.oop.db.utils.Comparator;

import java.util.ArrayList;
import java.util.Stack;

public class ConditionHandler {

    Stack<String> conversionStack;
    Stack<ArrayList<Integer>> evaluationStack;

    public ArrayList<Integer> getValidIndicesFrom(Table table, String[] condition){ // condition:String, after-where part.
        ArrayList<String> splittedTerms = getWillFormedArrayOf(condition);
        return evaluate(infixToPostfix(splittedTerms), table);
    }

    private ArrayList<String> infixToPostfix(ArrayList<String> infix){
        ArrayList<String> postfix = new ArrayList<>();
        conversionStack = new Stack<>();

        int openPar = 0;
        boolean prevIsOpr = false;

        for(String part : infix){
            if(isOperator(part)){
                if(prevIsOpr){
                    throw new RuntimeException("Error: Invalid expression after where cluase. Hint: Check operators.");
                }

                while(!conversionStack.isEmpty() && precedence(part) <= precedence(conversionStack.peek())){
                    postfix.add(conversionStack.pop());
                }
                conversionStack.push(part);
                prevIsOpr = true;
            }
            else if(part.equalsIgnoreCase("(")){
                if(prevIsOpr){
                    throw new RuntimeException("Error: Invalid expression after where cluase. Hint: Check operators.");
                }

                openPar++;
                conversionStack.push(part);
                prevIsOpr = true;
            }
            else if(part.equalsIgnoreCase(")")){
                if(prevIsOpr){
                    throw new RuntimeException("Error: Invalid expression after where cluase. Hint: Check operators.");
                }
                if(openPar == 0){
                    throw new RuntimeException("Error: Invalid expression after where cluase. Hint: Check parenthesis.");
                }

                while(!conversionStack.peek().equalsIgnoreCase("(")){
                    postfix.add(conversionStack.pop());
                }
                if(conversionStack.peek().equalsIgnoreCase("(")){
                    conversionStack.pop();
                }
                openPar--;
                prevIsOpr = true;
            }
            else{ // conditional term
                prevIsOpr = false;
                postfix.add(part);
            }
        }

        while(!conversionStack.isEmpty()){
            postfix.add(conversionStack.pop());
        }

        return postfix;
    }

    private ArrayList<Integer> evaluate(ArrayList<String> postfix, Table table){
        int colSize = table.getColumns().get(0).getSize();
        ArrayList<Integer> validIndices = new ArrayList<>();
        evaluationStack = new Stack<>();

        for(String part : postfix){
            if(isOperator(part)){
                if(part.equalsIgnoreCase("not")){
                    ArrayList<Integer> temp = evaluationStack.pop();
                    // Flipping
                    //temp.forEach(integer -> integer = 1 - integer); // The tester doesn't support lambdas
                    for(Integer x : temp){
                        x = 1-x;
                    }
                    evaluationStack.push(temp);
                }
                else if(part.equalsIgnoreCase("and")){
                    ArrayList<Integer> temp1 = evaluationStack.pop();
                    ArrayList<Integer> temp2 = evaluationStack.pop();
                    ArrayList<Integer> temp = new ArrayList<>(temp1.size()); // or: temp2.size()
                    for(int i=0; i<temp.size(); i++){
                        temp.set(i, temp1.get(i) + temp2.get(i) == 2 ? 1 : 0);
                    }
                    evaluationStack.push(temp);
                }
                else if(part.equalsIgnoreCase("or")){
                    ArrayList<Integer> temp1 = evaluationStack.pop();
                    ArrayList<Integer> temp2 = evaluationStack.pop();
                    ArrayList<Integer> temp = new ArrayList<>(temp1.size()); // or: temp2.size()
                    for(int i=0; i<temp.size(); i++){
                        temp.set(i, temp1.get(i) + temp2.get(i) == 0 ? 0 : 1);
                    }
                    evaluationStack.push(temp);
                }
            }
            else{ // conditional term
                String[] splitted = part.split(" ");
                if(splitted.length > 3){ // column_name operator 'multiple words'. ex: name = 'john adams'
                    for(int i=3; i<splitted.length; i++){
                        splitted[2] += " " + splitted[i];
                    }
                }
                String whereColumn = splitted[0];
                String operator = splitted[1];
                String whereValue = splitted[2];
                int index = table.getIndex(whereColumn);
                String type ;
                if(index != -1) {
                    ArrayList<Integer> indices = new ArrayList<>(colSize);
                    //indices.forEach(integer -> integer = 0); // The tester doesn't support lambdas
                    for(Integer x : indices){
                        x = 0;
                    }
                    type = table.getColumns().get(index).getType();
                    ArrayList<Record> records = table.getColumns().get(index).getRecords();
                    for(int i = 0 ; i < records.size() ; i++) {
                        if(type.equalsIgnoreCase("int")) {
                            Integer record = (Integer)records.get(i).getValue();
                            if(new Comparator<Integer>().compare(record, new Integer(Integer.parseInt(whereValue)), operator)) {
                                indices.set(i, 1);
                            }
                        }
                        else if(type.equalsIgnoreCase("varchar")) {
                            String record = (String) records.get(i).getValue();
                            if(new Comparator<String>().compare(record, whereValue , operator)) {
                                indices.set(i, 1);
                            }
                        }
                    }
                    evaluationStack.push(indices);
                }
            }
        }

        ArrayList<Integer> temp = evaluationStack.pop();
        for(int i=0; i<temp.size(); i++){
            if(temp.get(i) == 1){
                validIndices.add(i);
            }
        }

        return validIndices;
    }

    private boolean isOperator(String part) {
        return part.equalsIgnoreCase("not") || part.equalsIgnoreCase("and") || part.equalsIgnoreCase("or");
    }

    private int precedence(String operator){
        if(operator.equalsIgnoreCase("not"))
            return 3;
        if(operator.equalsIgnoreCase("and"))
            return 2;
        if(operator.equalsIgnoreCase("or"))
            return 1;
        return -1;
    }

    private ArrayList<String> getWillFormedArrayOf(String[] condition) {
        ArrayList<String> wellFormed = new ArrayList<>();
        String curr = "";
        int len = 0;

        for(int i=0; i<condition.length; i++){
            System.out.println(condition[i]);
            if(len == 3){
                System.out.println("curr : " + curr);
                wellFormed.add(curr);
                len = 0;
                curr = "";
            }

            if(condition[i].startsWith("'")){ // len == 2
                String temp = condition[i];
                i++;
                while(!condition[i].endsWith("'")){
                    System.out.println(condition[i]);
                    temp += " " + condition[i];
                    i++;
                }
                if(condition[i].endsWith("'")){
                    temp += " " + condition[i];
                }
                temp.replaceAll("'","");
                curr += " " + temp;
                System.out.println(temp);
                len++;
            }
            else if(condition[i].startsWith("\"")){ // len == 2
                String temp = condition[i];
                i++;
                while(!condition[i].endsWith("\"")){
                    temp += " " + condition[i];
                    i++;
                }
                if(condition[i].endsWith("\"")){
                    temp += " " + condition[i];
                }
                temp = temp.replaceAll("\"","");
                curr += " " + temp;
                len++;
            }

            else if(len == 0){
                if(isOperator(condition[i])
                    || condition[i].equalsIgnoreCase("(") || condition[i].equalsIgnoreCase(")")){

                    wellFormed.add(condition[i]);
                }
                else{
                    System.out.println(len + " : " + condition[i]);
                    curr += condition[i];
                    len++;
                }
            }
            else if(len == 1 || len == 2){
                System.out.println(len + " : " + condition[i]);
                curr += " " + condition[i];
                len++;
            }
        }

        if(!curr.isEmpty()){
            wellFormed.add(curr);
        }

        return wellFormed;
    }

}
