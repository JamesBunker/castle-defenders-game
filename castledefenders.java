import java.awt.*;
import hsa.Console;


public class castledefenders
{
    //declaring global variables
    static Console c;
    static int gold = 0;
    static int spawnt = 0;
    static int ruletest;
    static int clears;
    static int[] monsterlive = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
    static int timer;
    static int[] playerl = {100, 424};
    static int castlehp = 637;
    static int[] monsterx = {590, 590, 590, 590, 590, 590, 590, 590, 590, 590, 590, 590, 590, 590, 590, 590, 590, 590, 590, 590, 590, 590, 590, 590, 590, 590, 590, 590, 590, 590, 590, 590, 590, 590, 590, 590, 590, 590, 590, 590};
    static int[] monstery = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
    static int monsteralive = 1;
    static int points = 0;
    static int n = 0;
    static int mheight = 0;
    static int started = 1;
    static int g;
    static char menulose = 'x';
    static int wave = 0;
    static int spawn = 0;
    static char shop = 's';
    static Color goldc = new Color (255, 215, 0);
    static Color playercolor = Color.black;
    static char buycolor = 'x';
    static int redp = 0;
    static int fancyp = 0;
    static int bluep = 0;
    static Color fancy = new Color (105, 9, 188);
    

    // The output console

    public static void main (String[] args) throws InterruptedException
    {
	c = new Console ();
	int test = 1;
	int running = 0;
	char play = 'x';
	int clears = 0;
	ruletest = 1;
	timer = 0;
	int playerattack = 1;
	char ruleint = 'x';
	char ruleturn = 'x';



	//main method
	while (running == 0)
	{
	    started = 1;
	    menudisplay ();
	    menucastle ();
	    menufield ();
	    moneymenu ();
	    play = plays (play);
	    clears = 0;
	    while (play == 'p' && castlehp > 0)
	    {
		if (started == 1)
		{
		    //reset variables
		    wave = 0;
		    monsterreset ();
		    castlehp = 637;
		    n = 0;
		    spawnt = 0;
		    castlehealth ();
		    menucastle ();
		    menufield ();
		    playermove (playerl);
		    started = 0;
		    pointdisplay ();
		}
		playerlocation (playerl);
		monsterloop ();
		monsterlocation ();
		monsterspawn ();
		monsterdetermineattack ();

		if (castlehp <= 0)
		{
		    lose ();
		}
	    }
	    while (play == 'r')
	    {
		if (clears == 0)
		{
		    c.clear ();
		    clears = 1;
		}
		rule ();
		play = rulereturn (ruleint);
	    }

	    while (play == 's')
	    {
		if (clears == 0)
		{
		    c.clear ();
		    clears = 1;
		}
		shopping ();
		moneyshop ();
		shopbuy ();
		if (buycolor == 'm')
		{
		    play = buycolor;

		}
	    }
	}
	// Place your program here.  'c' is the output console
    } // main method


    public static void menudisplay ()
    {
	//intro, play and rule boxes
	c.setFont (new Font ("Imprint MT Shadow", Font.BOLD, 60));
	c.drawString ("Castle Defenders ", 90, 60);
	c.drawRect (110, 100, 200, 100);
	c.drawRect (350, 100, 200, 100);
	c.drawRect (230, 230, 200, 100);
	c.setColor (Color.red);
	c.setFont (new Font ("Imprint MT Shadow", Font.BOLD, 30));
	c.drawString ("(P)lay", 170, 150);
	c.drawString ("(R)ules", 400, 150);
	c.drawString ("(S)hop", 280, 280);
    }


    public static void menucastle ()
    {
	c.setColor (Color.black);
	c.setFont (new Font ("Arial", Font.BOLD, 30));
	//castle top
	c.drawString ("]==||==[", 0, 300);
	c.drawString ("]=||=[", 18, 275);
	c.drawString ("]||[", 36, 250);
	c.drawString ("||", 46, 225);
	//castle walls
	c.drawString ("|    ||    |", 7, 320);
	c.drawString ("|    ||    |", 7, 345);
	c.drawString ("|    ||    |", 7, 370);
	c.drawString ("|    ||    |", 7, 395);
	c.drawString ("|    ||    |", 7, 420);
	c.drawString ("|    ||    |", 7, 445);
	c.drawString ("|    ||    |", 7, 470);
	c.drawString ("|    ||    |", 7, 495);
	c.drawString ("|    ||    |", 7, 520);
    }


