
import core.EngineImpl;
import core.contracts.Engine;


public class Startup {
    public static void main(String[] args) {

        Engine engine = new EngineImpl();
        engine.run();

    }
}