package org.hbrs.se2.hausarbeit.carlookltd.views;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;
import com.vaadin.flow.server.VaadinSession;
import org.hbrs.se2.hausarbeit.carlookltd.model.objects.dto.Auto;
import org.hbrs.se2.hausarbeit.carlookltd.model.objects.dto.Reservierung;
import org.hbrs.se2.hausarbeit.carlookltd.model.objects.dto.User;
import org.hbrs.se2.hausarbeit.carlookltd.process.control.AutoSearchControl;
import org.hbrs.se2.hausarbeit.carlookltd.process.control.ReservierungsControl;
import org.hbrs.se2.hausarbeit.carlookltd.services.util.Roles;

@Route(value = "Reservieren", layout = MainView.class)
@PageTitle("Reservierte Autos")
@CssImport("./styles/views/view.css")
//@RouteAlias(value = "", layout = MainView.class)


public class ReservierungsView extends VerticalLayout {
    User user = (User) VaadinSession.getCurrent().getAttribute(Roles.CURRENT_USER);
    int idSelected;
    Grid<Auto> grid = new Grid<>(Auto.class);
    public ReservierungsView() {
        setup();
    }

    private void setup() {
        addClassName("Reservieren");
        grid.setItems(ReservierungsControl.getInstance().getReserviertesAutoByUserID(user.getId()));
        configureGrid();
        Button deleteButton = new Button("Reservierung löschen");

        add(grid, deleteButton);
        grid.addItemClickListener(
                event -> {
                    idSelected = event.getItem().getId();
                });
        deleteButton.addClickListener(e -> {
            ReservierungsControl.getInstance().deleteReservierungByID(idSelected);
        });

    }
    public void configureGrid() {
        grid.setColumns("marke","baujahr","beschreibung");
        grid.getColumns().forEach(col-> col.setAutoWidth(true));
    }
}
