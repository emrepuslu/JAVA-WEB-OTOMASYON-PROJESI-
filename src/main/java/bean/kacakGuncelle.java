package bean;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.control.TableColumn.CellEditEvent;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

@ManagedBean(name="k")
@RequestScoped
public class kacakGuncelle implements Serializable {
     private static final long serialVersionUID = 1L;
    int id;
    Connection con;
    List<kacakC> liste=new ArrayList<kacakC>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
   
 
    public List<kacakC> getListe()
    {
        return liste;
    }
 
    public String KayitCek()
    {
        try{
            Class.forName("com.mysql.jdbc.Driver");
            con=DriverManager.getConnection("jdbc:mysql://localhost:3306/veritabani","root","root");
            PreparedStatement ps=con.prepareStatement("SELECT * FROM kacak WHERE id LIKE ?");//İsme göre sorgu yapıyoruz.
            ps.setInt(1, id);//İsmin tamamını değil bir kısmını girseniz bile o kısmı içeren isimleri sorguya koyar.
            ResultSet rs=ps.executeQuery();
            while(rs.next())
            {
           kacakC aa=new kacakC();
           aa.setId(rs.getInt("id"));//Güncelleme için eşsiz alan lazım.ID bizim eşsiz alanımız.
           aa.setAd(rs.getString("ad"));
           aa.setSoyad(rs.getString("soyad"));
           aa.setCinsiyet(rs.getString("cinsiyet"));
           aa.setHakkinda(rs.getString("hakkinda"));
           aa.setYas(rs.getString("yas"));
           aa.setGozRengi(rs.getString("gozRengi"));
           aa.setResim(rs.getString("resim"));
           aa.setUyruk(rs.getString("uyruk"));
           aa.setSacRengi(rs.getString("sacRengi"));
           aa.setGuncellenebilirlik(false);//İlk başta normal yazı halinde gelmesi için güncellenebilirlik kapalı.
           liste.add(aa);
            }
        }
        catch(Exception e)
        {
            System.err.println(e);
        }
       return null;
    }
    public String DegisikliğiKaydet() {
		for (kacakC adialani : liste){
			adialani.setGuncellenebilirlik(false);
		}
                //Kayıt güncellenince güncellenebilirliği tekrar kapatılır ve değişiklik işlenir.
		return null;
 
	}
    
    public boolean Guncelle()//Güncellemeyi veri tabanına aktaracak metod.
    {
        int i=0;
        try{
        Class.forName("com.mysql.jdbc.Driver");
        con=DriverManager.getConnection("jdbc:mysql://localhost:3306/veritabani","root","root");
        PreparedStatement ps=con.prepareStatement("UPDATE kacak SET ad=?,soyad=?,uyruk=?,yas=?,hakkinda=?,sacRengi=?, gozRengi=?,resim=?,cinsiyet=? WHERE id=?");
           for(kacakC item:liste)
           //liste içindeki adı,alanı ve ID kısımlarını kullanmak için böyle bir döngü tanımladık. 
           {
            ps.setString(1, item.ad);
            ps.setString(2, item.soyad);
             ps.setString(3, item.uyruk);
              ps.setString(4, item.yas);
               ps.setString(5, item.hakkinda);
                ps.setString(6, item.sacRengi);
                 ps.setString(7, item.gozRengi);
                  ps.setString(8, item.resim);
                   ps.setString(9, item.cinsiyet);
            ps.setInt(10, item.id);
           i= ps.executeUpdate();//İşlem  başarılı olursa i 0'dan büyük değer alır. Olmazsa küçük değer alır.
           }
        }
        catch(Exception e)
        {
            System.err.print(e);
        }
        if(i>0)
        return true;//İşlemin başarılı olması durumunda true döner.
        else
        return false;//Başarısız olma durumunda false döner.
    }
    public String guncellenebilirligiDegistir(kacakC a)
    {
        a.setGuncellenebilirlik(true);
        return null;
    }
}
