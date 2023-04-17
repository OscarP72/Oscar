package once.curso.proyectobanco.entities;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;

@Data
@Entity
@Table (name="current_accounts")
public class CurrentAccount {
	@Id
	@GeneratedValue (strategy = GenerationType.AUTO)
	private int id;
	private int number;
	
	@Column (name="creation_date")
	@Temporal (TemporalType.DATE)
	private Calendar creationDate;
	
	@ManyToOne
	@JoinColumn (name="fees_id")
	private Fee fee;
	
	@ManyToOne
	@JoinColumn (name="type_accounts_id")
	private TypeAccount typeAccount;
	
	@ManyToOne
	@JoinColumn (name="users_id")
	private User user;

}
