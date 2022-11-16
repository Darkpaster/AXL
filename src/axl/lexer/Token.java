package axl.lexer;


import axl.general.Value;

public class Token {

    public enum Type
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
        OBJECT,     // parser

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

    private final Type type;

    private Value value = null;

    public Token(Type type, Value value)
    {
        this.type = type;
        this.value = value;
    }

    public Token(Type type)
    {
        this.type = type;
    }

    public Type getType() {
        return type;
    }

    public Value getValue()
    {
        return value;
    }

    public boolean equal(Type t)
    {
        return t == type;
    }

    public boolean is_eplus()
    {
        return equal(Type.EPLUS);
    }

    public boolean is_eminus()
    {
        return equal(Type.EMINUS);
    }

    public boolean is_estar()
    {
        return equal(Type.ESTAR);
    }

    public boolean is_eslash()
    {
        return equal(Type.ESLASH);
    }

    public boolean is_edstar()
    {
        return equal(Type.EDSTAR);
    }

    public boolean is_comma()
    {
        return equal(Type.COMMA);
    }

    public boolean is_lsquare()
    {
        return equal(Type.LSQUARE);
    }

    public boolean is_rsquare()
    {
        return equal(Type.RSQUARE);
    }

    public boolean is_dplus()
    {
        return equal(Type.DPLUS);
    }

    public boolean is_dminus()
    {
        return equal(Type.DMINUS);
    }

    public boolean is_dstar()
    {
        return equal(Type.DSTAR);
    }

    public boolean is_dless()
    {
        return equal(Type.DLESS);
    }

    public boolean is_dmore()
    {
        return equal(Type.DMORE);
    }

    public boolean is_percent()
    {
        return equal(Type.PERCENT);
    }

    public boolean is_and()
    {
        return equal(Type.AND);
    }

    public boolean is_or()
    {
        return equal(Type.OR);
    }

    public boolean is_less()
    {
        return equal(Type.LESS);
    }

    public boolean is_more()
    {
        return equal(Type.MORE);
    }

    public boolean is_eless()
    {
        return equal(Type.ELESS);
    }

    public boolean is_emore()
    {
        return equal(Type.EMORE);
    }

    public boolean is_dequal()
    {
        return equal(Type.DEQUAL);
    }

    public boolean is_nequal()
    {
        return equal(Type.NEQUAL);
    }

    public boolean is_dand()
    {
        return equal(Type.DAND);
    }

    public boolean is_dor()
    {
        return equal(Type.DOR);
    }

    public boolean is_dot()
    {
        return equal(Type.DOT);
    }

    public boolean is_word()
    {
        return equal(Type.WORD);
    }

    public boolean is_int()
    {
        return equal(Type.INT);
    }

    public boolean is_char()
    {
        return equal(Type.CHAR);
    }

    public boolean is_short()
    {
        return equal(Type.SHORT);
    }

    public boolean is_byte()
    {
        return equal(Type.BYTE);
    }

    public boolean is_boolean()
    {
        return equal(Type.BOOLEAN);
    }

    public boolean is_object()
    {
        return equal(Type.OBJECT);
    }

    public boolean is_long()
    {
        return equal(Type.LONG);
    }

    public boolean is_float()
    {
        return equal(Type.FLOAT);
    }

    public boolean is_double()
    {
        return equal(Type.DOUBLE);
    }

    public boolean is_string()
    {
        return equal(Type.STRING);
    }

    public boolean is_semi()
    {
        return equal(Type.SEMI);
    }

    public boolean is_equal()
    {
        return equal(Type.EQUAL);
    }

    public boolean is_plus()
    {
        return equal(Type.PLUS);
    }

    public boolean is_minus()
    {
        return equal(Type.MINUS);
    }

    public boolean is_star()
    {
        return equal(Type.STAR);
    }

    public boolean is_slash()
    {
        return equal(Type.SLASH);
    }

    public boolean is_lpar()
    {
        return equal(Type.LPAR);
    }

    public boolean is_rpar()
    {
        return equal(Type.RPAR);
    }

    public boolean is_lbrace()
    {
        return equal(Type.LBRACE);
    }

    public boolean is_rbrace()
    {
        return equal(Type.RBRACE);
    }

    public boolean is_epercent()
    {
        return equal(Type.EPERCENT);
    }

    public boolean is_eand()
    {
        return equal(Type.EAND);
    }

    public boolean is_eor()
    {
        return equal(Type.EOR);
    }

    public boolean is_not()
    {
        return equal(Type.NOT);
    }

    public boolean is_quest()
    {
        return equal(Type.QUEST);
    }

    public boolean is_colon()
    {
        return equal(Type.COLON);
    }

    public boolean is_endfile()
    {
        return equal(Type.ENDFILE);
    }
}