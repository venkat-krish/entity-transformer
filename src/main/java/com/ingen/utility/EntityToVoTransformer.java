package com.ingen.utility;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.DateConverter;

/**
 * Utility component for transforming entity object to value object
 * 
 * 
 * @author Venkat-Krish
 * @version 1.0.0
 * @since Jul 2014
 * 
 * */
public class EntityToVoTransformer<E, D> {

	private Class<E> entity;
	private Class<D> voClazz;
	
	/**
	 * Constructor to initialize the properties 
	 * 
	 * @param Class<E> entity 
	 * @param Class<D> voClazz
	 * */
	public EntityToVoTransformer(Class<E> entityClazz, Class<D> valueObjClazz){
		
		this.entity = entityClazz;
		
		this.voClazz = valueObjClazz;
		
		// Setting a default converter for date.
		DateConverter dateConverter = new DateConverter(new Date());
		dateConverter.setPattern("MM/dd/yyyy");
		
		ConvertUtils.register(dateConverter, Date.class);
	}
	
	/**
	 * Copies the property from Value object to 
	 * Entity object.
	 * 
	 * @param D value object
	 * @return Entity object 
	 * */
	public E toEntity(D valueObject) {
		E entity = null;

		try {
			entity = this.entity.newInstance();

			copySrcToDestProperties(valueObject, entity);

		} catch (Exception ie) {
			ie.printStackTrace();
		}

		return entity;
	}

	/**
	 * Copies the property values of entity to 
	 * value object.
	 * 
	 * @param E entity object
	 * @return Object value 
	 * */
	public D toValueObject(E entity) {
		// Declaration of value object
		D valueObjIns = null;
		
		try {
			// Initializing 
			valueObjIns = voClazz.newInstance();
			
			copySrcToDestProperties(entity, valueObjIns);
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return valueObjIns;

	}
	
	/**
	 * Copies the properties from Entity object to Value object.
	 * 
	 * */
	public void copySrcToDestProperties(Object srcObj,
			Object destObj) throws IllegalAccessException,
			InvocationTargetException, InstantiationException {

		// Get the list of fields from source objects
		Field[] srcObjFields = srcObj.getClass()
				.getDeclaredFields();

		try{
			// Iteration of entity properties
			for (Field entityField : srcObjFields) {
				//
				// Checks whether the entity field is an object and is of type
				// BaseEntity class.
				//				
				try{
					if (!voClazz.isAssignableFrom(entityField.getType())
							&& !Modifier.isFinal(entityField.getModifiers())
							&& !Modifier.isStatic(entityField.getModifiers())) {
						
						BeanUtils.setProperty(destObj,	entityField.getName(),	BeanUtils.getProperty(srcObj,	entityField.getName()));
					}
				}				
				catch(NoSuchMethodException nsm){
					System.out.println("No such method exception occured : "+nsm.getMessage());
				}
				
			}
		}
		catch(SecurityException se){
			System.out.println("Security exception occured : "+se.getMessage());
		}	
		
	}
	
	
	/**
	 * It evaluates and iterates the collection parameter and transform the object 
	 * item to entity object. 
	 * 
	 * @return Collection<? extends BaseEntity>-Any object that extends BaseEntity.
	 * 		
	 * */
	public Collection<? extends E> collVoToEntity(Collection<? extends D> objColl){
		// Output collection with entities
		Collection<E> entityColl = null;
		E entityObj = null;
		
		//
		// If the collection parameter is not null and empty
		//
		if(objColl != null && !objColl.isEmpty()){
			// Check for instance of Set or List class
			if(objColl instanceof Set){
				entityColl = new HashSet<E>();
			}
			else if(objColl instanceof List){
				entityColl = new ArrayList<E>();
			}
			
			for(D valueObj : objColl){
				// Transform the DTO object to entity
				entityObj = (E) toEntity(valueObj);
				
				entityColl.add(entityObj);
			}
					
		}
				
		return entityColl;
	}
	
	
	/**
	 * It evaluates and iterates the collection parameter and transform the object 
	 * item to entity object. 
	 * 
	 * @return Collection<? extends ValueObject>-Any object of type Value Object.
	 * 		
	 * */
	public Collection<? extends D> collEntityToVo(Collection<? extends E> entityColl){
		// Output collection with entities
		Collection<D> voColl = null;
		D voObj = null;
		
		//
		// If the collection parameter is not null and empty
		//
		if(entityColl != null && !entityColl.isEmpty()){
			// Check for instance of Set or List class
			if(entityColl instanceof Set){
				voColl = new HashSet<D>();
			}
			else if(entityColl instanceof List){
				voColl = new ArrayList<D>();
			}
			
			for(E entity : entityColl){
				// Transform the DTO object to entity
				voObj = (D) toValueObject(entity);
				
				voColl.add(voObj);
			}
					
		}
				
		return voColl;
	}
	
}
