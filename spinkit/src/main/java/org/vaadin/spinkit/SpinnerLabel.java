package org.vaadin.spinkit;

import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Label;

import java.util.Objects;

/**
 * Add-on can be fully server-side (with or without UI components) or have client-side widgets.
 */
public class SpinnerLabel extends Label {

    private SpinnerType spinnerType;
    private String value;


    public SpinnerLabel(SpinnerType spinnerType) {
        super("", ContentMode.HTML);
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
        //addStyleName(this.spinnerType.getStyle());
        super.setValue(String.format("<span>%s<span><div class=\"%s\">%s</div>",
                Objects.toString(value, ""), this.getPrimaryStyleName(),
                this.spinnerType.getHtml()));
    }

}
