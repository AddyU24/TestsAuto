package RegressionPagesObjetModel;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

// Cette page est dedié à la page d'accueil de rec1

public class HomeRecPage {
	
	
	private WebDriver driver;
	


    public HomeRecPage(WebDriver driver) {
        this.driver = driver;
    }
  //*[@id="sitemaplist-887f4a6e33"]/li[12]/a
    // Les diffrents elements sur la page d'accueil
    
    private By menuConnectToggle = By.id("componentId3-toggle");
    private By seConnecterBtn = By.id("escConnectBtnId");
    //private By factureLink = By.xpath("//a[normalize-space()='Ma Facture']");
    private By factureLink = By.linkText("Ma Facture");
    
    private By DansCeMagasin = By.xpath("//button[@id='dansCeMagasin']");
   // private By BouttonAccederPageMagasin = By.xpath("//div[@class='col-12 col-sm-6 col-md-12']//a[@class='u-btn u-btn--dark-primary']");
    private By BoutonAccederPageMagasin = By.xpath("//span[normalize-space()='Accéder à la page magasin']/ancestor::a");
    
    private By MriHeader = By.id("search-input");
    //private By ErrorMessageZeroResultat = By.xpath("")
    //private By noResultAlert = By.xpath("//div[contains(@class, 'u-notifications__infos') and contains(., 'Désolé')]");
    
    private By noResultAlert = By.xpath("//div[@role='alert' and contains(., 'Désolé')]");
 
    
    // ******************  Les différentes Méthodes   ****************************
    
    // Cette méthode permet de cliquer à des éléments
    public void Click(WebDriverWait wait,By locator) {
    	
    	wait.until(ExpectedConditions.elementToBeClickable(locator));
    	driver.findElement(locator).click();
    }
    
    //Cette fonction permet d'ecrire dans un Champs
    public void SendKey(WebDriverWait wait,By locator, String key) {
    	
    	WebElement input = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    	input.clear();
    	input.sendKeys(key);
    	//driver.findElement(locator).sendKeys(key);
    }

//
    /*
public void FaireUneRechercheNoResultatDansMRI(String texte) {
	
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(50));
	WebElement input = wait.until(ExpectedConditions.visibilityOfElementLocated(MriHeader));
	input.clear();
	input.sendKeys(texte);
	input.sendKeys(Keys.ENTER);
	
} */

public boolean AffichageDuMessageZeroResultat(String texte) {
	
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(50));
	
	try {
		
		WebElement input = wait.until(ExpectedConditions.visibilityOfElementLocated(MriHeader));
		input.clear();
		input.sendKeys(texte);
		System.out.println("URL avant recherche : " + driver.getCurrentUrl());

		input.sendKeys(Keys.ENTER);
		
		
		Actions actions = new Actions(driver);
		actions.keyDown(Keys.ENTER).keyUp(Keys.ENTER).perform();
      
        wait.until(ExpectedConditions.urlContains("/resultat"));
        System.out.println("URL après recherche : " + driver.getCurrentUrl());

		
		WebElement alert = wait.until(ExpectedConditions.visibilityOfElementLocated(noResultAlert));
		
		return alert.isDisplayed();
		
	}catch(TimeoutException e) {
		System.out.println("Le message d'erreur n'est pas affiché :" +e.getMessage() );
		return false;
	}
}
    
 	
	public void openLoginPage() {
        //driver.findElement(menuConnectToggle).click();
        //driver.findElement(seConnecterBtn).click();
    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(50));
        Click(wait, menuConnectToggle);
        Click(wait, seConnecterBtn);
    }
   
	
    // Cete methode renvoi la page de connexion afin de verifier si l'utilisateur est connecté
    public boolean isUserLoggedInByUrl() {
    	System.out.println(driver.getCurrentUrl());
        return driver.getCurrentUrl().contains(".connected");
    }
    
    // Cette methode permet d'acceder au formulaire nous contacter et compter le nombre de champs
    
    public MagasinThouarePage goToMagasinThouarePage() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));

        
        // Survoler l'onglet "Dans ce magasin"
        Actions actions = new Actions(driver);
        WebElement onglet = driver.findElement(DansCeMagasin);
        actions.moveToElement(onglet).perform();

        // Attendre que le bouton devienne cliquable après le hover
        wait.until(ExpectedConditions.elementToBeClickable(BoutonAccederPageMagasin));

        // Cliquer sur le bouton
        driver.findElement(BoutonAccederPageMagasin).click();

        System.out.println("Redirection vers la page Magasin ...");

        return new MagasinThouarePage(driver);
    }

    
    
    
    public FacturePage goToFacturePage() {
    	
    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    	wait.until(ExpectedConditions.visibilityOfElementLocated(factureLink));
        driver.findElement(factureLink).click();
        return new FacturePage(driver);
    }

}
