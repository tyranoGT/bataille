import java.util.Random; //pour la generation aleatoire
import java.util.Scanner; //pour les entrees utilisateurs
import java.util.concurrent.TimeUnit; //pour faire du delai
/** Application permettant de jouer a la bataille navale contre l'ordinateur (a executer via la console d'Intellij en raison des codes couleurs)
 * @author PITOU Tom
 * @since 08/02/2023
 * @see <a href="https://github.com/tyranoGT/bataille.git">Ce projet est disponible sur Github</a>
 */
public class bataille {
    /** Variable representant le code couleur du rouge pour l'affichage console d'Intellij
     */
    public static int rouge=31;
    /** Variable representant le code couleur du vert pour l'affichage console d'Intellij
     */
    public static int vert=32;
    /** Variable representant le code couleur du violet pour l'affichage console d'Intellij
     */
    public static int violet=35;
    /** Variable representant le code couleur du jaune pour l'affichage console d'Intellij
     */
    public static int jaune=33;
    /** Variable representant le code couleur du bleu pour l'affichage console d'Intellij
     */
    public static int bleu=34;
    /** Variable representant le code couleur du texte souligne pour l'affichage console d'Intellij
     */
    public static int souligne=4;
    /** Variable representant le code couleur du texte surligne pour l'affichage console d'Intellij
     */
    public static int surligne=7;

    /** Classe representant un tuple bateau et int (pour representer une quantite de tel bateau).
     * @author PITOU Tom
     * @since 08/02/2023
     */
    public static class Paire_bateau_quantite
    {
        /** Bateau du tuple
         */
        private Bateau objet;

        /** int du tuple
         */
        private int quantite;

        /** Constructeur du tuple bateau/int
         * @param a le bateau assigne au tuple
         * @param b le int assigne au tuple
         */
        public Paire_bateau_quantite(Bateau a, int b) {
            this.objet = a;
            this.quantite = b;
        }

        /** Permet d'obtenir le bateau du tuple
         * @return le bateau : objet
         */
        public Bateau obtenir_objet() {
            return objet;
        }

        /** Permet d'obtenir le int du tuple
         * @return le int : quantity
         */
        public int obtenir_quantite() {
            return quantite;
        }
    }

    /** Classe abstraite representant un bateau.
     * @author PITOU Tom
     * @since 08/02/2023
     */
    public static abstract class Bateau
    {
        /** nom du bateau
         */
        protected String m_nom;
        /** taille du bateau
         */
        protected int m_taille;
        /** identifiant du bateau representant le bateau sur la grille de jeu
         */
        protected int m_indentifiant;

        /** Permet d'obtenir le nom du bateau
         * @return le nom du bateau : m_nom
         */
        public String obtenir_nom()
        {
            return m_nom;
        }

        /** Permet d'obtenir la taille du bateau
         * @return la taille du bateau : m_taille
         */
        public int obtenir_taille()
        {
            return m_taille;
        }

        /** Permet d'obtenir l'identifiant du bateau
         * @return l'identifiant du bateau : m_identifiant
         */
        public int obtenir_identifiant()
        {
            return m_indentifiant;
        }
    }

    /** Classe heritant de bateau representant le type de bateau : Porte avions.
     * @author PITOU Tom
     * @since 08/02/2023
     */
    public static class Porte_avions extends Bateau
    {
        /** Constructeur par defaut qui initialise le nom,la taille et l'identifiant du bateau.
         */
        public Porte_avions()
        {
            m_nom="porte-avions";
            m_taille=5;
            m_indentifiant=1;
        }

    }

    /** Classe heritant de bateau representant le type de bateau : Croiseur.
     * @author PITOU Tom
     * @since 08/02/2023
     */
    public static class Croiseur extends Bateau
    {
        /** Constructeur par defaut qui initialise le nom,la taille et l'identifiant du bateau.
         */
        public Croiseur()
        {
            m_nom="croiseur";
            m_taille=4;
            m_indentifiant=2;
        }
    }

