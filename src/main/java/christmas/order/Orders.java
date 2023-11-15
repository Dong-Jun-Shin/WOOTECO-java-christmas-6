package christmas.order;

import java.util.HashMap;
import java.util.Map;

import christmas.event.DdayEvent;
import christmas.event.GivenEvent;
import christmas.event.SpecialEvent;
import christmas.event.WeekEvent;
import lib.enumeration.Badge;
import lib.enumeration.Menu;
import lib.utils.Validator;

public class Orders {
    public static final String MENU_SEPARATOR = ",";
    public static final String QUANTITY_SEPARATOR = "-";
    public static final int ORDER_MENU_INDEX = 0;
    public static final int ORDER_QUANTITY_INDEX = 1;

    public static final int MINIMUM_ORDER_PRICE = 10000;
    public static final int MAXIMUM_ORDER_PRICE = (55000 * 1) + (60000 * 19);
    public static final int MAXIMUM_ORDER_COUNT = 20;
    public static final int MAXIMUM_ORDER_BENEFITS = 3400 + (2023 * 19) + 1000 + 25000;

    private Integer visitDay;
    private Map<String, Integer> orders;
    private Map<String, Integer> givenBenefit; // TODO: 필요없으면 삭제
    private Map<String, Integer> benefits; 
    private Badge badge;

    public Integer getVisitDay() {
        return visitDay;
    }

    public Map<String, Integer> getOrders() {
        return orders;
    }

    public Map<String, Integer> getGivenBenefit() {
        return givenBenefit;
    }

    public Map<String, Integer> getBenefits() {
        return benefits;
    }
    
    public Badge getBadge() {
        return badge;
    }

    public Integer getTotalPrice() {
        int totalPrice = 0;

        for(Map.Entry<String, Integer> order: orders.entrySet()) {
            Menu menu = Menu.getMenuOfName(order.getKey());

            totalPrice += menu.getPrice() * order.getValue();
        }

        return totalPrice;
    }

    public Integer getTotalDiscount() {
        int totalDiscount = 0;

        for(Integer benefitValue: benefits.values()) {
            totalDiscount += benefitValue;
        }

        return totalDiscount;
    }

    public Integer getTotalBenefits() {
        int totalBenefits = getTotalDiscount();

        if (givenBenefit != null && givenBenefit.size() != 0) {
            totalBenefits += getGivenBenefit().get((Menu.CHAMPAGNE.getName()));
        }

        Validator.validateTotalBenefits(totalBenefits);

        return totalBenefits;
    }

    public Integer getPayPrice() {
        Integer payPrice = getTotalPrice() - getTotalDiscount();

        Validator.validatePayPrice(payPrice);

        return payPrice;
    }

    public void setVisitDay(String input) {
        Validator.validateVisitDay(input);

        this.visitDay = Integer.parseInt(input);
    }

    private void setOrder(Map<String, Integer> orders, String separatedInput) {
        String[] order = separatedInput.split(QUANTITY_SEPARATOR);
        String menu = order[ORDER_MENU_INDEX];
        String quantity = order[ORDER_QUANTITY_INDEX];

        Validator.validateMenu(orders, menu);

        orders.put(menu, Integer.parseInt(quantity));
    }

    public void setOrders(String input) {
        Map<String, Integer> orders = new HashMap<String, Integer>();

        
        Validator.validateOrdersTemplate(input);

        for (String separatedInput: input.split(MENU_SEPARATOR)){
            setOrder(orders, separatedInput);
        }

        Validator.validateRequiredOrders(orders);
        Validator.validateExceedOrder(orders);
        Validator.validateTotalPrice(orders);

        this.orders = orders;
    }

    public void setGivenBenefit() {
        Integer totalPrice = getTotalPrice();

        if (!GivenEvent.isApply(totalPrice)) {
            return;
        };

        this.givenBenefit = GivenEvent.getBenefit(totalPrice);
    }

    public void setBenefits() {
        Map<String, Integer> benefits = new HashMap<String, Integer>();
        
        Integer dDayBenefit = DdayEvent.getBenefit(visitDay);
        Integer weekBenefit = WeekEvent.getBenefit(visitDay, orders);
        Integer specialBenefit = SpecialEvent.getBenefit(visitDay);
        
        if (dDayBenefit != 0) benefits.put(DdayEvent.EVENT_NAME, dDayBenefit);
        if (weekBenefit != 0) benefits.put(WeekEvent.getEventName(visitDay), weekBenefit);
        if (specialBenefit != 0) benefits.put(SpecialEvent.EVENT_NAME, specialBenefit);

        this.benefits = benefits;
    }

    public void setBadge() {
        Integer price = getTotalBenefits();
        Badge badge = Badge.getBadgeOfPrice(price);

        this.badge = badge;
    }
}
