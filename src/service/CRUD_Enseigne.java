/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import util.DataSource;
import entites.Enseigne;
/**
 *
 * @author Mohamed El Hammi
 */
public class CRUD_Enseigne {
    Statement st;
    PreparedStatement pst;
    ResultSet res;
    /*neksa el horaire*/
    public void insertSt(Enseigne e) throws SQLException{
        String req="insert into enseigne (idUtilisateur,type,nom,description,adresse,note,specialite,numTel,photo1,photo2,photo3) values ('"+e.getIdUtilisateur()+"', '"+e.getType()+"','"+e.getNom()+"', '"+e.getDescription()+"', '"+e.getAdresse()+"', '"+e.getNote()+"', '"+e.getSpecialite()+"','"+e.getNumTel()+"', '"+e.getPhoto1()+"', '"+e.getPhoto2()+"', '"+e.getPhoto3()+"')";
        st=DataSource.getInstance().getConnection().createStatement();
        st.executeUpdate(req);
    }
    /*public void insertPst(Personne p) throws SQLException{
        String req="insert into personne (nom,prenom) values (?,?)";
        pst=DataSource.getInstance().getConnection().prepareStatement(req);
        pst.setString(1, p.getNom());
        pst.setString(2, p.getPrenom());
        pst.executeUpdate();
    }*/
    public void delete(int id) throws SQLException{
        String req="delete from enseigne where idEnseigne ='"+id+"'; ";
        st=DataSource.getInstance().getConnection().createStatement();
        st.executeUpdate(req);
    }
    
    public void delete(String nom) throws SQLException{
        String req="delete from enseigne where nom='"+nom+"'";
        st=DataSource.getInstance().getConnection().createStatement();
        st.executeUpdate(req);
    }
    
    public List<Enseigne> displayAll(int idUtilisateur) throws SQLException{
        String req="select * from enseigne where idUtilisateur = '"+idUtilisateur+"'";
        st=DataSource.getInstance().getConnection().createStatement();
        res=st.executeQuery(req);
        List<Enseigne> l=new ArrayList<>();
        while(res.next()){
            Enseigne e=new Enseigne(res.getInt("idEnseigne"),res.getInt("idUtilisateur"),res.getString("type"),res.getString("nom"),res.getString("description"),res.getString("adresse"),res.getFloat("note"),res.getString("specialite"),res.getString("numTel"),res.getInt("signalEnseigne"),res.getString("photo1"),res.getString("photo2"),res.getString("photo3"));
            l.add(e);
        }
        return l;
    }
    
    public Enseigne findByName(String nom , int idUtilisateur) throws SQLException{
            Enseigne e = null;
        String req="select * from enseigne where nom ='"+nom+"' and idUtilisateur = '"+idUtilisateur+"'";
        st=DataSource.getInstance().getConnection().createStatement();
        res=st.executeQuery(req);
        while(res.next()){
             e=new Enseigne(res.getInt("idEnseigne"),res.getInt("idUtilisateur"),res.getString("type"),res.getString("nom"),res.getString("description"),res.getString("adresse"),res.getFloat("note"),res.getString("specialite"),res.getString("numTel"),res.getInt("signalEnseigne"),res.getString("photo1"),res.getString("photo2"),res.getString("photo3"));
        }
        return e;
    }
    
    public void updateEnseigne(Enseigne e,int idEnseigne,int idUtilisateur) throws SQLException{
        String req="UPDATE enseigne SET type = '"+e.getType()+"',nom = '"+e.getNom()+"',description = '"+e.getDescription()+"',adresse = '"+e.getAdresse()+"',specialite = '"+e.getSpecialite()+"',numTel= '"+e.getNumTel()+"',note = '"+e.getNote()+"',photo1 = '"+e.getPhoto1()+"',photo2 = '"+e.getPhoto2()+"',photo3 = '"+e.getPhoto3()+"'  WHERE idEnseigne = '"+idEnseigne+"' and idUtilisateur ='"+idUtilisateur+"'";
        st=DataSource.getInstance().getConnection().createStatement();
        st.executeUpdate(req);
    }
    
