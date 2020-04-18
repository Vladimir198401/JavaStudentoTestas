package mano.javatestas;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import mano.javatestas.Menu;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import javax.persistence.EntityManagerFactory;

@Controller
public class PageController {
	
	@Autowired // This means to get the bean called userRepository
    // Which is auto-generated by Spring, we will use it to handle the data
	private KlausimaiRepository klausimai_rep;
	
	@Autowired // This means to get the bean called userRepository
    // Which is auto-generated by Spring, we will use it to handle the data	
	private AtsakymaiRepository atsakymai_rep;
	
	@Autowired // This means to get the bean called userRepository
     //Which is auto-generated by Spring, we will use it to handle the data	
	private StudentaiRepository studentai_rep;

	@Autowired // This means to get the bean called userRepository
    //Which is auto-generated by Spring, we will use it to handle the data	
	private TestaiRepository testai_rep;
		
	/*
	@Autowired // This means to get the bean called userRepository
    // Which is auto-generated by Spring, we will use it to handle the data	
	private AtaskaitaRepository testai_rep;
	*/
	
	@Autowired 
	EntityManagerFactory factory;

	// @Bean
	public SessionFactory sessionFactory() {
		
	        if (factory.unwrap(SessionFactory.class) == null) {
	            throw new NullPointerException("factory is not a hibernate factory");
	        }
	        return factory.unwrap(SessionFactory.class);
	}
	
	@RequestMapping(path="/", method={ RequestMethod.GET, RequestMethod.POST })
    public String index(Model model) {
        
	
        model.addAttribute("lst_menu", Menu.values() ); 
        
        return "index";
    }

	@RequestMapping(path="/klausimai", method={ RequestMethod.GET, RequestMethod.POST })
    public String klausimai(
    		@RequestParam(name="id", required=false, defaultValue="") Integer id
    		,@RequestParam(name="nr", required=false, defaultValue="") Integer nr
    		, @RequestParam(name="pav", required=false, defaultValue="") String pav
    		, @RequestParam(name="verte", required=false, defaultValue="0") Double verte
    		, @RequestParam(name="veiksmas", required=false, defaultValue="neveikti") String veiksmas
    		, @RequestParam(name="salinti", required=false, defaultValue="nesalinti") String salinti
    		, Model model 
    	) {
       
        if ( veiksmas.equals("papildyti") ) {
        	
        	List<Klausimai> lst_klausas = klausimai_rep.findByPav(pav);
        	
        	if ( lst_klausas.size() > 0 ) {
        		
        		
        	} else {
        		
	        	Klausimai klausimas = new Klausimai( id, nr, pav, verte );
	        	klausimai_rep.save( klausimas );	        	
        	}
        }
        
        if ( veiksmas.equals("pakeisti") ) {
        	
        	Klausimai klausimas = new Klausimai();
        	Optional <Klausimai> found = klausimai_rep.findById( id );
        	
        	if ( found.isPresent() ) {
    			
        		klausimas = found.get();
        		klausimas.setId(id);
        		klausimas.setNr(nr);
        		klausimas.setPav(pav);
        		klausimas.setVerte(verte);
        		klausimai_rep.save(klausimas);
        	}

        }
        if ( salinti.equals("pasalinti") ) {
        	
        	Optional <Klausimai> found = klausimai_rep.findById( id );
        	
        	if ( found.isPresent() ) {
    			
        		klausimai_rep.deleteById(id);
 		
 			}
        }
        model.addAttribute( "lst_menu", Menu.values() ); 
        model.addAttribute( "lst", klausimai_rep.findAll() );
        
        
        return "klausimai";
    }

	@RequestMapping(path="/klausimas", method={ RequestMethod.GET, RequestMethod.POST })
    public String klausimas(
    		@RequestParam(name="id", required=false, defaultValue="") Integer id
    		,@RequestParam(name="nr", required=false, defaultValue="") Integer nr
    		, @RequestParam(name="id_kl", required=false, defaultValue="") Integer id_kl    		
    		, @RequestParam(name="pav", required=false, defaultValue="") String pav
    		, @RequestParam(name="teisingas", required=false, defaultValue="0") Integer teisingas
    		, @RequestParam(name="veiksmas", required=false, defaultValue="neveikti") String veiksmas
    		, @RequestParam(name="salinti", required=false, defaultValue="nesalinti") String salinti
    		, Model model 
    	) {
       
        if ( veiksmas.equals("papildyti") ) {
        	
        	List<Atsakymai> lst_ats = atsakymai_rep.findByPav(pav);
        	
        	if ( lst_ats.size() > 0 ) {
        		
        		
        	} else {
        		
	        	Atsakymai atsakymas = new Atsakymai( id, pav, id_kl, teisingas );
	        	atsakymai_rep.save( atsakymas );	        	
        	}
        }
        
        if ( veiksmas.equals( "pakeisti" ) ) {
        	
        	Atsakymai atsakymas = new Atsakymai();
        	Optional <Atsakymai> found = atsakymai_rep.findById( id );
        	
        	if ( found.isPresent() ) {
    			
        		atsakymas = found.get();
        		atsakymas.setId(id);
        		atsakymas.setPav(pav);
        		atsakymas.setKlausimaiId(id_kl);
        		atsakymas.setTeisingas(teisingas);
        		atsakymai_rep.save(atsakymas);
        	}
        }
        
        if ( salinti.equals("pasalinti") ) {
        	
        	Optional <Atsakymai> found = atsakymai_rep.findById( id );
        	
        	if ( found.isPresent() ) {
    			
        		atsakymai_rep.deleteById(id);
 			}
        } 
        
        model.addAttribute( "lst_menu", Menu.values() ); 
        model.addAttribute( "lst", atsakymai_rep.findByKlausimaiId( id_kl ) );
        
        return "klausimas";
    }
	
