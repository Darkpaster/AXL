package axl.parser;

import axl.parser.ast.*;
import axl.lexer.*;

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

    public void next()
    {
        if(i < this.tokens.size())

    }
}
