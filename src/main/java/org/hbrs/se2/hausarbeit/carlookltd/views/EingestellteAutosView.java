package org.hbrs.se2.hausarbeit.carlookltd.views;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.charts.model.Label;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;
import org.hbrs.se2.hausarbeit.carlookltd.model.dao.AutoDAO;
import org.hbrs.se2.hausarbeit.carlookltd.model.dao.UserDAO;
import org.hbrs.se2.hausarbeit.carlookltd.model.objects.dto.Auto;
import org.hbrs.se2.hausarbeit.carlookltd.model.objects.dto.User;
import org.hbrs.se2.hausarbeit.carlookltd.process.control.AutoSearchControl;
import org.hbrs.se2.hausarbeit.carlookltd.process.control.LoginControl;
import org.hbrs.se2.hausarbeit.carlookltd.process.control.ReservierungsControl;
import org.hbrs.se2.hausarbeit.carlookltd.services.util.Roles;
import com.vaadin.flow.router.RouteAlias;

@Route(value = "EingestellteAutos", layout = MainView.class)
@PageTitle("Eingestellte Autos")
@CssImport("./styles/views/view.css")
@RouteAlias(value = "", layout = MainView.class)
public class EingestellteAutosView extends VerticalLayout {
    int idSelected;
    User user = (User) VaadinSession.getCurrent().getAttribute(Roles.CURRENT_USER);
    Grid<Auto> grid = new Grid<>(Auto.class);

    public EingestellteAutosView() {
        if(LoginControl.checkIsVertriebler()) {
            setup();
        }
    }
    public void setup() {
        addClassName("eingestellte-autos-view");
        H2 header = new H2("Deine eingestellten Autos: ");
        Label label = new Label();

        grid.setItems(AutoSearchControl.getInstance().getAutosByVertriebler(user.getId()));
        configureGrid();
        Button deleteButton = new Button("Auto löschen");
        add(header, grid, deleteButton);

        grid.addItemClickListener(
                event -> {
                    idSelected = event.getItem().getId();
                });
        deleteButton.addClickListener(e -> {
            AutoSearchControl.getInstance().deleteCarByID(idSelected);
            new Notification("Auto erfolgreich gelöscht!", 5000).open();
        });
    }
    public void configureGrid() {
        grid.setColumns("id", "marke","baujahr","beschreibung","vertrieblerId");
        grid.getColumns().forEach(col-> col.setAutoWidth(true));
       }
}

