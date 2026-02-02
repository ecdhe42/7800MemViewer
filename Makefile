all: MemViewer.jar

MemViewer.jar: MemViewer.class
	cp json-simple-1.1.1.jar MemViewer.jar
	jar ufe MemViewer.jar MemViewer *.class

MemViewer.class: MemViewer.java
	javac -cp .:json-simple-1.1.1.jar MemViewer.java

run: MemViewer.class
	java -cp .:json-simple-1.1.1.jar MemViewer
