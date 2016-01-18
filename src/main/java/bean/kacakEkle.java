
package bean;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;


@ManagedBean(name="kacakEkle")
@RequestScoped
public class kacakEkle {
    String resim,   ad, soyad, uyruk, hakkinda, 
            yas, cinsiyet, gozRengi, sacRengi;
    
    int i=0;
    public String getResim() {
        return resim;
    }

    public void setResim(String resim) {
        this.resim = resim;
    }

    public String getAd() {
        return ad;
    }

    public void setAd(String ad) {
        this.ad = ad;
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
    
    public String ekle(){
        mesaj nesne = new mesaj();
        if(resim.equals("") ||ad.equals("") || soyad.equals("")|| uyruk.equals("Secilmedi") || hakkinda.equals("")
                ||yas.equals("") || cinsiyet.equals("") || gozRengi.equals("Secilmedi") || sacRengi.equals("Secilmedi")){
            if(resim.equals(""))
                    nesne.eksik("resim");
            if(ad.equals(""))
                    nesne.eksik("ad");
            if(soyad.equals(""))
                    nesne.eksik("soyad");
            if(uyruk.equals("Secilmedi"))
                    nesne.eksik("uyruk");
            if(hakkinda.equals(""))
                    nesne.eksik("hakında");
            if(yas.equals(""))
                    nesne.eksik("Yaş");
            if(cinsiyet.equals(""))
                    nesne.eksik("cinsiyet");
            if(gozRengi.equals("Secilmedi"))
                    nesne.eksik("Göz Rengi");
            if(sacRengi.equals("Secilmedi"))
                    nesne.eksik("Saç Rengi");
        }else{
            PreparedStatement ps=null;
        Connection con=null;
        try{
            Class.forName("com.mysql.jdbc.Driver");
            con=DriverManager.getConnection("jdbc:mysql://localhost:3306/veritabani","root","root");
            ps=con.prepareStatement("INSERT INTO kacak(resim, ad, soyad, uyruk, hakkinda, yas, cinsiyet, gozRengi, sacRengi) VALUES(?,?,?,?,?,?,?,?,?)");
            ps.setString(1, resim);
            ps.setString(2, ad);
            ps.setString(3, soyad);
            ps.setString(4, uyruk);
            ps.setString(5, hakkinda);
            ps.setString(6, yas);
            ps.setString(7, cinsiyet);
            ps.setString(8, gozRengi);
            ps.setString(9, sacRengi);
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
