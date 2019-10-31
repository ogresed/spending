package dbConnectWindow;

public class DataWrapper <F, S> {
    private F firstObject;
    private S secondObject;

    public DataWrapper(F firstObject, S secondObject) {
        this.firstObject = firstObject;
        this.secondObject = secondObject;
    }

    public F getFirstObject() {
        return firstObject;
    }

    public S getSecondObject() {
        return secondObject;
    }
}
