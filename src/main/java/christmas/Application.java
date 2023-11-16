package christmas;

import christmas.order.Orders;
import christmas.view.InputView;
import christmas.view.OutputView;

public class Application {
    public static void main(String[] args) {
        Orders orders = new Orders();
        InputView inputView = new InputView();
        OutputView outputView = new OutputView();
        
        outputView.viewIntro();

        inputView.enterVisitDay(orders);
        inputView.enterOrders(orders);

        orders.setGivenBenefit();
        orders.setBenefits();
        orders.setBadge();

        outputView.viewStartMessege();
        outputView.viewEventPlanner(orders);
    }
}
