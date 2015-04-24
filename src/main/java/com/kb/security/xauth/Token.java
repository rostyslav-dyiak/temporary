package com.kb.security.xauth;

/**
 * Created by rdyyak on 24.04.15.
 */
public class Token {

    String token;
    long expires;

    public Token(String token, long expires){
        this.token = token;
        this.expires = expires;
    }

    public String getToken() {
        return token;
    }

    public long getExpires() {
        return expires;
    }
}
