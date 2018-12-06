public class Movement {

    final static String N = "N";
    final static String E = "E";
    final static String S = "S";
    final static String W = "W";
    final static String NE = "NE";
    final static String SE = "SE";
    final static String SW = "SW";
    final static String NW = "NW";


    public static void moveE(Entity entity, int distance) {
        entity.setMinX(entity.getMinX() + distance);
        entity.setMaxX(entity.getMaxX() + distance);
        entity.setDirection(E);
    }

    public static void moveW(Entity entity, int distance) {
        entity.setMinX(entity.getMinX() - distance);
        entity.setMaxX(entity.getMaxX() - distance);
        entity.setDirection(W);
    }

    public static void moveS(Entity entity, int distance) {
        entity.setMinY(entity.getMinY() + distance);
        entity.setMaxY(entity.getMaxY() + distance);
        entity.setDirection(S);
    }

    public static void moveN(Entity entity, int distance) {
        entity.setMinY(entity.getMinY() - distance);
        entity.setMaxY(entity.getMaxY() - distance);
        entity.setDirection(N);
    }

    public static void moveNE(Entity entity, int distance) {
        entity.setMinX(entity.getMinX() + distance);
        entity.setMaxX(entity.getMaxX() + distance);
        entity.setMinY(entity.getMinY() - distance);
        entity.setMaxY(entity.getMaxY() - distance);
        entity.setDirection(NE);
    }

    public static void moveNW(Entity entity, int distance) {
        entity.setMinX(entity.getMinX() - distance);
        entity.setMaxX(entity.getMaxX() - distance);
        entity.setMinY(entity.getMinY() - distance);
        entity.setMaxY(entity.getMaxY() - distance);
        entity.setDirection(NW);
    }

    public static void moveSE(Entity entity, int distance) {
        entity.setMinY(entity.getMinY() + distance);
        entity.setMaxY(entity.getMaxY() + distance);
        entity.setMinX(entity.getMinX() + distance);
        entity.setMaxX(entity.getMaxX() + distance);
        entity.setDirection(SE);
    }

    public static void moveSW(Entity entity, int distance) {
        entity.setMinY(entity.getMinY() + distance);
        entity.setMaxY(entity.getMaxY() + distance);
        entity.setMinX(entity.getMinX() - distance);
        entity.setMaxX(entity.getMaxX() - distance);
        entity.setDirection(SW);
    }

}
