/*
 * Copyright (C) 2016-2022 Marco Collovati (mcollovati@gmail.com)
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

import java.util.EnumSet;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.github.appreciated.css.grid.sizes.Length;
import com.github.appreciated.css.grid.sizes.Repeat;
import com.github.appreciated.layout.FlexibleGridLayout;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.vaadin.firitin.components.RichText;
import org.vaadin.firitin.components.orderedlayout.VHorizontalLayout;
import org.vaadin.firitin.components.orderedlayout.VVerticalLayout;
import org.vaadin.firitin.layouts.VTabSheet;
import org.vaadin.spinkit.Spinner;
import org.vaadin.spinkit.SpinnerSize;
import org.vaadin.spinkit.SpinnerType;


@PageTitle("Vaadin Spinkit Add-on Demo")
@Route("")
@CssImport("./styles/styles.css")
public class DemoUI extends Div {

    public DemoUI() {
        VTabSheet tabSheet = new VTabSheet();
        tabSheet.setFlexGrowForEnclosedTabs(1);
        tabSheet.addTab("Spinners", spinnersContainer());
        tabSheet.addTab("Sizes", spinnerSizesContainer());
        tabSheet.addTab("Source code", new RichText().withMarkDown(getClass().getResourceAsStream("source.md")));
        tabSheet.addTab("Dialog", new Button("Open dialog", ev -> openDialog()));

        RichText info = new RichText()
            .withMarkDown(getClass().getResourceAsStream("about.md"));


        VVerticalLayout layout = new VVerticalLayout()
            .withMargin(true)
            .withComponent(info).addExpanded(tabSheet)
            .withFullHeight().withFullWidth();

        add(layout);
    }

    private void openDialog() {
        Dialog dialog = new Dialog(
            new VVerticalLayout(createSpinner(SpinnerType.CIRCLE)
                .extraLarge().withDisplayBlock())
                .withJustifyContentMode(FlexComponent.JustifyContentMode.CENTER)
                .withSize("300px", "300px"));
        dialog.setCloseOnEsc(true);
        dialog.setCloseOnOutsideClick(true);
        dialog.open();
    }

    private Component spinnersContainer() {
        List<Spinner> spinners = Stream.of(SpinnerType.values()).filter(t -> !t.isAlias())
            .map(DemoUI::createSpinner)
            .collect(Collectors.toList());

        FlexibleGridLayout spinnersContainer = new FlexibleGridLayout()
            .withColumns(Repeat.RepeatMode.AUTO_FILL, new Length("25%"))
            .withPadding(true)
            .withSpacing(true)
            .withItems(spinners.stream().map(s -> spinnerWithName(s, Spinner::getType)).toArray(Component[]::new));

        TextField color = new TextField("Color (--sk-color)", "#333");
        color.addValueChangeListener(e -> spinners.forEach(s -> s.setColor(e.getValue())));

        ComboBox<String> theme = new ComboBox<>("Css class", "", "green", "red");
        theme.setPreventInvalidInput(true);
        theme.addValueChangeListener(e -> spinners.forEach(s -> {
            Optional.ofNullable(e.getOldValue()).ifPresent(css -> s.removeClassName("sk-demo-" + css));
            s.addClassName("sk-demo-" + e.getValue());
        }));
        VerticalLayout commands = new VerticalLayout();
        commands.setAlignItems(FlexComponent.Alignment.START);
        commands.setMargin(false);
        commands.setSpacing(true);
        commands.add(color, theme);
        commands.setSizeUndefined();

        VHorizontalLayout layout = new VHorizontalLayout(commands, spinnersContainer);
        layout.setSizeFull();
        layout.setMargin(true);
        layout.setSpacing(true);
        layout.setFlexGrow(1, spinnersContainer);
        return layout;
    }

    private Component spinnerSizesContainer() {

        List<Spinner> spinners = EnumSet.complementOf(EnumSet.of(SpinnerSize.DEFAULT)).stream()
            .map(size -> {
                Spinner s = createSpinner(SpinnerType.PLANE);
                s.setSize(size);
                s.setTitle(size.name());
                return s;
            }).collect(Collectors.toList());

        List<SpinnerType> spinnerTypes = Stream.of(SpinnerType.values())
            .filter(t -> !t.isAlias()).collect(Collectors.toList());

        ComboBox<SpinnerType> selector = new ComboBox<>("Select spinner type", spinnerTypes);
        selector.setPreventInvalidInput(true);
        selector.setValue(SpinnerType.ROTATING_PLANE);
        selector.addValueChangeListener(e -> spinners.forEach(s -> s.setType(selector.getValue())));

        TextField baseSize = new TextField("Base size (--sk-size)", "40px");
        baseSize.addValueChangeListener(e -> spinners.forEach(s -> s.setBaseSize(e.getValue())));

        FlexibleGridLayout spinnersContainer = new FlexibleGridLayout()
            .withColumns(Repeat.RepeatMode.AUTO_FILL, new Length("25%"))
            .withPadding(true)
            .withSpacing(true)
            .withItems(spinners.stream().map(s -> spinnerWithName(s, Spinner::getSize)).toArray(Component[]::new));

        for (SpinnerSize size : EnumSet.complementOf(EnumSet.of(SpinnerSize.DEFAULT))) {
            Spinner spinner = new Spinner(SpinnerType.PLANE);
            spinner.setSize(size);
            spinner.setTitle(size.name());
            spinners.add(spinner);
        }

        VerticalLayout commands = new VerticalLayout();
        commands.setSizeUndefined();
        commands.setAlignItems(FlexComponent.Alignment.START);
        commands.setMargin(false);
        commands.setSpacing(true);
        commands.add(selector, baseSize);

        VHorizontalLayout layout = new VHorizontalLayout(commands, spinnersContainer);
        layout.setSizeFull();
        layout.setMargin(true);
        layout.setSpacing(true);
        layout.setFlexGrow(1, spinnersContainer);
        return layout;
    }


    private static Spinner createSpinner(SpinnerType t) {
        Spinner spinner = new Spinner(t);
        spinner.setTitle(t.name());
        spinner.setCentered(true);
        return spinner;
    }

    private static VVerticalLayout spinnerWithName(Spinner s, Function<Spinner, Enum<?>> textFn) {
        return new VVerticalLayout().withComponent(s)
            .withComponent(new Span(textFn.apply(s).toString()), FlexComponent.Alignment.CENTER);
    }
}
