/**
 * TP1 FROGGYRINTHE
 * Antranik Bakkalian et Zidane Rakane Mansouri
 * PROJET
 * Remise au plus tard le 11 mai
 * Évaluation semaine 12
 */

import eko.EKO;
import eko.EKOAudio;
import eko.EKOSon;
import eko.EKOTouche;

public class Main {
    public static void main(String[] args) {
        // On initialise la fenêtre du jeu avec un titre, une largeur de 60 colonnes et une hauteur de 25 lignes
        EKO.initialiser("Froggyrinthe", 60, 25);

        EKOSon Bmusique = EKOAudio.charger("audio/TheMusicV2.wav");
        EKOAudio.jouer(Bmusique, true);

        new EcranDeveloppeurs();
        // On lance la boucle de jeu (mise à jour + affichage)
        boucleDeJeu();
    }

    // Cette méthode est la boucle principale du jeu.
    // Elle met à jour les objets du jeu, les affiche, et respecte un rythme constant.
    private static void boucleDeJeu() {
        final int TPS = 30;
        final long MS_PAR_TRAME = 1000 / TPS;

        long tempsAttente;
        long maintenant;
        long deltaTemps;
        long dernierTemps = System.nanoTime();

        while (true) {
            maintenant = System.nanoTime();
            deltaTemps = maintenant - dernierTemps;
            dernierTemps = maintenant;

            GestionnaireObjetsJeu.obtenir().mettreAJour(deltaTemps / 1_000_000);
            GestionnaireObjetsJeu.obtenir().dessiner();
            // Si le joueur appuie sur ÉCHAPPE, on quitte le jeu
            if (EKOTouche.ECHAPPEMENT.estEnfoncee()) {
                System.exit(0);
            }
            // Si on doit attendre (pour rester à 30 FPS), on attend
            tempsAttente = MS_PAR_TRAME - (System.nanoTime() - dernierTemps) / 1_000_000;
            if (tempsAttente > 0) {
                EKO.attendre(tempsAttente);
            }
        }
    }
}
