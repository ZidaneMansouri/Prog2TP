import eko.*;

public class EcranVictoire extends ObjetJeu {
    public EcranVictoire() {
        super("EcranVictoire", 0, 0, Etiquette.SOL);
    }

    @Override
    protected void mettreAJour(long deltaTemps) {
        if (EKOTouche.ESPACE.estEnfoncee() || EKOTouche.ECHAPPEMENT.estEnfoncee()) {
            System.exit(0); // Quitter le jeu
        }
    }

    @Override
    protected void dessiner() {
        String msg1 = " BRAVO ! Tu as terminé le jeu ! ";
        String msg2 = "Appuyez sur ESPACE ou ÉCHAPPE pour quitter.";

        int x1 = (EKOConsole.largeur() - msg1.length()) / 2;
        int x2 = (EKOConsole.largeur() - msg2.length()) / 2;
        int y = EKOConsole.hauteur() / 2;

        EKOConsole.afficher(x1, y, msg1, EKOCouleur.VERT);
        EKOConsole.afficher(x2, y + 2, msg2, EKOCouleur.GRIS_PALE);
    }
}
