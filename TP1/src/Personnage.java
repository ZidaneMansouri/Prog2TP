import eko.EKOConsole;
import eko.EKOTouche;

public class Personnage extends ObjetJeu implements Collisionnable {

    public Personnage(int x, int y) {
        super("Personnage", x, y, Etiquette.SOL);
    }

    @Override
    protected void mettreAJour(long deltaTemps) {
        if (EKOTouche.FLECHE_GAUCHE.estEnfoncee()) {
            position.x--;
        } else if (EKOTouche.FLECHE_DROITE.estEnfoncee()) {
            position.x++;
        } else if (EKOTouche.FLECHE_HAUT.estEnfoncee()) {
            position.y--;
        } else if (EKOTouche.FLECHE_BAS.estEnfoncee()) {
            position.y++;
        }
    }

    @Override
    protected void dessiner() {
        EKOConsole.afficher(getX(), getY(), "P");  // 'P' pour Personnage
    }

    @Override
    public void gererCollisionAvec(ObjetJeu autre) {
        // Plus tard, on mettra la gestion contre clef, potion, ennemis
    }
}
