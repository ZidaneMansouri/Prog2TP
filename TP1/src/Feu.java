import eko.*;

public class Feu extends ObjetJeu {
    private boolean versDroite = true;
    private long timer = 0;
    private long prochainChangement = 1000 + (long) (Math.random() * 2000);

    // Constructeur : crée un feu à la position donnée
    public Feu(int x, int y) {
        super("Feu", x, y, Etiquette.ENNEMI);
    }

    // Méthode appelée à chaque trame pour mettre à jour le feu
    @Override
    protected void mettreAJour(long deltaTemps) {
        timer += deltaTemps;
        if (timer >= prochainChangement) {
            versDroite = !versDroite;
            timer = 0;
            // Détermine un nouveau temps aléatoire avant le prochain changement
            prochainChangement = 1000 + (long) (Math.random() * 2000);
        }
    }

    // Affiche le feu à l'écran (le symbole est le même, mais il peut "regarder" à droite ou à gauche)
    @Override
    protected void dessiner() {
        String symbole = versDroite ? "\uE3BF" : "\uE3BF";
        EKOConsole.afficher(getX(), getY(), symbole, EKOCouleur.RVB(204, 102, 0));
    }
}