    /** Classe heritant de bateau representant le type de bateau : Contre torpilleurs.
     * @author PITOU Tom
     * @since 08/02/2023
     */
    public static class Contre_torpilleurs extends Bateau
    {
        /** Constructeur par defaut qui initialise le nom,la taille et l'identifiant du bateau.
         */
        public Contre_torpilleurs()
        {
            m_nom="contre-torpilleurs";
            m_taille=3;
            m_indentifiant=3;
        }
    }

    /** Classe heritant de bateau representant le type de bateau : Sous marin.
     * @author PITOU Tom
     * @since 08/02/2023
     */
    public static class Sous_marin extends Bateau
    {
        /** Constructeur par defaut qui initialise le nom,la taille et l'identifiant du bateau.
         */
        public Sous_marin()
        {
            m_nom="sous-marin";
            m_taille=3;
            m_indentifiant=4;
        }
    }

    /** Classe heritant de bateau representant le type de bateau : Torpilleur.
     * @author PITOU Tom
     * @since 08/02/2023
     */
    public static class Torpilleur extends Bateau
    {
        /** Constructeur par defaut qui initialise le nom,la taille et l'identifiant du bateau.
         */
        public Torpilleur()
        {
            m_nom="torpilleur";
            m_taille=2;
            m_indentifiant=5;
        }
    }
    /** Variable representant le nombre de lignes de la grille de jeu
     */
    public static final int nb_lignes=10;

    /** Variable representant le nombre de colonnes de la grille de jeu
     */
    public static final int nb_colonnes=10;

    /** Variable representant la grille de jeu de l'ordinateur
     */
    public static int[][] grilleOrdi=new int[nb_lignes][nb_colonnes];

    /** Variable representant la grille de jeu du joueur
     */
    public static int[][] grilleJeu=new int[nb_lignes][nb_colonnes];

    /** Variable representant les bateaux que possederont les deux joueurs
     */
    public static final Paire_bateau_quantite[] bateaux_de_chaque_joueur=
            {
                    new Paire_bateau_quantite(new Porte_avions(),1),
                    new Paire_bateau_quantite(new Croiseur(),1),
                    new Paire_bateau_quantite(new Contre_torpilleurs(),1),
                    new Paire_bateau_quantite(new Sous_marin(),1),
                    new Paire_bateau_quantite(new Torpilleur(),1)
            };
    /** Variable pour la generation aleatoire (seed)
     */
    public static Random rand = new Random ();

    /** Permet d'obtenir un nombre entier aleatoire entre deux bornes
     * @since 08/02/2023
     * @param a la borne minimale (inclu)
     * @param b la borne maximale (exclu)
     * @return un int le nombre resultant de la generation aleatoire
     */
    public static int randRange (int a, int b)
    {
        return rand.nextInt(b-a)+a;
    }

    /** Permet de verifier si un bateau peut etre place a tel endroit de la grille
     * @author PITOU Tom
     * @since 08/02/2023
     * @param grille la grille de jeu ou on veut placer le bateau
     * @param l la ligne de la position du bateau a place
     * @param c la colonne de la position du bateau a place
     * @param d si la valeur de ce parametre vaut : 1 le bateau est place horizontalement | 2 le bateau est place verticalement
     * @param t la taille du bateau a placer
     * @return un booleen pour signifier que la position est correct
     */
    public static boolean posOk (int [][] grille , int l, int c, int d, int t)
    {
        switch(d)
        {
            case 1:
                if(c+t>=nb_colonnes)
                    return false;
                for(int i=0;i<t;i++)
                {
                    if(grille[l][c+i]!=0)
                    {
                        return false;
                    }
                }
                break;
            case 2:
                if(l+t>=nb_lignes)
                    return false;
                for(int i=0;i<t;i++)
                {
                    if(grille[l+i][c]!=0)
                    {
                        return false;
                    }
                }
                break;
            default:
                return false;
        }
        return true;
    }

