import org.json.JSONObject;
import org.apache.commons.codec.digest.DigestUtils;


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
            //task 3 completed
            String path3 = "dkrest/gettask/3?sessionId=" + sessionID;
            String responseString3 = getTask.sendGet(path3);
            JSONObject task3Solved = new JSONObject();
            task3Solved.put("sessionId", sessionID);
            String sum = JSONParseExample.arrayExample(JSONParseExample.objectExample(responseString3));
            task3Solved.put("result", Integer.parseInt(sum));
            ans.sendPost(solvePath, task3Solved);
            //task 4 completed
            String path4 = "dkrest/gettask/4?sessionId=" + sessionID;
            String responseString4 = getTask.sendGet(path4);
            JSONObject task4Solved = new JSONObject();
            task4Solved.put("sessionId", sessionID);
            String encryptedCode = JSONParseExample.arrayExample(JSONParseExample.objectExample(responseString4));
            int pin = decryptionOfMD5(encryptedCode);
            task4Solved.put("pin", pin);
            ans.sendPost(solvePath, task4Solved);
            //secret task
            String path5 = "dkrest/gettask/2016?sessionId=" + sessionID;
            String responsString5 = getTask.sendGet(path5);
            JSONObject secretTask = new JSONObject();
            secretTask.put("sessionId", sessionID);
            //result feedback
            String resultPath = "dkrest/results/" + sessionID;
            getTask.sendGet(resultPath);
        }
    }

    private static int decryptionOfMD5(String encryptedCode) {
        int pin = 0;
        for(int i = 0000; i <= 9999; i++) {
            String result = DigestUtils.md5Hex(Integer.toString(i));
            if(encryptedCode.equals(result)){
                pin = i;
            }
        }
        return pin;
    }

}
