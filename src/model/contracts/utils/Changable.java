package model.contracts.utils;

public interface Changable <T extends Enum<T>> {

    void changeValue(T value);
}