    /** Initialise la grille de l'oridinateur automatiquement
     * @author PITOU Tom
     * @since 08/02/2023
     */
    public static void initGrilleOrdi ()
    {
        boolean fin=false;
        int nombre_de_bateaux_places=0;
        while(!fin)
        {
            int ligne=randRange (0,nb_lignes);
            int colonne=randRange (0, nb_colonnes);
            int direction=randRange (1,3);
            int taille_bateau=bateaux_de_chaque_joueur[nombre_de_bateaux_places].obtenir_objet().m_taille;
            if(posOk ( grilleOrdi,ligne, colonne, direction,taille_bateau))
            {
                switch(direction)
                {
                    case 1:
                        for(int i=0;i<taille_bateau;i++)
                        {
                            grilleOrdi[ligne][colonne+i]=bateaux_de_chaque_joueur[nombre_de_bateaux_places].obtenir_objet().obtenir_identifiant();
                        }
                        break;
                    case 2:
                        for(int i=0;i<taille_bateau;i++)
                        {
                            grilleOrdi[ligne+i][colonne]=bateaux_de_chaque_joueur[nombre_de_bateaux_places].obtenir_objet().obtenir_identifiant();
                        }
                        break;
                    default:
                        break;
                }
                nombre_de_bateaux_places+=1;
            }
            else
            {
                continue;
            }
            if(nombre_de_bateaux_places==bateaux_de_chaque_joueur.length)
            {
                fin=true;
            }
        }
    }

    /** Initialise la grille du joueur en lui demandant ou il veut placer ses bateaux
     * @author PITOU Tom
     * @since 08/02/2023
     */
    public static void initGrilleJeu()
    {
        Scanner scanner;
        boolean fin=false;
        int nombre_de_bateaux_places=0;
        while(!fin)
        {
            int ligne=0;
            int colonne=0;
            int direction=1;
            int etape_actuelle=1;
            int taille_bateau=bateaux_de_chaque_joueur[nombre_de_bateaux_places].obtenir_objet().m_taille;
            while(etape_actuelle<=3)
            {
                cls();
                System.out.println(
                        "\u001b[01;" + souligne +"m"+
                                "Vous devez placez ce bateau:"+
                                "\u001b[00m "+
                                "\u001b[01;" + violet +"m"+
                                bateaux_de_chaque_joueur[nombre_de_bateaux_places].obtenir_objet().m_nom+
                                "\u001b[00m "+
                                "\nIl a comme taille "+
                                "\u001b[01;" + violet +"m"+
                                taille_bateau+
                                "\u001b[00m" +
                                " cases"+
                                "\u001b[01;" + souligne +"m"+
                                "\nVoici l'état de votre grille:"+
                                "\u001b[00m ");
                AfficherGrille(grilleJeu);
                switch(etape_actuelle)
                {
                    case 1:
                        scanner = new Scanner(System.in);
                        System.out.println("\u001b[01;" + souligne  +"m"+ "Choissisez une colonne allant de "+(char)65+ " à "+(char)(nb_colonnes+64)+ "\u001b[00m :");
                        String colonne_rep = scanner.nextLine();
                        if(colonne_rep=="")
                        {
                            cls();
                            System.out.print("\u001b[01;" + rouge  +"m"+ "Repondez"+ "\u001b[00m ");
                            attendre(2);
                            break;
                        }
                        char premier_char_rep=colonne_rep.toUpperCase().charAt(0);
                        if(premier_char_rep>=(char)65 && premier_char_rep<=(char)(nb_colonnes+64))
                        {
                            colonne=premier_char_rep-65;
                            etape_actuelle+=1;
                        }
                        else
                        {
                            cls();
                            System.out.print("\u001b[01;" + rouge  +"m"+ "Votre choix est en dehors du terrain"+ "\u001b[00m ");
                            attendre(2);
                        }
                        break;
                    case 2:
                        scanner = new Scanner(System.in);
                        System.out.println("\u001b[01;" + souligne  +"m"+ "Choissisez une ligne allant de 1 à "+nb_colonnes+ "\u001b[00m :");
                        String ligne_rep = scanner.nextLine();
                        if(ligne_rep=="")
                        {
                            cls();
                            System.out.print("\u001b[01;" + rouge  +"m"+ "Repondez"+ "\u001b[00m ");
                            attendre(2);
                            break;
                        }
                        if(!est_un_entier(ligne_rep))
                        {
                            cls();
                            System.out.print("\u001b[01;" + rouge  +"m"+ "Mettez uniquement des chiffres"+ "\u001b[00m ");
                            attendre(2);
                            break;
                        }
                        int int_ligne_rep=Integer.parseInt(ligne_rep);
                        if(int_ligne_rep>=1 && int_ligne_rep<=nb_colonnes)
                        {
                            ligne=int_ligne_rep-1;
                            etape_actuelle+=1;
                        }
                        else
                        {
                            cls();
                            System.out.print("\u001b[01;" + rouge  +"m"+ "Votre choix est en dehors du terrain"+ "\u001b[00m ");
                            attendre(2);
                        }
                        break;
                    case 3:
                        scanner = new Scanner(System.in);
                        System.out.println("horizontal (1) ou vertical (2)");
                        String direction_rep = scanner.nextLine();
                        if(direction_rep=="")
                        {
                            cls();
                            System.out.print("\u001b[01;" + rouge  +"m"+ "Repondez"+ "\u001b[00m ");
                            attendre(2);
                            break;
                        }
                        if(!est_un_entier(direction_rep))
                        {
                            cls();
                            System.out.print("\u001b[01;" + rouge  +"m"+ "Mettez uniquement des chiffres"+ "\u001b[00m ");
                            attendre(2);
                            break;
                        }
                        direction=Integer.parseInt(direction_rep);
                        if(direction==1 || direction==2)
                        {
                            etape_actuelle+=1;
                        }
                        else
                        {
                            cls();
                            System.out.print("\u001b[01;" + rouge  +"m"+ "Votre choix est en dehors des propositions"+ "\u001b[00m ");
                            attendre(2);
                        }
                        break;
                    default:
                        break;
                }
            }
            if(posOk ( grilleJeu,ligne, colonne, direction,taille_bateau))
            {
                switch(direction)
                {
                    case 1:
                        for(int i=0;i<taille_bateau;i++)
                        {
                            grilleJeu[ligne][colonne+i]=bateaux_de_chaque_joueur[nombre_de_bateaux_places].obtenir_objet().obtenir_identifiant();
                        }
                        break;
                    case 2:
                        for(int i=0;i<taille_bateau;i++)
                        {
                            grilleJeu[ligne+i][colonne]=bateaux_de_chaque_joueur[nombre_de_bateaux_places].obtenir_objet().obtenir_identifiant();
                        }
                        break;
                    default:
                        break;
                }
                nombre_de_bateaux_places+=1;
            }
            else
            {
                cls();
                System.out.print("\u001b[01;" + rouge  +"m"+ "Votre bateau ne peut pas etre place ici"+ "\u001b[00m ");
                attendre(2);
                continue;
            }
            if(nombre_de_bateaux_places==bateaux_de_chaque_joueur.length)
            {
                fin=true;
            }
        }
    }

