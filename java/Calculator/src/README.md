# Calculator Project

_Calculator.java - This is the main entry point for the program. 

PushDownAutomata - This class models the PDA that parses the input. The grammar that describes this is as follows:

					<ParenCalc> 		::=	(<Calculation>) | <Calculation>
					<Calculation> 	::=	<ParenCalc>( )<Operator>( )<ParenCalc>|<Number>	
					<Operator> 		::=	+|-|/|*
					<Number>			::=	<Integer>(.<Digits>)
					<Integer>		::= (-)<PosInteger>	
					<PosInteger>		::= 0 | <NonZeroDigit> | <NonZeroDigit><Digits>	
					<Digits>			::= <Digit>(<Digits>)
					<Digit>			::=	0|1|2|..|9
					<NonZeroDigit	>	::=	1|2|3|..|9
					