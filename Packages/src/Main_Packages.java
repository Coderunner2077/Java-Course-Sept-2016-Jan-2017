import fr.first.step.A;
import fr.first.step.B;

/*
 * 													Les packages
 * 
 * Les packages sont comme des dossiers permettant de ranger mes classes. Charger un package me permet d'utiliser
 * les classes qu'il contient.
 * 
 * I./ Cr�ation d'un package
 * L'un des avantages des packages est que je vais gagner en lisibilit� dans mon package par d�faut, mais aussi 
 * que les classes mises dans un package sont plus facilement transportables d'une application � l'autre. Pour cela,
 * il me suffit  d'inclure le dossier de mon package dans un projet et d'y importer les classes qui m'int�ressent.
 * Pour cr�er un package : New > Package.
 * Le nom � donner au package doit ob�ir � la convention de nommage pour les packages:
 * 		-	enti�rement en minuscules
 * 		-	caract�res alphanum�riques (de a � z, de 0 � 9), et des points �ventuellement (.)
 * 		-	tout package doit commencer par com, edu, mil, net, org ou les deux lettres identifiant un pays ("fr"
 * 			correspond � la France, "en" correspond � l'Angleterre)
 * 		-	aucun mot cl� Java ne doit �tre pr�sent dans le nom, sauf si je le fais suivre par un underscore,
 * 			comme ceci : com.sdz.package_
 * 
 * Pour info, la port�e de mes classes est affect�e par les packages
 * 
 * II./ Droit d'acc�s entre les packages
 * 
 * Lorsque je cr�e ma premi�re classe, Eclipse met syst�matiquement le mot cl� public devant la d�claration de la
 * classe. Il faut savoir que public class Ville et class Ville sont sensiblement diff�rents et que le mot cl� 
 * public influe sur la port�e de ma classe. En fait, une classe d�clar�e public sera visible m�me � l'extr�rieur de
 * son package, les autres ne seront visibles que depuis l'int�rieur du package : on dit que leur port�e est
 * default.
 * Illustration:
 * Je vais maintenat cr�er un second package, appel� "fr.second.step". Dans le 1er package, je cr�e une classe A
 * de port�e public et une classe B de port�e default (avec des variables d'instance public), comme ceci :
 * Note : les classes contenues dans un package ont en toute premi�re instruction la d�claration de ce package.
 * Ensuite, toujours dans le 1er package, je cr�e une classe Main contenant la m�thode main. 
 * J'�cris dans Main :
 	A a = new A();
	B b = new B();
 * Constat : je remarque qu'il n'y a aucun probl�me de compilation.
 * Maintenant, dans le second package, je cr�e une classe Main contenant la m�thode main et je fais un copier-coller
 * de l'autre Main (du premier package, tout en important les classes A et B).
 * Constat : boucoup probl�me. L'Eclipse n'aime ni l'instruction "import fr.first.step.B;", ni l'instruction
 * "B b = new B();". Cela est d� � la d�claration de ma classe. Y aura le m�me probl�me si j'essaye de modifier
 * la variable d'instance de l'objet A. Donc, ceci :
 *  a.b.str = "toto"  --> n'est pas non plus autoris� dans le second package.
 *  La seule fa�on de corriger le probl�me est de d�clarer la classe B public (seule la classe A �tait d�clar�e 
 *  ainsi).
 *  
 *  Conclusion : 
 *  	Les classes d�clar�es public sont visibles depuis l'ext�rieur du package qui les contient.
 *  	Les classes n'ayant pas �t� d�clar�es public ne sont pas visibles depuis l'ext�rieur du package qui les 
 *  contient.
 *  	Si une classe d�clar�e public dans son package a une variable d'un type ayant une port�e default, cette
 *  derni�re ne pourra pas �tre modifi�e depuis l'ext�rieur de son package.
 * 
 * 
 */
public class Main_Packages {

	public static void main(String[] args) {
		A a = new A();
		System.out.println(a.toString());
		B b = new B();
		System.out.println(a.protectedString);

	}

}
