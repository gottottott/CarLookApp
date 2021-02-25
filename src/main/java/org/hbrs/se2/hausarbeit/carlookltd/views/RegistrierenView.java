package org.hbrs.se2.hausarbeit.carlookltd.views;


import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.hbrs.se2.hausarbeit.carlookltd.model.dao.UserDAO;
import org.hbrs.se2.hausarbeit.carlookltd.model.objects.dto.User;
import org.hbrs.se2.hausarbeit.carlookltd.process.control.LoginControl;
import org.hbrs.se2.hausarbeit.carlookltd.process.control.exceptions.DatabaseException;
import org.hbrs.se2.hausarbeit.carlookltd.process.control.exceptions.NoSuchUserOrPassword;

@Route(value = "registrieren", layout = MainView.class)
@PageTitle("Registrieren")
@CssImport("./styles/views/view.css")
public class RegistrierenView extends VerticalLayout {
    final TextField vNameField = new TextField("Vorname");
    final TextField nNameField = new TextField("Nachname");
    final TextField mailField = new TextField("E-Mail");
    final PasswordField passwordField = new PasswordField("Passwort");
    final Button registerButton = new Button("Registrieren");

    public RegistrierenView() {
        addClassName("registrieren-view");
        H3 welcomeLabel = new H3("Wir freuen uns das du dich bei Carlook Ltd. registrieren möchtest. ");
        add(welcomeLabel, vNameField, nNameField, mailField, passwordField, registerButton);
        setAlignItems(FlexComponent.Alignment.CENTER);

        registerButton.addClickListener(e -> {
            User user = new User(vNameField.getValue(), nNameField.getValue(), mailField.getValue(), passwordField.getValue());

            try {
                if (checkValues()) {
                    LoginControl.getInstance().addUser(user);
                    new Notification("Registrierung erfolgreich!", 5000).open();
                }
            } catch(NoSuchUserOrPassword ex){
                Notification.show("Fehler");
                vNameField.setValue("");
                nNameField.setValue("");
                mailField.setValue("");
                passwordField.setValue("");
            }
        });
    }
    private boolean checkValues() {
        boolean check = true;
        if(vNameField.isEmpty()) {
            new Notification("Bitte geben Sie einen Vornamen an!", 2000).open();
            check = false;
        }
        if(nNameField.isEmpty()) {
            new Notification("Bitte geben Sie einen Nachnamen an!",2000).open();
            check = false;
        }
        if(mailField.isEmpty()) {
            new Notification("Bitte geben Sie eine Email an!",2000).open();
            check = false;
        }
        if(passwordField.isEmpty()) {
            new Notification("Bitte geben Sie ein Passwort an!",2000).open();
            check = false;
        }
        return check;
    }
}

