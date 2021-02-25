package org.hbrs.se2.hausarbeit.carlookltd.process.control;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.crud.CrudI18n;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.page.Page;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.hbrs.se2.hausarbeit.carlookltd.model.dao.AutoDAO;
import org.hbrs.se2.hausarbeit.carlookltd.model.objects.dto.Auto;
import org.hbrs.se2.hausarbeit.carlookltd.process.control.exceptions.DatabaseException;
import org.hbrs.se2.hausarbeit.carlookltd.views.MainView;

import java.util.ArrayList;
import java.util.List;

@Route(value = "autosearch", layout = MainView.class)
@PageTitle("Auto Suchen")
@CssImport("./styles/views/view.css")
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
    public List<Auto> getAutosByMarke (String marke) {
        return AutoDAO.getInstance().getAutosByMarke(marke);
    }
    public List<Auto> getAutosByVertriebler(int vertriebler) {
       return AutoDAO.getInstance().getAutosByVertriebler(vertriebler);
    }

    public void deleteCarByID(int idSelected) {
        AutoDAO.getInstance().deleteCarByID(idSelected);
        UI.getCurrent().getPage().reload();
        Notification.show("Datensatz mit der ID: " + idSelected + "erfolgreich gelöscht!");
    }
    public boolean addAuto(Auto auto) throws DatabaseException {
        return AutoDAO.getInstance().addAuto(auto);
    }
}