	@RequestMapping(path="/testo-rezultatai", method={ RequestMethod.GET })
    public String testoRezultatai(
    		

    		 @RequestParam(name="studentai_id", required=false, defaultValue="0") Integer studentai_id

    		, Model model 
    	) {
		
		ArrayList<Testai>  studento_testas =  testai_rep.findByStudentaiId( studentai_id );
	
		Integer klaus_id = 0; 
		Double klausimo_balas = 0.0;
//		Integer atsakymu_viso = 0;
		Integer atsakymu_teisingu = 0;
		Integer parinkta_teisingu =0;
		Double balas = 0.0;
		Double balu_galimu = 0.0;
		
		//Traversing list through Iterator  
		Iterator<Testai> itr = studento_testas.iterator();  
		  
		while ( itr.hasNext() ) {  
			  
			Testai atsakymas = itr.next();
			
			Integer id_klausimo = atsakymas.getKlausimai().getId();
			
			if ( id_klausimo !=  klaus_id ) {
				
				if ( klaus_id != 0 ) {
					
					if ( atsakymu_teisingu > 0 ) {
						
						balas += klausimo_balas * ( parinkta_teisingu / atsakymu_teisingu );
					}
					klausimo_balas = 0.0;
					// Integer atsakymu_viso = 0;
					atsakymu_teisingu = 0;
					parinkta_teisingu =0;
				}
				klaus_id = id_klausimo;
				
				ArrayList<Atsakymai>  klausimo_atsakymai = ( ArrayList<Atsakymai> ) atsakymai_rep.findByKlausimaiId( id_klausimo );	
//				atsakymu_viso = klausimo_atsakymai.size();

				Iterator<Atsakymai> itr_kl_ats = klausimo_atsakymai.iterator();  
				  
				while ( itr_kl_ats.hasNext() ) {
					
					Atsakymai klausimo_atsakymas = itr_kl_ats.next();
					
					if ( klausimo_atsakymas.getTeisingas() == 1 ) {
						
						atsakymu_teisingu++;
					}
				}
			
				klausimo_balas = atsakymas.getKlausimai().getVerte();
				balu_galimu += klausimo_balas;
			}
			
			if ( atsakymas.getAtsakymai().getTeisingas() == 1 ) {
				
				parinkta_teisingu++;
			}			
		}
		if ( atsakymu_teisingu > 0 ) {
			
			balas += klausimo_balas * ( parinkta_teisingu / atsakymu_teisingu );
		}
		
		model.addAttribute( "balas", balas);
		model.addAttribute( "balu", balu_galimu);		
        model.addAttribute( "lst_menu", Menu.values() ); 		
        model.addAttribute( "stud_testas", studento_testas );
		return "testo_rezultatai";
	}
	
	@RequestMapping(path="/klausimo-atsakymai", method={ RequestMethod.GET, RequestMethod.POST })
    public String klausimoAtsakymai(
    		
    		@RequestParam(name="klausimai_nr", required=false, defaultValue="0") Integer klausimai_nr
    		, @RequestParam(name="studentai_id", required=false, defaultValue="0") Integer studentai_id
    		, @RequestParam(name="pasirinkti[]", required=false, defaultValue="0") Integer[] pasirinkti
    		, @RequestParam(name="veiksmas", required=false, defaultValue="neveikti") String veiksmas
    		, Model model 
    	) {
		
		String kur_po_to_arba_sablonas = "testo_klausimas";
		
		ArrayList<Klausimai> klausimai = ( ArrayList<Klausimai> ) klausimai_rep.findAll();
		
		Klausimai klausimas = klausimai_rep.findAllByNr( klausimai_nr );
       
        if ( veiksmas.equals("atsakyti") ) {
        	
        	for (int i=0; i < pasirinkti.length; i++ ) { 
        		
        		System.out.println( i + ": " + pasirinkti [ i ] );
        		
	        	Testai testas = new Testai( 0, klausimas.getId(), pasirinkti [ i ], studentai_id );
	        	testai_rep.save( testas );	  
        	}
        	
        	if ( klausimai_nr == klausimai.size() ) {
        		
        		kur_po_to_arba_sablonas = "redirect:/testo-rezultatai?studentai_id=" + studentai_id;
        		
        	} else {
        		
        		kur_po_to_arba_sablonas = "redirect:/klausimo-atsakymai?studentai_id=" + studentai_id + "&klausimai_nr=" + ( klausimai_nr + 1 );
        	}
        }
        
        model.addAttribute( "klausimas", klausimas  );
        model.addAttribute( "lst_menu", Menu.values() ); 
        model.addAttribute( "lst", atsakymai_rep.findByKlausimaiId( klausimas.getId() ) );
        
        
        return kur_po_to_arba_sablonas;
    }	
	