    /** Permet d'afficher une grille de jeu dans la console
     * @author PITOU Tom
     * @since 08/02/2023
     * @param grille la grille de jeu que l'on veut afficher
     */
    public static void AfficherGrille(int [][] grille)
    {
        int couleur=0;
        System.out.print("    ");
        for(int j=0;j<nb_colonnes;j++)
        {
            System.out.print("\u001b[01;" + surligne  +"m"+ "|"+(char)(j+65)+"|"+ "\u001b[00m ");
        }
        System.out.println();
        for(int i=0;i<nb_lignes;i++)
        {
            System.out.print("\u001b[01;" + surligne  +"m"+ String.format("|%02d|", (i+1))+ "\u001b[00m ");
            for(int j=0;j<nb_colonnes;j++)
            {
                int val=grille[i][j];
                switch(val)
                {
                    case 0:
                        couleur=bleu;
                        break;
                    case 6:
                        couleur=rouge;
                        break;
                    case 7:
                        couleur=jaune;
                        break;
                    case 8:
                        System.out.print("\u001b[01;" + rouge  +"m"+ "X"+ "\u001b[00m   ");
                        continue;
                    default:
                        couleur=vert;
                }
                System.out.print("\u001b[01;" + couleur  +"m"+ val+ "\u001b[00m   ");
            }
            System.out.println();
        }
    }

