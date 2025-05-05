import eko.EKOConsole;
import eko.EKOCouleur;

public class Fantome extends ObjetJeu {
    private boolean horizontal;
    private int direction = 1;

    public Fantome(int x, int y, boolean horizontal) {
        super("Fantome", x, y, Etiquette.ENNEMI);
        this.horizontal = horizontal;
    }

    @Override
    protected void mettreAJour(long deltaTemps) {
        if (horizontal) {
            position.x += direction;
            if (!peutAller(position.x, position.y)) {
                position.x -= direction;
                direction *= -1;
            }
        } else {
            position.y += direction;
            if (!peutAller(position.x, position.y)) {
                position.y -= direction;
                direction *= -1;
            }
        }
    }

    private boolean peutAller(int x, int y) {
        for (ObjetJeu objet : GestionnaireObjetsJeu.obtenir().trouverObjetsJeu(Etiquette.MUR)) {
            if (objet.getX() == x && objet.getY() == y) {
                return false;
            }
        }
        return true;
    }

    @Override
    protected void dessiner() {
        EKOConsole.afficher(getX(), getY(), "F", EKOCouleur.ROUGE);
    }
}
