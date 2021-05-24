package sgpca.uv.mx.domain;

/**
 *
 * @author azul_
 */
public class Action {
    private int idAction;
    private String title;
    private String description;
    private String result;
    private int idObjective;

    public Action() {
    }

    public Action(int idAction, String title, String description) {
        this.idAction = idAction;
        this.title = title;
        this.description = description;
    }

    public Action(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public Action(String title, String description, int idObjective) {
        this.title = title;
        this.description = description;
        this.idObjective = idObjective;
    }

    public int getIdAction() {
        return idAction;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getResult() {
        return result;
    }

    public int getIdObjective() {
        return idObjective;
    }

    public void setIdAction(int idAction) {
        this.idAction = idAction;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public void setIdObjective(int idObjective) {
        this.idObjective = idObjective;
    }
    
    
}
