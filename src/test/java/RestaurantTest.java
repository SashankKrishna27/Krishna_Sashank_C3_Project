import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

import java.time.temporal.ChronoUnit;

class RestaurantTest {
    //REFACTOR ALL THE REPEATED LINES OF CODE
    Restaurant restaurant = new Restaurant("Amelie's cafe","Chennai", LocalTime.parse("10:30:00"), LocalTime.parse("22:00:00"));
    //>>>>>>>>>>>>>>>>>>>>>>>>>OPEN/CLOSED<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    //-------FOR THE 2 TESTS BELOW, YOU MAY USE THE CONCEPT OF MOCKING, IF YOU RUN INTO ANY TROUBLE
    @Test
    public void is_restaurant_open_should_return_true_if_time_is_between_opening_and_closing_time(){
        restaurant.openingTime = LocalTime.now();
        restaurant.closingTime = LocalTime.now().plus(11, ChronoUnit.MINUTES);
        boolean status = restaurant.isRestaurantOpen();
        assertTrue(status);
    }

    @Test
    public void is_restaurant_open_should_return_false_if_time_is_outside_opening_and_closing_time(){
        boolean status = restaurant.isRestaurantOpen();
        assertFalse(status);
    }

    public void addMenuItems(){
        restaurant.addToMenu("Sweet corn soup",119);
        restaurant.addToMenu("Vegetable lasagne", 269);
    }

    //>>>>>>>>>>>>>>>>>>>>>>>>>>>MENU<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    @Test
    public void adding_item_to_menu_should_increase_menu_size_by_1(){
        LocalTime openingTime = LocalTime.parse("10:30:00");
        LocalTime closingTime = LocalTime.parse("22:00:00");
        restaurant =new Restaurant("Amelie's cafe","Chennai",openingTime,closingTime);
        addMenuItems();

        int initialMenuSize = restaurant.getMenu().size();
        restaurant.addToMenu("Sizzling brownie",319);
        assertEquals(initialMenuSize+1,restaurant.getMenu().size());
    }
    @Test
    public void removing_item_from_menu_should_decrease_menu_size_by_1() throws itemNotFoundException {
        LocalTime openingTime = LocalTime.parse("10:30:00");
        LocalTime closingTime = LocalTime.parse("22:00:00");
        restaurant =new Restaurant("Amelie's cafe","Chennai",openingTime,closingTime);
        addMenuItems();

        int initialMenuSize = restaurant.getMenu().size();
        restaurant.removeFromMenu("Vegetable lasagne");
        assertEquals(initialMenuSize-1,restaurant.getMenu().size());
    }
    @Test
    public void removing_item_that_does_not_exist_should_throw_exception() {
        LocalTime openingTime = LocalTime.parse("10:30:00");
        LocalTime closingTime = LocalTime.parse("22:00:00");
        restaurant =new Restaurant("Amelie's cafe","Chennai",openingTime,closingTime);
        addMenuItems();

        assertThrows(itemNotFoundException.class,
                ()->restaurant.removeFromMenu("French fries"));
    }
    //<<<<<<<<<<<<<<<<<<<<<<<MENU>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

    @Test
    public void selected_item_in_menu_should_return_value_more_than_0() {
        addMenuItems();
        restaurant.addToOrderList("Sweet corn soup");
        restaurant.addToOrderList("Milk shake");
        assertTrue(restaurant.getItemPrice("Sweet corn soup") > 0);
    }

    @Test
    public void total_value_must_be_greater_than_0_if_number_of_items_added_are_more_than_one() {
        restaurant.addToOrderList("Sweet corn soup");
        assertTrue(restaurant.getItemPrice("Sweet corn soup") > 0);
    }

    @Test
    public void item_price_must_be_greater_than_0_if_item_found() {
        restaurant.addToOrderList("Sweet corn soup");
        assertTrue(restaurant.getItemPrice("Milk shake") > 0);
    }
}