package com.example.tenancy;
import org.hibernate.engine.jdbc.connections.spi.AbstractMultiTenantConnectionProvider;
import org.hibernate.engine.jdbc.connections.spi.ConnectionProvider;

public class MultiTenantConnectionProvider extends AbstractMultiTenantConnectionProvider {

        private static final long serialVersionUID = -2822283226543856880L;

        public MultiTenantConnectionProvider(){
        }

        @Override
        protected ConnectionProvider getAnyConnectionProvider() {
                return ConnectionProviderUtil.getAdminConnectionProvider();
        }

        @Override
        protected ConnectionProvider selectConnectionProvider(String tenantId) {
                ConnectionProvider tenantDetail = ConnectionProviderUtil.getConnectionProviderForTenancyId(tenantId);
                return tenantDetail;
        }

}