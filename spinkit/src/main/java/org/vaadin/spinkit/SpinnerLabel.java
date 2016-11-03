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
package org.vaadin.spinkit;


import com.vaadin.shared.ui.ContentMode;
import com.vaadin.ui.Label;
import org.vaadin.spinkit.shared.SpinnerType;

import java.util.Objects;

/**
 * Add-on can be fully server-side (with or without UI components) or have client-side widgets.
 */
public class SpinnerLabel extends Label {

    private SpinnerType spinnerType;
    private String value;


    public SpinnerLabel(SpinnerType spinnerType) {
        super("", ContentMode.HTML);
        setPrimaryStyleName("spinkit");
        this.spinnerType = Objects.requireNonNull(spinnerType);
        setSizeUndefined();
        applyChanges();
    }

    public SpinnerType getSpinnerType() {
        return spinnerType;
    }

    public void setSpinnerType(SpinnerType spinnerType) {
        Objects.requireNonNull(spinnerType);
        if (spinnerType != this.spinnerType) {
            unapplySpinner(this.spinnerType);
            this.spinnerType = spinnerType;
            applyChanges();
        }
    }

    private void unapplySpinner(SpinnerType spinnerType) {
        removeStyleName(spinnerType.getStyle());

    }

    @Override
    public String getValue() {
        return value;
    }

    @Override
    public void setValue(String value) {
        this.value = value;
        if (this.spinnerType != null) {
            applyChanges();
        } else {
            super.setValue(value);
        }

    }

    @Override
    public void setContentMode(ContentMode contentMode) {
        if (contentMode != ContentMode.HTML) {
            throw new UnsupportedOperationException("Content mode must be HTML");
        }
        super.setContentMode(contentMode);
    }

    private void applyChanges() {
        super.setValue(String.format("<span>%s<span><div class=\"%s %s\">%s</div>",
                Objects.toString(value, ""), this.getPrimaryStyleName(),
            this.spinnerType.getStyle(), this.spinnerType.getHtml()));
    }

}
