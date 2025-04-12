import eko.EKOConsole;

public class PorteCondamnee extends ObjetJeu {

    public PorteCondamnee(int x, int y) {
        super("PorteCondamnee", x, y, Etiquette.MUR);
    }

    @Override
    protected void mettreAJour(long deltaTemps) {
        // Ne bouge pas
    }

    @Override
    protected void dessiner() {
        EKOConsole.afficher(getX(), getY(), "+");
    }
}
