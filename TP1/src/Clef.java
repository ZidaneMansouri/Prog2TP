import eko.EKOAudio;
import eko.EKOConsole;
import eko.EKOCouleur;
import eko.EKOSon;

public class Clef extends ObjetJeu implements Collisionnable {
//Ne fait rien (clé statique).
    public Clef(int x, int y) {
        super("Clef", x, y, Etiquette.ITEM);
    }

    @Override
    protected void mettreAJour(long deltaTemps) {
    }
//	Affiche la clé en jaune.
    @Override
    protected void dessiner() {
        EKOConsole.afficher(getX(), getY(), "\uF084", EKOCouleur.JAUNE);
    }
//Quand le joueur touche la clé : joue un son, détruit la clé, déverrouille la porte.
    @Override
    public void gererCollisionAvec(ObjetJeu autre) {
        if (autre instanceof Personnage) {
            this.detruire();

            EKOSon ClefTrouve = EKOAudio.charger("audio/ItemFound.wav");
            EKOAudio.jouer(ClefTrouve, false);

        }
    }
}
