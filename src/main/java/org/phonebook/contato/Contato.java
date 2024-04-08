package org.phonebook.contato;

import jakarta.persistence.*;
import lombok.Data;
import org.antlr.v4.runtime.misc.NotNull;
import org.hibernate.annotations.CreationTimestamp;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "contato")
public class Contato {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "contato_id")
    private Integer id;

    @Column(name = "contato_nome")
    private String  nome;

    @Column(name = "contato_email")
    private String email;

    @Column(name = "contato_celular")
    private String celular;

    @Column(name = "contato_telefone")
    private String telefone;

    @Column(name = "contato_sn_favorito")
    private String favorito = "0";

    @Column(name = "contato_sn_ativo")
    private String ativo = "1";

    @Column(name = "contato_sn_deletado")
    private String deletado = "0";

    @CreationTimestamp
    @Column(name = "contato_dh_ins")
    private LocalDateTime inserted_at;

    @Column(name = "contato_dh_upd")
    private LocalDateTime updated_at;

    @Column(name = "contato_dh_del")
    private LocalDateTime deleted_at;

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Contato)
        {
            Contato temp = (Contato) obj;
            if(this.id.equals(temp.id))
                return true;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return (this.id.hashCode());
    }

}
