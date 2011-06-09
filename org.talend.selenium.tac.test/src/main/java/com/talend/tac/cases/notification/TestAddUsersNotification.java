package com.talend.tac.cases.notification;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class TestAddUsersNotification extends AddNotification {
	
	 @Test(groups={"AddUserNotification"})
	 public void clearAllNotifications() {
    	 
         this.clickWaitForElementPresent("!!!menu.notification.element!!!");	
    
    	 for(int i=0;;i++) {
    		    if(selenium.isElementPresent("//div[@class='x-grid3-cell-inner x-grid3-col-category']")) {
    		    	selenium.mouseDown("//div[@class='x-grid3-cell-inner x-grid3-col-category']");
      				selenium.chooseOkOnNextConfirmation();
      				selenium.click("idSubModuleDeleteButton");
      				selenium.setSpeed(MID_SPEED);
      			    Assert.assertTrue(selenium.getConfirmation().matches("^Are you sure you want to remove the selected notification [\\s\\S]$"));
        		    selenium.setSpeed(MIN_SPEED);
        		 
    		    } else {
    		    	
    		    	System.out.println("element not exist");
    		    	break;
    		    }
    			
    	 }
    }
	 
	//add a user notification(MailNewUserNotification)
	@Test(dependsOnMethods={"clearAllNotifications"})
	@Parameters({"categoryUser","eventNewUser","descriptionNewUser","userName"})
	public void testAddUsersMailNewUserNotification(String categoryUser, String eventNewUser, 
			String descriptionNewUser, String LoginName) {
		
		this.addUserNotification(2, categoryUser, 1, eventNewUser, descriptionNewUser, LoginName);
		selenium.click("idFormSaveButton");
		selenium.setSpeed(MID_SPEED);
        Assert.assertTrue(selenium.isElementPresent("//div[text()='"+eventNewUser+"']/" +
        		"parent::td/parent::tr//img[@class='gwt-Image' and @title='true']"));		
 		selenium.setSpeed(MIN_SPEED);
	}
	//add a user notification(UserCreationNotification)
	@Test(dependsOnMethods={"testAddUsersMailNewUserNotification"})
	@Parameters({"categoryUser","eventUserCreation","descriptionUserCreation","userName"})
	public void testAddUsersUserCreationNotification(String categoryUser, String eventUserCreation,
			String descriptionUserCreation, String LoginName) {
		
		this.addUserNotification(2, categoryUser, 2,  eventUserCreation, descriptionUserCreation, LoginName);
		
		selenium.click("idFormSaveButton");
		selenium.setSpeed(MID_SPEED);
        Assert.assertTrue(selenium.isElementPresent("//div[text()='"+eventUserCreation+"']/" +
        		"parent::td/parent::tr//img[@class='gwt-Image' and @title='true']"));		
 		selenium.setSpeed(MIN_SPEED);
 		
	}
	//add a user notification(UserDeletionNotification)
	@Test(dependsOnMethods={"testAddUsersUserCreationNotification"})
	@Parameters({"categoryUser","eventUserDeletion","descriptionUserDeletion","userName"})
	public void testAddUsersUserDeletionNotification(String categoryUser, String eventUserDeletion,
			String descriptionUserDeletion, String LoginName) {
		
		this.addUserNotification(2, categoryUser, 3, eventUserDeletion, descriptionUserDeletion, LoginName);

		selenium.click("idFormSaveButton");
		selenium.setSpeed(MID_SPEED);
        Assert.assertTrue(selenium.isElementPresent("//div[text()='"+eventUserDeletion+"']/" +
        		"parent::td/parent::tr//img[@class='gwt-Image' and @title='true']"));		
 		selenium.setSpeed(MIN_SPEED);
 		
	}
	//add a user notification(Uncheck Active)
	@Test(dependsOnMethods={"testAddUsersUserDeletionNotification"})
	@Parameters({"categoryUser","eventNewUser","descriptionNewUser","userName"})
	public void testAddUsersNotificationUncheckActive(String categoryUser, String eventNewUser, 
			String descriptionNewUser, String LoginName) {
		
		this.addUserNotification(2, categoryUser, 1, eventNewUser, descriptionNewUser, LoginName);
		selenium.click("idActiveInput");
		selenium.setSpeed(MID_SPEED);
		Assert.assertFalse(selenium.isChecked("idActiveInput"));
		selenium.setSpeed(MIN_SPEED);
		
		selenium.click("idFormSaveButton");
		selenium.setSpeed(MID_SPEED);
        Assert.assertTrue(selenium.isElementPresent("//div[text()='"+eventNewUser+"']/" +
        		"parent::td/parent::tr//img[@class='gwt-Image' and @title='false']"));		
 		selenium.setSpeed(MIN_SPEED);
		
	}
	

}
