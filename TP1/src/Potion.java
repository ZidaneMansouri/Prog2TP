import eko.*;
public class Potion extends ObjetJeu implements Collisionnable{
    protected Potion( int x, int y) {
        super("Potion", x, y, Etiquette.ITEM);
    }

    @Override
    public void gererCollisionAvec(ObjetJeu autre) {
    if(autre instanceof Personnage p) {
        p.RegenererVie();
        this.detruire();

        EKOSon PotionSon = EKOAudio.charger("audio/PotionSon.wav");
        EKOAudio.jouer(PotionSon);
    }
    }

    @Override
    protected void mettreAJour(long deltaTemps) {

    }

    @Override
    protected void dessiner() {
    EKOConsole.afficher(getX(), getY(), "\uF0C3", EKOCouleur.ROUGE);
    }
}
