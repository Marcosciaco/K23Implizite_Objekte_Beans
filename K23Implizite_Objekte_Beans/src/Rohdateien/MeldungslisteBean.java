package Rohdateien;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Vector;

/**
 * Ein Objekt dieser Klasse erm�glicht es, Meldungen in eine Meldungsliste zu 
 * speichern und Meldungen aus dieser Liste zu holen. Die Meldungen werden auf 
 * externem Speicher abgelegt.<br><br>
 * Durch die Methode setPfad werden evtl. abgespeicherte Meldungen in die
 * Meldungsliste geladen.<br><br>
 * Durch die Methode meldungEinfuegen, k�nnen neue Meldungen in die Meldungsliste 
 * aufgenommen werden. Dabei wird die gesamte ge�nderte Meldungsliste wiederum 
 * zur�ck auf externen Speicher geschrieben, so dass der aktuellste Inhalt der Liste 
 * ebenfalls auf externen Speicher vorliegt.<br><br>
 * Die Meldungsliste kann nur abgespeichert bzw. geladen werden, wenn der Dateipfad 
 * gesetzt wurde. Um Die Meldungsdatei im classes-Ordner der Webanwendung abzulegen,
 * ruft man in der JSP-Datei folgende Methode mit den angegebenen Parametern auf:<br>
 * <b>application.getRealPath("/")</b><br><br>
 * Es kann eingestellt werden, wie viele Meldungen auf einmal dem Benutzer zur 
 * Verf�gung gestellt werden. Dadurch k�nnen die Meldungen blockweise dem Benutzer
 * zur Verf�gung gestellt werden.<br><br>
 * Durch die Methode getMeldung kann eine Meldung aus der Meldungliste geholt werden.
 * Weiters kann durch die Methode getMeldungen eine Liste von Meldungen aus der 
 * Meldungsliste geholt werden. Durch die Methode existierenMeldungen kann kontrolliert 
 * werden, ob weitere Meldungen existieren
 */
public class MeldungslisteBean
{
  /*
   * Statische Konstanten
   */
	/**
   * Anzahl der Meldungen, welche dem Benutzer auf einmal zur Verf�gung gestellt 
   * werden
   */
  private static final int ANZAHL_ZU_LIEFERNDER_MELDUNGEN = 5;
  
  /*
   * Membervariablen
   */
  /**
   * In diesem Vector werden die Meldungen abgespeichert. Dabei wird die aktuellste
   * Meldung ans Ende (!) angeh�ngt
   */
  protected Vector meldungen = null;
  /**
   * Dient zum Abspeichern des Dateipfades in welchem die Meldungsdatei zu finden ist
   */
  protected String pfad = null;
  
  /*
   * Getter- und Settermethoden
   */
  /**
   * Setzen des Dateipfades. Er wird nur gesetzt, wenn dieser nicht schon gesetzt ist.
   * Weiters wird die Datei der Meldungen im Ordner<br>
   * WEB-INF/classes/net/gobbz/meldungen/meldungen.ser<br>
   * abgelegt. Sollte dort eine Meldungsdatei vorhanden sein, so wird diese geladen, 
   * ansonsten wird ein leerer Meldungsvektor angelegt und versucht, diesen auf 
   * Datentr�ger zu schreiben. Wenn dies nicht gelingt, wird der Meldungsvektor 
   * wiederum auf null gesetzt
   * @param pfad der zu setzende Dateipfad
   */
  public void setPfad(String pfad) {
    if (this.pfad == null && pfad != null) {
      this.pfad = pfad.replace('\\','/') + "WEB-INF/classes/net/gobbz/meldungen/meldungen.ser";
      if (!laden()) {
        this.meldungen = new Vector();
        if (!speichern())
          this.meldungen = null;
      }
    } 
  }
  public String getPfad() {
  	return this.pfad != null ? this.pfad : "";
  }
  /**
   * Anzahl der Meldungen, welche dem Benutzer auf einmal zur Verf�gung gestellt werden
   * @return Anzahl der Meldungen die dem Benutzer blockweise zur Verf�gung 
   * gestellt werden
   */ 
  public int getAnzahlZuLiefernderMeldungen() {
    return ANZAHL_ZU_LIEFERNDER_MELDUNGEN;
  }
  
  /**
   * Anzahl der Meldungen, welche momentan in der Meldungsliste enthalten sind
   * @return die Anzahl der momentan in der Meldungsliste enthaltenen Meldungen
   */
  public int getAnzahlMeldungen() {
    int ret = 0;
    if (this.meldungen != null)
      ret = this.meldungen.size();
    return ret;
  }
  
