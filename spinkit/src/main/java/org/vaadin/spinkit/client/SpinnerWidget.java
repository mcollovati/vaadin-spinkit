package org.vaadin.spinkit.client;

import com.google.gwt.user.client.ui.HTML;

// Extend any GWT Widget
public class SpinnerWidget extends HTML {

	public SpinnerWidget() {

		// CSS class-name should not be v- prefixed
		setStyleName("spinkit");

		// State is set to widget in MyComponentConnector		
	}

}