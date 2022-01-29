package br.com.mobit.cadastroCliente.service;

import br.com.mobit.cadastroCliente.model.domain.Cliente;
import br.com.mobit.cadastroCliente.model.domain.Endereco;
import br.com.mobit.cadastroCliente.repository.ClienteRepository;
import br.com.mobit.cadastroCliente.repository.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private EnderecoRepository enderecoRepository;

    public Cliente criar(final Cliente cliente) {
        Endereco enderecoCLiente = enderecoRepository.findById(cliente.getEndereco().getId())
                .orElseThrow(() -> new EntityNotFoundException("Endereço não encontrado."));
        cliente.setEndereco(enderecoCLiente);
        enderecoCLiente.setCliente(cliente);
        return clienteRepository.save(cliente);
    }

    public Cliente obter(final Long id) {
        return clienteRepository.findById(id).orElseThrow(() ->  new EntityNotFoundException("Funcionário não encontrado."));
    }

    public List<Cliente> listar() {
        List<Cliente> clientes = clienteRepository.findAll();
        return clientes;
    }

    public Cliente atualizar(final Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    public void deletar(final Long id) {
        clienteRepository.deleteById(id);
    }
}
