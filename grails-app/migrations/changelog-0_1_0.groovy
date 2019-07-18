databaseChangeLog = {

	changeSet(author: "if-dev1 (generated)", id: "1488203140324-1") {
		createTable(tableName: "authentication_result") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "authenticatioPK")
			}

			column(name: "start", type: "datetime") {
				constraints(nullable: "false")
			}

			column(name: "type", type: "varchar(30)") {
				constraints(nullable: "false")
			}

			column(name: "user_id", type: "bigint") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "if-dev1 (generated)", id: "1488203140324-2") {
		createTable(tableName: "obsel_trust") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "obsel_trustPK")
			}

			column(name: "authentication_result_id", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "begin", type: "datetime") {
				constraints(nullable: "false")
			}

			column(name: "distance", type: "float") {
				constraints(nullable: "false")
			}

			column(name: "end", type: "datetime") {
				constraints(nullable: "false")
			}

			column(name: "id_action", type: "varchar(170)") {
				constraints(nullable: "false")
			}

			column(name: "trust", type: "float") {
				constraints(nullable: "false")
			}

			column(name: "type_action", type: "varchar(50)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "if-dev1 (generated)", id: "1488203140324-3") {
		createTable(tableName: "role") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "rolePK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "authority", type: "varchar(255)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "if-dev1 (generated)", id: "1488203140324-4") {
		createTable(tableName: "user") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "userPK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "account_expired", type: "bit") {
				constraints(nullable: "false")
			}

			column(name: "account_locked", type: "bit") {
				constraints(nullable: "false")
			}

			column(name: "email", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "enabled", type: "bit") {
				constraints(nullable: "false")
			}

			column(name: "firstname", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "iduser", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "lastname", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "model_trace_name", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "password", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "password_expired", type: "bit") {
				constraints(nullable: "false")
			}

			column(name: "primary_trace_name", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "profil_trace_name", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "signature_trace", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "userauthentification_id", type: "bigint")
		}
	}

	changeSet(author: "if-dev1 (generated)", id: "1488203140324-5") {
		createTable(tableName: "user_authentification") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "user_authentiPK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "last_obsel_profil", type: "integer") {
				constraints(nullable: "false")
			}

			column(name: "trust", type: "float") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "if-dev1 (generated)", id: "1488203140324-6") {
		createTable(tableName: "user_role") {
			column(name: "user_id", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "role_id", type: "bigint") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "if-dev1 (generated)", id: "1488203140324-7") {
		addPrimaryKey(columnNames: "user_id, role_id", constraintName: "user_rolePK", tableName: "user_role")
	}

	changeSet(author: "if-dev1 (generated)", id: "1488203140324-13") {
		createIndex(indexName: "FK_a4efj3yd5wd16rxhdu4f5y62m", tableName: "authentication_result") {
			column(name: "user_id")
		}
	}

	changeSet(author: "if-dev1 (generated)", id: "1488203140324-14") {
		createIndex(indexName: "FK_8e81j51nkcjy2ch2ai4uwyr2m", tableName: "obsel_trust") {
			column(name: "authentication_result_id")
		}
	}

	changeSet(author: "if-dev1 (generated)", id: "1488203140324-15") {
		createIndex(indexName: "authority_uniq_1488203140276", tableName: "role", unique: "true") {
			column(name: "authority")
		}
	}

	changeSet(author: "if-dev1 (generated)", id: "1488203140324-16") {
		createIndex(indexName: "FK_atvp2qp6nu9fyhakq6jnhyrvl", tableName: "user") {
			column(name: "userauthentification_id")
		}
	}

	changeSet(author: "if-dev1 (generated)", id: "1488203140324-17") {
		createIndex(indexName: "email_uniq_1488203140280", tableName: "user", unique: "true") {
			column(name: "email")
		}
	}

	changeSet(author: "if-dev1 (generated)", id: "1488203140324-18") {
		createIndex(indexName: "FK_apcc8lxk2xnug8377fatvbn04", tableName: "user_role") {
			column(name: "user_id")
		}
	}

	changeSet(author: "if-dev1 (generated)", id: "1488203140324-19") {
		createIndex(indexName: "FK_it77eq964jhfqtu54081ebtio", tableName: "user_role") {
			column(name: "role_id")
		}
	}

	changeSet(author: "if-dev1 (generated)", id: "1488203140324-8") {
		addForeignKeyConstraint(baseColumnNames: "user_id", baseTableName: "authentication_result", constraintName: "FK_a4efj3yd5wd16rxhdu4f5y62m", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "user", referencesUniqueColumn: "false")
	}

	changeSet(author: "if-dev1 (generated)", id: "1488203140324-9") {
		addForeignKeyConstraint(baseColumnNames: "authentication_result_id", baseTableName: "obsel_trust", constraintName: "FK_8e81j51nkcjy2ch2ai4uwyr2m", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "authentication_result", referencesUniqueColumn: "false")
	}

	changeSet(author: "if-dev1 (generated)", id: "1488203140324-10") {
		addForeignKeyConstraint(baseColumnNames: "userauthentification_id", baseTableName: "user", constraintName: "FK_atvp2qp6nu9fyhakq6jnhyrvl", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "user_authentification", referencesUniqueColumn: "false")
	}

	changeSet(author: "if-dev1 (generated)", id: "1488203140324-11") {
		addForeignKeyConstraint(baseColumnNames: "role_id", baseTableName: "user_role", constraintName: "FK_it77eq964jhfqtu54081ebtio", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "role", referencesUniqueColumn: "false")
	}

	changeSet(author: "if-dev1 (generated)", id: "1488203140324-12") {
		addForeignKeyConstraint(baseColumnNames: "user_id", baseTableName: "user_role", constraintName: "FK_apcc8lxk2xnug8377fatvbn04", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "user", referencesUniqueColumn: "false")
	}

	changeSet(author: "if-dev1 (generated)", id: "1488210572686-1") {
		modifyDataType(columnName: "begin", newDataType: "integer", tableName: "obsel_trust")
	}

	changeSet(author: "if-dev1 (generated)", id: "1488210572686-2") {
		modifyDataType(columnName: "end", newDataType: "integer", tableName: "obsel_trust")
	}

	changeSet(author: "if-dev1 (generated)", id: "1489094518902-1") {
		modifyDataType(columnName: "begin", newDataType: "bigint", tableName: "obsel_trust")
	}

	changeSet(author: "if-dev1 (generated)", id: "1489094518902-2") {
		addNotNullConstraint(columnDataType: "bigint", columnName: "begin", tableName: "obsel_trust")
	}

	changeSet(author: "if-dev1 (generated)", id: "1489094518902-3") {
		modifyDataType(columnName: "end", newDataType: "bigint", tableName: "obsel_trust")
	}

	changeSet(author: "if-dev1 (generated)", id: "1489094518902-4") {
		addNotNullConstraint(columnDataType: "bigint", columnName: "end", tableName: "obsel_trust")
	}

	changeSet(author: "Ilias (generated)", id: "1489077971500-1") {
		addColumn(tableName: "authentication_result") {
			column(name: "user_profile_id", type: "bigint") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "Ilias (generated)", id: "1489077971500-2") {
		addColumn(tableName: "authentication_result") {
			column(name: "user_signature_id", type: "bigint") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "Ilias (generated)", id: "1489077971500-6") {
		dropForeignKeyConstraint(constraintName: "FK_a4efj3yd5wd16rxhdu4f5y62m", baseTableName: "authentication_result")
	}

	changeSet(author: "Ilias (generated)", id: "1489077971500-13") {
		dropIndex(indexName: "FK_a4efj3yd5wd16rxhdu4f5y62m", tableName: "authentication_result")
	}

	changeSet(author: "Ilias (generated)", id: "1489077971500-14") {
		createIndex(indexName: "FK_873cb8joiegt64w71l15c2hj5", tableName: "authentication_result") {
			column(name: "user_profile_id")
		}
	}

	changeSet(author: "Ilias (generated)", id: "1489077971500-15") {
		createIndex(indexName: "FK_e1960oxvsgpt1hxxh5p4ftgbu", tableName: "authentication_result") {
			column(name: "user_signature_id")
		}
	}

	changeSet(author: "Ilias (generated)", id: "1489077971500-16") {
		dropColumn(columnName: "user_id", tableName: "authentication_result")
	}

	changeSet(author: "Ilias (generated)", id: "1489077971500-7") {
		addForeignKeyConstraint(baseColumnNames: "user_profile_id", baseTableName: "authentication_result", constraintName: "FK_873cb8joiegt64w71l15c2hj5", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "user", referencesUniqueColumn: "false")
	}

	changeSet(author: "Ilias (generated)", id: "1489077971500-8") {
		addForeignKeyConstraint(baseColumnNames: "user_signature_id", baseTableName: "authentication_result", constraintName: "FK_e1960oxvsgpt1hxxh5p4ftgbu", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "user", referencesUniqueColumn: "false")
	}

	changeSet(author: "Ilias (generated)", id: "1491320094356-1") {
		addColumn(tableName: "authentication_result") {
			column(name: "distance_mean", type: "float") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "Ilias (generated)", id: "1491320094356-2") {
		addColumn(tableName: "authentication_result") {
			column(name: "distance_median", type: "float") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "Ilias (generated)", id: "1491320094356-3") {
		addColumn(tableName: "authentication_result") {
			column(name: "distance_standard_deviation", type: "float") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "Ilias (generated)", id: "1491320094356-4") {
		addColumn(tableName: "authentication_result") {
			column(name: "distance_variance", type: "float") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "Ilias (generated)", id: "1491386174485-1") {
		addColumn(tableName: "authentication_result") {
			column(name: "anga", type: "integer") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "Ilias (generated)", id: "1491386174485-2") {
		addColumn(tableName: "authentication_result") {
			column(name: "ania", type: "float") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "Ilias (generated)", id: "1491386174485-3") {
		addColumn(tableName: "authentication_result") {
			column(name: "trust_treshold", type: "float") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "Ilias", id:"add default value ania - 1") {
		addDefaultValue(tableName: "authentication_result", columnName: "ania", defaultValue: 0f)
	}

	changeSet(author: "Ilias", id:"add default value anga - 1") {
		addDefaultValue(tableName: "authentication_result", columnName: "anga", defaultValue: 0)
	}

	changeSet(author: "Ilias", id:"add default value trust_treshold - 1") {
		addDefaultValue(tableName: "authentication_result", columnName: "trust_treshold", defaultValue: 0f)
	}

	changeSet(author: "Ilias", id:"add default value distance_mean - 1") {
		addDefaultValue(tableName: "authentication_result", columnName: "distance_mean", defaultValue: 0f)
	}

	changeSet(author: "Ilias", id:"add default value distance_median - 1") {
		addDefaultValue(tableName: "authentication_result", columnName: "distance_median", defaultValue: 0f)
	}

	changeSet(author: "Ilias", id:"add default value distance_variance - 1") {
		addDefaultValue(tableName: "authentication_result", columnName: "distance_variance", defaultValue: 0f)
	}

	changeSet(author: "Ilias", id:"add default value distance_standard_deviation - 1") {
		addDefaultValue(tableName: "authentication_result", columnName: "distance_standard_deviation", defaultValue: 0f)
	}
	changeSet(author: "fatma (generated)", id: "1504002652139-1") {
		addColumn(tableName: "user_authentification") {
			column(name: "last_obsel_profil_dynamic", type: "integer") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "fatma (generated)", id: "1504002652139-2") {
		addColumn(tableName: "user_authentification") {
			column(name: "trust_dynamic", type: "float") {
				constraints(nullable: "false")
			}
		}
	}
}
