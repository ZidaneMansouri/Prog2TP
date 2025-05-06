// Classe pour l'écran de défaite
import eko.*;

public class EcranDefaite extends ObjetJeu {
    public EcranDefaite() {
        super("EcranDefaite", 0, 0, Etiquette.SOL);
    }

    @Override
    protected void mettreAJour(long deltaTemps) {
        if (EKOTouche.ESPACE.estEnfoncee()) {
            System.exit(0);
        }
    }

    @Override
    protected void dessiner() {
        String ligne1 = "Vous avez été vaincu...";
        String ligne2 = "Appuyez sur ESPACE pour quitter";

        int x1 = (EKOConsole.largeur() - ligne1.length()) / 2;
        int x2 = (EKOConsole.largeur() - ligne2.length()) / 2;
        int y = EKOConsole.hauteur() / 2;

        EKOConsole.afficher(x1, y, ligne1, EKOCouleur.ROUGE);
        EKOConsole.afficher(x2, y + 2, ligne2, EKOCouleur.GRIS_PALE);
    }
}
