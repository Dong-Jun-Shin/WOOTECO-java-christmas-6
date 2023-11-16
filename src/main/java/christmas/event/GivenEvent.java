package christmas.event;

import java.util.HashMap;
import java.util.Map;

import christmas.order.Orders;
import lib.enumeration.Menu;


public class GivenEvent {
    private static final Map<String, Integer> BENEFIT = new HashMap<String, Integer>(){{ put(Menu.CHAMPAGNE.getName(), 25000); }};
    private static final Integer MINIMUM_APPLY_PRICE = 120000;
    public static final String EVENT_NAME = "증정 이벤트";

    public static boolean isApply(Integer totalPrice) {
        if (totalPrice < MINIMUM_APPLY_PRICE || totalPrice > Orders.MAXIMUM_ORDER_PRICE) {
            return false;
        }

        return true;
    }

    public static Map<String, Integer> getBenefit(Integer totalPrice) {
        if (!isApply(totalPrice)) {
            return null;
        }

        return BENEFIT;
    }
    
}
