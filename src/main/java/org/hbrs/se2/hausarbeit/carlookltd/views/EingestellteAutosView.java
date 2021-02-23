package org.hbrs.se2.hausarbeit.carlookltd.views;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H3;
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

@Route(value = "AutoListe", layout = MainView.class)
@PageTitle("Eingestellte Autos")
@CssImport("./styles/views/view.css")
@RouteAlias(value = "", layout = MainView.class)
public class EingestellteAutosView extends VerticalLayout {
    int idSelected;
    User user = (User) VaadinSession.getCurrent().getAttribute(Roles.CURRENT_USER);
    Grid<Auto> grid = new Grid<>(Auto.class);

    public EingestellteAutosView() {
        if(user == null) {
            LoginControl.logOutUser();
        }
        else if(user.getIstVertriebler()){
            setupVertriebler();
        } else {
            setupEndkunde();
        }
    }
    public void setupVertriebler() {
        addClassName("eingestellte-autos-view");
        grid.setItems(AutoSearchControl.getInstance().getAutosByVertriebler(user.getId()));
        configureGrid();
        Button deleteButton = new Button("Auto löschen");
        add(grid, deleteButton);

        grid.addItemClickListener(
                event -> {
                    idSelected = event.getItem().getId();
                });
        deleteButton.addClickListener(e -> {
            AutoSearchControl.getInstance().deleteCarByID(idSelected);
        });
    }
    public void setupEndkunde() {
        addClassName("auto-view");
        grid.setItems(AutoSearchControl.getInstance().getAutos());
        Button reservButton = new Button("Reservieren");
        add(grid, reservButton);
        configureGrid();
        grid.addItemClickListener(
                event -> {
                    idSelected = event.getItem().getId();
                });
        reservButton.addClickListener(e -> {
            ReservierungsControl.getInstance().addReservierung(idSelected);
        });
    }
    public void configureGrid() {
        grid.setColumns("id", "marke","baujahr","beschreibung","vertrieblerId");
        grid.getColumns().forEach(col-> col.setAutoWidth(true));
       }
}

