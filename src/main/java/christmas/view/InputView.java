package christmas.view;

import java.io.PrintStream;

import camp.nextstep.edu.missionutils.Console;
import christmas.order.Orders;

public class InputView {
    private final PrintStream printStream = System.out;

    public void enterVisitDay(Orders orders) {
        while (true) {
            try {
                printStream.println("12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)");
                String input = Console.readLine();
                
                orders.setVisitDay(input);
                break;
            } catch (IllegalArgumentException e) {
                printStream.println(e.getMessage());
                continue;
            }
        }
    }

    public void enterOrders(Orders orders) {
        while (true) {
            try {
                printStream.println("주문하실 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)");
                String input = Console.readLine();

                orders.setOrders(input);
                break;
            } catch (IllegalArgumentException e) {
                printStream.println(e.getMessage());
                continue;
            }
        }
    }
}
