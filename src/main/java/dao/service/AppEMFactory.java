package dao.service;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Created by alex on 7/30/15.
 * This class creates Entity Manager Factory as singleton Object
 */
public class AppEMFactory {

    private static AppEMFactory instance;
    private EntityManagerFactory emf;

    private AppEMFactory() {
        emf = Persistence.createEntityManagerFactory("HiberanteMySQL");
    }

    public static EntityManagerFactory getAppEMFactory() {
        if (instance != null) {
            return instance.emf;
        }

        instance = new AppEMFactory();
        return instance.emf;
    }
}