    /** Permet d'afficher une grille de jeu dans la console sans montrer l'emplacement des bateaux ennemis
     * @author PITOU Tom
     * @since 08/02/2023
     * @param grille la grille de jeu que l'on veut afficher
     */
    public static void AfficherGrilleTouche(int [][] grille)
    {
        System.out.print("    ");
        for(int j=0;j<nb_colonnes;j++)
        {
            System.out.print("\u001b[01;" + surligne  +"m"+ "|"+(char)(j+65)+"|"+ "\u001b[00m ");
        }
        System.out.println();
        for(int i=0;i<nb_lignes;i++)
        {
            System.out.print("\u001b[01;" + surligne  +"m"+ String.format("|%02d|", (i+1))+ "\u001b[00m ");
            for(int j=0;j<nb_colonnes;j++)
            {
                int val=grille[i][j];
                switch(val)
                {
                    case 6:
                        System.out.print("\u001b[01;" + vert  +"m"+ val+ "\u001b[00m   ");
                        break;
                    case 7:
                        System.out.print("\u001b[01;" + jaune  +"m"+ val+ "\u001b[00m   ");
                        break;
                    case 8:
                        System.out.print("\u001b[01;" + rouge  +"m"+ "X"+ "\u001b[00m   ");
                        break;
                    default:
                        System.out.print("\u001b[01;" + bleu  +"m"+ 0+ "\u001b[00m   ");
                }
            }
            System.out.println();
        }
    }

    /** Permet de verifier si on a coule un bateau
     * @author PITOU Tom
     * @since 08/02/2023
     * @param grille la grille de jeu a utiliser
     * @param bateau l'identifiant du bateau a verifier s'il est coule
     * @param l
     * @param c
     * @return un booleen indiquant si le bateau a ete coule
     */
    public static boolean coule(int [][] grille,int bateau,int l,int c)
    {
        for(int i=0;i<nb_lignes;i++)
        {
            for(int j=0;j<nb_colonnes;j++)
            {
                if(grille[i][j]==bateau)
                {
                    return false;
                }
            }
        }
        return true;

        /*
        int taille_ship=0;
        for(int i=0;i<ships_of_each_player.length;i++)
        {
            if(ships_of_each_player[i].get_object().m_indentifiant==ship)
            {
                taille_ship=ships_of_each_player[i].get_object().m_taille;
            }
        }
        for(int i=-taille_ship+1;i<taille_ship-1;i++)
        {
            int line=l+i;
            if(line>=0 && line<=nb_lignes)
            {
                if(grille[line][c]==ship)
                {
                    return false;
                }
            }
        }
        for(int j=-taille_ship+1;j<taille_ship-1;j++)
        {
            int colonne=c+j;
            if(colonne>=0 && colonne<=nb_lignes)
            {
                if (grille[l][colonne] == ship)
                {
                    return false;
                }
            }
        }
        return true;*/
    }

    /** Permet d'agir en fonction de ce que touche le tir
     * @author PITOU Tom
     * @since 08/02/2023
     * @param grille la grille de jeu ou a lieu le tir
     * @param l la ligne dans la position du tir
     * @param c la colonne dans la position du tir
     */
    public static void mouvement(int [][] grille,int l, int c)
    {
        int cible=grille[l][c];
        if(cible==0 || cible==7)
        {
            grille[l][c]=7;
            System.out.println("\u001b[01;" + bleu  +"m"+ "à l'eau"+ "\u001b[00m");
        }
        else
        {
            grille[l][c]=6;
            if(coule(grille,cible,l,c))
            {
                System.out.println("\u001b[01;" + rouge  +"m"+ "coule"+ "\u001b[00m");
            }
            else
            {
                System.out.println("\u001b[01;" + jaune  +"m"+ "touche"+ "\u001b[00m");
            }
        }
    }

    /** Permet de choisir la position du tir de l'ordinateur aleatoirement
     * @author PITOU Tom
     * @since 08/02/2023
     * @return un tableau de int contenant la position en ligne du tir et la position en colonne du tir
     */
    public static int[] tirOrdinateur()
    {
        int[] pos={randRange (0,nb_lignes),randRange (0,nb_colonnes)};
        return pos;
    }

