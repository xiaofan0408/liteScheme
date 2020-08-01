package com.xiaofan0408.eval;

import com.xiaofan0408.parser.Node;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author xuzefan  2020/7/31 16:19
 */
public class Eval {
    public Object eval(Node node,Env env) throws Exception {
        if (node.getValue() instanceof String) {
            Env e = env.find((String)node.getValue());
            if (e == null) {
                throw new Exception("no variable");
            }
            Object res = e.get((String)node.getValue());
            if (res == null) {
                throw new Exception("no variable");
            }
            return res;
        } else if (node.getChildren() == null) {
            return node.getValue();
        } else if ("quote".equals(node.getChildren().get(0).getValue())) {
            Node dataNode = node.getChildren().get(1);
            List<Object> objectList = new ArrayList<>();
            if (dataNode.getValue() != null) {
                objectList.add(dataNode.getValue());
            }
            if (dataNode.getChildren() != null) {
                objectList.addAll(dataNode.getChildren().stream().map(node1 -> node1.getValue()).collect(Collectors.toList()));
            }
            return objectList.toArray();
        }  else if ("if".equals(node.getChildren().get(0).getValue())) {
            Node testNode = node.getChildren().get(1);
            Node conseq = node.getChildren().get(2);
            Node alt = node.getChildren().get(3);
            Object bool = eval(testNode,env);
            Node exp;
            if (truth(bool)) {
                exp = conseq;
            } else {
                exp = alt;
            }
            return eval(exp,env);
        } else if ("define".equals(node.getChildren().get(0).getValue())) {
            Node varNode = node.getChildren().get(1);
            Node exp = node.getChildren().get(2);
            env.put(varNode.getValue().toString(), eval(exp,env));
            return null;
        } else if ("set!".equals(node.getChildren().get(0).getValue())) {
            Node varNode = node.getChildren().get(1);
            Node exp = node.getChildren().get(2);
            Env e = env.find(varNode.getValue().toString());
            if (e == null) {
                throw new Exception("variable no define");
            }
            e.put(varNode.getValue().toString(), eval(exp,env));
            return null;
        } else if ("lambda".equals(node.getChildren().get(0).getValue())){
            Node paramNode = node.getChildren().get(1);
            Node bodyNode = node.getChildren().get(2);
            List<String> objectList = new ArrayList<>();
            objectList.addAll(paramNode.getChildren().stream().map(node1 -> node1.getValue().toString()).collect(Collectors.toList()));
            String[] strings = new String[objectList.size()];
            objectList.toArray(strings);
            return new Procedure(strings,bodyNode,this,env);
        } else {
            Object fn = eval(node.getChildren().get(0), env);
            List<Object> args = new ArrayList<>();
            for (int i = 1;i < node.getChildren().size();i++) {
                args.add(eval(node.getChildren().get(i),env));
            }
            if (fn instanceof Fn) {
                return ((Fn)fn).apply(args.toArray());
            }
            return fn;
        }
    }


    private Boolean truth(boolean x) {
        return x ? Boolean.TRUE : Boolean.FALSE;
    }

    private Boolean truth(Boolean x) {
        return x ? Boolean.TRUE : Boolean.FALSE;
    }

    private boolean truth(Object x) {
        if(x instanceof Object[]){
            return ((Object[]) x).length > 0;
        }
        if (x == null) {
            return  Boolean.FALSE;
        }
        if (x instanceof Boolean) {
            return (Boolean) x ? Boolean.TRUE : Boolean.FALSE;
        }
        return x != Boolean.FALSE;
    }
}
