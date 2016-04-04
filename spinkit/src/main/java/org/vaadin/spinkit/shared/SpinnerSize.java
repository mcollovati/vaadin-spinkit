package org.vaadin.spinkit.shared;

/**
 * Created by marco on 04/04/16.
 */
public enum SpinnerSize {
    /**
     * Extra Small size
     */
    XS("xs"),
    /**
     * Small
     */
    SM("sm"),
    /**
     * Medium
     */
    MD("md"),
    DEFAULT(""),
    /**
     * Large
     */
    LG("lg"),
    /**
     * Extra large
     */
    XL("xl");

    private final String size;

    SpinnerSize(String size) {
        this.size = size;
    }

    public String getSize() {
        return size;
    }
}
