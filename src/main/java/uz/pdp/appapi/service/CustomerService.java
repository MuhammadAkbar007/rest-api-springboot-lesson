package uz.pdp.appapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.appapi.entity.Customer;
import uz.pdp.appapi.payload.ApiResponse;
import uz.pdp.appapi.payload.CustomerDto;
import uz.pdp.appapi.repository.CustomerRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {
    @Autowired
    CustomerRepository customerRepository;

    public List<Customer> getAll() {
        return customerRepository.findAll();
    }

    public Customer getCustomerById(Integer id) {
        Optional<Customer> optionalCustomer = customerRepository.findById(id);
        return optionalCustomer.orElse(null);
    }

    public ApiResponse addCustomer(CustomerDto customerDto) {
        if (customerRepository.existsByPhoneNumber(customerDto.getPhoneNumber()))
            return new ApiResponse("Bunday mijoz mavjud !", false);
        Customer customer = new Customer(customerDto.getFullName(), customerDto.getPhoneNumber(), customerDto.getAddress());
        customerRepository.save(customer);
        return new ApiResponse("Mijoz muvaffaqqiyatli saqlandi !", true);
    }

    public ApiResponse editCustomer(Integer id, CustomerDto customerDto) {
        Optional<Customer> optionalCustomer = customerRepository.findById(id);
        if (!optionalCustomer.isPresent()) return new ApiResponse("Mijoz topilmadi !", false);
        if (customerRepository.existsByPhoneNumberAndIdNot(customerDto.getPhoneNumber(), id))
            return new ApiResponse("Bunday telefon raqamli mijoz mavjud !", false);
        Customer customer = optionalCustomer.get();
        customer.setFullName(customerDto.getFullName());
        customer.setAddress(customerDto.getAddress());
        customer.setPhoneNumber(customerDto.getPhoneNumber());
        customerRepository.save(customer);
        return new ApiResponse("Mijoz tahrirlandi !", true);
    }

    public ApiResponse deleteCustomer(Integer id) {
        customerRepository.deleteById(id);
        return new ApiResponse("Mijoz o'chirildi !", true);
    }
}
