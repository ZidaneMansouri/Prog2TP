import eko.EKOConsole;
import eko.EKOTouche;

public class Personnage extends ObjetJeu implements Collisionnable {

    public Personnage(int x, int y) {
        super("Personnage", x, y, Etiquette.SOL);
    }

    @Override
    protected void mettreAJour(long deltaTemps) {
        int nouveauX = getX();
        int nouveauY = getY();

        if (EKOTouche.FLECHE_GAUCHE.estEnfoncee()) {
            nouveauX--;
        } else if (EKOTouche.FLECHE_DROITE.estEnfoncee()) {
            nouveauX++;
        } else if (EKOTouche.FLECHE_HAUT.estEnfoncee()) {
            nouveauY--;
        } else if (EKOTouche.FLECHE_BAS.estEnfoncee()) {
            nouveauY++;
        }

        // Avant de bouger : vérifier si la case est libre (pas un mur)
        if (peutAller(nouveauX, nouveauY)) {
            position.x = nouveauX;
            position.y = nouveauY;
        }
    }

    @Override
    protected void dessiner() {
        EKOConsole.afficher(getX(), getY(), "P"); // P pour Personnage
    }

    @Override
    public void gererCollisionAvec(ObjetJeu autre) {
        // Si tu veux plus tard : ramasser clefs, ouvrir portes, etc.
    }

    private boolean peutAller(int x, int y) {
        // Vérifie si la case (x, y) est libre (pas un mur)

        for (ObjetJeu objet : GestionnaireObjetsJeu.obtenir().trouverObjetsJeu(Etiquette.MUR)) {
            if (objet.getX() == x && objet.getY() == y) {
                return false; // 🚫 Il y a un mur ici, interdit d'aller
            }
        }
        return true; // ✅ Pas de mur, on peut y aller
    }
}
