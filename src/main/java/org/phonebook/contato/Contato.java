package org.phonebook.contato;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import java.time.LocalDateTime;
import com.fasterxml.jackson.annotation.JsonFormat;


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

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @CreationTimestamp
    @Column(name = "contato_dh_ins")
    private LocalDateTime inserted_at;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "contato_dh_upd")
    private LocalDateTime updated_at;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
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
