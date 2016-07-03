import java.util.*;

public class DataStructure {
  /**
   * In a preferential instant runoff voting system, each voter ranks all candidates in order. To determine the winner, the following steps are followed:
   * (1) The first-rank votes for each candidate are tallied. If any candidate has more than 50% of the vote, that candidate wins
   * (2) If no candidate has more than(or equal to) 50% of the vote, the candidate with the lowest number of votes is removed.
   * That candidate is removed from each ballot, and the rankings are adjusted accordingly (so, voters who ranked the losing candidate first now rank their second candidate first)
   * (3) The process is repeated with the new rankings
   *
   * @param ballots list of ballots, each ballot has a ranked vote for candidates, i.e. abc means rank candidate a as first, b as second, c as the third.
   * @return the winner candidate
   */
  public char preferentialVoting(List<String> ballots) {
    int ballotSize = ballots.size();
    if (ballotSize == 0) {
      return ' ';
    }
    Map<String, Integer> ballotCount = new HashMap<>();
    Map<Character, Set<String>> candidateBallotDetail = new HashMap<>();
    Map<Character, Integer> candidateBallotCount = new HashMap<>();
    ArrayList<Character> loserList = new ArrayList<>();
    int magicNumberToWin = ballotSize % 2 == 0 ? ballotSize / 2 : ballotSize / 2 + 1;

    //calculate ballot once
    for (int i = 0; i < ballotSize; ++i) {
      String currentBallot = ballots.get(i);
      if (ballotCount.containsKey(currentBallot)) {
        ballotCount.put(currentBallot, ballotCount.get(currentBallot) + 1);
      } else {
        ballotCount.put(currentBallot, 1);
      }
      char firstRankedCandidate = currentBallot.charAt(0);
      Set<String> currentCandidateBallotDetail;
      if (candidateBallotDetail.containsKey(firstRankedCandidate)) {
        currentCandidateBallotDetail = candidateBallotDetail.get(firstRankedCandidate);
        currentCandidateBallotDetail.add(currentBallot);
      } else {
        currentCandidateBallotDetail = new HashSet<>();
        currentCandidateBallotDetail.add(currentBallot);
        candidateBallotDetail.put(firstRankedCandidate, currentCandidateBallotDetail);
      }
      if (candidateBallotCount.containsKey(firstRankedCandidate)) {
        candidateBallotCount.put(firstRankedCandidate, candidateBallotCount.get(firstRankedCandidate) + 1);
      } else {
        candidateBallotCount.put(firstRankedCandidate, 1);
      }
    }

    //first pass try to find winner without redistribute
    boolean findWinner = false;
    char winner = ballots.get(0).charAt(0);
    int minVoteFound = Integer.MAX_VALUE;
    char loser = ballots.get(0).charAt(0);
    while (!findWinner) {
      //check whether a candidate votes are more than threshold, at same time identify loser
      for (Character currCandidate : candidateBallotCount.keySet()) {
        int currCandidateVotes = candidateBallotCount.get(currCandidate);
        if (currCandidateVotes >= magicNumberToWin) {
          winner = currCandidate;
          findWinner = true;
          break;
        }
        if (currCandidateVotes < minVoteFound) {
          minVoteFound = currCandidateVotes;
          loser = currCandidate;
        }
      }
      loserList.add(loser);
      //redistribute votes if no winner found
      if (!findWinner) {
        for (char pastLoser : loserList) {
          Set<String> loserBallots = candidateBallotDetail.get(pastLoser);
          for (String ballot : loserBallots) {
            int ballotLength = ballot.length();
            for (int i = 0; i < ballotLength; ++i) {
              char candidateToConsider = ballot.charAt(i);
              if (!loserList.contains(candidateToConsider)) {
                candidateBallotCount.put(candidateToConsider, candidateBallotCount.get(candidateToConsider) + 1);
                break;
              }
            }
          }
        }
      }
    }
    return winner;
  }
}
