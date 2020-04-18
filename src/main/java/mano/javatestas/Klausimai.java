package mano.javatestas;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
//import javax.persistence.JoinColumn;

@Entity //iesko duomenu bazeje tokios lenteles
public class Klausimai {
	
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
    private Integer nr;
    private String pav;
    private Double verte;
   
	public Klausimai( Integer id, Integer nr, String pav, Double verte) {
		super();
		this.id = id;
		this.nr = nr;
		this.pav = pav;
		this.verte = verte;
		
	}
	
	public Klausimai() {
		
	}
	 
	public Integer getNr() {
		return nr;
	}

	public void setNr(Integer nr) {
		this.nr = nr;
	}

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	/**
	 * @return the pav
	 */
	public String getPav() {
		return pav;
	}

	/**
	 * @param pav the pav to set
	 */
	public void setPav(String pav) {
		this.pav = pav;
	}
	
	public Double getVerte() {
		return verte;
	}

	public void setVerte( Double verte) {
		this.verte = verte;
	}

}
