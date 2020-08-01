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
//
        codes.add("(define first car)");
        codes.add("(define rest cdr)");
        codes.add("(define count (lambda (item L) (if L (+ (equal? item (first L)) (count item (rest L))) 0)))");
//        codes.add("(count 0 (list 0 1 2 3 4  0 0 0 ))");
        codes.add("(count (quote the) (quote (the more the merrier the bigger the better)))");

//        codes.add("(car (list 0 1 2 3 4))");
//        codes.add("(cdr (list 0 1 2 3 4))");
        Lexer lexer = new Lexer();
        Parser parser = new Parser();

        for (String code: codes) {
            List<Token> tokens = lexer.lex(code);
            try {
                Node node = parser.parse(tokens);
                Eval eval = new Eval();
                Object result = eval.eval(node, Env.STANDARD_ENV);
                if (result != null) {
                    System.out.printf(result.toString());
                    System.out.println();
                }
            } catch (Exception e) {
                System.out.println("解析错误:" + e.getMessage());
                e.printStackTrace();
            }
        }
    }
}
