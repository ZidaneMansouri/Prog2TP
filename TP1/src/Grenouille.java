import eko.*;
//Nous avons réussi à afficher la grenouille qui regarde vers la gauche, avec sa
// langue qui sort aussi vers la gauche, comme demandé. Pour la grenouille qui regarde vers la droite,
// nous avons seulement pu faire sortir la langue vers la droite, car il n’y avait pas de caractère Unicode
// spécifique pour afficher le corps de la grenouille tournée vers la droite (nous n’avions que le symbole \uEDF8
// pour la version gauche).
public class Grenouille extends ObjetJeu {
    private boolean regardeDroite; // true = droite, false = gauche
    private long tempsDepuisDernierTir = 0;
    private final long delaiTir = 2000; // 2 secondes
    private Langue langue;

    // Constructeur : crée une grenouille à une position donnée
    public Grenouille(int x, int y, boolean regardeDroite) {
        super("Grenouille", x, y, Etiquette.ENNEMI);
        this.regardeDroite = regardeDroite;
    }

    // Mise à jour de la grenouille (appelée à chaque trame)
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

    // Affichage de la grenouille
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

        // Met à jour la langue (appelée à chaque trame)
        @Override
        protected void mettreAJour(long deltaTemps) {
            tempsEcoule += deltaTemps;
            // Si le joueur est sur la même case que la langue, il perd de la vie
            ObjetJeu joueur = GestionnaireObjetsJeu.obtenir().trouverObjetJeu("Personnage");
            if (joueur != null && joueur.getX() == getX() && joueur.getY() == getY()) {
                if (joueur instanceof Personnage p) {
                    p.perdreVie(1);
                }
            }
        }

        // Affiche la langue rouge avec un symbole qui dépend de la direction
        @Override
        protected void dessiner() {
            String symbole = droite ? "\u257C" : "\u2500";
            EKOConsole.afficher(getX(), getY(), symbole, EKOCouleur.ROUGE);
        }

        // Vérifie si la langue a fini son animation
        public boolean estTerminee() {
            return tempsEcoule >= duree;
        }
    }
}
