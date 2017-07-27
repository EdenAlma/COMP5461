/**
 * Class BlockStack
 * Implements character block stack and operations upon it.
 *
 * $Revision: 1.4 $
 * $Last Revision Date: 2017/02/08 $
 *
 * @author Serguei A. Mokhov, mokhov@cs.concordia.ca;
 * Inspired by an earlier code by Prof. D. Probst

 */
class BlockStack
{
	/**
	 * # of letters in the English alphabet + 2
	 */

	private static final int MAX_SIZE = 28;

	/**
	 * Default stack size
	 */
	private static final int DEFAULT_SIZE = 6;

	/**
	 * Current size of the stack
	 */
	private int iSize = DEFAULT_SIZE;

	/**
	 * Current top of the stack
	 */
	private int iTop  = 3;

	/**
	 * stack[0:5] with four defined values
	 */
	private char acStack[] = new char[] {'a', 'b', 'c', 'd', '$', '$'};

	private int accessStackCounter = 0;


	BlockStackFullException full = new BlockStackFullException();
	BlockStackEmptyException empty = new BlockStackEmptyException();
	BlockStackOutOfBoundsException out = new BlockStackOutOfBoundsException();


	/**
	 * Default constructor
	 */
	public BlockStack()
	{
	}



	/**
	 * Supplied size
	 */
	public BlockStack(final int piSize)
	{


		if(piSize != DEFAULT_SIZE)
		{
			this.acStack = new char[piSize];

			// Fill in with letters of the alphabet and keep
			// 2 free blocks
			for(int i = 0; i < piSize - 2; i++)
				this.acStack[i] = (char)('a' + i);

			this.acStack[piSize - 2] = this.acStack[piSize - 1] = '$';

			this.iTop = piSize - 3;
			this.iSize = piSize;
		}
	}

	/**
	 * Picks a value from the top without modifying the stack
	 * @return top element of the stack, char
	 */
	public char pick()
	{
		++accessStackCounter;
		return this.acStack[this.iTop];
	}

	/**
	 * Returns arbitrary value from the stack array
	 * @return the element, char
	 */
	public char getAt(final int piPosition) throws BlockStackOutOfBoundsException 
	{
		++accessStackCounter;

		if(piPosition < 0 || piPosition >= acStack.length)
			throw out;

		return this.acStack[piPosition];
	}

	/**
	 * Standard push operation
	 */
	public void push(final char pcBlock) throws BlockStackFullException 
	{
		++accessStackCounter;

		if(iTop==-1)
			this.acStack[0] = 'a';
		else if (iTop >= acStack.length)
			throw full;
		else 
			this.acStack[++this.iTop] = pcBlock;
	}

	/**
	 * Standard pop operation
	 * @return ex-top element of the stack, char
	 */
	public char pop() throws BlockStackEmptyException  
	{
		++accessStackCounter;

		if(iTop == -1)throw empty;

		char cBlock = this.acStack[this.iTop];
		this.acStack[this.iTop--] = '$'; // Leave prev. value undefined
		return cBlock;
	}

	public int getTop() {
		// TODO Auto-generated method stub
		return iTop;
	}

	public int getSize() {
		// TODO Auto-generated method stub
		return iSize;
	}

	public boolean isEmpty() {
		return (this.iTop == -1);

	}

	public int getAccessCounter() {
		// TODO Auto-generated method stub
		return accessStackCounter;
	}

	public int getiSize() {
		return iSize;
	}

	public void setiSize(int iSize) {
		this.iSize = iSize;
	}

	public int getiTop() {
		return iTop;
	}

	public void setiTop(int iTop) {
		this.iTop = iTop;
	}

	public char[] getAcStack() {
		return acStack;
	}

	public void setAcStack(char[] acStack) {
		this.acStack = acStack;
	}

	public int getAccessStackCounter() {
		return accessStackCounter;
	}

	public void setAccessStackCounter(int accessStackCounter) {
		this.accessStackCounter = accessStackCounter;
	}





	public static int getMaxSize() {
		return MAX_SIZE;
	}





	class BlockStackOutOfBoundsException extends Exception{

		/**
		 * 
		 */
		private static final long serialVersionUID = 516174375008571794L;

		public BlockStackOutOfBoundsException(){

			super("BlockStack access attempt is out of bounds!");

		}

	}

	class BlockStackEmptyException extends Exception{


		/**
		 * 
		 */
		private static final long serialVersionUID = -7290610426060570375L;




		public BlockStackEmptyException(){

			super("BlockStack is empty!");

		}

	}

	class BlockStackFullException extends Exception{



		/**
		 * 
		 */
		private static final long serialVersionUID = -5388387344560211592L;

		public BlockStackFullException(){

			super("BlockStack is full!");

		}

	}


}

// EOF
