package org.vaadin.spinkit;

import org.vaadin.spinkit.client.SpinnerState;

public class Spinner2 extends com.vaadin.ui.AbstractComponent {

	private SpinnerType type;

	public Spinner2(SpinnerType type) {
		setType(type);
		setSizeUndefined();
	}

	public void setType(SpinnerType type) {
		if (this.type != type) {
			this.type = type;
			getState().spinnerStyle = type.getStyle();
			getState().spinnerHTML = type.getHtml();
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