    public static void menufield ()
    {
	c.setTextBackgroundColor (Color.white);
	int menufieldx = 100;
	//field lines
	c.drawString ("________________________________________________________________", menufieldx, 375);
	c.drawString ("________________________________________________________________", menufieldx, 425);
	c.drawString ("________________________________________________________________", menufieldx, 475);
    }


    public static void rule () throws InterruptedException
    {
	//printing rules
	if (ruletest == 1)
	{
	    String ruleset = ("Welcome to 'Castle Defenders'! The objective is to move up and down the three   lines to defeat all the enemies attacking the castle. When the enemies reach thecastle its health will go down. If the Castle's HP goes down to zero you lose! ");
	    for (int ruley = 0 ; ruley < ruleset.length () ; ruley++)
	    {
		String ruleprint = ruleset.substring (ruley, ruley + 1);
		c.print (ruleprint);
		Thread.sleep (10);
	    }
	    c.println ("");
	    c.println ("");
	    c.println ("");
	    c.println ("");
	    String ruleset2 = ("Use 'w' to move up and 's' to move down the line. Defeat enemies by interceptingthem before they reach the castle. Score points and gold by defeating monsters  and completing waves! Player colors are availiable in 'Ye Olde Shop'!");
	    for (int ruley = 0 ; ruley < ruleset2.length () ; ruley++)
	    {
		String ruleprint = ruleset2.substring (ruley, ruley + 1);
		c.print (ruleprint);
		Thread.sleep (10);
	    }
	    c.println ("");
	    c.println ("");
	    c.println ("");
	    c.println ("");
	    String ruleset3 = ("Return to (M)enu");
	    for (int ruley = 0 ; ruley < ruleset3.length () ; ruley++)
	    {
		String ruleprint = ruleset3.substring (ruley, ruley + 1);
		c.print (ruleprint);
		Thread.sleep (10);
	    }
	}
	ruletest = 0;
    }


    public static void shopping ()
    {
	//shop graphics
	c.setFont (new Font ("Imprint MT Shadow", Font.BOLD, 70));
	c.drawString ("Ye Olde Shop", 100, 60);
	c.setFont (new Font ("Arial", Font.BOLD, 30));

	c.drawRect (350, 100, 200, 70);
	c.drawString ("(D)efault", 170, 150);
	c.drawString ("O/", 400, 150);


	c.setColor (Color.red);
	c.drawRect (350, 200, 200, 70);
	c.drawString ("(R)ed", 170, 250);
	c.drawString ("O/", 400, 250);

	c.setColor (Color.blue);
	c.drawRect (350, 300, 200, 70);
	c.drawString ("(B)lue", 170, 350);
	c.drawString ("O/", 400, 350);

	c.setColor (fancy);
	c.drawRect (350, 400, 200, 70);
	c.drawString ("(F)ancy", 170, 450);
	c.drawString ("O/", 400, 450);

	c.setColor (goldc);
	if (redp == 0)
	{
	    c.drawString ("100 gold", 30, 250);
	}

	if (bluep == 0)
	{
	    c.drawString ("100 gold", 30, 350);
	}

	if (fancyp == 0)
	{
	    c.drawString ("300 gold", 30, 450);
	}

	c.setColor (Color.black);
	c.setFont (new Font ("Arial", Font.BOLD, 20));
	c.drawString ("return to (M)enu", 5, 500);
	c.setFont (new Font ("Arial", Font.BOLD, 30));
    }


    public static void castlehealth ()
    {
	//castle hp bar
	c.setFont (new Font ("Imprint MT Shadow", Font.BOLD, 30));
	c.setColor (Color.green);
	c.fillRect (1, 1, castlehp, 40);
	c.setColor (Color.black);
	c.drawRect (1, 1, 637, 40);
	c.drawString ("Castle Health", 240, 70);
	c.setFont (new Font ("Arial", Font.BOLD, 30));
    }


