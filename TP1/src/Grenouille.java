import eko.EKOConsole;
import eko.EKOCouleur;

public class Grenouille extends ObjetJeu {
    private boolean versDroite;
    private boolean langueSortie = false;
    private long timer = 0;

    public Grenouille(int x, int y, boolean versDroite) {
        super("Grenouille", x, y, Etiquette.ENNEMI);
        this.versDroite = versDroite;
    }

    @Override
    protected void mettreAJour(long deltaTemps) {
        timer += deltaTemps;
        if (timer > 1500) {
            langueSortie = !langueSortie;
            timer = 0;
        }
    }

    @Override
    protected void dessiner() {
        String grenouille = versDroite ? ")" : "(";
        EKOConsole.afficher(getX(), getY(), grenouille, EKOCouleur.VERT);

        if (langueSortie) {
            int xLangue = versDroite ? getX() + 1 : getX() - 1;
            EKOConsole.afficher(xLangue, getY(), "-", EKOCouleur.ROUGE);

            ObjetJeu joueur = GestionnaireObjetsJeu.obtenir().trouverObjetJeu("Personnage");
            if (joueur != null && joueur.getX() == xLangue && joueur.getY() == getY()) {
                ((Personnage) joueur).perdreVie(20);
            }
        }
    }
}
