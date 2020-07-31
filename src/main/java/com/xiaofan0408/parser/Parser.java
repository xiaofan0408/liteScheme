package com.xiaofan0408.parser;

import com.xiaofan0408.lex.Token;

import java.util.LinkedList;
import java.util.List;

/**
 * @author xuzefan  2020/7/31 14:59
 */
public class Parser {

    public Node parse(List<Token> tokens) throws Exception {
        LinkedList<Token> linkedList = new LinkedList<>(tokens);
        return readFromTokens(linkedList);
    }

    private Node readFromTokens(LinkedList<Token> tokens) throws Exception {
        if (tokens.size() == 0) {
            throw new Exception("unexpected EOF while reading");
        }
        Token token = tokens.pop();
        if ("(".equals(token.getLiteral())) {
            Node node = new Node();
            while (!tokens.get(0).getLiteral().equals(")")){
                node.getChildren().add(readFromTokens(tokens));
            }
            tokens.pop();
            return node;
        } else if (")".equals(token.getLiteral())) {
            throw new Exception("unexpected )");
        } else {
            return atom(token);
        }
    }

    private Node atom(Token token) {
        try {
            Long l = Long.parseLong(token.getLiteral());
            return new Node(token,l,null);
        }catch (Exception e){
            try {
                Double l = Double.parseDouble(token.getLiteral());
                return new Node(token,l,null);
            }catch (Exception e1){
                return new Node(token,token.getLiteral(),null);
            }
        }
    }

}
