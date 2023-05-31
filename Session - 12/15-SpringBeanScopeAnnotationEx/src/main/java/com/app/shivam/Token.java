package com.app.shivam;

import java.util.Random;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component("t1")
@Scope("prototype")
public class Token {
    private int code;

    public Token() {
        super();
        code = Math.abs(new Random().nextInt());
    }

    @Override
    public String toString() {
        return "Token [code=" + code + "]";
    }
}