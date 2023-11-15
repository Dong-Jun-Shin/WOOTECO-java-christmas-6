package lib.utils;

import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import christmas.error.Error;
import christmas.order.Orders;
import lib.enumeration.Menu;
import lib.enumeration.MenuType;

public class Validator {
    private static boolean validateDayInEventRange(String input) {
        String REGEXP_DAY = "^([1-9]|[1-2][0-9]|[3][0-1])$";

        if (!Pattern.matches(REGEXP_DAY, input)) {
            throw new IllegalArgumentException(Error.invalidDate());
        }

        return true;
    }

    public static void validateVisitDay(String input) {
        if (input == null || input == "") {
            throw new IllegalArgumentException(Error.invalidDate());
        }

        validateDayInEventRange(input);
    }

    private static boolean validateMenuExist(String menuName) {
        Menu menu = Menu.getMenuOfName(menuName);

        if (menu == null) {
            throw new IllegalArgumentException(Error.invalidOrder());
        }

        return true;
    }
    
    private static boolean validateMenuDuplicated(Map<String, Integer> orders, String menu) {
        if (orders.get(menu) != null) {
            throw new IllegalArgumentException(Error.invalidOrder());
        }

        return true;
    }

    public static boolean validateMenu(Map<String, Integer> orders, String menu) {
        validateMenuDuplicated(orders, menu);
        validateMenuExist(menu);

        return true;
    }

    public static boolean validateOrdersTemplate(String input) {
        String REGEXP_MENU_TEMPLATE = "^(([가-힣]+)-([1-9]|[1][0-9]|[2][0]),?){1,}$";
        String REGEXP_COMMA_LAST_TYPING = ".+,$";

        if (input == null || input == "") {
            throw new IllegalArgumentException(Error.invalidOrder());
        }

        if (!Pattern.matches(REGEXP_MENU_TEMPLATE, input)) {
            throw new IllegalArgumentException(Error.invalidOrder());
        }

        if (Pattern.matches(REGEXP_COMMA_LAST_TYPING, input)) {
            throw new IllegalArgumentException(Error.invalidOrder());
        }

        return true;
    }

    public static boolean validateRequiredOrders(Map<String, Integer> orders) {
        List<Menu> appetizerMenus = Menu.getMenusOfType(MenuType.APPETIZER);
        List<Menu> mainMenus = Menu.getMenusOfType(MenuType.MAIN);
        List<Menu> dessertMenus = Menu.getMenusOfType(MenuType.DESSERT);

        for (Map.Entry<String, Integer> order : orders.entrySet()) {
            String orderMenu = order.getKey();

            if (Menu.containsMenu(appetizerMenus, orderMenu)) return true;
            if (Menu.containsMenu(mainMenus, orderMenu)) return true;
            if (Menu.containsMenu(dessertMenus, orderMenu)) return true;
        }

        throw new IllegalArgumentException(Error.invalidOrder());
    }
    
    public static boolean validateExceedOrder(Map<String, Integer> orders) {
        int totalOrderQuantity = orders.values().stream().mapToInt(Integer::intValue).sum();

        if (totalOrderQuantity > Orders.MAXIMUM_ORDER_COUNT) {
            throw new IllegalArgumentException(Error.invalidOrder());
        }

        return true;
    }

    public static boolean validateTotalPrice(Map<String, Integer> orders) {
        int totalPrice = 0;

        for(Map.Entry<String, Integer> order: orders.entrySet()) {
            Menu menu = Menu.getMenuOfName(order.getKey());

            totalPrice += menu.getPrice() * order.getValue();
        }

        if (totalPrice < 0 || totalPrice > Orders.MAXIMUM_ORDER_PRICE) {
            throw new IllegalArgumentException(Error.invalidOrder());
        }

        return true;
    }

    public static boolean validateTotalBenefits(int totalBenefits) {
        if (totalBenefits < 0 || totalBenefits > Orders.MAXIMUM_ORDER_BENEFITS) {
            throw new IllegalArgumentException(Error.invalidBenefits());
        }

        return true;
    }

    public static boolean validatePayPrice(int payPrice) {
        if (payPrice < 0) {
            throw new IllegalArgumentException(Error.invalidOrder());
        }

        return true;
    }
}
