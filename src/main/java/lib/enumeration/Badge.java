package lib.enumeration;

import christmas.order.Orders;

public enum Badge {
    // 배지
    STAR("별", 5000),
    TREE("트리", 10000),
    SANTA("산타", 20000);

    private final String badgeName;
    private final int minimumPrice;

    Badge(String badgeName, int minimumPrice) {
        this.badgeName = badgeName;
        this.minimumPrice = minimumPrice;
    }

    public String getBadgeName() {
        return badgeName;
    }

    public int getMinimumPrice() {
        return minimumPrice;
    }

    public static Badge getBadgeOfPrice(Integer price) {
        if (Orders.MAXIMUM_ORDER_BENEFITS < price) return null;
        if (SANTA.getMinimumPrice() <= price) return SANTA;
        if (TREE.getMinimumPrice() <= price) return TREE;
        if (STAR.getMinimumPrice() <= price) return STAR;

        return null;
    }
}
