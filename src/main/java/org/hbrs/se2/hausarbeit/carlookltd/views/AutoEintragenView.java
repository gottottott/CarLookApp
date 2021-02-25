package org.hbrs.se2.hausarbeit.carlookltd.views;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.hbrs.se2.hausarbeit.carlookltd.model.objects.dto.Auto;
import org.hbrs.se2.hausarbeit.carlookltd.process.control.AutoSearchControl;
import org.hbrs.se2.hausarbeit.carlookltd.process.control.LoginControl;
import org.hbrs.se2.hausarbeit.carlookltd.process.control.exceptions.DatabaseException;

@Route(value = "AutoEintragen", layout = MainView.class)
@PageTitle("Auto eintragen")
@CssImport("./styles/views/view.css")
public class AutoEintragenView extends VerticalLayout {
    public AutoEintragenView() {
        addClassName("Auto-eintragen-view");
        if(LoginControl.checkIsVertriebler()) {
            setup();
        };
    }
    private void setup() {
        H3 welcome = new H3("Bitte tragen Sie hier ein neues Auto ein:");
        TextField markeField = new TextField("Marke");
        IntegerField baujahrField = new IntegerField();
        baujahrField.setValue(2010);
        baujahrField.setHasControls(true);
        baujahrField.setMin(1900);
        baujahrField.setMax(2021);
        TextArea beschreibungArea = new TextArea("Beschreibung");
        beschreibungArea.getStyle().set("minHeight", "150px");
        Button addButton = new Button("Auto eintragen");
        add(welcome, markeField, baujahrField, beschreibungArea, addButton);
        setAlignItems(Alignment.CENTER);

        addButton.addClickListener(e -> {
            Auto auto = new Auto(markeField.getValue(), baujahrField.getValue(), beschreibungArea.getValue());
            try {
                AutoSearchControl.getInstance().addAuto(auto);
                markeField.setValue("");
                baujahrField.setValue(2010);
                beschreibungArea.setValue("");
                new Notification("Auto erfolgreich hinzugefügt", 5000).open();
            } catch (DatabaseException databaseException) {
                new Notification().show("Fehler: Datensatz schon vorhanden!");
            }


        });
    }
}