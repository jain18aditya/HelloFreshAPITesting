package api.steps;

import org.apache.log4j.Logger;
import org.junit.Assert;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.type.TypeFactory;

import api.model.Country;
import api.model.RestResponse;
import api.model.Result;
import api.utils.Api;
import api.utils.Api.Transport;
import api.utils.Constant;
import api.utils.CountryAPI;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import com.fasterxml.jackson.databind.ObjectMapper;

public class APITest {
	Logger log = Logger.getLogger(Api.class);
	CountryAPI countryAPI = new CountryAPI(Transport.HTTP, Constant.SERVER, Constant.PORT);
	Country country = new Country();
	RestResponse response = new RestResponse();
	Result[] resultList;
	String singleCountry;
	ObjectMapper mapper = new ObjectMapper();
	Result result = new Result();

	@Given("^user calls get all country API and expect status code (\\d+)$")
	public void getAllCountry(int statusCode) throws Throwable {
		resultList = mapper.readValue(mapper.writeValueAsString(countryAPI.getAllCountry(statusCode).getRestResponse().getResult()), TypeFactory.defaultInstance().constructArrayType(Result.class));
	}

	@Then("^verify country \"([^\"]*)\" present is according to status \"([^\"]*)\"$")
	public void verifyCountryStatusInList(String country, String status) throws Throwable {
		String flag = "false";
		for(int i=0; i<resultList.length;i++) {
			if (resultList[i].getAlpha2_code().equals(country)) {
				flag = "true";
				break;
			}
		}
		Assert.assertEquals("Contry present status is not as expected", status, flag);
	}

	@Given("^user calls get single country api with \"([^\"]*)\" and expect status code (\\d+)$")
	public void getSingleCountry(String country, int statusCode) throws Throwable {
		response = countryAPI.getSingleCountry(country, statusCode).getRestResponse();
		result = mapper.readValue(mapper.writeValueAsString(response.getResult()), new TypeReference<Result>(){});
	}

	@Then("^verify country name as \"([^\"]*)\" information is displayed$")
	public void verifyIndividualCountryName(String country) throws Throwable {
		System.out.println(result.getName());
		Assert.assertEquals("Invalid country name present", country, result.getName());
	}

	@Then("^verify correct message \"([^\"]*)\" is displayed$")
	public void verifyMessage(String message) throws Throwable {
		Assert.assertEquals("Invalid message displayed", true, response.getMessages().get(0).contains(message));
	}

	@Given("^user calls add country api with \"([^\"]*)\", \"([^\"]*)\" and \"([^\"]*)\" and expect status code (\\d+)$")
	public void addCountry(String country, String alpha2_code, String alpha3_code, int statusCode) throws Throwable {
		Result addReq = new Result();
		addReq.setName(country);
		addReq.setAlpha2_code(alpha2_code);
		addReq.setAlpha3_code(alpha3_code);
		response = countryAPI.addCountry(addReq, statusCode);
	}
}
