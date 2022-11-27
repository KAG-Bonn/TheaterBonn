public class KartenverkaufManager{
    private Theatersaal saal;
    
    public KartenverkaufManager(int pAnzahlReihen, int pAnzahlPlaetzeInReihen){
        saal = new Theatersaal(pAnzahlReihen,pAnzahlPlaetzeInReihen);
    }
    
    public double sitzplatzReservieren(int pReihe, int pNummer){
        Sitzplatz sitz = saal.gibSitzplatz(pReihe, pNummer);
        if(sitz == null) return 0;
        if(!sitz.istBelegt()){
            sitz.reservieren();
            System.out.println("Sitzplatz reserviert. Kosten: " + sitz.gibPreis());
            return sitz.gibPreis();
        }
        else{
            System.out.println("Fehler: Sitzplatz ist bereits reserviert");
            return 0;
        }
    }
    public double sitzplatzStornieren(int pReihe, int pNummer){
        Sitzplatz sitz = saal.gibSitzplatz(pReihe, pNummer);
        if(sitz.istBelegt()){
            sitz.stornieren();
            System.out.println("Sitzplatz storniert. Erstattung: " + sitz.gibPreis());
            return -sitz.gibPreis();
        }
        else{
            System.out.println("Fehler: Sitzplatz ist nicht besetzt");
            return 0;
        }
    }
    public double sitzPlaetzeReservieren(int[] pReihen, int[] pNummern){
        if(pReihen.length != pNummern.length) return 0;
        double gesamtpreis = 0.0;
        for(int i = 0; i < pReihen.length; i++){
            gesamtpreis += sitzplatzReservieren(pReihen[i], pNummern[i]);
        }
        return gesamtpreis;
    }
    public double sitzPlaetzeStornieren(int[] pReihen, int[] pNummern){
        if(pReihen.length != pNummern.length) return 0;
        double gesamtpreis = 0.0;
        for(int i = 0; i < pReihen.length; i++){
            gesamtpreis += sitzplatzStornieren(pReihen[i], pNummern[i]);
        }
        return -gesamtpreis;
    }
    
    public String gibTheatersaalbelegung(){
        String ausgabe = "    ";
        int anzahlPlaetzeInReihen = saal.gibAnzahlPlaetzeInReihen();
        int anzahlReihen = saal.gibAnzahlReihen();
        for(int k = 0; k < anzahlPlaetzeInReihen; k++){
            ausgabe += (k + "   ");
        }
        for(int i = 0; i < anzahlReihen; i++){
            ausgabe += ("\n" + i + ": ");
            for(int j = 0; j < anzahlPlaetzeInReihen; j++){
                Sitzplatz aktuellerSitzplatz = saal.gibSitzplatz(i,j);
                if(aktuellerSitzplatz == null){
                    System.out.println("Fehler in " + i + " " + j);
                    continue;
                }
                boolean istBelegt = aktuellerSitzplatz.istBelegt();
                if(istBelegt){
                    ausgabe += "[X]";
                }
                else{
                    ausgabe += "[ ]";
                }
                ausgabe += " ";
            }
            
        }
        return ausgabe;
    }
    public void TheatersaalbelegungAusgeben(){
        System.out.println(gibTheatersaalbelegung());
    }
    public int gibAnzahlBelegterPlaetzeUnten(){
        int anzahlPlaetzeInReihen = saal.gibAnzahlPlaetzeInReihen();
        int anzahlReihen = saal.gibAnzahlReihen();
        int anzahlBelegungen = 0;
        for(int i = 0; i < anzahlReihen/3; i++){
            for(int j = 0; j < anzahlPlaetzeInReihen;j++){
                if(!saal.istUnten(i,j)){
                    if(saal.gibSitzplatz(i,j).istBelegt()){
                        anzahlBelegungen += 1;
                    }
                }
            }
        }
        return anzahlBelegungen;
    }
    public int gibAnzahlBelegterPlaetzeMitte(){
        int anzahlBelegungen = 0;
        int anzahlPlaetzeInReihen = saal.gibAnzahlPlaetzeInReihen();
        int anzahlReihen = saal.gibAnzahlReihen();
        for(int i = anzahlReihen/3; i < 2*anzahlReihen/3; i++){
            for(int j = 0; j < anzahlPlaetzeInReihen;j++){
                if(!saal.istMitte(i,j)){
                    if(saal.gibSitzplatz(i,j).istBelegt()){
                        anzahlBelegungen += 1;
                    }
                }
            }
        }
        return anzahlBelegungen;
    }
    public int gibAnzahlBelegterPlaetzeOben(){
        int anzahlBelegungen = 0;
        int anzahlPlaetzeInReihen = saal.gibAnzahlPlaetzeInReihen();
        int anzahlReihen = saal.gibAnzahlReihen();
        for(int i = 2*anzahlReihen/3; i < anzahlReihen; i++){
            for(int j = 0; j < anzahlPlaetzeInReihen;j++){
                if(!saal.istOben(i,j)){
                    if(saal.gibSitzplatz(i,j).istBelegt()){
                        anzahlBelegungen += 1;
                    }
                }
            }
        }
        return anzahlBelegungen;
    }
    public int gibAnzahlBelegtePlaetze(){
        return gibAnzahlBelegterPlaetzeUnten() + gibAnzahlBelegterPlaetzeMitte() + gibAnzahlBelegterPlaetzeOben();
    }
    
    public int gibFreiePlaetze(){
        int anzahlPlaetzeInReihen = saal.gibAnzahlPlaetzeInReihen();
        int anzahlReihen = saal.gibAnzahlReihen();
        return anzahlReihen*anzahlPlaetzeInReihen - gibAnzahlBelegtePlaetze();
    }
}
