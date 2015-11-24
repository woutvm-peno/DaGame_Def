package andreas.gps;


import android.app.Activity;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;

public class Servercomm extends Activity {

    public List<Object> receivedmessage = new ArrayList<>();
    String dataToPut;
    String TAG = "abcd";

    public void getMessage() {

        new GetAsyncTask().execute("http://daddi.cs.kuleuven.be/peno3/data/B3_test/666");
    }

    public void putMessage(String message) {
        dataToPut = message;
        new PutAsyncTask().execute("http://daddi.cs.kuleuven.be/peno3/data/B3_test/666");
    }


    public List getLastMessage(){
        return receivedmessage;
    }

    public void sendMessage(JSONObject data) {
        Log.i(TAG, "sending message");

        try {
            JSONObject obj = new JSONObject();
            obj.put("groupid", "B3_test");
            obj.put("sessionid", "666");
            obj.put("data", data);
            mSocket.emit("broadcast", obj);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.i(TAG, "message sent");
    }

    public String getDataFromServer(String URL) {
        String result = "";
        try {
            Log.i(TAG, "gettin data from server");
            Log.i(TAG, URL);
            java.net.URL url = new URL(URL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");
            Log.i(TAG, "1");

            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + conn.getResponseCode());
            }
            Log.i(TAG, "2");

            BufferedReader br = new BufferedReader(new InputStreamReader(
                    (conn.getInputStream())));
            Log.i(TAG, "3");
            //Log.i(TAG,br.readLine());
            String output;
            Log.i(TAG, "starting while loop");
            while ((output = br.readLine()) != null) {
                Log.i(TAG, "line processed");
                result += output;
            }
            Log.i(TAG, result);
            conn.disconnect();

        } catch (Exception e) {
            Log.d(TAG, e.getLocalizedMessage());
        }
        return result;
    }


    public String putDataToServer(String URL) {
        String status = "Put the data to server successfully!";
        try {
            Log.i(TAG, "Putting data to server");
            Log.i(TAG, URL);
            URL url = new URL(URL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("PUT");

            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");


            String input = dataToPut;

            OutputStream os = conn.getOutputStream();
            os.write(input.getBytes());
            os.flush();


            //Read the acknowledgement message after putting data to server
            BufferedReader br = new BufferedReader(new InputStreamReader(
                    (conn.getInputStream())));
            String output;
            System.out.println("Output from Server .... \n");
            while ((output = br.readLine()) != null) {
                System.out.println(output);
            }
            conn.disconnect();

        } catch (MalformedURLException e) {
            e.printStackTrace();
            Log.i(TAG, "catch 1");
            Log.i(TAG, String.valueOf(e));
        } catch (IOException e) {
            Log.i(TAG, "catch 2");
            Log.i(TAG, String.valueOf(e));
            e.printStackTrace();
        }
        return status;
    }




    class GetAsyncTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... urls) {
            Log.i(TAG, "urls coming up");
            Log.i(TAG, urls[0]);
            return getDataFromServer(urls[0]);
        }

        @Override
        protected void onPostExecute(String result) {
            return;
        }
    }


    class PutAsyncTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... urls) {
            return putDataToServer(urls[0]);
        }

        @Override
        protected void onPostExecute(String result) {
            return;
        }
    }


    static ServercommEventListener mCallback;

    public interface ServercommEventListener {
        void respondToMessage();
    }

    static void registerCallback(Activity callback) {
        mCallback = (ServercommEventListener) callback;
    }

    private Emitter.Listener onNewMessage = new Emitter.Listener() {
        @Override
        public void call(final Object... args) {
            Runnable r = new Runnable() {
                @Override
                public void run() {
                    Log.i(TAG, "message received");
                    JSONObject data = (JSONObject) args[0];
                    String message;
                    String receiver;
                    String category;
                    String sender;
                    Integer points;
                    Double latitude;
                    Double longitude;
                    try {
                        //you should define the parameter of getString according to your data content
                        message = data.getString("message");
                        receiver = data.getString("receiver");
                        category = data.getString("category");
                        sender = data.getString("sender");
                        points = data.getInt("points");
                        Log.i(TAG,sender);
                        Log.i(TAG, message);
                        latitude = data.getDouble("latitude");
                        longitude = data.getDouble("longitude");

                    } catch (JSONException e) {
                        Log.i(TAG, "exception");
                        Log.i(TAG, e.toString());
                        return;
                    }
                    receivedmessage.clear();
                    receivedmessage.add(receiver);
                    receivedmessage.add(sender);
                    receivedmessage.add(category);
                    receivedmessage.add(message);
                    receivedmessage.add(points);
                    receivedmessage.add(latitude);
                    receivedmessage.add(longitude);
                    mCallback.respondToMessage();



                }
            };
            new Handler(Looper.getMainLooper()).post(r);
        }
    };
    private Socket mSocket;

    {
        try {
            IO.Options opts = new IO.Options();
            opts.path = "/peno3/socket.io";
            mSocket = IO.socket("http://daddi.cs.kuleuven.be", opts);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
        mSocket.on("broadcastReceived", onNewMessage);

        mSocket.connect();

        try {
            JSONObject obj = new JSONObject();
            obj.put("groupid", "B3_test");
            obj.put("sessionid", "666");
            mSocket.emit("register", obj);
        } catch (JSONException e) {
            e.printStackTrace();
        }


    }
}
