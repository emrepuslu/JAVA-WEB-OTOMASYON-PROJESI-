/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author EPUSLU
 */
@ManagedBean(name="kayipBean")
@RequestScoped
public class kayipBean implements Serializable{
    private static final long serialVersionUID = 1L;
    List<kayipA> liste=new ArrayList<kayipA>();
    Connection con;
   
    public List<kayipA> getListe(){
        return liste;
    }
    
    public String kayipCek(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            con=DriverManager.getConnection("jdbc:mysql://localhost:3306/veritabani","root","root");
            PreparedStatement ps=con.prepareStatement("SELECT * FROM kayip");
            ResultSet rs=ps.executeQuery();
            while(rs.next())
            {
                kayipA kA= new kayipA();
                kA.setResim(rs.getString("resim"));
                kA.setAd(rs.getString("ad"));
                kA.setSoyad(rs.getString("soyad"));
                kA.setUyruk(rs.getString("uyruk"));
                kA.setHakkinda(rs.getString("hakkinda"));
                kA.setYas(rs.getString("yas"));
                kA.setCinsiyet(rs.getString("cinsiyet"));
                kA.setGozRengi(rs.getString("gozRengi"));
                kA.setSacRengi(rs.getString("sacRengi"));
                liste.add(kA);
            }
        }
        catch(Exception e)
        {
            System.err.println(e);
        }
        return null;
    }
}
