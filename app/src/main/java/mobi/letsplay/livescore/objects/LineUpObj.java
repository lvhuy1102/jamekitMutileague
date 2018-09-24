package mobi.letsplay.livescore.objects;

public class LineUpObj {

	private String mId, mMatchId, mClubId, mPlayerName, mPlayerNumber,
			mPosition;

	public LineUpObj() {
	}

	public LineUpObj(String id, String matchId, String clubId,
					 String playerName, String playerNumber, String position) {
		this.mId = id;
		this.mMatchId = matchId;
		this.mClubId = clubId;
		this.mPlayerName = playerName;
		this.mPlayerNumber = playerNumber;
		this.mPosition = position;
	}

	public String getmId() {
		return mId;
	}

	public void setmId(String mId) {
		this.mId = mId;
	}

	public String getmMatchId() {
		return mMatchId;
	}

	public void setmMatchId(String mMatchId) {
		this.mMatchId = mMatchId;
	}

	public String getmClubId() {
		return mClubId;
	}

	public void setmClubId(String mClubId) {
		this.mClubId = mClubId;
	}

	public String getmPlayerName() {
		return mPlayerName;
	}

	public void setmPlayerName(String mPlayerName) {
		this.mPlayerName = mPlayerName;
	}

	public String getmPlayerNumber() {
		return mPlayerNumber;
	}

	public void setmPlayerNumber(String mPlayerNumber) {
		this.mPlayerNumber = mPlayerNumber;
	}

	public String getmPosition() {
		return mPosition;
	}

	public void setmPosition(String mPosition) {
		this.mPosition = mPosition;
	}

}