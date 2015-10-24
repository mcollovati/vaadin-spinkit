package org.vaadin.spinkit;

import org.vaadin.spinkit.client.SpinnerState;

public class Spinner extends com.vaadin.ui.AbstractComponent {

    private SpinnerType type;

    public Spinner(SpinnerType type) {
        setType(type);
        setSizeUndefined();
    }

    public void setType(SpinnerType type) {
        if (this.type != type) {
            this.type = type;
            if (type != null) {
                getState().spinnerStyle = type.getStyle();
                getState().spinnerHTML = type.getHtml();
            } else {
                getState().spinnerStyle = null;
                getState().spinnerHTML = null;
            }
        }
    }

    public SpinnerType getType() {
        return type;
    }

    @Override
    public SpinnerState getState() {
        return (SpinnerState) super.getState();
    }


}
