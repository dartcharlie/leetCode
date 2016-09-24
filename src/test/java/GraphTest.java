import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ZSong on 9/24/16.
 * Unit test for graph class
 */
public class GraphTest {
  @Test
  public void getDirectedEdgeListTest(){
    Integer[][] edges = new Integer[][]{{0,1}, {0,6}, {0,8}, {1,4}, {1,6},
        {1,9}, {2,4}, {2,6}, {3,4}, {3,5}, {3,8}, {4,5}, {4,9}, {7,8}, {7,9}};
    Graph<Integer> intGraph = new Graph<>(edges);
    List<Graph.DirectedEdge> expected_result = Arrays.asList(
        buildDirectedEdge(0,1),
        buildDirectedEdge(0,6),
        buildDirectedEdge(0,8),
        buildDirectedEdge(1,4),
        buildDirectedEdge(1,6),
        buildDirectedEdge(1,9),
        buildDirectedEdge(2,4),
        buildDirectedEdge(2,6),
        buildDirectedEdge(3,4),
        buildDirectedEdge(3,5),
        buildDirectedEdge(3,8),
        buildDirectedEdge(4,5),
        buildDirectedEdge(4,9),
        buildDirectedEdge(7,8),
        buildDirectedEdge(7,9));
    List<Graph.DirectedEdge> result = intGraph.directedEdges;
    Assert.assertEquals(result.size(),expected_result.size());
    Assert.assertTrue(result.containsAll(expected_result));
    Assert.assertTrue(expected_result.containsAll(result));
  }

  @Test
  public void getAdjacencyListTest(){
    Character[][] edges = new Character[][]{{'a','b'}, {'a','d'},{'a','f'}, {'b','e'}, {'c','e'}, {'d','b'},
        {'e','d'}, {'f','f'},{'f','g'}};
    Graph<Character> intGraph = new Graph<>(edges);
    Map<Graph.Vertex,List<Graph.Vertex>> expected_result = new HashMap<>();
    expected_result.put(new Graph.Vertex<>('a'),Arrays.asList(new Graph.Vertex('b'),new Graph.Vertex('d'),new Graph.Vertex('f')));
    expected_result.put(new Graph.Vertex<>('b'),Arrays.asList(new Graph.Vertex('e')));
    expected_result.put(new Graph.Vertex<>('c'),Arrays.asList(new Graph.Vertex('e')));
    expected_result.put(new Graph.Vertex<>('d'),Arrays.asList(new Graph.Vertex('b')));
    expected_result.put(new Graph.Vertex<>('e'),Arrays.asList(new Graph.Vertex('d')));
    expected_result.put(new Graph.Vertex<>('f'),Arrays.asList(new Graph.Vertex('f'),new Graph.Vertex('g')));
    Map<Graph.Vertex,List<Graph.Vertex>> result = intGraph.adjacencyList;
    Assert.assertEquals(result,expected_result);
  }

  private Graph.DirectedEdge buildDirectedEdge(int start, int end){
    return new Graph.DirectedEdge<>(new Graph.Vertex<>(start),new Graph.Vertex<>(end));
  }
}
