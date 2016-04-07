/**
 * Copyright (C) 2016 Marco Collovati (mcollovati@gmail.com)
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

import org.vaadin.spinkit.shared.SpinnerSize;
import org.vaadin.spinkit.shared.SpinnerState;
import org.vaadin.spinkit.shared.SpinnerType;

/**
 * A spinner component
 *
 * @author Marco Collovati
 */
public class Spinner extends com.vaadin.ui.AbstractComponent {

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
        if (getState(false).type != type) {
            getState().type = type;
        }
    }

    /**
     * Sets the spinner size
     *
     * @param size The spinner size
     */
    public void setSize(SpinnerSize size) {
        if (getState(false).size != size) {
            getState().size = size;
        }
    }

    /**
     * Returns the spinner size
     *
     * @return the spinner size
     */
    public SpinnerSize getSize() {
        return getState(false).size;
    }

    /**
     * Returns the spinner type
     *
     * @return the spinner type
     */
    public SpinnerType getType() {
        return getState(false).type;
    }

    @Override
    public SpinnerState getState() {
        return (SpinnerState) super.getState();
    }

    @Override
    protected SpinnerState getState(boolean markAsDirty) {
        return (SpinnerState) super.getState(markAsDirty);
    }

    // Fluent

    /**
     * Sets given styles
     *
     * @param style styles to apply to the spinner
     * @return the spinner instance
     */
    public Spinner withStyleName(String... style) {
        for (String s : style) {
            this.setStyleName(s);
        }
        return this;
    }

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
}