  /**
   * Holt eine Meldung aus der Meldungsliste. Dabei wird jene Meldung geholt, deren
   * Nummer �bergeben wird. Ist keine entsprechende Meldung vorhanden, so wird
   * null zur�ck geliefert
   * @param nummer der Meldung welche aus der Meldungsliste geholt werden soll
   * @return die Meldung die an dieser Stelle im Meldungsvektor gefunden wurde
   */
  public MeldungsBean getMeldung(int nummer) {
    MeldungsBean meldung = null;
    if (this.meldungen != null) {
      if (nummer >=0 && nummer < this.meldungen.size())
        meldung = (MeldungsBean)this.meldungen.get(nummer);
    }
    return meldung;
  }
  
  /**
   * Holen der Meldungen aus der Meldungsliste. Ist nummer negativ, so werden die 
   * aktuellsten Meldungen aus der Liste geholt, also jene Meldungen, die gerade eben
   * erst eingetragen wurden. Ist nummer positiv und richtung auf
   * true gesetzt, so werden die Meldungen die zeitlich vor der �bergebenen Meldung 
   * eingetragen wurden, zur�ckgeliefert. Ist richtung auf false gesetzt, so werden die 
   * Meldungen welche zeitlich nach der �bergebenen Meldungsnummer eingetragen wurden,
   * zur�ckgeliefert. Es werden nicht alle Meldungen zur�ckgeliefert, sondern ein Block 
   * von Meldungen.<br>
   * Beispiel:<br>
   * Wenn der Vektor folgende Meldungen enth�lt, liefern die nachfolgenden Aufrufe 
   * folgende Ergebnisse (die Nummern am Beginn geben die Positionen der Meldungen im
   * Vektor wieder, ANZAHL_ZU_LIEFERNDER_MELDUNGEN sei auf 3 gesetzt):<br>
   * 10 10.02.2004 21:18:51 Paris: Gro�e Mehrheit f�r das Kopftuchverbot<br> 
   * 9 10.02.2004 21:18:23 Mit Solarkraft rund um die Welt<br>
   * 8 10.02.2004 21:17:27 Viel gesprochen, nichts gesagt<br>
   * 7 10.02.2004 21:16:40 Sicherheitsl�cke bei Nokia-Handys<br>
   * 6 10.02.2004 21:15:48 �lminister senken F�rderung drastisch<br>
   * 5 10.02.2004 21:15:11 Bode vs. Blitzherminatorsteff<br>
   * 4 10.02.2004 21:14:32 50 Tote bei Autobombenanschlag s�dlich von Bagdad<br> 
   * 3 10.02.2004 21:13:57 Auch ein Virus folgt den Regeln der Evolution<br>
   * 2 10.02.2004 21:13:17 Strenges "Klimaregime" kommt nicht<br>
   * 1 10.02.2004 21:12:32 EU-Kommission widersetzt sich Druck der Nettozahler<br>
   * 0 10.02.2004 21:07:56 Pr�sidentschaftskandidat Rybkin wieder aufgetaucht<br>
   * Aufruf von getMeldungen(5,true) liefert:<br>
   * 4 10.02.2004 21:14:32 50 Tote bei Autobombenanschlag s�dlich von Bagdad<br> 
   * 3 10.02.2004 21:13:57 Auch ein Virus folgt den Regeln der Evolution<br>
   * 2 10.02.2004 21:13:17 Strenges "Klimaregime" kommt nicht<br>
   * Aufruf von getMeldungen(5,false) liefert:<br>
   * 8 10.02.2004 21:17:27 Viel gesprochen, nichts gesagt<br>
   * 7 10.02.2004 21:16:40 Sicherheitsl�cke bei Nokia-Handys<br>
   * 6 10.02.2004 21:15:48 �lminister senken F�rderung drastisch<br>
   * @param nummer der Meldung ab welcher gesucht wird
   * @param richtung in der gesucht wird
   * @return ein Feld, welches den Block der gefundenen Meldungen enth�lt
   */
  public MeldungsBean[] getMeldungen(int nummer, boolean richtung) {
    MeldungsBean[] ret = null;
    // gibt es �berhaupt Meldungen
    if (this.meldungen != null && this.meldungen.size() > 0) {
      int vektorPosition = -1;
      int anzahlZuLesenderMeldungen = this.getAnzahlZuLiefernderMeldungen();
      if (nummer < 0) {
        // Springe zu den aktuellsten Meldungen am Endes des Vektors
        vektorPosition = this.meldungen.size() - 1;
        if (anzahlZuLesenderMeldungen > this.meldungen.size())
          anzahlZuLesenderMeldungen = this.meldungen.size();
      } else {
        // Kontrolliere, ob �berhaupt Meldungen existieren
        if (existierenMeldungen(nummer,richtung)) {
          if (richtung) {
            // Suche nach hinten im Vektor
            vektorPosition = nummer - 1;
            if (vektorPosition - anzahlZuLesenderMeldungen + 1 < 0)
              anzahlZuLesenderMeldungen = vektorPosition + 1;
          } else {
            // Suche nach vorne im Vektor
            int anzahlMeldungen = this.meldungen.size();
            if (nummer + anzahlZuLesenderMeldungen > anzahlMeldungen - 1)
              vektorPosition = anzahlMeldungen - 1;
            else
              vektorPosition = nummer + anzahlZuLesenderMeldungen;
            if (anzahlMeldungen < anzahlZuLesenderMeldungen)
              anzahlZuLesenderMeldungen = anzahlMeldungen;
          }
        }
      }
      if (vektorPosition != -1) {
	      ret = new MeldungsBean[anzahlZuLesenderMeldungen];
	      int feldPosition = 0;
	      while (anzahlZuLesenderMeldungen > 0) {
	        ret[feldPosition] =
	          (MeldungsBean)this.meldungen.get(vektorPosition);
	        anzahlZuLesenderMeldungen--;
	        vektorPosition--;
	        feldPosition++;
	      }
      }
    }
    return ret;
  }
  
