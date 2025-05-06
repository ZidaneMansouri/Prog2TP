import eko.*;

public class Grenouille extends ObjetJeu {
    private boolean regardeDroite; // true = droite, false = gauche
    private long tempsDepuisDernierTir = 0;
    private final long delaiTir = 2000; // 2 secondes
    private Langue langue;

    public Grenouille(int x, int y, boolean regardeDroite) {
        super("Grenouille", x, y, Etiquette.ENNEMI);
        this.regardeDroite = regardeDroite;
    }

    @Override
    protected void mettreAJour(long deltaTemps) {
        tempsDepuisDernierTir += deltaTemps;

        if (tempsDepuisDernierTir >= delaiTir && langue == null) {
            int langueX = getX() + (regardeDroite ? 1 : -1);
            int langueY = getY();

            // Vérifier s'il y a un mur
            for (ObjetJeu objet : GestionnaireObjetsJeu.obtenir().trouverObjetsJeu(Etiquette.MUR)) {
                if (objet.getX() == langueX && objet.getY() == langueY) {
                    return; // N'avance pas dans un mur
                }
            }

            langue = new Langue(langueX, langueY, regardeDroite);
            tempsDepuisDernierTir = 0;
        }

        // Si la langue est terminée, la supprimer
        if (langue != null && langue.estTerminee()) {
            langue.detruire();
            langue = null;
        }
    }

    @Override
    protected void dessiner() {
        EKOConsole.afficher(getX(), getY(), "\uEDF8", EKOCouleur.VERT);
    }

    // Classe interne pour gérer la langue
    private class Langue extends ObjetJeu {
        private long duree = 500; // millisecondes
        private long tempsEcoule = 0;
        private boolean droite;

        public Langue(int x, int y, boolean droite) {
            super("Langue", x, y, Etiquette.ENNEMI);
            this.droite = droite;
        }

        @Override
        protected void mettreAJour(long deltaTemps) {
            tempsEcoule += deltaTemps;

            ObjetJeu joueur = GestionnaireObjetsJeu.obtenir().trouverObjetJeu("Personnage");
            if (joueur != null && joueur.getX() == getX() && joueur.getY() == getY()) {
                if (joueur instanceof Personnage p) {
                    p.perdreVie(1);
                }
            }
        }

        @Override
        protected void dessiner() {
            String symbole = droite ? "\u257C" : "\u2500";
            EKOConsole.afficher(getX(), getY(), symbole, EKOCouleur.ROUGE);
        }

        public boolean estTerminee() {
            return tempsEcoule >= duree;
        }
    }
}
