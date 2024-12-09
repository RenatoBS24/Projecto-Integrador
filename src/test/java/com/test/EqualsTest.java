package com.test;
import com.chichos_snack_project.model.*;
import nl.jqno.equalsverifier.EqualsVerifier;
import nl.jqno.equalsverifier.Warning;
import org.junit.jupiter.api.Test;

public class EqualsTest {
   // Test for Product DTO
    @Test
    public void checkEquals(){
        // Strict Check for Student DTO
        EqualsVerifier.simple().forClass(Product.class).verify();
    }

    // Test for User DTO
    @Test
    public void checkEqualsUser(){
        EqualsVerifier.simple().forClass(User.class)
                .suppress(Warning.NONFINAL_FIELDS)
                .suppress(Warning.ALL_FIELDS_SHOULD_BE_USED)
                .verify();
    }

    // Test for Sale DTO
    @Test
    public void checkEqualsSale(){
        EqualsVerifier.simple().forClass(Sale.class)
                .suppress(Warning.NONFINAL_FIELDS)
                .suppress(Warning.ALL_FIELDS_SHOULD_BE_USED)
                .verify();
    }
    // Test for Inventory DTO
    @Test
    public void checkEqualsInventory(){
        EqualsVerifier.simple().forClass(Inventory.class)
                .suppress(Warning.NONFINAL_FIELDS)
                .suppress(Warning.ALL_FIELDS_SHOULD_BE_USED)
                .verify();
    }
    // Test for Notification DTO
    @Test
    public void checkEqualsNotification(){
        EqualsVerifier.simple().forClass(Notification.class)
                .suppress(Warning.NONFINAL_FIELDS)
                .suppress(Warning.ALL_FIELDS_SHOULD_BE_USED)
                .verify();
    }
    // Test for Category DTO
    @Test
    public void checkEqualsCategory(){
        EqualsVerifier.simple().forClass(Category.class)
                .suppress(Warning.NONFINAL_FIELDS)
                .suppress(Warning.ALL_FIELDS_SHOULD_BE_USED)
                .verify();
    }
    // Test for UnitOfMeasurement DTO
    @Test
    public void checkEqualsUnitOfMeasurement(){
        EqualsVerifier.simple().forClass(UnitOfMeasurement.class)
                .suppress(Warning.NONFINAL_FIELDS)
                .suppress(Warning.ALL_FIELDS_SHOULD_BE_USED)
                .verify();
    }
    // Test for Customer DTO
    @Test
    public void checkEqualsCustomer(){
        EqualsVerifier.simple().forClass(Customer.class)
                .suppress(Warning.NONFINAL_FIELDS)
                .suppress(Warning.ALL_FIELDS_SHOULD_BE_USED)
                .verify();
    }
    // Test for Employee DTO
    @Test
    public void checkEqualsEmployee(){
        EqualsVerifier.simple().forClass(Employee.class)
                .suppress(Warning.NONFINAL_FIELDS)
                .suppress(Warning.ALL_FIELDS_SHOULD_BE_USED)
                .verify();
    }
}


