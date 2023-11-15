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
}
