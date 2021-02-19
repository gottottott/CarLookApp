package org.hbrs.se2.hausarbeit.carlookltd.views.eingestellteautos;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.charts.model.Label;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.hbrs.se2.hausarbeit.carlookltd.model.objects.dto.Auto;
import org.hbrs.se2.hausarbeit.carlookltd.model.objects.dto.User;
import org.hbrs.se2.hausarbeit.carlookltd.process.control.AutoSearchControl;
import org.hbrs.se2.hausarbeit.carlookltd.services.util.Roles;
import org.hbrs.se2.hausarbeit.carlookltd.views.main.MainView;
import com.vaadin.flow.router.RouteAlias;

@Route(value = "AutoListe", layout = MainView.class)
@PageTitle("Eingestellte Autos")
@CssImport("./styles/views/eingestellteautos/eingestellte-autos-view.css")
@RouteAlias(value = "", layout = MainView.class)
public class EingestellteAutosView extends HorizontalLayout {

    Grid<Auto> grid = new Grid<>(Auto.class);

    public EingestellteAutosView() {

        setup();

    }
    public void setup() {
        addClassName("eingestellte-autos-view");
        grid.setItems(AutoSearchControl.getInstance().getAutobyVertriebler(2));
        add(grid);
        configureGrid();
    }
    public void configureGrid() {
        grid.setColumns("id","marke","baujahr","beschreibung");
        grid.getColumns().forEach(col-> col.setAutoWidth(true));
       }
}

