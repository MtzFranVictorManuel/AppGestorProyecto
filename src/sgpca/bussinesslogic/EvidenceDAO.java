package sgpca.bussinesslogic;

import sgpca.dataaccess.ConnectDB;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import sgpca.domain.Evidence;

public class EvidenceDAO extends ConnectDB implements IEvidenceDAO{

    @Override
    public boolean saveEvidence(Evidence evidence) {
        boolean evidenceSave = false;
        try{
            getDataBaseConnection();
            PreparedStatement evidenceState = this.connection.prepareStatement("INSERT into evidencia (nombre, autor, tipo, fecha, fkIntegrante) values (?, ?, ? ,?, 1)");
            evidenceState.setString(1, evidence.getEvidenceName());
            evidenceState.setString(2, evidence.getAutor());
            evidenceState.setString(3, evidence.getEvidenceType());
            evidenceState.setDate(4, evidence.getDate());
            evidenceState.executeUpdate();
            evidenceSave = true;
            evidenceState.close();
        }catch(SQLException exSQL){
            System.out.println("Error: " + exSQL);
        }finally{
            closeDataBaseConnection();
        }
        return evidenceSave;
    }

    
    @Override
    public boolean modifyEvidence(Evidence evidence, int idEvidence) {
        boolean modifyEvidence = false;
        try{
            getDataBaseConnection();
            PreparedStatement updateEvidenceStatement = this.connection.prepareStatement("UPDATE evidencia set nombre = ?, autor = ?, tipo = ?, fecha = ? where idEvidencia = ?");
            updateEvidenceStatement.setString(1, evidence.getEvidenceName());
            updateEvidenceStatement.setString(2, evidence.getAutor());
            updateEvidenceStatement.setString(3, evidence.getEvidenceType());
            updateEvidenceStatement.setDate(4, evidence.getDate());
            updateEvidenceStatement.setInt(5, idEvidence);
            updateEvidenceStatement.executeUpdate();
            modifyEvidence = true;
            updateEvidenceStatement.close();
        }catch(SQLException exSQL){
            System.out.println("Error: " + exSQL);
        }finally{
            closeDataBaseConnection();
        }
        return modifyEvidence;
    }

    
    @Override
    public boolean deleteEvidence(Evidence evidence) {
        boolean deleteEvidence = false;
        try{
            getDataBaseConnection();
            PreparedStatement deleteEvidenceStatement = this.connection.prepareStatement("DELETE from evidencia where nombre = ?");
            deleteEvidenceStatement.setString(1, evidence.getEvidenceName());
            deleteEvidenceStatement.executeUpdate();         
            deleteEvidence = true;
            deleteEvidenceStatement.close();
        }catch(SQLException exSQL){
            System.out.println("Error: " + exSQL);
        }finally{
            closeDataBaseConnection();
        }
        return deleteEvidence;
    }

    
    @Override
    public Evidence consultEvidenceDetails(int idEvidence) {
        Evidence evidenceDetails = new Evidence();
        try{
            getDataBaseConnection();
            PreparedStatement consultStatement = this.connection.prepareStatement("SELECT nombre, autor, tipo, fecha from evidencia where idEvidencia = ?");
            consultStatement.setInt(1, idEvidence);
            ResultSet consultSet = consultStatement.executeQuery();
            if(consultSet.next()){
                evidenceDetails.setEvidenceName(consultSet.getString("nombre"));
                evidenceDetails.setAutor(consultSet.getString("autor"));
                evidenceDetails.setEvidenceType(consultSet.getString("tipo"));
                evidenceDetails.setDate(consultSet.getDate("fecha"));
            }
            consultStatement.close();
            consultSet.close();          
        }catch(SQLException exSQL){
            System.out.println("Error: " + exSQL);
        }finally{
            closeDataBaseConnection();
        }
        return evidenceDetails;
    }

    
    @Override
    public List<Evidence> consultEvidenceList() {
        List<Evidence> allEvidences = new ArrayList();
        try{
            this.getDataBaseConnection();
            PreparedStatement allEvidenceStatement = this.connection.prepareStatement("SELECT nombre, autor, fecha, tipo from evidencia");
            ResultSet allConsulSet = allEvidenceStatement.executeQuery();
            while (allConsulSet.next()){
                Evidence recoveredEvidence = new Evidence();
                recoveredEvidence.setEvidenceName(allConsulSet.getString("nombre"));
                recoveredEvidence.setAutor(allConsulSet.getString("autor"));
                recoveredEvidence.setDate(allConsulSet.getDate("fecha"));
                recoveredEvidence.setEvidenceType(allConsulSet.getString("tipo"));
                allEvidences.add(recoveredEvidence);
            }
            allEvidenceStatement.close();
            allConsulSet.close();
        }catch(SQLException exSQL){
            System.out.println("Error: " + exSQL);
        }finally{
            this.closeDataBaseConnection();
        }
        return allEvidences;
    }
    

    @Override
    public List<Evidence> searchEvidenceByName(String evidenceName) {
        List<Evidence> searchEvidence = new ArrayList();
        try{
            this.getDataBaseConnection();
            PreparedStatement searchEvidenceStatement = this.connection.prepareStatement("SELECT * from evidencia where nombre like '%"+evidenceName+"%'");
            ResultSet searchResult = searchEvidenceStatement.executeQuery();
            while(searchResult.next()){
                Evidence searchedEvidence = new Evidence();
                searchedEvidence.setEvidenceName(searchResult.getString("nombre"));
                searchedEvidence.setAutor(searchResult.getString("autor"));
                searchedEvidence.setDate(searchResult.getDate("fecha"));
                searchedEvidence.setEvidenceType(searchResult.getString("tipo"));
                searchEvidence.add(searchedEvidence);
            }
            searchEvidenceStatement.close();
            searchResult.close();
        }catch(SQLException exSQL){
            System.out.println("Error: " + exSQL);
        }finally{
            this.closeDataBaseConnection();
        }
        return searchEvidence;
    }
    
}