  /*
   * Methoden
   */
  /**
   * Eine neue Meldung wird in die Meldungsliste aufgenommen. Die Meldung darf nur 
   * aufgenommen werden, wenn zuerst die Methode setPfad aufgerufen wurde und der
   * Meldungsvektor nicht null ist. Weiters darf die Meldung nur aufgenommen werden,
   * wenn diese korrekt ist. Die Meldung wird an das Ende des Meldungsvektors geh�ngt.
   * So ist die aktuellste Meldung immer ganz hinten an letzter Stelle im Vektor. 
   * <br><br>
   * Diese Methode setzt nach erfolgreichen Eintragen im Meldungsobjekt die Nummer
   * der Meldung auf jene Zahl, welche die Nummer der Meldung im Meldungsvektor 
   * wiederspiegelt. Anhand dieser Nummer kann dann eine Meldung schnell im Vektor
   * angesprungen werden aber auch erkannt werden, ob die Meldung bereits eingetragen
   * wurde.
   * @return
   * 0 wenn meldung erfolgreich eingetragen werden konnte<br>
   * -1 wenn der Meldungsvektor auf null zeigt, d. h. noch nicht setPfad aufgerufen 
   * wurde<br>
   * -2 wenn keine einzutragende Meldung �bergeben wurde<br>
   * -3 wenn einzutragende Meldung bereits eingetragen wurde<br>
   * -4 wenn einzutragende Meldung fehlerhaft oder unvollst�ndig<br>
   * -5 wenn die Meldungsliste nicht auf Datei gespeichert werden konnte. 
   * In diesem Fall wird Meldung nicht aufgenommen
   */
  public int meldungEinfuegen(MeldungsBean meldung) {
    int ret = 0;
    if (this.meldungen == null)
      ret = -1;
    else {
      if (meldung == null)
        ret = -2;
      else {
        if (meldung.getNummer() >= 0)
          ret = -3;
        else {
          meldung.validiere();
          if (meldung.getFehler() != null)
            ret = -4;
          else {
            // Setze die Nummer der Meldung auf die letzte Position des Vektors.
            // Dort wurde n�mlich die Meldung eingetragen
            meldung.setNummer(this.meldungen.size());
            // Meldung enth�lt keine Fehler. H�nge diese am Ende des Meldungsvektors
            this.meldungen.addElement(meldung);
            if (!speichern()) {
              ret = -5;
              this.meldungen.removeElementAt(meldungen.size() - 1);
              // Markiere die Meldung als nicht eingetragen
              meldung.setNummer(-1);
            }
          }
        }
      }
    }        
    return ret;
  }

