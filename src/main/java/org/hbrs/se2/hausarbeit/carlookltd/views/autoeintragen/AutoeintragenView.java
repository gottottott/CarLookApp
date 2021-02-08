package org.hbrs.se2.hausarbeit.carlookltd.views.autoeintragen;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.hbrs.se2.hausarbeit.carlookltd.views.main.MainView;

@Route(value = "AutoEintragen", layout = MainView.class)
@PageTitle("Auto eintragen")
@CssImport("./styles/views/autoeintragen/autoeintragen-view.css")
public class AutoeintragenView extends HorizontalLayout {

    private TextField name;
    private Button sayHello;

    public AutoeintragenView() {
        addClassName("autoeintragen-view");
        name = new TextField("Auto eintragen");
        sayHello = new Button("Say hello");
        add(name, sayHello);
        setVerticalComponentAlignment(Alignment.END, name, sayHello);
        sayHello.addClickListener(e -> {
            Notification.show("Hello " + name.getValue());
        });
    }

}
