package org.vaadin.spinkit;

/**
 * Created by marco on 16/09/15.
 */
public enum SpinnerType {

    ROTATING_PLANE("sk-rotating-plane"),
    DOUBLE_BOUNCE("sk-double-bounce", "<div class=\"sk-child sk-double-bounce1\"></div><div class=\"sk-child sk-double-bounce2\"></div>"),
    WAVE("sk-wave", "<div class=\"sk-rect sk-rect1\"></div><div class=\"sk-rect sk-rect2\"></div>" +
                    "<div class=\"sk-rect sk-rect3\"></div><div class=\"sk-rect sk-rect4\"></div><div class=\"sk-rect sk-rect5\"></div>"),
    WANDERING_CUBES("sk-wandering-cubes", "<div class=\"sk-cube sk-cube1\"></div><div class=\"sk-cube sk-cube2\"></div>"),
    PULSE("sk-spinner sk-spinner-pulse"),
    CHASING_DOTS("sk-chasing-dots","<div class=\"sk-child sk-dot1\"></div><div class=\"sk-child sk-dot2\"></div>"),
    THREE_BOUNCE("sk-three-bounce","<div class=\"sk-child sk-bounce1\"></div><div class=\"sk-child sk-bounce2\"></div>"+
                    "<div class=\"sk-child sk-bounce3\"></div>"),
    CIRCLE("sk-circle","<div class=\"sk-circle1 sk-child\"></div><div class=\"sk-circle2 sk-child\"></div>" +
                        "<div class=\"sk-circle3 sk-child\"></div><div class=\"sk-circle4 sk-child\"></div>" +
                        "<div class=\"sk-circle5 sk-child\"></div><div class=\"sk-circle6 sk-child\"></div>" +
                        "<div class=\"sk-circle7 sk-child\"></div><div class=\"sk-circle8 sk-child\"></div>" +
                        "<div class=\"sk-circle9 sk-child\"></div><div class=\"sk-circle10 sk-child\"></div>" +
                        "<div class=\"sk-circle11 sk-child\"></div><div class=\"sk-circle12 sk-child\"></div>"),
    CUBE_GRID("sk-cube-grid","<div class=\"sk-cube sk-cube1\"></div><div class=\"sk-cube sk-cube2\"></div>" +
                        "<div class=\"sk-cube sk-cube3\"></div><div class=\"sk-cube sk-cube4\"></div>" +
                        "<div class=\"sk-cube sk-cube5\"></div><div class=\"sk-cube sk-cube6\"></div>" +
                        "<div class=\"sk-cube sk-cube7\"></div><div class=\"sk-cube sk-cube8\"></div>" +
                        "<div class=\"sk-cube sk-cube9\"></div>"),
    FADING_CIRCLE("sk-fading-circle", "<div class=\"sk-circle1 sk-circle\"></div><div class=\"sk-circle2 sk-circle\"></div>"+
                        "<div class=\"sk-circle3 sk-circle\"></div><div class=\"sk-circle4 sk-circle\"></div>" +
                        "<div class=\"sk-circle5 sk-circle\"></div><div class=\"sk-circle6 sk-circle\"></div>" +
                        "<div class=\"sk-circle7 sk-circle\"></div><div class=\"sk-circle8 sk-circle\"></div>" +
                        "<div class=\"sk-circle9 sk-circle\"></div><div class=\"sk-circle10 sk-circle\"></div>" +
                        "<div class=\"sk-circle11 sk-circle\"></div><div class=\"sk-circle12 sk-circle\"></div>"),
    FOLDING_CUBE("sk-folding-cube","<div class=\"sk-cube1 sk-cube\"></div><div class=\"sk-cube2 sk-cube\"></div>" +
                        "<div class=\"sk-cube4 sk-cube\"></div><div class=\"sk-cube3 sk-cube\"></div>");

    private final String cssClass;
    private final String html;

    SpinnerType(String cssClass) {
        this(cssClass,"");
    }

    SpinnerType(String cssClass, String html) {
        this.cssClass = cssClass;
        this.html = html;
    }

    public String getStyle() {
        return cssClass;
    }

    public String getHtml() {
        return html;
    }
}
