import java.lang.reflect.Array;

import lib.Bag;
import lib.In;
import lib.MinPQ;

public class Graph {
	
	private final int V;		// number of vertices
	private int E;				// number of edges
	private MinPQ<Integer>[] adj;	// adjusting vertices
	
	/**
	 * Створюємо порожній граф розмірності V 
	 * ініціалізуємо масив порожніми списками типу Bag
	 * @param V - кількість вершин
	 */
	public Graph(int V){
		this.V=V; this.E = 0;
		Class<?> c = null;
		try {
			c = Class.forName("lib.MinPQ");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		adj = (MinPQ<Integer>[]) Array.newInstance(c, V);
		for (int v=0; v<V; v++)
			adj[v] = new MinPQ<Integer>();
	}
	
	public Graph (In in){
		this(in.readInt());
		int E = in.readInt();
		for(int i = 0; i < E; i++){
			int v = in.readInt();
			int w = in.readInt();
			addEdge(v, w);
		}
	}
	
	public int V()	{	return V;	}
	public int E()	{	return E;	}
	
	/**
	 * додаємо ребро між двома вершинами
	 * @param v - вершина
	 * @param w - вершина
	 */
	public void addEdge(int v, int w){
		adj[v].insert(w);
		adj[w].insert(v);
		E++;
	}
	
	/**
	 * @param v - вершина графу
	 * @return - ступінь вершини графу v
	 */
	public int degree(int v){
		int degree = 0;
		for (int w : adj(v))
			degree++;
		return degree;
	}
	
	/**
	 * ітератор по вершинам суміжним з вершиною v
	 * @param v - вершина
	 * @return - ітератор по мішку суміжних з v вершин
	 */
	public Iterable<Integer> adj(int v){
		return adj[v];
	}
}
