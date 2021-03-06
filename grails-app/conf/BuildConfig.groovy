grails.project.class.dir = "target/classes"
grails.project.test.class.dir = "target/test-classes"
grails.project.test.reports.dir = "target/test-reports"
grails.project.target.level = 1.6
//grails.project.war.file = "target/${appName}-${appVersion}.war"

grails.project.dependency.resolution = {
    // inherit Grails" default dependencies
    inherits("global") {
        // uncomment to disable ehcache
        // excludes "ehcache"
    }
    log "warn" // log level of Ivy resolver, either "error", "warn", "info", "debug" or "verbose"
    repositories {
        inherits true
        grailsPlugins()
        grailsHome()
        grailsCentral()

        // from public Maven repositories
        mavenCentral()
        //mavenLocal()
		
	mavenRepo "http://www.broadleafcommerce.org/nexus/content/repositories/snapshots/"
        mavenRepo "http://www.terracotta.org/download/reflector/releases"		
        //mavenRepo "http://snapshots.repository.codehaus.org"
        //mavenRepo "http://repository.codehaus.org"
        //mavenRepo "http://download.java.net/maven/2/"
        //mavenRepo "http://repository.jboss.com/maven2/"
    }
    dependencies {
        // specify dependencies here under either "build", "compile", "runtime", "test" or "provided" scopes eg.
		
		def broadleafVersion = "1.7.0-SNAPSHOT"
		def springVersion = "3.1.0.RELEASE"
		
		def excludes = {
			excludes "slf4j-simple", "persistence-api", "commons-logging", "jcl-over-slf4j", "slf4j-api", "jta", "xmlbeans"
			excludes "spring-core", "spring-beans", "spring-aop", "spring-asm","spring-webmvc","spring-tx"
			excludes "spring-context", "spring-web", "spring-parent", "spring-jdbc"
			excludes "log4j", "slf4j-log4j12"
			excludes group:"org.grails", name:"grails-core"
			excludes group:"org.grails", name:"grails-gorm"
			excludes group:"org.grails", name:"grails-test"
			excludes group:"xml-apis", name:"xml-apis"
			excludes "ehcache-core"
			excludes "jsr173_api", "stax-api"
			//transitive = false
		}
		
		compile (
			[group: "org.broadleafcommerce", name: "broadleaf-common", version: "${broadleafVersion}"],
			[group: "org.broadleafcommerce", name: "broadleaf-admin-module", version: "${broadleafVersion}"], 
			[group: "org.broadleafcommerce", name: "broadleaf-profile", version: "${broadleafVersion}"],
			[group: "org.broadleafcommerce", name: "broadleaf-contentmanagement-module", version: "${broadleafVersion}"],
			[group: "org.broadleafcommerce", name: "broadleaf-open-admin-platform", version: "${broadleafVersion}"],
			[group: "org.hibernate", name: "hibernate-entitymanager", version: "3.6.8.Final"],
			[group: "org.apache.velocity", name: "velocity-tools", version: "2.0"],
			[group: "org.apache.velocity", name: "velocity", version: "1.6.2"],
			[group: "javax.mail", name: "mail", version: "1.4.1"],
			
			//Need Spring security. Is it better to use a manual dependency or a Grails plugin?
			//Let"s go with manual dependency for now...
			[group: "org.springframework.security", name: "spring-security-acl", version: "${springVersion}"],
			[group: "org.springframework.security", name: "spring-security-core", version: "${springVersion}"],
			[group: "org.springframework.security", name: "spring-security-web", version: "${springVersion}"],
			[group: "org.springframework.security", name: "spring-security-taglibs", version: "${springVersion}"],
			[group: "org.springframework.security", name: "spring-security-config", version: "${springVersion}"],
			
			excludes
			)  
		
        // runtime "mysql:mysql-connector-java:5.1.5"
    }

    plugins {
	compile ":hibernate-jpa-provider:1.0.0.M1"
	compile ":gorm-jpa:1.0.0.M1" 
		
        build(":release:2.0.0"){
            export = false
        }
    }
}
