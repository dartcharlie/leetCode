import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by ZSong on 8/3/16.
 * Different presentations of Graph data structure
 */
public class Graph<T> {
  public static class Vertex<T> {
    T val;
    public Vertex(T v){
      val = v;
    }
    @Override
    public boolean equals(Object obj){
      if(obj == this){
        return true;
      }else if(obj instanceof Vertex) {
        Vertex vertex = (Vertex) obj;
        return this.val == vertex.val;
      }else{
        return false;
      }

    }

    @Override
    public int hashCode(){
      return val.hashCode();
    }
  }

  public static class DirectedEdge<T>{
    Vertex start;
    Vertex end;
    public DirectedEdge(){

    }
    public DirectedEdge(Vertex<T> s, Vertex<T> e){
      start = s;
      end = e;
    }

    @Override
    public boolean equals(Object obj){
      if(obj == this){
        return true;
      }else if(obj instanceof DirectedEdge){
        DirectedEdge directedEdge = (DirectedEdge) obj;
        return this.start.equals(directedEdge.start) && this.end.equals(directedEdge.end);
      }else{
        return false;
      }
    }
  }

  public static class DirectedWeightEdge extends DirectedEdge{
    int weight;
    public DirectedWeightEdge(Vertex s, Vertex e, int w){
      start = s;
      end = e;
      weight = w;
    }
  }

  public static class UndirectedEdge<T>{
    Vertex node1;
    Vertex node2;

    public UndirectedEdge(Vertex<T> n1, Vertex<T> n2){
      node1 = n1;
      node2 = n2;
    }

    @Override
    public boolean equals(Object obj){
      if(obj == this){
        return true;
      }else if(obj instanceof UndirectedEdge){
        UndirectedEdge directedEdge = (UndirectedEdge) obj;
        return (this.node1.equals(directedEdge.node1) && this.node2.equals(directedEdge.node2) ||
            this.node1.equals(directedEdge.node2) && this.node2.equals(directedEdge.node1));
      }else{
        return false;
      }
    }
  }

  private List<UndirectedEdge> getUndirectedEdgeList(){

    List<UndirectedEdge> res = new LinkedList<>();
    for(T[] edge:_edges){
      UndirectedEdge<T> ue = new UndirectedEdge<>(new Vertex<>(edge[0]),new Vertex<>(edge[1]));
      res.add(ue);
    }
    return res;
  }

  private List<DirectedEdge> getDirectedEdgeList(){
    List<DirectedEdge> res = new LinkedList<>();
    for(T[] edge:_edges){
      Vertex<T> start = new Vertex<>(edge[0]);
      Vertex<T> end = new Vertex<>(edge[1]);
      DirectedEdge<T> de = new DirectedEdge<>(start,end);
      res.add(de);
    }
    return res;
  }

  private Map<Vertex,List<Vertex>> getAdjacencyList(){
    Map<Vertex,List<Vertex>> res = new HashMap<>();
    for(T[] edge:_edges){
      Vertex<T> start = new Vertex<>(edge[0]);
      Vertex<T> end = new Vertex<>(edge[1]);
      if(res.containsKey(start)){
        res.get(start).add(end);
      }else{
        List<Vertex> neighbors = new LinkedList<>();
        neighbors.add(end);
        res.put(start,neighbors);
      }
    }
    return res;
  }

  public Map<Vertex,List<Vertex>> adjacencyList;
  public List<DirectedEdge> directedEdges;
  public List<UndirectedEdge> undirectedEdges;
  private T[][] _edges;
  public Graph(T[][] edges){
    _edges = edges;
    adjacencyList = getAdjacencyList();
    directedEdges = getDirectedEdgeList();
    undirectedEdges = getUndirectedEdgeList();
  }
}
