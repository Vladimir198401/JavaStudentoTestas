package mano.javatestas;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Studentai {

	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;
    private String pav;
    private String data;
    
    //@OneToMany(mappedBy="studentai",cascade=CascadeType.ALL)
    //private List<Produktai_medziagos> produktai_medziagos;    
   
	public Studentai (Integer id, String pav, String data) {
		super();
		this.id = id;
		this.pav = pav;
		this.data = data;
		
	}
	
	public Studentai() {
		
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

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}
}
