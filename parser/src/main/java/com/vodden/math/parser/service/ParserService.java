package com.vodden.math.parser.service;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.vodden.math.parser.domain.ParseRequest;
import com.vodden.math.parser.domain.ParseResponse;

@Path("/parser/")
public interface ParserService {
	
	/**
	 * Returns the result of a mathematical expression which is provided in the string.
	 * The present API only supports integer literals.
	 * 
	 * @param 	expression	a mathematical expression containing only literal integers
	 * @return 	the result of the calculation represented by the expression
	 */
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public ParseResponse parse(ParseRequest parseRequest);
	
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String happyBeep();
}
