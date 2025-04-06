package RegressionPagesTestCase;

import org.testng.Assert;
import org.testng.annotations.Test;

import RegressionPagesObjetModel.HomeRecPage;

public class HomeRecPageTest extends BaseTest {

	@Test
	public void AffichageMessageErrorZeroResultat_6764() throws InterruptedException{
		loginToApp();
		HomeRecPage homePage = new HomeRecPage(driver);
		Assert.assertTrue(homePage.AffichageDuMessageZeroResultat("noresultat"), "Le message d'alerte pour recherche sans resultat n'est pas affiché");
	}
	
}
