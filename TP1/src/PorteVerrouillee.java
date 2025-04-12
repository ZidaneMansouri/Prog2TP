import eko.EKOConsole;

public class PorteVerrouillee extends ObjetJeu {

    public PorteVerrouillee(int x, int y) {
        super("PorteVerrouillee", x, y, Etiquette.SORTIE);
    }

    @Override
    protected void mettreAJour(long deltaTemps) {
        // Ne bouge pas
    }

    @Override
    protected void dessiner() {
        EKOConsole.afficher(getX(), getY(), "-");
    }
}
