//$Id$
package com.example.tenancy;

import org.hibernate.context.spi.CurrentTenantIdentifierResolver;

public class SchemaResolver implements CurrentTenantIdentifierResolver {

	@Override
	public String resolveCurrentTenantIdentifier() {
		Object tenancy_id = "DBAccessThreadlocal.getTenancyId()";
		return tenancy_id!=null ? tenancy_id.toString() : null;
	}

	@Override
	public boolean validateExistingCurrentSessions() {
		return false;
	}

}
