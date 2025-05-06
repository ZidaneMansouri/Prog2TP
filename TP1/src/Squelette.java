import eko.*;

public class Squelette extends ObjetJeu {
    private long delaiEntreMouvements = 300; // en ms
    private long tempsDepuisDernierDeplacement = 0;

    public Squelette(int x, int y) {
        super("Squelette", x, y, Etiquette.ENNEMI);
    }

    @Override
    protected void mettreAJour(long deltaTemps) {
        tempsDepuisDernierDeplacement += deltaTemps;

        if (tempsDepuisDernierDeplacement < delaiEntreMouvements) return;

        tempsDepuisDernierDeplacement = 0;

        ObjetJeu joueur = GestionnaireObjetsJeu.obtenir().trouverObjetJeu("Personnage");
        if (joueur == null) return;

        int dx = Integer.compare(joueur.getX(), getX());
        int dy = Integer.compare(joueur.getY(), getY());

        int newX = getX() + dx;
        int newY = getY() + dy;

        // Évite de marcher dans les murs
        for (ObjetJeu objet : GestionnaireObjetsJeu.obtenir().trouverObjetsJeu(Etiquette.MUR)) {
            if (objet.getX() == newX && objet.getY() == newY) {
                return; // on ne bouge pas si mur
            }
        }

        position.x = newX;
        position.y = newY;
    }

    @Override
    protected void dessiner() {
        EKOConsole.afficher(getX(), getY(), "\uEE15", EKOCouleur.BLANC);
    }
}