    public static void pointdisplay ()
    {
	//displaying points
	c.setFont (new Font ("Imprint MT Shadow", Font.BOLD, 30));
	String pointsstring = Integer.toString (points);
	c.drawString ("Points", 10, 100);
	c.drawString (pointsstring, 10, 130);
	String wavestring = Integer.toString (wave);
	c.drawString ("Wave", 10, 160);
	c.drawString (wavestring, 105, 160);
	c.setFont (new Font ("Arial", Font.BOLD, 30));
    }


    public static char rulereturn (char ruleint)
    {
	//returning to menu
	char backtomenu = 'x';
	while (backtomenu != 'm')
	{
	    backtomenu = c.getChar ();
	    if (backtomenu == 'm')
	    {
		ruleint = 'x';
		c.clear ();
		ruletest = 1;
		clears = 0;
	    }

	}
	return ruleint;
    }


    public static char plays (char play)
    {
	// determining if user want to play game
	play = c.getChar ();
	castlehp = 637;
	if (play == 'p' && clears == 0)
	{
	    c.clear ();
	    clears = 1;
	    monsterx [0] = 590;
	    monsteralive = 1;
	    points = 0;
	}
	return play;
    }


    public static int[] playerlocation (int[] playerl)
    {
	//determining if an action key is used and moves player accordingly, with limits
	boolean press = c.isCharAvail ();
	if (press == true)
	{
	    char direction = c.getChar ();
	    if (direction == 'w' && playerl [1] > 374)
	    {
		playerl [1] -= 50;
		c.clear ();
		menufield ();
		playermove (playerl);
		menucastle ();
		castlehealth ();
		pointdisplay ();
	    }
	    if (direction == 's' && playerl [1] < 474)
	    {
		playerl [1] += 50;
		c.clear ();
		menufield ();
		playermove (playerl);
		menucastle ();
		castlehealth ();
		pointdisplay ();
	    }
	}
	return playerl;
    }


    public static void playermove (int[] playerl)
    {
	//drawing player
	c.setColor (playercolor);
	c.drawString ("O/", playerl [0], playerl [1]);
	c.setColor (Color.black);
    }


    public static void monsterlocation () throws InterruptedException
    {
	//moving monster and removing trail following
	for (int x = 0 ; x < spawnt ; x++)
	{
	    c.setColor (Color.white);
	    c.drawString ("orc", monsterx [x], monstery [x]);
	    monsterx [x] -= 10;
	    c.setColor (Color.black);
	    c.drawString ("orc", monsterx [x], monstery [x]);
	}
	menufield ();
	playermove (playerl);
	menucastle ();
	castlehealth ();
	pointdisplay ();
	timer++;
	Thread.sleep (50 - (wave * 3));
    }


    public static void attack () throws InterruptedException
    {
	// player collision with monster and killing monster
	gold++;
	points += 50;
	monsterisalive ();
	c.clear ();
	menufield ();
	menucastle ();
	castlehealth ();
	pointdisplay ();
	monsterlocation ();
	playermove (playerl);
    }


    public static void castleattack ()
    {
	// if monster reaches castle hp goes down
	int dmg = (int) (80 * Math.random () + 40);
	castlehp -= dmg;
	monsterisalive ();
	c.clear ();
	menufield ();
	playermove (playerl);
	menucastle ();
	castlehealth ();
	pointdisplay ();
    }


    public static void lose () throws InterruptedException
    {
	//if castle hp goes down to 0 the game is over
	c.setColor (Color.red);
	c.fillRect (180, 160, 360, 120);
	c.setColor (Color.black);
	String pointsstring = Integer.toString (points);
	c.setFont (new Font ("Imprint MT Shadow", Font.BOLD, 30));
	c.drawString ("You Lose", 210, 200);
	c.drawString (pointsstring, 320, 230);
	c.drawString ("Points: ", 210, 230);
	c.drawString ("Return to (M)enu...", 210, 260);
	c.setFont (new Font ("Arial", Font.BOLD, 30));
	int returnmenu = 0;
	while (returnmenu == 0)
	{
	    menulose = c.getChar ();
	    if (menulose == 'm')
	    {
		returnmenu = 1;
	    }
	}
	returnmenu = 0;
	c.clear ();
	menulose = 'x';
	clears = 0;
	started = 1;
    }


