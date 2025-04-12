import eko.EKOConsole;

public class Mur extends ObjetJeu {

    public Mur(int x, int y) {
        super("Mur", x, y, Etiquette.MUR);
    }

    @Override
    protected void mettreAJour(long deltaTemps) {
        // Ne bouge pas
    }

    @Override
    protected void dessiner() {
        EKOConsole.afficher(getX(), getY(), "#");
    }
}
