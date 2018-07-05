package com.specmate.migration.h2;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import org.osgi.service.log.LogService;

import com.specmate.common.SpecmateException;

public class ObjectToSQLMapper extends SQLMapper {

	public ObjectToSQLMapper(Connection connection, LogService logService, String packageName, String sourceVersion,
			String targetVersion) {
		super(connection, logService, packageName, sourceVersion, targetVersion);
	}

	public void newObject(String tableName) throws SpecmateException {
		String failmsg = "Migration: Could not add table " + tableName + ".";
		List<String> queries = new ArrayList<>();

		queries.add("CREATE TABLE " + tableName + "(" + "CDO_ID NUMBER NOT NULL, " + "CDO_VERSION NUMBER NOT NULL, "
				+ "CDO_CREATED NUMBER NOT NULL, " + "CDO_REVISED NUMBER NOT NULL, " + "CDO_RESOURCE NUMBER NOT NULL, "
				+ "CDO_CONTAINER NUMBER NOT NULL, " + "CDO_FEATURE NUMBER NOT NULL)");

		queries.add("CREATE UNIQUE INDEX " + SQLUtil.createRandomIdentifier("PRIMARY_KEY_" + tableName) + " ON "
				+ tableName + " (CDO_ID ASC, CDO_VERSION ASC)");

		queries.add("CREATE INDEX " + SQLUtil.createRandomIdentifier("INDEX_" + tableName) + " ON " + tableName
				+ " (CDO_REVISED ASC)");

		queries.add("ALTER TABLE " + tableName + " ADD CONSTRAINT "
				+ SQLUtil.createRandomIdentifier("CONSTRAINT_" + tableName) + " PRIMARY KEY (CDO_ID, CDO_VERSION)");

		queries.addAll(insertExternalObjectReference(tableName));
		SQLUtil.executeStatements(queries, connection, failmsg);
	}
}
