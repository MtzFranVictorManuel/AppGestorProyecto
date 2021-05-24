package sgpca.uv.mx.bussinesslogic;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import sgpca.uv.mx.dataaccess.ConnectDB;
import sgpca.uv.mx.domain.Blueprint;

public class BlueprintDAO extends ConnectDB implements IBlueprintDAO {

    @Override
    public boolean saveBlueprint(Blueprint blueprint) {
        boolean savedBlueprint = false;
        try{
            getDataBaseConnection();
            PreparedStatement saveBlueprintStatement = this.connection.prepareStatement("INSERT into anteproyecto (titulo, fechaInicio, duracion, descripcion, fkProyectosInvestigacion) values (?, ?, ?, ?, 1) ");
            saveBlueprintStatement.setString(1, blueprint.getBlueprintTitle());
            saveBlueprintStatement.setDate(2, blueprint.getStartDate());
            saveBlueprintStatement.setString(3, blueprint.getDuration());
            saveBlueprintStatement.setString(4, blueprint.getDescription());
            saveBlueprintStatement.executeUpdate();
            savedBlueprint = true;
            saveBlueprintStatement.close();
        }catch(SQLException exSQL){
            System.out.println("Error " + exSQL);
        }finally{
            closeDataBaseConnection();
        }
        return savedBlueprint;
    }

    
    @Override
    public boolean modifyBlueprint(Blueprint blueprint, int idAnteproyecto) {
        boolean modifiedBlueprint = false;
        try{
            getDataBaseConnection();
            PreparedStatement modifyBlueprintStatement = this.connection.prepareStatement("UPDATE anteproyecto set titulo = ?, fechaInicio = ?, duracion = ?, descripcion = ? where idAnteproyecto = ?");
            modifyBlueprintStatement.setString(1, blueprint.getBlueprintTitle());
            modifyBlueprintStatement.setDate(2, blueprint.getStartDate());
            modifyBlueprintStatement.setString(3, blueprint.getDuration());
            modifyBlueprintStatement.setString(4, blueprint.getDescription());
            modifyBlueprintStatement.setInt(5, idAnteproyecto);
            modifyBlueprintStatement.executeUpdate();
            modifiedBlueprint = true;
            modifyBlueprintStatement.close();
        }catch(SQLException exSQL){
            System.out.println("Error " + exSQL);
        }finally{
            closeDataBaseConnection();
        }
        return modifiedBlueprint;
    }

    
    @Override
    public boolean deleteBlueprint(Blueprint blueprint) {
        boolean deletedBlueprint = false;
        try{
            getDataBaseConnection();
            PreparedStatement deleteBlueprintStatement = this.connection.prepareStatement("DELETE from anteproyecto where titulo = ?");
            deleteBlueprintStatement.setString(1, blueprint.getBlueprintTitle());
            deleteBlueprintStatement.executeUpdate();
            deletedBlueprint = true;
            deleteBlueprintStatement.close();
        }catch(SQLException exSQL){
            System.out.println("Error " + exSQL);
        }finally{
            closeDataBaseConnection();
        }
        return deletedBlueprint;
    }

    
    @Override
    public Blueprint consultBlueprintDetails(int idBlueprint) {
        Blueprint blueprintDetails = new Blueprint();
        try{
            getDataBaseConnection();
            PreparedStatement consultBlueprintStatement = this.connection.prepareStatement("SELECT * from anteproyecto where idAnteproyecto = ?");
            consultBlueprintStatement.setInt(1, idBlueprint);
            ResultSet consultSet = consultBlueprintStatement.executeQuery();
            if(consultSet.next()){
                blueprintDetails.setBlueprintTitle(consultSet.getString("titulo"));
                blueprintDetails.setStartDate(consultSet.getDate("fechaInicio"));
                blueprintDetails.setDuration(consultSet.getString("duracion"));
                blueprintDetails.setDescription(consultSet.getString("descripcion"));
            }
            consultBlueprintStatement.close();
            consultSet.close();          
        }catch(SQLException exSQL){
            System.out.println("Error: " + exSQL);
        }finally{
            closeDataBaseConnection();
        }
        return blueprintDetails;
    }

    
    @Override
    public List<Blueprint> consultBlueprintList() {
        List<Blueprint> allBlueprints = new ArrayList();
        try{
            this.getDataBaseConnection();
            PreparedStatement listBPStatement = this.connection.prepareStatement("SELECT titulo, fechaInicio, duracion from anteproyecto");
            ResultSet bpSet = listBPStatement.executeQuery();
            while(bpSet.next()){
                Blueprint recoveredBP = new Blueprint();
                recoveredBP.setBlueprintTitle(bpSet.getString("titulo"));
                recoveredBP.setStartDate(bpSet.getDate("fechaInicio"));
                recoveredBP.setDuration(bpSet.getString("duracion"));
                allBlueprints.add(recoveredBP);
            }
            listBPStatement.close();
            bpSet.close();
        }catch(SQLException exSQL){
            System.out.println("Error " + exSQL);
        }finally{
            this.closeDataBaseConnection();
        }
        return allBlueprints;
    }
    
}