  /**
   * Speichert alle vorhandene Meldungen in eine Datei, indem diese serialisiert
   * werden. Die im Objekt vorhandenen Meldungen werden dabei nicht gel�scht
   * @return liefert true zur�ck, wenn die Speicherung erfolgreich durchgef�hrt werden
   * konnte
   */
  private boolean speichern() {
    boolean ret = false;
    if (this.pfad != null) {
    	FileOutputStream fout = null;
    	try {
        fout = new FileOutputStream(this.pfad);
        ObjectOutputStream oout = null;
        try {
          oout = new ObjectOutputStream(fout);
          oout.writeObject(this.meldungen);
          ret = true;
        } catch (IOException e) {
          System.out.println("Datei: " + this.pfad + " Fehler: " + e.toString());
        } finally {
        	try { oout.close(); } catch (Exception e) { ; }
        }
      } catch (FileNotFoundException e) {
        System.out.println("Datei: " + this.pfad + " Fehler: " + e.toString());
      } finally {
      	try { fout.close(); } catch (Exception e) { ; }
      }
    }
    return ret;
  }

  /**
   * L�dt alle in eine Datei gespeicherten Meldungen, indem die serialisierten
   * Meldungen deserialisiert werden. Eventuell bereits vorhandene Meldungen
   * werden gel�scht
   * @return liefert true zur�ck, wenn das Laden der Meldungen
   * erfolgreich durchgef�hrt werden konnte
   */
  private boolean laden() {
    boolean ret = false;
    if (this.pfad != null) {
    	FileInputStream fin = null;
    	try {
        fin = new FileInputStream(this.pfad);
        ObjectInputStream oin = null;
        try {
          oin = new ObjectInputStream(fin);
          try {
            this.meldungen = (Vector)(oin.readObject());
            ret = true;
          } catch (ClassNotFoundException e) {
            System.out.println("Datei: " + this.pfad + " Fehler: " + e.toString());
          }
        } catch (IOException e) {
          System.out.println("Datei: " + this.pfad + " Fehler: " + e.toString());
        } finally {
        	try { oin.close(); } catch (Exception e) { ; }
        }
      } catch (FileNotFoundException e) {
        System.out.println("Datei: " + this.pfad + " Fehler: " + e.toString());
      } finally {
      	try { fin.close(); } catch (Exception e) { ; }
      }
    }
    return ret;
  }

  /**
   * Kontrolliert ob Meldungen, die zeitlich vor (true) bzw. nach (false) der 
   * �bergebenen Meldung in der Meldungsliste eingetragen wurden, existieren.
   * Die richtung gibt an, ob nach fr�her (true) oder sp�ter (false) in die 
   * Meldungsliste eingetragenen Meldungen gesucht werden soll.<br>
   * Beispiel:<br>
   * Wenn der Vektor folgende Meldungen enth�lt, liefern die nachfolgenden Aufrufe 
   * folgende Ergebnisse (die Nummern am Beginn geben die Positionen der Meldungen im
   * Vektor wieder, ANZAHL_ZU_LIEFERNDER_MELDUNGEN sei auf 3 gesetzt):<br>
   * 10 10.02.2004 21:18:51 Paris: Gro�e Mehrheit f�r das Kopftuchverbot<br> 
   * 9 10.02.2004 21:18:23 Mit Solarkraft rund um die Welt<br>
   * 8 10.02.2004 21:17:27 Viel gesprochen, nichts gesagt<br>
   * 7 10.02.2004 21:16:40 Sicherheitsl�cke bei Nokia-Handys<br>
   * 6 10.02.2004 21:15:48 �lminister senken F�rderung drastisch<br>
   * 5 10.02.2004 21:15:11 Bode vs. Blitzherminatorsteff<br>
   * 4 10.02.2004 21:14:32 50 Tote bei Autobombenanschlag s�dlich von Bagdad<br> 
   * 3 10.02.2004 21:13:57 Auch ein Virus folgt den Regeln der Evolution<br>
   * 2 10.02.2004 21:13:17 Strenges "Klimaregime" kommt nicht<br>
   * 1 10.02.2004 21:12:32 EU-Kommission widersetzt sich Druck der Nettozahler<br>
   * 0 10.02.2004 21:07:56 Pr�sidentschaftskandidat Rybkin wieder aufgetaucht<br>
   * Aufruf von existierenMeldungen(5,true) liefert wahr, weil Meldung 4 
   * existiert<br>
   * Aufruf von existierenMeldungen(5,false) liefert wahr, weil Meldung 6
   * existiert
   * @param nummer der Meldung ab der kontrolliert wird
   * @param richtung in der gesucht wird
   * @return ob Meldungen existieren
   */
  public boolean existierenMeldungen(int nummer, boolean richtung) {
    boolean ret = false;
    if (richtung) {
      // Suche nach vorne im Vektor
      if (nummer > 0)
        ret = true;
    } else {
      // Suche nach hinten im Vektor
      if (nummer < this.meldungen.size() - 1)
        ret = true;
    }
    return ret;
  }
}