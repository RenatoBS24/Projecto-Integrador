package com.chichos_snack_project.service;

import com.chichos_snack_project.dao.CustomerDAOImpl;
import com.chichos_snack_project.model.Customer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CustomerServiceTest {

    @Mock
    private CustomerDAOImpl customerDAO;

    @InjectMocks
    private CustomerService customerService;


    @BeforeEach
    void setUp() {
        java.sql.Date date = new java.sql.Date(System.currentTimeMillis());
        Customer customer = new Customer();
        customer.setId_customer(1);
        customer.setName("Renato");
        customer.setPhone("98765431");
        customer.setAmount_total(200);
        customer.setAmount_available(100);
        customer.setAmount_used(100);
        customer.setDate_register(date);
        List<Customer> customerList = new ArrayList<>();
        customerList.add(customer);
        when(customerService.getCustomer()).thenReturn(customerList);
        MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void tearDown() {

    }

    @Test
    void getCustomer() throws SQLException {
        ResultSet mockResultSet = mock(ResultSet.class);

        // Configura el comportamiento del ResultSet simulado
        when(mockResultSet.next()).thenReturn(true).thenReturn(false); // Primera llamada devuelve true, segunda false
        when(mockResultSet.getInt(1)).thenReturn(1); // ID
        when(mockResultSet.getString(2)).thenReturn("Nombre Prueba"); // Nombre
        when(mockResultSet.getString(3)).thenReturn("Apellido Prueba"); // Apellido
        when(mockResultSet.getDate(4)).thenReturn(new java.sql.Date(System.currentTimeMillis())); // Fecha de registro
        when(mockResultSet.getDouble(5)).thenReturn(10.0); // Monto de compra
        when(mockResultSet.getDouble(6)).thenReturn(5.0); // Descuento
        when(mockResultSet.getDouble(7)).thenReturn(15.0); // Total
        when(mockResultSet.getInt(8)).thenReturn(1); // Estado

        // Configura el DAO para que devuelva el ResultSet simulado
        when(customerDAO.findAll()).thenReturn(mockResultSet);

        // Ejecuta el método
        List<Customer> customers = customerService.getCustomer();

        // Verifica los resultados
        assertEquals(1, customers.size());
        assertEquals("Nombre Prueba", customers.get(0).getName());


        // Verifica que el método findAll haya sido llamado en el DAO
        verify(customerDAO, times(1)).findAll();
    }

    @Test
    void create() {
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }
}