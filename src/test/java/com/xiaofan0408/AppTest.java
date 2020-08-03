package com.xiaofan0408;

import static org.junit.Assert.assertTrue;

import com.xiaofan0408.eval.Env;
import com.xiaofan0408.eval.Eval;
import com.xiaofan0408.lex.Lexer;
import com.xiaofan0408.lex.Token;
import com.xiaofan0408.parser.Node;
import com.xiaofan0408.parser.Parser;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue()
    {
        List<String> codes = new ArrayList<>();
        codes.add("(define first car)");
        codes.add("(define rest cdr)");
        codes.add("(define count (lambda (item L) (if L (+ (equal? item (first L)) (count item (rest L))) 0)))");
        codes.add("(count 0 (list 0 1 2 3 4  0 0 0 ))");
        codes.add("(count (quote the) (quote (the more the merrier the bigger the better)))");
        codes.add("(define twice (lambda (x) (* 2 x)))");
        codes.add("(twice 5)");
        codes.add("(define repeat (lambda (f) (lambda (x) (f (f x)))))");
        codes.add("((repeat twice) 10)");
        codes.add("((repeat (repeat twice)) 10)");
        codes.add("((repeat (repeat (repeat twice))) 10)");
        codes.add("((repeat (repeat (repeat (repeat twice)))) 10)");
        codes.add("(pow 2 16)");
        codes.add("(define fib (lambda (n) (if (< n 2) 1 (+ (fib (- n 1)) (fib (- n 2))))))");
        codes.add("(define range (lambda (a b) (if (= a b) (quote ()) (cons a (range (+ a 1) b)))))");
        codes.add("(range 0 10)");

        Lexer lexer = new Lexer();
        Parser parser = new Parser();

        for (String code: codes) {
            List<Token> tokens = lexer.lex(code);
            try {
                Node node = parser.parse(tokens);
                Eval eval = new Eval();
                Object result = eval.eval(node, Env.STANDARD_ENV);
                if (result != null) {
                    if (result.getClass().isArray()) {
                        StringBuffer stringBuffer = new StringBuffer();
                        stringBuffer.append("(");
                        List<String> stringList = new ArrayList<>();
                        for (Object object: (Object[])result) {
                           stringList.add(object.toString());
                        }
                        stringBuffer.append(String.join(",",stringList));
                        stringBuffer.append(")");
                        System.out.printf(stringBuffer.toString());
                    } else {
                        System.out.printf(result.toString());
                    }
                    System.out.println();
                }
            } catch (Exception e) {
                System.out.println("解析错误:" + e.getMessage());
                e.printStackTrace();
            }
        }
    }
}
