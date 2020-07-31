package com.xiaofan0408.lex;


import java.util.ArrayList;
import java.util.List;

/**
 * @author xuzefan  2020/7/30 11:58
 */
public class Lexer {

    public List<Token> lex(String chars){
        List<Token> tokens = new ArrayList<>();
        String newCode = chars.replace("(", " ( ").replace(")", " ) ").trim();
        String[] list = newCode.split("\\s+");
        for (String word: list) {
            tokens.add(new Token(word));
        }
        return tokens;
    }
}
