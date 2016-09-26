import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.*;

/**
 * Created by ZSong on 9/24/16.
 * Unit test for DirectedGraph class
 */
public class DirectedGraphTest {
  @Test
  public void getDirectedEdgeListTest(){
    Integer[][] edges = new Integer[][]{{0,1}, {0,6}, {0,8}, {1,4}, {1,6},
        {1,9}, {2,4}, {2,6}, {3,4}, {3,5}, {3,8}, {4,5}, {4,9}, {7,8}, {7,9}};
    DirectedGraph<Integer> intDirectedGraph = new DirectedGraph<>(edges);
    List<DirectedGraph.DirectedEdge> expected_result = Arrays.asList(
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
    List<DirectedGraph.DirectedEdge> result = intDirectedGraph.directedEdges;
    Assert.assertEquals(result.size(),expected_result.size());
    Assert.assertTrue(result.containsAll(expected_result));
    Assert.assertTrue(expected_result.containsAll(result));
  }

  @Test
  public void getAdjacencyListTest(){
    Character[][] edges = new Character[][]{{'a','b'}, {'a','d'},{'a','f'}, {'b','e'}, {'c','e'}, {'d','b'},
        {'e','d'}, {'f','f'},{'f','g'}};
    DirectedGraph<Character> charDirectedGraph = new DirectedGraph<>(edges);
    Map<DirectedGraph.Vertex,List<DirectedGraph.Vertex>> expected_result = new HashMap<>();
    expected_result.put(new DirectedGraph.Vertex<>('a'),Arrays.asList(new DirectedGraph.Vertex('b'),new DirectedGraph.Vertex('d'),new DirectedGraph.Vertex('f')));
    expected_result.put(new DirectedGraph.Vertex<>('b'),Arrays.asList(new DirectedGraph.Vertex('e')));
    expected_result.put(new DirectedGraph.Vertex<>('c'),Arrays.asList(new DirectedGraph.Vertex('e')));
    expected_result.put(new DirectedGraph.Vertex<>('d'),Arrays.asList(new DirectedGraph.Vertex('b')));
    expected_result.put(new DirectedGraph.Vertex<>('e'),Arrays.asList(new DirectedGraph.Vertex('d')));
    expected_result.put(new DirectedGraph.Vertex<>('f'),Arrays.asList(new DirectedGraph.Vertex('f'),new DirectedGraph.Vertex('g')));
    Map<DirectedGraph.Vertex,List<DirectedGraph.Vertex>> result = charDirectedGraph.adjacencyList;
    Assert.assertEquals(result,expected_result);
  }

  @Test
  public void checkCycleTest(){
    Integer[][] edges1 = new Integer[][]{{0,1}, {0,6}, {0,8}, {1,4}, {1,6},
        {1,9}, {2,4}, {2,6}, {3,4}, {3,5}, {3,8}, {4,5}, {4,9}, {7,8}, {7,9}};
    DirectedGraph<Integer> intDirectedGraph = new DirectedGraph<>(edges1);
    Assert.assertFalse(intDirectedGraph.hasCycle);

    Character[][] edges2 = new Character[][]{{'a','b'}, {'a','d'},{'a','f'}, {'b','e'}, {'c','e'},
        {'e','d'}, {'f','f'},{'f','g'}};
    DirectedGraph<Character> charDirectedGraph = new DirectedGraph<>(edges2);
    Assert.assertTrue(charDirectedGraph.hasCycle);
  }

  @Test
  public void topologicalSortTest(){
    Character[][] edges1 = new Character[][]{{'c','d'}, {'b','a'},{'c','f'}, {'a','c'}};
    DirectedGraph<Character> charDirectedGraph = new DirectedGraph<>(edges1);
    LinkedList<DirectedGraph.Vertex<Character>> topoSortOrder11 = new LinkedList<>();
    topoSortOrder11.addAll(Arrays.asList(new DirectedGraph.Vertex<>('b'),
        new DirectedGraph.Vertex<>('a'),
        new DirectedGraph.Vertex<>('c'),
        new DirectedGraph.Vertex<>('d'),
        new DirectedGraph.Vertex<>('f')));
    LinkedList<DirectedGraph.Vertex<Character>> topoSortOrder12 = new LinkedList<>();
    topoSortOrder12.addAll(Arrays.asList(new DirectedGraph.Vertex<>('b'),
        new DirectedGraph.Vertex<>('a'),
        new DirectedGraph.Vertex<>('c'),
        new DirectedGraph.Vertex<>('f'),
        new DirectedGraph.Vertex<>('d')));
    Assert.assertTrue(charDirectedGraph.topologicalSortOrder.equals(topoSortOrder11) || charDirectedGraph.topologicalSortOrder.equals(topoSortOrder12));
  }

  private DirectedGraph.DirectedEdge buildDirectedEdge(int start, int end){
    return new DirectedGraph.DirectedEdge<>(new DirectedGraph.Vertex<>(start),new DirectedGraph.Vertex<>(end));
  }
}
