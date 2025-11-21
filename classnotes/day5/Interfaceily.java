interface Animal {
  void sound();
}

class Dog implements Animal {
  public void sound() {
    System.out.println("Dog barks");
  }
}

public class Interfaceily{
  public static void main(String[] args) {
    Animal a = new Dog(); 
    a.sound();
  }
}
