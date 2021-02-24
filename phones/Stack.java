package phones;

public class Stack <T> {
    int top = 0;
    Object[] items;

    /**
     * Constructor that sets the size of the stack
     * @param sizeOfTheStack
     */
    public Stack(int sizeOfTheStack){
        this.items = new Object[sizeOfTheStack];
    }

    /**
     * Pushes the first element in the stack and increments the top by one, preparing it for the next position in the stack
     * @param itemToBePushed
     */
    public void push(Object itemToBePushed){
        this.items[this.top++] = itemToBePushed;
    }

    /**
     * It's not used anywhere but it's better to have a method that removes the top element in the stack and
     * decrements the top value by one
     */
    public void pop(){
        if(top >= 0){
            items[top--] = null;
        }
    }

    /**
     *
     * @return the number of items in the stack
     */
    public int stackLength(){
        return top;
    }

    /**
     * Checks if the stack is empty
     * @return
     */
    public boolean isEmpty(){
        return top == 0;
    }

    /**
     * Check if the stack is full
     * @return
     */
    public boolean stackIsFull(){
        return (top == items.length - 1);
    }

    /**
     * Gets the wanted item in the stack
     * @param index
     * @return Chosen by the user item
     */
    public T get(int index){
        return (T)this.items[index];
    }
}
