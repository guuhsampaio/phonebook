package org.phonebook.contato;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class ContatoService {

    @Autowired
    ContatoRepository contatoRepository;

    public List<Contato> listAll() {
        List<Contato> contatos = new ArrayList<>();
        contatos.addAll(contatoRepository.findAllContatoFavoritos());
        contatos.addAll(contatoRepository.findAllContato());
        contatos.addAll(contatoRepository.findAllContatoInativo());
        return contatos;
    }

    public Contato findById(@RequestBody Integer id){
        return contatoRepository.findByIdContato(id);
    }

    public List<Contato> findByAnything(@RequestBody String anything) {
        List<Contato> contatos = new ArrayList<>();
        contatos.addAll(contatoRepository.findByNome(anything));
        contatos.addAll(contatoRepository.findByCelular(anything));
        contatos.addAll(contatoRepository.findByTelefone(anything));
        contatos.addAll(contatoRepository.findByEmail(anything));

        Set<Contato> s = new HashSet<Contato>(contatos);
        contatos = new ArrayList<Contato>(s);

        return contatos;
    }

    public void favoriteById(Integer id){
        LocalDateTime updatedAt = LocalDateTime.now();
        contatoRepository.findById(id).ifPresent(contato -> {
            String favorito = contato.getFavorito();
            if(favorito.equals("0")) {
                contatoRepository.favoriteById(id, updatedAt);
            } else {
                contatoRepository.disFavoriteById(id, updatedAt);
            }
        });
    }

    public void activeById(Integer id){
        LocalDateTime updatedAt = LocalDateTime.now();
        contatoRepository.findById(id).ifPresent(contato -> {
            String ativo = contato.getAtivo();
            if(ativo.equals("0")) {
                contatoRepository.activeById(id, updatedAt);
            } else {
                contatoRepository.inactiveById(id, updatedAt);
            }
        });
    }

    public Contato save(Contato contato) {
        List<Contato> contatos = new ArrayList<>();
        contatos.addAll(contatoRepository.findByCelular(contato.getCelular()));
        if(!contatos.isEmpty()) {
            throw new RuntimeException("Celular já cadastrado.");
        }
        contatos.addAll(contatoRepository.findByTelefone(contato.getTelefone()));
        if(!contatos.isEmpty()) {
            throw new RuntimeException("Telefone já cadastrado.");
        }
        contatos.addAll(contatoRepository.findByEmail(contato.getEmail()));
        if(!contatos.isEmpty()) {
            throw new RuntimeException("Email já cadastrado.");
        }

        return contatoRepository.save(contato);

    }

    public Contato update(Contato contato){
        Contato oldContato = findById(contato.getId());
        contato.setInserted_at(oldContato.getInserted_at());
        contato.setFavorito(oldContato.getFavorito());
        contato.setAtivo(oldContato.getAtivo());
        contato.setUpdated_at(LocalDateTime.now());
        return contatoRepository.save(contato);
    }

    public void delete(Integer id) {
        LocalDateTime deletedAt = LocalDateTime.now();
        contatoRepository.softDeleteById(id, deletedAt);
    }

}
