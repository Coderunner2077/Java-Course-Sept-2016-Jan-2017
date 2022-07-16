import fr.first.step.A;
import fr.first.step.B;

/*
 * 													Les packages
 * 
 * Les packages sont comme des dossiers permettant de ranger mes classes. Charger un package me permet d'utiliser
 * les classes qu'il contient.
 * 
 * I./ Création d'un package
 * L'un des avantages des packages est que je vais gagner en lisibilité dans mon package par défaut, mais aussi 
 * que les classes mises dans un package sont plus facilement transportables d'une application à l'autre. Pour cela,
 * il me suffit  d'inclure le dossier de mon package dans un projet et d'y importer les classes qui m'intéressent.
 * Pour créer un package : New > Package.
 * Le nom à donner au package doit obéir à la convention de nommage pour les packages:
 * 		-	entièrement en minuscules
 * 		-	caractères alphanumériques (de a à z, de 0 à 9), et des points éventuellement (.)
 * 		-	tout package doit commencer par com, edu, mil, net, org ou les deux lettres identifiant un pays ("fr"
 * 			correspond à la France, "en" correspond à l'Angleterre)
 * 		-	aucun mot clé Java ne doit être présent dans le nom, sauf si je le fais suivre par un underscore,
 * 			comme ceci : com.sdz.package_
 * 
 * Pour info, la portée de mes classes est affectée par les packages
 * 
 * II./ Droit d'accès entre les packages
 * 
 * Lorsque je crée ma première classe, Eclipse met systèmatiquement le mot clé public devant la déclaration de la
 * classe. Il faut savoir que public class Ville et class Ville sont sensiblement différents et que le mot clé 
 * public influe sur la portée de ma classe. En fait, une classe déclarée public sera visible même à l'extrérieur de
 * son package, les autres ne seront visibles que depuis l'intérieur du package : on dit que leur portée est
 * default.
 * Illustration:
 * Je vais maintenat créer un second package, appelé "fr.second.step". Dans le 1er package, je crée une classe A
 * de portée public et une classe B de portée default (avec des variables d'instance public), comme ceci :
 * Note : les classes contenues dans un package ont en toute première instruction la déclaration de ce package.
 * Ensuite, toujours dans le 1er package, je crée une classe Main contenant la méthode main. 
 * J'écris dans Main :
 	A a = new A();
	B b = new B();
 * Constat : je remarque qu'il n'y a aucun problème de compilation.
 * Maintenant, dans le second package, je crée une classe Main contenant la méthode main et je fais un copier-coller
 * de l'autre Main (du premier package, tout en important les classes A et B).
 * Constat : boucoup problème. L'Eclipse n'aime ni l'instruction "import fr.first.step.B;", ni l'instruction
 * "B b = new B();". Cela est dû à la déclaration de ma classe. Y aura le même problème si j'essaye de modifier
 * la variable d'instance de l'objet A. Donc, ceci :
 *  a.b.str = "toto"  --> n'est pas non plus autorisé dans le second package.
 *  La seule façon de corriger le problème est de déclarer la classe B public (seule la classe A était déclarée 
 *  ainsi).
 *  
 *  Conclusion : 
 *  	Les classes déclarées public sont visibles depuis l'extérieur du package qui les contient.
 *  	Les classes n'ayant pas été déclarées public ne sont pas visibles depuis l'extérieur du package qui les 
 *  contient.
 *  	Si une classe déclarée public dans son package a une variable d'un type ayant une portée default, cette
 *  dernière ne pourra pas être modifiée depuis l'extérieur de son package.
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
