package com.elearning.rest1.resources;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.DiscoveryClient;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@Component
public class Rest1ServiceEurekaLinkCreator {

	private String rest1ServiceName = "e-learning-microservice-rest-1"; // TODO: load from property/yml file.

	private final DiscoveryClient discoveryClient;

	@Autowired
	public Rest1ServiceEurekaLinkCreator(DiscoveryClient discoveryClient) {
		this.discoveryClient = discoveryClient;
	}

	public String defaultServerUri() {
		return "blablabla defualt server uri"; // TODO: implement a real solution.
	}

	@HystrixCommand( fallbackMethod = "defaultServerUri" )
	public String getServerUri() {
		InstanceInfo instanceInfo = discoveryClient.getNextServerFromEureka(rest1ServiceName, false);
		URI baseUri = URI.create(instanceInfo.getHomePageUrl());

		return baseUri.toString();
	}
}
