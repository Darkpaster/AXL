package axl.lexer;

import axl.LOGGER;
import axl.general.Values.*;

import java.util.ArrayList;

public class Lexer {

    private final String content;

    public Lexer(String content)
    {
        this.content = content+" ";
        LOGGER.log("[LEXER] обработка:\n\"\"\"\n"+content+"\n\"\"\"");
        run();
    }

    public ArrayList<Token> getTokens()
    {
        return tokens;
    }

    private final ArrayList<Token> tokens = new ArrayList<>();
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

            if ((current >= '0' && '9' >= current) || current == '.' || current == '-') {
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
        add(new Token(Token.Type.ENDFILE));
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
        tokens.add(new Token(Token.Type.STRING, new ValueString(str.toString())));
    }

    private void token_char()
    {
        next();
        LOGGER.log("[LEXER] char '"+current+"'");
        tokens.add(new Token(Token.Type.CHAR, new ValueChar(current)));
        next();

        if(current != '\'')
            LOGGER.log("Не закрыт символ '\\''", true);

        next();
    }

    private void token_number_bit()
    {
        StringBuilder str = new StringBuilder();
        while((current >= '0' && '1' >= current))
        {
            str.append(current);
            next();
        }
        if(str.toString().equals("")) LOGGER.log("[LEXER] передана структура 0b без числа", true);

        long value = Long.parseLong(str.toString(), 2);

        token_number_value(value);
    }

    private void token_number_oct()
    {
        StringBuilder str = new StringBuilder();
        while((current >= '0' && '7' >= current))
        {
            str.append(current);
            next();
        }
        if(str.toString().equals("")) LOGGER.log("[LEXER] передана структура 0q без числа", true);

        long value = Long.parseLong(str.toString(), 8);

        token_number_value(value);
    }

    private void token_number_hex()
    {
        StringBuilder str = new StringBuilder();
        while((current >= '0' && '9' >= current) || (current >= 'a' && 'f' >= current) || (current >= 'A' && 'F' >= current))
        {
            str.append(current);
            next();
        }
        if(str.toString().equals("")) LOGGER.log("[LEXER] передана структура 0x без числа", true);

        long value = Long.parseLong(str.toString(), 16);

        token_number_value(value);
    }

