package edu.kit.informatik.quarto;

import java.util.LinkedHashMap;
import java.util.Map;

public class Token {
    private final Map<TokenPropertyType, IProperty<?>> mProperties;
    private int mIndex;

    public Token() {
        this(-1);
    }

    public Token(Token pToken) {
        this.mProperties = pToken.getProperties();
        this.mIndex = pToken.getIndex();
    }

    public Token(int pIndex) {
        this.mProperties = new LinkedHashMap<>();
        this.mIndex = pIndex;
    }

    public int getIndex() {
        return this.mIndex;
    }

    public void setIndex(int pIndex) {
        this.mIndex = pIndex;
    }

    public Map<TokenPropertyType, IProperty<?>> getProperties() {
        return this.mProperties;
    }

    public void addProperty(TokenPropertyType pType, IProperty<?> pProperty) {
        this.mProperties.put(pType, pProperty);
    }

    @Override
    public String toString() {
        return String.valueOf(this.mIndex);
    }

}
