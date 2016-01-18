
package bean;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;


@ManagedBean(name="haberEkle")
@RequestScoped
public class haberEkle {

    int i=0;
    String resim, baslik, icerik;

    public String getResim() {
        return resim;
    }

    public void setResim(String resim) {
        this.resim = resim;
    }

    public String getBaslik() {
        return baslik;
    }

    public void setBaslik(String baslik) {
        this.baslik = baslik;
    }

    public String getIcerik() {
        return icerik;
    }

    public void setIcerik(String icerik) {
        this.icerik = icerik;
    }
    
    public String ekle(){
        mesaj nesne = new mesaj();
        if(resim.equals("") ||baslik.equals("") || icerik.equals("")){
            if(resim.equals(""))
                    nesne.eksik("resim");
            if(baslik.equals(""))
                    nesne.eksik("Başlık");
            if(icerik.equals(""))
                    nesne.eksik("İçerik");
        }else{
            PreparedStatement ps=null;
        Connection con=null;
        try{
            Class.forName("com.mysql.jdbc.Driver");
            con=DriverManager.getConnection("jdbc:mysql://localhost:3306/veritabani","root","root");
            ps=con.prepareStatement("INSERT INTO haber(resim, baslik, icerik) VALUES(?,?,?)");
            ps.setString(1, resim);
            ps.setString(2, baslik);
            ps.setString(3, icerik);
            i=ps.executeUpdate();
            nesne.kayitEkleme();
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        finally 
        {
            try{
            con.close();
            ps.close();
            }
            catch(Exception e)
            {
                System.out.println(e);
            }
        }
        }
        return null;
    }
    
}
