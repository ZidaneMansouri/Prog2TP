import eko.*;

public class Feu extends ObjetJeu {
    private boolean versDroite = true;
    private long timer = 0;
    private long prochainChangement = 1000 + (long)(Math.random() * 2000);

    public Feu(int x, int y) {
        super("Feu", x, y, Etiquette.ENNEMI);
    }

    @Override
    protected void mettreAJour(long deltaTemps) {
        timer += deltaTemps;
        if (timer >= prochainChangement) {
            versDroite = !versDroite;
            timer = 0;
            prochainChangement = 1000 + (long)(Math.random() * 2000);
        }
    }

    @Override
    protected void dessiner() {
        String symbole = versDroite ? "\uE3BF" : "\uE3BF";
        EKOConsole.afficher(getX(), getY(), symbole, EKOCouleur.RVB(204, 102, 0));
    }
}