    public List<Enseigne> displayType(String type) throws SQLException{
        String req="select * from enseigne where type = '"+type+"' ";
        st=DataSource.getInstance().getConnection().createStatement();
        res=st.executeQuery(req);
        List<Enseigne> l=new ArrayList<>();
        while(res.next()){
            Enseigne e=new Enseigne(res.getInt("idEnseigne"),res.getInt("idUtilisateur"),res.getString("type"),res.getString("nom"),res.getString("description"),res.getString("adresse"),res.getFloat("note"),res.getString("specialite"),res.getString("numTel"),res.getInt("signalEnseigne"),res.getString("photo1"),res.getString("photo2"),res.getString("photo3"));
            l.add(e);
        }
        return l;
    }
    
    public List<Enseigne> displayAllAdmin() throws SQLException{//enseigne.nom, enseigne.type,enseigne.note,enseigne.signal
        String req = "SELECT utilisateur.nom as nomUtilisateur , enseigne.* FROM enseigne INNER JOIN utilisateur ON enseigne.idUtilisateur=utilisateur.id";
        st=DataSource.getInstance().getConnection().createStatement();
        res=st.executeQuery(req);
        List<Enseigne> l=new ArrayList<>();
        while(res.next()){
            Enseigne e=new Enseigne(res.getString("nomUtilisateur"),res.getInt("idEnseigne"),res.getInt("idUtilisateur"),res.getString("type"),res.getString("nom"),res.getString("description"),res.getString("adresse"),res.getFloat("note"),res.getString("specialite"),res.getString("numTel"),res.getInt("signalEnseigne"),res.getString("photo1"),res.getString("photo2"),res.getString("photo3"));
            l.add(e);
        }
        return l;
    }
    
    public int nombreEnseigne(String type) throws SQLException {
        int nbr = 0;
        String req = "Select count(*) as nombre from enseigne where type = '" +type+ "'";
        st=DataSource.getInstance().getConnection().createStatement();
        res = st.executeQuery(req);
        while(res.next()){
             nbr = res.getInt("nombre");
        }
        return nbr;
    }
    
    public void addSignal(int idEnseigne) throws SQLException {
        String req = "UPDATE enseigne SET signalEnseigne = signalEnseigne + 1 WHERE idEnseigne = '"+idEnseigne+"'";
        st=DataSource.getInstance().getConnection().createStatement();
        st.executeUpdate(req);
        
    }
    
        public int findSignal(int idEnseigne) throws SQLException{
            int s = 0;
        String req="select signalEnseigne from enseigne where idEnseigne ='"+idEnseigne+"'";
        st=DataSource.getInstance().getConnection().createStatement();
        res=st.executeQuery(req);
        while(res.next()){
            s = res.getInt("signalEnseigne");
        }
        return s;
    }
        
            public List<Enseigne> displayEnsNote() throws SQLException {
        String req = "select * from enseigne order by note desc ";
        st=DataSource.getInstance().getConnection().createStatement();
        res=st.executeQuery(req);
        List<Enseigne> l=new ArrayList<>();
        while(res.next()){
            Enseigne e=new Enseigne(res.getInt("idEnseigne"),res.getInt("idUtilisateur"),res.getString("type"),res.getString("nom"),res.getString("description"),res.getString("adresse"),res.getFloat("note"),res.getString("specialite"),res.getString("numTel"),res.getInt("signalEnseigne"),res.getString("photo1"),res.getString("photo2"),res.getString("photo3"));
            l.add(e);
        }
        return l;
        }
        
        
    public void updateNoteEnseigne(int idEnseigne,float note) throws SQLException{
        String req="UPDATE enseigne set note = '"+note+"' where idEnseigne = '"+idEnseigne+"'";
        st=DataSource.getInstance().getConnection().createStatement();
        st.executeUpdate(req);
    }
    
    
}
