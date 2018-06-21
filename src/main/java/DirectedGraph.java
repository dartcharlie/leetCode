import java.util.*;

/**
 * Created by ZSong on 8/3/16.
 * Different presentations of Graph data structure
 */
public class DirectedGraph<T> {
  public static class Vertex<T> {
    T val;
    Color color;
    Set<Vertex> parent;
    public enum Color {
      White,Grey,Black
    }

    public Vertex(T v){
      val = v;
      color = Color.White;
      parent = new HashSet<>();
    }
    @Override
    public boolean equals(Object obj){
      if(obj == this){
        return true;
      }else if(obj instanceof Vertex) {
        Vertex vertex = (Vertex) obj;
        return this.val.equals(vertex.val);
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


  private List<DirectedEdge> getDirectedEdgeList(){
    List<DirectedEdge> res = new LinkedList<>();
    for(T[] edge:_edges){
      Vertex<T> start = getVertexFromValue(edge[0]);
      Vertex<T> end = getVertexFromValue(edge[1]);
      DirectedEdge<T> de = new DirectedEdge<>(start,end);
      res.add(de);
    }
    return res;
  }

  private Map<Vertex,List<Vertex>> getAdjacencyList(){
    Map<Vertex,List<Vertex>> res = new HashMap<>();
    for(T[] edge:_edges){
      Vertex<T> start = getVertexFromValue(edge[0]);
      Vertex<T> end = getVertexFromValue(edge[1]);
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

  public Vertex<T> getVertexFromValue(T val){
    Vertex v;
    if(_vertexMap.containsKey(val)){
      v = _vertexMap.get(val);
    }else {
      v = new Vertex(val);
      _vertexMap.put(val, v);
    }
    return v;
  }

  public Map<Vertex,List<Vertex>> adjacencyList;
  public List<DirectedEdge> directedEdges;
  private T[][] _edges;
  private Map<T,Vertex> _vertexMap;
  public LinkedList<Vertex> topologicalSortOrder;
  //to check whether a vertex already in visited path
  public boolean hasCycle;
  public DirectedGraph(T[][] edges){
    _vertexMap = new HashMap<>();
    _edges = edges;
    adjacencyList = getAdjacencyList();
    directedEdges = getDirectedEdgeList();
    hasCycle = false;

    //dfs
    topologicalSortOrder = new LinkedList<>();
    for(Vertex<T> v:adjacencyList.keySet()){
      if(v.color == Vertex.Color.White){
        checkCycle(v);
      }
    }

  }

  public void checkCycle(Vertex<T> sourceVertex){
    //first visited, still could be backtrack.
    sourceVertex.color = Vertex.Color.Grey;
    List<Vertex> neighbors = adjacencyList.get(sourceVertex);
    if(neighbors != null){
      for(Vertex neighbor:neighbors){
        if(neighbor.color == Vertex.Color.White) {
          neighbor.parent.add(sourceVertex);
          checkCycle(neighbor);
        }
        if(neighbor.color == Vertex.Color.Grey) {
          hasCycle = true;
          return;
        }
      }
    }
    sourceVertex.color = Vertex.Color.Black;
    topologicalSortOrder.addFirst(sourceVertex);

  }

  public <T extends Comparable<T>> T maxVertex(T[] vertices){
    T ret = vertices[0];
    for(int i=1;i<vertices.length;++i){
      if(ret.compareTo(vertices[i])< 0) {
        ret = vertices[i];
      }
    }
    return ret;
  }

}
