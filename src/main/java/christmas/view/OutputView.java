package christmas.view;

import java.io.PrintStream;
import java.text.DecimalFormat;
import java.util.Map;

import christmas.event.GivenEvent;
import christmas.order.Orders;
import lib.enumeration.Badge;
import lib.enumeration.Menu;

public class OutputView {
    private final PrintStream printStream = System.out;
    private final String NO_ITEM = "없음";
    private final DecimalFormat DECIMAL_FORMAT = new DecimalFormat("###,###");
    
    public void viewEventPlanner(Orders orders) {
        viewOrders(orders);
        viewTotalPrice(orders);
        viewGivenBenefit(orders);
        viewBenefits(orders);
        viewTotalBenefit(orders);
        viewPayPrice(orders);
        viewBadge(orders);
    }

    public void viewIntro() {
        printStream.println("안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.");
    }

    public void viewStartMessege() {
        printStream.println("12월 26일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!");
        printStream.println("");
    }
    
    public void viewOrders(Orders orders) {
        printStream.println("<주문 메뉴>");

        for (Map.Entry<String, Integer> order: orders.getOrders().entrySet()) {
            printStream.println(order.getKey() + " " + order.getValue() + "개");
        }

        printStream.println("");
    }
    
    public void viewTotalPrice(Orders orders) {
        printStream.println("<할인 전 총주문 금액>");
        printStream.println(DECIMAL_FORMAT.format(orders.getTotalPrice()) + "원");
        printStream.println("");
    }
    
    public void viewGivenBenefit(Orders orders) {
        Map<String, Integer> givenBenefit = orders.getGivenBenefit();

        printStream.println("<증정 메뉴>");
        
        if (givenBenefit == null || givenBenefit.size() == 0) {
            printStream.println(NO_ITEM);
            printStream.println("");
            return;
        }
        
        for(Map.Entry<String, Integer> benefit: givenBenefit.entrySet()) {
            printStream.println(benefit.getKey() + " 1개");
        }

        printStream.println("");
    }
    
    public void viewBenefits(Orders orders) {
        Map<String, Integer> benefits = orders.getBenefits();
        Map<String, Integer> givenBenefit = orders.getGivenBenefit();
        
        printStream.println("<혜택 내역>");

        if (benefits.size() == 0) {
            printStream.println(NO_ITEM);
            printStream.println("");
            return;
        }
        
        for(Map.Entry<String, Integer> benefit: benefits.entrySet()) {
            printStream.println(benefit.getKey() + ": -" + DECIMAL_FORMAT.format(benefit.getValue()) + "원");
        }

        if (givenBenefit != null && givenBenefit.size() != 0) printStream.println(GivenEvent.EVENT_NAME + ": -" + DECIMAL_FORMAT.format(givenBenefit.get(Menu.CHAMPAGNE.getName())) + "원");

        printStream.println("");
    }
}
