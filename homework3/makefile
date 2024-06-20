JC = javac
JFLAGS = -classpath .
JD = javadoc
JDFLAGS = -protected -splitindex -use -author -version -d ./javadoc
RM = rm
JR = java

CLASSES = \
		Inventory.java \
		Device.java \
		Main.java \
		SmartPhone.java \
		Laptop.java	\
		HeadPhone.java \
		Tv.java \
		Watch.java \
		ElectronicDevice.java \
		ErrorHandlingForInvalidInput.java

all : Main.class

run : 
	$(JR) Main

classes : $(CLASSES:.java=.class)

%.class : %.java
	$(JC) $(JFLAGS) $<

doc:
	$(JD) $(JDFLAGS) *.java 

clean:
	$(RM) *.class 

cleandoc:
	$(RM) -r ./javadoc