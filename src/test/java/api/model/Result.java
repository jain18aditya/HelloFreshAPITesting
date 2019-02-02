package api.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data

public class Result
{
	private String name;
	private String alpha2_code;
	private String alpha3_code;
}