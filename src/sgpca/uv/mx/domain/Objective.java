package sgpca.uv.mx.domain;

/**
 *
 * @author azul_
 */
public class Objective {
    private static int idObjective;
    private int idNoStaticObjective;
    private String title;
    private String strategy;
    private String result; 
    private String goal;
    private String description;
    private String targetState;
    private int idWorkplan;

    public Objective() {
    }

    public Objective(String title){
        this.title = title;
    }
    
    public Objective(String title, String strategy, String result, String goal, String description, String targetState, int idWorkplan) {
        this.title = title;
        this.strategy = strategy;
        this.result = result;
        this.goal = goal;
        this.description = description;
        this.targetState = targetState;
        this.idWorkplan = idWorkplan;
    }

    public Objective(String title, String strategy, String result, String goal, String description, String targetState) {
        this.title = title;
        this.strategy = strategy;
        this.result = result;
        this.goal = goal;
        this.description = description;
        this.targetState = targetState;
    }

    public static int getIdObjective() {
        return idObjective;
    }

    public int getIdNoStaticObjective() {
        return idNoStaticObjective;
    }

    public String getTitle() {
        return title;
    }

    public String getStrategy() {
        return strategy;
    }

    public String getResult() {
        return result;
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

    public static void setIdObjective(int idObjective) {
        Objective.idObjective = idObjective;
    }

    public void setIdNoStaticObjective(int idNoStaticObjective) {
        this.idNoStaticObjective = idNoStaticObjective;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }

    public void setStrategy(String strategy) {
        this.strategy = strategy;
    }

    public void setResult(String result) {
        this.result = result;
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
