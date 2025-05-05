import eko.EKOConsole;
import eko.EKOCouleur;

public class Squelette extends ObjetJeu {
    public Squelette(int x, int y) {
        super("Squelette", x, y, Etiquette.ENNEMI);
    }

    @Override
    protected void mettreAJour(long deltaTemps) {
        Personnage joueur = (Personnage) GestionnaireObjetsJeu.obtenir().trouverObjetJeu("Personnage");
        if (joueur == null) return;

        int dx = Integer.compare(joueur.getX(), getX());
        int dy = Integer.compare(joueur.getY(), getY());

        if (peutAller(getX() + dx, getY())) {
            position.x += dx;
        } else if (peutAller(getX(), getY() + dy)) {
            position.y += dy;
        }
    }

    private boolean peutAller(int x, int y) {
        for (ObjetJeu objet : GestionnaireObjetsJeu.obtenir().trouverObjetsJeu(Etiquette.MUR)) {
            if (objet.getX() == x && objet.getY() == y) return false;
        }
        return true;
    }

    @Override
    protected void dessiner() {
        EKOConsole.afficher(getX(), getY(), "S", EKOCouleur.BLANC);
    }
}
