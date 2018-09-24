package com.hcpt.multileagues.json.utils;

import android.app.Activity;
import android.content.Context;

import com.hcpt.multileagues.R;
import com.hcpt.multileagues.configs.Constants;
import com.hcpt.multileagues.configs.JsonConfigs;
import com.hcpt.multileagues.objects.EventObject;
import com.hcpt.multileagues.objects.LeagueObj;
import com.hcpt.multileagues.objects.LineUpObj;
import com.hcpt.multileagues.objects.MatchObj;
import com.hcpt.multileagues.objects.OddObj;
import com.hcpt.multileagues.objects.RankClubsObj;
import com.hcpt.multileagues.objects.RankGroupObject;
import com.hcpt.multileagues.objects.RoundObj;
import com.hcpt.multileagues.objects.SettingObj;
import com.hcpt.multileagues.objects.Team;
import com.hcpt.multileagues.objects.Timeline;
import com.hcpt.multileagues.objects.TopScorer;
import com.hcpt.multileagues.objects.TopScorerObj;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ParserUtils {

    public static ArrayList<RankClubsObj> parserRankClubs(String json) {
        ArrayList<RankClubsObj> mArrRankClubs = new ArrayList<>();
        try {
            JSONObject jsonObj = new JSONObject(json);
            if (jsonObj.getString(JsonConfigs.KEY_JSON_STATUS)
                    .equalsIgnoreCase(JsonConfigs.JSON_STATUS_SUCCESS)) {
                RankClubsObj rankObj = null;
                JSONArray arrObj = jsonObj
                        .getJSONArray(JsonConfigs.KEY_JSON_DATA);
                JSONObject obj;
                for (int i = 0; i < arrObj.length(); i++) {
                    obj = arrObj.getJSONObject(i);
                    rankObj = new RankClubsObj(
                            obj.getString(JsonConfigs.KEY_CLUB_ID),
                            obj.getString(JsonConfigs.KEY_CLUB_IMAGE),
                            obj.getString(JsonConfigs.KEY_CLUB_NAME),
                            obj.getString(JsonConfigs.KEY_CLUB_PLAYED),
                            obj.getString(JsonConfigs.KEY_CLUB_WIN),
                            obj.getString(JsonConfigs.KEY_CLUB_DRAW),
                            obj.getString(JsonConfigs.KEY_CLUB_LOSE),
                            obj.getString(JsonConfigs.KEY_CLUB_GD),
                            obj.getString(JsonConfigs.KEY_CLUB_PTS),
                            obj.getString(JsonConfigs.KEY_CLUB_MANAGER),
                            obj.getString(JsonConfigs.KEY_CLUB_NICKNAME),
                            obj.getString(JsonConfigs.KEY_CLUB_ESTABLISHED),
                            obj.getString(JsonConfigs.KEY_CLUB_STADIUM),
                            obj.getString(JsonConfigs.KEY_CLUB_URL));
                    rankObj.setGroup("");

                    mArrRankClubs.add(rankObj);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return mArrRankClubs;
    }

    public static boolean isSuccess(String json) {
        try {
            JSONObject object = new JSONObject(json);
            if (object.getString(JsonConfigs.KEY_JSON_STATUS)
                    .equalsIgnoreCase(JsonConfigs.JSON_STATUS_SUCCESS)) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {

        }
        return false;
    }

    public static boolean isDemo(String json) {
        try {
            JSONObject object = new JSONObject(json);
            if (object.getString(JsonConfigs.KEY_JSON_DEMO)
                    .equalsIgnoreCase("1")) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {

        }
        return false;
    }

    public static ArrayList<RankClubsObj> parserRankGroup(String json) {
        ArrayList<RankClubsObj> mArrRankClubs = new ArrayList<>();
        try {
            JSONObject jsonObj = new JSONObject(json);
            if (jsonObj.getString(JsonConfigs.KEY_JSON_STATUS)
                    .equalsIgnoreCase(JsonConfigs.JSON_STATUS_SUCCESS)) {
                RankClubsObj rankClubsObj = null;
                JSONArray arrObj = jsonObj.getJSONArray(JsonConfigs.KEY_JSON_DATA);
                JSONObject obj;
                for (int i = 0; i < arrObj.length(); i++) {
                    obj = arrObj.getJSONObject(i);
                    JSONArray arrTeams = obj.getJSONArray("teams");
                    JSONObject teamObj;
                    for (int j = 0; j < arrTeams.length(); j++) {
                        teamObj = arrTeams.getJSONObject(j);
                        rankClubsObj = new RankClubsObj(
                                teamObj.getString(JsonConfigs.KEY_CLUB_ID),
                                teamObj.getString(JsonConfigs.KEY_CLUB_IMAGE),
                                teamObj.getString(JsonConfigs.KEY_CLUB_NAME),
                                teamObj.getString(JsonConfigs.KEY_CLUB_PLAYED),
                                teamObj.getString(JsonConfigs.KEY_CLUB_WIN),
                                teamObj.getString(JsonConfigs.KEY_CLUB_DRAW),
                                teamObj.getString(JsonConfigs.KEY_CLUB_LOSE),
                                teamObj.getString(JsonConfigs.KEY_CLUB_GD),
                                teamObj.getString(JsonConfigs.KEY_CLUB_PTS),
                                teamObj.getString(JsonConfigs.KEY_CLUB_MANAGER),
                                teamObj.getString(JsonConfigs.KEY_CLUB_NICKNAME),
                                teamObj.getString(JsonConfigs.KEY_CLUB_ESTABLISHED),
                                teamObj.getString(JsonConfigs.KEY_CLUB_STADIUM),
                                teamObj.getString(JsonConfigs.KEY_CLUB_URL),
                                teamObj.getString(JsonConfigs.KEY_CLUB_GROUP),
                                teamObj.getString(JsonConfigs.KEY_CLUB_POSITION));

                        mArrRankClubs.add(rankClubsObj);
                    }
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return mArrRankClubs;
    }


    public static ArrayList<MatchObj> parseSchedule(String json) {
        ArrayList<MatchObj> mArrSchedule = new ArrayList<>();
        try {
            JSONObject jsonObj = new JSONObject(json);
            if (jsonObj.getString(JsonConfigs.KEY_JSON_STATUS)
                    .equalsIgnoreCase(JsonConfigs.JSON_STATUS_SUCCESS)) {
                MatchObj matchObj = null;
                JSONArray arrObj = jsonObj
                        .getJSONArray(JsonConfigs.KEY_JSON_DATA);
                JSONObject obj;
                for (int i = 0; i < arrObj.length(); i++) {
                    obj = arrObj.getJSONObject(i);
                    matchObj = new MatchObj(
                            obj.getString(JsonConfigs.KEY_MATCH_ID),
                            obj.getString(JsonConfigs.KEY_MATCH_HOME),
                            obj.getString(JsonConfigs.KEY_MATCH_AWAY),
                            obj.getString(JsonConfigs.KEY_MATCH_STADIUM),
                            obj.getString(JsonConfigs.KEY_MATCH_TIME),
                            obj.getString(JsonConfigs.KEY_MATCH_STATUS),
                            obj.getString(JsonConfigs.KEY_MATCH_HOME_SCORE),
                            obj.getString(JsonConfigs.KEY_MATCH_AWAY_SCORE),
                            obj.getString(JsonConfigs.KEY_MATCH_ROUND),
                            obj.getString(JsonConfigs.KEY_MATCH_HOME_IMAGE),
                            obj.getString(JsonConfigs.KEY_MATCH_AWAY_IMAGE),
                            obj.getString(JsonConfigs.KEY_MATCH_HOME_NAME),
                            obj.getString(JsonConfigs.KEY_MATCH_AWAY_NAME),
                            obj.getString(JsonConfigs.KEY_EVENT_MINUTE),
                            obj.getString(JsonConfigs.KEY_MATCH_DETAIL_HOME_PEN),
                            obj.getString(JsonConfigs.KEY_MATCH_DETAIL_AWAY_PEN));

                    mArrSchedule.add(matchObj);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return mArrSchedule;
    }

    public static ArrayList<MatchObj> parserDashboard(String json) {
        ArrayList<MatchObj> mArrSchedule = new ArrayList<>();
        final String MATCH = "match";
        try {
            JSONObject jsonObj = new JSONObject(json);
            if (jsonObj.getString(JsonConfigs.KEY_JSON_STATUS)
                    .equalsIgnoreCase(JsonConfigs.JSON_STATUS_SUCCESS)) {
                MatchObj matchObj = null;

                JSONArray arrData = jsonObj.getJSONArray(JsonConfigs.KEY_JSON_DATA);

                for (int i = 0; i < arrData.length(); i++) {
                    JSONObject arrObj = arrData.getJSONObject(i);
                    String leaguedID = arrObj.getString(JsonConfigs.KEY_LEAGUEID);
                    JSONArray arrMatch = arrObj.getJSONArray(MATCH);
                    JSONObject obj;
                    for (int j = 0; j < arrMatch.length(); j++) {
                        obj = arrMatch.getJSONObject(j);
                        matchObj = new MatchObj(
                                Integer.parseInt(leaguedID),
                                obj.getString(JsonConfigs.KEY_MATCH_ID),
                                obj.getString(JsonConfigs.KEY_MATCH_HOME),
                                obj.getString(JsonConfigs.KEY_MATCH_HOME_NAME),
                                obj.getString(JsonConfigs.KEY_MATCH_HOME_IMAGE),
                                obj.getString(JsonConfigs.KEY_MATCH_AWAY),
                                obj.getString(JsonConfigs.KEY_MATCH_AWAY_NAME),
                                obj.getString(JsonConfigs.KEY_MATCH_AWAY_IMAGE),
                                obj.getString(JsonConfigs.KEY_MATCH_STADIUM),
                                obj.getString(JsonConfigs.KEY_MATCH_TIME),
                                obj.getString(JsonConfigs.KEY_MATCH_STATUS),
                                obj.getString(JsonConfigs.KEY_MATCH_HOME_SCORE),
                                obj.getString(JsonConfigs.KEY_MATCH_AWAY_SCORE),
                                obj.getString(JsonConfigs.KEY_MATCH_ROUND),
                                obj.getString(JsonConfigs.KEY_EVENT_MINUTE),
                                obj.getString(JsonConfigs.KEY_MATCH_DETAIL_HOME_PEN),
                                obj.getString(JsonConfigs.KEY_MATCH_DETAIL_AWAY_PEN))
                        ;
                        mArrSchedule.add(matchObj);
                    }
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return mArrSchedule;
    }

    public static ArrayList<TopScorerObj> parserTopScorer(String json) {
        ArrayList<TopScorerObj> mArrTopScorer = new ArrayList<>();
        try {
            JSONObject jsonObj = new JSONObject(json);
            if (jsonObj.getString(JsonConfigs.KEY_JSON_STATUS)
                    .equalsIgnoreCase(JsonConfigs.JSON_STATUS_SUCCESS)) {
                TopScorerObj topObj = null;
                JSONArray arrObj = jsonObj
                        .getJSONArray(JsonConfigs.KEY_JSON_DATA);
                JSONObject obj;
                for (int i = 0; i < arrObj.length(); i++) {
                    obj = arrObj.getJSONObject(i);
                    topObj = new TopScorerObj(
                            obj.getString(JsonConfigs.KEY_TOP_SCORER_ID),
                            obj.getString(JsonConfigs.KEY_TOP_SCORER_PLAYER),
                            obj.getString(JsonConfigs.KEY_TOP_SCORER_GOAL),
                            obj.getString(JsonConfigs.KEY_TOP_SCORER_CLUB));

                    mArrTopScorer.add(topObj);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return mArrTopScorer;
    }

    /**
     * Parse TopScorers
     */
    public static ArrayList<TopScorer> parserTopScorers(JSONObject json) {
        ArrayList<TopScorer> arrTopScorer = new ArrayList<TopScorer>();
        if (json != null) {
            try {
                if (json.getString(JsonConfigs.KEY_JSON_STATUS).equals(JsonConfigs.JSON_STATUS_SUCCESS)) {
                    JSONArray jsonArray = json.getJSONArray(JsonConfigs.KEY_JSON_DATA);
                    if (jsonArray != null && jsonArray.length() > 0) {
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                            TopScorer topScorer = new TopScorer();
                            topScorer.setOrdinarily(jsonObject.getInt("id"));
                            topScorer.setPlayerName(jsonObject.getString("player"));
                            topScorer.setScore(jsonObject.getString("goal"));
                            String teamName = jsonObject.getString("clubName");

                            Team team = new Team();
                            team.setLinkFlag(jsonObject.getString("clubImage"));
                            team.setTeamCode(jsonObject.getString("fifaCode"));
                            team.setName(teamName);
                            topScorer.setTeam(team);
                            arrTopScorer.add(topScorer);

                        }
                    }
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        return arrTopScorer;
    }

    public static ArrayList<EventObject> parserEvents(String json) {
        ArrayList<EventObject> mArrEvent = new ArrayList<>();
        try {
            JSONObject jsonObj = new JSONObject(json);
            if (jsonObj.getString(JsonConfigs.KEY_JSON_STATUS)
                    .equalsIgnoreCase(JsonConfigs.JSON_STATUS_SUCCESS)) {
                EventObject eventObj = null;
                JSONArray arrObj = jsonObj
                        .getJSONArray(JsonConfigs.KEY_JSON_DATA);
                JSONObject obj;
                for (int i = 0; i < arrObj.length(); i++) {
                    obj = arrObj.getJSONObject(i);
                    eventObj = new EventObject(
                            obj.getString(JsonConfigs.KEY_EVENT_ID),
                            obj.getString(JsonConfigs.KEY_EVENT_MATCH_ID),
                            obj.getString(JsonConfigs.KEY_EVENT_PLAYER),
                            obj.getString(JsonConfigs.KEY_EVENT_CLUB_ID),
                            obj.getString(JsonConfigs.KEY_EVENT_MINUTE),
                            obj.getString(JsonConfigs.KEY_EVENT_IS_SCORE),
                            obj.getString(JsonConfigs.KEY_EVENT_TYPE),
                            obj.getString(JsonConfigs.KEY_EVENT_SCORES));

                    mArrEvent.add(eventObj);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return mArrEvent;
    }

    public static ArrayList<Timeline> parserTimelines(JSONObject json) {
        ArrayList<Timeline> arrTimeline = new ArrayList<Timeline>();
        if (json != null) {
            try {
                if (json.getString(JsonConfigs.KEY_JSON_STATUS).equalsIgnoreCase(JsonConfigs.JSON_STATUS_SUCCESS)) {
                    JSONArray jsonArray = json.getJSONArray(JsonConfigs.KEY_JSON_DATA);
                    if (jsonArray != null && jsonArray.length() > 0) {
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                            Timeline timeline = new Timeline();
                            timeline.setIdTeam(jsonObject.getString("clubId"));
                            timeline.setEvent(Integer.parseInt(jsonObject.getString("type")));
                            timeline.setPlayer(jsonObject.getString("player"));
                            timeline.setTime(jsonObject.getString("minute"));
                            arrTimeline.add(timeline);
                        }
                    }
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return arrTimeline;
    }

    public static ArrayList<LineUpObj> parserLineUps(String json) {
        ArrayList<LineUpObj> mArrLineUp = new ArrayList<>();
        try {
            JSONObject jsonObj = new JSONObject(json);
            if (jsonObj.getString(JsonConfigs.KEY_JSON_STATUS)
                    .equalsIgnoreCase(JsonConfigs.JSON_STATUS_SUCCESS)) {
                LineUpObj luObj = null;
                JSONArray arrObj = jsonObj
                        .getJSONArray(JsonConfigs.KEY_JSON_DATA);
                JSONObject obj;
                for (int i = 0; i < arrObj.length(); i++) {
                    obj = arrObj.getJSONObject(i);
                    luObj = new LineUpObj();
                    luObj.setmId(obj.getString(JsonConfigs.KEY_LINEUPS_ID));
                    luObj.setmMatchId(obj.getString(JsonConfigs.KEY_LINEUPS_MATCH_ID));
                    luObj.setmClubId(obj.getString(JsonConfigs.KEY_LINEUPS_CLUB_ID));
                    luObj.setmPlayerName(obj.getString(JsonConfigs.KEY_LINEUPS_PLAYER_NAME));
                    luObj.setmPlayerNumber(obj.getString(JsonConfigs.KEY_LINEUPS_PLAYER_NAME));
                    luObj.setmPosition(obj.getString(JsonConfigs.KEY_LINEUPS_POSITION));
//                    luObj = new LineUpObj(
//                            obj.getString(JsonConfigs.KEY_LINEUPS_ID),
//                            obj.getString(JsonConfigs.KEY_LINEUPS_MATCH_ID),
//                            obj.getString(JsonConfigs.KEY_LINEUPS_CLUB_ID),
//                            obj.getString(JsonConfigs.KEY_LINEUPS_PLAYER_NAME),
//                            "",
//                            obj.getString(JsonConfigs.KEY_LINEUPS_POSITION));

                    mArrLineUp.add(luObj);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mArrLineUp;
    }

    public static MatchObj parserMatchDetail(String json) {
        MatchObj matchObj = null;
        try {
            JSONObject jsonObj = new JSONObject(json);
            if (jsonObj.getString(JsonConfigs.KEY_JSON_STATUS)
                    .equalsIgnoreCase(JsonConfigs.JSON_STATUS_SUCCESS)) {
                JSONObject obj = jsonObj
                        .getJSONObject(JsonConfigs.KEY_JSON_DATA);

                matchObj = new MatchObj(
                        Integer.parseInt(obj.getString(JsonConfigs.KEY_LEAGUEID)),
                        obj.getString(JsonConfigs.KEY_MATCH_ID),
                        obj.getString(JsonConfigs.KEY_MATCH_HOME),
                        obj.getString(JsonConfigs.KEY_MATCH_HOME_NAME),
                        obj.getString(JsonConfigs.KEY_MATCH_HOME_IMAGE),
                        obj.getString(JsonConfigs.KEY_MATCH_AWAY),
                        obj.getString(JsonConfigs.KEY_MATCH_AWAY_NAME),
                        obj.getString(JsonConfigs.KEY_MATCH_AWAY_IMAGE),
                        obj.getString(JsonConfigs.KEY_MATCH_STADIUM),
                        obj.getString(JsonConfigs.KEY_MATCH_TIME),
                        obj.getString(JsonConfigs.KEY_MATCH_STATUS),
                        obj.getString(JsonConfigs.KEY_MATCH_HOME_SCORE),
                        obj.getString(JsonConfigs.KEY_MATCH_AWAY_SCORE),
                        obj.getString(JsonConfigs.KEY_MATCH_ROUND),
                        obj.getString(JsonConfigs.KEY_EVENT_MINUTE),
                        obj.getString(JsonConfigs.KEY_MATCH_DETAIL_HOME_PEN),
                        obj.getString(JsonConfigs.KEY_MATCH_DETAIL_AWAY_PEN));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return matchObj;
    }

    public static ArrayList<LeagueObj> parserLeagues(String json) {
        ArrayList<LeagueObj> arrLeague = new ArrayList<>();
        try {
            JSONObject jsonObj = new JSONObject(json);
            if (jsonObj.getString(JsonConfigs.KEY_JSON_STATUS)
                    .equalsIgnoreCase(JsonConfigs.JSON_STATUS_SUCCESS)) {
                JSONArray arr = jsonObj.getJSONArray(JsonConfigs.KEY_JSON_DATA);
                for (int i = 0; i < arr.length(); i++) {
                    JSONObject obj = arr.getJSONObject(i);
                    arrLeague.add(new LeagueObj(
                            obj.getString(JsonConfigs.KEY_LEAGUE_ID),
                            obj.getString(JsonConfigs.KEY_LEAGUE_NAME),
                            obj.getString(JsonConfigs.KEY_LEAGUE_NAME),
                            obj.getString(JsonConfigs.KEY_LEAGUE_NAME),
                            obj.getString(JsonConfigs.KEY_LEAGUE_NAME),
                            obj.getString(JsonConfigs.KEY_LEAGUE_NAME),
                            obj.getString(JsonConfigs.KEY_LEAGUE_NAME),
                            obj.getString(JsonConfigs.KEY_LEAGUE_LOGO),
                            obj.getString(JsonConfigs.KEY_LEAGUE_TYPE),
                            jsonObj.getString(JsonConfigs.KEY_JSON_DEMO)));
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return arrLeague;
    }

    public static String parserFeeds(String json) {
        String feedsUrl = "";
        try {
            JSONObject jsonObj = new JSONObject(json);
            if (jsonObj.getString(JsonConfigs.KEY_JSON_STATUS)
                    .equalsIgnoreCase(JsonConfigs.JSON_STATUS_SUCCESS)) {
                JSONArray arr = jsonObj.getJSONArray(JsonConfigs.KEY_JSON_DATA);
                if (arr.length() != 0) {
                    JSONObject obj = arr.getJSONObject(0);
                    feedsUrl = obj.getString("rss");
                } else {
                    feedsUrl = Constants.DEFAULT_URL_RSS;
                }

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return feedsUrl;
    }

    public static ArrayList<RoundObj> parseRounds(Activity act, String json) {
        ArrayList<RoundObj> arrRound = new ArrayList<>();
        try {
            JSONObject jsonObj = new JSONObject(json);
            if (jsonObj.getString(JsonConfigs.KEY_JSON_STATUS).equalsIgnoreCase(JsonConfigs.JSON_STATUS_SUCCESS)) {
                JSONArray arrObj = jsonObj.getJSONArray(JsonConfigs.KEY_JSON_DATA);

                arrRound.add(new RoundObj("", "All", false));
                for (int i = 0; i < arrObj.length(); i++) {
                    JSONObject obj = arrObj.getJSONObject(i);
                    arrRound.add(new RoundObj(obj.getString(JsonConfigs.KEY_ROUND_ID), obj.getString(JsonConfigs.KEY_ROUND_NAME),
                            obj.getString(JsonConfigs.KEY_ROUND_CURRENT).equals("1")));
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return arrRound;
    }

    public static SettingObj parseSettings(String json) {
        try {
            JSONObject jsonObj = new JSONObject(json);
            if (jsonObj.getString(JsonConfigs.KEY_JSON_STATUS).equalsIgnoreCase(JsonConfigs.JSON_STATUS_SUCCESS)) {
                JSONObject dataObject = jsonObj.getJSONObject(JsonConfigs.KEY_JSON_DATA);
                ArrayList<String> arrClub = new ArrayList<>();
                if (!dataObject.getString(JsonConfigs.KEY_SETTING_CLUBS).equals("")) {
                    if (!dataObject.getString(JsonConfigs.KEY_SETTING_CLUBS).trim().equals("null")) {
                        String[] strClub = dataObject.getString(JsonConfigs.KEY_SETTING_CLUBS).split(",");
                        if (strClub.length > 0) {
                            for (int i = 0; i < strClub.length; i++) {
                                if (!strClub[i].trim().equals("")) {
                                    arrClub.add(strClub[i]);
                                }
                            }
                        }
                    } else {
                        arrClub = null;
                    }
                }

                return new SettingObj(dataObject.getString(JsonConfigs.KEY_SETTING_STATUS), arrClub);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static ArrayList<OddObj> parseOdds(String json) {
        ArrayList<OddObj> arrOdds = new ArrayList<>();
        try {
            JSONObject jsonObj = new JSONObject(json);
            if (jsonObj.getString(JsonConfigs.KEY_JSON_STATUS)
                    .equalsIgnoreCase(JsonConfigs.JSON_STATUS_SUCCESS)) {
                JSONArray arr = jsonObj.getJSONArray(JsonConfigs.KEY_JSON_DATA);
                for (int i = 0; i < arr.length(); i++) {
                    JSONObject obj = arr.getJSONObject(i);
                    arrOdds.add(new OddObj(obj.getString(JsonConfigs.KEY_NAME), obj.getString(JsonConfigs.KEY_HOME),
                            obj.getString(JsonConfigs.KEY_DRAW), obj.getString(JsonConfigs.KEY_AWAY),
                            obj.getString(JsonConfigs.KEY_URL)));
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return arrOdds;
    }
}
