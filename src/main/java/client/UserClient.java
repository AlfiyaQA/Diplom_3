package client;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import model.User;
import static org.apache.http.HttpStatus.*;

public class UserClient extends RestAssuredClient {

    private final String REGISTRATION = "/auth/register";
    private final String USERDELETE = "/auth/user";

    @Step("Create new user")
    public ValidatableResponse createUser(User user) {
        return reqSpec
                .body(user)
                .when()
                .post(REGISTRATION)
                .then().log().all();
    }

    @Step("Delete user")
    public void deleteUser(String token) {
        reqSpec
                .auth().oauth2(token)
                .when()
                .delete(USERDELETE)
                .then().log().all().assertThat()
                .statusCode(SC_ACCEPTED);
    }
}
