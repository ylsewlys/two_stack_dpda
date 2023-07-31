import java.util.Stack;
import java.util.ArrayList;
import java.lang.StringBuilder;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


public class Main{

    public static String reverseString(String input){

        StringBuilder strBuilder = new StringBuilder(input);

        strBuilder.reverse();

        return strBuilder.toString();

    }


    public static void pushString(Stack<Character> stack, String input){

        String reverseStr = reverseString(input);
        
        for(int i = 0; i < input.length(); i++){
            if(reverseStr.charAt(i) != 'ε'){
                stack.push(reverseStr.charAt(i));
            }

        }

    }

    public static boolean isStringValidForPop(Stack<Character> stack, String input){
        @SuppressWarnings("unchecked") // This suppresses warnings in the IDE interface
        Stack<Character> checkStack = (Stack<Character>) stack.clone();

        int i = 0;

        for(i = 0; i < input.length(); i++){


            if(checkStack.isEmpty() && i < input.length()){
                System.out.println("STACK IS EMPTY AND REMAINING INPUT IS EXISTING");
                return false;
            } else if(checkStack.peek() == input.charAt(i)){
                checkStack.pop();
            }else{
                System.out.println("STACK POP IS NOT EQUAL TO CURRENT TOP OF STACK");
                return false;
            }
        }

        return true;

    }


    public static boolean popString(Stack<Character> stack, String input){
        int i;
        // IF WHOLE STRING CAN BE POPPED, EXECUTE CODE BELOW

        for(i = 0; i < input.length(); i++){
            stack.pop();
        }

        return true;
    }



