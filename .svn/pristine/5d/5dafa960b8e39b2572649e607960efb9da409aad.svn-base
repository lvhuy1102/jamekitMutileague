package com.hcpt.multileagues.modelmanager;

import android.content.Context;
import android.util.Log;

import com.android.volley.VolleyError;
import com.hcpt.multileagues.configs.GlobalValue;
import com.hcpt.multileagues.configs.WebservicesConfigs;
import com.hcpt.multileagues.database.DatabaseUtility;
import com.hcpt.multileagues.network.ParameterFactory;
import com.hcpt.multileagues.objects.APIObj;
import com.hcpt.multileagues.utilities.DateTimeUtility;
import com.hcpt.multileagues.volley.HttpError;
import com.hcpt.multileagues.volley.HttpGet;
import com.hcpt.multileagues.volley.HttpListener;
import com.hcpt.multileagues.volley.HttpPost;
import com.hcpt.multileagues.volley.HttpRequest;

import org.json.JSONException;

import java.util.HashMap;
import java.util.Map;

public class ModelManager {

    private static String TAG = ModelManager.class.getSimpleName();

    public static void getClubs(final Context context, boolean isProgess,
                                final ModelManagerListener listener) {

        final String url = WebservicesConfigs.URL_GET_RANK_CLUBS;

        if (!DatabaseUtility.checkExistsApi(context, url + GlobalValue.leagueId)) {
            APIObj apiInfo = new APIObj();
            // add vao database
            apiInfo.setmApi(url + GlobalValue.leagueId);
            if (DatabaseUtility.insertApi(context, apiInfo)) {
                Log.e(TAG, "INSERT successfully");
            }
        }

        // Create params
        Map<String, String> params = ParameterFactory.createLeagueIdParams();
        new HttpGet(context, url, params, isProgess, new HttpListener() {
            @Override
            public void onHttpResponse(Object response) {
                if (response != null) {
                    listener.onSuccess(response.toString());

                    if (DatabaseUtility.updateResuft_Api(context,
                            response.toString(), url + GlobalValue.leagueId)) {
                    }
                } else {
                    listener.onError();
                }
            }
        }, new HttpError() {
            @Override
            public void onHttpError(VolleyError volleyError) {
                listener.onError();
            }
        });
    }

    public static void getClubsGroup(final Context context, boolean isProgess,
                                     final ModelManagerListener listener) {

        final String url = WebservicesConfigs.URL_GET_RANK_GROUP;

        if (!DatabaseUtility.checkExistsApi(context, url + GlobalValue.leagueId)) {
            APIObj apiInfo = new APIObj();
            // add vao database
            apiInfo.setmApi(url + GlobalValue.leagueId);
            if (DatabaseUtility.insertApi(context, apiInfo)) {
                Log.e(TAG, "INSERT successfully");
            }
        }

        // Create params
        Map<String, String> params = new HashMap<>();
        new HttpGet(context, url, params, isProgess, new HttpListener() {
            @Override
            public void onHttpResponse(Object response) {
                if (response != null) {
                    listener.onSuccess(response.toString());

                    if (DatabaseUtility.updateResuft_Api(context,
                            response.toString(), url + GlobalValue.leagueId)) {
                    }
                } else {
                    listener.onError();
                }
            }
        }, new HttpError() {
            @Override
            public void onHttpError(VolleyError volleyError) {
                listener.onError();
            }
        });
    }

    public static void getTopScorer(final Context context, boolean isProgess,
                                    final ModelManagerListener listener) {

        final String url = WebservicesConfigs.URL_GET_TOP_SCORER;

        if (!DatabaseUtility.checkExistsApi(context, url + GlobalValue.leagueId)) {
            APIObj apiInfo = new APIObj();
            // add vao database
            apiInfo.setmApi(url + GlobalValue.leagueId);
            if (DatabaseUtility.insertApi(context, apiInfo)) {
                Log.e(TAG, "INSERT successfully");
            }
        }

        // Create params
        Map<String, String> params = ParameterFactory.createLeagueIdParams();
        new HttpGet(context, url, params, isProgess, new HttpListener() {
            @Override
            public void onHttpResponse(Object response) {
                if (response != null) {
                    listener.onSuccess(response.toString());

                    if (DatabaseUtility.updateResuft_Api(context,
                            response.toString(), url + GlobalValue.leagueId)) {
                    }
                } else {
                    listener.onError();
                }
            }
        }, new HttpError() {
            @Override
            public void onHttpError(VolleyError volleyError) {
                listener.onError();
            }
        });
    }

