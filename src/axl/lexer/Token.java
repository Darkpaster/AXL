package axl.lexer;


import axl.general.Value;

public class Token {

    public enum TokenType
    {
        WORD,

        INT,
        BYTE,
        SHORT,
        LONG,
        BOOLEAN,
        FLOAT,
        DOUBLE,
        STRING,
        CHAR,

        SEMI,       // ;
        DOT,        // .
        COMMA,      // ,

        LPAR,       // (
        RPAR,       // )
        LBRACE,     // {
        RBRACE,     // }
        LSQUARE,    // [
        RSQUARE,    // ]

        DPLUS,      // ++
        DMINUS,     // --
        DSTAR,      // **
        DLESS,      // <<
        DMORE,      // >>
        PLUS,       // +
        MINUS,      // -
        STAR,       // *
        SLASH,      // /
        PERCENT,    // %
        AND,        // &
        OR,         // |
        QUEST,      // ?
        COLON,      // :

        EQUAL,      // =
        EPLUS,      // +=
        EMINUS,     // -=
        ESTAR,      // *=
        ESLASH,     // /=
        EDSTAR,     // **=
        EPERCENT,   // %=
        EAND,       // &=
        EOR,        // |=

        LESS,       // <
        MORE,       // >
        ELESS,      // <=
        EMORE,      // >=
        DEQUAL,     // ==
        NEQUAL,     // !=
        NOT,        // !

        DAND,       // &&
        DOR,        // ||

        ENDFILE
    }

    private final TokenType type;

    private Value value = null;

    public Token(TokenType type, Value value)
    {
        this.type = type;
        this.value = value;
    }

    public Token(TokenType type)
    {
        this.type = type;
    }

    public Value getValue()
    {
        return value;
    }

    public boolean equal(TokenType t)
    {
        return t == type;
    }

    public boolean is_eplus()
    {
        return equal(TokenType.EPLUS);
    }

    public boolean is_eminus()
    {
        return equal(TokenType.EMINUS);
    }

    public boolean is_estar()
    {
        return equal(TokenType.ESTAR);
    }

    public boolean is_eslash()
    {
        return equal(TokenType.ESLASH);
    }

    public boolean is_edstar()
    {
        return equal(TokenType.EDSTAR);
    }

    public boolean is_comma()
    {
        return equal(TokenType.COMMA);
    }

    public boolean is_lsquare()
    {
        return equal(TokenType.LSQUARE);
    }

    public boolean is_rsquare()
    {
        return equal(TokenType.RSQUARE);
    }

    public boolean is_dplus()
    {
        return equal(TokenType.DPLUS);
    }

    public boolean is_dminus()
    {
        return equal(TokenType.DMINUS);
    }

    public boolean is_dstar()
    {
        return equal(TokenType.DSTAR);
    }

    public boolean is_dless()
    {
        return equal(TokenType.DLESS);
    }

    public boolean is_dmore()
    {
        return equal(TokenType.DMORE);
    }

    public boolean is_percent()
    {
        return equal(TokenType.PERCENT);
    }

    public boolean is_and()
    {
        return equal(TokenType.AND);
    }

    public boolean is_or()
    {
        return equal(TokenType.OR);
    }

    public boolean is_less()
    {
        return equal(TokenType.LESS);
    }

    public boolean is_more()
    {
        return equal(TokenType.MORE);
    }

    public boolean is_eless()
    {
        return equal(TokenType.ELESS);
    }

    public boolean is_emore()
    {
        return equal(TokenType.EMORE);
    }

    public boolean is_dequal()
    {
        return equal(TokenType.DEQUAL);
    }

    public boolean is_nequal()
    {
        return equal(TokenType.NEQUAL);
    }

    public boolean is_dand()
    {
        return equal(TokenType.DAND);
    }

    public boolean is_dor()
    {
        return equal(TokenType.DOR);
    }

    public boolean is_dot()
    {
        return equal(TokenType.DOT);
    }

    public boolean is_word()
    {
        return equal(TokenType.WORD);
    }

    public boolean is_int()
    {
        return equal(TokenType.INT);
    }

    public boolean is_char()
    {
        return equal(TokenType.CHAR);
    }

    public boolean is_short()
    {
        return equal(TokenType.SHORT);
    }

    public boolean is_byte()
    {
        return equal(TokenType.BYTE);
    }

    public boolean is_boolean()
    {
        return equal(TokenType.BOOLEAN);
    }

    public boolean is_long()
    {
        return equal(TokenType.LONG);
    }

    public boolean is_float()
    {
        return equal(TokenType.FLOAT);
    }

    public boolean is_double()
    {
        return equal(TokenType.DOUBLE);
    }

    public boolean is_string()
    {
        return equal(TokenType.STRING);
    }

    public boolean is_semi()
    {
        return equal(TokenType.SEMI);
    }

    public boolean is_equal()
    {
        return equal(TokenType.EQUAL);
    }

    public boolean is_plus()
    {
        return equal(TokenType.PLUS);
    }

    public boolean is_minus()
    {
        return equal(TokenType.MINUS);
    }

    public boolean is_star()
    {
        return equal(TokenType.STAR);
    }

    public boolean is_slash()
    {
        return equal(TokenType.SLASH);
    }

    public boolean is_lpar()
    {
        return equal(TokenType.LPAR);
    }

    public boolean is_rpar()
    {
        return equal(TokenType.RPAR);
    }

    public boolean is_lbrace()
    {
        return equal(TokenType.LBRACE);
    }

    public boolean is_rbrace()
    {
        return equal(TokenType.RBRACE);
    }

    public boolean is_epercent()
    {
        return equal(TokenType.EPERCENT);
    }

    public boolean is_eand()
    {
        return equal(TokenType.EAND);
    }

    public boolean is_eor()
    {
        return equal(TokenType.EOR);
    }

    public boolean is_not()
    {
        return equal(TokenType.NOT);
    }

    public boolean is_quest()
    {
        return equal(TokenType.QUEST);
    }

    public boolean is_colon()
    {
        return equal(TokenType.COLON);
    }

    public boolean is_endfile()
    {
        return equal(TokenType.ENDFILE);
    }
}