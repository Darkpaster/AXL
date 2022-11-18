package axl.parser;

import axl.parser.ast.*;
import axl.lexer.*;
import java.util.ArrayList;

public class Parser
{
    private ArrayList<Token> tokens;
    private Token current;
    private int i;

    public Parser(ArrayList<Token> tokens)
    {
        this.i = 0;
        this.tokens = tokens;
        this.current = next();
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
}
