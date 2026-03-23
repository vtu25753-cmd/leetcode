class MyQueue {
     
     Stack<Integer> s1,s2;
    public MyQueue() {
        s1 = new Stack<>();
        s2 = new Stack<>();
    }
    
    public void push(int x) {
        s1.push(x);
        
    }
    
    public int pop() {
        
        if(s2.isEmpty()){
            while(!s1.isEmpty()){
                s2.push(s1.pop());
            }
            
        }
        if(s2.isEmpty()){
            return -1;
        }

        int topelement = s2.pop();
        return topelement;
    }
    
    public int peek() {

        if(s2.isEmpty()){
            while(!s1.isEmpty()){
            s2.push(s1.pop());
            }

        }

        int topelement = s2.peek();
        return topelement;
        
    }
    
    public boolean empty() {
        return s1.isEmpty() && s2.isEmpty();
        
    }
}

/**
 * Your MyQueue object will be instantiated and called as such:
 * MyQueue obj = new MyQueue();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.peek();
 * boolean param_4 = obj.empty();
 */