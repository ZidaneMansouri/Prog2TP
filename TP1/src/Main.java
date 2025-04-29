/**
 * TP1 FROGGYRINTHE
 * Antranik Bakkalian et Zidane Rakane Mansouri
 * PROJET
 * Remise au plus tard le 23 avril
 * Évaluation semaine 12
 */
import eko.EKO;
import eko.EKOAudio;
import eko.EKOSon;
import eko.EKOTouche;

public class Main {
    public static void main(String[] args) {
        EKO.initialiser("Froggyrinthe", 60, 25);

        EKOSon Bmusique = EKOAudio.charger("audio/TheMusicV2.wav");
        EKOAudio.jouer(Bmusique, true);

        new EcranDeveloppeurs();
        boucleDeJeu();
    }
//Boucle principale : met à jour + dessine tout + vérifie si on appuie sur ÉCHAPPE.
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
            if (EKOTouche.ECHAPPEMENT.estEnfoncee()) {
                System.exit(0);
            }
            tempsAttente = MS_PAR_TRAME - (System.nanoTime() - dernierTemps) / 1_000_000;
            if (tempsAttente > 0) {
                EKO.attendre(tempsAttente);
            }
        }
    }
}
