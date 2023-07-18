package com.CarRental.Frontend.views;


import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;


@Route(value = "register")
public class RegisterSite extends VerticalLayout {

    public RegisterSite() {
        setJustifyContentMode(JustifyContentMode.CENTER);
        setAlignItems(Alignment.CENTER);
        EmailField emailField = new EmailField();
        emailField.setLabel("Email address");

        PasswordField passwordField = new PasswordField();
        passwordField.setLabel("Password");
        Div passwordStrength = new Div();
        Span passwordStrengthText = new Span();
        passwordStrength.add(new Text("Password strength: "), passwordStrengthText);
        passwordField.setHelperComponent(passwordStrength);

        TextField textField = new TextField("Login");

        Button button = new Button("Register");
        add(textField);
        add(passwordField);
        add(emailField);
        add(button);

    }
}
