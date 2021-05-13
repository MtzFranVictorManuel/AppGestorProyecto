package domain;

/**
 *
 * @author azul_
 */
public class Objective {
    private int idObjective;
    private String title;
    private String strategy;
    private String outcome;
    private String goal;
    private String description;
    private String targetState;
    private int idWorkplan;

    public Objective() {
    }

    public Objective(String title, String strategy, String outcome, String goal, String description, String targetState, int idWorkplan) {
        this.title = title;
        this.strategy = strategy;
        this.outcome = outcome;
        this.goal = goal;
        this.description = description;
        this.targetState = targetState;
        this.idWorkplan = idWorkplan;
    }

    public Objective(String title, String strategy, String outcome, String goal, String description, String targetState) {
        this.title = title;
        this.strategy = strategy;
        this.outcome = outcome;
        this.goal = goal;
        this.description = description;
        this.targetState = targetState;
    }

    public int getIdObjective() {
        return idObjective;
    }

    public String getTitle() {
        return title;
    }

    public String getStrategy() {
        return strategy;
    }

    public String getOutcome() {
        return outcome;
    }

    public String getGoal() {
        return goal;
    }

    public String getDescription() {
        return description;
    }
    
    public String getTargetState(){
        return targetState;
    }

    public int getIdWorkplan() {
        return idWorkplan;
    }

    public void setIdObjective(int idObjective) {
        this.idObjective = idObjective;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setStrategy(String strategy) {
        this.strategy = strategy;
    }

    public void setOutcome(String outcome) {
        this.outcome = outcome;
    }

    public void setGoal(String goal) {
        this.goal = goal;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    public void setTargetState(String targetState){
        this.targetState = targetState;
    }

    public void setIdWorkplan(int idWorkplan) {
        this.idWorkplan = idWorkplan;
    }
    
    
}
