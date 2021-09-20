package com.gowtham.ecommerce.config;


import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.metamodel.EntityType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.http.HttpMethod;

import com.gowtham.ecommerce.entity.Country;
import com.gowtham.ecommerce.entity.Product;
import com.gowtham.ecommerce.entity.ProductCategory;
import com.gowtham.ecommerce.entity.State;

@Configuration
public class MyRestDataConfig implements RepositoryRestConfigurer{
	
	//Exposing the Entity Id's
	private EntityManager entityManager;
	
	@Autowired
	public MyRestDataConfig(EntityManager theEntityManager)
	{
		entityManager = theEntityManager;
	}

	@Override
	public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
		
		HttpMethod[] unSupportedmethods = {HttpMethod.DELETE,HttpMethod.PUT,HttpMethod.POST};
		
		//for product
		disableHTTPMethods(Product.class, config, unSupportedmethods);
		//for productcategory
		disableHTTPMethods(ProductCategory.class, config, unSupportedmethods);
		//country
		disableHTTPMethods(Country.class, config, unSupportedmethods);
		//state
		disableHTTPMethods(State.class, config, unSupportedmethods);
		
		exposeIds(config);
	}

	private void disableHTTPMethods(Class theClass, RepositoryRestConfiguration config, HttpMethod[] unSupportedmethods) {
		config.getExposureConfiguration()
		.forDomainType(theClass) 
		.withItemExposure((metadata, httmethods) -> httmethods.disable(unSupportedmethods))
		.withCollectionExposure((metadata, httmethods) -> httmethods.disable(unSupportedmethods));
	}

	private void exposeIds(RepositoryRestConfiguration config) {
		//getting the list aof all entities
		Set<EntityType<?>> entities = entityManager.getMetamodel().getEntities(); 
		
		//create a list to store all the entities class type
		List<Class> entityClasses = new ArrayList<>();
		
		for(EntityType tempEntityType : entities)
		{
			entityClasses.add(tempEntityType.getJavaType());
		}
		
		
		//convert the list to array to pass it to config.expostId's
		Class[] domainTypes = entityClasses.toArray(new Class[0]);
		config.exposeIdsFor(domainTypes);
	}
	

}
