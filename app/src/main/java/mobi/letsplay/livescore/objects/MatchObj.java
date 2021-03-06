package mobi.letsplay.livescore.objects;

public class MatchObj {

    private String mMatchId, mHome, mAway, mStadium, mTime, mHomeScore,
            mMatchStatus, mAwayScore, mRound, mMinute, mLiveStreamLink,
            mHomeName, mAwayName, mHomeImage, mAwayImage;
    private int leagueId;
    private String idTeamA, idTeamB;
    private String homePen, awayPen;

    public MatchObj(String matchid, String home, String mAway, String mStadium, String mTime, String mHomeScore
            , String mMatchStatus, String mAwayScore, String mRound, String mMinute, String mLiveStreamLink
            , String mHomeName, String mAwayName, String mHomeImage, String mAwayImage, int leagueId) {
        this.leagueId = leagueId;
        this.mMatchId = matchid;
        this.mHome = home;
        this.mAway = mAway;
        this.mStadium = mStadium;
        this.mTime = mTime;
        this.mMatchStatus = mMatchStatus;
        this.mHomeScore = mHomeScore;
        this.mAwayScore = mAwayScore;
        this.mRound = mRound;
        this.mHomeName = mHomeName;
        this.mHomeImage = mHomeImage;
        this.mAwayName = mAwayName;
        this.mAwayImage = mAwayImage;
        this.mMinute = mMinute;
        this.mLiveStreamLink = mLiveStreamLink;
    }

    public MatchObj(String matchid, String home, String away, String stadium,
                    String time, String matchstatus, String homeScore, String awayScore, String round,
                    String homeImage, String awayImage, String homeName, String awayName, String mMinute, String homePen, String awayPen) {
        this.mMatchId = matchid;
        this.mHome = home;
        this.mAway = away;
        this.mStadium = stadium;
        this.mTime = time;
        this.mMatchStatus = matchstatus;
        this.mHomeScore = homeScore;
        this.mAwayScore = awayScore;
        this.mRound = round;
        this.mHomeImage = homeImage;
        this.mAwayImage = awayImage;
        this.mHomeName = homeName;
        this.mAwayName = awayName;
        this.mMinute = mMinute;
        this.homePen = homePen;
        this.awayPen = awayPen;
    }

    public MatchObj(String minute, String home, String away, String stadium, String time, String matchstatus,
                    String homeScore, String awayScore) {
        this.mMinute = minute;
        this.mHome = home;
        this.mAway = away;
        this.mStadium = stadium;
        this.mTime = time;
        this.mMatchStatus = matchstatus;
        this.mHomeScore = homeScore;
        this.mAwayScore = awayScore;
    }

    public MatchObj(int leagueId, String matchid, String home, String homeName,
                    String homeImage, String away, String awayName, String awayImage,
                    String stadium, String time, String matchstatus, String homeScore,
                    String awayScore, String round, String mMinute, String homePen, String awayPen) {
        this.leagueId = leagueId;
        this.mMatchId = matchid;
        this.mHome = home;
        this.mAway = away;
        this.mStadium = stadium;
        this.mTime = time;
        this.mMatchStatus = matchstatus;
        this.mHomeScore = homeScore;
        this.mAwayScore = awayScore;
        this.mRound = round;
        this.mHomeName = homeName;
        this.mHomeImage = homeImage;
        this.mAwayName = awayName;
        this.mAwayImage = awayImage;
        this.mMinute = mMinute;
        this.homePen = homePen;
        this.awayPen = awayPen;
    }


    public String getHomePen() {
        return homePen;
    }

    public void setHomePen(String homePen) {
        this.homePen = homePen;
    }

    public String getAwayPen() {
        return awayPen;
    }

    public void setAwayPen(String awayPen) {
        this.awayPen = awayPen;
    }

    public String getIdTeamA() {
        return idTeamA;
    }

    public void setIdTeamA(String idTeamA) {
        this.idTeamA = idTeamA;
    }

    public String getIdTeamB() {
        return idTeamB;
    }

    public void setIdTeamB(String idTeamB) {
        this.idTeamB = idTeamB;
    }

    public String getmLiveStreamLink() {
        return mLiveStreamLink;
    }

    public void setmLiveStreamLink(String mLiveStreamLink) {
        this.mLiveStreamLink = mLiveStreamLink;
    }

    public String getmMatchId() {
        return mMatchId;
    }

    public void setmMatchId(String mMatchId) {
        this.mMatchId = mMatchId;
    }

    public String getmHome() {
        return mHome;
    }

    public void setmHome(String mHome) {
        this.mHome = mHome;
    }

    public String getmAway() {
        return mAway;
    }

    public void setmAway(String mAway) {
        this.mAway = mAway;
    }

    public String getmStadium() {
        return mStadium;
    }

    public void setmStadium(String mStadium) {
        this.mStadium = mStadium;
    }

    public String getmTime() {
        return mTime;
    }

    public void setmTime(String mTime) {
        this.mTime = mTime;
    }

    public String getmHomeScore() {
        return mHomeScore;
    }

    public void setmHomeScore(String mHomeScore) {
        this.mHomeScore = mHomeScore;
    }

    public String getmMatchStatus() {
        return mMatchStatus;
    }

    public void setmMatchStatus(String mMatchStatus) {
        this.mMatchStatus = mMatchStatus;
    }

    public String getmAwayScore() {
        return mAwayScore;
    }

    public void setmAwayScore(String mAwayScore) {
        this.mAwayScore = mAwayScore;
    }

    public String getmRound() {
        return mRound;
    }

    public void setmRound(String mRound) {
        this.mRound = mRound;
    }

    public String getmMinute() {
        return mMinute;
    }

    public void setmMinute(String mMinute) {
        this.mMinute = mMinute;
    }

    public String getmHomeName() {
        return mHomeName;
    }

    public void setmHomeName(String mHomeName) {
        this.mHomeName = mHomeName;
    }

    public String getmAwayName() {
        return mAwayName;
    }

    public void setmAwayName(String mAwayName) {
        this.mAwayName = mAwayName;
    }

    public String getmHomeImage() {
        return mHomeImage;
    }

    public void setmHomeImage(String mHomeImage) {
        this.mHomeImage = mHomeImage;
    }

    public String getmAwayImage() {
        return mAwayImage;
    }

    public void setmAwayImage(String mAwayImage) {
        this.mAwayImage = mAwayImage;
    }

    public int getLeagueId() {
        return leagueId;
    }

    public void setLeagueId(int leagueId) {
        this.leagueId = leagueId;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