    /** Permet de verifier si la partie est fini soit que tous les bateaux d'une grille ont ete coules
     * @author PITOU Tom
     * @since 08/02/2023
     * @param grille la grille de jeu que l'on veut verifier
     * @return un booleen indiquant si la partie est fini
     */
    public static boolean vainqueur(int [][] grille)
    {
        for(int i=0;i<nb_lignes;i++)
        {
            for(int j=0;j<nb_colonnes;j++)
            {
                int val=grille[i][j];
                if(val==0 || val==6 || val==7)
                {
                    continue;
                }
                else
                {
                    return false;
                }
            }
        }
        return true;
    }

    /** Permet de commencer,jouer une partie et d'en recommencer une a la fin de celle ci
     * @author PITOU Tom
     * @since 08/02/2023
     */
    public static void engagement()
    {
        initGrilleOrdi();
        initGrilleJeu();
        boolean tour_ordi=(0 == randRange (0, 2));
        cls();
        if(tour_ordi)
        {
            System.out.println("_____________________________");
            System.out.println("L'ordinateur joue en premier");
            System.out.println("_____________________________");
        }
        else
        {
            System.out.println("_____________________________");
            System.out.println("Vous jouez en premier");
            System.out.println("_____________________________");
        }
        attendre(2);
        Scanner scanner;
        int ligne=0;
        int colonne=0;
        boolean fin=false;
        while(!fin)
        {
            if(tour_ordi)
            {
                cls();
                int[] pos=tirOrdinateur();
                System.out.println("_____________________________");
                System.out.println("L'ordinateur joue "+(pos[0]+1)+" "+(char)(pos[1]+65));
                System.out.println("_____________________________");
                System.out.println("Votre grille :");
                int cible=grilleJeu[pos[0]][pos[1]];
                grilleJeu[pos[0]][pos[1]]=8;
                AfficherGrille(grilleJeu);
                attendre(2);
                grilleJeu[pos[0]][pos[1]]=cible;
                cls();
                System.out.println("_____________________________");
                System.out.print("L'ordinateur joue "+(pos[0]+1)+" "+(char)(pos[1]+65)+" c'est: ");
                mouvement(grilleJeu,pos[0],pos[1]);
                System.out.println("_____________________________");
                System.out.println("Votre grille :");
                AfficherGrille(grilleJeu);
                attendre(4);
                if(vainqueur(grilleJeu))
                {
                    System.out.println("_____________________________");
                    System.out.println("Vous avez perdu");
                    System.out.println("_____________________________");
                    fin=true;
                }
                tour_ordi=!tour_ordi;
            }
            else
            {
                int etape_actuelle=1;
                while(etape_actuelle<=2)
                {
                    cls();
                    System.out.println("_____________________________");
                    System.out.println("Ou envoyez vous votre torpille?");
                    System.out.println("_____________________________");
                    System.out.println("Grille ennemi");
                    AfficherGrilleTouche(grilleOrdi);
                    switch(etape_actuelle)
                    {
                        case 1:
                            scanner = new Scanner(System.in);
                            System.out.println("\u001b[01;" + souligne  +"m"+ "Choissisez une colonne allant de "+(char)65+ " à "+(char)(nb_colonnes+64)+ "\u001b[00m :");
                            String colonne_rep = scanner.nextLine();
                            if(colonne_rep=="")
                            {
                                cls();
                                System.out.print("\u001b[01;" + rouge  +"m"+ "Repondez"+ "\u001b[00m ");
                                attendre(2);
                                break;
                            }
                            char premier_char_rep=colonne_rep.toUpperCase().charAt(0);
                            if(premier_char_rep>='A' && premier_char_rep<='J')
                            {
                                colonne=premier_char_rep-65;
                                etape_actuelle+=1;
                            }
                            else
                            {
                                cls();
                                System.out.print("\u001b[01;" + rouge  +"m"+ "Votre choix est en dehors du terrain"+ "\u001b[00m ");
                                attendre(2);
                            }
                            break;
                        case 2:
                            scanner = new Scanner(System.in);
                            System.out.println("\u001b[01;" + souligne  +"m"+ "Choissisez une ligne allant de 1 à "+nb_colonnes+ "\u001b[00m :");
                            String ligne_rep = scanner.nextLine();
                            if(ligne_rep=="")
                            {
                                cls();
                                System.out.print("\u001b[01;" + rouge  +"m"+ "Repondez"+ "\u001b[00m ");
                                attendre(2);
                                break;
                            }
                            if(!est_un_entier(ligne_rep))
                            {
                                cls();
                                System.out.print("\u001b[01;" + rouge  +"m"+ "Mettez uniquement des chiffres"+ "\u001b[00m ");
                                attendre(2);
                                break;
                            }
                            int int_ligne_rep=Integer.parseInt(ligne_rep);
                            if(int_ligne_rep>=1 && int_ligne_rep<=10)
                            {
                                ligne=int_ligne_rep-1;
                                etape_actuelle+=1;
                            }
                            else
                            {
                                cls();
                                System.out.print("\u001b[01;" + rouge  +"m"+ "Votre choix est en dehors du terrain"+ "\u001b[00m ");
                                attendre(2);
                            }
                            break;
                        default:
                            break;
                    }
                }
                cls();
                System.out.println("_____________________________");
                System.out.println("Vous jouez "+(ligne+1)+" "+(char)(colonne+65));
                System.out.println("_____________________________");
                int cible=grilleOrdi[ligne][colonne];
                grilleOrdi[ligne][colonne]=8;
                AfficherGrilleTouche(grilleOrdi);
                attendre(2);
                grilleOrdi[ligne][colonne]=cible;
                cls();
                System.out.println("_____________________________");
                System.out.print("Vous jouez "+(ligne+1)+" "+(char)(colonne+65)+" c'est: ");
                mouvement(grilleOrdi,ligne,colonne);
                System.out.println("_____________________________");
                AfficherGrilleTouche(grilleOrdi);
                attendre(2);
                if(vainqueur(grilleOrdi))
                {
                    System.out.println("_____________________________");
                    System.out.println("Vous avez gagne");
                    System.out.println("_____________________________");
                    fin=true;
                }
                tour_ordi=!tour_ordi;
            }
        }
        System.out.println("Voulez-vous rejouez? 1=oui 2=non");
        int int_rep;
        do
        {
            scanner = new Scanner(System.in);
            String rep = scanner.nextLine();
            int_rep=Integer.parseInt(rep);
        }
        while(int_rep!=1 || int_rep!=2);
        if(int_rep==1)
        {
            engagement();
        }
    }

