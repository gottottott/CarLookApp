package org.hbrs.se2.hausarbeit.carlookltd.views.eingestellteautos;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.hbrs.se2.hausarbeit.carlookltd.views.main.MainView;
import com.vaadin.flow.router.RouteAlias;

@Route(value = "AutoListe", layout = MainView.class)
@PageTitle("Eingestellte Autos")
@CssImport("./styles/views/eingestellteautos/eingestellte-autos-view.css")
@RouteAlias(value = "", layout = MainView.class)
public class EingestellteAutosView extends HorizontalLayout {

    private TextField name;
    private Button sayHello;

    public EingestellteAutosView() {
        addClassName("eingestellte-autos-view");
        name = new TextField("Tabele Autos");
        sayHello = new Button("Say hello");
        add(name, sayHello);
        setVerticalComponentAlignment(Alignment.END, name, sayHello);
        sayHello.addClickListener(e -> {
            Notification.show("Hello " + name.getValue());
        });
    }

}
