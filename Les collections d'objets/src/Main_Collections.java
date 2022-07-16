/*
 * 											LES COLLECTIONS D'OBJETS
 * 
 * En effet, on peut stocker des données ailleurs que dans les tableaux. Les collections d'objets sont d'ailleurs
 * dynamiques : elles n'ont pas de taille prédéfinie ==> impossible de dépasser leur capacité.
 * Les objets que je vais aborder ici sont tous dans le package java.util
 * Les collections sont primordiales dans Java.
 * 
 * I./ Les différents types de collection
 * Voici un petit diagramme de classes schématisant la hiérarchie d'interfaces composant ce qu'on appelle les 
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
 * Oui, il s'agit bien d'interfaces, celles-ci encapsulent la majeure partie des méthodes utilisables avec toutes
 * les implémentations concrètes.
 * Je peux voir qu'il existe plusieurs types de collections, que les interfaces List et Set implémentent directement
 * l'interface Collection et que l'interface Map gravite autour de cette hiérarchie, tout en faisant partie des
 * collections Java.
 * Comme on le vera par la suite, ces interfaces ont des particularités correspondant à des besoins spécifiques. Les
 * objets de type List servent à stocker des objets sans condition particulière sur la façon de les stocker. Ils 
 * acceptent toutes les valeurs, même les valeurs null. Les types Set sont un peu plus restrictifs, car ils 
 * n'autorisent pas deux fois la même valeur (le même objet), ce qui est pratique pour une liste d'éléments uniques,
 * par exemple. Les Map sont particulières car elles fonctionnement avec un système clé - valeur pour ranger et 
 * retrouver les objets qu'elles contiennent.
 * Voyons comment utiliser ces objets...
 * 
 * II./ Les objets List
 * 
 * Les objets appartenant à List sont, pour simplifier, des tableaux extensibles à volonté. On y trouve des 
 * Vector, LinkedList et ArrayList. On peut y insérer autant d'éléments qu'on le souhaite sans craindre de 
 * dépasser la taille du tableau. Ils fonctionnent tous de la même manière : je peux récupérer les éléments de la 
 * liste via leurs indices. De plus, les List contiennent des objets.
 * Voici deux objets très utiles de ce type : LinkedList et ArrayList
 * 
 * 1.) L'objet LinkedList
 * 
 * Une liste chaînée (LinkedList en anglais) est une liste dont chaque élément est lié aux éléments adjacents par
 * une référence à ces derniers. Chaque élément contient une référence à l'élément précédent et à l'élément suivant,
 * exceptés le premier, dont l'élément précédent vaut null, et le dernier, dont l'élément suivant vaut également
 * null.  
 * Remarque : il s'agit ici d'une liste doublement chaînée (contrairement à celle que j'ai apprise en C).
 * Réf main 225 je fais afficher tous les éléments d'une liste grâce à la fonction get()
 * Autre chose à savoir sur ce genre d'objets : ceux-ci implémentent l'interface ListIterator. Ainsi, je peux
 * utiliser cette interface pour lister ma LinkedList (réf main 230)
 * 
 * 2.) Un itérateur
 * 
 * Un itérateur est un objet qui a pour rôle de parcourir une collection. C'est d'ailleurs son unique raison d'être.
 * Pour être tout à fait précis, l'utilisation des itérateurs dans Java fonctionne de la même manière que le 
 * pattern du même nom. Tout comme j'ai pu le voir avec la pattern strategy, les design patterns sont en fait
 * des modèles de conception d'objets permettant une meilleure stabilité et une réutilisabilité accrue. Les 
 * itérateurs en font partie.
 * Tout d'abord je dois associer un itérateur à une liste de cette manière :
 * ListIterator li = liste.listIterator();
 * Voici les méthodes pour parcourir la liste avec l'itérateur :
 * 		-	hasNext() : retourne "vrai" si l'élément suivant est non null (à mettre dans la condition de la boucle)
 * 		-	next() : retourne la valeur de l'élément suivant (et donc parcourt la liste vers l'avant)
 * 		-	hasPrevious() : renvoie "vrai" si l'élément précédant n'est pas null
 * 		-	previous() : retourne la valeur de l'élément précédent (et donc parcourt la liste vers l'arrière)
 * 
 * Réf main 230 parcours avec l'itérateur
 * Les deux manières de procéder sont analogues.
 * Attention : vu que tous les éléments de la liste contiennent une référence à l'élément suivant, de telles listes
 * risquent de devenir particulièrement lourdes en grandissant. Cependant, elles sont adaptées lorsqu'il faut bcp
 * manipuler une collection en supprimant ou en ajoutant des objets en milieu de liste. Elles sont donc à utiliser
 * avec précaution.
 * 
 * 3.) ArrayList
 * 
 * Voici un objet bien pratique. ArrayList est un de ces objets qui n'ont pas de taille limite et qui en plus 
 * acceptent n'importe quel type de données, y compris null (LinkedList aussi !). On peut mettre tout ce qu'on
 * veut dans un ArrayList.
 * Mais la principale différence avec LinkedList, c'est le fait que les ArrayList sont rapides en lecture, même
 * avec un gros volume d'objets. Elles sont cependant plus lentes si je dois ajouter ou supprimer des données en
 * milieu de liste. Pour résumer à l'extrême, si j'effectue bcp de lectures sans me soucier de l'ordre des 
 * éléments, j'opte pour une ArrayList; en revanche, si j'insère bcp de données au milieu de la liste, j'opte
 * pour une LinkedList.
 * 
 * 4.) Méthodes des List
 * 
 * 		-	size() : retourne le nombre des cases de la liste
 * 		-	add() : permet d'ajouter un élément
 * 		-	get(int index) : retourne l'élément à l'indice demandé
 * 		-	remove(int index) : efface l'élément à l'indice demandé
 * 		-	isEmpty() : renvoie "vrai" si l'objet est vide
 * 		-	removeAll(Collection) : efface tout le contenu de l'objet
 * 		-	contains(Object element) : retourne "vrai" si l'objet passé en paramètre est dans la liste
 * 
 * III./ Les objets Map
 * 
 * Une collection de type Map est une collection qui fonctionne avec un couple clé - valeur. On y trouve des 
 * objets Hashtable, HashMap, TreeMap, WeakHashMap... La clé, qui sert à identifier une entrée dans une collection
 * donnée, est unique. La valeur, au contraire, peut être associée à plusieurs clés.
 * Ces objets ont comme point faible majeur leur rapport conflictuel avec la taille des données à stocker. En effet,
 * plus on aura de valeurs à mettre dans un objet Map, plus celles-ci seront lentes et lourdes : logique, puisque
 * par rapport aux autres collections, il stocke une donnée supplémentaire par enregistrement. Une donnée, c'est
 * de la mémoire en plus, et la "mémoire, c'est sacré" (les applications Java n'étant pas forcément destinées à 
 * des appareils bénéficiant de bcp de mémoire).
 * 
 * 1.) L'objet Hashtable
 * 
 * C'est la table de hachage... On parcourt cet objet grâce aux clés qu'il contient en recourant à la classe 
 * Enumeration (pas Enum !). L'objet Enumeration contient notre Hashtable et permet de le parcourir très simplement
 * grâce à des méthodes toutes prêtes.
 * Voici comment on associe un objet Enumération aux valeurs (et non les clés ) d'une table de hachage (ici, ht):
 * Enumeration e =  ht.elements();
 * Voici les deux méthodes de l'objet Enumeration qui permettent de parcourir une table de hashage :
 * 		-	hasMoreElements() : renvoie "true" si l'objet contient encore au moins un élément
 * 		-	nextElement() : renvoie la valeur de l'élément suivant (et donc parcourt la table de hashage)
 * 
 * Voici maintenant les méthodes offertes par l'objet Hashtable :
 * 		-	isEmpty : retourne "vrai" si l'objet est vide
 * 		-	contains(Object value) : retourne "vrai" si la valeur est présente
 * 		-	containsValue(Object value) : idem
 * 		-	containsKey(Object key) : retourne "vrai" si la clé passée en paramètre est présente dans la Hashtable
 * 		-	put(Object key, Object value) : ajoute le couple key - value dans l'objet
 * 		-	elements() : retourne une énumération des éléments de l'objet
 * 		-	keys() : retourne la liste des clés sous forme d'énumération
 * 		-   remove(Object key) : efface la clé passé en paramètre ainsi que la valeur correspondante
 * 
 * De plus, il faut savoir qu'un objet Hashtable n'accepte pas la valeur null et qu'il est Thread safe, i.e. il est
 * utilisable dans plusieurs threads (cela signifie que plusieurs éléments de mon programme peuvent l'utiliser 
 * simultanément) simultanément sans qu'il y ait un risque de conflit de données.
 * 
 * 2.) L'objet HashMap
 * 
 * Cet objet ne diffère que très peu de la Hashtable :
 * 		-	il accepte les valeurs null
 * 		-	il n'est pas thread safe
 * 
 * En fait, les deux objets de type Map sont, à peu de choses près, équivalents... ou pas...
 * La principale différence entre les deux : impossible de parcourir les valeurs du HashMap en recourant à une
 * Enumeration comme pour Hashtable.
 * 
 * IV./ Les objets Set
 * 
 * 1.) Quelques points de repères
 * 
 * Un Set est une collection qui n'accepte pas les doublons. Par exemple, elle n'accepte qu'une seule fois null, car
 * deux valeurs null sont considérées comme un doublon. On trouve parmi les objets Set les objets HashSet, TreeSet,
 * LinkedHashSet... Certains Set sont plus restrictifs que d'autres : il en existe qui n'acceptent pas null, certains
 * types d'objets, etc.
 * Les Set sont particulièrement adaptés pour manipuler une grande quantité de données. Cependant, les performances
 * de ceux-ci peuvent être amoindries en insertion. Généralement, on opte pour un HashSet, car il est plus
 * performant en temps d'accès, mais si j'ai besoin que ma collection soit constamment triée, il vaut mieux
 * opter pour un TreeSet.
 * 
 * 2.) L'objet HashSet
 * 
 * C'est sans nul doute la plus utilisée de l'interface Set. On peut parcourir ce type de collection avec un objet
 * Iterator ou extraire de cet objet un tableau d'Object.
 * Pour parcourir les valeurs d'un objet HashSet avec un itérateur, j'importe d'abord un Iterator (et non pas un
 * ListIterator) du java.util, et j'associe l'itérateur à l'objet (ici, hs) comme ceci :
 * Iterator it = hs.iterator();
 * Ensuite je fais une boucle :
 * while(it.hasNext())
 * 		System.out.println(it.next())
 * Comme avec ListIterator pour LinkedList donc, sauf qu'il n'y a pas moyen de parcourir les valeurs dans l'autre 
 * sens (à ce que je vois).
 * Pour extraire de l'objet HashSet un tableau d'Object, et parcourir les valeurs du tableau, je dois procéder 
 * comme suit :
 * Object[] obj = hs.toArray();
 * for(Object o : obj)
 * 		System.out.println(o);
 * 
 * 3) Les méthodes de l'objet HashSet
 * 
 * Voici la liste :
 * 		-	add() : ajoute un élément
 * 		-	contains(Object value) : retourne "vrai" si l'objet contient value
 * 		-	isEmpty() : retourne "vrai" si l'objet est vide
 * 		-	iterator() : renvoie un objet de type Iterator
 * 		-	remove(Object o) : retire l'objet o de la collection
 * 		-	toArray() : retourne un tableau d'Object
 * 
 * RESUMé :
 * 
 * 		-	Une collection permet de stocker un nombre variable d'objets
 * 		-	Il y a principalement trois types de collection : les List, les Set et les Map
 * 		-	Chaque type a ses avantages et ses inconvénients
 * 		-	Les Collection stockent des objets alors que les Map stockent un couple clé - valeur
 * 		-	Si on insère fréquemment des données en milieu de liste ==> utiliser une LinkedList
 * 		-	Si on veut rechercher ou accéder à une valeur via une clé de recherche ==> opter pour une collection de
 * type Map
 * 		-	S'il y a une grande quantité de données à traiter ==> opter pour une liste de type Set
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
			System.out.println("Elément à l'index "+ i + " = " + liste.get(i));
		System.out.println("---------- Parcours avec un itérateur -----------");
		ListIterator li = liste.listIterator();
		System.out.println("La liste contient un élément suivant : " + li.hasNext());
		while(li.hasNext())
			System.out.println(li.next());
		while(li.hasPrevious())
			System.out.println(li.previous());
		System.out.println("La liste contient un élément suivant : " + li.hasNext());
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
		ht.put(10, "été");
		ht.put(12, "automn");
		ht.put(45, "hiver");
		ht.put(50, "une phrase très très longue");
		
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
