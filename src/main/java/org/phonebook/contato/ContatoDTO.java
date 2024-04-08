package org.phonebook.contato;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class ContatoDTO {

    private String nome;
    private String email;
    private String celular;
    private String telefone;
    private String favorito;
    private String ativo;

}