    public static void getMatchesByClub(final Context context, final String clubId, boolean isProgess,
                                        final ModelManagerListener listener) {

        final String url = WebservicesConfigs.URL_GET_MATCHES_BY_CLUB;

        if (!DatabaseUtility.checkExistsApi(context, url + GlobalValue.leagueId + clubId)) {
            APIObj apiInfo = new APIObj();
            // add vao database
            apiInfo.setmApi(url + GlobalValue.leagueId + clubId);

            if (DatabaseUtility.insertApi(context, apiInfo)) {
                Log.e(TAG, "INSERT successfully");
            }

        }

        // Create params
        Map<String, String> params = ParameterFactory.createClubIdParams(clubId);
        new HttpGet(context, url, params, isProgess, new HttpListener() {
            @Override
            public void onHttpResponse(Object response) {
                if (response != null) {
                    listener.onSuccess(response.toString());

                    if (DatabaseUtility.updateResuft_Api(context,
                            response.toString(), url + GlobalValue.leagueId + clubId)) {
                        Log.e(TAG, "Update successfully");
                    }
                } else {
                    listener.onError();
                }
            }
        }, new HttpError() {
            @Override
            public void onHttpError(VolleyError volleyError) {
                listener.onError();
            }
        });
    }

    public static void getMatchesByRound(final Context context, final String roundId,
                                         boolean isProgess, final ModelManagerListener listener) {

        final String url = WebservicesConfigs.URL_GET_MATCHES_BY_ROUND;

        if (!DatabaseUtility.checkExistsApi(context, (url + GlobalValue.leagueId + roundId))) {
            APIObj apiInfo = new APIObj();
            // add vao database
            apiInfo.setmApi(url + GlobalValue.leagueId + roundId);

            if (DatabaseUtility.insertApi(context, apiInfo)) {
                Log.e(TAG, "INSERT successfully");
            }

        }

        // Create params
        Map<String, String> params = ParameterFactory.createRoundIdParams(roundId);
        new HttpGet(context, url, params, isProgess, new HttpListener() {
            @Override
            public void onHttpResponse(Object response) {
                if (response != null) {
                    listener.onSuccess(response.toString());

                    if (DatabaseUtility.updateResuft_Api(context,
                            response.toString(), url + GlobalValue.leagueId + roundId)) {
                        Log.e(TAG, "Update successfully");
                    }
                } else {
                    listener.onError();
                }
            }
        }, new HttpError() {
            @Override
            public void onHttpError(VolleyError volleyError) {
                listener.onError();
            }
        });
    }

    public static void getMatchesByClubAndRound(final Context context,
                                                final String clubId, final String roundId, boolean isProgess,
                                                final ModelManagerListener listener) {

        final String url = WebservicesConfigs.URL_GET_MATCHES_BY_CLUB_AND_ROUND;

        if (!DatabaseUtility.checkExistsApi(context, (url
                + GlobalValue.leagueId + clubId + roundId))) {
            APIObj apiInfo = new APIObj();

            // add vao database
            apiInfo.setmApi(url + GlobalValue.leagueId + clubId + roundId);
            if (DatabaseUtility.insertApi(context, apiInfo)) {
                Log.e(TAG, "INSERT successfully");
            }
        }

        // Create params
        Map<String, String> params = ParameterFactory.createClubandRoundIdParams(clubId, roundId);
        new HttpGet(context, url, params, isProgess, new HttpListener() {
            @Override
            public void onHttpResponse(Object response) {
                if (response != null) {
                    listener.onSuccess(response.toString());

                    if (DatabaseUtility.updateResuft_Api(context,
                            response.toString(), url + GlobalValue.leagueId + clubId + roundId)) {
                        Log.e(TAG, "Update successfully");
                    }
                } else {
                    listener.onError();
                }
            }
        }, new HttpError() {
            @Override
            public void onHttpError(VolleyError volleyError) {
                listener.onError();
            }
        });
    }

    public static void getMatchById(final Context context, final String matchId,
                                    boolean isProgress, final ModelManagerListener listener) {
        final String url = WebservicesConfigs.URL_GET_MATCH_BY_ID;

        if (!DatabaseUtility.checkExistsApi(context, (url + matchId))) {
            APIObj apiInfo = new APIObj();
            // add vao database
            apiInfo.setmApi(url + matchId);
            if (DatabaseUtility.insertApi(context, apiInfo)) {
                Log.e(TAG, "INSERT successfully");
            }
        }

        // Create params
        Map<String, String> params = ParameterFactory.createMatchIdParams(matchId);
        new HttpGet(context, url, params, isProgress, new HttpListener() {
            @Override
            public void onHttpResponse(Object response) {
                if (response != null) {
                    listener.onSuccess(response.toString());

                    if (DatabaseUtility.updateResuft_Api(context,
                            response.toString(), url + matchId)) {
                        Log.e(TAG, "Update successfully");
                    }
                } else {
                    listener.onError();
                }
            }
        }, new HttpError() {
            @Override
            public void onHttpError(VolleyError volleyError) {
                listener.onError();
            }
        });
    }

