package api.hooks;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import org.apache.log4j.Logger;

public class Hooks {
	Logger log = Logger.getLogger(Hooks.class);

	@Before
	public void beforeScenario(Scenario scene) 
	{
		log.info( "************Scenario "+scene.getName()+ " is started************");
	}
	
	@After
	public void afterScenario(Scenario scene) 
	{
		log.info("Scenario "+scene.getName()+" is: "+scene.getStatus().toUpperCase());
	}
}
