import eko.*;

public class Squelette extends ObjetJeu {
    // Temps minimum entre deux déplacements (en millisecondes)
    private long delaiEntreMouvements = 300; // en ms
    private long tempsDepuisDernierDeplacement = 0;

    // Constructeur : crée un squelette à une position donnée
    public Squelette(int x, int y) {
        super("Squelette", x, y, Etiquette.ENNEMI);
    }

    @Override
    protected void mettreAJour(long deltaTemps) {
        // On ajoute le temps qui passe
        tempsDepuisDernierDeplacement += deltaTemps;
// Si le délai n'est pas encore écoulé, on ne bouge pas
        if (tempsDepuisDernierDeplacement < delaiEntreMouvements) return;
// Le délai est écoulé : on réinitialise le compteur
        tempsDepuisDernierDeplacement = 0;

        ObjetJeu joueur = GestionnaireObjetsJeu.obtenir().trouverObjetJeu("Personnage");
        if (joueur == null) return;

        int dx = Integer.compare(joueur.getX(), getX());
        int dy = Integer.compare(joueur.getY(), getY());

        int newX = getX() + dx;
        int newY = getY() + dy;

        // Si la case ciblée est un mur, on ne bouge pas
        for (ObjetJeu objet : GestionnaireObjetsJeu.obtenir().trouverObjetsJeu(Etiquette.MUR)) {
            if (objet.getX() == newX && objet.getY() == newY) {
                return; // on ne bouge pas si mur
            }
        }
        // Sinon, on avance vers le joueur
        position.x = newX;
        position.y = newY;
    }

    // Affiche le squelette avec un symbole blanc
    @Override
    protected void dessiner() {
        EKOConsole.afficher(getX(), getY(), "\uEE15", EKOCouleur.BLANC);
    }
}
