package Rohdateien;

import java.io.Serializable;
import java.text.DateFormat;
import java.util.Date;
import java.util.Hashtable;

/**
 * Diese Klasse kann eine einzelne Meldung mit Datum, Verfasser, Titel, Meldungstext 
 * und Nummer aufnehmen. Sie enth�lt die Getter- und Setter-Methoden f�r 
 * folgende Eigenschaften: datum, verfasser, titel, text, nummer und fehler.
 * Weiters enth�lt sie die Methoden getKurzTitel und getKurzText welche
 * einen auf MAXLAENGE bzw. MAXLAENGE * 2 gek�rzten Titel bzw. Text zur�ckliefern.
 * <br><br>
 * Diese Klasse muss das Interface Serializable implementieren, ansonsten kann
 * das Objekt nicht in eine Datei geschrieben werden.
 * <br><br>
 * Die Klasse enth�lt die Methode validiere, welche kontrolliert, ob die Eingaben
 * korrekt sind und Fehler in die Eigenschaft fehler vom Typ Hashtable schreibt.
 * Ist die Meldung korrekt, so erh�lt die Meldung den Zeitstempel (datum).
 * <br><br>
 * Die Bean enth�lt eine Nummer. Diese gibt die Position der Meldung in jenem
 * Meldungsvektor wieder, in welchem die Meldung eingetragen wurde. Wurde die Meldung
 * noch nicht eingetragen, ist diese Nummer negativ
 * @author Michael Wild
 */
public class MeldungsBean implements Serializable
{
  /**
   * private Klassenkonstante, welche die gek�rzte L�nge eines Titels bzw. 
   * Meldungstextes angibt
   */
  private static final int MAXLAENGE = 90;
  
  /*
   * Membervariablen
   */
  /**
   * Das Datum der Meldung an dem diese ver�ffentlicht wurde
   */
  protected Date datum = null;
  /**
   * Der Benutzername des Verfassers der meldung
   */
  protected String verfasser = null;
  /**
   * Der Titel der Meldung
   */
  protected String titel = null;
  /**
   * Der Text der Meldung
   */
  protected String text = null;
  /**
   * Die Nummer der Meldung. Diese gibt die Postion der Meldung im Meldungsvektor
   * wieder. Wurde die Meldung noch nicht ver�ffentlicht, so ist die Nummer 
   * negativ
   */
  protected int nummer = -1;
  /**
   * Eine Hashtable welche eventuell aufgetretene Fehler in der Meldung enth�lt
   */
  protected Hashtable<String,String> fehler = null;

  /*
   * Getter- und Settermethoden
   */
  public String getDatum() {
    return this.datum == null ? "" : DateFormat.getDateInstance().format(this.datum)
      + " " + DateFormat.getTimeInstance(2).format(this.datum);
  }
  public String getVerfasser() {
  	return this.verfasser == null ? "" : this.verfasser;
  }
  public void setVerfasser(String verfasser) {
  	this.verfasser = verfasser;
  }
  public String getTitel() {
    return this.titel == null ? "" : this.titel;
  }
  public String getKurzTitel() {
    return this.titel == null ? "" : kuerzen(this.titel,0);
  }
  public void setTitel(String titel) {
    this.titel = titel;
  }
  public String getText() {
    return this.text == null ? "" : this.text;
  }
  public String getKurzText() {
    return this.text == null ? "" : kuerzen(this.text,1);
  }
  public void setText(String text) {
    this.text = text;
  }
  public void setNummer(int nummer) {
    this.nummer = nummer;
  }
  /**
   * Nummer der Meldung in der Meldungsliste. Diese Methode liefert die Nummer der 
   * Meldung in der Meldungsliste zur�ck. Ist die zur�ckgelieferte Nummer kleiner 
   * als 0, so bedeutet dies, dass die Meldung noch nicht in die Liste aufgenommen 
   * wurde
   * @return die Nummer der Meldung
   */
  public int getNummer() {
    return this.nummer;
  }
  
  public Hashtable<String, String> getFehler() {
    return this.fehler;
  }

  /*
   * Methoden
   */
  /**
   * Kontrolliert ob die Meldung vollst�ndig ist. Diese ist vollst�ndig
   * wenn der Verfasser, der Titel und der Text der Meldung eingegeben wurden. 
   * Wurde eine dieser Komponenten nicht gesetzt, so wird dies in der 
   * Hashtable notiert. Ist die Meldung korrekt, so ist fehler = null.
   * Ist die Meldung vollst�ndig und korrekt, so wird ein Zeitstempel gesetzt
   */
  public void validiere() {
    this.fehler = null;
    if (this.verfasser == null || this.verfasser.length() == 0) {
    	this.fehler = new Hashtable();
    	this.fehler.put("verfasser","Der Verfasser der Meldung wurde nicht gesetzt");
    }
    if (this.titel == null || this.titel.length() == 0) {
      if (this.fehler == null)
      	this.fehler = new Hashtable();
      this.fehler.put("titel","Bitte geben Sie den Titel der Meldung ein");
    }
    if (this.text == null || this.text.length() == 0) {
      if (this.fehler == null)
      	this.fehler = new Hashtable();
      this.fehler.put("text","Bitte geben Sie den Text der Meldung ein");
    }
    if (this.fehler == null)
    	System.out.println("halasd");
      // Setzen des Zeitstempels
    	this.datum = new Date();
  }
  
  /**
   * Diese Methode k�rzt den �bergebenen Text auf MAXLAENGE. Wird dabei in art 0 
   * eingestellt, wo wird der Text auf genau MAXLAENGE gek�rzt, ansonsten wird
   * der Text auf 2mal MAXLAENGE gek�rzt
   * @param text der zu k�rzende Text
   * @param art der K�rzung. Dabei bedeutet 0, dass der Text auf MAXLAENGE 
   * gek�rzt wird und 1, dass Text auf MAXLAENGE * 2 gek�rzt wird
   * @return den gek�rzten Text
   */
  private String kuerzen(String text, int art) {
    String ret = text;
    if (art == 0) {
      // K�rzen auf genau MAXLAENGE
      if (text.length() > MAXLAENGE)
        ret = text.substring(0,MAXLAENGE - 4) + "...";
    } else {
      // K�rzen auf 2mal MAXLAENGE
      if (text.length() > MAXLAENGE * 2)
        ret = text.substring(0,(MAXLAENGE * 2) - 4) + "...";
    }
    return ret;
  }
}
