.SILENT:

.PHONY: build
build:
	mvn package

.PHONY: clean
clean:
	git clean -f

.PHONY: run
run: build
	java -cp target/calculator-1.0-SNAPSHOT.jar io.confluent.App
