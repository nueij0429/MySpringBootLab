package com.rookies3.myspringbootlab.config;

import lombok.Getter;

@Getter
public class MyEnvironment {
    private String mode;

    public MyEnvironment(String mode) {
        this.mode = mode;
    }

    public String getMode() {
        return mode;
    }
}