import eko.*;

public class Grenouille extends ObjetJeu {
    private boolean versDroite;
    private int langueLongueur = 0;
    private boolean tirer = false;
    private long timer = 0;

    public Grenouille(int x, int y, boolean droite) {
        super("Grenouille", x, y, Etiquette.ENNEMI);
        this.versDroite = droite;
    }

    @Override
    protected void mettreAJour(long deltaTemps) {
        timer += deltaTemps;
        if (timer >= 1000) {
            tirer = !tirer;
            timer = 0;
            langueLongueur = tirer ? 3 : 0;
        }
    }

    @Override
    protected void dessiner() {
        EKOConsole.afficher(getX(), getY(), "\uEDF8", EKOCouleur.VERT);
        if (tirer) {
            for (int i = 1; i <= langueLongueur; i++) {
                int xLangue = versDroite ? getX() + i : getX() - i;
                EKOConsole.afficher(xLangue, getY(), "\u2500", EKOCouleur.ROUGE);
            }
        }
    }
}
