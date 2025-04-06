package RegressionPagesObjetModel;

import org.testng.Assert;
import org.testng.annotations.Test;

public class FirstClasse {
	
	@Test
	void Essaie() {
		System.out.println("Je teste TestNG...");
	}
	
	@Test
	void FailTest() {
		System.out.println("Ce test est un echec...");
		
		Assert.assertTrue(false);
	}

}
