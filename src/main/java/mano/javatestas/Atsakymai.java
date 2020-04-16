package mano.javatestas;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Atsakymai {
	
	 @Id
	    @GeneratedValue(strategy=GenerationType.IDENTITY)
	    private Integer id;
	    private String pav;
	    private Integer klausimaiId;	
	    private Integer teisingas;
	    
	    @ManyToOne()
	    @JoinColumn(name="klausimaiId",insertable=false, updatable=false)
	    Klausimai klausimai;  
	    
	   
		public Klausimai getKlausimai() {
			return klausimai;
		}

		public void setKlausimai(Klausimai klausimai) {
			this.klausimai = klausimai;
		}
		
		public Atsakymai( String pav, Integer klausimai_id, Integer teisingas) {
			
			super();
			this.pav = pav;
			this.klausimaiId = klausimai_id;
			this.teisingas = teisingas;
		}		

		public Atsakymai(Integer id, String pav, Integer klausimai_id, Integer teisingas) {
			
			super();
			this.id = id;
			this.pav = pav;
			this.klausimaiId = klausimai_id;
			this.teisingas = teisingas;
		}
		
		public Atsakymai() {
			
		}
		/**
		 * @return the pav
		 */
		
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

		public Integer getKlausimaiId() {
			return klausimaiId;
		}

		public void setKlausimaiId(Integer klausimai_id) {
			this.klausimaiId = klausimai_id;
		}

		public Integer getTeisingas() {
			return teisingas;
		}

		public void setTeisingas(Integer teisingas) {
			this.teisingas = teisingas;
		}

		@Override
		public String toString() {
			return "Atsakymai [id=" + id + ", pav=" + pav + ", klausimaiId=" + klausimaiId + ", teisingas=" + teisingas
					+ "]";
		}
		
}
