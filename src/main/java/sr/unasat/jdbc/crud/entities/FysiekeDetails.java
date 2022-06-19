package sr.unasat.jdbc.crud.entities;

public class FysiekeDetails {
    private int lengte;
    private int id;
    private float gewicht;
    private Persoon persoon;
    private String geslacht;

    public FysiekeDetails(int id,int lengte, float gewicht, String geslacht, Persoon persoon) {
        this.id = id;
        this.lengte = lengte;
        this.gewicht = gewicht;
        this.geslacht = geslacht;
        this.persoon = persoon;
    }
    public FysiekeDetails(int lengte, float gewicht, String geslacht, Persoon persoon) {
      this(0,lengte,gewicht,geslacht,persoon);
    }

    public int getLengte() {
        return lengte;
    }

    public void setLengte(int lengte) {
        this.lengte = lengte;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getGewicht() {
        return gewicht;
    }

    public void setGewicht(float gewicht) {
        this.gewicht = gewicht;
    }

    public Persoon getPersoon() {
        return persoon;
    }

    public void setPersoon(Persoon persoon) {
        this.persoon = persoon;
    }

    public String getGeslacht() {
        return geslacht;
    }

    public void setGeslacht(String geslacht) {
        this.geslacht = geslacht;
    }


    @Override
    public String toString() {
        return String.format("{\n Geslacht: %s,\n Gewicht: %s kg,\n Lengte: %s cm\n}", geslacht, gewicht, lengte);
    }
}
