package org.hbrs.se2.hausarbeit.carlookltd.views.logout;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.hbrs.se2.hausarbeit.carlookltd.views.main.MainView;

@Route(value = "logout", layout = MainView.class)
@PageTitle("Logout")
@CssImport("./styles/views/logout/logout-view.css")
public class LogoutView extends HorizontalLayout {

    private TextField name;
    private Button sayHello;

    public LogoutView() {
        addClassName("logout-view");
        name = new TextField("Your name");
        sayHello = new Button("Say hello");
        add(name, sayHello);
        setVerticalComponentAlignment(Alignment.END, name, sayHello);
        sayHello.addClickListener(e -> {
            Notification.show("Hello " + name.getValue());
        });
    }

}
