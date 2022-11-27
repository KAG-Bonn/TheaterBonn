public class Sitzplatz{
    private boolean istBelegt;
    private double preis;
    
    public Sitzplatz(double pPreis){
        istBelegt = false;
        preis = pPreis;
    }
    
    public void reservieren(){
        istBelegt = true;
    }
    public void stornieren(){
        istBelegt = false;
    }
    public boolean istBelegt(){
        return istBelegt;
    }
    public double gibPreis(){
        return preis;
    }
    public void setzePreis(double pPreis){
        preis = pPreis;
    }
}
