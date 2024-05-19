package utils.contracts;

public interface PrintableName {
    default String getRealName(){
        return this.getClass().getSimpleName().substring(0, getClass().getSimpleName().length() - 4);
    }
}
