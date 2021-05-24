package sgpca.bussinesslogic;

import java.util.List;
import sgpca.domain.Blueprint;

public interface IBlueprintDAO {
    public boolean saveBlueprint(Blueprint blueprint);
    public boolean modifyBlueprint(Blueprint blueprint, int idAnteproyecto);
    public boolean deleteBlueprint(Blueprint blueprint);
    public Blueprint consultBlueprintDetails(int idBlueprint);
    public List<Blueprint> consultBlueprintList();
}