    private void token_number()
    {

        if(current == '0')
        {
            next();
            if(current == 'x')
            {
                next();
                token_number_hex();
                return;
            }
            if(current == 'b')
            {
                next();
                token_number_bit();
                return;
            }
            if(current == 'q')
            {
                next();
                token_number_oct();
                return;
            }
        }

        StringBuilder str = new StringBuilder();
        boolean type = false;

        if(current == '.' || current == '-')
        {
            str.append(current);

            {
                Token last_token;
                if(tokens.size() > 0)
                    last_token = tokens.get(tokens.size() - 1);
                else
                    last_token = new Token(Token.Type.EQUAL);
                if (current == '-' &&
                        (
                                last_token.is_word() ||
                                last_token.is_rpar() ||
                                last_token.is_char() ||
                                        last_token.is_byte() ||
                                        last_token.is_short() ||
                                        last_token.is_string() ||
                                        last_token.is_int() ||
                                        last_token.is_long() ||
                                        last_token.is_float() ||
                                        last_token.is_double()
                        )) {
                    token_op();
                    return;
                }
                else if(current == '.')
                    type = true;
            }


            next();
            if(!(current >= '0' && '9' >= current))
            {
                last();
                token_op();
                return;
            }

            last();

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
        double value = Double.parseDouble(str.toString());

        byte is_double = 2;
        if (current == 'f') {
            is_double = 0;
            next();
        }
        else if (current == 'd') {
            is_double = 1;
            next();
        }

        if(type || is_double != 2) {
            if (is_double > 0) {
                LOGGER.log("[LEXER] число double \"" + str + "\"");
                add(new Token(Token.Type.DOUBLE, new ValueDouble(value)));
            } else {
                LOGGER.log("[LEXER] число float \"" + str + "\"");
                add(new Token(Token.Type.FLOAT, new ValueFloat((float) value)));
            }
            return;
        }

        token_number_value((long) value);
    }

    private void token_number_value(long value)
    {
        if(value >= -127 && value <= 128) {
            LOGGER.log("[LEXER] число byte \""+value+"\"");
            add(new Token(Token.Type.BYTE, new ValueByte((byte) value)));
        }
        else if (value >= -32768 && value <= 32767) {
            LOGGER.log("[LEXER] число short \""+value+"\"");
            add(new Token(Token.Type.SHORT, new ValueShort((short) value)));
        }
        else if (value >= -2147483648 && value <= 2147483647) {
            LOGGER.log("[LEXER] число int \""+value+"\"");
            add(new Token(Token.Type.INT, new ValueInt((int) value)));
        }
        else {
            LOGGER.log("[LEXER] число long \""+value+"\"");
            add(new Token(Token.Type.LONG, new ValueLong(value)));
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
            tokens.add(new Token(Token.Type.BOOLEAN, new ValueBoolean(str.toString().equals("true"))));
            return;
        }

        LOGGER.log("[LEXER] слово \""+str+"\"");
        tokens.add(new Token(Token.Type.WORD, new ValueString(str.toString())));
    }

    private void token_op()
    {
        if(current == 0x00) return;
        Token.Type type;
        switch (current) {
            case ';' -> type = Token.Type.SEMI;
            case '.' -> type = Token.Type.DOT;
            case ',' -> type = Token.Type.COMMA;
            case '(' -> type = Token.Type.LPAR;
            case ')' -> type = Token.Type.RPAR;
            case '{' -> type = Token.Type.LBRACE;
            case '}' -> type = Token.Type.RBRACE;
            case '[' -> type = Token.Type.LSQUARE;
            case ']' -> type = Token.Type.RSQUARE;
            case '?' -> type = Token.Type.QUEST;
            case ':' -> type = Token.Type.COLON;
            case '+' -> {
                next();
                if (current == '+') {
                    type = Token.Type.DPLUS;
                    next();
                }
                else if (current == '=') {
                    type = Token.Type.EPLUS;
                    next();
                }
                else
                    type = Token.Type.PLUS;
                last();
            }
            case '-' -> {
                next();
                if (current == '-') {
                    type = Token.Type.DMINUS;
                    next();
                }
                else if (current == '=') {
                    type = Token.Type.EMINUS;
                    next();
                }
                else {
                    type = Token.Type.MINUS;
                }
                last();
            }
            case '*' -> {
                next();
                if (current == '*') {
                    next();
                    if (current == '=') {
                        type = Token.Type.EDSTAR;
                        next();
                    }
                    else {
                        type = Token.Type.DSTAR;
                    }
                } else if (current == '=') {
                    type = Token.Type.ESTAR;
                    next();
                }
                else {
                    type = Token.Type.STAR;
                }
                last();
            }
            case '/' -> {
                next();
                if (current == '/') {
                    token_comment_uno();
                    return;
                } else if (current == '*') {
                    token_comment_multi();
                    return;
                } else if (current == '=') {
                    type = Token.Type.ESLASH;
                    next();
                }
                else
                    type = Token.Type.SLASH;
                last();
            }
            case '<' -> {
                next();
                if (current == '<') {
                    type = Token.Type.DLESS;
                    next();
                }
                else if (current == '=') {
                    type = Token.Type.ELESS;
                    next();
                }
                else
                    type = Token.Type.LESS;
                last();
            }
            case '>' -> {
                next();
                if (current == '>') {
                    type = Token.Type.DMORE;
                    next();
                }
                else if (current == '=') {
                    type = Token.Type.EMORE;
                    next();
                }
                else
                    type = Token.Type.MORE;
                last();
            }
            case '%' -> {
                next();
                if (current == '=') {
                    type = Token.Type.EPERCENT;
                    next();
                }
                else
                    type = Token.Type.PERCENT;
                last();
            }
            case '&' -> {
                next();
                if (current == '=') {
                    type = Token.Type.EAND;
                    next();
                }
                else if (current == '&') {
                    type = Token.Type.DAND;
                    next();
                }
                else
                    type = Token.Type.AND;
                last();
            }
            case '|' -> {
                next();
                if (current == '=') {
                    type = Token.Type.EOR;
                    next();
                }
                else if (current == '|') {
                    type = Token.Type.DOR;
                    next();
                }
                else
                    type = Token.Type.OR;
                last();
            }
            case '=' -> {
                next();
                if (current == '=') {
                    type = Token.Type.DEQUAL;
                    next();
                }
                else
                    type = Token.Type.EQUAL;
                last();
            }
            case '!' -> {
                next();
                if (current == '=') {
                    type = Token.Type.NEQUAL;
                    next();
                }
                else
                    type = Token.Type.NOT;
                last();
            }
            default -> type = Token.Type.ENDFILE;
        }
        next();

        if(type == Token.Type.ENDFILE)
            LOGGER.log("[LEXER] неизвестный символ 0x"+(byte)current);
        else
        {
            LOGGER.log("[LEXER] оператор '"+type+"'");
            add(new Token(type));
        }
    }

    private void token_comment_multi()
    {
        while (i < content.length()-1)
        {
            next();
            if(current == '*')
            {
                next();
                if(current == '/')
                    break;
            }
        }
        if (!(i < content.length()-1)) LOGGER.log("[LEXER] не закрыт коментарий '/*'");
        next();
    }

    private void token_comment_uno()
    {
        while (i < content.length()-1 && !(current == '\n' || current == '\r'))
            next();
        next();
    }
}