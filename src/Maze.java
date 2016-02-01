import java.util.HashMap;
import java.util.Map;

import lib.In;
import lib.StdRandom;


public class Maze {
	static final String FILE = "TinyG.txt";
	private int lamp;
	private Graph G;
	private DepthFirstPaths dfp;
	private BreadthFirstPaths bfp;
	
	public Maze(Graph G, int lamp){
		this.G = G;
		this.lamp = lamp;
		dfp = new DepthFirstPaths(G, 0);
		bfp = new BreadthFirstPaths(G, 0);
	}
	
	private Map<String, String> buildMap(boolean bfs){
		Map <String, String> map = new HashMap<String, String>();
		StringBuilder sb = new StringBuilder();
		Iterable <Integer> path;
		if(bfs) path = bfp.pathTo(lamp);
		else	path = dfp.pathTo(lamp);
		if(path==null) return null;
		int l = 0;
		for(int i : path){
			l++;
			sb.replace(0, 0, i+" - ");
		}
		sb.replace(sb.length()-3, sb.length()-1, "");
		
				map.put("path", sb.toString());
				map.put("length", String.valueOf(l));
		if(bfs) map.put("marked", String.valueOf(bfp.markedNumber()));
		else	map.put("marked", String.valueOf(dfp.markedNumber()));
		return map;
	}
	
	public Map<String, String> doDFS(){
		return buildMap(false);
	}
	
	public Map<String, String> doBFS(){
		return buildMap(true);
	}
	
	public static void main(String[] args){
		In in = new In(FILE);
		Graph G = new Graph(in); //читаємо граф з вхідного потоку
		int lamp = StdRandom.uniform(G.V());
		Maze maze = new Maze(G, lamp);
		System.out.println("Start: 0;\nLamp: "+lamp+";\n");
		Map<String, String> dMap = maze.doDFS();
		Map<String, String> bMap = maze.doBFS();
		if(dMap == null && bMap == null){
			System.out.println("Sorry, Mario, your lamp is in another componennt of maze.");
			return;
		}
		System.out.println("DEPTH SEARCH: ");
		System.out.println("Path: "+dMap.get("path"));
		System.out.println("Length: "+dMap.get("length"));
		System.out.println("Marked vertices: "+dMap.get("marked"));
		System.out.println();
		
		System.out.println("BREADTH SEARCH: ");
		System.out.println("Path: "+bMap.get("path"));
		System.out.println("Length: "+bMap.get("length"));
		System.out.println("Marked vertices: "+bMap.get("marked"));
	}
}
