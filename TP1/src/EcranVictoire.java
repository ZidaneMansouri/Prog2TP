import eko.*;

public class EcranVictoire extends ObjetJeu {
    //Constructeur : crée un écran de victoire à la fin du jeu
    public EcranVictoire() {
        super("EcranVictoire", 0, 0, Etiquette.SOL);
    }

    // Cette méthode vérifie si le joueur appuie sur ESPACE ou ÉCHAPPE
    // Si oui, le jeu se ferme
    @Override
    protected void mettreAJour(long deltaTemps) {
        if (EKOTouche.ESPACE.estEnfoncee() || EKOTouche.ECHAPPEMENT.estEnfoncee()) {
            System.exit(0); // Quitter le jeu
        }
    }

    // Cette méthode affiche les messages de victoire à l'écran
    @Override
    protected void dessiner() {
        String msg1 = " BRAVO ! Tu as terminé le jeu ! ";
        String msg2 = "Appuyez sur ESPACE ou ÉCHAPPE pour quitter.";

        int x1 = (EKOConsole.largeur() - msg1.length()) / 2;
        int x2 = (EKOConsole.largeur() - msg2.length()) / 2;
        int y = EKOConsole.hauteur() / 2;

        EKOConsole.afficher(x1, y, msg1, EKOCouleur.VERT);// Message de victoire
        EKOConsole.afficher(x2, y + 2, msg2, EKOCouleur.GRIS_PALE);  // Instruction pour quitter
    }
}
