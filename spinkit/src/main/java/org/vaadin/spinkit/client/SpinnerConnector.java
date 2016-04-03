package org.vaadin.spinkit.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.Widget;
import com.vaadin.client.communication.StateChangeEvent;
import com.vaadin.client.ui.AbstractComponentConnector;
import com.vaadin.shared.ui.Connect;
import org.vaadin.spinkit.Spinner;
import org.vaadin.spinkit.shared.SpinnerState;

import java.util.Objects;

@Connect(Spinner.class)
public class SpinnerConnector extends AbstractComponentConnector {

    @Override
    protected Widget createWidget() {
        return GWT.create(SpinnerWidget.class);
    }


    @Override
    public SpinnerWidget getWidget() {
        return (SpinnerWidget) super.getWidget();
    }

    @Override
    public SpinnerState getState() {
        return (SpinnerState) super.getState();
    }

    @Override
    public void onStateChanged(StateChangeEvent stateChangeEvent) {

        super.onStateChanged(stateChangeEvent);

        applyState(getWidget(), getState());
    }

    static void applyState(SpinnerWidget widget, SpinnerState state) {
        Objects.requireNonNull(widget);
        Objects.requireNonNull(state);

        if (state.spinnerType != null) {
            final String spinnerHTML = state.spinnerType.getHtml();
            final String spinnerStyle = state.spinnerType.getStyle();
            widget.setStyleName(widget.getStylePrimaryName());
            widget.getElement().setInnerHTML(spinnerHTML);
            widget.addStyleName(spinnerStyle);
            widget.setVisible(true);
        } else {
            widget.setVisible(false);
        }

    }

}
