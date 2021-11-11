package model;

public class CountryTransport {

    private Transports transport;
    private double ileProcentZarazonychDoUzycia;
    private boolean czyZamkniete = false;
    private double decreaseInfectedPercent;

    public CountryTransport(Transports transport, double ileProcentZarazonychDoUzycia) {
        this.transport = transport;
        this.ileProcentZarazonychDoUzycia = ileProcentZarazonychDoUzycia;

        switch (transport) {
            case BUS:
                decreaseInfectedPercent = 0.25;
                break;
            case AUTO:
                decreaseInfectedPercent = 0.02;
                break;
            case PLANE:
                decreaseInfectedPercent = 0.04;
                break;
            case CARGO:
                decreaseInfectedPercent = 0.05;
                break;
            case TRAIN:
                decreaseInfectedPercent = 0.09;
                break;
        }
    }

    public Transports getTransport() {
        return transport;
    }

    public double getIleProcentZarazonychDoUzycia() {
        return ileProcentZarazonychDoUzycia;
    }

    public boolean czyZamkniete() {
        return czyZamkniete;
    }

    public void setCzyZamkniete(boolean czyZamkniete) {
        this.czyZamkniete = czyZamkniete;
    }
}
