import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ChargeurSalle {

    public static void chargerSalle(String cheminFichier) {
        try (BufferedReader lecteur = new BufferedReader(new FileReader(cheminFichier))) {
            String ligne;
            int y = 0;

            while ((ligne = lecteur.readLine()) != null) {
                for (int x = 0; x < ligne.length(); x++) {
                    char symbole = ligne.charAt(x);

                    switch (symbole) {
                        case '#':
                            new Mur(x, y);
                            break;
                        case '+':
                            new PorteCondamnee(x, y);
                            break;
                        case 'C':
                            new Clef(x, y);
                            break;
                        case '-':
                            new PorteVerrouillee(x, y);
                            break;
                        default:

                            break;
                    }
                }
                y++;
            }
        } catch (IOException e) {
            System.err.println("Erreur de lecture du fichier : " + cheminFichier);
            e.printStackTrace();
        }
    }
}
