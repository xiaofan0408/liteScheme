package com.xiaofan0408.eval;


import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author xuzefan  2020/7/31 15:43
 */
public class Env {
    //    def standard_env():
//            "An environment with some Scheme standard procedures."
//            import math, operator as op
//            env = Env()
//    env.update(vars(math)) # sin, cos, sqrt, pi, ...
//            env.update({
//        '+':op.add, '-':op.sub, '*':op.mul, '/':op.div,
//                '>':op.gt, '<':op.lt, '>=':op.ge, '<=':op.le, '=':op.eq,
//                'abs':     abs,
//                'append':  op.add,
//                'apply':   apply,
//                'begin':   lambda *x: x[-1],
//                'car':     lambda x: x[0],
//                'cdr':     lambda x: x[1:],
//        'cons':    lambda x,y: [x] + y,
//                'eq?':     op.is_,
//                'equal?':  op.eq,
//                'length':  len,
//                'list':    lambda *x: list(x),
//                'list?':   lambda x: isinstance(x,list),
//                'map':     map,
//                'max':     max,
//                'min':     min,
//                'not':     op.not_,
//                'null?':   lambda x: x == [],
//        'number?': lambda x: isinstance(x, Number),
//                'procedure?': callable,
//                'round':   round,
//                'symbol?': lambda x: isinstance(x, Symbol),
//    })


    private Map<String,Object> env = new ConcurrentHashMap<>();

    private Env outer;

    private String[] params;

    private Object[] args;


    public Env(Env outer, String[] params, Object[] args) {
        this.outer = outer;
        this.params = params;
        this.args = args;
    }

    public Env(Env outer) {
        this.outer = outer;
    }

    public Object get(String key){
        return env.get(key);
    }

    public void put(String value,Object object){
        env.put(value,object);
    }

    public Env find(String key) {
        if (env.get(key)!=null) {
            return this;
        }
        if (outer != null) {
            return outer.find(key);
        }
        return null;
    }


    public final static Env STANDARD_ENV = new Env(null);

    static {
        STANDARD_ENV.put("+", new Fn() {
            @Override
            public Object apply(Object... args) throws Exception {
                if (args.length == 2) {
                    Number number = (Number)args[0];
                    Number number2 = (Number)args[1];
                    Double d1 = number.doubleValue();
                    Double d2 = number2.doubleValue();
                    return d1 + d2;
                }
                throw new Exception("add error");
            }
        });
        STANDARD_ENV.put("-",new Fn() {
            @Override
            public Object apply(Object... args) throws Exception {
                if (args.length == 2) {
                    Number number = (Number)args[0];
                    Number number2 = (Number)args[1];
                    Double d1 = number.doubleValue();
                    Double d2 = number2.doubleValue();
                    return d1 - d2;
                }
                throw new Exception("sub error");
            }
        });
        STANDARD_ENV.put("*",new Fn() {
            @Override
            public Object apply(Object... args) throws Exception {
                if (args.length == 2) {
                    Number number = (Number)args[0];
                    Number number2 = (Number)args[1];
                    Double d1 = number.doubleValue();
                    Double d2 = number2.doubleValue();
                    return d1 * d2;
                }
                throw new Exception("mul error");
            }
        });
        STANDARD_ENV.put("/",new Fn() {
            @Override
            public Object apply(Object... args) throws Exception {
                if (args.length == 2) {
                    Number number = (Number)args[0];
                    Number number2 = (Number)args[1];
                    Double d1 = number.doubleValue();
                    Double d2 = number2.doubleValue();
                    return d1 / d2;
                }
                throw new Exception("div error");
            }
        });
        STANDARD_ENV.put(">",new Fn() {
            @Override
            public Object apply(Object... args) throws Exception {
                if (args.length == 2) {
                    Number number = (Number)args[0];
                    Number number2 = (Number)args[1];
                    Double d1 = number.doubleValue();
                    Double d2 = number2.doubleValue();
                    return d1 > d2;
                }
                throw new Exception("> error");
            }
        });
        STANDARD_ENV.put("<",new Fn() {
            @Override
            public Object apply(Object... args) throws Exception {
                if (args.length == 2) {
                    Number number = (Number)args[0];
                    Number number2 = (Number)args[1];
                    Double d1 = number.doubleValue();
                    Double d2 = number2.doubleValue();
                    return d1 < d2;
                }
                throw new Exception("< error");
            }
        });

        STANDARD_ENV.put(">=",new Fn() {
            @Override
            public Object apply(Object... args) throws Exception {
                if (args.length == 2) {
                    Number number = (Number)args[0];
                    Number number2 = (Number)args[1];
                    Double d1 = number.doubleValue();
                    Double d2 = number2.doubleValue();
                    return d1 >= d2;
                }
                throw new Exception(">= error");
            }
        });

        STANDARD_ENV.put("<=",new Fn() {
            @Override
            public Object apply(Object... args) throws Exception {
                if (args.length == 2) {
                    Number number = (Number)args[0];
                    Number number2 = (Number)args[1];
                    Double d1 = number.doubleValue();
                    Double d2 = number2.doubleValue();
                    return d1 <= d2;
                }
                throw new Exception("<= error");
            }
        });

        STANDARD_ENV.put("=",new Fn() {
            @Override
            public Object apply(Object... args) throws Exception {
                if (args.length == 2) {
                    Number number = (Number)args[0];
                    Number number2 = (Number)args[1];
                    Double d1 = number.doubleValue();
                    Double d2 = number2.doubleValue();
                    return d1.equals(d2);
                }
                throw new Exception("= error");
            }
        });
        STANDARD_ENV.put("PI", 3.1415926);
        STANDARD_ENV.put("abs", new Fn() {
            @Override
            public Object apply(Object... args) throws Exception {
                if (args.length == 1) {
                    Number number = (Number)args[0];
                    return Math.abs(number.doubleValue());
                }
                throw new Exception("abs error");
            }
        });
    }
}
