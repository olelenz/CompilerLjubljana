		LOC		Data_Segment
		GREG	0
		GREG	0
		GREG	@
		GREG	@
Buffer	BYTE	0,0
	LOC	Buffer+80
Arg	OCTA	Buffer,2
	OCTA	80
bufferOut		BYTE	0,0,0,0,0,0,0,0
_c		OCTA	1
		LOC		#100
Main	SET		$0,252
		ORH	$254,#5000
		OR	$253,$254,#f0
		PUSHJ	$4,_main
		LDO		$255,$254,0
		TRAP	0,Halt,0
_nothing	SWYM
		SETL	$0,8
		SUB	$254,$254,$0
		STO	$253,$254,0
		SUB	$254,$254,8
		GET	$253,rJ
		STO	$253,$254,0
		ADD	$254,$254,8
		ADD	$254,$254,$0
		OR	$253,$254,0
		SETL	$0,16
		SUB	$254,$254,$0
		JMP		L7
L7	SWYM
		SETL	$0,0
		JMP		L8
L8	SWYM
		STO		$0,$253,0
		SET	$254,$253
		SUB	$0,$254,0
		SUB	$0,$0,8
		LDO	$253,$0,0
		SUB	$0,$0,8
		LDO	$0,$0,0
		PUT	rJ,$0
		POP		0,0
_main	SWYM
		SETL	$0,8
		SUB	$254,$254,$0
		STO	$253,$254,0
		SUB	$254,$254,8
		GET	$253,rJ
		STO	$253,$254,0
		ADD	$254,$254,8
		ADD	$254,$254,$0
		OR	$253,$254,0
		SETL	$0,32
		SUB	$254,$254,$0
		JMP		L9
L9	SWYM
		LDA		$0,_c
		SET		$1,$0
		SETL	$0,101
		STO		$0,$1,0
L0	SWYM
		LDA		$0,_c
		LDO		$0,$0,0
		SET		$1,$0
		SETL	$0,113
		CMP		$0,$1,$0
		AND		$0,$0,1
		BNZ		$0,L1
L11	SWYM
		JMP		L2
L1	SWYM
		LDO		$0,$253,0
		STO		$0,$254,0
		PUSHJ	$4,_getChar
		LDO		$0,$254,0
		SET		$2,$0
		LDA		$0,_c
		SET		$1,$0
		SET		$0,$2
		STO		$0,$1,0
		LDO		$0,$253,0
		SET		$1,$0
		LDA		$0,_c
		LDO		$0,$0,0
		STO		$1,$254,0
		STO		$0,$254,8
		PUSHJ	$4,_putChar
		LDO		$0,$254,0
		JMP		L0
L2	SWYM
		LDO		$0,$253,0
		SET		$1,$0
		SETL	$0,10
		SET		$2,$0
		SETL	$0,256
		DIV		$0,$2,$0
		GET		$0,rR
		STO		$1,$254,0
		STO		$0,$254,8
		PUSHJ	$4,_putChar
		LDO		$0,$254,0
		SETL	$0,1
		JMP		L10
L10	SWYM
		STO		$0,$253,0
		SET	$254,$253
		SUB	$0,$254,0
		SUB	$0,$0,8
		LDO	$253,$0,0
		SUB	$0,$0,8
		LDO	$0,$0,0
		PUT	rJ,$0
		POP		0,0
_getChar	LDA	$255,Arg
		TRAP	0,Fgets,StdIn
		LDA	$0,Buffer
	SET	$1,0
		LDB	$1,$0,0
		STO	$1,$254,0
		POP	0,0
_putChar	LDO		$0,$254,8
		LDA		$255,bufferOut
		STB		$0,$255,0
		XOR		$0,$0,$0
		STB		$0,$255,1
		TRAP	0,Fputs,StdOut
		POP		0,0
_new		SWYM
		STO	$252,$254,0
		LDO	$0,$254,8
		MUL	$0,$0,8
		ADD	$252,$252,$0
		POP	0,0
_del		POP	0,0
