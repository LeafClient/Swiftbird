package com.leafclient.swiftbird;

import org.junit.Test;

public class Settings {

    private static Swiftbird swiftbird = Swiftbird.create();
    private static ConfigurableObject configurableObject = new ConfigurableObject();

    @Test
    public void assertLookUp() {
        swiftbird.in(configurableObject)
                .forEach(setting -> System.out.println(setting.getLabel() + " " + setting.getValue()));
    }

}
