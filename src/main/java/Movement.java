public class Movement {

    public static void moveE(Entity entity, int distance) {
        entity.setMinX(entity.getMinX() + distance);
        entity.setMaxX(entity.getMaxX() + distance);
    }

    public static void moveW(Entity entity, int distance) {
        entity.setMinX(entity.getMinX() - distance);
        entity.setMaxX(entity.getMaxX() - distance);
    }

    public static void moveS(Entity entity, int distance) {
        entity.setMinY(entity.getMinY() + distance);
        entity.setMaxY(entity.getMaxY() + distance);
    }

    public static void moveN(Entity entity, int distance) {
        entity.setMinY(entity.getMinY() - distance);
        entity.setMaxY(entity.getMaxY() - distance);
    }

    public static void moveNE(Entity entity, int distance) {
        entity.setMinX(entity.getMinX() + distance);
        entity.setMaxX(entity.getMaxX() + distance);
        entity.setMinY(entity.getMinY() - distance);
        entity.setMaxY(entity.getMaxY() - distance);
    }

    public static void moveNW(Entity entity, int distance) {
        entity.setMinX(entity.getMinX() - distance);
        entity.setMaxX(entity.getMaxX() - distance);
        entity.setMinY(entity.getMinY() - distance);
        entity.setMaxY(entity.getMaxY() - distance);
    }

    public static void moveSE(Entity entity, int distance) {
        entity.setMinY(entity.getMinY() + distance);
        entity.setMaxY(entity.getMaxY() + distance);
        entity.setMinX(entity.getMinX() + distance);
        entity.setMaxX(entity.getMaxX() + distance);
    }

    public static void moveSW(Entity entity, int distance) {
        entity.setMinY(entity.getMinY() + distance);
        entity.setMaxY(entity.getMaxY() + distance);
        entity.setMinX(entity.getMinX() - distance);
        entity.setMaxX(entity.getMaxX() - distance);
    }

}
