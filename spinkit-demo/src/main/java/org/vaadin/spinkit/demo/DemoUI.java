/**
 * Copyright (C) 2016-2017 Marco Collovati (mcollovati@gmail.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.vaadin.spinkit.demo;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Component;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;
import com.vaadin.v7.data.util.converter.StringToEnumConverter;
import com.vaadin.v7.ui.ComboBox;
import org.vaadin.spinkit.Spinner;
import org.vaadin.spinkit.SpinnerLabel;
import org.vaadin.spinkit.shared.SpinnerSize;
import org.vaadin.spinkit.shared.SpinnerType;
import org.vaadin.viritin.label.RichText;
import org.vaadin.viritin.layouts.MVerticalLayout;

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
        tabSheet.addTab(new RichText().withMarkDown(getClass().getResourceAsStream("source.md"))).setCaption("Source code");
        //layout.addComponent(tabSheet);
        //layout.expand(tabSheet);
        RichText info = new RichText()
            .withMarkDown(getClass().getResourceAsStream("about.md"));


        MVerticalLayout layout = new MVerticalLayout()
            .withMargin(true)
            .with(info).expand(tabSheet)
            .withFullHeight().withFullWidth();

        //layout.setExpandRatio(info, 1);
        //layout.setExpandRatio(tabSheet, 4);

        setContent(layout);
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
        l.setExpandRatio(spinners, 1);
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
