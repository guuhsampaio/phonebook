package org.phonebook.contato;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ContatoRepository extends JpaRepository<Contato, Integer> {

    @Query("SELECT c FROM Contato c WHERE c.deletado = '0' AND c.favorito = '1' ORDER BY c.nome")
    public List<Contato> findAllContatoFavoritos();

    @Query("SELECT c FROM Contato c WHERE c.deletado = '0' AND c.favorito = '0' AND c.ativo = '1' ORDER BY c.nome")
    public List<Contato> findAllContato();

    @Query("SELECT c FROM Contato c WHERE c.deletado = '0' AND c.ativo = '0' ORDER BY c.nome")
    public List<Contato> findAllContatoInativo();

    @Query("SELECT c FROM Contato c WHERE c.deletado = '0' AND c.id = :id")
    public Contato findByIdContato(Integer id);

    @Query("SELECT c FROM Contato c WHERE c.deletado = '0' AND UPPER(c.nome) LIKE UPPER(concat('%', :nome, '%'))")
    public List<Contato> findByNome(String nome);

    @Query("SELECT c FROM Contato c WHERE c.deletado = '0' AND c.celular LIKE %:celular%")
    public List<Contato> findByCelular(String celular);

    @Query("SELECT c FROM Contato c WHERE c.deletado = '0' AND c.telefone LIKE %:telefone%")
    public List<Contato> findByTelefone(String telefone);

    @Query("SELECT c FROM Contato c WHERE c.deletado = '0' AND UPPER(c.email) LIKE UPPER(concat('%', :email, '%'))")
    public List<Contato> findByEmail(String email);

    @Transactional
    @Modifying
    @Query("UPDATE Contato c SET c.favorito = '1', c.updated_at = :updatedAt WHERE c.id = :id")
    void favoriteById(Integer id, LocalDateTime updatedAt);

    @Transactional
    @Modifying
    @Query("UPDATE Contato c SET c.favorito = '0', c.updated_at = :updatedAt WHERE c.id = :id")
    void disFavoriteById(Integer id, LocalDateTime updatedAt);

    @Transactional
    @Modifying
    @Query("UPDATE Contato c SET c.ativo = '0', c.favorito = '0', c.updated_at = :updatedAt WHERE c.id = :id")
    void inactiveById(Integer id, LocalDateTime updatedAt);

    @Transactional
    @Modifying
    @Query("UPDATE Contato c SET c.ativo = '1', c.updated_at = :updatedAt WHERE c.id = :id")
    void activeById(Integer id, LocalDateTime updatedAt);

    @Transactional
    @Modifying
    @Query("UPDATE Contato c SET c.deletado = '1', c.deleted_at = :deletedAt WHERE c.id = :id")
    void softDeleteById(Integer id, LocalDateTime deletedAt);

    @Transactional
    @Modifying
    @Query("UPDATE Contato c SET c.deletado = '0', c.updated_at = :updatedAt, c.deleted_at = :deletedAt WHERE c.id = :id")
    void undeleteById(Integer id,LocalDateTime updatedAt, LocalDateTime deletedAt);

}
