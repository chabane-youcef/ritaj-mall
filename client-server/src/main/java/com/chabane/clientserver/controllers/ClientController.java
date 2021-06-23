package com.chabane.clientserver.controllers;

import com.chabane.clientserver.dao.ClientDao;
import com.chabane.clientserver.exceptions.ClientNotFoundException;
import com.chabane.clientserver.models.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class ClientController {
    @Autowired
    private ClientDao clientDao;

    @GetMapping(value = "/clients")
    public List<Client> index() {
        return clientDao.findAll();
    }

    @GetMapping(value = "clients/{id}")
    public Client show(@PathVariable("id") int id) {

        Client client = clientDao.findById(id);

        if (client == null) throw new ClientNotFoundException();

        return client;
    }

    @GetMapping(value = "/clientbyemail/{email}")
    public Client show(@PathVariable("email") String email) {

        Client client = clientDao.findByEmail(email);

        if (client == null) throw new ClientNotFoundException();

        return client;
    }

    @PostMapping(value = "clients")
    @ResponseStatus(HttpStatus.CREATED)
    public Client store(@Valid @RequestBody Client client) {

        return clientDao.save(client);
    }

    @PutMapping(value = "clients/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Client update(@RequestBody Client client) {

        return clientDao.save(client);
    }

    @DeleteMapping(value = "clients/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void destroy(@PathVariable("id") int id) {
        try {
            clientDao.deleteById(id);
        } catch (Exception e) {
            ;
        }
    }
}
