package org.hbrs.se2.hausarbeit.carlookltd.process.control;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.crud.CrudI18n;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.page.Page;
import org.hbrs.se2.hausarbeit.carlookltd.model.dao.AutoDAO;
import org.hbrs.se2.hausarbeit.carlookltd.model.objects.dto.Auto;

import java.util.ArrayList;
import java.util.List;

public class AutoSearchControl {
    private AutoSearchControl() {

    }
    public static AutoSearchControl search = null;

    public static AutoSearchControl getInstance() {
        if (search == null) {
            search = new AutoSearchControl();
        }
        return search;
    }
    public List<Auto> getAutos () {

        return AutoDAO.getInstance().getAutos();
    }
    public List<Auto> getAutosByVertriebler(int vertriebler) {
       return AutoDAO.getInstance().getAutosByVertriebler(vertriebler);
    }

    public void deleteCarByID(int idSelected) {
        AutoDAO.getInstance().deleteCarByID(idSelected);
        UI.getCurrent().getPage().reload();
        Notification.show("Datensatz mit der ID: " + idSelected + "erfolgreich gelöscht!");
    }
}
