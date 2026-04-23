package sudoku;

public class Space {

    private Integer value;
    private final boolean fixed;

    public Space(Integer value, boolean fixed) {
        this.value = value;
        this.fixed = fixed;
    }

    public Integer getValue() {
        return value;
    }

    public boolean isFixed() {
        return fixed;
    }

    public boolean setValue(Integer value) {
        if (fixed) return false;
        this.value = value;
        return true;
    }

    public void clear() {
        if (!fixed) {
            value = null;
        }
    }
}