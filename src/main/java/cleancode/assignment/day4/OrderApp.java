package cleancode.assignment.day4;

import cleancode.assignment.day4.message.errorMessage.OrderErrorMessage;
import lombok.extern.log4j.Log4j2;


@Log4j2
public class OrderApp {

    public boolean validateOrder(Order order) {
        if (order.emptyOrderItems()) {
            log.info(OrderErrorMessage.NO_ORDER_ITEMS);
            return false;
        }

        if (order.userInfoNotFound()) {
            log.info(OrderErrorMessage.NO_CUSTOMER_INFO);
            return false;
        }

        if (order.isOverTotalPriceZero()) {
            log.info(OrderErrorMessage.INVALID_TOTAL_PRICE);
            return false;
        }

        return true;
    }
}