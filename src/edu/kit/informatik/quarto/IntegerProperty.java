package edu.kit.informatik.quarto;

public class IntegerProperty implements IProperty<Integer> {
    private int mValue;

    public IntegerProperty(Integer pValue) {
        this.mValue = pValue;
    }

    @Override
    public Integer getValue() {
        return this.mValue;
    }
}
