package com.projeto_engenharia.projeto.endereco;


import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.projeto_engenharia.projeto.user.User;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Table(name = "enderecos", schema = "plataforma_cursos" )
@Entity(name = "enderecos")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Endereco {
	@Id
	@SequenceGenerator(name = "address_seq", sequenceName = "address_sequence", initialValue = 100000, allocationSize = 20)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator =  "address_seq")
	private Long id;
	private String cep;
	private String logradouro;
	private String bairro;
	private String cidade;
	private String uf;
	private String complemento;
	private String numero;
    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
	private User user;
    
    public Endereco (EnderecoRequestDTO endereco, User user) {
    	this.logradouro = endereco.logradouro();
    	this.cep = endereco.cep();
    	this.cidade = endereco.cidade();
    	this.uf = endereco.uf();
    	this.bairro = endereco.bairro();
    	this.complemento = endereco.complemento();
    	this.numero = endereco.numero();
    	this.user = user;
    }
    
    

}
