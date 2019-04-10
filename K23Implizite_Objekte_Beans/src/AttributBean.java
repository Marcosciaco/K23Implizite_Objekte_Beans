import java.util.Hashtable;

public class AttributBean
{
	protected String name = "";
	protected String wert = "";
	protected String scope = "";
	
	protected Hashtable<String,String> fehler = null;
	
	public void validiere() {
		this.fehler = null;
		if(this.name == null || this.name.length() == 0) {
			this.fehler = new Hashtable<String,String>();
			this.fehler.put("name", "Name des Attributs nicht eingegeben");
		}
		if(this.wert == null || this.wert.length() == 0) {
			this.fehler = new Hashtable<String,String>();
			this.fehler.put("wert", "Wert des Attributs nicht eingegeben");
		}
		if(this.scope == null || this.scope.length() == 0) {
			this.fehler = new Hashtable<String,String>();
			this.fehler.put("scope", "Scope des Attributs nicht eingegeben");
		}
	}
	
	public Hashtable<String,String> getFehler() {
		return this.fehler;
	}
}
