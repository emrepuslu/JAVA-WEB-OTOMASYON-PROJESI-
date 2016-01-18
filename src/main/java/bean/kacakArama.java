
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

@ManagedBean(name="kacakArama")
@RequestScoped
public class kacakArama implements Serializable{

    private static final long serialVersionUID = 1L;
    
    String adi;
    String text;
    String soyad, uyruk, hakkinda, 
            yas, cinsiyet, gozRengi, sacRengi;
    Connection con;
    List<kacakB> liste=new ArrayList<kacakB>();

    public String getAdi() {
        return adi;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
    
    public void setAdi(String adi) {
        this.adi = adi;
    }

    public String getSoyad() {
        return soyad;
    }

    public void setSoyad(String soyad) {
        this.soyad = soyad;
    }

    public String getUyruk() {
        return uyruk;
    }

    public void setUyruk(String uyruk) {
        this.uyruk = uyruk;
    }

    public String getHakkinda() {
        return hakkinda;
    }

    public void setHakkinda(String hakkinda) {
        this.hakkinda = hakkinda;
    }

    public String getYas() {
        return yas;
    }

    public void setYas(String yas) {
        this.yas = yas;
    }

    public String getCinsiyet() {
        return cinsiyet;
    }

    public void setCinsiyet(String cinsiyet) {
        this.cinsiyet = cinsiyet;
    }

    public String getGozRengi() {
        return gozRengi;
    }

    public void setGozRengi(String gozRengi) {
        this.gozRengi = gozRengi;
    }

    public String getSacRengi() {
        return sacRengi;
    }

    public void setSacRengi(String sacRengi) {
        this.sacRengi = sacRengi;
    }
    
    public List<kacakB> getListe()
    {
        return liste;
    }
    
    
    public String kacakAra(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            con=DriverManager.getConnection("jdbc:mysql://localhost:3306/veritabani","root","root");
            PreparedStatement ps=con.prepareStatement("SELECT * FROM kacak WHERE ad=? OR soyad=? OR uyruk=? OR cinsiyet=?"
                    + "OR gozRengi=? OR yas=? OR sacRengi=?");
            ps.setString(1,text);
            ps.setString(2,soyad);
            ps.setString(3, uyruk);
            ps.setString(4, cinsiyet);
            ps.setString(5, gozRengi);
            ps.setString(6, yas);
            ps.setString(7, sacRengi);
            ResultSet rs=ps.executeQuery();
             while(rs.next())
            {
                kacakB bb = new kacakB();
                if(text.equals(rs.getString("ad")) || soyad.equals(rs.getString("soyad"))
                         || uyruk.equals(rs.getString("uyruk")) || cinsiyet.equals(rs.getString("cinsiyet"))
                         || gozRengi.equals(rs.getString("gozRengi")) || yas.equals(rs.getString("yas"))
                         || sacRengi.equals(rs.getString("sacRengi"))){
                    bb.setAd(rs.getString("ad"));
                    bb.setResim(rs.getString("resim"));
                    bb.setSoyad(rs.getString("soyad"));
                    bb.setUyruk(rs.getString("uyruk"));
                    bb.setHakkinda(rs.getString("hakkinda"));
                    bb.setYas(rs.getString("yas"));
                    bb.setCinsiyet(rs.getString("cinsiyet"));
                    bb.setGozRengi(rs.getString("gozRengi"));
                    bb.setSacRengi(rs.getString("sacRengi"));
                    liste.add(bb);
                }
            }  
        }
        catch(Exception e)
        {
            System.err.println(e);
        }
        return null;
    }
}
