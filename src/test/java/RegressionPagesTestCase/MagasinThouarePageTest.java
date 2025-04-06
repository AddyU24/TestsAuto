package RegressionPagesTestCase;

import org.testng.Assert;
import org.testng.annotations.Test;


import RegressionPagesObjetModel.HomeRecPage;
import RegressionPagesObjetModel.MagasinThouarePage;


public class MagasinThouarePageTest extends BaseTest {
	
	@Test
	void AffichageFormulaireNousContacter_FR7010() throws InterruptedException {
		
		loginToApp();
		HomeRecPage homePage = new HomeRecPage(driver);
		MagasinThouarePage magasinThouarePage = homePage.goToMagasinThouarePage();
		//magasinThouarePage.ClickSurBouttonNousContacter();
		Assert.assertTrue(magasinThouarePage.VerifierAffichageDesChampsDuFormulaireNousContacter());
	}

	
}
