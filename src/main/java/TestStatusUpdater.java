package main.java;

import main.java.com.gurock.testrail.APIClient;
import main.java.com.gurock.testrail.APIException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by davits on 7/20/17.
 */
public class TestStatusUpdater {

    public static ResponseDto receiveTestData(String testId, int status, String comment) throws IOException, APIException {
        ResponseDto response = new ResponseDto();
        TestStatus testStatus = TestStatus.valueOf(status);

        // validate testId
        if (testId == null) {
            response.setStatus(Response.Status.BAD_REQUEST);
            response.setMessage("testId is required");
        }

        // validate status
        if (testStatus == null) {
            response.setStatus(Response.Status.BAD_REQUEST);
            response.setMessage("invalid status");
        }

        if (response.getStatus() == null) {
            response = updateTestStatus(testId, status, comment);
        }

        return response;
    }


    private static ResponseDto updateTestStatus(String testId, int status, String comment) throws IOException {

        ResponseDto responseDto = new ResponseDto();

        //auth/login
        APIClient client = new APIClient("https://libre.testrail.net");
        client.setUser("aram@develandoo.com");
        client.setPassword("********");

          //gets necessary fields for test
//        JSONArray projects = (JSONArray) client.sendGet("get_projects");
//        JSONArray runs  = (JSONArray) client.sendGet("get_runs/" + ((JSONObject)projects.get(0)).get("id"));
//        JSONArray tests = (JSONArray) client.sendGet("get_tests/" + ((JSONObject) runs.get(0)).get("id"));
//        String tId = String.valueOf(((JSONObject)tests.get(0)).get("id"));

        Map<String, Object> data = new HashMap<>();
        data.put("status_id", status);
        data.put("comment", comment);

        try {
            //update test status
            JSONObject addResult = (JSONObject) client.sendPost("add_result/" + testId, data);

            if (!addResult.isEmpty()) {
                JSONObject test = (JSONObject) client.sendGet("get_test/" + testId);
                System.out.println("test id = " + test.get("id") + ", " + "status id = " + test.get("status_id"));
            }
        } catch (APIException e) {
            responseDto.setStatus(Response.Status.INTERNAL_SERVER_ERROR);
            responseDto.setMessage(e.getMessage() + " test id: " + testId);
            return responseDto;
        }

        responseDto.setStatus(Response.Status.OK);
        responseDto.setMessage("Test status updated");
        return responseDto;
    }


    public static void main(String[] args) throws IOException, APIException {

        ResponseDto result = receiveTestData("627", 1, "Some comment --testing--");

    }
}
