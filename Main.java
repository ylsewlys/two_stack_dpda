import java.util.Stack;
import java.util.ArrayList;
import java.lang.StringBuilder;


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

        String inputString = "aaabbbcccc";
        boolean isAccept = false;
        boolean isFinalState = false;
        boolean isStringRejected = false;
        boolean isTransitionFound;

        
        ArrayList<State> stateList = new ArrayList<State>();

        Stack<Character> stackZero = new Stack<Character>();
        Stack<Character> stackOne = new Stack<Character>();

        // for while loops
        int i, k;
        char c = ' ';


        ArrayList<Transition> q0Transitions = new ArrayList<Transition>();
        ArrayList<Transition> q1Transitions = new ArrayList<Transition>();
        ArrayList<Transition> q2Transitions = new ArrayList<Transition>();
        ArrayList<Transition> qfTransitions = new ArrayList<Transition>();      

        Character initialStackZeroSymbol = 'X';
        Character initialStackOneSymbol = 'Y';

        stackZero.push(initialStackZeroSymbol);
        stackOne.push(initialStackOneSymbol);

        // INITIALIZE STATES
        State stateZero = new State("q0");
        State stateOne = new State("q1");
        State stateTwo = new State("q2");
        State finalState = new State("qf");


        // STATE NAME LIST IN STRING FORMAT
        ArrayList<String> strStateList = new ArrayList<String>();

        strStateList.add(stateZero.getStateName());
        strStateList.add(stateOne.getStateName());
        strStateList.add(stateTwo.getStateName());
        strStateList.add(finalState.getStateName());

        // INITIALIZE TRANSITIONS

        // q0 Transitions
        Transition q0TsOne = new Transition("a", "a", "Y", stateZero, "aa", "Y");
        Transition q0TsTwo = new Transition("a", "X", "Y", stateZero, "aX", "Y");
        Transition q0TsThree = new Transition("b", "a", "Y", stateOne, "\u03B5", "bY");

        q0Transitions.add(q0TsOne);
        q0Transitions.add(q0TsTwo);
        q0Transitions.add(q0TsThree);

        // q1 Transitions
        Transition q1TsOne = new Transition("b", "a", "b", stateOne, "\u03B5", "bb");
        Transition q1TsTwo = new Transition("c", "X", "b", stateTwo, "X", "\u03B5");

        q1Transitions.add(q1TsOne);
        q1Transitions.add(q1TsTwo);        

        // q2 Transitions
        Transition q2TsOne = new Transition("c", "X", "b", stateTwo, "X", "\u03B5");
        Transition q2TsTwo = new Transition("\u03B5", "X", "Y", finalState, "ε", "ε");

        q2Transitions.add(q2TsOne);
        q2Transitions.add(q2TsTwo);       

        // SET STATE TRANSITIONS
        stateZero.setTransitionList(q0Transitions);
        stateOne.setTransitionList(q1Transitions);
        stateTwo.setTransitionList(q2Transitions);
        finalState.setTransitionList(qfTransitions);





        // ADD STATES TO STATES LIST
        stateList.add(stateZero);
        stateList.add(stateOne);
        stateList.add(stateTwo);
        stateList.add(finalState);




        State currentState = stateZero;

        i = 0;

        String testStr = "HELLO" + " ";
        System.out.println(testStr.length());


        while((i <= inputString.length()) && !isStringRejected){

            if(i < inputString.length()){
                c = inputString.charAt(i);
                System.out.println("CHARACTER READ: " + c);
            }



            k = 0;
            isTransitionFound = false;
            while(k < currentState.getStateTransitions().size() && !isTransitionFound){

                
                Transition transition = currentState.getStateTransitions().get(k);

                System.out.println("TRANSITION: ");
                System.out.printf(transition.getReadInput() + " ");
                System.out.printf(transition.getStackOnePop() + " ");
                System.out.printf(transition.getStackTwoPop() + " ");
                System.out.printf(transition.getNextState().getStateName() + " ");
                System.out.printf(transition.getStackOnePush() + " ");
                System.out.println(transition.getStackTwoPush());


                System.out.println("STACK ZERO POP: " + transition.getStackOnePop());
                System.out.println("STACK ONE POP: " + transition.getStackTwoPop());
                System.out.println("STACK ZERO TOP: " + stackZero.peek());
                System.out.println("STACK ONE TOP: " + stackOne.peek());
                System.out.println("STACK ZERO PUSH: " + transition.getStackOnePush());
                System.out.println("STACK ONE PUSH: " + transition.getStackTwoPush());

                System.out.println("STACK ZERO: " + stackZero);
                System.out.println("STACK ONE: " + stackOne);

                if(i < inputString.length()){
                    System.out.println("CHARACTER READ: " + c);
                }


                System.out.println("TRANSITION READ INPUT: " + transition.getReadInput().charAt(0));
                if((c == transition.getReadInput().charAt(0) && isStringValidForPop(stackZero, transition.getStackOnePop()) && isStringValidForPop(stackOne, transition.getStackTwoPop())) || (i == inputString.length() && transition.getReadInput().charAt(0) == 'ε' && isStringValidForPop(stackZero, transition.getStackOnePop()) && isStringValidForPop(stackOne, transition.getStackTwoPop()))){
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

            if(!isTransitionFound){
                System.out.println("REJECT STRING PLS");
                isStringRejected = true;
            }

            if(currentState == finalState){
                System.out.println("FINAL STATE");
            }

            i++;
        }




        System.out.println("IS STRING REJECTED: " + isStringRejected);
        if(!isStringRejected){
            System.out.println("ACCEPT STRING :O");
        }
        System.out.println(stackZero);
        System.out.println(stackOne);


    }
}