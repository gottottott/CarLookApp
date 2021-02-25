package org.hbrs.se2.hausarbeit.carlookltd.views;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.hbrs.se2.hausarbeit.carlookltd.process.control.LoginControl;

@Route(value = "Logout", layout = MainView.class)
@PageTitle("Logout")
@CssImport("./styles/views/view.css")
public class LogoutView extends HorizontalLayout {

    private TextField name;
    private Button sayHello;

    public LogoutView() {
        addClassName("logout-view");
        LoginControl.logOutUser();
    }
}
