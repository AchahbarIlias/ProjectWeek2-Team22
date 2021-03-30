package projectweek.team22;

import projectweek.team22.model.entity.Wedstrijd;

public class WedstrijdBuilder {
    private String omschrijving,locatie;
    private int aantal;

    private WedstrijdBuilder() {
    }
    public static WedstrijdBuilder aWedstrijd(){
        return new WedstrijdBuilder();
    }

    public WedstrijdBuilder withOmschrijving(String omschrijving){
        this.omschrijving=omschrijving;
        return this;
    }
    public WedstrijdBuilder withLocatie(String locatie){
        this.locatie=locatie;
        return this;
    }
    public WedstrijdBuilder withAantalToeschouwers(int aantaltoeschouwers){
        this.aantal=aantaltoeschouwers;
        return this;
    }
    public static WedstrijdBuilder aWedstrijdleuven(){
        return aWedstrijd().withOmschrijving("leuven-mechelen").withLocatie("leuven").withAantalToeschouwers(20);
    }

    public static WedstrijdBuilder aWedstrijdAntwerpen(){
        return aWedstrijd().withOmschrijving("leuven-antwerpen").withLocatie("antwerpen").withAantalToeschouwers(20);
    }
    public Wedstrijd build(){
        Wedstrijd wedstrijd = new Wedstrijd();
        wedstrijd.setOmschrijving(this.omschrijving);
        wedstrijd.setLocatie(this.locatie);
        wedstrijd.setAantalToeschouwers(this.aantal);
        return wedstrijd;
    }
}
