
package bean;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

@ManagedBean
@RequestScoped
public class mesaj {

     
    public void kayitEkleme() {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage("Kayıt eklendi.") );
    }
    public void kayitSilme() {
        FacesContext context = FacesContext.getCurrentInstance();
         
        context.addMessage(null, new FacesMessage("Kayıt silindi.") );
    }
    
    public void eksik(String mesaj){
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(mesaj + " kısmı boş bırakılamaz") );
    }
}
