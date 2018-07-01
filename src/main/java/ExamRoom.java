import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

class ExamRoom {
  int totalSlot = 0;
  PriorityQueue<AvailableSegment> segmentQueue;
  Map<Integer, AvailableSegment> segmentFirstMap;
  Map<Integer, AvailableSegment> segmentLastMap;
  public ExamRoom(int N) {
    totalSlot = N;
    segmentQueue = new PriorityQueue<>(30000, new SegmentComparator());
    segmentFirstMap = new HashMap<>();
    segmentLastMap = new HashMap<>();
    addSegment(0, N-1);
  }

  public int seat() {
    AvailableSegment topSegment = retrieveTopSegment();
    if(topSegment.firstIndex == 0) {
      addSegment(1, topSegment.lastIndex);
      return 0;
    }
    if(topSegment.lastIndex == totalSlot -1) {
      addSegment(topSegment.firstIndex, totalSlot -2);
      return totalSlot -1;
    }
    int retIndex = topSegment.firstIndex + topSegment.priority;
    addSegment(topSegment.firstIndex, retIndex-1);
    addSegment(retIndex + 1, topSegment.lastIndex);
    return retIndex;
  }

  public void leave(int p) {
    AvailableSegment oldPreSegment, oldPostSegment;
    if(p == 0 && segmentFirstMap.containsKey(1)) {
      oldPostSegment = segmentFirstMap.get(1);
      removeSegment(oldPostSegment);
      addSegment(0, oldPostSegment.lastIndex);
    } else if(p == totalSlot-1 && segmentLastMap.containsKey(totalSlot-2)) {
      oldPreSegment = segmentLastMap.get(totalSlot-2);
      removeSegment(oldPreSegment);
      addSegment(oldPreSegment.firstIndex, totalSlot-1);
    } else if(segmentFirstMap.containsKey(p+1) && segmentLastMap.containsKey(p-1)) {
      oldPreSegment = segmentLastMap.get(p-1);
      oldPostSegment = segmentFirstMap.get(p+1);
      removeSegment(oldPreSegment);
      removeSegment(oldPostSegment);
      addSegment(oldPreSegment.firstIndex, oldPostSegment.lastIndex);
    } else {
      addSegment(p, p);
    }
  }

  private void addSegment(int firstIndex, int lastIndex) {
    if(firstIndex <= lastIndex) {
      AvailableSegment newSegment = new AvailableSegment(firstIndex, lastIndex);
      segmentQueue.add(newSegment);
      segmentFirstMap.put(firstIndex, newSegment);
      segmentLastMap.put(lastIndex, newSegment);
    }
  }

  private AvailableSegment retrieveTopSegment() {
    AvailableSegment ret = segmentQueue.poll();
    segmentFirstMap.remove(ret.firstIndex);
    segmentLastMap.remove(ret.lastIndex);
    return ret;
  }

  private void removeSegment(AvailableSegment segment) {
    segmentQueue.remove(segment);
    segmentFirstMap.remove(segment.firstIndex);
    segmentLastMap.remove(segment.lastIndex);
  }

  private class AvailableSegment {
    Integer priority;
    Integer firstIndex;
    int lastIndex;
    public AvailableSegment(int first, int last) {
      firstIndex = first;
      lastIndex = last;
      if (first == 0 || last == totalSlot-1) {
        priority = last - first;
      } else {
        priority = (last - first)/2;
      }
    }
  }

  private class SegmentComparator implements Comparator<AvailableSegment> {
    public int compare(AvailableSegment as1, AvailableSegment as2) {
      if(as1.priority.equals(as2.priority)) {
        return as1.firstIndex.compareTo(as2.firstIndex);
      }
      return -as1.priority.compareTo(as2.priority);
    }
  }
}
