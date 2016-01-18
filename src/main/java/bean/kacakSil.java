
package bean;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
@ManagedBean
@RequestScoped
public class kacakSil {
    
    int id;
    
    Connection con;
    PreparedStatement ps;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    int i;
    public boolean sil(){
        mesaj nesne = new mesaj();
        if(id==0){
            nesne.eksik("ID");
        } else {
        try{
                Class.forName("com.mysql.jdbc.Driver");
                con=DriverManager.getConnection("jdbc:mysql://localhost:3306/veritabani","root","root");
                ps=con.prepareStatement("DELETE FROM kacak WHERE id=?");
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
