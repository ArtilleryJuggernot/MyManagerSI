# Compiler settings
JAVAC = javac
JAVAFLAGS = -d bin -cp bin:lib/*  # Add all JAR dependencies from the lib directory

# Main class
MAIN_CLASS = com.hj.mymanagersi.mymanagersi.App

# Source files directory
SRC_DIR = ./src/main/java

# Source files
SOURCES = $(shell find src/main/java -name "*.java")

# Default target
all: compile

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
	@echo "  all       : Compile the project"
	@echo "  compile   : Compile Java source files"
	@echo "  run       : Run the application"
	@echo "  clean     : Remove compiled files"
	@echo "  help      : Display this help message"

