/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author EPUSLU
 */
@ManagedBean
@RequestScoped
public class kayipSil {

    int id;
    
    Connection con;
    PreparedStatement ps;
    int i;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    
    
    public boolean sil(){
        mesaj nesne = new mesaj();
        if(id==0){
            nesne.eksik("ID");
        } else {
        try{
                Class.forName("com.mysql.jdbc.Driver");
                con=DriverManager.getConnection("jdbc:mysql://localhost:3306/veritabani","root","root");
                ps=con.prepareStatement("DELETE FROM kayip WHERE id=?");
                ps.setInt(1, id);
                i=ps.executeUpdate();
                nesne.kayitSilme();
                }
                catch(Exception e)
                {
                    System.out.print(e);
                }
        }
        return false;
    }
}
