public class Theatersaal{
    private Sitzplatz[][] sitzplaetze;
    private int anzahlReihen;
    private int anzahlPlaetzeInReihen;
    
    public Theatersaal(int pAnzahlReihen, int pAnzahlPlaetzeInReihen){
        sitzplaetze = new Sitzplatz[pAnzahlReihen][pAnzahlPlaetzeInReihen];
        anzahlReihen = pAnzahlReihen;
        anzahlPlaetzeInReihen = pAnzahlPlaetzeInReihen;
        
        for(int i = 0; i < pAnzahlReihen; i++){
            for(int j = 0; j < pAnzahlPlaetzeInReihen; j++){
                if(istUnten(i,j)){
                    sitzplaetze[i][j] = new Sitzplatz(60.50);
                }
                else if(istMitte(i,j)){
                    sitzplaetze[i][j] = new Sitzplatz(40.75);
                }
                else if(istOben(i,j)){
                    sitzplaetze[i][j] = new Sitzplatz(25.25);
                }
            }
        }
    }
    
        
    public Sitzplatz gibSitzplatz(int pReihe, int pNummer){
        if(pReihe < 0 || pReihe >= anzahlReihen) return null;
        if(pNummer < 0 || pNummer >= anzahlPlaetzeInReihen) return null;
        return sitzplaetze[pReihe][pNummer];
    }
    
    public int gibAnzahlReihen(){
        return anzahlReihen;
    }
    
    public int gibAnzahlPlaetzeInReihen(){
        return anzahlPlaetzeInReihen;
    }
    
    public boolean istUnten(int pReihe, int pNummer){
        return (pReihe >= 0 && pReihe < anzahlReihen/3);
    }
    public boolean istMitte(int pReihe, int pNummer){
        return (pReihe >= anzahlReihen/3 && pReihe < 2*anzahlReihen/3);
    }
    public boolean istOben(int pReihe, int pNummer){
        return (pReihe >= 2*anzahlReihen/3);
    }
}
