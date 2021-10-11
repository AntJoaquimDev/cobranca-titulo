package com.joaquim.cobranca.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;



import lombok.Data;

@Entity
@Data
public class Titulo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Size(message = "Ddescriçao  naõ pode ter mais de 60 caracteres")
	@NotEmpty(message = "Campo descrição. Preenchemento é obrigatório.")
	private String descricao;
	
	@NotNull(message = "Campo data. Obrigatório")
	@DateTimeFormat(pattern = "dd/MM/yyyy")
   // @Temporal(TemporalType.DATE)
	private String dataVencimento;
	//private Date dataVencimento;
	
	@NotNull(message="Campo valor. Preenchemento é obrigatório")
	@DecimalMin(value = "0.01",message = "Valor não pode ser menor que 0,01 ")
	@NumberFormat(pattern = "#,##0.00")
	private BigDecimal valor;
	
	@Enumerated(EnumType.STRING)
	private StatusTitulo status;

	public boolean isPendente() {
		return StatusTitulo.PENDENTE.equals(this.status);
		
	}
	

}
