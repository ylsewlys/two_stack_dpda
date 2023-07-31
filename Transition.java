public class Transition {

    // Transition Function
    private String readInput;
    private String stkOnePop;
    private String stkTwoPop;

    private State nextState;
    private String stkOnePush;
    private String stkTwoPush;


    public Transition(String readInput, String stkOnePop, String stkTwoPop, State nextState, String stkOnePush, String stkTwoPush){
        this.readInput = readInput;
        this.stkOnePop = stkOnePop;
        this.stkTwoPop = stkTwoPop;
        this.nextState = nextState;
        this.stkOnePush = stkOnePush;
        this.stkTwoPush = stkTwoPush;
    }


    // GETTERS

    public String getReadInput(){
        return readInput;
    }

    public String getStackOnePop(){
        return stkOnePop;
    }

    public String getStackTwoPop(){
        return stkTwoPop;
    }

    public State getNextState(){
        return nextState;
    }

    public String getStackOnePush(){
        return stkOnePush;
    }

    public String getStackTwoPush(){
        return stkTwoPush;
    }



}
