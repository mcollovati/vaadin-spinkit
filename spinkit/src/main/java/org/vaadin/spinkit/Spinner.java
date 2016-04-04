package org.vaadin.spinkit;

import org.vaadin.spinkit.shared.SpinnerSize;
import org.vaadin.spinkit.shared.SpinnerState;
import org.vaadin.spinkit.shared.SpinnerType;

public class Spinner extends com.vaadin.ui.AbstractComponent {


    public Spinner(SpinnerType type) {
        setType(type);
        setSize(SpinnerSize.DEFAULT);
        setSizeUndefined();
    }

    public void setType(SpinnerType type) {
        if (getState(false).type != type) {
            getState().type = type;
        }
    }

    public void setSize(SpinnerSize size) {
        if (getState(false).size != size) {
            getState().size = size;
        }
    }

    public SpinnerSize getSize() {
        return getState(false).size;
    }

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
    public Spinner withType(SpinnerType type) {
        setType(type);
        return this;
    }
    public Spinner extraSmall() {
        setSize(SpinnerSize.XS);
        return this;
    }
    public Spinner small() {
        setSize(SpinnerSize.SM);
        return this;
    }
    public Spinner medium() {
        setSize(SpinnerSize.MD);
        return this;
    }
    public Spinner defaultSize() {
        setSize(SpinnerSize.DEFAULT);
        return this;
    }
    public Spinner large() {
        setSize(SpinnerSize.LG);
        return this;
    }
    public Spinner extraLarge() {
        setSize(SpinnerSize.XL);
        return this;
    }
}
