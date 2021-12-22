package edu.neu.csye6200.Utils;

/**
 * @author SaiAkhil
 */
public class FunctionalUtilities {
    @FunctionalInterface
    public interface BiFunction<One, Two> {
        void accept(One one, Two two);
    }

    @FunctionalInterface
    public interface TriFunction<One, Two, Three> {
        void accept(One one, Two two, Three three);
    }

    @FunctionalInterface
    public interface BiFunctionWithReturnType<One, Two, Three> {
        Three accept(One one, Two two);
    }
}
