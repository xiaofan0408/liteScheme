package com.xiaofan0408.lex;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author xuzefan  2020/7/31 14:13
 */
public class Token {

    private TokenType type;

    private String literal;

    public Token(String literal) {
        this.literal = literal;
    }

    public Token(TokenType type, String literal) {
        this.type = type;
        this.literal = literal;
    }

    public TokenType getType() {
        return type;
    }

    public void setType(TokenType type) {
        this.type = type;
    }

    public String getLiteral() {
        return literal;
    }

    public void setLiteral(String literal) {
        this.literal = literal;
    }

    public static void printTokens(List<Token> tokens){
        List<String> strings = tokens.stream().map(token -> token.literal).collect(Collectors.toList());
        System.out.printf(String.join(",",strings));
    }
}
