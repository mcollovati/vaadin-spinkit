/**
 * Copyright (C) 2016-2019 Marco Collovati (mcollovati@gmail.com)
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

import java.util.function.Consumer;
import java.util.stream.Stream;

import com.vaadin.flow.component.html.Div;

/**
 * Created by marco on 16/09/15.
 */
public enum SpinnerType {


    PLANE("sk-plane"),
    ROTATING_PLANE(PLANE),
    CHASE("sk-chase", el -> el.add(innerDivs("sk-chase-dot", 6))),
    CHASING_DOTS(CHASE),
    BOUNCE("sk-bounce", el -> el.add(innerDivs("sk-bounce-dot", 2))),
    DOUBLE_BOUNCE(BOUNCE),
    THREE_BOUNCE("sk-bounce", el -> el.add(innerDivs("sk-bounce-dot", 3))),
    WAVE("sk-wave", el -> el.add(innerDivs("sk-wave-rect", 5))),
    PULSE("sk-pulse"),
    FLOW("sk-flow", el -> el.add(innerDivs("sk-flow-dot", 3))),
    SWING("sk-swing", el -> el.add(innerDivs("sk-swing-dot", 2))),
    CIRCLE("sk-circle", el -> el.add(innerDivs("sk-circle-dot", 12))),
    CIRCLE_FADE("sk-circle-fade", el -> el.add(innerDivs("sk-circle-fade-dot", 12))),
    FADING_CIRCLE(CIRCLE_FADE),
    GRID("sk-grid", el -> el.add(innerDivs("sk-grid-cube", 9))),
    CUBE_GRID(GRID),
    FOLD("sk-fold", el -> el.add(innerDivs("sk-fold-cube", 4))),
    FOLDING_CUBE(FOLD),
    WANDER("sk-wander", el -> el.add(innerDivs("sk-wander-cube", 3))),
    WANDERING_CUBES(WANDER);

    private static Div[] innerDivs(String className, int count) {
        return Stream.generate(Div::new).peek(d -> d.setClassName(className)).limit(count).toArray(Div[]::new);
    }

    private final String cssClass;
    private final Consumer<Div> htmlBuilder;
    private final boolean alias;

    SpinnerType(String cssClass) {
        this(cssClass, div -> {});
    }

    SpinnerType(String cssClass, Consumer<Div> htmlBuilder) {
        this(cssClass, htmlBuilder, false);
    }

    SpinnerType(String cssClass, Consumer<Div> htmlBuilder, boolean alias) {
        this.cssClass = cssClass;
        this.htmlBuilder = htmlBuilder;
        this.alias = alias;
    }

    SpinnerType(SpinnerType aliased) {
        this(aliased.cssClass, aliased.htmlBuilder, true);
    }

    public boolean isAlias() {
        return alias;
    }

    public String getClassName() {
        return cssClass;
    }

    public void apply(Div element) {
        element.removeAll();
        element.addClassName(cssClass);
        htmlBuilder.accept(element);
    }
}
