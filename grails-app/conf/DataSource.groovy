
	/*mongo {
		host = '127.0.0.1'
		port = 27017
		databaseName = 'trac'
	}*/
	environments {
		development {	
			dataSource {
				pooled = true
				dbCreate = "update" // empty for database-migration plugin

				url = "jdbc:mysql://127.0.0.1:3306/tracFormation?autoReconnect=true&useSSL=false"

				//url = "jdbc:mysql://localhost/tractestbench?autoReconnect=true&useSSL=false"
				driverClassName = "com.mysql.jdbc.Driver"
				dialect = "org.hibernate.dialect.MySQL5InnoDBDialect"
				username = "root"
				password = ""
			
			}
			hibernate {
				show_sql=true
				//cache.queries = true
				//cache.use_second_level_cache = true
				//cache.use_query_cache = true
				//cache.provider_class =  'net.sf.ehcache.hibernate.SingletonEhCacheProvider'
			}
		}
		
		test {
			dataSource {
				pooled = true
				dbCreate = "update"  // empty for database-migration plugin
				url = "jdbc:mysql://localhost/tracFormation?autoReconnect=true&useSSL=false"

				driverClassName = "com.mysql.jdbc.Driver"
				username = "root"
				password = ""
				
				properties {
					// See http://grails.org/doc/latest/guide/conf.html#dataSource for documentation
					jmxEnabled = true
					initialSize = 5
					maxActive = 50
					minIdle = 5
					maxIdle = 25
					maxWait = 10000
					maxAge = 10 * 60000
					timeBetweenEvictionRunsMillis = 5000
					minEvictableIdleTimeMillis = 60000
					validationQuery = "SELECT 1"
					validationQueryTimeout = 3
					validationInterval = 15000
					testOnBorrow = true
					testWhileIdle = true
					testOnReturn = false
					jdbcInterceptors = "ConnectionState"
					defaultTransactionIsolation = java.sql.Connection.TRANSACTION_READ_COMMITTED
				}
			}
		}
		
		production {
			dataSource {
				pooled = true
				dbCreate = ""  // empty for database-migration plugin
				url = "jdbc:mysql://localhost/tracFormation3"

				driverClassName = "com.mysql.jdbc.Driver"
				username = "root"
				password = "root"
			}
		}
		 
		
	}
	
	


hibernate {
	cache.use_second_level_cache = true
	cache.use_query_cache = false
	//    cache.region.factory_class = 'org.hibernate.cache.SingletonEhCacheRegionFactory' // Hibernate 3
	cache.region.factory_class = 'org.hibernate.cache.ehcache.SingletonEhCacheRegionFactory' // Hibernate 4
	singleSession = true // configure OSIV singleSession mode
	flush.mode = 'manual' // OSIV session flush mode outside of transactional context
	show_sql=false
}