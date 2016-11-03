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
package org.vaadin.spinkit.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.Widget;
import com.vaadin.client.communication.StateChangeEvent;
import com.vaadin.client.ui.AbstractComponentConnector;
import com.vaadin.shared.ui.Connect;
import org.vaadin.spinkit.Spinner;
import org.vaadin.spinkit.shared.SpinnerSize;
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

        if (state.type != null) {
            widget.setStyleName(widget.getStylePrimaryName());
            widget.getElement().setInnerHTML(state.type.getHtml());
            widget.addStyleName(state.type.getStyle());
            if (state.size != null && state.size != SpinnerSize.DEFAULT) {
                widget.addStyleName(widget.getStylePrimaryName() + "-" + state.size.getSize());
            }
            widget.setVisible(true);
        } else {
            widget.setVisible(false);
        }

    }

}
