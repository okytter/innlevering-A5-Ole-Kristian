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


        JSONObject loginInfo = new JSONObject();
        loginInfo.put("phone", phoneNumber);
        loginInfo.put("email", email);
        POSTExample post = new POSTExample(host, port);
        post.sendPost(postPath, loginInfo);

    }

}
