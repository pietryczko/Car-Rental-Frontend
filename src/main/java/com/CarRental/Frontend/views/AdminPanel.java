package com.CarRental.Frontend.views;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route("admin")
public class AdminPanel extends VerticalLayout {
    public AdminPanel() {
        Paragraph header = new Paragraph("ADMIN PANEL");
        Anchor mainView = new Anchor("", "Main View");
        Anchor carPanel = new Anchor("car", "Car Panel");
        HorizontalLayout buttons = new HorizontalLayout();
        buttons.add(mainView, carPanel);
        add(header, buttons);
        setSizeFull();
        setAlignItems(Alignment.CENTER);
    }
}
