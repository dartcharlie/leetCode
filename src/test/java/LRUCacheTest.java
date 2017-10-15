import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.Assert;

public class LRUCacheTest {
  LRUCache _LRUCache;

  @BeforeTest
  public void Setup(){
    _LRUCache = new LRUCache(3);
  }

  @Test
  public void setTest(){
    _LRUCache.set(1,5);
    _LRUCache.set(2,6);
    Assert.assertEquals(_LRUCache.get(1),5);
    _LRUCache.set(3,7);
    _LRUCache.set(4,8);
    Assert.assertEquals(_LRUCache.get(2),-1);
    Assert.assertEquals(_LRUCache.get(1),5);
    _LRUCache.set(5,9);
    Assert.assertEquals(_LRUCache.get(3),-1);
  }
}
