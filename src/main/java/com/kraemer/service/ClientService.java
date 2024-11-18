package com.kraemer.service;

import java.util.List;

import com.kraemer.domain.entities.dto.ClientDTO;
import com.kraemer.domain.entities.enums.EnumDBImpl;
import com.kraemer.domain.usecases.client.CreateClient;
import com.kraemer.domain.usecases.client.DeleteClient;
import com.kraemer.domain.usecases.client.FindAllClients;
import com.kraemer.domain.usecases.client.UpdateClientInfo;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class ClientService extends AbstractService {
    
    @Transactional
    public ClientDTO create(ClientDTO dto, EnumDBImpl dbImpl) {
        var repository = dbFactory.getClientImpl(dbImpl);
        
        var createClient = new CreateClient(repository);

        return createClient.execute(dto);
    }

    public List<ClientDTO> findAll(EnumDBImpl dbImpl) {
        var repository = dbFactory.getClientImpl(dbImpl);

        var findAllClients = new FindAllClients(repository);
       
        return findAllClients.execute();
    }

    @Transactional
    public ClientDTO updateClientInfo(ClientDTO dto, Long id, EnumDBImpl dbImpl) {
        var repository = dbFactory.getClientImpl(dbImpl);

        var updateClientInfo = new UpdateClientInfo(repository);

        return updateClientInfo.execute(dto, id);
    }

    @Transactional
    public void delete(Long id, EnumDBImpl dbImpl) {
        var repository = dbFactory.getClientImpl(dbImpl);
        
        var deleteClient = new DeleteClient(repository);

        deleteClient.execute(id);
    } 

}
