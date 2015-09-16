package org.vaadin.spinkit.client;

import org.vaadin.spinkit.Spinner2;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.Widget;

import com.vaadin.client.communication.StateChangeEvent;
import com.vaadin.client.ui.AbstractComponentConnector;
import com.vaadin.shared.ui.Connect;

// Connector binds client-side widget class to server-side component class
// Connector lives in the client and the @Connect annotation specifies the
// corresponding server-side component
@Connect(Spinner2.class)
public class SpinnerConnector extends AbstractComponentConnector {


	public SpinnerConnector() {}

	// We must implement createWidget() to create correct type of widget
	@Override
	protected Widget createWidget() {
		return GWT.create(SpinnerWidget.class);
	}

	
	// We must implement getWidget() to cast to correct type
	@Override
	public SpinnerWidget getWidget() {
		return (SpinnerWidget) super.getWidget();
	}

	// We must implement getState() to cast to correct type
	@Override
	public SpinnerState getState() {
		return (SpinnerState) super.getState();
	}

	// Whenever the state changes in the server-side, this method is called
	@Override
	public void onStateChanged(StateChangeEvent stateChangeEvent) {
		super.onStateChanged(stateChangeEvent);

		// State is directly readable in the client after it is set in server
		final String spinnerHTML = getState().spinnerHTML;
		final String spinnerStyle = getState().spinnerStyle;
		getWidget().setHTML(spinnerHTML);
		getWidget().setStyleName(spinnerStyle);

	}

}
