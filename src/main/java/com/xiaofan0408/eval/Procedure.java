package com.xiaofan0408.eval;

import com.xiaofan0408.parser.Node;

/**
 * @author xuzefan  2020/7/31 18:04
 */
public class Procedure implements Fn{
    String[] params;
    Node node;
    Eval eval;
    Env env;

    public Procedure(String[] params, Node node, Eval eval, Env env) {
        this.params = params;
        this.node = node;
        this.eval = eval;
        this.env = env;
    }

    @Override
    public Object apply(Object... args) throws Exception {
        return eval.eval(node,new Env(env,params,args));
    }
}
