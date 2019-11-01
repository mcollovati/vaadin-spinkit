/**
 * Copyright (C) 2016-2019 Marco Collovati (mcollovati@gmail.com)
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
package org.vaadin.spinkit;

import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.dependency.NpmPackage;
import com.vaadin.flow.component.html.Div;

/**
 * A spinner component
 *
 * @author Marco Collovati
 */
@Tag("spinkit")
@NpmPackage(value = "spinkit", version = "^2.0.0")
@CssImport("spinkit/spinkit.min.css")
@CssImport("./spinkit/vaadin-spinkit.css")
public class Spinner extends Div {

    static final String SK_BASE_SIZE = "--sk-base-size";
    static final String SK_SIZE = "--sk-size";
    static final String SK_COLOR = "--sk-color";
    static final String DEFAULT_SK_SIZE_VALUE = "var(--sk-size)";
    private static final String SK_BASE_SIZE_VAR = String.format("var(%s)", SK_BASE_SIZE);

    /**
     * Creates a new spinner for the given type
     *
     * @param type The spinner type
     */
    public Spinner(SpinnerType type) {
        setType(type);
        setSize(SpinnerSize.DEFAULT);
        setSizeUndefined();
    }

    /**
     * Sets the type of the spinner
     *
     * @param type The spinner type
     */
    public void setType(SpinnerType type) {
        SpinnerType old = getType();
        if (type != old) {
            if (old != null) { removeClassName(old.getClassName()); }
            getElement().setProperty("spinner-type", type.name());
            type.apply(this);
        }
    }

    /**
     * Sets the spinner size
     *
     * @param size The spinner size
     */
    public void setSize(SpinnerSize size) {
        SpinnerSize old = getSize();
        if (size != old) {
            removeClassName(old.getSize());
            addClassName(size.getSize());
            getElement().setProperty("spinner-size", size.name());
        }
    }

    /**
     * Sets the spinner base size.
     * <p>
     * Corresponds to the --sk-size css variable.
     *
     * @param size The spinner base size
     */
    public void setBaseSize(String size) {
        if (size != null && !size.trim().isEmpty()) {
            getElement().getStyle().set(SK_BASE_SIZE, size);
        } else {
            getElement().getStyle().remove(SK_BASE_SIZE);
        }
    }

    public void setColor(String color) {
        if (color != null && !color.trim().isEmpty()) {
            getElement().getStyle().set(SK_COLOR, color);
        } else {
            getElement().getStyle().remove(SK_COLOR);
        }
    }

    public String getColor() {
        return getElement().getStyle().get(SK_COLOR);
    }

    /**
     * Returns the spinner size
     *
     * @return the spinner size
     */
    public SpinnerSize getSize() {
        return SpinnerSize.valueOf(getElement().getProperty("spinner-size", SpinnerSize.DEFAULT.name()));
    }

    /**
     * Returns the spinner base size.
     * <p>
     * Corresponds to the --sk-size css variable.
     *
     * @return the spinner base size
     */
    public String getBaseSize() {
        return getElement().getStyle().get(SK_BASE_SIZE);
    }

    /**
     * Returns the spinner type
     *
     * @return the spinner type
     */
    public SpinnerType getType() {
        String type = getElement().getProperty("spinner-type");
        return type != null ? SpinnerType.valueOf(type) : null;
    }

    /**
     * Defines if the spinner should be centered.
     *
     * @param center true if the spinner should be centered
     */
    public void setCentered(boolean center) {
        if (center) {
            addClassName("sk-center");
        } else {
            removeClassName("sk-center");
        }
    }

    /**
     * Indicated if the spinner is centered.
     *
     * @return true if the spinner is centered, otherwise false
     */
    public boolean isCentered() {
        return hasClassName("sk-center");
    }

    // Fluent

    /**
     * Sets the spinner type
     *
     * @param type the spinner type
     * @return the spinner instances
     */
    public Spinner withType(SpinnerType type) {
        setType(type);
        return this;
    }

    /**
     * Sets the spinner color
     *
     * @param color the spinner color
     * @return the spinner instance
     */
    public Spinner withColor(String color) {
        setColor(color);
        return this;
    }

    /**
     * Sets the spinner base size
     *
     * @param size the spinner base size
     * @return the spinner instances
     */
    public Spinner withBaseSize(String size) {
        setBaseSize(size);
        return this;
    }

    /**
     * Sets the spinner size
     *
     * @param size The spinner size
     */
    public Spinner withSize(SpinnerSize size) {
        setSize(size);
        return this;
    }
    /**
     * Sets the spinner size to extra small
     *
     * @return the spinner instances
     */
    public Spinner extraSmall() {
        setSize(SpinnerSize.XS);
        return this;
    }

    /**
     * Sets the spinner size to small
     *
     * @return the spinner instances
     */
    public Spinner small() {
        setSize(SpinnerSize.SM);
        return this;
    }

    /**
     * Sets the spinner size to medium
     *
     * @return the spinner instances
     */
    public Spinner medium() {
        setSize(SpinnerSize.MD);
        return this;
    }

    /**
     * Sets the spinner size to default
     *
     * @return the spinner instances
     */
    public Spinner defaultSize() {
        setSize(SpinnerSize.DEFAULT);
        return this;
    }

    /**
     * Sets the spinner size to large
     *
     * @return the spinner instances
     */
    public Spinner large() {
        setSize(SpinnerSize.LG);
        return this;
    }

    /**
     * Sets the spinner size to extra large
     *
     * @return the spinner instances
     */
    public Spinner extraLarge() {
        setSize(SpinnerSize.XL);
        return this;
    }

    /**
     * Center the spinner.
     *
     * @return the spinner instance
     */
    public Spinner centered() {
        setCentered(true);
        return this;
    }
}