	@RequestMapping(path="/atsakymai", method={ RequestMethod.GET, RequestMethod.POST })
    public String atsakymai(
    		@RequestParam(name="id", required=false, defaultValue="") Integer id
    		, @RequestParam(name="pav", required=false, defaultValue="") String pav
    		, @RequestParam(name="klausimai_id", required=false, defaultValue="") Integer klausimai_id
    		, @RequestParam(name="teisingas", required=false, defaultValue="0") Integer teisingas
    		, @RequestParam(name="veiksmas", required=false, defaultValue="neveikti") String veiksmas
    		, @RequestParam(name="salinti", required=false, defaultValue="nesalinti") String salinti
    		, Model model 
    	) {
       
        if ( veiksmas.equals("papildyti") ) {
        	
        	List<Klausimai> lst_klausas = klausimai_rep.findByPav(pav);
        	
        	if ( lst_klausas.size() > 0 ) {
        		
        		
        	} else {
        		
	        	Atsakymai atsakymas = new Atsakymai( id, pav, klausimai_id, teisingas );
	        	atsakymai_rep.save( atsakymas );	        	
        	}
        }
        
        if ( veiksmas.equals("pakeisti") ) {
        	
        	Atsakymai atsakymas = new Atsakymai();
        	Optional <Atsakymai> found = atsakymai_rep.findById( id );
        	
        	if ( found.isPresent() ) {
    			
        		atsakymas = found.get();
        		atsakymas.setId(id);
        		atsakymas.setPav(pav);
        		atsakymas.setKlausimaiId(klausimai_id);
        		atsakymas.setTeisingas(teisingas);
        		atsakymai_rep.save(atsakymas);
        	}

        }
        if ( salinti.equals("pasalinti") ) {
        	
        	Optional <Atsakymai> found = atsakymai_rep.findById( id );
        	
        	if ( found.isPresent() ) {
    			
        		atsakymai_rep.deleteById(id);
 		
 			}
        }
        
        
        model.addAttribute( "lst_menu", Menu.values() ); 
        model.addAttribute( "lst", atsakymai_rep.findAll() );
        
        
        return "atsakymai";
    }
	
	@RequestMapping(path="/studentai", method={ RequestMethod.GET, RequestMethod.POST })
    public String studentai(
    		@RequestParam(name="id", required=false, defaultValue="") Integer id
    		, @RequestParam(name="pav", required=false, defaultValue="") String pav
    		, @RequestParam(name="data", required=false, defaultValue="") String data
    		, @RequestParam(name="veiksmas", required=false, defaultValue="neveikti") String veiksmas
    		, @RequestParam(name="salinti", required=false, defaultValue="nesalinti") String salinti
    		, Model model 
    	) {
       
        if ( veiksmas.equals("papildyti") ) {
        	
        	List<Studentai> lst_prod = studentai_rep.findByPav(pav);
        	
        	if ( lst_prod.size() > 0 ) {
        		
        	}else {
        		  
	        	Studentai studentai = new Studentai( id, pav, data );
	        	studentai_rep.save( studentai );
        	 
        	}
        }
        if ( veiksmas.equals("pakeisti") ) {
        	
        	Studentai studentai = new Studentai();// id, pav;
        	Optional <Studentai> found = studentai_rep.findById( id );
        	
        	if ( found.isPresent() ) {
    			
        		studentai = found.get();
        		studentai.setId(id);
        		studentai.setPav(pav);
        		studentai.setData(data);
        		studentai_rep.save(studentai);
        	}

        }
        if ( salinti.equals("pasalinti") ) {
        	
        	Optional <Studentai> found = studentai_rep.findById( id );
        	
        	if ( found.isPresent() ) {
    			 
        		studentai_rep.deleteById(id);
 		
 			}
        } 
        model.addAttribute( "lst_menu", Menu.values() ); 
        model.addAttribute( "lst", studentai_rep.findAll() );
        
        
        return "studentai";
    }

}
