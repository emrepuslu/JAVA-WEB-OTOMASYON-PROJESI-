
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
@ManagedBean(name="haberBean")
@RequestScoped
public class haber implements Serializable{
    private static final long serialVersionUID = 1L;
    List<haberA> liste=new ArrayList<haberA>();
    Connection con;
   
    public List<haberA> getListe(){
        return liste;
    }
    public String haberCek(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            con=DriverManager.getConnection("jdbc:mysql://localhost:3306/veritabani","root","root");
            PreparedStatement ps=con.prepareStatement("SELECT * FROM haber ORDER BY id DESC");
            ResultSet rs=ps.executeQuery();
            while(rs.next())
            {
                haberA hA= new haberA();
                hA.setResim(rs.getString("resim"));
                hA.setBaslik(rs.getString("baslik"));
                hA.setIcerik(rs.getString("icerik"));
                liste.add(hA);
            }
        }
        catch(Exception e)
        {
            System.err.println(e);
        }
        return null;
    }
    
   
}