    public static void monsterspawn ()
    {
	//creates a new monster on a random line at every time interval
	int randomtime = ((int) (3 * Math.random () + 0)) * 5;
	if (spawn == 0)
	{
	    if (timer % (20 - randomtime) == 0)
	    {
		int mheight = ((int) (3 * Math.random () + 0)) * 50 + 374;
		n++;
		monsterx [n] = 590;
		monstery [n] = mheight;
		spawnt++;
	    }
	}
    }


    public static void monsterisalive ()
    {
	//moves monster off screen if it hits the castle or player
	monsterx [g] = 0;
	monstery [g] = 0;
    }


    public static void monsterloop () throws InterruptedException
    {
	//when the array has run through it resets so more monsters can spawn, but after all the monsters pass
	if (g == 39)
	{
	    spawn = 1;
	    if (monsterx [38] == 100 || (monstery [38] == playerl [1] && monsterx [38] == 140))
	    {
		points += 200;
		gold += 10;
		g = 1;
		c.clear ();
		n = 0;
		spawnt = 0;
		monsterreset ();
		spawn = 0;
	    }
	}
    }


    public static void monsterreset () throws InterruptedException
    {
	//resets the location of all the monsters
	for (int monsterarray = 0 ; monsterarray < monsterx.length ; monsterarray++)
	{
	    monsterx [monsterarray] = 590;
	    monstery [monsterarray] = 0;
	}
	wave++;
    }


    public static void monsterdetermineattack () throws InterruptedException
    {
	//determines if each of the monsters attacks the castle or player
	for (g = 1 ; g < spawnt ; g++)
	{
	    if ((monsterx [g] - 20) == (playerl [0] + 10) && monstery [g] == (playerl [1]))
	    {
		attack ();
	    }
	    if ((monsterx [g] - 30) == 60)
	    {
		castleattack ();
	    }
	}
    }


    public static void moneymenu ()
    {
	//drawing gold amount at menu
	c.setFont (new Font ("Imprint MT Shadow", Font.BOLD, 20));
	c.setColor (goldc);
	String goldint = Integer.toString (gold);
	c.drawString ("Gold:", 280, 310);
	c.drawString (goldint, 340, 310);
	c.setColor (Color.black);
    }


    public static void moneyshop ()
    {
	//drawing gold amount in shop
	c.setFont (new Font ("Imprint MT Shadow", Font.BOLD, 20));
	c.setColor (goldc);
	String goldint = Integer.toString (gold);
	c.drawString ("Gold:", 120, 90);
	c.drawString (goldint, 180, 90);
	c.setColor (Color.black);
    }


    public static void shopbuy ()
    {
	//if enough gold, buying color and chaning playercolor
	buycolor = c.getChar ();
	if ((buycolor == 'r' && gold >= 100) || (redp == 1 && buycolor == 'r'))
	{

	    playercolor = Color.red;
	    if (redp == 0)
	    {
		gold -= 100;
		redp = 1;
	    }
	}
	if ((buycolor == 'b' && gold >= 100) || (bluep == 1 && buycolor == 'b'))
	{
	    playercolor = Color.blue;
	    if (bluep == 0)
	    {
		gold -= 100;
		bluep = 1;

	    }

	}
	if ((buycolor == 'f' && gold >= 300) || (fancyp == 1 && buycolor == 'g'))
	{

	    playercolor = fancy;
	    if (fancyp == 0)
	    {
		fancyp = 1;

		gold -= 300;
	    }

	}


	if (buycolor == 'd')
	{
	    playercolor = Color.black;
	}

	c.clear ();
	shopping ();
	moneyshop ();
	if (buycolor == 'm')
	{
	    c.clear ();
	}
    }
} 





