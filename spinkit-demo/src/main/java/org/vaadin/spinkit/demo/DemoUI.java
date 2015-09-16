package org.vaadin.spinkit.demo;

import org.vaadin.spinkit.Spinner2;
import org.vaadin.spinkit.Spinner;
import org.vaadin.spinkit.SpinnerType;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.data.util.converter.ConverterUtil;
import com.vaadin.data.util.converter.StringToFloatConverter;
import com.vaadin.data.util.converter.StringToIntegerConverter;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

import java.util.Arrays;

@Theme("demo")
@Title("MyComponent Add-on Demo")
@SuppressWarnings("serial")
public class DemoUI extends UI
{

    @WebServlet(value = "/*", asyncSupported = true)
    @VaadinServletConfiguration(productionMode = false, ui = DemoUI.class, widgetset = "org.vaadin.spinkit.demo.DemoWidgetSet")
    public static class Servlet extends VaadinServlet {
    }

    @Override
    protected void init(VaadinRequest request) {

        // Initialize our new UI component

        SpinnerType initialType = SpinnerType.ROTATING_PLANE;
        final Spinner2 widgetSpinner = new Spinner2(initialType);
        final Spinner componentSpinner = new Spinner(initialType);

        // Show it in the middle of the screen
        final VerticalLayout layout = new VerticalLayout();
        layout.setMargin(true);
        layout.setSpacing(true);
        layout.setStyleName("demoContentLayout");
        layout.setSizeFull();

        ComboBox selector = new ComboBox("Select spinner stype", Arrays.asList(SpinnerType.values()));
        selector.setValue(initialType);
        selector.addValueChangeListener(e -> {
            widgetSpinner.setType((SpinnerType) e.getProperty().getValue());
            componentSpinner.setSpinnerType((SpinnerType) e.getProperty().getValue());
        });
        TextField size = new TextField("Size");
        size.setConverter(new StringToFloatConverter());
        size.addValueChangeListener(e -> {
            try {
                widgetSpinner.setWidth((Float)size.getConvertedValue(), Unit.PIXELS);
                widgetSpinner.setHeight((Float) size.getConvertedValue(), Unit.PIXELS);
                componentSpinner.setWidth((Float) size.getConvertedValue(), Unit.PIXELS);
                componentSpinner.setHeight((Float)size.getConvertedValue(), Unit.PIXELS);
            } catch (Exception ex) {
            }
        });

        layout.addComponent(selector);
        layout.addComponent(size);

        layout.addComponent(new Label("Widget"));
        layout.addComponent(widgetSpinner);
        layout.addComponent(new Label("Component"));
        layout.addComponent(componentSpinner);
        setContent(layout);

    }

}