    public static void getEventsByMatch(final Context context, final String matchId,
                                        boolean isProgess, final ModelManagerListener listener) {

        final String url = WebservicesConfigs.URL_GET_EVENTS_BY_MATCH;

//        if (!DatabaseUtility.checkExistsApi(context, (url + matchId))) {
//            APIObj apiInfo = new APIObj();
//            // add vao database
//            apiInfo.setmApi(url + matchId);
//            if (DatabaseUtility.insertApi(context, apiInfo)) {
//                Log.e(TAG, "INSERT successfully");
//            }
//        }

        // Create params
        Map<String, String> params = ParameterFactory.createMatchIdParams(matchId);
        new HttpGet(context, url, params, isProgess, new HttpListener() {
            @Override
            public void onHttpResponse(Object response) {
                if (response != null) {
                    listener.onSuccess(response.toString());

//                    if (DatabaseUtility.updateResuft_Api(context,
//                            response.toString(), url + matchId)) {
//                        Log.e(TAG, "Update successfully");
//                    }
                } else {
                    listener.onError();
                }
            }
        }, new HttpError() {
            @Override
            public void onHttpError(VolleyError volleyError) {
                listener.onError();
            }
        });
    }

    public static void getLineUpsByMatch(final Context context, final String matchId,
                                         boolean isProgess, final ModelManagerListener listener) {

        final String url = WebservicesConfigs.URL_GET_LINEUPS_BY_MATCH;

        if (!DatabaseUtility.checkExistsApi(context, (url + matchId))) {
            APIObj apiInfo = new APIObj();
            // add vao database
            apiInfo.setmApi(url + matchId);
            if (DatabaseUtility.insertApi(context, apiInfo)) {
                Log.e(TAG, "INSERT successfully");
            }
        }

        // Create params
        Map<String, String> params = ParameterFactory.createMatchIdParams(matchId);
        new HttpGet(context, url, params, isProgess, new HttpListener() {
            @Override
            public void onHttpResponse(Object response) {
                if (response != null) {
                    listener.onSuccess(response.toString());

                    if (DatabaseUtility.updateResuft_Api(context,
                            response.toString(), url + matchId)) {
                        Log.e(TAG, "Update successfully");
                    }
                } else {
                    listener.onError();
                }
            }
        }, new HttpError() {
            @Override
            public void onHttpError(VolleyError volleyError) {
                listener.onError();
            }
        });
    }

    public static void postRegistratrionId(Context context, String registrationId) {

        String url = WebservicesConfigs.URL_GET_REGISTRATION_GMC_ID;

        // Create params
        Map<String, String> params = ParameterFactory.createGCMIdParams(context, registrationId);
        new HttpGet(context, url, params, false, new HttpListener() {
            @Override
            public void onHttpResponse(Object response) {
            }
        }, new HttpError() {
            @Override
            public void onHttpError(VolleyError volleyError) {
            }
        });
    }

    public static void registerNotification(Context context,
                                            String getNotification, String clubsId) {

        String url = WebservicesConfigs.URL_GET_NOTIFICATION;

        // Create params
        Map<String, String> params = ParameterFactory.createNotificationParams(context, getNotification, clubsId);
        Log.e(TAG, url + params.toString());
        new HttpGet(context, url, params, false, new HttpListener() {
            @Override
            public void onHttpResponse(Object response) {
                Log.e(TAG, response.toString());
            }
        }, new HttpError() {
            @Override
            public void onHttpError(VolleyError volleyError) {
            }
        });
    }

    public static void getLeagues(final Context context, boolean isProgess,
                                  final ModelManagerListener listener) {

        final String url = WebservicesConfigs.URL_GET_LEAGUE;

        if (!DatabaseUtility.checkExistsApi(context, (url))) {
            APIObj apiInfo = new APIObj();
            // add vao database
            apiInfo.setmApi(url);
            if (DatabaseUtility.insertApi(context, apiInfo)) {
                Log.e(TAG, "INSERT successfully");
            }
        }

        // Create params
        Map<String, String> params = new HashMap<>();
        new HttpGet(context, url, params, isProgess, new HttpListener() {
            @Override
            public void onHttpResponse(Object response) {
                if (response != null) {
                    listener.onSuccess(response.toString());

                    if (DatabaseUtility.updateResuft_Api(context,
                            response.toString(), url)) {
                        Log.e(TAG, "Update successfully");
                    }
                } else {
                    listener.onError();
                }
            }
        }, new HttpError() {
            @Override
            public void onHttpError(VolleyError volleyError) {
                listener.onError();
            }
        });
    }

