import java.util.Stack;
import java.util.ArrayList;
import java.lang.StringBuilder;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;


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

        boolean isStringRejected = false;
        boolean isTransitionFound;
        String acceptStringBy = "Empty Stack";

        
        ArrayList<State> stateList = new ArrayList<State>();
        ArrayList<String> stateNameList = new ArrayList<String>();
        ArrayList<String> inputList = new ArrayList<String>();


        Stack<Character> stackZero = new Stack<Character>();
        Stack<Character> stackOne = new Stack<Character>();

        ArrayList<Character> stackSymbolList = new ArrayList<Character>();

        State initialState = new State("");
        ArrayList<State> finalStateList = new ArrayList<State>();

        // FOR READING INPUTS
        Scanner sc = new Scanner(System.in);

        // FOR LOOPS
        int i, k;
        char c = ' ';

        // FOR MENU OPTIONS
        char menuInput = ' ';
        char acceptBasisInput = ' ';

        // FOR FILE READING
        String machineFilename = "";
        boolean isFileFound = false;

        // FOR MACHINE INPUT
        String inputString = "";



        while(!(menuInput == 'E' || menuInput == 'e')){

            System.out.println("----------------------------------------------------------------");
            System.out.println("Two-Stack Deterministic Pushdown Automaton Program");
            System.out.println("Group Members: ");
            System.out.println("Austria, Rafael Antonio ");
            System.out.println("Larraquel, Reign Elaiza ");
            System.out.println("Samson, Wesly");
            System.out.println("----------------------------------------------------------------\n\n");
            System.out.println("[S]tart Program\n[E]xit Program");

            System.out.printf("\nEnter your choice: ");
            menuInput = sc.nextLine().charAt(0);


            if(!(menuInput == 'S' || menuInput == 's' || menuInput == 'E' || menuInput == 'e')){
                while(!(menuInput == 'S' || menuInput == 's' || menuInput == 'E' || menuInput == 'e')){
                    System.out.println("You entered an invalid input! Please choose among the given options...");
                    System.out.println("[S]tart Program\n[E]xit Program");
                    System.out.printf("\nEnter your choice: ");
                    menuInput = sc.nextLine().charAt(0);
                }
            }


            if(menuInput == 'S' || menuInput == 's'){
                

                menuInput = ' ';
                System.out.println("\nChoose a basis for string acceptance:\n[F]inal State Basis\n[E]mpty Stack Basis\n\n");

                System.out.printf("Enter your choice: ");
                acceptBasisInput = sc.nextLine().charAt(0);

                if(!(acceptBasisInput == 'F' || acceptBasisInput == 'f' || acceptBasisInput == 'E' || acceptBasisInput == 'e')){
                    while(!(acceptBasisInput == 'F' || acceptBasisInput == 'f' || acceptBasisInput == 'E' || acceptBasisInput == 'e')){

                        System.out.println("You entered an invalid input! Please choose among the given options...");
                        System.out.println("Choose a basis for string acceptance:\n[F]inal State Basis\n[E]mpty Stack Basis\n");

                        System.out.printf("\nEnter your choice: ");
                        acceptBasisInput = sc.nextLine().charAt(0);

                    }

                }

                if(acceptBasisInput == 'F' || acceptBasisInput == 'f'){
                    acceptStringBy = "Final State";
                }else if(acceptBasisInput == 'E' || acceptBasisInput == 'e'){
                    acceptStringBy = "Empty Stack";
                }

                acceptBasisInput = ' ';


                isFileFound = false;
                while(!isFileFound){

                    System.out.println("\nPlease provide the proper filename of your machine file in text format. Please ensure that the machine file is strictly a deterministic two-stack pushdown automaton with proper formats and definitions inside the text file.");
                    System.out.printf("\n\nEnter your machine's filename (including .txt): ");
                    machineFilename = sc.nextLine();

                    // READ FILE
                    try {
                        // INITIALIZE LISTS 
                        
                        BufferedReader br;
                        br = new BufferedReader(new FileReader("machine_text_files/" + machineFilename));


                        int stateCount;
                        int inputCount;
                        int transitionCount;
                        int finalStateCount;

                        stateCount = Integer.parseInt(br.readLine());

                        String line = br.readLine();
                        String[] stateNameArr = line.split(" ");

                        
                        stateList.clear();
                        stateNameList.clear();
                    
                        for(i = 0; i < stateCount; i++){
                            stateList.add(new State(stateNameArr[i]));
                            stateNameList.add(stateNameArr[i]);
                        }

                        inputCount = Integer.parseInt(br.readLine());

                        line = br.readLine();
                        String[] inputArr = line.split(" ");

                        inputList.clear();
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

                        finalStateList.clear();
                        for(i = 0; i < finalStateCount; i++){
                            stateList.get(stateNameList.indexOf(finalStateNameArr[i])).setFinalStateStatus(true);
                            finalStateList.add(stateList.get(stateNameList.indexOf(finalStateNameArr[i])));
                        }

                        String[] stackSymbolsArr = br.readLine().split(" ");
                        stackZero.push(stackSymbolsArr[0].charAt(0));
                        stackOne.push(stackSymbolsArr[1].charAt(0));


                        stackSymbolList.clear();
                        stackSymbolList.add(stackSymbolsArr[0].charAt(0));
                        stackSymbolList.add(stackSymbolsArr[1].charAt(0));

                        isFileFound = true;

                        System.out.println("FILE LOADED...");
                        br.close();


                    } catch (IOException e) {
                        System.out.println("\nERROR: FILE NOT FOUND!\nPlease ensure that the file exists and you have entered the proper filename and extensions");
                        isFileFound = false;
                    }



                }






                inputString = "";

                if(!(inputString.compareTo("Exit") == 0)){
                    while(!(inputString.compareTo("Exit") == 0)){

                        System.out.printf("Enter string input based on the given input alphabet [");

                        for(i = 0; i < inputList.size(); i++){
                            if(i == inputList.size() - 1){
                                System.out.printf("" + inputList.get(i));
                            }else{
                                System.out.printf("" + inputList.get(i) + " ");
                            }

                        }


                        System.out.printf("]. Otherwise, input 'Exit' to go back to menu.\nEnter input: ");
                        inputString = sc.nextLine();

                        if(!(inputString.compareTo("Exit") == 0)){
                            // INITIALIZE STACK
                            stackZero.clear();
                            stackOne.clear();

                            stackZero.push(stackSymbolList.get(0));
                            stackOne.push(stackSymbolList.get(1));



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
                            System.out.println("------------------------");
                            System.out.println("INITIAL STATE: " + initialState.getStateName());
                            System.out.printf("FINAL STATES: ");
                            for(i = 0; i < finalStateList.size(); i++){
                                System.out.printf("" + finalStateList.get(i).getStateName());
                            }
                        
                            System.out.printf("\n");
                            
                            State currentState = initialState;

                            i = 0;

                            // String testStr = "HELLO" + " ";
                            // System.out.println(testStr.length());

                            isStringRejected = false;

                            while((i <= inputString.length()) && !isStringRejected){

                                System.out.println("------------------------");
                                if(i < inputString.length()){
                                    c = inputString.charAt(i);
                                    System.out.println("CHARACTER READ: " + c);
                                }



                                k = 0;
                                isTransitionFound = false;
                                while(k < currentState.getStateTransitions().size() && !isTransitionFound){

                                    
                                    Transition transition = currentState.getStateTransitions().get(k);

                                    if((c == transition.getReadInput().charAt(0) && i < inputString.length() && isStringValidForPop(stackZero, transition.getStackOnePop()) && isStringValidForPop(stackOne, transition.getStackTwoPop())) || (i == inputString.length() && transition.getReadInput().charAt(0) == 'ε' && isStringValidForPop(stackZero, transition.getStackOnePop()) && isStringValidForPop(stackOne, transition.getStackTwoPop()))){
                                        System.out.println("------------------------");
                                        System.out.println("TRANSITION CHOSEN: ");


                                        System.out.println("STACK ZERO POP: " + transition.getStackOnePop());
                                        System.out.println("STACK ONE POP: " + transition.getStackTwoPop());
                                        System.out.println("STACK ZERO TOP: " + stackZero.peek());
                                        System.out.println("STACK ONE TOP: " + stackOne.peek());
                                        System.out.println("STACK ZERO PUSH: " + transition.getStackOnePush());
                                        System.out.println("STACK ONE PUSH: " + transition.getStackTwoPush());                                
                                            
                                        popString(stackZero, transition.getStackOnePop());
                                        popString(stackOne, transition.getStackTwoPop());

                                        pushString(stackZero, transition.getStackOnePush());
                                        pushString(stackOne, transition.getStackTwoPush());

                                        System.out.println("STACK ZERO: " + stackZero);
                                        System.out.println("STACK ONE: " + stackOne);

                                        isTransitionFound = true;

                                        currentState = transition.getNextState();

                                        System.out.println("TRANSITION FOUND!");
                                        System.out.println("NEXT STATE: " + currentState.getStateName());
                                    }

                                    k++;
                                }


                                // IF NO TRANSITION IS FOUND
                                if(!isTransitionFound){
                                    System.out.println("REJECT STRING PLS");
                                    isStringRejected = true;
                                }

                                if(finalStateList.indexOf(currentState) != -1){
                                    System.out.println("FINAL STATE");
                                }

                                i++;
                            }


                            // IF WHOLE STRING INPUT IS READ, CHECK IF STRING IS TO BE ACCEPTED VIA FINAL STATE OR EMPTY STACKS

                            if(acceptStringBy == "Final State"){
                                if(currentState.getFinalStateStatus() == true){
                                    isStringRejected = false;
                                    System.out.println("FINAL STATE REACHED! ACCEPT STRING");
                                }else{
                                    isStringRejected = true;
                                    System.out.println("FINAL STATE NOT REACHED");
                                }
                            }else if(acceptStringBy == "Empty Stack"){
                                if(stackZero.isEmpty() == true && stackOne.isEmpty() == true){
                                    isStringRejected = false;
                                    System.out.println("BOTH STACKS ARE EMPTY");
                                }else{
                                    isStringRejected = true;
                                    System.out.println("EITHER OF THE STACKS IS NOT YET EMPTY");
                                }
                            }



                            System.out.println("IS STRING REJECTED: " + isStringRejected);
                            if(!isStringRejected){
                                System.out.println("ACCEPT STRING :O");
                            }
                            System.out.println(stackZero);
                            System.out.println(stackOne);                                           
                        }

     
                    }

                }else{
                    System.out.println("Going back to menu...");
                }

            }


        }



    }
}