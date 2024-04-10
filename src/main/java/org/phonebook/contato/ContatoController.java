package org.phonebook.contato;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/contato")
public class ContatoController {

    @Autowired
    private ContatoService contatoService;

    @GetMapping
    public ResponseEntity<List<Contato>> findAll() {
        return ResponseEntity.ok(contatoService.listAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Contato> findById(@PathVariable("id") Integer id){
        Contato contato = contatoService.findById(id);
        return ResponseEntity.ok(contato);
    }

    @GetMapping("/pesquisa/{anything}")
    public ResponseEntity<List<Contato>> findByAnything(@PathVariable("anything") String anything){
        return ResponseEntity.ok(contatoService.findByAnything(anything));
    }

    @PatchMapping("/favorite/{id}")
    public ResponseEntity<Void> favoriteById(@PathVariable("id") Integer id){
        contatoService.favoriteById(id);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/active/{id}")
    public ResponseEntity<Void> inactiveById(@PathVariable("id") Integer id){
        contatoService.activeById(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping
    public ResponseEntity<Contato> save(@RequestBody Contato contato){
        return ResponseEntity.status(HttpStatus.CREATED).body(contatoService.save(contato));
    }

    @PutMapping
    public ResponseEntity<Contato> update(@RequestBody Contato contato){
        return ResponseEntity.status(HttpStatus.OK).body(contatoService.update(contato));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id){
        contatoService.delete(id);
        return ResponseEntity.noContent().build();
    }
    @PatchMapping("/undelete/{id}")
    public ResponseEntity<Void> undeleteById(@PathVariable("id") Integer id){
        contatoService.undeleteById(id);
        return ResponseEntity.ok().build();
    }

}
