JAVA            = java --enable-preview
ANTLRDIR        = ../lib/antlr-4.13.1-complete.jar
XSLTPROC	= xsltproc
RM              = 'rm' -fr

PHASE		= all
LOG		= all
NUMBER = 2
OUTNAME = out

.PHONY	: %
%	: %.p23
	$(RM) -fr $@.*.xml $@.*.html
	if $(JAVA) -cp ../bin:../src:$(ANTLRDIR) prev23.Compiler --xsl=../lib/xsl/ --logged-phase=$(LOG) --target-phase=$(PHASE) --num-regs=$(NUMBER) --dst-file-name=$(OUTNAME) $< ; then for XML in `'ls' -1 $@.*.xml` ; do $(XSLTPROC) $$XML -o `basename $$XML xml`html; done ; fi

.PHONY  : clean
clean   :
	$(RM) *~
	$(RM) *xml *html

