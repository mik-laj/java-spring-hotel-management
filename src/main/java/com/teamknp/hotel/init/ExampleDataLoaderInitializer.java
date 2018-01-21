package com.teamknp.hotel.init;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class ExampleDataLoaderInitializer implements InitializingBean {
    @Autowired
    UserInitializer userInitializer;

    @Autowired
    WarehouseInitializer warehouseInitializer;

    @Autowired
    RepositoryMockupInitializer repositoryMockupInitializer;

    @Autowired
    SaleInitializer saleInitializer;

    public void afterPropertiesSet() throws Exception {
        userInitializer.load();
        repositoryMockupInitializer.load();;
        warehouseInitializer.load();
        saleInitializer.load();
    }
}
