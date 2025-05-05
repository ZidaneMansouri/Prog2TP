import eko.EKOConsole;
import eko.EKOCouleur;

public class Grenouille extends ObjetJeu {
    private long temps = 0;

    public Grenouille(int x, int y) {
        super("Grenouille", x, y, Etiquette.ENNEMI);
    }

    @Override
    protected void mettreAJour(long deltaTemps) {
        temps += deltaTemps;
        if (temps >= 3000) {
            temps = 0;
            new LangueGrenouille(getX() + 1, getY(), 1); // vers la droite
        }
    }

    @Override
    protected void dessiner() {
        EKOConsole.afficher(getX(), getY(), "\uF438", EKOCouleur.VERT);
    }
}

