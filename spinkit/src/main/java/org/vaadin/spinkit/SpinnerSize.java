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

public enum SpinnerSize {
    /**
     * Extra Small size
     */
    XS("sk-xs"),
    /**
     * Small
     */
    SM("sk-sm"),
    /**
     * Medium
     */
    MD("sk-md"),
    DEFAULT(""),
    /**
     * Large
     */
    LG("sk-lg"),
    /**
     * Extra large
     */
    XL("sk-xl");

    private final String size;

    SpinnerSize(String size) {
        this.size = size;
    }

    public String getSize() {
        return size;
    }

}
