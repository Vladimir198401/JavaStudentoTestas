package mano.javatestas;

/**
 * @author
 * by https://www.mkyong.com/java/java-enum-example/
 * by https://docs.oracle.com/javase/tutorial/java/javaOO/enum.html
 * by https://www.geeksforgeeks.org/enum-in-java/
 */
public enum Menu {

	Klausimai("/klausimai", "Klausimai")
	, Atsakymai("/atsakymai", "Ataskaita")
	, Studentai("/studentai", "Studentai");
	
	private final String itemurl;
	private final String naujasPavadinimas;
	
	Menu( String url, String pavadinimas ) {
		this.itemurl = url;
		this.naujasPavadinimas = pavadinimas;
	}
	
	public String itemurl() {
		return this.itemurl;
	}
	public String naujasPavadinimas() {
		return this.naujasPavadinimas;
	}
}