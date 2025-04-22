import eko.EKOAudio;
import eko.EKOConsole;
import eko.EKOCouleur;
import eko.EKOSon;

public class Clef extends ObjetJeu implements Collisionnable {

    public Clef(int x, int y) {
        super("Clef", x, y, Etiquette.ITEM);
    }

    @Override
    protected void mettreAJour(long deltaTemps) {
        // Ne bouge pas
    }

    @Override
    protected void dessiner() {
        EKOConsole.afficher(getX(), getY(), "\uF084", EKOCouleur.JAUNE);
    }

    @Override
    public void gererCollisionAvec(ObjetJeu autre) {
        if (autre instanceof Personnage) {
            this.detruire();

            EKOSon ClefTrouve = EKOAudio.charger("audio/ItemFound.wav");
            EKOAudio.jouer(ClefTrouve, false);

        }
    }
}
