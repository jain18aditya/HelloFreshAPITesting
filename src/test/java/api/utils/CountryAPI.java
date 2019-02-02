package api.utils;

import api.model.Country;
import api.model.RestResponse;
import api.model.Result;

public class CountryAPI extends Api
{	
	public CountryAPI(Transport transport, String hostname, int port)
	{
		super(transport, hostname, port);
		// TODO Auto-generated constructor stub
	}

	public Country getAllCountry(int expectedStatusCode) {
        return get(getBaseUrl() + "/all", Country.class, expectedStatusCode);
    }

/*	public String getSingleCountry(String country, int expectedStatusCode) {
		return get(getBaseUrl() + "/iso2code/"+country, String.class, expectedStatusCode);
    }
*/	
	public Country getSingleCountry(String country, int expectedStatusCode) {
		return get(getBaseUrl() + "/iso2code/"+country, Country.class, expectedStatusCode);
    }

	public RestResponse addCountry(Result requestBody, int statusCode) {
		return post(getBaseUrl() + "/addNew", requestBody, RestResponse.class, statusCode);
    }
}
