package textgen;

import java.util.AbstractList;


/**
 * A class that implements a doubly linked list
 *
 * @param <E> The type of the elements stored in the list
 * @author UC San Diego Intermediate Programming MOOC team
 */
public class MyLinkedList<E> extends AbstractList<E> {
  LLNode<E> head;           // sentinel head
  LLNode<E> tail;           // sentinel tail
  int size;

  /**
   * Create a new empty doublyLinkedList
   */
  public MyLinkedList() {
    this.size = 0;
    this.head = new LLNode<E>(null);
    this.tail = new LLNode<E>(null);

    // Set the initial references
    head.setNext(tail);
    tail.setPrev(head);
  }

  /**
   * Appends an element to the end of the list
   *
   * @param element The element to add
   */
  public boolean add(E element) {
    if (element == null) {
      throw new NullPointerException("Null elements not supported by list.");
    } else {
      final LLNode<E> node = new LLNode<>(element);
      if (tail.data == null) {
        head = tail = node;
      } else {
        tail.next = node;
        node.prev = tail;
        tail = node;
      }
      size++;
      return true;
    }
  }

  /**
   * Get the element at position index
   *
   * @throws IndexOutOfBoundsException if the index is out of bounds.
   */
  public E get(int index) {
    if (index < 0 || index >= size) {
      throw new IndexOutOfBoundsException("Index out of bounds: " + index);
    } else {
      LLNode<E> node;
      node = head;
      while (index > 0) {
        node = node.next;
        index--;
      }
      return node.data;
    }
  }

  public LLNode<E> getNodeByIndex(int index) {
    if (index < 0 || index >= size) {
      throw new IndexOutOfBoundsException("Index out of bounds: " + index);
    } else {
      LLNode<E> node = head;
      while (index > 0) {
        node = node.next;
        index--;
      }
      return node;
    }

  }

  /**
   * Add an element to the list at the specified index
   *
   * @param index   where the element should be added
   * @param element The element to add
   */
  public void addback(int index, E element) {
    if (index < 0 || index > size) {
      throw new IndexOutOfBoundsException("Index out of bounds: " + index);
    } else if (element == null) {
      throw new NullPointerException("Null elements not supported by list.");
    } else if (index == 0) {
      final LLNode<E> node = new LLNode<>(element);
      node.next = head;
      node.prev = node;
      head = node;
      size++;
    } else if (index == size) {
      final LLNode<E> node = new LLNode<>(element);
      tail.next = node;
      node.prev = tail;
      tail = node;
      size++;
    } else {
      final LLNode<E> node = new LLNode<>(element);
      LLNode<E> nextNode = new LLNode<>(null);
      nextNode = head;
      while (index > 0) {
        nextNode = nextNode.next;
        index--;
      }
      nextNode.prev.next = node;
      node.prev = nextNode.prev;
      node.next = nextNode;
      nextNode.prev = node;
      size++;
    }
  }


  public void add(int index, E element) {
    if (index < 0 || index > size) {
      throw new IndexOutOfBoundsException("Index out of bounds: " + index);
    } else if (element == null) {
      throw new NullPointerException("Null elements not supported by list.");
    } else if(index == 0) {
      final LLNode<E> node = new LLNode<>(element);
      node.setNext(head);
      head.setPrev(node);
      head = node;
      size++;
    } else if (index == size){
      final LLNode<E> node = new LLNode<>(element);
      tail.setNext(node);
      node.setPrev(tail);
      tail = node;
      size++;
    } else {
      final LLNode<E> node = new LLNode<>(element);
      LLNode<E> nextNode = new LLNode<>(null);
      nextNode = head;
      while (index > 0) {
        nextNode = nextNode.next;
        index--;
      }
      nextNode.prev.next = node;
      node.prev = nextNode.prev;
      node.next = nextNode;
      nextNode.prev = node;
      size++;
    }

//    This method adds an element at the specified index.
// If elements exist at that index, you will move elements at that
// index (and beyond) up, effectively inserting this element at that location in the list.
// Although drawings are helpful for implementing any method, you will benefit heavily from
// drawing out what should happen in this method before trying to code it.
// You will want to throw an IndexOutOfBoundsException if the index provided is not reasonable for
// inserting an element.
// Optional: After authoring this version of the add method, you could consider removing redundant
// code by having the add method you originally wrote just call this method.
// To test this method, use the method in MyLinkedListTester called testAddAtIndex.
  }


