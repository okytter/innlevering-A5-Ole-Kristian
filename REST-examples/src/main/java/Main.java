import org.json.JSONObject;

public class Main {

    private static String host = "104.248.47.74";
    private static int port = 80;
    private static String getPath = "dkrest/test/get";
    private static String postPath= "dkrest/auth";
    private static String phoneNumber = "92648523";
    private static String email = "olekrisy@stud.ntnu.no";


    /**
    *sends a request to a server and parses the information and prints its information.
     */
    public static void main(String[] args){
        //GETExample request = new GETExample(host, port);
        //String responseString = request.sendGet(getPath);
        //JSONParseExample.objectExample(responseString);
        POSTExample ans = new POSTExample(host, port);
        JSONParseExample parsing = new JSONParseExample();
        String loginString = "";
        JSONObject loginInfo = new JSONObject();
        loginInfo.put("phone", phoneNumber);
        loginInfo.put("email", email);
        POSTExample post = new POSTExample(host, port);
        loginString = post.sendPost(postPath, loginInfo);

        JSONObject loginDetails = new JSONObject(loginString);
        if(loginDetails.getBoolean("success")) {
            int sessionID = 0;
            sessionID = loginDetails.getInt("sessionId");
            String path1 = "dkrest/gettask/1?sessionId=" + sessionID;
            GETExample getTask = new GETExample(host, port);
            //task1 completed.
            String responseString1 = getTask.sendGet(path1);
            String solvePath = "dkrest/solve";
            JSONObject task1Solved = new JSONObject();
            task1Solved.put("sessionId", sessionID);
            task1Solved.put("msg", "Hello");
            ans.sendPost(solvePath, task1Solved);
            //task 2 completed
            String path2 = "dkrest/gettask/2?sessionId=" + sessionID;
            String responseString2 = getTask.sendGet(path2);
            JSONObject task2Solved = new JSONObject();
            task2Solved.put("sessionId", sessionID);
            String element1 = parsing.arrayExample(JSONParseExample.objectExample(responseString2));
            task2Solved.put("msg", element1);
            ans.sendPost(solvePath, task2Solved);
        }
    }

}
