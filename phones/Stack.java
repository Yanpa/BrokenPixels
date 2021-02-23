package phones;

public class Stack <T> {
    int top = 0;
    Object[] items;

    public Stack(int sizeOfTheStack){
        this.items = new Object[sizeOfTheStack];
    }

    public void push(T itemToBePushed){
        items[top] = itemToBePushed;
        top++;
    }

    public void pop(){
        if(top >= 0){
            items[top] = null;
            top--;
        }
    }

    public boolean isEmpty(){
        return top == 0;
    }

    public boolean stackIsFull(){
        return (top == items.length - 1);
    }

}
