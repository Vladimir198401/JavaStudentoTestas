package mano.javatestas;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Testai {

	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;	
	
	private Integer klausimaiId;

	private Integer atsakymaiId;
	
	private Integer studentaiId;
	
    @ManyToOne()
    @JoinColumn(name="klausimaiId",insertable=false, updatable=false)
    Klausimai klausimai;
    
    @ManyToOne()
    @JoinColumn(name="atsakymaiId",insertable=false, updatable=false)
    Atsakymai atsakymai;    
	
	public Klausimai getKlausimai() {
		return klausimai;
	}

	public void setKlausimai(Klausimai klausimai) {
		this.klausimai = klausimai;
	}

	public Atsakymai getAtsakymai() {
		return atsakymai;
	}

	public void setAtsakymai(Atsakymai atsakymai) {
		this.atsakymai = atsakymai;
	}

	public Testai() {
		
	}

	public Testai(Integer id, Integer klausimaiId, Integer atsakymaiId, Integer studentaiId) {
		super();
		this.id = id;
		this.klausimaiId = klausimaiId;
		this.atsakymaiId = atsakymaiId;
		this.studentaiId = studentaiId;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getKlausimaiId() {
		return klausimaiId;
	}

	public void setKlausimaiId(Integer klausimaiId) {
		this.klausimaiId = klausimaiId;
	}

	public Integer getAtsakymaiId() {
		return atsakymaiId;
	}

	public void setAtsakymaiId(Integer atsakymaiId) {
		this.atsakymaiId = atsakymaiId;
	}

	public Integer getStudentaiId() {
		return studentaiId;
	}

	public void setStudentaiId(Integer studentaiId) {
		this.studentaiId = studentaiId;
	}	
	
	
}
