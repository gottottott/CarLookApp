package org.hbrs.se2.hausarbeit.carlookltd.process.control;

import com.vaadin.flow.server.VaadinSession;
import org.hbrs.se2.hausarbeit.carlookltd.model.dao.AutoDAO;
import org.hbrs.se2.hausarbeit.carlookltd.model.dao.ReservierungsDAO;
import org.hbrs.se2.hausarbeit.carlookltd.model.objects.dto.Auto;
import org.hbrs.se2.hausarbeit.carlookltd.model.objects.dto.Reservierung;
import org.hbrs.se2.hausarbeit.carlookltd.model.objects.dto.User;
import org.hbrs.se2.hausarbeit.carlookltd.services.util.Roles;

import java.util.List;

// Control Klasse für alles was mit Reservierungen zu tun hat.
public class ReservierungsControl {

    // ....................................................................................
    // Singleton Pattern
    private static ReservierungsControl reserv;
    private  ReservierungsControl() {
    }
    public static ReservierungsControl getInstance() {
        if(reserv == null) {
            reserv = new ReservierungsControl();
        }
        return reserv;
    }
    // ....................................................................................

    // sucht aus der Session die aktuelle User ID und erstellt eine reservierung
    // anschließend wird diese Reservierung an DAO weitergegeben zum eintragen in die DB.
    public void addReservierung(int autoID) {
        User user = (User) VaadinSession.getCurrent().getAttribute(Roles.CURRENT_USER);
        Reservierung reserv = new Reservierung(autoID, user.getId());
        ReservierungsDAO.getInstance().addReservierung(reserv);
    }
    public List<Auto> getReserviertesAutoByUserID(int id) {
        return ReservierungsDAO.getInstance().getReservierteAutosByUserID(id);
    }
    // ....................................................................................
    public void deleteReservierungByID(int autoID) {
        User user = (User) VaadinSession.getCurrent().getAttribute(Roles.CURRENT_USER);
        Reservierung reserv = new Reservierung(autoID, user.getId());
        ReservierungsDAO.getInstance().deleteReservierungByID(reserv);
    }
}
