import java.util.ArrayList;


public class State {

    private String stateName;
    private ArrayList<Transition> transitionList = new ArrayList<Transition>();


    public State(String stateName){
        this.stateName = stateName;
    }

    public State(String stateName, ArrayList<Transition> transitionList){
        this.stateName = stateName;
        this.transitionList = transitionList;
    }   

    
    // SETTERS

    public void setStateName(String stateName){
        this.stateName = stateName;
    }

    public void setTransitionList(ArrayList<Transition> transitionList){
        this.transitionList = transitionList;
    }


    // GETTERS

    public String getStateName(){
        return stateName;
    }

    public ArrayList<Transition> getStateTransitions(){
        return transitionList;
    }

}
