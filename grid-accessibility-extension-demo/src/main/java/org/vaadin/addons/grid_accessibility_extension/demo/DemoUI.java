package org.vaadin.addons.grid_accessibility_extension.demo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.annotation.WebServlet;

import org.vaadin.addons.grid_accessibility_extension.CellRendererConfiguration;
import org.vaadin.addons.grid_accessibility_extension.GridAccessibilityExtension;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.data.provider.ListDataProvider;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Grid.Column;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

@Theme("demo")
@Title("GridAccessbilityExtension Add-on Demo")
@SuppressWarnings("serial")
public class DemoUI extends UI {

    @WebServlet(value = "/*", asyncSupported = true)
    @VaadinServletConfiguration(productionMode = false, ui = DemoUI.class)
    public static class Servlet extends VaadinServlet {
    }

    @Override
    protected void init(VaadinRequest request) {

        Grid<Person> personGrid = new Grid<>();
        List<Person> list = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            list.add(new Person("a" + i, "a1" + i, "" + i));
            list.add(new Person("a" + i, "a2" + i, "" + i));
            list.add(new Person("a" + i, "a3" + i, "" + i));
        }

        personGrid.setDataProvider(new ListDataProvider<Person>(list));
        Column<Person, String> c1 = personGrid
                .addColumn(person -> "<b>" + person.getName() + "</b>")
                .setCaption("First Name")
                .setAssistiveCaption("First name").setId("first-name");
        personGrid.addColumn(person -> "<b>" + person.getLastName() + "</b>")
        .setId("last-name")
        .setCaption("Last Name").setAssistiveCaption("Last name");
        personGrid.addColumn(Person::getAttr1).setId("attr1")
        .setCaption("Attribute 1").setAssistiveCaption("Attribute 1");

        // add HeaderRow to join first name and last name to full name
        personGrid.addHeaderRowAt(0);
        personGrid.getHeaderRow(0).join("first-name", "last-name")
                .setText("Full name");

        HashMap<String, CellRendererConfiguration> columnIdToCellHeaders = new HashMap<>();

        // no HTML content, two headers
        columnIdToCellHeaders.put("first-name",
                new CellRendererConfiguration(
                        new String[] { "first-name", "full-name" }, false));

        // HTML content, two headers
        columnIdToCellHeaders.put("last-name",
                new CellRendererConfiguration(
                        new String[] { "last-name", "full-name" }, true));

        // no HTML content
        columnIdToCellHeaders.put("attr1", new CellRendererConfiguration(
                new String[] { "attribute-1" }, false));

        // all, including empty, header ids in order of appearance
        String[] idsForHeaders = { "full-name", "", "", "first-name",
                "last-name", "attribute-1" };

        GridAccessibilityExtension.extend(personGrid, columnIdToCellHeaders,
                idsForHeaders);

        final VerticalLayout layout = new VerticalLayout();

        layout.addComponent(personGrid);
        layout.setSizeFull();
        layout.setMargin(false);
        layout.setSpacing(false);
        personGrid.setSizeFull();
        layout.setComponentAlignment(personGrid, Alignment.MIDDLE_CENTER);
        setContent(layout);
    }
}
