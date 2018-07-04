import org.testng.Assert;
import org.testng.annotations.Test;

public class BitOperationTest {
  @Test
  public void andTest() {
    Assert.assertEquals(1&0, 0);
    Assert.assertEquals((1<<1)&1, 0);
    Assert.assertEquals(8&(1<<3), 8);
    Assert.assertEquals(8&5, 0);
  }

  @Test
  public void orTest() {
    Assert.assertEquals(1|0, 1);
    Assert.assertEquals((1<<1)|1, 3);
    Assert.assertEquals(8|(1<<2), 12);
    Assert.assertEquals((1<<4)|17, 17);
  }

  @Test
  public void xorTest() {
    Assert.assertEquals((1<<1)^1, 3);
    Assert.assertEquals(8^(1<<3), 0);
    Assert.assertEquals((9>>2)^5, 7);
  }

  @Test
  public void notTest() {
    Assert.assertEquals(~(1<<31), Integer.MAX_VALUE);
    Assert.assertEquals(~1, -2);
    Assert.assertEquals(~0, -1);
  }
}
