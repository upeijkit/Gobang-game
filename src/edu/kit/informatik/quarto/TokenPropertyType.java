package edu.kit.informatik.quarto;

public enum TokenPropertyType {

    COLOR("schwaz", "weiss"), SHAPE("eckig", "zylinderfoermig"), SIZE("gross", "klein"), DENSITY("hohl", "massiv");

    private final Object[] mNames;

    private TokenPropertyType(Object... pNames) {
        this.mNames = pNames;
    }

    public int getNumberOfProperties() {
        return this.mNames.length;
    }

}