    public static void main(String[] args){

        String inputString = "aaabbbccc";
        boolean isAccept = false;
        boolean isFinalState = false;
        boolean isStringRejected = false;
        boolean isTransitionFound;

        
        ArrayList<State> stateList = new ArrayList<State>();
        ArrayList<String> stateNameList = new ArrayList<String>();
        ArrayList<String> inputList = new ArrayList<String>();

        ArrayList<Transition> transitionList = new ArrayList<Transition>();

        Stack<Character> stackZero = new Stack<Character>();
        Stack<Character> stackOne = new Stack<Character>();

        State initialState = new State("");
        ArrayList<State> finalStateList = new ArrayList<State>();

        // for while loops
        int i, k;
        char c = ' ';


        // READ FILE
        try {
            // INITIALIZE LISTS 
            
            BufferedReader br;
            br = new BufferedReader(new FileReader("machine_text_files/anbncn.txt"));


            int stateCount;
            int inputCount;
            int transitionCount;
            int finalStateCount;

            stateCount = Integer.parseInt(br.readLine());

            String line = br.readLine();
            String[] stateNameArr = line.split(" ");

            
        
            for(i = 0; i < stateCount; i++){
                stateList.add(new State(stateNameArr[i]));
                stateNameList.add(stateNameArr[i]);
            }

            inputCount = Integer.parseInt(br.readLine());

            line = br.readLine();
            String[] inputArr = line.split(" ");

            for(i = 0; i < inputCount; i++){
                inputList.add(inputArr[i]);
            }

            transitionCount = Integer.parseInt(br.readLine());

            String readInput;
            String stkOnePop;
            String stkTwoPop;
        
            State nextState;
            String stkOnePush;
            String stkTwoPush;

            String[] transitionElementsArr;
            for(i = 0; i < transitionCount; i++){
                line = br.readLine();
                transitionElementsArr = line.split(" ");

                readInput = transitionElementsArr[1];
                stkOnePop = transitionElementsArr[2];
                stkTwoPop = transitionElementsArr[3];
                nextState = stateList.get(stateNameList.indexOf(transitionElementsArr[4]));
                stkOnePush = transitionElementsArr[5];
                stkTwoPush = transitionElementsArr[6];

                stateList.get(stateNameList.indexOf(transitionElementsArr[0])).getStateTransitions().add(new Transition(readInput, stkOnePop, stkTwoPop, nextState, stkOnePush, stkTwoPush));
            }


            initialState = stateList.get(stateNameList.indexOf(br.readLine()));

            finalStateCount = Integer.parseInt(br.readLine());

            line = br.readLine();
            String[] finalStateNameArr = line.split(" ");

            for(i = 0; i < finalStateCount; i++){
                finalStateList.add(stateList.get(stateNameList.indexOf(finalStateNameArr[i])));
            }


            br.close();


        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("STATES: ");
        for(i = 0; i < stateList.size(); i++){
            System.out.println("STATE: " + stateList.get(i).getStateName());
            for(k = 0; k < stateList.get(i).getStateTransitions().size(); k++){
                System.out.printf("TRANSITION: (" + stateList.get(i).getStateTransitions().get(k).getReadInput());
                System.out.printf(", " + stateList.get(i).getStateTransitions().get(k).getStackOnePop());
                System.out.printf(", " + stateList.get(i).getStateTransitions().get(k).getStackTwoPop());
                System.out.printf(", " + stateList.get(i).getStateTransitions().get(k).getNextState().getStateName());
                System.out.printf(", " + stateList.get(i).getStateTransitions().get(k).getStackOnePush());
                System.out.println(", " + stateList.get(i).getStateTransitions().get(k).getStackTwoPush() + ")");
            }
            
        }

        System.out.println("INITIAL STATES: " + initialState.getStateName());
        System.out.printf("FINAL STATES: ");
        for(i = 0; i < finalStateList.size(); i++){
            System.out.printf("" + finalStateList.get(i).getStateName());
        }
    
        System.out.printf("\n");
        
        State currentState;

        i = 0;

        // String testStr = "HELLO" + " ";
        // System.out.println(testStr.length());


        // while((i <= inputString.length()) && !isStringRejected){

        //     if(i < inputString.length()){
        //         c = inputString.charAt(i);
        //         System.out.println("CHARACTER READ: " + c);
        //     }



        //     k = 0;
        //     isTransitionFound = false;
        //     while(k < currentState.getStateTransitions().size() && !isTransitionFound){

                
        //         Transition transition = currentState.getStateTransitions().get(k);

        //         System.out.println("TRANSITION: ");
        //         System.out.printf(transition.getReadInput() + " ");
        //         System.out.printf(transition.getStackOnePop() + " ");
        //         System.out.printf(transition.getStackTwoPop() + " ");
        //         System.out.printf(transition.getNextState().getStateName() + " ");
        //         System.out.printf(transition.getStackOnePush() + " ");
        //         System.out.println(transition.getStackTwoPush());


        //         System.out.println("STACK ZERO POP: " + transition.getStackOnePop());
        //         System.out.println("STACK ONE POP: " + transition.getStackTwoPop());
        //         System.out.println("STACK ZERO TOP: " + stackZero.peek());
        //         System.out.println("STACK ONE TOP: " + stackOne.peek());
        //         System.out.println("STACK ZERO PUSH: " + transition.getStackOnePush());
        //         System.out.println("STACK ONE PUSH: " + transition.getStackTwoPush());

        //         System.out.println("STACK ZERO: " + stackZero);
        //         System.out.println("STACK ONE: " + stackOne);

        //         if(i < inputString.length()){
        //             System.out.println("CHARACTER READ: " + c);
        //         }


        //         System.out.println("TRANSITION READ INPUT: " + transition.getReadInput().charAt(0));
        //         if((c == transition.getReadInput().charAt(0) && isStringValidForPop(stackZero, transition.getStackOnePop()) && isStringValidForPop(stackOne, transition.getStackTwoPop())) || (i == inputString.length() && transition.getReadInput().charAt(0) == 'ε' && isStringValidForPop(stackZero, transition.getStackOnePop()) && isStringValidForPop(stackOne, transition.getStackTwoPop()))){
        //             popString(stackZero, transition.getStackOnePop());
        //             popString(stackOne, transition.getStackTwoPop());

        //             pushString(stackZero, transition.getStackOnePush());
        //             pushString(stackOne, transition.getStackTwoPush());

        //             System.out.println("STACK ZERO: " + stackZero);
        //             System.out.println("STACK ONE: " + stackOne);

        //             isTransitionFound = true;

        //             currentState = transition.getNextState();

        //             System.out.println("TRANSITION FOUND!");
        //             System.out.println("NEXT STATE: " + currentState.getStateName());
        //         }

        //         k++;
        //     }

        //     if(!isTransitionFound){
        //         System.out.println("REJECT STRING PLS");
        //         isStringRejected = true;
        //     }

        //     if(currentState == finalState){
        //         System.out.println("FINAL STATE");
        //     }

        //     i++;
        // }




        // System.out.println("IS STRING REJECTED: " + isStringRejected);
        // if(!isStringRejected){
        //     System.out.println("ACCEPT STRING :O");
        // }
        // System.out.println(stackZero);
        // System.out.println(stackOne);


    }
}