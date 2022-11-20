package axl.parser;

import axl.LOGGER;
import axl.lexer.Token;
import axl.parser.ast.Ast;
import axl.parser.ast.AstFunctionCall;
import axl.parser.ast.AstGetLocalVar;
import axl.parser.ast.math.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;

import static axl.lexer.Token.Type.*;

public class Parser
{
    private final ArrayList<Token> tokens;
    private Token current;
    private int i;

    private final HashMap<Token.Type, Integer> priority = new HashMap<>();
    {
        priority.put(Token.Type.LPAR,     0);
        priority.put(PLUS,                1);
        priority.put(Token.Type.MINUS,    1);
        priority.put(Token.Type.PERCENT,  1);
        priority.put(Token.Type.STAR,     2);
        priority.put(Token.Type.SLASH,    2);
        priority.put(Token.Type.DSTAR,    3);
    }

    public Parser(ArrayList<Token> tokens)
    {
        this.i = 0;
        this.tokens = tokens;
        next();
    }

    private void next() // Переход к следующему токену
    {
        if (i < tokens.size())
            current = tokens.get(i++);
        else
            current = new Token(Token.Type.ENDFILE);
    }

    private void last() // Переход к прошлому токену
    {
        if (i >= 0)
            current = tokens.get(--i);
    }

    private void last(int count) // Переход к прошлому токену
    {
        for (int x = 0; x < count; x++)
            last();
    }

    public Ast parse_expr(ArrayList<Token.Type> end)
    {
        Ast result;

        {
            ArrayList<Object> postfix = new ArrayList<>();

            {
                Stack<Object> stack = new Stack<>();
                while (!is_token(end)) {
                    if (current.is_boolean() || current.is_byte() || current.is_char() || current.is_short() || current.is_int() || current.is_float() || current.is_double() || current.is_long()) {
                        postfix.add(current.getValue());
                        next();
                        continue;
                    }
                    if (current.is_word()) {
                        String name = current.getValue().getString();
                        next();
                        if (current.is_lpar()) {
                            postfix.add(parse_function_call(name));
                            continue;
                        }
                        postfix.add(new AstGetLocalVar(name));
                        continue;
                    }

                    if (current.is_lpar()) {
                        stack.push(Token.Type.LPAR);
                        next();
                        continue;
                    }

                    if (current.is_rpar()) {
                        while ((!stack.isEmpty()) && stack.peek() != Token.Type.LPAR)
                            postfix.add(stack.pop());
                        stack.pop(); // pop lpar
                        next();
                        continue;
                    }

                    if(!priority.containsKey(current.getType()))
                        LOGGER.log("[PARSER] неизвестный токен '"+current.getType()+"' при парсинге мат. выражения", true);

                    if ((!stack.isEmpty()) && (priority.get((Token.Type)stack.peek()) >= priority.get(current.getType())))
                        postfix.add(stack.pop());

                    stack.push(current.getType());

                    next();
                }
                while(stack.size() != 0)
                    postfix.add(stack.pop());
            }
            if(postfix.size() == 0)
                LOGGER.log("[PARSER] ошибка при парсинге мат. вырежения", true);

            int i = 0;
            while (postfix.size() != 1)
            {
                if(postfix.size() <= i)
                    LOGGER.log("[PARSER] ошибка при парсинге мат. вырежения (бесконечный цикл)", true);

                if(postfix.get(i) instanceof Token.Type op)
                {
                    AstMath math;
                    switch (op) {
                        case PLUS ->     math = new AstSum();
                        case MINUS ->    math = new AstSub();
                        case STAR ->     math = new AstMul();
                        case SLASH ->    math = new AstDiv();
                        case DSTAR ->    math = new AstPow();
                        case PERCENT ->  math = new AstRem();
                        default -> {
                            LOGGER.log("[PARSER] ошибка при парсинге мат. вырежения", true);
                            math = new AstMath(); // чтобы ide не ругалась
                        }
                    }
                    math.left = (Ast) postfix.get(i-2);
                    math.right = (Ast) postfix.get(i-1);
                    postfix.remove(i);
                    postfix.remove(i-1);
                    postfix.set(i-2, math);
                    i -= 3;
                }
                i++;
            }
            result = (Ast) postfix.get(0);
        }

        return result;
    }

    private AstFunctionCall parse_function_call(String name) {
        next(); // skip lpar

        while (!current.is_rpar()) next(); // потом допишу

        next();

        return new AstFunctionCall();
    }

    public boolean is_token(ArrayList<Token.Type> tokens)
    {
        for (Token.Type t: tokens)
            if(t == current.getType())
                return true;
        return false;
    }
}
