JC = javac
JFLAGS = -g
TARGET = Main
SOURCES = Main.java SocialNetwork.java Person.java
CLASSES = Main.class SocialNetwork.class Person.class SocialNetwork\$$Suggestion.class

# Default target: compile the program
default: compile

# Compile target: compile the Java source files
compile: $(SOURCES)
	$(JC) $(JFLAGS) $(SOURCES)

# Run target: run the compiled Main class
run: $(TARGET).class
	java $(TARGET)

# Clean target: remove all class files
clean:
	rm -f $(CLASSES)

# Documentation target: generate JavaDoc documentation
doc:
	javadoc -d doc $(SOURCES)

.PHONY: clean default compile run doc
