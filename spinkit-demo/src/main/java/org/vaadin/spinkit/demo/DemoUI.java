package org.vaadin.spinkit.demo;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.data.util.converter.StringToEnumConverter;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import org.vaadin.spinkit.Spinner;
import org.vaadin.spinkit.SpinnerLabel;
import org.vaadin.spinkit.shared.SpinnerSize;
import org.vaadin.spinkit.shared.SpinnerType;

import javax.servlet.annotation.WebServlet;
import java.util.Arrays;

@Theme("demo")
@Title("Vaadin Spinkit Add-on Demo")
@SuppressWarnings("serial")
public class DemoUI extends UI {


    @WebServlet(value = "/*", asyncSupported = true)
    @VaadinServletConfiguration(productionMode = false, ui = DemoUI.class, widgetset = "org.vaadin.spinkit.demo.DemoWidgetSet")
    public static class Servlet extends VaadinServlet {
    }

    @Override
    protected void init(VaadinRequest request) {
        TabSheet tabSheet = new TabSheet();
        tabSheet.setSizeFull();
        tabSheet.addStyleName(ValoTheme.TABSHEET_CENTERED_TABS);
        tabSheet.addTab(spinnersContainer()).setCaption("Spinners");
        tabSheet.addTab(spinnerSizesContainer()).setCaption("Sizes");
        tabSheet.addTab(spinnersContainer("greenspin")).setCaption("Themed Spinners");
        setContent(tabSheet);
    }

    private Component spinnersContainer() {
        return spinnersContainer(null);
    }

    private Component spinnersContainer(String primaryStyleName) {
        int types = SpinnerType.values().length;
        GridLayout spinners = new GridLayout(4, (types / 4 + types % 4));
        spinners.setSizeFull();
        spinners.setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);
        spinners.setSpacing(true);
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

    private Component spinnerSizesContainer() {
        int types = SpinnerSize.values().length;
        GridLayout spinners = new GridLayout(4, (types / 4 + types % 4));
        spinners.setSizeFull();
        spinners.setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);
        spinners.setSpacing(true);

        ComboBox selector = new ComboBox("Select spinner type", Arrays.asList(SpinnerType.values()));
        selector.setNullSelectionAllowed(false);
        selector.setPageLength(0);
        selector.setValue(SpinnerType.ROTATING_PLANE);
        selector.addValueChangeListener(e -> {
            for (Component c : spinners) {
                if (c instanceof Spinner) {
                    ((Spinner) c).setType((SpinnerType) selector.getValue());
                }
            }
        });

        StringToEnumConverter converter = new StringToEnumConverter();
        for (SpinnerSize size : SpinnerSize.values()) {
            Spinner spinner = new Spinner(SpinnerType.ROTATING_PLANE);
            spinner.setSize(size);
            spinner.setCaption(converter.convertToPresentation(size, String.class, getLocale()));
            spinners.addComponent(spinner);
        }

        VerticalLayout l = new VerticalLayout();
        l.setDefaultComponentAlignment(Alignment.TOP_CENTER);
        l.setSizeFull();
        l.setMargin(false);
        l.setSpacing(true);
        l.addComponents(selector, spinners);
        l.setExpandRatio(spinners,1);
        return l;
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


}