    public static void getFeeds(final Context context, boolean isProgess,
                                final ModelManagerListener listener) {

        final String url = WebservicesConfigs.URL_GET_FEEDS_LINK;

        // Create params
        Map<String, String> params = ParameterFactory.createLeagueIdParams();
        new HttpGet(context, url, params, isProgess, new HttpListener() {
            @Override
            public void onHttpResponse(Object response) {
                if (response != null) {
                    listener.onSuccess(response.toString());
                } else {
                    listener.onError();
                }
            }
        }, new HttpError() {
            @Override
            public void onHttpError(VolleyError volleyError) {
                listener.onError();
            }
        });
    }

    public static void getFeedContent(final Context context, String url,
                                      boolean isProgess, final ModelManagerListener listener) {
        // Create params
        Map<String, String> params = new HashMap<>();
        new HttpGet(context, url, params, isProgess, new HttpListener() {
            @Override
            public void onHttpResponse(Object response) {
                if (response != null) {
                    listener.onSuccess(response.toString());
                } else {
                    listener.onError();
                }
            }
        }, new HttpError() {
            @Override
            public void onHttpError(VolleyError volleyError) {
                listener.onError();
            }
        });
    }

    public static void getDashboard(final Context context,
                                    final String dateTimestamp, String strDefault, boolean isProgess,
                                    final ModelManagerListener listener) {

        final String url = WebservicesConfigs.URL_GET_DASHBOARD;

        final String strDate = DateTimeUtility.convertTimeStampToDate(dateTimestamp, "yyyyMMdd");

        if (!DatabaseUtility.checkExistsApi(context, (url + strDate))) {
            APIObj apiInfo = new APIObj();
            // add vao database
            apiInfo.setmApi(url + strDate);
            if (DatabaseUtility.insertApi(context, apiInfo)) {
                Log.e(TAG, "INSERT successfully");
            }
        }

        // Create params
        Map<String, String> params = ParameterFactory.createDashboardParams(dateTimestamp, strDefault);
        new HttpGet(context, url, params, isProgess, new HttpListener() {
            @Override
            public void onHttpResponse(Object response) {
                if (response != null) {
                    listener.onSuccess(response.toString());

                    if (DatabaseUtility.updateResuft_Api(context,
                            response.toString(), url + strDate)) {
                        Log.e(TAG, "Update successfully");
                    }
                } else {
                    listener.onError();
                }
            }
        }, new HttpError() {
            @Override
            public void onHttpError(VolleyError volleyError) {
                listener.onError();
            }
        });
    }

    public static void getRoundsByLeague(final Context context, boolean isProgess,
                                         final ModelManagerListener listener) {

        final String url = WebservicesConfigs.URL_GET_ROUND;

        if (!DatabaseUtility.checkExistsApi(context, url + GlobalValue.leagueId)) {
            APIObj apiInfo = new APIObj();
            // add vao database
            apiInfo.setmApi(url + GlobalValue.leagueId);
            if (DatabaseUtility.insertApi(context, apiInfo)) {
                Log.e(TAG, "INSERT successfully");
            }
        }

        // Create params
        Map<String, String> params = ParameterFactory.createLeagueIdParams();
        new HttpGet(context, url, params, isProgess, new HttpListener() {
            @Override
            public void onHttpResponse(Object response) {
                if (response != null) {
                    listener.onSuccess(response.toString());

                    if (DatabaseUtility.updateResuft_Api(context,
                            response.toString(), url + GlobalValue.leagueId)) {

                    }
                } else {
                    listener.onError();
                }
            }
        }, new HttpError() {
            @Override
            public void onHttpError(VolleyError volleyError) {
                listener.onError();
            }
        });
    }

    public static void getGroupStanding(final Context context, boolean isProgess, String groupId,
                                        final ModelManagerListener listener) {

        final String url = WebservicesConfigs.URL_GET_GROUP_STANDING;

        // Create params
        Map<String, String> params = ParameterFactory.createShowGroupStandingParam(context, groupId);
        new HttpGet(context, url, params, isProgess, new HttpListener() {
            @Override
            public void onHttpResponse(Object response) {
                if (response != null) {
                    listener.onSuccess(response.toString());
                } else {
                    listener.onError();
                }
            }
        }, new HttpError() {
            @Override
            public void onHttpError(VolleyError volleyError) {
                listener.onError();
            }
        });
    }

