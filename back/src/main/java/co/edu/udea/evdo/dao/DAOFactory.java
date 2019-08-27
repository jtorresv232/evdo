/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.udea.evdo.dao;

/**
 *
 * @author Jonathan
 */
public abstract class DAOFactory {
    private static DAOFactory factory;
    
    public static DAOFactory getDAOFactory(FactoryType factoryType) {

		switch(factoryType) {
			case HIBERNATE:
				break;
			case POOL:
				factory = new POOLDAOFactory();
				break;
		}

		return factory;
	}

	public enum FactoryType {
		HIBERNATE,
		POOL
	}

}


