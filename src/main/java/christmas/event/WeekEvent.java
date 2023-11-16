package christmas.event;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lib.enumeration.DayType;
import lib.enumeration.Menu;
import lib.enumeration.MenuType;

public class WeekEvent {
    private static final Integer BENEFIT = 2023;
    private static final ArrayList<Integer> WEEKENDS = new ArrayList<>(List.of(1, 2, 8, 9, 15, 16, 22, 23, 29, 30));
    public static final String WEEKDAY_EVENT_NAME = "평일 할인";
    public static final String WEEKEND_EVENT_NAME = "주말 할인";

    public static int getAppliedMenuQuantity(Integer visitDay, Map<String, Integer> orders) {
        Map<DayType, MenuType> benefitType = getBenefitType(visitDay);
        DayType dayType = getDayType(visitDay);
        MenuType menuType = benefitType.get(dayType);
        List<Menu> menus = Menu.getMenusOfType(menuType);
        Integer applyedMenuQuantity = 0;

        for (Map.Entry<String, Integer> order: orders.entrySet()) {
            Menu menu = Menu.getMenuOfName(order.getKey());

            if (menus.contains(menu)) {
                applyedMenuQuantity += order.getValue();
            }
        }

        return applyedMenuQuantity;
    }

    public static DayType getDayType(Integer visitDay) {
        if (WEEKENDS.contains(visitDay)) {
            return DayType.WEEKEND;
        }

        return DayType.WEEKDAY;
    }

    public static Map<DayType, MenuType> getBenefitType(Integer visitDay) {
        DayType dayType = getDayType(visitDay);
        Map<DayType, MenuType> benefit = new HashMap<DayType, MenuType>();

        if (dayType == DayType.WEEKDAY) {
            benefit.put(DayType.WEEKDAY, MenuType.DESSERT);
        }

        if (dayType == DayType.WEEKEND) {
            benefit.put(DayType.WEEKEND, MenuType.MAIN);
        }

        return benefit;
    }

    public static Integer getBenefit(Integer visitDay, Map<String, Integer> orders) {
        Integer appliedMenuQuantity = getAppliedMenuQuantity(visitDay, orders);

        if (appliedMenuQuantity == 0){
            return 0;
        }

        return BENEFIT * appliedMenuQuantity;
    }

    public static String getEventName(Integer visitDay) {
        DayType dayType = getDayType(visitDay);

        if (dayType == DayType.WEEKDAY) {
            return WEEKDAY_EVENT_NAME;
        }

        return WEEKEND_EVENT_NAME;
    }
}
