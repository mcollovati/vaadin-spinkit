package org.vaadin.spinkit.demo;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.data.util.converter.StringToEnumConverter;
import com.vaadin.data.util.converter.StringToFloatConverter;
import com.vaadin.data.validator.FloatRangeValidator;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.*;

import com.vaadin.ui.themes.ValoTheme;
import org.vaadin.spinkit.Spinner;
import org.vaadin.spinkit.SpinnerLabel;
import org.vaadin.spinkit.shared.SpinnerType;

import java.util.Arrays;

import javax.servlet.annotation.WebServlet;

@Theme("demo")
@Title("Vaadin Spinkit Add-on Demo")
@SuppressWarnings("serial")
public class DemoUI extends UI {

    private Spinner widgetSpinner;
    private Spinner widgetSpinnerCustomStyle;
    private SpinnerLabel labelSpinner;

    @WebServlet(value = "/*", asyncSupported = true)
    @VaadinServletConfiguration(productionMode = false, ui = DemoUI.class, widgetset = "org.vaadin.spinkit.demo.DemoWidgetSet")
    public static class Servlet extends VaadinServlet {
    }

    @Override
    protected void init(VaadinRequest request) {
        VerticalLayout layout = new VerticalLayout();
        layout.setDefaultComponentAlignment(Alignment.TOP_CENTER);

        layout.addComponent(title("Default spinners"));
        layout.addComponent(spinnersContainer());
        layout.addComponent(title("Themed spinners"));
        layout.addComponent(spinnersContainer("greenspin"));
        layout.addComponent(title("Label spinners"));

        layout.addComponent(labelSpinnersContainer());
        layout.setExpandRatio(layout.getComponent(layout.getComponentCount()-1),1);

        setContent(layout);
    }

    private Label title(String title) {
        Label label = new Label(title);
        label.addStyleName(ValoTheme.LABEL_H2);
        label.addStyleName(ValoTheme.LABEL_COLORED);
        return label;
    }

    private HorizontalLayout spinnersContainer() {
        return spinnersContainer(null);
    }
    private HorizontalLayout spinnersContainer(String primaryStyleName) {
        HorizontalLayout spinners = new HorizontalLayout();
        spinners.setWidth(100, Unit.PERCENTAGE);
        StringToEnumConverter converter = new StringToEnumConverter();
        for (SpinnerType type : SpinnerType.values()) {
            Spinner spinner = new Spinner(type);
            spinner.setCaption(converter.convertToPresentation(type, String.class, getLocale()));
            if (primaryStyleName != null) {
                spinner.setPrimaryStyleName(primaryStyleName);
            }
            spinners.addComponent(spinner);
        }
        return spinners;
    }
    private HorizontalLayout labelSpinnersContainer() {
        HorizontalLayout spinners = new HorizontalLayout();
        spinners.setWidth(100, Unit.PERCENTAGE);
        StringToEnumConverter converter = new StringToEnumConverter();
        for (SpinnerType type : SpinnerType.values()) {
            SpinnerLabel spinner = new SpinnerLabel(type);
            spinner.setValue("Text with spinner");
            spinner.setCaption(converter.convertToPresentation(type, String.class, getLocale()));
            spinners.addComponent(spinner);
        }
        return spinners;
    }


    protected void initOld(VaadinRequest request) {

        // Initialize our new UI component

        SpinnerType initialType = SpinnerType.ROTATING_PLANE;
        widgetSpinner = new Spinner(initialType);
        widgetSpinnerCustomStyle = new Spinner(initialType);
        widgetSpinnerCustomStyle.setPrimaryStyleName("greenspin");
        labelSpinner = new SpinnerLabel(initialType);

        // Show it in the middle of the screen
        final HorizontalLayout layout = new HorizontalLayout();
        layout.setMargin(true);
        layout.setSpacing(true);
        layout.setSizeFull();

        widgetSpinner.setCaption("Manudulis");
        layout.addComponent(new Label("Spinner"));
        layout.addComponent(widgetSpinner);

        layout.addComponent(new Label("Spinner (greenspin style)"));
        layout.addComponent(widgetSpinnerCustomStyle);

        layout.addComponent(new Label("SpinnerLabel"));
        layout.addComponent(labelSpinner);


        VerticalLayout mainLayout = new VerticalLayout();
        mainLayout.setStyleName("demoContentLayout");
        mainLayout.setMargin(true);
        mainLayout.setSpacing(true);
        mainLayout.setSizeFull();
        mainLayout.addComponent(createTools(initialType));
        mainLayout.addComponent(layout);
        mainLayout.setExpandRatio(layout, 1);
        setContent(mainLayout);
    }

    private HorizontalLayout createTools(SpinnerType initialType) {
        ComboBox selector = new ComboBox("Select spinner type", Arrays.asList(SpinnerType.values()));
        selector.setNullSelectionAllowed(false);
        selector.setPageLength(0);
        selector.setValue(initialType);
        selector.addValueChangeListener(e -> {
            widgetSpinner.setType((SpinnerType) e.getProperty().getValue());
            widgetSpinnerCustomStyle.setType((SpinnerType) e.getProperty().getValue());
            labelSpinner.setSpinnerType((SpinnerType) e.getProperty().getValue());
        });
        TextField size = new TextField("Size (from 10px to 200px)");
        size.setNullRepresentation("");
        size.setImmediate(true);
        size.setConverter(new StringToFloatConverter());
        size.addValidator(new FloatRangeValidator("Size must be between 10px and 200px", 10f, 200f));
        size.addValueChangeListener(e -> {
            if (size.isValid()) {
                try {
                    Float fsize = (Float) size.getConvertedValue();
                    widgetSpinner.setWidth(fsize, Unit.PIXELS);
                    widgetSpinner.setHeight(fsize, Unit.PIXELS);
                    widgetSpinnerCustomStyle.setWidth(fsize, Unit.PIXELS);
                    widgetSpinnerCustomStyle.setHeight(fsize, Unit.PIXELS);
                    labelSpinner.setWidth(fsize, Unit.PIXELS);
                    labelSpinner.setHeight(fsize, Unit.PIXELS);
                    labelSpinner.setValue("Size is now " + fsize);
                } catch (Exception ex) {
                }
            }
        });

        HorizontalLayout topLayout = new HorizontalLayout(selector, size);
        return topLayout;
    }


}
