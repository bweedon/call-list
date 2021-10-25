package com.singlestone.calllist.db.model;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum PhoneType {
    MOBILE("mobile"),
    WORK("work"),
    HOME("home");

    private String typeText;
    PhoneType(String typeText) {
        this.typeText = typeText;
    }

    @JsonCreator
    public static PhoneType fromText(String text) {
        for(PhoneType pt : PhoneType.values()) {
            if(pt.typeText.equalsIgnoreCase(text)) {
                return pt;
            }
        }
        throw new IllegalArgumentException(String.format("No Phone Type matching the text: %s", text));
    }

    @Override
    public String toString() {
        return typeText;
    }
}
