package lib.enumeration;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum Menu {
    YANGSONGEE_SOUP("양송이수프", 6000, MenuType.APPETIZER),
    TAPAS("타파스", 5500, MenuType.APPETIZER),
    CAESAR_SALAD("시저샐러드", 8000, MenuType.APPETIZER),

    T_BONE_STEAK("티본스테이크", 55000, MenuType.MAIN),
    BARBECUE_RIBS("바비큐립", 54000, MenuType.MAIN),
    SEAFOOD_PASTA("해산물파스타", 35000, MenuType.MAIN),
    CHRISTMAS_PASTA("크리스마스파스타", 25000, MenuType.MAIN),

    CHOCOLATE_CAKE("초코케이크", 15000, MenuType.DESSERT),
    ICE_CREAM("아이스크림", 5000, MenuType.DESSERT),

    ZERO_COLA("제로콜라", 3000, MenuType.BEVERAGE),
    RED_WINE("레드와인", 60000, MenuType.BEVERAGE),
    CHAMPAGNE("샴페인", 25000, MenuType.BEVERAGE);

    private final String name;
    private final int price;
    private final MenuType type;

    Menu(String name, int price, MenuType type) {
        this.name = name;
        this.price = price;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public MenuType getType() {
        return type;
    }

    private static final Map<String, Menu> BY_NAME = 
        Stream.of(values()).collect(Collectors.toMap(Menu::getName, Function.identity()));

    public static Menu getMenuOfName(String name) {
        return BY_NAME.get(name);
    }
        
    public static List<Menu> getMenusOfType(MenuType type) {
        return Arrays.stream(Menu.values())
                .filter(menu -> menu.getType() == type)
                .collect(Collectors.toList());
    }

    public static boolean containsMenu(List<Menu> menuList, String itemName) {
        for (Menu menu : menuList) {
            if (menu.getName().equals(itemName)) {
                return true;
            }
        }
        return false;
    }
}
