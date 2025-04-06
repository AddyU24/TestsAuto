package RegressionPagesTestCase;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import RegressionPagesObjetModel.FacturePage;
import RegressionPagesObjetModel.HomeRecPage;

public class FacturePageTest extends BaseTest {
	
	
	//Il faudrait tester en premier lieu la connexion 
	
	//@Test
	void ConnecterUtilisateur() throws InterruptedException {
		
		loginToApp();
	} 
	
	//@Test(dependsOnMethods= {"ConnecterUtilisateur"})
	public void MontantNegatifTest() throws InterruptedException {
		
		loginToApp();
		HomeRecPage homePage = new HomeRecPage(driver);
		FacturePage facturePage = homePage.goToFacturePage();
		facturePage.InsererMontant("-19,18");
		System.out.println("Test d'acceptation des montants negatifs ...   ");
		Assert.assertFalse(facturePage.ErrorMontantInvalide (), "Le message d'erreur est affiché alors que le montant est valide !");
	
		
	}
	
	@Test
	public void AffichageErrorChampsNomPrenom_FR6777() throws InterruptedException {
		loginToApp();
		HomeRecPage homePage = new HomeRecPage(driver);
		FacturePage facturePage = homePage.goToFacturePage();
		System.out.println("Test d'affichage des messages d'erreur des champs Nom et Prenom");
		Assert.assertTrue(facturePage.AffichageErrorChampsNomPrenom());
		
		
	}
	
	

}
