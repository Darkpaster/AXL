package axl.lexer;

import axl.LOGGER;
import axl.general.*;

import java.util.ArrayList;

import static java.lang.System.exit;

public class Lexer {

    private final String content;

    public Lexer(String content)
    {
        this.content = content;
        LOGGER.log("[LEXER] обработка:\n\"\"\"\n"+content+"\"\"\"");
        run();
    }

    private ArrayList<Token> tokens = new ArrayList<>();
    private int i = 0;
    private char current;

    private void add(Token token)
    {
        tokens.add(token);
    }

    private void next()
    {
        if(i < content.length()-1)
            current = content.charAt(++i);
        else
            current = 0x00;
    }

    private void last()
    {
        if(i > 0)
            current = content.charAt(--i);
    }

    private void skip()
    {
        while(current == ' ' || current == '\n' || current == '\t' || current == '\r')
            next();
    }

    private void run()
    {
        current = content.charAt(i);

        skip();

        while(i < content.length()-1)
        {
            skip();

            if (current == '"') {
                token_string();
                continue;
            }

            if (current == '\'') {
                token_char();
                continue;
            }

            if ((current >= '0' && '9' >= current) || current == '-' || current == '.') {
                token_number();
                continue;
            }

            if(Character.isDigit(current) || Character.isLetter(current) || current == '_') {
                token_word();
                continue;
            }

            token_op();
        }
        LOGGER.log("[LEXER] конец файла");
        add(new Token(Token.TokenType.ENDFILE));
    }

    private void token_string()
    {
        next();

        StringBuilder str = new StringBuilder();

        while(current != '"')
        {
            str.append(current);
            next();
            if(i == content.length())
                LOGGER.log("Не закрыт символ '\"'", true);
        }

        next();

        LOGGER.log("[LEXER] строка \""+str+"\"");
        tokens.add(new Token(Token.TokenType.STRING, new ValueString(str.toString())));
    }

    private void token_char()
    {
        next();
        LOGGER.log("[LEXER] char '"+current+"'");
        tokens.add(new Token(Token.TokenType.CHAR, new ValueChar(current)));
        next();

        if(current != '\'')
            LOGGER.log("Не закрыт символ '\\''", true);

        next();
    }

    private void token_number()
    {
        StringBuilder str = new StringBuilder();
        boolean type = false;

        if(current == '-' || current == '.')
        {
            str.append(current);

            if(current == '.') {
                type = true;
                next();
                if(!(current >= '0' && '9' >= current))
                {
                    last();
                    token_op();
                    return;
                }
                last();
            }

            next();
        }

        while((current >= '0' && '9' >= current) || current == '.')
        {
            if(current == '.')
            {
                if(type) LOGGER.log("Лишний символ dot '.' в числе", true);
                else type = true;
            }

            str.append(current);
            next();
        }

        double value = Float.parseFloat(str.toString());

        boolean is_double = true;
        if(current == 'f')
        {
            is_double = false;
            next();
        }

        if(type || !is_double)
        {
            if(is_double) {
                LOGGER.log("[LEXER] число double \""+str+"\"");
                add(new Token(Token.TokenType.DOUBLE, new ValueDouble(value)));
            }
            else {
                LOGGER.log("[LEXER] число float \""+str+"\"");
                add(new Token(Token.TokenType.FLOAT, new ValueFloat((float) value)));
            }
            return;
        }

        if(value >= -127 && value <= 128) {
            LOGGER.log("[LEXER] число byte \""+str+"\"");
            add(new Token(Token.TokenType.BYTE, new ValueByte((byte) value)));
        }
        else if (value >= -32768 && value <= 32767) {
            LOGGER.log("[LEXER] число short \""+str+"\"");
            add(new Token(Token.TokenType.SHORT, new ValueShort((short) value)));
        }
        else if (value >= -2147483648 && value <= 2147483647) {
            LOGGER.log("[LEXER] число int \""+str+"\"");
            add(new Token(Token.TokenType.INT, new ValueInt((int) value)));
        }
        else {
            LOGGER.log("[LEXER] число long \""+str+"\"");
            add(new Token(Token.TokenType.LONG, new ValueLong((long) value)));
        }
    }

    private void token_word()
    {
        StringBuilder str = new StringBuilder();

        while(Character.isDigit(current) || Character.isLetter(current) || current == '_')
        {
            str.append(current);
            next();
        }

        if (str.toString().equals("true") || str.toString().equals("false"))
        {
            LOGGER.log("[LEXER] булево значение "+str);
            tokens.add(new Token(Token.TokenType.BOOLEAN, new ValueBoolean(str.toString().equals("true"))));
            return;
        }

        LOGGER.log("[LEXER] слово \""+str+"\"");
        tokens.add(new Token(Token.TokenType.WORD, new ValueString(str.toString())));
    }

    private void token_op()
    {
        LOGGER.log("[LEXER] неизвестный символ '"+current+"'");
        next();
    }

}