    /** Permet de faire un delai de x secondes
     * @author PITOU Tom
     * @since 08/02/2023
     * @param nb_secondes le nombre de secondes du delai
     */
    public static void attendre(int nb_secondes)
    {
        try
        {
            TimeUnit.SECONDS.sleep(nb_secondes);
        }
        catch (InterruptedException e) {
        }
    }

    /** Permet de verifier si un string est un int
     * @author PITOU Tom
     * @since 08/02/2023
     * @param str le string a verifier
     * @return un booleen indiquant si le string est un int ou non
     */
    public static boolean est_un_entier(String str)
    {
        for(int i=0;i<str.length();i++)
        {
            if(Character.digit(str.charAt(i),10)<0)
            {
                return false;
            }
        }
        return true;
    }

    /** Permet d'effacer la console d'Intellij (cette console ne pouvant etre efface utilisation de println pour sauter un nombre de lignes suffisantes)
     * @author PITOU Tom
     * @since 08/02/2023
     */
    public static void cls()
    {
        /*le code commente permet d'effacer la console quand notre application est execute sur le cmd de windows cependant les couleurs de la
        console d'Intellij etant plus interessantes*/
        /*try
        {
            new ProcessBuilder("cmd","/c","cls").inheritIO().start().waitFor();
        }
        catch(Exception e)
        {

        }*/
        for(int i=0;i<50;i++)
        {
            System.out.println();
        }
    }

    /**Le main de l'application, permet de lancer une premiere partie a l'execution du programme
     * @author PITOU Tom
     * @since 08/02/2023
     */
    public static void main(String[] args)
    {
        engagement();
    }
}