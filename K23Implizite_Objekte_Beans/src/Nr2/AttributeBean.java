package Nr2;

import java.util.Hashtable;

public class AttributeBean
{
	private String name = "";
	private String wert = "";
	private String scope = "";

	private Hashtable<String, String> fehler;

	public AttributeBean() {

	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getWert() {
		return wert;
	}

	public void setWert(String wert) {
		this.wert = wert;
	}

	public String getScope() {
		return scope;
	}

	public void setScope(String scope) {
		this.scope = scope;
	}

	public void validiere() {
		this.fehler = null;
		if (this.name == null || this.name.length() == 0) {
			this.fehler = new Hashtable<String, String>();
			this.fehler.put("name", "Name des Attributes nicht eingegeben");
		}
		
		if (this.wert == null || this.wert.length() == 0) {
			if(this.fehler == null) {
				this.fehler = new Hashtable<String, String>();
			}
			this.fehler.put("wert", "Wert des Attributes nicht eingegeben");
		}
		
		if (this.scope == null || this.scope.length() == 0) {
			if(this.fehler == null) {
				this.fehler = new Hashtable<String, String>();
			}
			this.fehler.put("scope", "Scope muss entweder Session oder Application sein");
		}
	}
	
	public void valdel() {
		this.fehler = null;
		if (this.name == null || this.name.length() == 0) {
			this.fehler = new Hashtable<String, String>();
			this.fehler.put("name", "Name des Attributes nicht eingegeben");
		}
		
		if (this.scope == null || this.scope.length() == 0) {
			if(this.fehler == null) {
				this.fehler = new Hashtable<String, String>();
			}
			this.fehler.put("scope", "Scope muss entweder Session oder Application sein");
		}
	}

	public void setFehler(Hashtable<String, String> fehler) {
		this.fehler = fehler;
	}

	public Hashtable<String, String> getFehler() {
		return this.fehler;
	}
}
