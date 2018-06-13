package edu.kit.informatik.quarto;

import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import edu.kit.informatik.exception.SemanticsException;

public class Bag {
    private Comparator<Token> tokenComparator = new Comparator<Token>() {
        @Override
        public int compare(Token t1, Token t2) {
            if (t1.getIndex() > t2.getIndex()) {
                return 1;
            } else
                return -1;
        }
    };

    private final List<Token> mTokens = new LinkedList<>();

    public Bag() {
        mTokens.addAll(getAllTokensRecursivly(0, null));
        for (int i = 0; i < mTokens.size(); i++) {
            mTokens.get(i).setIndex(i);
        }
    }

    public boolean isEmpty() {
        if (this.mTokens.isEmpty()) {
            return true;
        }
        return false;
    }

    public List<Token> getAllTokensRecursivly(int propertyIndex, Token pToken) {
        List<Token> tokens = new LinkedList<>();
        Token cToken = (pToken != null) ? pToken : new Token();
        if (propertyIndex == TokenPropertyType.values().length) {
            tokens.add(cToken);
            return tokens;
        }
        TokenPropertyType cType = TokenPropertyType.values()[propertyIndex];
        for (int i = 0; i < cType.getNumberOfProperties(); i++) {
            Token newToken = new Token(cToken);
            newToken.addProperty(cType, new IntegerProperty(i));
            tokens.addAll(getAllTokensRecursivly(propertyIndex + 1, newToken));
        }
        return tokens;
    }

    public void addToken(Token pToken) {
        this.mTokens.add(pToken);
    }

    public void removeToken(int pIndex) {
        Iterator<Token> it = this.mTokens.iterator();
        while (it.hasNext()) {
            Token cToken = it.next();
            if (cToken.getIndex() == pIndex) {
                it.remove();
                return;
            }
        }
    }

    public Token getToken(int pIndex) throws SemanticsException {
        if (pIndex < 0 || pIndex > this.mTokens.size() - 1) {
            throw new SemanticsException("the token with the given index " + pIndex + " does exist inside bag.");
        }
        Token cToken = getTokenByIndex(pIndex);
        if (cToken == null) {
            throw new SemanticsException("the token with given index " + pIndex + " is already in use.");
        }
        return cToken;
    }

    public Token getTokenByIndex(int pIndex) {
        for (Token cToken : this.mTokens) {
            if (cToken.getIndex() == pIndex) {
                return cToken;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        String out = "";
        for (int i = 0; i < this.mTokens.size(); i++) {
            if (!out.isEmpty()) {
                out += " ";
            }
            out += getTokenByIndex(i).toString();
        }
        return out;
    }
}
