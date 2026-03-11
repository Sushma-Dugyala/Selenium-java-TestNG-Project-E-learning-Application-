package testcases;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.NavigationPage;
import pages.VideoPage;

public class NavigationTest extends BaseTest {
	NavigationPage navigationPage;
	VideoPage videoPage; 

	@BeforeMethod
	public void setupNavigation() {
		createAndLoginUser();
		navigationPage = new NavigationPage(driver);
		videoPage = new VideoPage(driver);
	}
	@Test
	public void completeNavigationFlowTest() throws Exception {
		//About
		navigationPage.goToAbout();
		Assert.assertTrue(navigationPage.isAboutPageLoaded(),"About page not loaded");
		Thread.sleep(3000);
		// Courses
		navigationPage.goToCourses();
		Thread.sleep(3000);
		//Playlist
		navigationPage.openPlaylist();
		Assert.assertTrue(navigationPage.isPlaylistLoaded(),
				"Playlist page not loaded");
		Thread.sleep(3000);
		//Video
		navigationPage.openFirstVideo();
		Assert.assertTrue(videoPage.isVideoDisplayed(),"Video player not displayed");
		videoPage.playVideo();
		Thread.sleep(5000);
		Assert.assertTrue(videoPage.isVideoPlaying(),"Video did not start playing");
		//        //Teachers
		navigationPage.goToTeachers();
		Assert.assertTrue(navigationPage.isTeachersPageLoaded(),
				"Teachers page not loaded");
	}
}
