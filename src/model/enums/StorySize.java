package model.enums;

public enum StorySize {
    LARGE, MEDIUM,  SMALL;
    @Override
    public String toString() {
        return this.name().substring(0, 1).toUpperCase() + this.name().substring(1).toLowerCase();
    }
}
