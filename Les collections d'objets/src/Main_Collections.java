/*
 * 											LES COLLECTIONS D'OBJETS
 * 
 * En effet, on peut stocker des donn�es ailleurs que dans les tableaux. Les collections d'objets sont d'ailleurs
 * dynamiques : elles n'ont pas de taille pr�d�finie ==> impossible de d�passer leur capacit�.
 * Les objets que je vais aborder ici sont tous dans le package java.util
 * Les collections sont primordiales dans Java.
 * 
 * I./ Les diff�rents types de collection
 * Voici un petit diagramme de classes sch�matisant la hi�rarchie d'interfaces composant ce qu'on appelle les 
 * collections :
 * 
 * 						<<interface>>									<<interface>>	
 * 						  Collection										 Map
 * 							  /|\											 /|\
 * 							   |											  |
 * 							   |											  |
 * 			--------------------------------------							  |
 * 			|									 |							  |
 * 			|									 |							  |
 * 			|									 |							  |
 * 			|									 |							  |
 *    <<interface>>					       <<interface>>				<<interface>>
 *    	  List									Set						   SortedMap
 *    											/|\
 *    											 |
 *    											 |
 *    											 |
 *    											 |
 *    									   <<interface>>
 *    										 SortedSet
 * 
 * 
 * Oui, il s'agit bien d'interfaces, celles-ci encapsulent la majeure partie des m�thodes utilisables avec toutes
 * les impl�mentations concr�tes.
 * Je peux voir qu'il existe plusieurs types de collections, que les interfaces List et Set impl�mentent directement
 * l'interface Collection et que l'interface Map gravite autour de cette hi�rarchie, tout en faisant partie des
 * collections Java.
 * Comme on le vera par la suite, ces interfaces ont des particularit�s correspondant � des besoins sp�cifiques. Les
 * objets de type List servent � stocker des objets sans condition particuli�re sur la fa�on de les stocker. Ils 
 * acceptent toutes les valeurs, m�me les valeurs null. Les types Set sont un peu plus restrictifs, car ils 
 * n'autorisent pas deux fois la m�me valeur (le m�me objet), ce qui est pratique pour une liste d'�l�ments uniques,
 * par exemple. Les Map sont particuli�res car elles fonctionnement avec un syst�me cl� - valeur pour ranger et 
 * retrouver les objets qu'elles contiennent.
 * Voyons comment utiliser ces objets...
 * 
 * II./ Les objets List
 * 
 * Les objets appartenant � List sont, pour simplifier, des tableaux extensibles � volont�. On y trouve des 
 * Vector, LinkedList et ArrayList. On peut y ins�rer autant d'�l�ments qu'on le souhaite sans craindre de 
 * d�passer la taille du tableau. Ils fonctionnent tous de la m�me mani�re : je peux r�cup�rer les �l�ments de la 
 * liste via leurs indices. De plus, les List contiennent des objets.
 * Voici deux objets tr�s utiles de ce type : LinkedList et ArrayList
 * 
 * 1.) L'objet LinkedList
 * 
 * Une liste cha�n�e (LinkedList en anglais) est une liste dont chaque �l�ment est li� aux �l�ments adjacents par
 * une r�f�rence � ces derniers. Chaque �l�ment contient une r�f�rence � l'�l�ment pr�c�dent et � l'�l�ment suivant,
 * except�s le premier, dont l'�l�ment pr�c�dent vaut null, et le dernier, dont l'�l�ment suivant vaut �galement
 * null.  
 * Remarque : il s'agit ici d'une liste doublement cha�n�e (contrairement � celle que j'ai apprise en C).
 * R�f main 225 je fais afficher tous les �l�ments d'une liste gr�ce � la fonction get()
 * Autre chose � savoir sur ce genre d'objets : ceux-ci impl�mentent l'interface ListIterator. Ainsi, je peux
 * utiliser cette interface pour lister ma LinkedList (r�f main 230)
 * 
 * 2.) Un it�rateur
 * 
 * Un it�rateur est un objet qui a pour r�le de parcourir une collection. C'est d'ailleurs son unique raison d'�tre.
 * Pour �tre tout � fait pr�cis, l'utilisation des it�rateurs dans Java fonctionne de la m�me mani�re que le 
 * pattern du m�me nom. Tout comme j'ai pu le voir avec la pattern strategy, les design patterns sont en fait
 * des mod�les de conception d'objets permettant une meilleure stabilit� et une r�utilisabilit� accrue. Les 
 * it�rateurs en font partie.
 * Tout d'abord je dois associer un it�rateur � une liste de cette mani�re :
 * ListIterator li = liste.listIterator();
 * Voici les m�thodes pour parcourir la liste avec l'it�rateur :
 * 		-	hasNext() : retourne "vrai" si l'�l�ment suivant est non null (� mettre dans la condition de la boucle)
 * 		-	next() : retourne la valeur de l'�l�ment suivant (et donc parcourt la liste vers l'avant)
 * 		-	hasPrevious() : renvoie "vrai" si l'�l�ment pr�c�dant n'est pas null
 * 		-	previous() : retourne la valeur de l'�l�ment pr�c�dent (et donc parcourt la liste vers l'arri�re)
 * 
 * R�f main 230 parcours avec l'it�rateur
 * Les deux mani�res de proc�der sont analogues.
 * Attention : vu que tous les �l�ments de la liste contiennent une r�f�rence � l'�l�ment suivant, de telles listes
 * risquent de devenir particuli�rement lourdes en grandissant. Cependant, elles sont adapt�es lorsqu'il faut bcp
 * manipuler une collection en supprimant ou en ajoutant des objets en milieu de liste. Elles sont donc � utiliser
 * avec pr�caution.
 * 
 * 3.) ArrayList
 * 
 * Voici un objet bien pratique. ArrayList est un de ces objets qui n'ont pas de taille limite et qui en plus 
 * acceptent n'importe quel type de donn�es, y compris null (LinkedList aussi !). On peut mettre tout ce qu'on
 * veut dans un ArrayList.
 * Mais la principale diff�rence avec LinkedList, c'est le fait que les ArrayList sont rapides en lecture, m�me
 * avec un gros volume d'objets. Elles sont cependant plus lentes si je dois ajouter ou supprimer des donn�es en
 * milieu de liste. Pour r�sumer � l'extr�me, si j'effectue bcp de lectures sans me soucier de l'ordre des 
 * �l�ments, j'opte pour une ArrayList; en revanche, si j'ins�re bcp de donn�es au milieu de la liste, j'opte
 * pour une LinkedList.
 * 
 * 4.) M�thodes des List
 * 
 * 		-	size() : retourne le nombre des cases de la liste
 * 		-	add() : permet d'ajouter un �l�ment
 * 		-	get(int index) : retourne l'�l�ment � l'indice demand�
 * 		-	remove(int index) : efface l'�l�ment � l'indice demand�
 * 		-	isEmpty() : renvoie "vrai" si l'objet est vide
 * 		-	removeAll(Collection) : efface tout le contenu de l'objet
 * 		-	contains(Object element) : retourne "vrai" si l'objet pass� en param�tre est dans la liste
 * 
 * III./ Les objets Map
 * 
 * Une collection de type Map est une collection qui fonctionne avec un couple cl� - valeur. On y trouve des 
 * objets Hashtable, HashMap, TreeMap, WeakHashMap... La cl�, qui sert � identifier une entr�e dans une collection
 * donn�e, est unique. La valeur, au contraire, peut �tre associ�e � plusieurs cl�s.
 * Ces objets ont comme point faible majeur leur rapport conflictuel avec la taille des donn�es � stocker. En effet,
 * plus on aura de valeurs � mettre dans un objet Map, plus celles-ci seront lentes et lourdes : logique, puisque
 * par rapport aux autres collections, il stocke une donn�e suppl�mentaire par enregistrement. Une donn�e, c'est
 * de la m�moire en plus, et la "m�moire, c'est sacr�" (les applications Java n'�tant pas forc�ment destin�es � 
 * des appareils b�n�ficiant de bcp de m�moire).
 * 
 * 1.) L'objet Hashtable
 * 
 * C'est la table de hachage... On parcourt cet objet gr�ce aux cl�s qu'il contient en recourant � la classe 
 * Enumeration (pas Enum !). L'objet Enumeration contient notre Hashtable et permet de le parcourir tr�s simplement
 * gr�ce � des m�thodes toutes pr�tes.
 * Voici comment on associe un objet Enum�ration aux valeurs (et non les cl�s ) d'une table de hachage (ici, ht):
 * Enumeration e =  ht.elements();
 * Voici les deux m�thodes de l'objet Enumeration qui permettent de parcourir une table de hashage :
 * 		-	hasMoreElements() : renvoie "true" si l'objet contient encore au moins un �l�ment
 * 		-	nextElement() : renvoie la valeur de l'�l�ment suivant (et donc parcourt la table de hashage)
 * 
 * Voici maintenant les m�thodes offertes par l'objet Hashtable :
 * 		-	isEmpty : retourne "vrai" si l'objet est vide
 * 		-	contains(Object value) : retourne "vrai" si la valeur est pr�sente
 * 		-	containsValue(Object value) : idem
 * 		-	containsKey(Object key) : retourne "vrai" si la cl� pass�e en param�tre est pr�sente dans la Hashtable
 * 		-	put(Object key, Object value) : ajoute le couple key - value dans l'objet
 * 		-	elements() : retourne une �num�ration des �l�ments de l'objet
 * 		-	keys() : retourne la liste des cl�s sous forme d'�num�ration
 * 		-   remove(Object key) : efface la cl� pass� en param�tre ainsi que la valeur correspondante
 * 
 * De plus, il faut savoir qu'un objet Hashtable n'accepte pas la valeur null et qu'il est Thread safe, i.e. il est
 * utilisable dans plusieurs threads (cela signifie que plusieurs �l�ments de mon programme peuvent l'utiliser 
 * simultan�ment) simultan�ment sans qu'il y ait un risque de conflit de donn�es.
 * 
 * 2.) L'objet HashMap
 * 
 * Cet objet ne diff�re que tr�s peu de la Hashtable :
 * 		-	il accepte les valeurs null
 * 		-	il n'est pas thread safe
 * 
 * En fait, les deux objets de type Map sont, � peu de choses pr�s, �quivalents... ou pas...
 * La principale diff�rence entre les deux : impossible de parcourir les valeurs du HashMap en recourant � une
 * Enumeration comme pour Hashtable.
 * 
 * IV./ Les objets Set
 * 
 * 1.) Quelques points de rep�res
 * 
 * Un Set est une collection qui n'accepte pas les doublons. Par exemple, elle n'accepte qu'une seule fois null, car
 * deux valeurs null sont consid�r�es comme un doublon. On trouve parmi les objets Set les objets HashSet, TreeSet,
 * LinkedHashSet... Certains Set sont plus restrictifs que d'autres : il en existe qui n'acceptent pas null, certains
 * types d'objets, etc.
 * Les Set sont particuli�rement adapt�s pour manipuler une grande quantit� de donn�es. Cependant, les performances
 * de ceux-ci peuvent �tre amoindries en insertion. G�n�ralement, on opte pour un HashSet, car il est plus
 * performant en temps d'acc�s, mais si j'ai besoin que ma collection soit constamment tri�e, il vaut mieux
 * opter pour un TreeSet.
 * 
 * 2.) L'objet HashSet
 * 
 * C'est sans nul doute la plus utilis�e de l'interface Set. On peut parcourir ce type de collection avec un objet
 * Iterator ou extraire de cet objet un tableau d'Object.
 * Pour parcourir les valeurs d'un objet HashSet avec un it�rateur, j'importe d'abord un Iterator (et non pas un
 * ListIterator) du java.util, et j'associe l'it�rateur � l'objet (ici, hs) comme ceci :
 * Iterator it = hs.iterator();
 * Ensuite je fais une boucle :
 * while(it.hasNext())
 * 		System.out.println(it.next())
 * Comme avec ListIterator pour LinkedList donc, sauf qu'il n'y a pas moyen de parcourir les valeurs dans l'autre 
 * sens (� ce que je vois).
 * Pour extraire de l'objet HashSet un tableau d'Object, et parcourir les valeurs du tableau, je dois proc�der 
 * comme suit :
 * Object[] obj = hs.toArray();
 * for(Object o : obj)
 * 		System.out.println(o);
 * 
 * 3) Les m�thodes de l'objet HashSet
 * 
 * Voici la liste :
 * 		-	add() : ajoute un �l�ment
 * 		-	contains(Object value) : retourne "vrai" si l'objet contient value
 * 		-	isEmpty() : retourne "vrai" si l'objet est vide
 * 		-	iterator() : renvoie un objet de type Iterator
 * 		-	remove(Object o) : retire l'objet o de la collection
 * 		-	toArray() : retourne un tableau d'Object
 * 
 * RESUM� :
 * 
 * 		-	Une collection permet de stocker un nombre variable d'objets
 * 		-	Il y a principalement trois types de collection : les List, les Set et les Map
 * 		-	Chaque type a ses avantages et ses inconv�nients
 * 		-	Les Collection stockent des objets alors que les Map stockent un couple cl� - valeur
 * 		-	Si on ins�re fr�quemment des donn�es en milieu de liste ==> utiliser une LinkedList
 * 		-	Si on veut rechercher ou acc�der � une valeur via une cl� de recherche ==> opter pour une collection de
 * type Map
 * 		-	S'il y a une grande quantit� de donn�es � traiter ==> opter pour une liste de type Set
 * 
 * 
 */
