package game.base;

/**
 * Created by huynq on 5/20/17.
 */
public class Mathx {
    public static double lerp(double a, double b, double f) {
        return a + f * (b - a);
    }

    public static float clamp(float value, float min, float max) {
        if (value < min) return min;
        if (value > max) return max;
        return value;
    }

    public static int clamp(int value, int min, int max) {
        if (value < min) return min;
        if (value > max) return max;
        return value;
    }
    public static float sign(float value) {
        if (value == 0) return 0;
        return value > 0 ? 1: -1;
    }

    public static boolean inRange(float value, float min, float max) {
        return value >= min && value <= max;
    }
}
