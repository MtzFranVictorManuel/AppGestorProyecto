package sgpca.uv.mx.bussinesslogic;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import sgpca.uv.mx.dataaccess.ConnectDB;
import sgpca.uv.mx.domain.ResearchProyect;

public class ResearchProyectDAO extends ConnectDB implements IResearchProyectDAO {

    @Override
    public boolean saveResearchProyect(ResearchProyect researchProyect) {
        boolean savedRProyect = false;
        try{
            getDataBaseConnection();
            PreparedStatement saveRPStatement = this.connection.prepareStatement("INSERT into proyectoinvestigacion (titulo, fechaInicio, fechaFin, descripcion, fkIntegrante) values (?, ?, ?, ?, 1)");
            saveRPStatement.setString(1, researchProyect.getProyectTitle());
            saveRPStatement.setDate(2, researchProyect.getStartDate());
            saveRPStatement.setDate(3, researchProyect.getEndDate());
            saveRPStatement.setString(4, researchProyect.getProyectDescription());
            saveRPStatement.executeUpdate();
            savedRProyect = true;
            saveRPStatement.close();
        }catch(SQLException exSQL){
            System.out.println("Error " + exSQL);
        }finally{
            closeDataBaseConnection();
        }
        return savedRProyect;
    }

    
    @Override
    public boolean modifyResearchProyect(ResearchProyect researchProyect, int idRProyect) {
        boolean modifiedRP = false;
        try{
            getDataBaseConnection();
            PreparedStatement modifyRPStatement = this.connection.prepareStatement("UPDATE proyectoinvestigacion set titulo = ?, fechaInicio = ?, fechaFin = ?, descripcion = ? where idProyectosInvestigacion = ?");
            modifyRPStatement.setString(1, researchProyect.getProyectTitle());
            modifyRPStatement.setDate(2, researchProyect.getStartDate());
            modifyRPStatement.setDate(3, researchProyect.getEndDate());
            modifyRPStatement.setString(4, researchProyect.getProyectDescription());
            modifyRPStatement.setInt(5, idRProyect);
            modifyRPStatement.executeUpdate();
            modifiedRP = true;
            modifyRPStatement.close();
        }catch(SQLException exSQL){
            System.out.println("Error " + exSQL);
        }finally{
            closeDataBaseConnection();
        }
        return modifiedRP;
    }

    
    @Override
    public boolean deleteResearchProyect(ResearchProyect researchProyect) {
        boolean deleteRP = false;
        try{
            this.getDataBaseConnection();
            PreparedStatement deleteRPStatement = this.connection.prepareStatement("DELETE from proyectoinvestigacion where titulo = ?");
            deleteRPStatement.setString(1, researchProyect.getProyectTitle());
            deleteRPStatement.executeUpdate();
            deleteRP = true;
            deleteRPStatement.close();
        }catch(SQLException exSQL){
            System.out.println("Error " + exSQL);
        }finally{
            closeDataBaseConnection();
        }
        return deleteRP;
    }

    
    @Override
    public ResearchProyect consultRProyectDetails(int idRProyect) {
        ResearchProyect researchPDetails = new ResearchProyect();
        try{
            getDataBaseConnection();
            PreparedStatement consultRPStatement = this.connection.prepareStatement("SELECT titulo, fechaInicio, fechaFin, descripcion from proyectoinvestigacion where idProyectosInvestigacion = ?");
            consultRPStatement.setInt(1, idRProyect);
            ResultSet consultRPSet = consultRPStatement.executeQuery();
            if(consultRPSet.next()){
                researchPDetails.setProyectTitle(consultRPSet.getString("titulo"));
                researchPDetails.setStartDate(consultRPSet.getDate("fechaInicio"));
                researchPDetails.setEndDate(consultRPSet.getDate("fechaFin"));
                researchPDetails.setProyectDescription(consultRPSet.getString("descripcion"));
            }
            consultRPStatement.close();
            consultRPSet.close();          
        }catch(SQLException exSQL){
            System.out.println("Error: " + exSQL);
        }finally{
            closeDataBaseConnection();
        }
        return researchPDetails;
    }

    
    @Override
    public List<ResearchProyect> consultRProyectList() {
        List<ResearchProyect> allResearchProyects = new ArrayList();
        try{
            this.getDataBaseConnection();
            PreparedStatement listRPStatement = this.connection.prepareStatement("SELECT * from proyectoinvestigacion");
            ResultSet allRP = listRPStatement.executeQuery();
            while(allRP.next()){
                ResearchProyect recoveredRP = new ResearchProyect();
                recoveredRP.setProyectTitle(allRP.getString("titulo"));
                recoveredRP.setStartDate(allRP.getDate("fechaInicio"));
                recoveredRP.setEndDate(allRP.getDate("fechaFin"));
                allResearchProyects.add(recoveredRP);
            }
            listRPStatement.close();
            allRP.close();
        }catch(SQLException exSQL){
            System.out.println("Error " + exSQL);
        }finally{
            this.closeDataBaseConnection();
        }
        return allResearchProyects;
    }

    
    @Override
    public List<ResearchProyect> searchRPByName(String name) {
        List<ResearchProyect> searchProyects = new ArrayList();
        try{
            this.getDataBaseConnection();
            PreparedStatement searchProyectStatement = this.connection.prepareCall("SELECT * from proyectoinvestigacion where titulo = ?");
            searchProyectStatement.setString(1, name);
            ResultSet searchResult = searchProyectStatement.executeQuery();
            while(searchResult.next()){
                ResearchProyect searchedProyect = new ResearchProyect();
                searchedProyect.setProyectTitle(searchResult.getString("titulo"));
                searchedProyect.setStartDate(searchResult.getDate("fechaInicio"));
                searchedProyect.setEndDate(searchResult.getDate("fechaFin"));
                searchProyects.add(searchedProyect);
            }
            searchProyectStatement.close();
            searchResult.close();
        }catch(SQLException exSQL){
            System.out.println("Error " + exSQL);
        }finally{
            this.closeDataBaseConnection();
        }
        return searchProyects;
    }
    
    
    
}
