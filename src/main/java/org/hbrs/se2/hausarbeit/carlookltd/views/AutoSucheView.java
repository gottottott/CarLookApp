package org.hbrs.se2.hausarbeit.carlookltd.views;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
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

@Route(value = "AutoSuche", layout = MainView.class)
@PageTitle("AutoSuche")
@CssImport("./styles/views/view.css")
public class AutoSucheView extends VerticalLayout {
    int idSelected;

    Grid<Auto> grid = new Grid<>(Auto.class);

    public AutoSucheView() {
        if (LoginControl.checkIsEndkunde()) {
            setup();
        }
    }
    public void setup() {
        User user = (User) VaadinSession.getCurrent().getAttribute(Roles.CURRENT_USER);
        addClassName("eingestellte-autos-view");
        TextField sucheTextField = new TextField("Suche");
        Button sucheButton = new Button("Suche");
        grid.setItems(AutoSearchControl.getInstance().getAutosByMarke(""));
        configureGrid();
        Button deleteButton = new Button("Auto reservieren");
        add(sucheTextField, sucheButton, grid, deleteButton);

        sucheButton.addClickListener(e -> {
            grid.setItems(AutoSearchControl.getInstance().getAutosByMarke(sucheTextField.getValue()));
        });


        grid.addItemClickListener(
                event -> {
                    idSelected = event.getItem().getId();
                });
        deleteButton.addClickListener(e -> {
            ReservierungsControl.getInstance().addReservierung(idSelected);
            new Notification("Auto wurde reserviert!", 5000).open();
        });
    }
    public void configureGrid() {
        grid.setColumns("id", "marke","baujahr","beschreibung","vertrieblerId");
        grid.getColumns().forEach(col-> col.setAutoWidth(true));
    }
}

