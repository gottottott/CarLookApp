package org.hbrs.se2.hausarbeit.carlookltd.views.login;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.charts.model.Label;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.hbrs.se2.hausarbeit.carlookltd.process.control.LoginControl;
import org.hbrs.se2.hausarbeit.carlookltd.process.control.exceptions.NoSuchUserOrPassword;
import org.hbrs.se2.hausarbeit.carlookltd.views.main.MainView;

@Route(value = "login", layout = MainView.class)
@PageTitle("Login")
@CssImport("./styles/views/logout/logout-view.css")

public class Login extends VerticalLayout {
    public Login() {

    addClassName("login-view");

    H1 welcomeLabel = new H1("Willkommen bei Carlook Ltd.");
    final TextField userIDTextField = new TextField("Name");
    final PasswordField passwordField = new PasswordField("Passwort");
    final Button loginButton = new Button("Login");
    add(welcomeLabel, userIDTextField, passwordField, loginButton);
    setAlignItems(FlexComponent.Alignment.CENTER);

    loginButton.addClickListener(e -> {
        String userID = userIDTextField.getValue();
        String password = passwordField.getValue();
        try {
            LoginControl.checkAuthenthication(userID, password);
        } catch (NoSuchUserOrPassword noSuchUserOrPassword) {
            Notification.show("Login fehlerhaft. Das Passwort und/oder der Name ist nicht korrekt");
            userIDTextField.setValue("");
            passwordField.setValue("");
        }

    });
    }
}
