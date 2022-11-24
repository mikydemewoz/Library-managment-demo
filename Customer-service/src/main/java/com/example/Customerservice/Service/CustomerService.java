package com.example.Customerservice.Service;

import com.example.Customerservice.Adapter.CustomerAdapter;
import com.example.Customerservice.Dao.CustomerDao;
import com.example.Customerservice.Dto.CustomerMsgSend;
import com.example.Customerservice.Dto.CustomerRequestDto;
import com.example.Customerservice.Dto.CustomerResponseDto;
import com.example.Customerservice.Messaging.Sender;
import com.example.Customerservice.Model.Customer;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.netflix.discovery.converters.Auto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {
    @Autowired
    private CustomerDao customerDao;
    @Autowired
    private Sender sender;
    ObjectMapper mapper = new ObjectMapper();
    private CustomerAdapter customerAdapter = new CustomerAdapter();
    public List<CustomerResponseDto> getAll(){
        List<Customer> customers = customerDao.findAll();
        return customerAdapter.CustomerListToCustomerResponseDto(customers);
    }

    public void add(CustomerRequestDto customerRequestDto) {
        Customer customer = customerAdapter.CustomerRequestToCustomer(customerRequestDto);
        customerDao.save(customer);
    }

    public CustomerResponseDto getOne(String customerId) {
        Customer customer = customerDao.findByCustomerNumber(customerId).get();
        CustomerResponseDto customerResponseDto = customerAdapter.CustomerToCustomerResponseDto(customer);
        return customerResponseDto;
    }

//    Todo: a value object should not change so modification is needed
    public void update(String customerId, CustomerRequestDto customerRequestDto) {
        Customer customer = customerDao.findByCustomerNumber(customerId).get();
        Customer customerFromCustomerDto = customerAdapter.CustomerRequestDtoToCustomer(customerRequestDto);
        customer.setCustomerNumber(
                customerFromCustomerDto.getCustomerNumber()!=null ?
                        customerFromCustomerDto.getCustomerNumber():
                        customer.getCustomerNumber());
        customer.setName(
                customerFromCustomerDto.getName()!=null ?
                        customerFromCustomerDto.getName():
                        customer.getName());
        customer.setContact(
                customerFromCustomerDto.getContact()!=null ?
                        customerFromCustomerDto.getContact():
                        customer.getContact());
        customer.setAddress(customerFromCustomerDto.getAddress()!=null ? customerFromCustomerDto.getAddress(): customer.getAddress());
        customerDao.save(customer);
        String customerStr = null;
        try {

            CustomerMsgSend customerMsgSend = new CustomerMsgSend();
            customerMsgSend.setCustomerNumber(customerId);
            customerMsgSend.setName(customerRequestDto.getName());
            customerStr = mapper.writeValueAsString(customerMsgSend);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        sender.sendToBorrowing(customerStr);

    }

    public void delete(String customerId) {
        customerDao.deleteByCustomerNumber(customerId);
    }
}
