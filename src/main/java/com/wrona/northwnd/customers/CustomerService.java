package com.wrona.northwnd.customers;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;

    public Clients findAllCustomerByName(String name) {

        try {
            List<CustomerEntity> entities = customerRepository.findAllCustomerByName(name);
            List<ClientResponse> clients = entities.stream()
                    .map(client -> {
                        ClientResponse clientResponse = new ClientResponse();
                        clientResponse.setId(client.getId());
                        clientResponse.setName(client.getName());
                        clientResponse.setSurname(client.getSurname());
                        return clientResponse;
                    })
                    .collect(Collectors.toList());

            Clients response = new Clients();
            response.setClients(clients);

            return response;

        } catch (SQLException ignore) {
        }
        return null;
    }

}
