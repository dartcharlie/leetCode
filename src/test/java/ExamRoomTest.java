import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class ExamRoomTest {
  ExamRoom _examRoom;

  @Test
  public void fourSeatSequenceTest() {
    _examRoom = new ExamRoom(4);
    Assert.assertEquals(_examRoom.seat(), 0);
    Assert.assertEquals(_examRoom.seat(), 3);
    Assert.assertEquals(_examRoom.seat(), 1);
    Assert.assertEquals(_examRoom.seat(), 2);
    _examRoom.leave(1);
    _examRoom.leave(3);
    Assert.assertEquals(_examRoom.seat(), 1);
  }

  @Test
  public void oneSeatSequenceTest() {
    _examRoom = new ExamRoom(1);
    Assert.assertEquals(_examRoom.seat(), 0);
    _examRoom.leave(0);
    Assert.assertEquals(_examRoom.seat(), 0);
    _examRoom.leave(0);
    Assert.assertEquals(_examRoom.seat(), 0);
  }

  @Test
  public void tenSeatSequenceTest() {
    _examRoom = new ExamRoom(10);
    Assert.assertEquals(_examRoom.seat(), 0);
    Assert.assertEquals(_examRoom.seat(), 9);
    Assert.assertEquals(_examRoom.seat(), 4);
    Assert.assertEquals(_examRoom.seat(), 2);
    _examRoom.leave(4);
    Assert.assertEquals(_examRoom.seat(), 5);
  }

  @Test
  public void tenSeatSequenceTest2() {
    _examRoom = new ExamRoom(10);
    Assert.assertEquals(_examRoom.seat(), 0);
    Assert.assertEquals(_examRoom.seat(), 9);
    Assert.assertEquals(_examRoom.seat(), 4);
    _examRoom.leave(0);
    _examRoom.leave(4);
    Assert.assertEquals(_examRoom.seat(), 0);
    Assert.assertEquals(_examRoom.seat(), 4);
    Assert.assertEquals(_examRoom.seat(), 2);
    Assert.assertEquals(_examRoom.seat(), 6);
    Assert.assertEquals(_examRoom.seat(), 1);
    Assert.assertEquals(_examRoom.seat(), 3);
    Assert.assertEquals(_examRoom.seat(), 5);
    Assert.assertEquals(_examRoom.seat(), 7);
    Assert.assertEquals(_examRoom.seat(), 8);
    _examRoom.leave(0);
  }
}
