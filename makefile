#Les dossiers 
SOURCEDIR=src
CLASSDIR=classes
SOURCETESTDIR=srctest
CLASSTESTDIR=classestest
CLASSTESTPROFDIR=classestest2
LIBBIR=lib
DOCDIR=doc

# Outils java
JAVAC=javac
JAVA=java
JAVADOC=javadoc
JAR=jar
JFLAGS = -d $(CLASSDIR) -sourcepath $(SOURCEDIR)
JFLAGSTEST =  -sourcepath $(SOURCEDIR):$(SOURCETESTDIR) -classpath $(CLASSDIR):$(CLASSTESTDIR):$(JUNIT) -d $(CLASSTESTDIR)
#librairie
JUNIT=/usr/share/java/junit4.jar:/usr/share/java/hamcrest-all.jar

#Les fichiers java 
SOURCES_TEST := $(shell find $(SOURCETESTDIR) -name "*.java")
SOURCES := $(shell find $(SOURCEDIR) -name "*.java")

#La classe de test 
TEST_CLASS ?= fr.insarouen.iti.prog.aventure.AllTests
TEST_PROF_CLASS ?= fr.insarouen.iti.prog.aventure.AllTests

.PHONY: all test doc clean help comp comp-test test-prof

#Création de classes si il n'existe pas 
$(CLASSDIR) :
	mkdir -p $(CLASSDIR)

$(CLASSTESTDIR) : 
	mkdir -p $(CLASSTESTDIR)

all: comp comp-test

comp: $(CLASSDIR) 
	$(JAVAC) $(JFLAGS) $(SOURCES)

comp-test: $(CLASSTESTDIR) comp
	$(JAVAC) $(JFLAGSTEST) $(SOURCES_TEST)

test: comp-test
	@echo "Lancement du test JUnit : $(TEST_CLASS)"
	$(JAVA) -classpath $(CLASSTESTDIR):$(CLASSDIR):$(JUNIT) org.junit.runner.JUnitCore $(TEST_CLASS)

test-prof: comp
	@echo "Lancement des tests JUnit du prof : $(TEST_PROF_CLASS)"
	$(JAVA) -classpath $(CLASSTESTPROFDIR):$(CLASSDIR):$(JUNIT) org.junit.runner.JUnitCore $(TEST_PROF_CLASS)
doc : 
	@echo "Création de la javadoc"
	@test -d $(DOCDIR) || mkdir -p $(DOCDIR)
	$(JAVADOC) -d $(DOCDIR) -sourcepath $(SOURCEDIR) -subpackages fr

clean : 
	@echo "Nettoyage"
	rm -rf $(CLASSDIR)
	rm -rf $(CLASSTESTDIR)
	rm -rf $(DOCDIR)


help:
	@echo "Commandes disponibles :"
	@echo "  make              : Compile le projet et les tests"
	@echo "  make comp         : Compile uniquement le code source (src -> classes)"
	@echo "  make comp-test    : Compile le code source puis les tests (srctest -> classestest)"
	@echo "  make test         : Lance la classe de test par défaut defined dans TEST_CLASS"
	@echo "  make test TEST_CLASS=mon.package.MonTest : Lance une classe de test spécifique"
	@echo "  make test-prof    : Lance la classe de test du prof défini dans TEST_CLASS_PROF"
	@echo "  make test TEST_CLASS=mon.package.MonTest : Lance une classe de test spécifique du prof"
	@echo "  make doc          : Génère la Javadoc"
	@echo "  make clean        : Supprime les dossiers générés"
 
