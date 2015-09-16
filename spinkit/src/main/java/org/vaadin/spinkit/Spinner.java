package org.vaadin.spinkit;

import com.vaadin.annotations.StyleSheet;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Label;

/**
 * Add-on can be fully server-side (with or without UI components) or have client-side widgets.
 */
@StyleSheet("spinkit.css")
public class Spinner extends Label
{

    private SpinnerType spinnerType;

    public Spinner(SpinnerType spinnerType) {
        super("", ContentMode.HTML);
        setSizeUndefined();
        this.spinnerType = spinnerType;
        applyChanges();
    }

    public SpinnerType getSpinnerType() {
        return spinnerType;
    }

    public void setSpinnerType(SpinnerType spinnerType) {
        if (spinnerType != this.spinnerType) {
            this.spinnerType = spinnerType;
            applyChanges();
        }
    }

    private void applyChanges() {
        super.setPrimaryStyleName(this.spinnerType.getStyle());
        super.setValue(this.spinnerType.getHtml());
    }



}
