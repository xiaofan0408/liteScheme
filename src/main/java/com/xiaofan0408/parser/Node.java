package com.xiaofan0408.parser;

import com.xiaofan0408.lex.Token;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xuzefan  2020/7/31 15:02
 */

public class Node {
    Token token;
    Object value;
    List<Node> children;

    public Node(){
        this.children = new ArrayList<>();
    }

    public Node(Token token, Object value, List<Node> children) {
        this.token = token;
        this.value = value;
        this.children = children;
    }

    public Token getToken() {
        return token;
    }

    public void setToken(Token token) {
        this.token = token;
    }

    public List<Node> getChildren() {
        return children;
    }

    public void setChildren(List<Node> children) {
        this.children = children;
    }

    public Object getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                ", children=" + children +
                '}';
    }
}
