package com.example.testingapps;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

import android.widget.Button;
import android.widget.EditText;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.drafts.Draft;
import org.java_websocket.drafts.Draft_6455;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;


@RunWith(MockitoJUnitRunner.class)
public class MockitoTests {

    //These are the Calculator class tests
    @Mock
    private calculator demo = mock(calculator.class);
    @Mock
    private foodEntryPage demo2 = mock(com.example.testingapps.foodEntryPage.class);
    @Mock
    Chat test = mock(Chat.class);

    @Mock
    WebSocketClient test1 = mock(WebSocketClient.class);
    Draft[] drafts = {
            new Draft_6455()
    };
    //addTest() - cspainho
    @Test
    public void addTest(){
        int a = 2;
        int b = 3;
        int answer = 5;
        if(demo.add){
            assertEquals(answer, demo.equalsTotal());
            //Expect 2+3
        }
    }
    //subtractTest() - cspainho
    @Test
    public void subtractTest(){
        int a = 2;
        int b = 3;
        int answer = -1;
        if(demo.subtract){
            assertEquals(answer, demo.equalsTotal());
            //Expect 2-3
        }
    }
    //multiplyTest() - cspainho
    @Test
    public void multiplyTest(){
        int a = 2;
        int b = 3;
        int answer = 6;
        if(demo.multiply){
            assertEquals(answer, demo.equalsTotal());
            //Expect 2*3
        }
    }
    //divideTest() - cspainho
    @Test
    public void divideTest(){
        int a = 2;
        int b = 3;
        int answer = 0;
        if(demo.divide){
            assertEquals(answer, demo.equalsTotal());
            //Expect 0
        }
    }

    /*
    Websocket test - cspainho
     */

    @Test
    public void connectionTest(){
        test1.close();
        assertEquals(false,test1.isClosed());
    }

    /*
    Admin home and login tests
     */
    @Test
    public void adminTest(){
        adminLogin test = mock(adminLogin.class);
        test.createAccount();
        assertEquals(null, test.idNum);
    }

   /* @Test
    public void adminHomeTest(){

        adminHome test = mock(adminHome.class);
        try {
            test.deleteAccount();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(test.)
        assertEquals("null");
    }
*/

//Zachs mockito test
  //These are the Main Menu Tests
  //  @InjectMocks
  //  calculator calculator;

 //   @InjectMocks
 //   workoutPage workoutPage;

  //  @InjectMocks
  //  foodEntryPage foodEntryPage;

 //   @InjectMocks
 //   CalorieCounter_activity CalorieCounter_activity;

   // @InjectMocks
 //  MainActivity mainActivity;

    //login test zach
    @Mock
    private MainActivity dem = mock(MainActivity.class);
    @Mock
    register demo3 = mock(register.class);
    @Test
    public void logUsertest()
    {

        EditText user = dem.et_username;
        if(user != null){
         assertEquals(dem.et_username, user);
        }
    }
    //zach
    @Test
    public void loginpassword(){
        EditText pass = dem.et_password;
        if(pass != null){
            assertEquals(dem.et_password, pass);
        }
    }
    @Mock
    private Button mockButton;

    private MainActivity tested;

   /* @Test
   public void shouldStartActivity() throws Exception {
       // Let's setup our mockButton so that it will capture the listener
        ArgumentCaptor<View.OnClickListener> captor =
                ArgumentCaptor.forClass(View.OnClickListener.class);
        doNothing().when(mockButton).setOnClickListener(captor.capture());
        // When we call the onCreate...
        dem.onCreate(mock(Bundle.class));

        // Listener should be in place (Was tested before) so now we need to check that it starts
        //  an activity.
        doNothing().when(dem).startActivity((Intent) any());
        doNothing().when(dem).finish();  // We should also test this to be called in a new test.
        captor.getValue().onClick(mockButton);
        verify(dem).startActivity((Intent) any());
    }
*/
    //zach
    @Test
    public void regInput(){
        EditText input = demo3.et_username;
        EditText password = demo3.et_password;
        EditText cpass = demo3.et_cpassword;
        if(input!=null && password==cpass){
            assertEquals(demo3.et_username,input);

        }
    }
//zach
    @Test
    public void testOnClickListener() {
      Button pressed = dem.btn_login;
      boolean press = true;
      if(pressed == null ){
          press = false;

      }
    }
    //zach
    @Test
    public void TrainerTest(){
        trainerLogin test = mock(trainerLogin.class);
        test.getId();
        assertEquals(null, test.idNum);
    }
    //zach
    @Test
    public void UserTest(){
        user testing = new user(16,"will","smith","100");
        assertEquals(testing.getId(),16);
        assertEquals(testing.getUsername(),"will");
        assertEquals(testing.getPassword(),"smith");
        assertEquals(testing.getCalories(),"100");


    }
}
