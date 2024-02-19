# List of dependencies
DEPENDENCIES = antlr/antlr/2.7.7/antlr-2.7.7.jar \
               net/bytebuddy/byte-buddy/1.12.18/byte-buddy-1.12.18.jar \
               com/fasterxml/classmate/1.5.1/classmate-1.5.1.jar \
               com/sun/xml/fastinfoset/FastInfoset/1.2.15/FastInfoset-1.2.15.jar \
               org/hibernate/common/hibernate-commons-annotations/5.1.2.Final/hibernate-commons-annotations-5.1.2.Final.jar \
               org/hibernate/hibernate-core/5.6.14.Final/hibernate-core-5.6.14.Final.jar \
               org/hibernate/hibernate-entitymanager/5.6.14.Final/hibernate-entitymanager-5.6.14.Final.jar \
               com/sun/istack/istack-commons-runtime/3.0.7/istack-commons-runtime-3.0.7.jar \
               org/jboss/jandex/2.4.2.Final/jandex-2.4.2.Final.jar \
               javax/activation/javax.activation-api/1.2.0/javax.activation-api-1.2.0.jar \
               javax/persistence/javax.persistence-api/2.2/javax.persistence-api-2.2.jar \
               javax/xml/bind/jaxb-api/2.3.1/jaxb-api-2.3.1.jar \
               org/glassfish/jaxb/jaxb-runtime/2.3.1/jaxb-runtime-2.3.1.jar \
               org/jboss/logging/jboss-logging/3.4.3.Final/jboss-logging-3.4.3.Final.jar \
               org/jboss/spec/javax/transaction/jboss-transaction-api_1.2_spec/1.1.1.Final/jboss-transaction-api_1.2_spec-1.1.1.Final.jar \
               junit/junit/3.8.1/junit-3.8.1.jar \
               org/mariadb/jdbc/mariadb-java-client/2.7.4/mariadb-java-client-2.7.4.jar \
               org/jvnet/staxex/stax-ex/1.8/stax-ex-1.8.jar \
               org/glassfish/jaxb/txw2/2.3.1/txw2-2.3.1.jar

# Target directory for dependencies
LIB_DIR = ./lib

# Copy dependencies from Maven repository to the lib directory
copy_dependencies:
	@mkdir -p $(LIB_DIR)
	@for dep in $(DEPENDENCIES); do \
		cp ~/.m2/repository/$$dep $(LIB_DIR)/; \
	done

# Compiler settings
JAVAC = javac
JAVAFLAGS = -d bin -cp bin:lib/*

# Main class
MAIN_CLASS = com.hj.mymanagersi.mymanagersi.App

# Source files directory
SRC_DIR = ./src/main/java

# Source files
SOURCES = $(shell find src/main/java -name "*.java")

# Default target
all: copy_dependencies compile

# Compile Java source files
compile: $(SOURCES)
	$(JAVAC) $(JAVAFLAGS) $(SOURCES)

# Run the application
run:
	java -cp bin:lib/* $(MAIN_CLASS)

# Clean compiled files
clean:
	rm -rf bin/*

# Help target
help:
	@echo "Usage: make [target]"
	@echo "Targets:"
	@echo "  all             : Compile the project and copy dependencies"
	@echo "  copy_dependencies: Copy dependencies from Maven repository to the lib directory"
	@echo "  compile         : Compile Java source files"
	@echo "  run             : Run the application"
	@echo "  clean           : Remove compiled files"
	@echo "  help            : Display this help message"

