.SILENT:

.PHONY: build
build:
	mvn clean install

.PHONY: clean
clean:
	mvn clean

.PHONY: run
run: build
	mvn exec:java