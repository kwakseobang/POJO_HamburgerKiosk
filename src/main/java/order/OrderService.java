package order;


import admin.entity.Admin;
import customer.entity.Customer;
import io.response.InputMessage;

public class OrderService {

    public void buy(Customer customer, Admin admin) {
        System.out.printf(
            (InputMessage.DISPLAY_INTRO.getMessage()) + "%n",
            customer.getCustomerId(),admin.getName()
        );
        Order order = new Order();
        order.buy(customer);
    }

}