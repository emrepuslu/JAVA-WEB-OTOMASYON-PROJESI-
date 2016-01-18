package bean;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
@ManagedBean
@RequestScoped
public class haberSil {

    String baslik;
    Connection con;
    PreparedStatement ps;
    int i;

    public String getBaslik() {
        return baslik;
    }

    public void setBaslik(String baslik) {
        this.baslik = baslik;
    }
    
     public boolean sil(){
          mesaj nesne = new mesaj();
        if(baslik.equals("")){
            nesne.eksik("Başlık");
        } else {
        try{
                Class.forName("com.mysql.jdbc.Driver");
                con=DriverManager.getConnection("jdbc:mysql://localhost:3306/veritabani","root","root");
                ps=con.prepareStatement("DELETE FROM haber WHERE baslik=?");
                ps.setString(1, baslik);
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