  /**
   * Return the size of the list
   */
  public int size() {
    return size;
  }

  /**
   * Remove a node at the specified index and return its data element.
   *
   * @param index The index of the element to remove
   * @return The data element removed
   * @throws IndexOutOfBoundsException If index is outside the bounds of the list
   */
  public E remove(int index) {
    if (index < 0 || index > (size - 1)) {
      throw new IndexOutOfBoundsException("Index out of bounds: " + index);
    } else {
      LLNode<E> node;
      node = head;
      if (index == 0) {
        size--;
        E data = head.data;
        head = head.next;
        return data;
      } else {
        if (index == size - 1) {
          E data = tail.data;
          tail = tail.prev;
          size--;
          return data;
        } else {
          while (index > 0) {
            node = node.next;
            index--;
          }
          size--;
          E data = node.data;
          node.prev.next = node.next;
          node.next.prev = node.prev;
          return data;
        }
      }

    }
  }

  /**
   * Set an index position in the list to a new element
   *
   * @param index   The index of the element to change
   * @param element The new element
   * @return The element that was replaced
   * @throws IndexOutOfBoundsException if the index is out of bounds.
   */
  public E set(int index, E element) {
    if (index < 0 || index > (this.size - 1)) {
      throw new IndexOutOfBoundsException("Index out of bounds: " + index);
    } else if (element == null) {
      throw new NullPointerException("Null elements not supported by list.");
    } else {
      LLNode<E> node = head;
      while (index > 0) {
        node = node.next;
        index--;
      }
      node.data = element;
      return element;
    }
  }

  // Return the next node based on the referenceNode
  private LLNode<E> getNext(LLNode<E> referenceNode) {
    if (referenceNode == tail) {
      throw new BoundaryViolationException("Reference cannot be equal to tail");
    }
    return referenceNode.getNext();
  }

  // Return the previous node based on the referenceNode
  private LLNode<E> getPrev(LLNode<E> referenceNode) {
    if (referenceNode == head) {
      throw new BoundaryViolationException("Reference cannot be equal to head");
    }
    return referenceNode.getPrev();
  }


  @Override
  public String toString() {
    String result = "MyLinkedList{";
    LLNode<E> probe = head;
    for (int i = 0; i < this.size; i++) {
      result += probe + " ";
      probe = probe.getNext();
    }
    result += '}';
    return result;
  }
}

class LLNode<E> {
  LLNode<E> prev;
  LLNode<E> next;
  E data;

  // Add any other methods you think are useful here
  // E.g. you might want to add another constructor

  public LLNode(E e) {
    this.data = e;
    this.prev = null;
    this.next = null;
  }

  public LLNode(LLNode<E> prev, LLNode<E> next, E data) {
    this.prev = prev;
    this.next = next;
    this.data = data;
  }

  // Get the data stored in this node
  public E getData(){
    return data;
  }

  // Set the element in this node
  public void setData(E data) {
      this.data = data;
  }

  // Get the previous node
  public LLNode<E> getPrev(){
    return prev;
  }

  // Set the previous node
  public void setPrev(LLNode<E> prev) {
    this.prev = prev;
  }

  // Get the next node
  public LLNode<E> getNext(){
    return next;
  }

  // Set the next node
  public void setNext(LLNode<E> next){
    this.next = next;
  }

  @Override
  public String toString() {
    if (data == null) {
      return null;
    } else {
      return data.toString();
    }
  }
}

class BoundaryViolationException extends RuntimeException{
  public BoundaryViolationException(String message) {
    super(message);
  }
}
