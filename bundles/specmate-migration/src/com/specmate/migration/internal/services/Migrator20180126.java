package com.specmate.migration.internal.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.log.LogService;

import com.specmate.common.SpecmateException;
import com.specmate.migration.api.IMigrator;

@Component(property = "sourceVersion=20180126")
public class Migrator20180126 implements IMigrator {

	private static final String TABLE_EXTERNAL_REFS = "CDO_EXTERNAL_REFS";

	private LogService logService;

	@Override
	public String getSourceVersion() {
		return "20180126";
	}

	@Override
	public String getTargetVersion() {
		return "20180412";
	}

	@Override
	public void migrate(Connection connection) throws SpecmateException {
		// immitate broken migration
		logService.log(LogService.LOG_INFO, "Simulating broken migration.");
		throw new SpecmateException("Simulated Broken Migration");
		// updateExternalRefs(connection);

		// new attribute expected outcome
		// AttributeToSQLMapper expOutcomeAdded = new
		// AttributeToSQLMapper(connection, logService, "model/processes",
		// getSourceVersion(), getTargetVersion());
		// expOutcomeAdded.migrateNewStringAttribute("ProcessStep",
		// "expectedOutcome", "");

		// new object status
		// String objectName = "Status";
		// ObjectToSQLMapper oAdded = new ObjectToSQLMapper(connection,
		// logService, "model/administration",
		// getSourceVersion(), getTargetVersion());
		// oAdded.newObject(objectName);

		// new attribute value@Status
		// AttributeToSQLMapper valueAdded = new
		// AttributeToSQLMapper(connection, logService, "model/administration",
		// getSourceVersion(), getTargetVersion());
		// value is a reserved term, hence cdo will use the attribute name
		// "value0"
		// valueAdded.migrateNewStringAttribute(objectName, "value0", "");
	}

	private void updateExternalRefs(Connection connection) throws SpecmateException {
		PreparedStatement stmt;
		try {
			stmt = connection.prepareStatement("update " + TABLE_EXTERNAL_REFS
					+ " set URI=REGEXP_REPLACE(URI,'http://specmate.com/model/20180126/model',"
					+ "'http://specmate.com/20180412/model')");
			boolean result = stmt.execute();
			int update = stmt.getUpdateCount();
			stmt.close();
		} catch (SQLException e) {
			throw new SpecmateException("Migration: Could not update external references table.");
		}

	}

	@Reference
	public void setLogService(LogService logService) {
		this.logService = logService;
	}
}