import java.util.List;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
public class Main_Collections {

	public static void main(String[] args) {
		List liste = new LinkedList();
		liste.add(12);
		liste.add("toto");
		liste.add(12.20f);
		liste.add(null);
		liste.add('c');
		for(int i = 0; i < liste.size(); i++)
			System.out.println("El�ment � l'index "+ i + " = " + liste.get(i));
		System.out.println("---------- Parcours avec un it�rateur -----------");
		ListIterator li = liste.listIterator();
		System.out.println("La liste contient un �l�ment suivant : " + li.hasNext());
		while(li.hasNext())
			System.out.println(li.next());
		while(li.hasPrevious())
			System.out.println(li.previous());
		System.out.println("La liste contient un �l�ment suivant : " + li.hasNext());
		liste.remove(2);
		System.out.println("La liste contient le nombre 12 : " + liste.contains(12));
		System.out.println("La liste est vide : " + liste.isEmpty());
		for(int i = 0; i < liste.size(); i++)
			System.out.println("liste["+i+"] = "+liste.get(i));
		liste.removeAll(liste);
		liste.add(0);
		for(int i = 0; i < liste.size(); i++)
			System.out.println(liste.get(i));
		System.out.println("----------------------------------------------------------");
		System.out.println("L'objet Hashtable");
		Hashtable ht = new Hashtable();
		ht.put(1, "printemps");
		ht.put(10, "�t�");
		ht.put(12, "automn");
		ht.put(45, "hiver");
		ht.put(50, "une phrase tr�s tr�s longue");
		
		Enumeration e = ht.elements();
		while(e.hasMoreElements())
			System.out.println(e.nextElement());
		HashMap hm = new HashMap();
		hm.put(0, "spring");
		hm.put(1, "summer");
		hm.put(2, "fall");
		hm.put(3, "winter");
		System.out.println("----------------------------------------------------------");
		System.out.println("L'objet HashSet");
		HashSet hs = new HashSet();
		hs.add("toto");
		hs.add(14);
		hs.add('d');
		Iterator it = hs.iterator();
		System.out.println("Parcours avec un Iterator");
		while(it.hasNext())
			System.out.println(it.next());

		System.out.println("----------------------------------");
		System.out.println("Parcours avec un tableau d'objet");
		Object[] obj = hs.toArray();
		for(Object o : obj)
			System.out.println(o);
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		Hashtable hcht = new Hashtable();
		hcht.put(1223232, "lundi");
		hcht.put(341, "mardi");
		hcht.put(492, "mercredi");
		hcht.put("dsdsd4343", "doidfd");
		hcht.put(309, "jeudi");
		System.out.println(hcht.get(309));
		hcht.remove(309);
		Enumeration en = hcht.elements();
		while(en.hasMoreElements())
			System.out.println(en.nextElement());
		HashSet hst = new HashSet();
		hst.add("un");
		hst.add("deux");
		hst.add("trois");
		hst.add("quatre");
		Iterator ite = hst.iterator();
		while(ite.hasNext())
			System.out.println(ite.next());
		Object[] ob = hst.toArray();
		for(Object o : ob)
			System.out.println(o);
		

	}

}