    public static void getSettings(final Context context, final ModelManagerListener listener) {
        final String url = WebservicesConfigs.URL_GET_SETTINGS;

        if (!DatabaseUtility.checkExistsApi(context, url + GlobalValue.leagueId)) {
            APIObj apiInfo = new APIObj();
            // add vao database
            apiInfo.setmApi(url + GlobalValue.leagueId);
            if (DatabaseUtility.insertApi(context, apiInfo)) {
                Log.e(TAG, "INSERT successfully");
            }
        }

        // Create params
        Map<String, String> params = ParameterFactory.createGetSettingsParam(context);
        new HttpGet(context, url, params, false, new HttpListener() {
            @Override
            public void onHttpResponse(Object response) {
                if (response != null) {
                    listener.onSuccess(response.toString());

                    if (DatabaseUtility.updateResuft_Api(context,
                            response.toString(), url + GlobalValue.leagueId)) {
                    }
                } else {
                    listener.onError();
                }
            }
        }, new HttpError() {
            @Override
            public void onHttpError(VolleyError volleyError) {
                listener.onError();
            }
        });
    }

    public static void updateSettings(final Context context, String status, String clubId,
                                      final ModelManagerListener listener) {

        final String url = WebservicesConfigs.URL_UPDATE_SETTINGS;

        // Create params
        Map<String, String> params = ParameterFactory.createUpdateSettingsParam(context, status, clubId);
        new HttpPost(context, url, params, HttpRequest.REQUEST_STRING_PARAMS,false, new HttpListener() {
            @Override
            public void onHttpResponse(Object response) {
                if (response != null) {
                    listener.onSuccess(response.toString());
                } else {
                    listener.onError();
                }
            }
        }, new HttpError() {
            @Override
            public void onHttpError(VolleyError volleyError) {
                listener.onError();
            }
        });
    }

    public static void getOddsByMatch(final Context context, final String matchId,
                                      boolean isProgess, final ModelManagerListener listener) {

        final String url = WebservicesConfigs.URL_GET_ODDS;

        if (!DatabaseUtility.checkExistsApi(context, (url + matchId))) {
            APIObj apiInfo = new APIObj();
            // add vao database
            apiInfo.setmApi(url + matchId);
            if (DatabaseUtility.insertApi(context, apiInfo)) {
                Log.e(TAG, "INSERT successfully");
            }
        }

        // Create params
        Map<String, String> params = ParameterFactory.createMatchIdParams(matchId);
        new HttpGet(context, url, params, isProgess, new HttpListener() {
            @Override
            public void onHttpResponse(Object response) {
                if (response != null) {
                    listener.onSuccess(response.toString());

                    if (DatabaseUtility.updateResuft_Api(context,
                            response.toString(), url + matchId)) {
                        Log.e(TAG, "Update successfully");
                    }
                } else {
                    listener.onError();
                }
            }
        }, new HttpError() {
            @Override
            public void onHttpError(VolleyError volleyError) {
                listener.onError();
            }
        });
    }

    public static void getBanner(final Context context, final ModelManagerListener listener) {

        final String url = WebservicesConfigs.URL_GET_BANNER;

        // Create params
        Map<String, String> params = new HashMap<>();
        new HttpGet(context, url, params, false, new HttpListener() {
            @Override
            public void onHttpResponse(Object response) {
                if (response != null) {
                    listener.onSuccess(response.toString());
                } else {
                    listener.onError();
                }
            }
        }, new HttpError() {
            @Override
            public void onHttpError(VolleyError volleyError) {
                listener.onError();
            }
        });
    }

    /**
     * LineUp
     */
//    public static void getTimeline(String matchId, final Context context, boolean showDialog, final ModelManagerListener listener) {
//        String url = WebservicesConfigs.URL_GET_TIMELINES;
//        Map<String, String> params = new HashMap<>();
//        params.put("id", matchId);
//        new HttpGet(context, url, params, false, new HttpListener() {
//            @Override
//            public void onHttpResponse(Object response) {
//                if (response != null) {
//                    listener.onSuccess(response.toString());
//                } else {
//                    listener.onError();
//                }
//            }
//        }, new HttpError() {
//            @Override
//            public void onHttpError(VolleyError volleyError) {
//                listener.onError();
//            }
//        });
//    }
}